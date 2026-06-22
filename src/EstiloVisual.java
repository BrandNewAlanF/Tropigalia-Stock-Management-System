import javax.swing.*;
import java.awt.*;

public class EstiloVisual {
    static final Color BRANCO_GELO    = new Color(248, 249, 250);
    static final Color VERDE_SALVIA   = new Color(125, 193, 154);
    static final Color VERDE_ABACATE  = new Color(46,  204, 113);
    static final Color VERDE_FLORESTA = new Color(30,   58,  39);
    static final Color CORAL_ALERTA   = new Color(217, 107,  98);
    static final Color BRANCO         = Color.WHITE;
    static final Color CINZA_SUAVE    = new Color(107, 123, 111);

    EstiloVisual() {}

    static void estilizarTabela(JTable t) {
        t.setFont(new Font("SansSerif", Font.PLAIN, 11));
        t.setForeground(VERDE_FLORESTA);
        t.setBackground(BRANCO);
        t.setRowHeight(26);
        t.setGridColor(new Color(30, 58, 39, 20));
        t.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 10));
        t.getTableHeader().setForeground(CINZA_SUAVE);
        t.getTableHeader().setBackground(BRANCO_GELO);
        t.setSelectionBackground(new Color(125, 193, 154, 60));
        t.setSelectionForeground(VERDE_FLORESTA);
        t.setShowVerticalLines(false);
        t.setFocusable(false);
    }

    static void estilizarBotaoPrimario(RoundedButton btn) {
        btn.setBackground(VERDE_ABACATE);
        btn.setForeground(BRANCO);
        btn.setFont(new Font("SansSerif", Font.BOLD, 12));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    static void estilizarCombo(JComboBox<?> c) {
        c.setFont(new Font("SansSerif", Font.PLAIN, 12));
        c.setBackground(BRANCO_GELO);
        c.setForeground(VERDE_FLORESTA);
    }
}
