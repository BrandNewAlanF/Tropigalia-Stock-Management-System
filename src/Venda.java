public class Venda {
    private String nomeProduto, datadaVenda;
    private float valor;
    private short quantidade, idDaVenda, idDoProduto;

    Venda(short idDaVenda,short idDoProduto, String produto, short quant,
          float valor, String datadaVenda ){
        this.idDaVenda=idDaVenda;
        this.idDoProduto=idDoProduto;
        nomeProduto=produto;
        quantidade=quant;
        this.valor=valor;
        this.datadaVenda=datadaVenda;
    }

    Venda(){
        this((short)0,(short)0,"",(short)0,(short)0,"");
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDatadaVenda() {
        return datadaVenda;
    }

    public void setDatadaVenda(String datadaVenda) {
        this.datadaVenda = datadaVenda;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public short getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(short quantidade) {
        this.quantidade = quantidade;
    }

    public short getIdDaVenda() {
        return idDaVenda;
    }

    public void setIdDaVenda(short idDaVenda) {
        this.idDaVenda = idDaVenda;
    }

    public short getIdDoProduto() {
        return idDoProduto;
    }

    public void setIdDoProduto(short idDoProduto) {
        this.idDoProduto = idDoProduto;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "nomeProduto='" + nomeProduto + '\'' +
                ", datadaVenda='" + datadaVenda + '\'' +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                ", idDaVenda=" + idDaVenda +
                ", idDoProduto=" + idDoProduto +
                '}';
    }
}