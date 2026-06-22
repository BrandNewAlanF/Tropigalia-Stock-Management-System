import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.*;
public class RoundedButton extends JButton{
    private int radius;
    RoundedButton(String text, int radius){
        super(text);
        this.radius=radius;
        setOpaque(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2= (Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

        super.paintComponent(g2);
        g2.dispose();
    }

    public void updateUI() {
        super.updateUI();
        setContentAreaFilled(false);
    }
}