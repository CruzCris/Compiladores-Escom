public class Operaciones {
    
    public AFN CrearBasico(char SimbInf, char SimbSup){
        Estado Edo1 = new Estado(true, -1);
        Transicion Trans = new Transicion(SimbInf, SimbSup, Edo1);
        Estado Edo2 = new Estado(Trans,false,-1);
        AFN afn = new AFN(Edo2,Edo1);
        for(int i=(int)SimbInf; i<=(int)SimbSup; i++){
            afn.setSimbolos((char)i);
        }
        return afn;
    }

    public AFN Unir(AFN afn1, AFN afn2){
        Transicion Trans1 = new Transicion('\0','\0', afn1.getEdoIni());
        Estado Edo1 = new Estado(Trans1, false, -1);
        Transicion Trans2 = new Transicion('\0','\0',afn2.getEdoIni());
        Edo1.setTrans(Trans2);
        afn1.setEdoIni(Edo1);
        afn1.setEdo(Edo1);
        Estado Edo2 = new Estado(true,-1);
        Transicion Trans3 = new Transicion('\0','\0',Edo2);
        for(Estado EstadoActual : afn1.getEdosAcept()){
            EstadoActual.setTrans(Trans3);
            EstadoActual.setEdoAcept(false);
            for(Estado Edo : afn1.getEdos()){ // Revisarrrrrrrrrrrr
                if(EstadoActual.getIdEstado() == Edo.getIdEstado()){
                    Edo.setEdoAcept(false);
                }
            }
            afn1.removerEdosAcept(EstadoActual);
        }
        for(Estado EstadoActual : afn2.getEdosAcept()){
            EstadoActual.setTrans(Trans3);
            EstadoActual.setEdoAcept(false);
            for(Estado Edo : afn2.getEdos()){
                if(EstadoActual.getIdEstado() == Edo.getIdEstado()){
                    Edo.setEdoAcept(false);
                }
                afn1.setEdo(Edo);
            }
        }
        for(char Simbolo : afn2.getAlfabeto()){
            afn1.setSimbolos(Simbolo);
        }
        afn1.setEdo(Edo2);
        afn1.setEdosAcept(Edo2);
        return afn1;
    }

    public AFN Concatenar(AFN afn1, AFN afn2){
        AFN afn = new AFN(afn1.getEdoIni());
        for(Estado Edo : afn1.getEdos()){
            afn.setEdo(Edo);
            for(Transicion Trans : Edo.getTrans()){
                for(Estado EstadoActual : afn1.getEdosAcept()){
                    if(EstadoActual == Trans.getEdo()){
                        EstadoActual.setEdoAcept(false);
                        Trans.setEdo(afn2.getEdoIni());
                        afn.setEdo(EstadoActual);
                    }
                }
            }
        }
        for(Estado Edo : afn2.getEdosAcept()){
            afn.setEdo(Edo);
            afn.setEdosAcept(Edo);
        }
        for(Estado Edo : afn2.getEdos()){
            afn.setEdo(Edo);
        }
        for(char Simb : afn1.getAlfabeto()){
            afn.setSimbolos(Simb);
        }
        for(char Simb : afn2.getAlfabeto()){
            afn.setSimbolos(Simb);
        }
        return afn;
    }

    public AFN Opcional(AFN afn){
        Transicion Trans1 = new Transicion('\0','\0',afn.getEdoIni());
        Estado Estado1 = new Estado(Trans1, false, -1);
        Estado Estado2 = new Estado(true, -1);
        Transicion Trans2 = new Transicion('\0','\0',Estado2);
        Estado1.setTrans(Trans2);
        afn.setEdoIni(Estado1);
        for(Estado Edo : afn.getEdos()){
            for(Transicion Trans : Edo.getTrans()){
                for(Estado EstadoActual : afn.getEdosAcept()){
                    if(EstadoActual == Trans.getEdo()){
                        EstadoActual.setEdoAcept(false);
                        EstadoActual.setTrans(Trans2);
                        afn.removerEdosAcept(EstadoActual);
                    }
                }
            }
        }
        afn.setEdo(Estado1);
        afn.setEdo(Estado2);
        afn.setEdosAcept(Estado2);
        return afn;
    }

    public AFN CerraduraKleene(AFN afn){
        Transicion Trans1 = new Transicion('\0','\0',afn.getEdoIni());
        Estado Estado1 = new Estado(Trans1,false,-1);
        Estado Estado2 = new Estado(true,-1);
        Transicion Trans2 = new Transicion('\0','\0',Estado2);
        Estado1.setTrans(Trans2);
        afn.setEdoIni(Estado1);
        afn.setEdo(Estado1);
        afn.setEdo(Estado2);
        for(Estado Edo : afn.getEdos()){
            for(Transicion Trans : Edo.getTrans()){
                for(Estado EstadoActual : afn.getEdosAcept()){
                    if(EstadoActual == Trans.getEdo()){
                        EstadoActual.setEdoAcept(false);
                        EstadoActual.setTrans(Trans1);
                        EstadoActual.setTrans(Trans2);
                        afn.setEdo(EstadoActual);
                        afn.removerEdosAcept(EstadoActual);
                    }
                }
            }
        }
        afn.setEdosAcept(Estado2);
        return afn;
    }

    public AFN CerraduraPos(AFN afn){
        Transicion Trans1 = new Transicion('\0','\0',afn.getEdoIni());
        Estado Estado1 = new Estado(Trans1,false,-1);
        afn.setEdoIni(Estado1);
        afn.setEdo(Estado1);
        Estado Estado2 = new Estado(true,-1);
        Transicion Trans2 = new Transicion('\0','\0',Estado2);
        for(Estado Edo : afn.getEdos()){
            for(Transicion Trans : Edo.getTrans()){
                for(Estado EstadoActual : afn.getEdosAcept()){
                    if(EstadoActual == Trans.getEdo()){
                        EstadoActual.setEdoAcept(false);
                        EstadoActual.setTrans(Trans1);
                        EstadoActual.setTrans(Trans2);
                        afn.setEdo(EstadoActual);
                        afn.removerEdosAcept(EstadoActual);
                    }
                }
            }
        }
        afn.setEdo(Estado2);
        afn.setEdosAcept(Estado2);
        return afn;
    }

}
