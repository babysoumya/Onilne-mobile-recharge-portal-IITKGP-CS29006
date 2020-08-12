

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class user {
    private JButton newRechargeButton;
    private JButton viewProfileButton;
    private JButton updateProfileButton;
    private JButton viewNotificationsButton;
    private JButton viewBillsButton;
    private JButton logoutButton;
    private JPanel userPanel;
    private JLabel notlab;
    private JButton rechargeAccountButton;
    private JFrame userFrame;
    private String user;

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;


    public user(String un) {
        userFrame= new JFrame("user");
        userFrame.setContentPane(userPanel);
        userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userFrame.pack();
        userFrame.setVisible(true);

        user =un;

        try {
            String query = "SELECT `View Status` FROM `notification` WHERE `Username`=?";
            con = DriverManager.getConnection("jdbc:mysql://localhost/online mobile recharge portal?useTimezone=true&serverTimezone=UTC","root","");
            pst = con.prepareStatement(query);
            pst.setString(1,user);
            rs=pst.executeQuery();

            while(rs.next()){
               if(rs.getString("View Status").contains("False")){
                   notlab.setText("You have notifications to be viewed!");
                   break;
               }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Are you sure to logout?");
                givefeedback g = new givefeedback(user);
                userFrame.dispose();
            }
        });

        newRechargeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Recharge r = new Recharge(user);
                userFrame.dispose();
            }
        });

        viewProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                profile p = new profile(user);
                p.setText();
                userFrame.dispose();
            }
        });

        updateProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                profile p = new profile(user);
                userFrame.dispose();
            }
        });

        viewNotificationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notlist nl = new notlist(user);
                userFrame.dispose();
            }
        });

        rechargeAccountButton.addActionListener(new ActionListener() {
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

                    try{
                        String query = "UPDATE `Amount` SET `Amount`=? WHERE `Username`=?";
                        con = DriverManager.getConnection("jdbc:mysql://localhost/online mobile recharge portal?useTimezone=true&serverTimezone=UTC", "root", "");
                        pst = con.prepareStatement(query);
                        pst.setInt(1, balance+2000);
                        pst.setString(2,user);
                        pst.executeUpdate();

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    int abalance=0;
                    String aun=null;
                    try {
                        String query = "SELECT * FROM `Amount` WHERE `Type`=?";
                        con = DriverManager.getConnection("jdbc:mysql://localhost/online mobile recharge portal?useTimezone=true&serverTimezone=UTC", "root", "");
                        pst = con.prepareStatement(query);
                        pst.setString(1,"Admin");
                        rs = pst.executeQuery();
                        rs.next();
                        abalance = rs.getInt("Amount");
                        aun=rs.getString("Username");

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    try{
                        String query = "UPDATE `Amount` SET `Amount`=? WHERE `Username`=?";
                        con = DriverManager.getConnection("jdbc:mysql://localhost/online mobile recharge portal?useTimezone=true&serverTimezone=UTC", "root", "");
                        pst = con.prepareStatement(query);
                        pst.setInt(1, abalance-2000);
                        pst.setString(2,aun);
                        pst.executeUpdate();
                        JOptionPane.showMessageDialog(null,"Account credited 2000!");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

            }
        });

        viewBillsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                billlist b = new billlist(un);
                userFrame.dispose();
            }
        });
    }
}
