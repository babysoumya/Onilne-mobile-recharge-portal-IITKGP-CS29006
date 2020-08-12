import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class NPassword {
    private JPasswordField passwordField1;
    private JCheckBox showPasswordCheckBox;
    private JPasswordField passwordField2;
    private JButton confirmButton;
    private JPanel NPasswordPanel;
    private JFrame NPasswordFame;
    String un;

    Connection con = null;
    PreparedStatement pst =null;

    public NPassword() {
        NPasswordFame = new JFrame("NPasswprd");
        NPasswordFame.setContentPane(NPasswordPanel);
        NPasswordFame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        NPasswordFame.pack();
        NPasswordFame.setVisible(true);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String P1 = passwordField1.getText();
                String P2 = passwordField2.getText();
                String u = un;
                if (P1.endsWith(P2)){
                    try {
                        String query = "UPDATE `registration` SET `Password`=? WHERE `Username`=?";
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Online Mobile Recharge Portal?useTimezone=true&serverTimezone=UTC", "root", "");
                        pst = con.prepareStatement(query);
                        pst.setString(1,P1);
                        pst.setString(2,u);
                        pst.executeUpdate();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    try {
                        String query = "UPDATE `login` SET `Password`=? WHERE `Username`=?";
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Online Mobile Recharge Portal?useTimezone=true&serverTimezone=UTC", "root", "");
                        pst = con.prepareStatement(query);
                        pst.setString(1,P1);
                        pst.setString(2,u);
                        pst.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Your password is updated!");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    NPasswordFame.dispose();
                    Login U = new Login();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Passwords does not match");
                    passwordField1.setText(null);
                    passwordField2.setText(null);
                }
            }
        });

        showPasswordCheckBox.setSelected(false);
        passwordField1.setEchoChar('*');
        showPasswordCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(showPasswordCheckBox.isSelected()){
                    passwordField1.setEchoChar((char)0);
                }
                else{
                    passwordField1.setEchoChar('*');
                }
            }
        });

    }
}
