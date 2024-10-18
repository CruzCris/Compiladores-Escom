public class Operaciones {
    
    public AFN CrearBasico(char SimbInf, char SimbSup){
        Estado Edo1 = new Estado(true, -1);
        Transicion Trans = new Transicion(SimbInf, SimbSup, Edo1);
        Estado Edo2 = new Estado(Trans,false,-1);
        AFN afn = new AFN(Edo2,Edo1);
        for(int i=(int)SimbInf; i<=(int)SimbSup; i++){
            afn.setSimb((char)i);
        }
        //System.out.println("AFN basico creado, con el id")
        return afn;
    }

    public AFN Unir(AFN afn1, AFN afn2){
        Transicion Trans1 = new Transicion('\0','\0', afn1.getEdoIni());
        Estado Edo1 = new Estado(Trans1, false, -1);
        Transicion Trans2 = new Transicion('\0','\0',afn2.getEdoIni());
        Edo1.setTrans(Trans2);
        Estado Edo2 = new Estado(true,-1);
        Transicion Trans3 = new Transicion('\0','\0',Edo2);
        afn1.setEdoIni(Edo1);
        afn1.setEdo(Edo1);
        for(Estado EstadoA : afn1.getEdosAcept()){
            EstadoA.setTrans(Trans3);
            EstadoA.setEdoAcept(false);
            for(Estado Edo : afn1.getEdos()){ // Revisar
                if(EstadoA.getIdEstado() == Edo.getIdEstado()){
                    Edo.setEdoAcept(false);
                }
            }
            afn1.removerEdosAcept(EstadoA);
        }
        for(Estado EstadoA : afn2.getEdosAcept()){
            EstadoA.setTrans(Trans3);
            EstadoA.setEdoAcept(false);
            for(Estado Edo : afn2.getEdos()){
                if(EstadoA.getIdEstado() == Edo.getIdEstado()){
                    Edo.setEdoAcept(false);
                }
                afn1.setEdo(Edo);
            }
        }
        for(char Simb : afn2.getAlfabeto()){
            afn1.setSimb(Simb);
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
                for(Estado EstadoA : afn1.getEdosAcept()){
                    if(EstadoA == Trans.getEdo()){
                        EstadoA.setEdoAcept(false);
                        Trans.setEdo(afn1.getEdoIni());
                        afn.setEdo(EstadoA);
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
            afn.setSimb(Simb);
        }
        for(char Simb : afn2.getAlfabeto()){
            afn.setSimb(Simb);
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
                for(Estado EstadoA : afn.getEdosAcept()){
                    if(EstadoA == Trans.getEdo()){
                        EstadoA.setEdoAcept(false);
                        EstadoA.setTrans(Trans2);
                        afn.removerEdosAcept(EstadoA);
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
                for(Estado EstadoA : afn.getEdosAcept()){
                    if(EstadoA == Trans.getEdo()){
                        EstadoA.setEdoAcept(false);
                        EstadoA.setTrans(Trans1);
                        EstadoA.setTrans(Trans2);
                        afn.setEdo(EstadoA);
                        afn.removerEdosAcept(EstadoA);
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
                for(Estado EstadoA : afn.getEdosAcept()){
                    if(EstadoA == Trans.getEdo()){
                        EstadoA.setEdoAcept(false);
                        EstadoA.setTrans(Trans1);
                        EstadoA.setTrans(Trans2);
                        afn.setEdo(EstadoA);
                        afn.removerEdosAcept(EstadoA);
                    }
                }
            }
        }
        afn.setEdo(Estado2);
        afn.setEdosAcept(Estado2);
        return afn;
    }

}
