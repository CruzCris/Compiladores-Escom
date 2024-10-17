import java.util.*;

public class Estado {
    private static int ContadorIdEstado = 0;
    private int IdEstado, Token;
    private boolean EdoAcept;
    private HashSet<Transicion> Trans = new HashSet<Transicion>();
    private HashSet<Estado> Edos = new HashSet<Estado>();
    
    public int getIdEstado(){
        return this.IdEstado;
    }

    public void setIdEstado(int IdEstado){
        this.IdEstado = IdEstado;
    }

    public HashSet<Transicion> getTrans(){
        return this.Trans;
    }

    public void setTrans(Transicion t){
        this.Trans.add(t);
    }

    public boolean getEdoAcept(){
        return this.EdoAcept;
    }

    public void setEdoAcept(boolean EdoAcept){
        this.EdoAcept = EdoAcept;
    }

    public int getToken(){
        return this.Token;
    }

    public void setToken(int Token){
        this.Token = Token;
    }

    public HashSet<Estado> getEdos(){
        return this.Edos;
    }

    public void setEdos(HashSet<Estado> Edos){
        this.Edos = Edos;
    }

    public Estado(boolean EdoAcept, int Token){
        this.IdEstado = ContadorIdEstado++;
        this.EdoAcept = EdoAcept;
        this.Token = Token;
    }

    public Estado(Transicion t, boolean EdoAcept, int Token){
        this.IdEstado = ContadorIdEstado++;
        this.EdoAcept = EdoAcept;
        this.Token = Token;
        this.Trans.add(t);
    }

    public Estado(boolean EdoAcept, int Token, HashSet<Estado> Edos){
        this.IdEstado = ContadorIdEstado++;
        this.EdoAcept = EdoAcept;
        this.Token = Token;
        this.Edos = Edos;
    }
}
