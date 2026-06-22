public class Horeca extends Cliente{
    private String tipoDeServico;
    Horeca(String nuit, String nome, String tipoC, String endereco, String email, String servico){
        super(nuit, nome, tipoC, endereco, email);
        tipoDeServico=servico;
    }

    Horeca(){this("","","","","","");}

    public String getTipoDeServico() {return tipoDeServico;}
    public void setTipoDeServico(String tipoDeServico) {this.tipoDeServico = tipoDeServico;}

    @Override
    public String toString() {
        return super.toString()+ "Horeca{" +
                "tipoDeServico='" + tipoDeServico;
    }
}