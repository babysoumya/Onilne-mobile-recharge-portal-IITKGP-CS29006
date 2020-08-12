
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Login {
    private JPanel UserLogin;
    private JTextField usertext;
    private JTextField loginTextField;
    private JButton forgotPasswordButton;
    private JButton signupButton;
    private JCheckBox showPasswordCheckBox;
    private JButton backButton;
    private JButton loginbutton;
    private JPasswordField passwordField;
    public JComboBox comboBox1;
    private JFrame LoginFrame;

    Connection con = null;
    Statement st = null;
    ResultSet rs = null;

    public Login() {
        LoginFrame = new JFrame("Login");
        LoginFrame.setContentPane(UserLogin);
        LoginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        LoginFrame.pack();
        LoginFrame.setVisible(true);

        loginbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String un = usertext.getText();
                String p = passwordField.getText();
                String type = String.valueOf(comboBox1.getSelectedItem());
                try{
                    int log =1;
                    String query ="SELECT * FROM `login`";
                    con = DriverManager.getConnection("jdbc:mysql://localhost/online mobile recharge portal?useTimezone=true&serverTimezone=UTC","root","");
                    st = (Statement) con.createStatement();
                    rs = st.executeQuery(query);
                   // System.out.println(rs);
                    while(rs.next()){
                        if(rs.getString(1).equals(un) && rs.getString(2).equals(p) && rs.getString(3).equals(type) ){
                           log = 0;
                           break;
                        }
                    }
                    if(log ==0) {
                        JOptionPane.showMessageDialog(null, "You are successfully logged in as " + rs.getString("username"));
                        if (comboBox1.getSelectedIndex() == 0) {
                            LoginFrame.dispose();
                            user u = new user(un);
                        } else {
                            LoginFrame.dispose();
                            admin a = new admin();
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"invalid username or password!");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginFrame.dispose();
                Welcome W = new Welcome();
            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginFrame.dispose();
                SignUp S = new SignUp();
            }
        });

        showPasswordCheckBox.setSelected(false);
        passwordField.setEchoChar('*');
        showPasswordCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(showPasswordCheckBox.isSelected()){
                    passwordField.setEchoChar((char)0);
                }
                else{
                    passwordField.setEchoChar('*');
                }
            }
        });

        forgotPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginFrame.dispose();
                FPassword F = new FPassword();
            }
        });

    }
}
