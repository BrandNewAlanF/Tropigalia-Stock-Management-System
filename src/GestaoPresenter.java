public class GestaoPresenter {
    private final GestaoView view;

    public GestaoPresenter(GestaoView view) {
        this.view = view;
    }

    public void adicionarFornecedor() {
        GestorDeFornecedores.adicionarFornecedor(view.getFrame(), view.getCampoFornNome(),
                view.getCampoFornContacto(), view.getCampoFornEndereco(),
                view.getCampoFornMetodoPag(), view.getCampoFornMercado(),
                view.getFornecedores(), view.getPilhaOperacoes(),
                view.getTabelaPilha(), view.getTabelaFornecedores(),
                view::actualizarDashboard);
    }

    public void adicionarProduto() {
        GestaoDeProdutos.adicionarProduto(view.getFrame(), view.getCampoProdID(),
                view.getCampoProdTipo(), view.getCampoProdNome(),
                view.getCampoProdDesc(), view.getCampoProdCat(),
                view.getCampoProdPreco(), view.getCampoProdQtd(),
                view.getCampoProdValidade(), view.getProdutos(),
                view.getPilhaOperacoes(), view.getTabelaPilha(),
                view.getTabelaProdutos(), view.getMoedaFormat(),
                view::actualizarDashboard, view::actualizarCombos);
    }

    public void removerProduto() {
        GestaoDeProdutos.removerProduto(view.getFrame(), view.getTabelaProdutos(),
                view.getProdutos(), view.getPilhaOperacoes(), view.getTabelaPilha(),
                view.getMoedaFormat(), view::actualizarDashboard,
                view::actualizarCombos);
    }

    public void registarEntradaStock() {
        GestorDeInventario.registarEntradaStock(view.getFrame(), view.getComboFornProduto(),
                view.getCampoFornQtd(), view.getCampoFornFornecedor(),
                view.getComboFornMetodo(), view.getCampoFornData(),
                view.getProdutos(), view.getPilhaOperacoes(), view.getTabelaPilha(),
                view.getTabelaProdutos(), view.getMoedaFormat(),
                view.getIvaReposicao(), view::actualizarDashboard);
    }

    public void registarVenda() {
        GestorDeInventario.registarVenda(view.getFrame(), view.getComboVendProduto(),
                view.getCampoVendQtd(), view.getCampoVendCliente(),
                view.getCampoVendData(), view.getLblVendPrecoUnitario(),
                view.getLblVendTotal(), view.getProdutos(), view.getPilhaOperacoes(),
                view.getTabelaPilha(), view.getTabelaProdutos(), view.getMoedaFormat(),
                view.getIvaVenda(), view::actualizarDashboard);
    }

    public void calcularTotalVenda() {
        GestorDeInventario.calcularTotalVenda(view.getComboVendProduto(),
                view.getCampoVendQtd(), view.getLblVendPrecoUnitario(),
                view.getLblVendTotal(), view.getProdutos(), view.getMoedaFormat());
    }

    public void gerarRelatorio() {
        GestorDeRelatorios.gerarRelatorio(view.getComboTipoRelatorio(),
                view.getTabelaRelatorio(), view.getProdutos(), view.getFornecedores(),
                view.getClientes(), view.getMoedaFormat(), view.getLimiteReposicao());
    }

    public void mostrarAlertas() {
        GestorDeRelatorios.mostrarAlertas(view.getFrame(), view.getProdutos(),
                view.getLimiteReposicao());
    }

    public void desfazerUltimaOperacao() {
        GestaoDaPila.desfazerUltimaOperacao(view.getFrame(), view.getTabelaPilha(),
                view.getPilhaOperacoes());
    }
}
