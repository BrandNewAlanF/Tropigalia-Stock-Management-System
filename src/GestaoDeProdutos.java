import javax.swing.*;
import java.text.DecimalFormat;
import java.util.Stack;
import java.util.Vector;

public class GestaoDeProdutos {
    GestaoDeProdutos() {}

    static void adicionarProduto(JFrame parent,
                                 JTextField campoProdID,
                                 JTextField campoProdTipo,
                                 JTextField campoProdNome,
                                 JTextField campoProdDesc,
                                 JTextField campoProdCat,
                                 JTextField campoProdPreco,
                                 JTextField campoProdQtd,
                                 JTextField campoProdValidade,
                                 Vector<Produto> vectorProdutos,
                                 Stack<String> pilhaOperacoes,
                                 JTable tabelaPilha,
                                 JTable tabelaProdutos,
                                 DecimalFormat mt,
                                 Runnable actualizarDashboard,
                                 Runnable actualizarCombos) {
        try {
            String idStr = campoProdID.getText().trim();
            String tipo = campoProdTipo.getText().trim();
            String nome = campoProdNome.getText().trim();
            String desc = campoProdDesc.getText().trim();
            String cat = campoProdCat.getText().trim();
            String precoStr = campoProdPreco.getText().trim();
            String qtdStr = campoProdQtd.getText().trim();
            String validade = campoProdValidade.getText().trim();

            if (Validador.campoVazio(idStr) || Validador.campoVazio(tipo)
                    || Validador.campoVazio(nome) || Validador.campoVazio(desc)
                    || Validador.campoVazio(cat) || Validador.campoVazio(precoStr)
                    || Validador.campoVazio(qtdStr) || Validador.campoVazio(validade)) {
                JOptionPane.showMessageDialog(parent,
                        "Preencha todos os campos do produto.");
                return;
            }

            if (!Validador.numeroPositivo(idStr) || !Validador.numeroPositivo(qtdStr)) {
                JOptionPane.showMessageDialog(parent,
                        "ID e Quantidade devem ser positivos.");
                return;
            }

            float preco = Float.parseFloat(precoStr);
            if (preco <= 0) {
                JOptionPane.showMessageDialog(parent,
                        "O preço deve ser maior que zero.");
                return;
            }

            if (!Validador.dataValida(validade)) {
                JOptionPane.showMessageDialog(parent,
                        "Data inválida. Use dd/MM/yyyy.");
                return;
            }

            short id = Short.parseShort(idStr);
            for (int i = 0; i < vectorProdutos.size(); i++) {
                if (vectorProdutos.get(i).getIdProduct() == id) {
                    JOptionPane.showMessageDialog(parent,
                            "Já existe um produto com esse ID.");
                    return;
                }
            }

            short qtd = Short.parseShort(qtdStr);
            Produto novo = new Produto(id, tipo, nome, desc, cat, preco, qtd, validade);
            vectorProdutos.add(novo);

            pilhaOperacoes.push("ADICIONAR_PRODUTO:" + nome);
            GestaoDaPila.actualizarTabelaPilha(tabelaPilha, pilhaOperacoes);
            GestorDeTabelas.preencherTabelaProdutos(tabelaProdutos, vectorProdutos, mt);
            actualizarDashboard.run();
            actualizarCombos.run();

            JOptionPane.showMessageDialog(parent,
                    "Produto \"" + nome + "\" adicionado!");

            Formularios.limparCamposProduto(campoProdID, campoProdTipo, campoProdNome,
                    campoProdDesc, campoProdCat, campoProdPreco, campoProdQtd, campoProdValidade);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(parent,
                    "Verifique os campos numéricos.");
        }
    }

    static void removerProduto(JFrame parent,
                               JTable tabelaProdutos,
                               Vector<Produto> vectorProdutos,
                               Stack<String> pilhaOperacoes,
                               JTable tabelaPilha,
                               DecimalFormat mt,
                               Runnable actualizarDashboard,
                               Runnable actualizarCombos) {
        int linha = tabelaProdutos.getSelectedRow();
        if (linha < 0) {
            JOptionPane.showMessageDialog(parent, "Seleccione um produto primeiro.");
            return;
        }

        String nome = vectorProdutos.get(linha).getNome();
        int c = JOptionPane.showConfirmDialog(parent, "Remover \"" + nome + "\"?",
                "Confirmar", JOptionPane.YES_NO_OPTION);
        if (c == JOptionPane.YES_OPTION) {
            pilhaOperacoes.push("REMOVER_PRODUTO:" + nome);
            GestaoDaPila.actualizarTabelaPilha(tabelaPilha, pilhaOperacoes);
            vectorProdutos.remove(linha);
            GestorDeTabelas.preencherTabelaProdutos(tabelaProdutos, vectorProdutos, mt);
            actualizarDashboard.run();
            actualizarCombos.run();
        }
    }
}
