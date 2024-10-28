import java.util.*;

public class Home {
    public static void main(String[] args){
        
        int opcion;
        boolean bandera = true;
        ArrayList<AFN> afns = new ArrayList<AFN>();
        Operaciones op = new Operaciones();
        AFD afd = new AFD();
        Scanner sc = new Scanner(System.in);

        while(bandera){
            System.out.println("\tMenu de opciones");
            System.out.println("Elija la opcion que desea utilizar");
            System.out.println("1.- Crear AFN basico\n2.- Unir AFN's\n3.- Concatenar AFN's\n4.- Cerradura de Kleene\n5.- Cerradura positiva\n6.- Opcional\n7.- Unir AFN's para análisis léxico\n8.- Convertir AFN a AFD\n9.- Análisis léxico\n10.- Calculadora\n11.- Salir");
            System.out.println("Hay " + afns.size() + " AFNs");
            opcion = sc.nextInt();
            sc.nextLine();
            int Id1, Id2;
            String cadena;
            switch(opcion){
                case 1:
                    // Crear AFN basico
                    System.out.print("Ingresa el simbolo inferior: ");
                    char SimbInf = sc.next().charAt(0);
                    System.out.print("Ingresa el simbolo superior: ");
                    char SimbSup = sc.next().charAt(0);
                    afns.add(op.CrearBasico(SimbInf, SimbSup));
                    System.out.println("AFN creado");
                    break;
                case 2:
                    // Unir AFN's
                    System.out.println("Ingrese el id del primer AFN");
                    Id1 = sc.nextInt();
                    System.out.println("Ingrese el id del segundo AFN");
                    Id2 = sc.nextInt();
                    afns.set(Id1, op.Unir(afns.get(Id1), afns.get(Id2)));
                    afns.remove(Id2);
                    System.out.println("AFN's unidos");
                    break;
                case 3:
                    // Concatenar AFN's
                    System.out.println("Ingrese el id del primer AFN");
                    Id1 = sc.nextInt();
                    System.out.println("Ingrese el id del segundo AFN");
                    Id2 = sc.nextInt();
                    afns.set(Id1, op.Concatenar(afns.get(Id1), afns.get(Id2)));
                    afns.remove(Id2);
                    System.out.println("AFN's concatenados");
                    break;
                case 4:
                    // Cerradura de Kleene
                    System.out.println("Ingrese el id del AFN");
                    Id1 = sc.nextInt();
                    afns.set(Id1, op.CerraduraKleene(afns.get(Id1)));
                    System.out.println("Cerradura de Kleene aplicada al AFN");
                    break;
                case 5:
                    // Cerradura positiva
                    System.out.println("Ingrese el id del AFN");
                    Id1 = sc.nextInt();
                    afns.set(Id1, op.CerraduraPos(afns.get(Id1)));
                    System.out.println("Cerradura positiva aplicada al AFN");
                    break;
                case 6:
                    // Opcional
                    System.out.println("Ingrese el id del AFN");
                    Id1 = sc.nextInt();
                    afns.set(Id1, op.Opcional(afns.get(Id1)));
                    System.out.println("Opcional aplicado al AFN");
                    break;
                case 7:
                    // Unir AFN's para análisis léxico
                    HashSet<AFN> agregarAFNs = new HashSet<AFN>();
                    ArrayList<AFN> seleccion = new ArrayList<AFN>();
                    int indice = -2;
                    while(indice != -1){
                        System.out.println("\nIntroduce el \u00edndice del AFN a unir\nIntroduce -1 para terminar");
                        indice = sc.nextInt();
                        if(indice > -1){
                            seleccion.add(afns.get(indice));
                        }
                    }
                    for(AFN afn : seleccion){
                        System.out.println("\nIntroduzca el token del AFN " + afns.indexOf(afn));
                        int token = sc.nextInt();
                        HashSet<Estado> eliminarEdos = new HashSet<Estado>();
                        HashSet<Estado> agregarEdos = new HashSet<Estado>();
                        for(Estado edoAcept : afn.getEdosAcept()){
                            eliminarEdos.add(edoAcept);
                            edoAcept.setToken(token);
                            agregarEdos.add(edoAcept);
                        }
                        for(Estado edo : eliminarEdos){
                            afn.removeEdo(edo);
                            afn.removerEdosAcept(edo);
                        }
                        for(Estado edo : agregarEdos){
                            afn.setEdo(edo);
                            afn.setEdosAcept(edo);
                        }
                        agregarAFNs.add(afn);
                    }
                    afns.removeAll(seleccion);
                    afns.add(afd.unirAFNs(agregarAFNs));
                    System.out.println("Union de AFN's para analisis lexico exitoso");
                    break;
                case 8:
                    // Convertir AFN a AFD
                    System.out.println("Ingrese el id del AFN a convertir");
                    Id1 = sc.nextInt();
                    afd.Conversion(afns.get(Id1));
                    System.out.println("Se convirtio el AFN a AFD exitosamente");
                    break;
                case 9:
                    // Análisis léxico
                    System.out.println("\nIntroduzca el nombre del AFD");
                    String nombre = sc.nextLine();
                    System.out.println("\nIngrese la cadena a analizar");
                    AnalizadorLexico lexico = new AnalizadorLexico(nombre, sc.nextLine());
                    boolean continuarLexema = true;
                    while(continuarLexema){
                        int token = lexico.yylex();
                        System.out.println("Token: " + token);
                        System.out.println("Lexema: " + lexico.yytext() + "\n");
                        if(token == 0){
                            continuarLexema = false;
                        }
                    }
                    System.out.println("Analisis lexico exitoso");
                    break;
                case 10: 
                    // Calculadora
                    Calculadora calc = new Calculadora();
                    System.out.println("\nIngrese la cadena a evaluar");
                    cadena = sc.nextLine();
                    calc.descRec("calculadora", cadena);
                    break;
                default:
                    System.exit(0);
                    bandera = false;
                    sc.close();
                    break;
            }
        }
    }
}