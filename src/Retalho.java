public class Retalho extends Cliente {
    private short areaComercial;
    private String publicoAlvo;

    Retalho(String nuit, String nome, String tipoC, String endereco, String email,
            String publicoAlvo, short areaComercial ){
        super("","","","","");
        this.areaComercial=areaComercial;
        this.publicoAlvo=publicoAlvo;
    }

    Retalho(){ this("","","","","","", (short)0);}

    public short getAreaComercial() {return areaComercial;}
    public void setAreaComercial(short areaComercial) {this.areaComercial = areaComercial;}
    public String getPublicoAlvo() {return publicoAlvo;}
    public void setPublicoAlvo(String publicoAlvo) {this.publicoAlvo = publicoAlvo;}
}