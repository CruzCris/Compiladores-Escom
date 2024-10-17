import java.util.*;

public class AFN {
    Estado EdoIni;
    HashSet<Estado> Edos = new HashSet<Estado>();
    HashSet<Estado> EdosAcept = new HashSet<Estado>();
    HashSet<Character> Alfabeto = new HashSet<Character>();

    public Estado getEdoIni(){
        return EdoIni;
    }

    public void setEdoIni(Estado EdoIni){
        this.EdoIni = EdoIni;
    }

    public HashSet<Estado> getEdos(){
        return Edos;
    }

    public HashSet<Estado> getEdosAcept(){
        return EdosAcept;
    }

    public void setEdosAcept(Estado Edo){
        this.EdosAcept.add(Edo);
    }

    public void removerEdosAcept(Estado Edo){
        this.EdosAcept.remove(Edo);
    }
    
    public HashSet<Character> getAlfabeto(){
        return Alfabeto;
    }

    public void setEdo(Estado Edo){
        this.Edos.add(Edo);
    }

    public void removeEdo(Estado Edo){
        this.Edos.remove(Edo);
    }

    public void setSimb(char Simb){
        this.Alfabeto.add(Simb);
    }

    public AFN(){}

    public AFN(Estado EdoIni){
        this.EdoIni = EdoIni;
        this.Edos.add(EdoIni);
    }

    public AFN(Estado EdoIni, Estado EdoAcept){
        this.EdoIni = EdoIni;
        this.Edos.add(EdoIni);
        this.Edos.add(EdoAcept);
        this.EdosAcept.add(EdoAcept);
    }
}
