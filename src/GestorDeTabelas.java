import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;
import java.util.Vector;

public class GestorDeTabelas {
    GestorDeTabelas() {}

    static void preencherTabelaClientes(JTable tabelaClientes, Vector<Object[]> vectorClientes) {
        DefaultTableModel modelo = (DefaultTableModel) tabelaClientes.getModel();
        modelo.setRowCount(0);
        for (int i = 0; i < vectorClientes.size(); i++) {
            Object[] p = vectorClientes.get(i);
            modelo.addRow(new Object[]{
                    p.length > 0 ? p[0].toString().trim() : "",
                    p.length > 1 ? p[1].toString().trim() : "",
                    p.length > 2 ? p[2].toString().trim() : "",
                    p.length > 3 ? p[3].toString().trim() : "",
                    p.length > 4 ? p[4].toString().trim() : "",
                    p.length > 5 ? p[5].toString().trim() : "",
                    p.length > 6 ? p[6].toString().trim() : ""
            });
        }
    }

    static void preencherTabelaFornecedores(JTable tabelaFornecedores,
                                            Vector<Fornecedor> vectorFornecedores) {
        DefaultTableModel modelo = (DefaultTableModel) tabelaFornecedores.getModel();
        modelo.setRowCount(0);
        for (int i = 0; i < vectorFornecedores.size(); i++) {
            Fornecedor f = vectorFornecedores.get(i);
            modelo.addRow(new Object[]{
                    f.getNome(), f.getContacto(), f.getEndereco(),
                    f.getMetodoDePagamento(), f.getMercado()
            });
        }
    }

    static void preencherTabelaProdutos(JTable tabelaProdutos,
                                        Vector<Produto> vectorProdutos,
                                        DecimalFormat mt) {
        DefaultTableModel modelo = (DefaultTableModel) tabelaProdutos.getModel();
        modelo.setRowCount(0);

        for (int i = 0; i < vectorProdutos.size(); i++) {
            Produto p = vectorProdutos.get(i);
            modelo.addRow(new Object[]{
                    p.getIdProduct(), p.getTipo(), p.getNome(), p.getDescricao(),
                    p.getCategoria(), mt.format(p.getPrice()),
                    p.getAmountStock(), p.getValidade()
            });
        }
    }
}
