import javax.swing.*;
import java.awt.*;

public class FabricaComponentes {
    FabricaComponentes() {}

    static JLabel criarTituloSecao(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 12));
        lbl.setForeground(EstiloVisual.VERDE_FLORESTA);
        lbl.setOpaque(true);
        lbl.setBackground(EstiloVisual.BRANCO);
        lbl.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(30, 58, 39, 30)));
        return lbl;
    }

    static JLabel criarLabelFormulario(String texto) {
        JLabel lbl = new JLabel(texto.toUpperCase());
        lbl.setFont(new Font("SansSerif", Font.BOLD, 9));
        lbl.setForeground(EstiloVisual.CINZA_SUAVE);
        return lbl;
    }

    static JTextField criarCampoComLabel(JPanel pai, String label,
                                         int x, int y, int w, int h) {
        JLabel lbl = criarLabelFormulario(label);
        lbl.setBounds(x, y - 15, w, 13);
        pai.add(lbl);

        JTextField campo = new JTextField();
        campo.setBounds(x, y, w, h);
        campo.setFont(new Font("SansSerif", Font.PLAIN, 12));
        campo.setForeground(EstiloVisual.VERDE_FLORESTA);
        campo.setBackground(EstiloVisual.BRANCO_GELO);
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(30, 58, 39, 50)),
                BorderFactory.createEmptyBorder(2, 6, 2, 6)
        ));
        pai.add(campo);
        return campo;
    }

    static JPanel criarCartaoKPI(String label, String valor, String sub) {
        JPanel cartao = new JPanel(null);
        cartao.setBackground(EstiloVisual.BRANCO);
        cartao.setBorder(BorderFactory.createLineBorder(new Color(30, 58, 39, 30)));
        JLabel lLbl = new JLabel(label);
        lLbl.setFont(new Font("SansSerif", Font.PLAIN, 9));
        lLbl.setForeground(EstiloVisual.CINZA_SUAVE);
        lLbl.setBounds(10, 10, 155, 14);
        cartao.add(lLbl);
        JLabel lVal = new JLabel(valor);
        lVal.setFont(new Font("SansSerif", Font.BOLD, 18));
        lVal.setForeground(EstiloVisual.VERDE_FLORESTA);
        lVal.setBounds(10, 28, 155, 26);
        cartao.add(lVal);
        JLabel lSub = new JLabel(sub);
        lSub.setFont(new Font("SansSerif", Font.PLAIN, 9));
        lSub.setForeground(EstiloVisual.VERDE_ABACATE);
        lSub.setBounds(10, 58, 155, 14);
        cartao.add(lSub);
        return cartao;
    }
}
