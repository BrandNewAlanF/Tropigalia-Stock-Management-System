import javax.swing.*;

public class Formularios {
    Formularios() {}

    static void limparCamposProduto(JTextField campoProdID,
                                    JTextField campoProdTipo,
                                    JTextField campoProdNome,
                                    JTextField campoProdDesc,
                                    JTextField campoProdCat,
                                    JTextField campoProdPreco,
                                    JTextField campoProdQtd,
                                    JTextField campoProdValidade) {
        campoProdID.setText("");
        campoProdTipo.setText("");
        campoProdNome.setText("");
        campoProdDesc.setText("");
        campoProdCat.setText("");
        campoProdPreco.setText("");
        campoProdQtd.setText("");
        campoProdValidade.setText("");
    }

    static void limparPainelFornecer(JComboBox<String> comboFornProduto,
                                     JTextField campoFornQtd,
                                     JTextField campoFornFornecedor,
                                     JComboBox<String> comboFornMetodo,
                                     JTextField campoFornData) {
        comboFornProduto.setSelectedIndex(0);
        campoFornQtd.setText("");
        campoFornFornecedor.setText("");
        comboFornMetodo.setSelectedIndex(0);
        campoFornData.setText("");
    }

    static void limparPainelVendas(JComboBox<String> comboVendProduto,
                                   JTextField campoVendQtd,
                                   JTextField campoVendCliente,
                                   JTextField campoVendData,
                                   JLabel lblVendPrecoUnitario,
                                   JLabel lblVendTotal) {
        comboVendProduto.setSelectedIndex(0);
        campoVendQtd.setText("");
        campoVendCliente.setText("");
        campoVendData.setText("");
        lblVendPrecoUnitario.setText("—");
        lblVendTotal.setText("—");
    }
}
