import java.text.DecimalFormat;
class Compra {
    private String nomedoRelacionado, data, metodoP;
    private float valor;
    private short iDdaCompra, idFornecedor;

    Compra(short iDC, float valor,short idFornecedor,String nomedoRelacionado, String data, String metodoP ){
        short IDdaCompra=iDC;
        this.idFornecedor= idFornecedor;
        this.valor=valor;
        this.nomedoRelacionado=nomedoRelacionado;
        this.data=data;
        this.metodoP=metodoP;
    }

    Compra(){
        this((short)0,0,(short)0, "","","");
    }

    public short getiDdaCompra() {
        return iDdaCompra;
    }

    public void setiDdaCompra(short iDdaCompra) {
        this.iDdaCompra = iDdaCompra;
    }

    public short getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(short idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getNomedoRelacionado() {
        return nomedoRelacionado;
    }

    public void setNomedoRelacionado(String nomedoRelacionado) {
        this.nomedoRelacionado = nomedoRelacionado;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMetodoP() {
        return metodoP;
    }

    public void setMetodoP(String metodoP) {
        this.metodoP = metodoP;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public short getIDdaCompra() {
        return iDdaCompra;
    }

    public void setIDdaCompra(short id) {
        this.iDdaCompra = id;
    }
}
