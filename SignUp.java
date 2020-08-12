import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.Random;

public class SignUp{
    private JTextField nametextfield;
    private JTextField mobiletextfield;
    private JTextField emailtextfield;
    private JTextField usernametextfield;
    private JPasswordField ConfirmPassword;
    private JPasswordField Password;
    private JCheckBox showPasswordCheckBox;
    private JCheckBox showCPasswordCheckBox;
    private JButton confirmButton;
    private JButton backButton;
    private JPanel Registration;
    private JFrame signUpFrame;
    private int otp;
    private int result;
    private String n;
    private String mob;
    private String em;
    private String un;
    private String P1;
    private String P2;

    Connection con = null;
    PreparedStatement pst =null;
    public SignUp() {
        signUpFrame = new JFrame("SignUp");
        signUpFrame.setContentPane(Registration);
        signUpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        signUpFrame.pack();
        signUpFrame.setVisible(true);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                n = nametextfield.getText();
                mob = mobiletextfield.getText();
                em = emailtextfield.getText();
                un = usernametextfield.getText();
                P1 = Password.getText();
                P2 = ConfirmPassword.getText();
                if (P1.equals(P2)) {
                    OTPreg O = new OTPreg(n,mob,em,un,P1);
                    signUpFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Passwords does not match!");
                    Password.setText(null);
                    ConfirmPassword.setText(null);
                }

            }
        });

        showPasswordCheckBox.setSelected(false);
        Password.setEchoChar('*');
        showPasswordCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(showPasswordCheckBox.isSelected()){
                    Password.setEchoChar((char)0);
                }
                else{
                    Password.setEchoChar('*');
                }
            }
        });

        showCPasswordCheckBox.setSelected(false);
        ConfirmPassword.setEchoChar('*');
        showCPasswordCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(showCPasswordCheckBox.isSelected()){
                    ConfirmPassword.setEchoChar((char)0);
                }
                else{
                    ConfirmPassword.setEchoChar('*');
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signUpFrame.dispose();
                Login L = new Login();
            }
        });

    }
}
