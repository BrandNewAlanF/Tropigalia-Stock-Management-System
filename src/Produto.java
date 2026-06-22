class Produto {
   protected short idProduct, amountStock;
   protected String nome,tipo,descricao, categoria, validade;
   protected float price;

   //Formato: idProduct | tipo | name | descricao | categoria | price | amountStock | validade
   Produto(short idProduct, String tipo, String name, String descricao, String categoria, float price, short amountStock, String validade ){
      this.idProduct= idProduct;
      this.tipo=tipo;
      nome=name;
      this.descricao=descricao;
      this.categoria=categoria;
      this.price=price;
      this.amountStock=amountStock;
      this.validade=validade;
   }

   Produto(){
      this((short) 0,"","","","", (float) 0, (short) 0,"");
   }

   //GETTERS:
   public short getIdProduct() {return idProduct;}
   public short getAmountStock() {return amountStock;}
   public String getNome() {return nome;}
   public String getDescricao() {return descricao;}
   public String getCategoria() {return categoria;}
   public float getPrice() {return price;}

   public String getTipo() {
      return tipo;
   }

   public void setTipo(String tipo) {
      this.tipo = tipo;
   }

   // SETTERS:
   public void setIdProduct(byte idProduct) {this.idProduct = idProduct;}
   public void setAmountStock(byte amountStock) {this.amountStock = amountStock;}
   public void setNome(String nome) {this.nome = nome;}
   public void setDescricao(String descricao) {this.descricao = descricao;}
   public void setCategoria(String categoria) {this.categoria = categoria;}
   public void setPrice(float price) {this.price = price;}


   public void setIdProduct(short idProduct) {
      this.idProduct = idProduct;
   }

   public void setAmountStock(short amountStock) {
      this.amountStock = amountStock;
   }

   public String getValidade() {
      return validade;
   }

   public void setValidade(String validade) {
      this.validade = validade;
   }
}