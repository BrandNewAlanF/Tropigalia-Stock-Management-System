import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
public class Cadastro extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel panel,panel2;
    private JTextField tusername,tsur;
    private JPasswordField tpassW, tconfirmP;
    private JToggleButton showpassword1, showpassword2;
    private JLabel username,passW,confirmP,surname,titulo,img;
    private RoundedButton cancel,save;
    private Ficheiros fi;
    private ImageIcon i,i2,i3,i4;

    public Cadastro(Vector v) {
        super("Sign In");
        setBounds(100, 100, 887, 512);
        panel = new JPanel();
        panel.setBackground(new Color(255, 250, 240));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panel);

        fi= new Ficheiros();
        panel.setLayout(null);

        titulo= new JLabel("Sign In");
        panel2 = new JPanel();
        panel2.setBounds(77, 0, 370, 134);
        panel2.setBackground(Color.BLACK);
        panel2.setLayout(null);
        panel2.add(titulo);
        panel.add(panel2);

        JLabel lblNewLabel = new JLabel("    Sign In   ");
        lblNewLabel.setFont(new Font("Segoe UI Symbol", Font.BOLD, 45));
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(38, 38, 235, 50);
        panel2.add(lblNewLabel);

        username = new JLabel("Username:");
        username.setBounds(103, 159, 129, 23);
        username.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 11));
        panel.add(username);

        passW = new JLabel("Password:");
        passW.setBounds(104, 292, 83, 14);
        passW.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 11));
        panel.add(passW);

        confirmP = new JLabel("Confirm password: ");
        confirmP.setBounds(104, 354, 118, 14);
        confirmP.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 11));
        panel.add(confirmP);

        tusername = new JTextField();
        tusername.setBounds(104, 192, 305, 28);
        tusername.setBackground(Color.WHITE);
        tusername.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 11));
        tusername.setForeground(Color.BLACK);
        panel.add(tusername);
        tusername.setColumns(10);

        surname = new JLabel("Surname:");
        surname.setBounds(104, 230, 83, 14);
        surname.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 11));
        panel.add(surname);


        tsur = new JTextField();
        tsur.setBounds(104, 254, 305, 28);
        tsur.setBackground(Color.WHITE);
        tsur.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 11));
        panel.add(tsur);
        tsur.setColumns(10);


        tpassW = new JPasswordField();
        tpassW.setBounds(104, 316, 305, 28);
        tpassW.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 11));
        tpassW.setForeground(Color.BLACK);
        tpassW.setColumns(10);
        panel.add(tpassW);

        showpassword1= new JToggleButton("Show");
        showpassword1.setBounds(411, 316, 60, 28);
        showpassword1.setFont(new Font("SansSerif", Font.PLAIN, 12));
        showpassword1.setBackground(new Color(255, 250, 240));
        showpassword1.setForeground(new Color(65, 110, 101));
        showpassword1.setFocusable(false);
        char defaultecho= tpassW.getEchoChar();
        showpassword1.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (showpassword1.isSelected()) {
                            tpassW.setEchoChar((char)0);
                            showpassword1.setForeground(Color.RED);
                            showpassword1.setText("Hide");
                        }
                        else {
                            tpassW.setEchoChar(defaultecho);
                            showpassword1.setForeground(new Color(65, 110, 101));
                            showpassword1.setText("Show");
                        }
                    }
                }
        );
        panel.add(showpassword1);


        tconfirmP = new JPasswordField();
        tconfirmP.setBounds(104, 378, 305, 28);
        tconfirmP.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 11));
        tconfirmP.setForeground(Color.BLACK);
        tconfirmP.setColumns(10);
        panel.add(tconfirmP);

        showpassword2= new JToggleButton("Show");
        showpassword2.setBounds(411, 378, 60, 28);
        showpassword2.setFont(new Font("SansSerif", Font.PLAIN, 12));
        showpassword2.setBackground(new Color(255, 250, 240));
        showpassword2.setForeground(new Color(65, 110, 101));
        showpassword2.setFocusable(false);
        char defaultecho2= tconfirmP.getEchoChar();
        showpassword2.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (showpassword2.isSelected()) {
                            tconfirmP.setEchoChar((char)0);
                            showpassword2.setForeground(Color.RED);
                            showpassword2.setText("Hide");
                        }
                        else {
                            tconfirmP.setEchoChar(defaultecho2);
                            showpassword2.setForeground(new Color(65, 110, 101));
                            showpassword2.setText("Show");
                        }
                    }
                }
        );
        panel.add(showpassword2);

        i= new ImageIcon("people.png");
        JLabel forI = new JLabel("",i,SwingConstants.RIGHT);
        forI.setBounds(111, 52, 46, 14);
        panel.add(forI);

        i2= new ImageIcon("people.png");
        JLabel forI2 = new JLabel("",i2,SwingConstants.RIGHT);
        forI2.setBounds(111, 118, 46, 14);
        panel.add(forI2);

        i3= new ImageIcon("lock.png");
        JLabel forI3 = new JLabel("",i3,SwingConstants.RIGHT);
        forI3.setBounds(111, 182, 46, 14);
        panel.add(forI3);

        i4= new ImageIcon("lock.png");
        JLabel forI4 = new JLabel("",i4,SwingConstants.RIGHT);
        forI4.setBounds(111, 244, 46, 14);
        panel.add(forI4);

        save = new RoundedButton("Save",20);
        save.setText("Registrate");
        save.setBounds(103, 416, 166, 48);
        save.setForeground(new Color(255, 255, 255));
        save.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        save.setBackground(new Color(47, 79, 79));
        saveAct(v);
        panel.add(save);

        cancel = new RoundedButton("Cancel",20);
        cancel.setText("Cancel");
        cancel.setForeground(new Color(255, 255, 255));
        cancel.setBounds(280, 416, 129, 48);
        panel.add(cancel);
        cancel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        cancel.setBackground(new Color(0, 0, 0));
        cancelAct(v);

        ImageIcon cool = new ImageIcon("victoria-shes-XgFFJKSPkNk-unsplash.jpg");
        Image sooCOOL = cool.getImage().getScaledInstance(347, 173, Image.SCALE_SMOOTH);
        img = new JLabel(new ImageIcon(sooCOOL));
        img.setBounds(35, 474, 447, 173);
        panel.add(img);

        setVisible(true);
        setSize(526,684);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void saveAct(Vector v){
        save.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent ev) {
                        boolean done=false;
                        String password= new String(tpassW.getPassword());
                        String confirmPassword= new String(tconfirmP.getPassword());
                        System.out.println(password +" / " + confirmPassword);
                        if(!confirmPassword.trim().equals(password.trim())) {
                            JOptionPane.showMessageDialog(tconfirmP,"The password introduced is diferent the before, please try it again.");
                            tconfirmP.setText("");
                        }
                        else {
                            if(password.equals("") && tusername.getText().equals(""))
                                JOptionPane.showMessageDialog(null,"The name and the password are empty, please introduce a name and password.");
                            else {
                                if(password.equals(""))
                                    JOptionPane.showMessageDialog(tpassW,"The password is empty, please introduce a valid password.");
                                else
                                {
                                    if(tusername.getText().equals(""))
                                        JOptionPane.showMessageDialog(tusername,"The name is empty, please introduce a name Password.");
                                    else
                                    {
                                        if(tsur.getText().equals(""))
                                            JOptionPane.showMessageDialog(tusername,"The surname is empty, please introduce a name Password.");
                                    }
                                }
                            }
                        }
                        if(password.trim().equals(confirmPassword.trim()) && !confirmPassword.equals(" ") && !tusername.getText().equals(" ") && !password.equals("") )
                            done=true;
                        if(done == true) {
                            Usuarios ur= new Usuarios(tusername.getText(), tsur.getText(), confirmPassword);
                            filewriter(tusername.getText(), tsur.getText(),confirmPassword);
                            JOptionPane.showMessageDialog(null,"Account created succesfuly.");
                            Sessao lg = new Sessao();
                            lg.read(v);
                            dispose();
                        }
                    }
                }
        );
    }

    public void cancelAct(Vector v){
        cancel.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent ev) {
                        JOptionPane.showMessageDialog(null,"Your SignIn was canceled and your account wasn't registered.");
                        new Sessao();
                        dispose();
                    }
                }
        );
    }

    public void filewriter(String name,String surname,String pass) {
        try {
            FileWriter fw= new FileWriter("Account.txt",true);
            BufferedWriter bw= new BufferedWriter(fw);
            bw.write("\n"+name+";"+surname+";"+pass);
            bw.close();
        }catch(IOException e) {
            JOptionPane.showMessageDialog(null,"Error. There a mistake inserting a character or a set of characters"
                    +e.getMessage());
        }
    }
}