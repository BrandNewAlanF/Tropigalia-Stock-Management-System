import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Sessao extends JFrame {
    private static final long serialVersionUID = 1L;
    private Vector v;
    private JPanel main, painel1;
    private JLabel code, usern, signLabel,passCheck, email, unisctem;
    private JPasswordField passwordF;
    private JToggleButton showpassword;
    private RoundedButton cancel, save;
    private JComboBox<String> username;
    private JLabel coolLam;
    private JLabel coolLam_1;
    private String[] objects;
    private String es, passVer;
    private ImageIcon back;
    private Ficheiros fi;

    public Sessao() {
        super("Login");
        v= new Vector();
        main = new JPanel();
        main.setLayout(null);
        setContentPane(main);
        fi = new Ficheiros();
        read(v);

        Icon cool = new ImageIcon("plane2.jpg");
        painel1 = new JPanel();
        painel1.setLayout(null);
        painel1.setBackground(new Color(248, 249, 250));
        painel1.setBounds(0, 0, 552, 643);
        main.setBackground(new Color(248, 249, 250));
        main.add(painel1);

        coolLam = new JLabel("Login you account");
        coolLam.setFont(new Font("Georgia", Font.BOLD, 28));
        coolLam.setForeground(new Color(51, 51, 51));
        coolLam.setBounds(148, 28, 300, 40);
        painel1.add(coolLam);

        objects = names(v);
        username = new JComboBox<>(objects);
        username.setBounds(170, 108, 220, 35);
        username.setFont(new Font("SansSerif", Font.PLAIN, 12));
        username.setBackground(new Color(255, 250, 240));
        username.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        userName();
        painel1.add(username);

        passwordF = new JPasswordField();
        passwordF.setBounds(170, 175, 220, 35);
        passwordF.setFont(new Font("SansSerif", Font.PLAIN, 15));
        passwordF.setBackground(new Color(255, 250, 240));
        passwordF.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        painel1.add(passwordF);

        showpassword= new JToggleButton("Show");
        showpassword.setBounds(400, 176, 80, 35);
        showpassword.setFont(new Font("SansSerif", Font.PLAIN, 15));
        showpassword.setBackground(new Color(255, 250, 240));
        showpassword.setForeground(new Color(65, 110, 101));
        showpassword.setFocusable(false);
        char defaultecho= passwordF.getEchoChar();
        showpassword.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (showpassword.isSelected()) {
                            passwordF.setEchoChar((char)0);
                            showpassword.setForeground(Color.RED);
                            showpassword.setText("Hide");
                        }
                        else {
                            passwordF.setEchoChar(defaultecho);
                            showpassword.setForeground(new Color(65, 110, 101));
                            showpassword.setText("Show");
                        }
                    }
                }
        );
        painel1.add(showpassword);

        usern = new JLabel("Manager: ");
        usern.setFont(new Font("SansSerif", Font.PLAIN, 15));
        usern.setBounds(170, 78, 200, 20);
        painel1.add(usern);

        code = new JLabel("Password: ");
        code.setFont(new Font("SansSerif", Font.PLAIN, 15));
        code.setBounds(170, 150, 100, 20);
        painel1.add(code);

        save = new RoundedButton("Next", 10);
        save.setFont(new Font("Segoe UI", Font.BOLD, 14));
        save.setBackground(new Color(65, 110, 101));
        save.setForeground(Color.WHITE);
        save.setBounds(170, 220, 220, 35);
        saveUser();
        painel1.add(save);

        cancel = new RoundedButton("Exit", 5);
        cancel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        cancel.setBackground(new Color(245, 245, 220));
        cancel.setForeground(Color.RED);
        cancel.setBounds(170, 265, 220, 35);
        cancelButton();
        painel1.add(cancel);

        signLabel = new JLabel("Not logged yet?", SwingConstants.CENTER);
        signLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        signLabel.setForeground(new Color(255, 0, 0));
        signLabel.setBounds(228, 333, 110, 20);
        sign();
        painel1.add(signLabel);

        email = new JLabel("© 2025 - GOO: Maputo/Moçambique.\r\nAll rights reserved.");
        email.setBounds(125, 350, 350, 20);
        painel1.add(email);
        email.setForeground(Color.DARK_GRAY);
        email.setFont(new Font("SansSerif", Font.PLAIN, 12));

        unisctem = new JLabel("UNISC-CTRM");
        unisctem.setBounds(240, 376, 76, 20);
        painel1.add(unisctem);
        unisctem.setForeground(Color.DARK_GRAY);
        unisctem.setFont(new Font("SansSerif", Font.PLAIN, 12));

        back = new ImageIcon("victor-rutka-4FujjkcI40g-unsplash.jpg");
        Image sooCOOL = back.getImage().getScaledInstance(360, 237, Image.SCALE_SMOOTH);
        coolLam_1 = new JLabel(new ImageIcon(sooCOOL));
        coolLam_1.setBounds(100, 406, 380, 237);
        painel1.add(coolLam_1);

        passCheck = new JLabel("Insert a password");
        passCheck.setForeground(new Color(51, 51, 51));
        passCheck.setFont(new Font("SansSerif", Font.PLAIN, 12));
        passCheck.setBounds(180, 310, 320, 13);
        painel1.add(passCheck);

        setSize(562, 680);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    //UTLIZACAO DOS METODOS PRESENTES NO CONSTRUTOR:
    public void read(Vector v) {
        Usuarios ur;
        StringTokenizer str;
        String name,surname,passW,linha="";
        try {
            FileReader fr= new FileReader("Contas.txt");
            BufferedReader br= new BufferedReader(fr);
            linha=br.readLine();
            while(linha != null) {
                str= new StringTokenizer(linha,";");
                name=str.nextToken();
                surname=str.nextToken();
                passW=str.nextToken();
                ur = new Usuarios(name, surname, passW);
                v.add(ur);
                v.trimToSize();

                linha=br.readLine();
            }
            br.close();
        }catch(IOException e) {
            JOptionPane.showMessageDialog(null,"ERROR!\nThere was a mistake while entering a charater or a set of charaters."
                    +e.getMessage());
        }
    }

    public String[] names(Vector v) {
        Usuarios ur = null;
        String[] x=new String[v.size()];
        for(int i=0;i<v.size();i++) {
            ur=(Usuarios)v.elementAt(i);
            x[i]=ur.getName0()+" ";
        }
        return x;
    }

    public void userName(){
        username.addItemListener(
                new ItemListener() {
                    public void itemStateChanged(ItemEvent ev) {
                        if(ev.getStateChange() == ItemEvent.SELECTED) {
                            es=objects[username.getSelectedIndex()];
                        }
                    }
                }
        );
    }

    public void sign(){
        signLabel.addMouseListener(
                new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        new Cadastro(v);
                        dispose();
                    }
                }
        );
    }

    public void saveUser(){
        save.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent ev) {
                        boolean avanca=false,goo=false;
                        int codigo=0,indice=-1;
                        Usuarios ur;
                        ur=(Usuarios)v.elementAt(username.getSelectedIndex());
                        indice=username.getSelectedIndex();

                        if(indice==-1)
                            JOptionPane.showMessageDialog(null,"The user does not exist.");
                        else
                        {
                            if(indice!=-1) {
                                avanca=true;
                            }
                        }

                        if(passwordF.getText().equals(ur.getPassW0())) {
                            goo=true;
                        }

                        if(goo == false) {
                            if(passwordF.getText().isBlank())
                                JOptionPane.showMessageDialog(null,"Please insere a valid password.");
                            else {
                                if(!passwordF.getText().equals(ur.getPassW0())){
                                    JOptionPane.showMessageDialog(null,"Password is incorrect, try again.");
                                    passwordF.setText("");
                                }
                            }
                        }
                        if(goo==true && avanca == true) {
                            JOptionPane.showMessageDialog(null,objects[username.getSelectedIndex()]+"logged sucessfuly.");
                            new Gestao(""+objects[username.getSelectedIndex()]+"");
                            dispose();
                        }
                    }
                }
        );
    }

    public void cancelButton(){
        cancel.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent ev) {
                        int answer=JOptionPane.showConfirmDialog(null, "Do you really want to exit?!!");
                        switch(answer) {
                            case 0:
                                JOptionPane.showMessageDialog(null,"Thank you for accessing our services.");
                                System.exit(0);
                                break;
                            case 1:
                                JOptionPane.showMessageDialog(null, "Nice!\nKeep managing.");
                                setDefaultCloseOperation(EXIT_ON_CLOSE);
                                break;
                        }
                    }
                }
        );
    }
}