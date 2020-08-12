import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class profile {
    private JPanel profile;
    private JLabel PROFILE;
    private JButton updateButton;
    private JButton backButton;
    private JLabel name;
    private JTextField txt_username;
    private JTextField txt_password;
    private JTextField txt_email;
    private JTextField txt_name;
    private JTextField txt_phone;
    private JButton viewButton;
    private JButton resetButton;
    private JLabel ballab;
    private JFrame profileFrame;
    String user;

    Connection con= null;
    PreparedStatement pst= null;
    ResultSet rs = null;

        public profile(String un){

            profileFrame= new JFrame("Profile");
            profileFrame.setContentPane(profile);
            profileFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            profileFrame.pack();
            profileFrame.setVisible(true);

            user=un;

            updateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String query = ("UPDATE `registration` SET `Username`=?, `Password`=?,`emailId`=?,`Name`=?,`Phone`=? WHERE `Username`= ? ");
                        con = DriverManager.getConnection("jdbc:mysql://localhost/online mobile recharge portal?useTimezone=true&serverTimezone=UTC", "root", "");
                        pst = con.prepareStatement(query);


                        pst.setString(1, txt_username.getText());
                        pst.setString(2, txt_password.getText());
                        pst.setString(3, txt_email.getText());
                        pst.setString(4, txt_name.getText());
                        pst.setString(5, txt_phone.getText());
                        pst.setString(6,user);


                        pst.executeUpdate();

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    try {
                        String query = ("UPDATE `login` SET `Username`=?, `Password`=?,`Phone`=? WHERE `Username`= ? ");
                        con = DriverManager.getConnection("jdbc:mysql://localhost/online mobile recharge portal?useTimezone=true&serverTimezone=UTC", "root", "");
                        pst = con.prepareStatement(query);


                        pst.setString(1, txt_username.getText());
                        pst.setString(2, txt_password.getText());
                        pst.setString(3, txt_phone.getText());
                        pst.setString(4,user);


                        pst.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Profile is updated");

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    user =txt_username.getText();
                    setTextNull();
                }
            });

            viewButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int balance = 0;
                    try {
                        String query = "SELECT * FROM `Amount` WHERE `Username`=?";
                        con = DriverManager.getConnection("jdbc:mysql://localhost/online mobile recharge portal?useTimezone=true&serverTimezone=UTC", "root", "");
                        pst = con.prepareStatement(query);
                        pst.setString(1, user);
                        rs = pst.executeQuery();
                        rs.next();
                        balance = rs.getInt("Amount");

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    try {
                        con = DriverManager.getConnection("jdbc:mysql://localhost/online mobile recharge portal?useTimezone=true&serverTimezone=UTC", "root", "");
                        pst = con.prepareStatement("SELECT * FROM `registration` WHERE `Username`=?");
                        pst.setString(1,user);
                        rs = pst.executeQuery();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null,ex.getMessage());
                        ex.printStackTrace();
                    }
                   // Function f = new Function();
                    String user="Username";
                    String pass="Password";
                    String eId="emailId";
                    String name ="Name";
                    String phone="Phone";
                   // rs = f.find(user);
                    try{
                        if(rs.next())
                        {
                            txt_username.setText(rs.getString(user));
                            txt_password.setText(rs.getString(pass));
                            txt_name.setText(rs.getString(name));
                            txt_email.setText(rs.getString(eId));
                            txt_phone.setText(rs.getString(phone));
                            ballab.setText(String.valueOf(balance));
                        }
                        else JOptionPane.showMessageDialog(null,"No data for this");
                    }
                    catch (Exception ex) {
                        JOptionPane.showMessageDialog(null,ex.getMessage());
                        ex.printStackTrace();
                    }
                }
            });

            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    profileFrame.dispose();
                    user u = new user(user);
                }
            });

            resetButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setTextNull();
                }
            });
        }

        private void setTextNull(){
            txt_password.setText(null);
            txt_email.setText(null);
            txt_username.setText(null);
            txt_phone.setText(null);
            txt_name.setText(null);
        }

        public void setText(){

            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost/online mobile recharge portal?useTimezone=true&serverTimezone=UTC", "root", "");
                pst = con.prepareStatement("SELECT * FROM `registration` WHERE `Username`=?");
                pst.setString(1,user);
                rs = pst.executeQuery();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage());
                ex.printStackTrace();
            }
            String user="Username";
            String pass="Password";
            String eId="emailId";
            String name ="Name";
            String phone="Phone";

            try{
                if(rs.next())
                {
                    txt_username.setText(rs.getString(user));
                    txt_password.setText(rs.getString(pass));
                    txt_name.setText(rs.getString(name));
                    txt_email.setText(rs.getString(eId));
                    txt_phone.setText(rs.getString(phone));
                }
                else JOptionPane.showMessageDialog(null,"No data for this");
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage());
                ex.printStackTrace();
            }
        }

}
