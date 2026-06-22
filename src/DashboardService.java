import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class DashboardService {
    DashboardService() {}

    static void actualizarDashboard(JTable tabelaStocks,
                                    JTable tabelaAlertas,
                                    JLabel lblValorTotalStock,
                                    JLabel lblValorProdutosActivos,
                                    JLabel lblValorAlertas,
                                    JLabel lblValorFornecedores,
                                    Vector<Produto> vectorProdutos,
                                    Vector<Fornecedor> vectorFornecedores,
                                    DecimalFormat mt,
                                    int limiteReposicao) {
        DefaultTableModel mStock = (DefaultTableModel) tabelaStocks.getModel();
        DefaultTableModel mAlerta = (DefaultTableModel) tabelaAlertas.getModel();
        mStock.setRowCount(0);
        mAlerta.setRowCount(0);

        float totalValor = 0f;
        int numAlertas = 0;
        String hoje = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

        for (int i = 0; i < vectorProdutos.size(); i++) {
            Produto p = vectorProdutos.get(i);
            boolean vencido = estaVencido(p.getValidade(), hoje);
            boolean baixoStock = p.getAmountStock() < limiteReposicao;
            String estado = vencido ? "VENCIDO" : (baixoStock ? "Repor Stock" : "OK");

            mStock.addRow(new Object[]{
                    p.getIdProduct(), p.getTipo(), p.getNome(), p.getCategoria(),
                    mt.format(p.getPrice()), p.getAmountStock(),
                    p.getValidade(), estado
            });

            if (vencido || baixoStock) {
                numAlertas++;
                mAlerta.addRow(new Object[]{
                        p.getNome(), p.getCategoria(), p.getAmountStock(),
                        p.getValidade(),
                        vencido ? "Lixo Perigoso - Descartar" : "Stock Mínimo Atingido"
                });
            }
            totalValor += p.getPrice() * p.getAmountStock();
        }

        lblValorTotalStock.setText(mt.format(totalValor));
        lblValorProdutosActivos.setText("" + vectorProdutos.size());
        lblValorAlertas.setText("" + numAlertas);
        lblValorFornecedores.setText("" + vectorFornecedores.size());
    }

    static boolean estaVencido(String validade, String hoje) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.parse(validade).before(sdf.parse(hoje));
        } catch (Exception e) {
            return false;
        }
    }
}
