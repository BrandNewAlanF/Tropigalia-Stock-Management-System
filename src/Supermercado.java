public class Supermercado extends Cliente{
    private short subfiliais;
    Supermercado(String nuit, String nome, String tipoC, String endereco, String email, short subfiliais){
        super(nuit, nome, tipoC, endereco, email);
        this.subfiliais=subfiliais;
    }

    Supermercado(){
        this("","","","","",(short)0);
    }

    public short getSubfiliais() {
        return subfiliais;
    }

    public void setSubfiliais(short subfiliais) {
        this.subfiliais = subfiliais;
    }

    @Override
    public String toString() {
        return super.toString()+"\nSupermercado{" +
                "\n````````````````Subfiliais=" + subfiliais +
                '}';
    }
}
