public class Transicion {
    private char SimbInf, SimbSup;
    private Estado Edo;
    private int PosEdo;

    public char getSimbInf(){
        return this.SimbInf;
    }

    public void setSimbInf(char SimbInf){
        this.SimbInf = SimbInf;
    }

    public char getSimbSup(){
        return this.SimbSup;
    }

    public void setSimbSup(char SimbSup){
        this.SimbSup = SimbSup;
    }

    public Estado getEdo(){
        return this.Edo;
    }

    public void setEdo(Estado Edo){
        this.Edo = Edo;
    }

    public int getPosEdo(){
        return this.PosEdo;
    }

    public Transicion(char SimbInf, char SimbSup, Estado Edo){
        this.SimbInf = SimbInf;
        this.SimbSup = SimbSup;
        this.Edo = Edo;
    }

    public Transicion(char Simb, int Pos){
        this.SimbInf = Simb;
        this.PosEdo = Pos;
    }
}
