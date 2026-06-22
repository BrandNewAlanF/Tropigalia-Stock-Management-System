import javax.swing.*;
import java.util.Stack;
import java.util.Vector;

public class GestorDeFornecedores {
    GestorDeFornecedores() {}

    static void adicionarFornecedor(JFrame parent,
                                    JTextField campoFornNome,
                                    JTextField campoFornContacto,
                                    JTextField campoFornEndereco,
                                    JTextField campoFornMetodoPag,
                                    JTextField campoFornMercado,
                                    Vector<Fornecedor> vectorFornecedores,
                                    Stack<String> pilhaOperacoes,
                                    JTable tabelaPilha,
                                    JTable tabelaFornecedores,
                                    Runnable actualizarDashboard) {
        String nome = campoFornNome.getText().trim();
        String contacto = campoFornContacto.getText().trim();
        String endereco = campoFornEndereco.getText().trim();
        String metodo = campoFornMetodoPag.getText().trim();
        String mercado = campoFornMercado.getText().trim();

        if (Validador.campoVazio(nome)
                || Validador.campoVazio(contacto)
                || Validador.campoVazio(endereco)
                || Validador.campoVazio(metodo)
                || Validador.campoVazio(mercado)) {
            JOptionPane.showMessageDialog(parent,
                    "Preencha todos os campos do fornecedor.");
            return;
        }

        for (int i = 0; i < vectorFornecedores.size(); i++) {
            if (vectorFornecedores.get(i).getNome().equalsIgnoreCase(nome)) {
                JOptionPane.showMessageDialog(parent,
                        "Fornecedor já existente.");
                return;
            }
        }

        vectorFornecedores.add(new Fornecedor(nome, contacto, endereco, metodo, mercado));

        pilhaOperacoes.push("ADICIONAR_FORNECEDOR:" + nome);
        GestaoDaPila.actualizarTabelaPilha(tabelaPilha, pilhaOperacoes);
        GestorDeTabelas.preencherTabelaFornecedores(tabelaFornecedores, vectorFornecedores);
        actualizarDashboard.run();

        JOptionPane.showMessageDialog(parent,
                "Fornecedor \"" + nome + "\" adicionado!");

        campoFornNome.setText("");
        campoFornContacto.setText("");
        campoFornEndereco.setText("");
        campoFornMetodoPag.setText("");
        campoFornMercado.setText("");
    }
}
