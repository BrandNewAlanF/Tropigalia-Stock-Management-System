import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class GestorDeRelatorios {
    GestorDeRelatorios() {}

    static void gerarRelatorio(JComboBox<String> comboTipoRelatorio,
                               JTable tabelaRelatorio,
                               Vector<Produto> vectorProdutos,
                               Vector<Fornecedor> vectorFornecedores,
                               Vector<Object[]> vectorClientes,
                               DecimalFormat mt,
                               int limiteReposicao) {
        int tipo = comboTipoRelatorio.getSelectedIndex();
        DefaultTableModel m = (DefaultTableModel) tabelaRelatorio.getModel();
        m.setRowCount(0);
        String hoje = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

        switch (tipo) {
            case 0:
                m.setColumnIdentifiers(new String[]{"ID","Nome","Categoria","Preço (MT)","Stock","Validade"});
                for (int i = 0; i < vectorProdutos.size(); i++) {
                    Produto p = vectorProdutos.get(i);
                    m.addRow(new Object[]{
                            p.getIdProduct(), p.getNome(), p.getCategoria(),
                            mt.format(p.getPrice()), p.getAmountStock(), p.getValidade()
                    });
                }
                break;
            case 1:
                m.setColumnIdentifiers(new String[]{"Nome","Categoria","Stock","Validade","Situação"});
                for (int i = 0; i < vectorProdutos.size(); i++) {
                    Produto p = vectorProdutos.get(i);
                    if (DashboardService.estaVencido(p.getValidade(), hoje)) {
                        m.addRow(new Object[]{p.getNome(), p.getCategoria(),
                                p.getAmountStock(), p.getValidade(), "LIXO PERIGOSO - Descartar"});
                    }
                }
                break;
            case 2:
                m.setColumnIdentifiers(new String[]{"Nome","Categoria","Stock Actual","Mínimo","Acção"});
                for (int i = 0; i < vectorProdutos.size(); i++) {
                    Produto p = vectorProdutos.get(i);
                    if (p.getAmountStock() < limiteReposicao) {
                        m.addRow(new Object[]{p.getNome(), p.getCategoria(),
                                p.getAmountStock(), limiteReposicao, "Encomendar Urgente"});
                    }
                }
                break;
            case 3:
                m.setColumnIdentifiers(new String[]{"Nome","Contacto","Endereço","Pagamento","Mercado"});
                for (int i = 0; i < vectorFornecedores.size(); i++) {
                    Fornecedor f = vectorFornecedores.get(i);
                    m.addRow(new Object[]{f.getNome(), f.getContacto(),
                            f.getEndereco(), f.getMetodoDePagamento(), f.getMercado()});
                }
                break;
            case 4:
                m.setColumnIdentifiers(new String[]{"NUIT","Nome","Tipo","Endereço","Contacto"});
                for (int i = 0; i < vectorClientes.size(); i++) {
                    Object[] c = vectorClientes.get(i);
                    m.addRow(new Object[]{
                            c.length > 0 ? c[0] : "", c.length > 1 ? c[1] : "",
                            c.length > 2 ? c[2] : "", c.length > 3 ? c[3] : "",
                            c.length > 5 ? c[5] : ""});
                }
                break;
            default:
                break;
        }
    }

    static void mostrarAlertas(JFrame parent,
                               Vector<Produto> vectorProdutos,
                               int limiteReposicao) {
        String hoje = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        StringBuilder sb = new StringBuilder("=== ALERTAS DO SISTEMA ===\n\n");
        int cont = 0;
        for (int i = 0; i < vectorProdutos.size(); i++) {
            Produto p = vectorProdutos.get(i);
            if (DashboardService.estaVencido(p.getValidade(), hoje)) {
                sb.append("VENCIDO: ").append(p.getNome()).append(" (").append(p.getValidade()).append(")\n");
                cont++;
            } else if (p.getAmountStock() < limiteReposicao) {
                sb.append("STOCK BAIXO: ").append(p.getNome()).append(" (").append(p.getAmountStock()).append(" un)\n");
                cont++;
            }
        }
        if (cont == 0) {
            sb.append("Sem alertas activos. Todos os stocks estão OK.");
        }
        JOptionPane.showMessageDialog(parent, sb.toString(), "Alertas", JOptionPane.WARNING_MESSAGE);
    }
}
