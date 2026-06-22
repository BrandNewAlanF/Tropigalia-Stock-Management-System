public class Cliente {
    protected String nome, endereco, contacto, email, tipoCliente, nuit;

    Cliente(String nuit, String nome, String tipoC, String endereco, String email){
        this.nuit=nuit;
        this.nome=nome;
        tipoCliente=tipoC;
        this.endereco=endereco;
        this.email=email;
    }

    Cliente(){
        this("","","","","");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getNuit() {
        return nuit;
    }

    public void setNuit(String nuit) {
        this.nuit = nuit;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", contacto='" + contacto + '\'' +
                ", email='" + email + '\'' +
                ", tipoCliente='" + tipoCliente + '\'' +
                ", nuit='" + nuit + '\'' +
                '}';
    }
}