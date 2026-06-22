import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Stack;

public class GestaoDaPila {
    GestaoDaPila() {}

    static void actualizarTabelaPilha(JTable tabelaPilha, Stack<String> pilhaOperacoes) {
        if (tabelaPilha == null) {
            return;
        }

        DefaultTableModel modelo = (DefaultTableModel) tabelaPilha.getModel();
        modelo.setRowCount(0);

        Stack<String> copia = (Stack<String>) pilhaOperacoes.clone();
        int topo = copia.size();

        while (!copia.isEmpty()) {
            modelo.addRow(new Object[]{topo, copia.pop()});
            topo--;
        }
    }

    static void desfazerUltimaOperacao(JFrame parent,
                                       JTable tabelaPilha,
                                       Stack<String> pilhaOperacoes) {
        if (pilhaOperacoes.isEmpty()) {
            JOptionPane.showMessageDialog(parent,
                    "Não existem operações para desfazer.");
            return;
        }

        String ultima = pilhaOperacoes.pop();
        actualizarTabelaPilha(tabelaPilha, pilhaOperacoes);

        JOptionPane.showMessageDialog(parent,
                "Última operação removida da Pilha:\n\n" + ultima);
    }
}
