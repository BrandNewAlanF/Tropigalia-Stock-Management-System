class Fornecedor {
    private String nome,
            contacto,
            endereco,
            metodoDePagamento,
            mercado;
    private short idFornecedor;

    //Formato: nome | contacto | endereco | metodoDePagamento | mercado
    Fornecedor(String nome, String contacto, String endereco, String metodo, String mercado){
        this.nome= nome;
        this.contacto=contacto;
        this.endereco=endereco;
        metodoDePagamento= metodo;
        this.mercado= mercado;
    }

    Fornecedor(){
        this("","","","","");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getMetodoDePagamento() {
        return metodoDePagamento;
    }

    public void setMetodoDePagamento(String metodoDePagamento) {
        this.metodoDePagamento = metodoDePagamento;
    }

    public String getMercado() {
        return mercado;
    }

    public void setMercado(String mercado) {
        this.mercado = mercado;
    }

    @Override
    public String toString() {
        return "Fornecedor{" +
                "nome='" + nome + '\'' +
                ", contacto='" + contacto + '\'' +
                ", endereco='" + endereco + '\'' +
                ", metodoDePagamento='" + metodoDePagamento + '\'' +
                ", mercado='" + mercado + '\'' +
                ", idFornecedor=" + idFornecedor +
                '}';
    }
}