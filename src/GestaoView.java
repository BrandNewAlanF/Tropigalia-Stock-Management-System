import javax.swing.*;
import java.text.DecimalFormat;
import java.util.Stack;
import java.util.Vector;

public interface GestaoView {
    JFrame getFrame();

    Vector<Produto> getProdutos();
    Vector<Fornecedor> getFornecedores();
    Vector<Object[]> getClientes();
    Stack<String> getPilhaOperacoes();
    DecimalFormat getMoedaFormat();

    int getLimiteReposicao();
    double getIvaReposicao();
    double getIvaVenda();

    JTextField getCampoProdID();
    JTextField getCampoProdTipo();
    JTextField getCampoProdNome();
    JTextField getCampoProdDesc();
    JTextField getCampoProdCat();
    JTextField getCampoProdPreco();
    JTextField getCampoProdQtd();
    JTextField getCampoProdValidade();
    JTable getTabelaProdutos();

    JComboBox<String> getComboFornProduto();
    JTextField getCampoFornQtd();
    JTextField getCampoFornFornecedor();
    JComboBox<String> getComboFornMetodo();
    JTextField getCampoFornData();

    JComboBox<String> getComboVendProduto();
    JTextField getCampoVendQtd();
    JTextField getCampoVendCliente();
    JTextField getCampoVendData();
    JLabel getLblVendPrecoUnitario();
    JLabel getLblVendTotal();

    JTextField getCampoFornNome();
    JTextField getCampoFornContacto();
    JTextField getCampoFornEndereco();
    JTextField getCampoFornMetodoPag();
    JTextField getCampoFornMercado();
    JTable getTabelaFornecedores();

    JTable getTabelaClientes();
    JTable getTabelaStocks();
    JTable getTabelaAlertas();
    JLabel getLblValorTotalStock();
    JLabel getLblValorProdutosActivos();
    JLabel getLblValorAlertas();
    JLabel getLblValorFornecedores();

    JTable getTabelaRelatorio();
    JComboBox<String> getComboTipoRelatorio();
    JTable getTabelaPilha();

    void actualizarDashboard();
    void actualizarCombos();
}
