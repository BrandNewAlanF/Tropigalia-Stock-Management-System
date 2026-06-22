import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Welcome extends JFrame {
    private static final long serialVersionUID = 1L;
    private JLabel iconLabel;
    private ImageIcon image, beachImage;
    private Image scaledImage;
    private RoundedButton continuarBtn;

    public Welcome() {
        super("Welcome to Goo");

        // Painel principal com layout Box
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(248, 249, 250)); // Bege claro
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Ícone da palmeira
        image= new ImageIcon("icon-palm.png");
        iconLabel = new JLabel(image); // Substitua por seu ícone
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(iconLabel);

        // Espaçamento
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        // Título
        JLabel titulo = new JLabel("Bem-Vindo a Tropigalia");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titulo.setForeground(new Color(51, 51, 51));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titulo);

        // Subtítulo
        JLabel subtitulo = new JLabel("Para uma gestão inteligente");
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        subtitulo.setForeground(new Color(80, 80, 80));
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(subtitulo);

        // Espaçamento
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Imagem da praia
        beachImage = new ImageIcon("SupermarketWelcome.jpg"); // Substitua com a imagem correta
        scaledImage = beachImage.getImage().getScaledInstance(520, 450, Image.SCALE_SMOOTH);
        JLabel beachLabel = new JLabel(new ImageIcon(scaledImage));
        beachLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(beachLabel);

        // Espaçamento
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Botão continuar
        continuarBtn = new RoundedButton("Continuar", 30);
        continuarBtn.setBackground(new Color(125, 193, 154)); // Marrom claro
        continuarBtn.setForeground(Color.WHITE);
        continuarBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        continuarBtn.setFocusPainted(false);
        continuarBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        continuarBtn.setPreferredSize(new Dimension(200, 40));
        continuarBtn.setMaximumSize(new Dimension(200, 40));
        avancar();
        mainPanel.add(continuarBtn);

        // Adiciona painel à janela
        add(mainPanel);
        setVisible(true);
        setSize(600,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public void avancar(){
        continuarBtn.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent ev) {
                      new Sessao();
                        dispose();
                    }
                });
    }

    public static void main(String[] args) {
        new Welcome();
    }
}