import javax.swing.*;
import java.text.DecimalFormat;
import java.util.Stack;
import java.util.Vector;

public class GestorDeInventario {
    GestorDeInventario() {}

    static void registarEntradaStock(JFrame parent,
                                     JComboBox<String> comboFornProduto,
                                     JTextField campoFornQtd,
                                     JTextField campoFornFornecedor,
                                     JComboBox<String> comboFornMetodo,
                                     JTextField campoFornData,
                                     Vector<Produto> vectorProdutos,
                                     Stack<String> pilhaOperacoes,
                                     JTable tabelaPilha,
                                     JTable tabelaProdutos,
                                     DecimalFormat mt,
                                     double ivaReposicao,
                                     Runnable actualizarDashboard) {
        if (!Validador.comboValido(comboFornProduto)) {
            JOptionPane.showMessageDialog(parent, "Seleccione um produto.");
            return;
        }
        if (!Validador.numeroPositivo(campoFornQtd.getText())) {
            JOptionPane.showMessageDialog(parent, "Quantidade inválida.");
            return;
        }
        if (Validador.campoVazio(campoFornFornecedor.getText())) {
            JOptionPane.showMessageDialog(parent, "Fornecedor obrigatório.");
            return;
        }
        if (!Validador.comboValido(comboFornMetodo)) {
            JOptionPane.showMessageDialog(parent, "Seleccione o método de pagamento.");
            return;
        }
        if (!Validador.dataValida(campoFornData.getText())) {
            JOptionPane.showMessageDialog(parent, "Data inválida. Use dd/MM/yyyy.");
            return;
        }

        short qtd = Short.parseShort(campoFornQtd.getText().trim());
        Produto p = vectorProdutos.get(comboFornProduto.getSelectedIndex() - 1);
        p.setAmountStock((short)(p.getAmountStock() + qtd));

        pilhaOperacoes.push("ENTRADA:" + p.getNome() + ":+" + qtd);
        GestaoDaPila.actualizarTabelaPilha(tabelaPilha, pilhaOperacoes);
        actualizarDashboard.run();
        GestorDeTabelas.preencherTabelaProdutos(tabelaProdutos, vectorProdutos, mt);

        double subtotal = p.getPrice() * qtd;
        double iva = subtotal * ivaReposicao;
        double total = subtotal + iva;

        JOptionPane.showMessageDialog(parent,
                "Entrada registada!\n\n"
                        + "Produto: " + p.getNome()
                        + "\nQuantidade: " + qtd + " un."
                        + "\nSubtotal: " + mt.format(subtotal)
                        + "\nIVA (3%): " + mt.format(iva)
                        + "\nTotal da Reposição: " + mt.format(total)
                        + "\n\nNovo stock: " + p.getAmountStock() + " un.");

        Formularios.limparPainelFornecer(comboFornProduto, campoFornQtd,
                campoFornFornecedor, comboFornMetodo, campoFornData);
    }

    static void registarVenda(JFrame parent,
                              JComboBox<String> comboVendProduto,
                              JTextField campoVendQtd,
                              JTextField campoVendCliente,
                              JTextField campoVendData,
                              JLabel lblVendPrecoUnitario,
                              JLabel lblVendTotal,
                              Vector<Produto> vectorProdutos,
                              Stack<String> pilhaOperacoes,
                              JTable tabelaPilha,
                              JTable tabelaProdutos,
                              DecimalFormat mt,
                              double ivaVenda,
                              Runnable actualizarDashboard) {
        if (!Validador.comboValido(comboVendProduto)) {
            JOptionPane.showMessageDialog(parent, "Seleccione um produto.");
            return;
        }
        if (!Validador.numeroPositivo(campoVendQtd.getText())) {
            JOptionPane.showMessageDialog(parent, "Quantidade inválida.");
            return;
        }
        if (Validador.campoVazio(campoVendCliente.getText())) {
            JOptionPane.showMessageDialog(parent, "Cliente obrigatório.");
            return;
        }
        if (!Validador.dataValida(campoVendData.getText())) {
            JOptionPane.showMessageDialog(parent, "Data inválida. Use dd/MM/yyyy.");
            return;
        }

        short qtd = Short.parseShort(campoVendQtd.getText().trim());
        Produto p = vectorProdutos.get(comboVendProduto.getSelectedIndex() - 1);

        if (qtd > p.getAmountStock()) {
            JOptionPane.showMessageDialog(parent,
                    "Quantidade superior ao stock disponível.");
            return;
        }

        p.setAmountStock((short)(p.getAmountStock() - qtd));

        pilhaOperacoes.push("VENDA:" + p.getNome() + ":-" + qtd);
        GestaoDaPila.actualizarTabelaPilha(tabelaPilha, pilhaOperacoes);
        actualizarDashboard.run();
        GestorDeTabelas.preencherTabelaProdutos(tabelaProdutos, vectorProdutos, mt);

        double subtotal = p.getPrice() * qtd;
        double iva = subtotal * ivaVenda;
        double total = subtotal + iva;

        JOptionPane.showMessageDialog(parent,
                "Venda registada!\n\n"
                        + "Produto: " + p.getNome()
                        + "\nQuantidade: " + qtd + " un."
                        + "\nSubtotal: " + mt.format(subtotal)
                        + "\nIVA (17%): " + mt.format(iva)
                        + "\nTotal da Venda: " + mt.format(total)
                        + "\n\nNovo stock: " + p.getAmountStock());

        Formularios.limparPainelVendas(comboVendProduto, campoVendQtd,
                campoVendCliente, campoVendData, lblVendPrecoUnitario, lblVendTotal);
    }

    static void calcularTotalVenda(JComboBox<String> comboVendProduto,
                                   JTextField campoVendQtd,
                                   JLabel lblVendPrecoUnitario,
                                   JLabel lblVendTotal,
                                   Vector<Produto> vectorProdutos,
                                   DecimalFormat mt) {
        int idx = comboVendProduto.getSelectedIndex();
        if (idx <= 0) {
            lblVendPrecoUnitario.setText("—");
            lblVendTotal.setText("—");
            return;
        }

        try {
            Produto p = vectorProdutos.get(idx - 1);
            lblVendPrecoUnitario.setText(mt.format(p.getPrice()));
            String qs = campoVendQtd.getText().trim();
            if (!qs.isEmpty()) {
                lblVendTotal.setText(String.format("%.2f MT", p.getPrice() * Integer.parseInt(qs)));
            }
        } catch (Exception ex) {
            lblVendTotal.setText("—");
        }
    }
}
