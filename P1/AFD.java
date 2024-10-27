import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class AFD {

    public HashSet<Estado> CEpsilon(HashSet<Estado> Edos, int IdEdo){
        HashSet<Estado> EdosCEpsilon = new HashSet<Estado>();
        Stack<Estado> PilaEdos = new Stack<Estado>();
        Estado Actual = new Estado(false,-1);
        for(Estado Edo : Edos){
            if(Edo.getIdEstado() == IdEdo){
                PilaEdos.push(Edo);
                while(!PilaEdos.empty()){
                    Actual = PilaEdos.pop();
                    EdosCEpsilon.add(Actual);
                    for(Transicion Trans : Actual.getTrans()){
                        if(Trans.getSimbInf() == '\0' && Trans.getSimbSup() == '\0'){
                            for(Estado EdoTrans : Edos){
                                if(EdoTrans.getIdEstado() == Trans.getEdo().getIdEstado() && !EdosCEpsilon.contains(EdoTrans)){
                                    PilaEdos.push(EdoTrans);
                                }
                            }
                        }
                    }
                }
            }
        }
        return EdosCEpsilon;
    }

    public HashSet<Estado> Mover(HashSet<Estado> Edos, char Simb){
        HashSet<Estado> EdosFinal = new HashSet<Estado>();
        for(Estado Edo : Edos){
            for(Transicion Trans : Edo.getTrans()){
                if((int) Trans.getSimbInf() <= (int) Simb && (int) Trans.getSimbSup() >= (int) Simb){
                    EdosFinal.add(Trans.getEdo());
                }
            }
        }
        return EdosFinal;
    }

    public HashSet<Estado> IrA(HashSet<Estado> Edos, HashSet<Estado> EdosAFN, char Simb){
        HashSet<Estado> EdosIrA = new HashSet<Estado>();
        HashSet<Estado> EdoAux = new HashSet<Estado>();
        EdoAux = Mover(Edos,Simb);
        for(Estado Aux : EdoAux){
            EdosIrA.addAll(CEpsilon(EdosAFN,Aux.getIdEstado()));
        }
        return EdosIrA;
    }
    
    public String Conversion(AFN afn){
        HashSet<Estado> EdoAux = new HashSet<>();
        Queue<HashSet<Estado>> ColaEdos = new LinkedList<>();
        HashSet<HashSet<Estado>> ConjuntoEdos = new HashSet<HashSet<Estado>>();
        ArrayList<Estado> EdosAFD = new ArrayList<>();

        HashSet<Estado> Edos = CEpsilon(afn.getEdos(),afn.getEdoIni().getIdEstado());
        ColaEdos.add(Edos);
        ConjuntoEdos.add(Edos);
        Estado EdoIniAFD = new Estado(false,-1,Edos);
        EdosAFD.add(EdoIniAFD);

        while(!ColaEdos.isEmpty()){
            Edos = ColaEdos.poll();
            for(char Simb : afn.getAlfabeto()){
                EdoAux = IrA(Edos,afn.getEdos(),Simb);
                boolean contiene = false;
                for(HashSet<Estado> EstadosConjunto : ConjuntoEdos){
                    if(EstadosConjunto.equals(EdoAux)){
                        contiene = true;
                    }
                }
                if(!contiene && !EdoAux.isEmpty()){
                    ColaEdos.add(EdoAux);
                    ConjuntoEdos.add(EdoAux);
                    boolean EstadoAcept = false;
                    int Token = -1;
                    for(Estado Edo : EdoAux){
                        if(Edo.getEdoAcept()){
                            EstadoAcept = true;
                            Token = Edo.getToken();
                        }
                    }
                    Estado EstadoAFD = new Estado(EstadoAcept,Token,EdoAux);
                    Estado QuitarEstado = new Estado(false,-1);
                    Estado AgregarEstado = new Estado(false,-1);
                    EdosAFD.add(EstadoAFD);
                    for(Estado Actual : EdosAFD){
                        if(Actual.getEdos().equals(Edos)){
                            QuitarEstado = Actual;
                            Transicion Trans = new Transicion(Simb,EdosAFD.lastIndexOf(EstadoAFD));
                            Actual.setTrans(Trans);
                            AgregarEstado = Actual;
                        }
                    }
                    EdosAFD.set(EdosAFD.indexOf(QuitarEstado),AgregarEstado);
                }else if(!EdoAux.isEmpty()){
                    Estado QuitarEstado = new Estado(false,-1);
                    Estado AgregarEstado = new Estado(false,-1);
                    for(Estado Actual : EdosAFD){
                        if(Edos.equals(Actual.getEdos())){
                            for(Estado EdoTrans : EdosAFD){
                                if(EdoAux.equals(EdoTrans.getEdos())){
                                    QuitarEstado = Actual;
                                    Transicion Trans = new Transicion(Simb,EdosAFD.lastIndexOf(EdoTrans));
                                    Actual.setTrans(Trans);
                                    AgregarEstado = Actual;
                                }
                            }
                        }
                    }
                    EdosAFD.set(EdosAFD.indexOf(QuitarEstado),AgregarEstado);
                }
            }
        }

        int tabla[][] = new int[EdosAFD.size()][128];
        for(int i=0; i<EdosAFD.size(); i++){
            for(int j=0; j<128; j++){
                tabla[i][j] = -1;
            }
        }

        for(Estado Edo : EdosAFD){
            for(Transicion Trans : Edo.getTrans()){
                tabla[EdosAFD.lastIndexOf(Edo)][(int) Trans.getSimbInf()] = Trans.getPosEdo();
            }
            tabla[EdosAFD.lastIndexOf(Edo)][127] = Edo.getToken();
        }

        // Crear archivo de salida

        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el nombre del archivo de salida");
        String nombre = sc.nextLine();
        File archivo = new File(nombre+".txt");

        try{
            archivo.createNewFile();
            FileWriter fw = new FileWriter(nombre+".txt");
            for(int i=0; i<EdosAFD.size(); i++){
                String linea = "";
                for(int j=0; j<128; j++){
                    linea = linea + tabla[i][j] + ';';
                }
                fw.write(linea + '\n');
            }
            fw.close();
        }catch(IOException e){
            System.out.println("Error al crear el archivo");
            e.printStackTrace();
        }
        //sc.close();
        return nombre;
    }

}
