import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;

public class Payment {
    private JLabel Payment;
    private JTextField txtamount;
    private JButton cardButton;
    private JButton backButton;
    private JPanel paymentpanel;
    private JButton PAYNOWButton;
    private JTextField txtoperator;
    private JTextField txttype;
    private JTextField txtdata;
    private JTextField txtvalidity;
    private JButton checkoutButton;
    private JTextField textmob;
    private JFrame paymentFrame;


    Connection con= null;
    PreparedStatement pst=null;
    ResultSet rs=null;


    public Payment(String Id,String un,String mob,int amount,int bId){
        int am=amount;
        paymentFrame=new JFrame("PAYMENT");
        paymentFrame.setContentPane(paymentpanel);
        paymentFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        paymentFrame.pack();
        paymentFrame.setVisible(true);

        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    con= DriverManager.getConnection("jdbc:mysql://localhost/online mobile recharge portal?useTimezone=true&serverTimezone=UTC", "root", "");
                    pst=con.prepareStatement("SELECT * FROM `plan` WHERE `Id number`=?");
                    pst.setString(1,Id);
                    rs=pst.executeQuery();
                    rs.next();

                }

                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                    ex.printStackTrace();
                }

                String op="Operator";
                String type="Type";
                String amount="Amount";
                String data ="Data";
                String validity="validity";

                try{
                    txtoperator.setText(rs.getString(op));
                    txtvalidity.setText(rs.getString(validity));
                    txtdata.setText(rs.getString(data));
                    txtamount.setText(String.valueOf(am));
                    txttype.setText(rs.getString(type));
                    textmob.setText(mob);
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                    ex.printStackTrace();
                }

            }
        });

        PAYNOWButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int balance = 0;
                try {
                    String query = "SELECT * FROM `Amount` WHERE `Username`=?";
                    con = DriverManager.getConnection("jdbc:mysql://localhost/online mobile recharge portal?useTimezone=true&serverTimezone=UTC", "root", "");
                    pst = con.prepareStatement(query);
                    pst.setString(1, un);
                    rs = pst.executeQuery();
                    rs.next();
                    balance = rs.getInt("Amount");

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                if (balance > am) {
                    try{
                        String query = "UPDATE `Amount` SET `Amount`=? WHERE `Username`=?";
                        con = DriverManager.getConnection("jdbc:mysql://localhost/online mobile recharge portal?useTimezone=true&serverTimezone=UTC", "root", "");
                        pst = con.prepareStatement(query);
                        pst.setInt(1, balance-am);
                        pst.setString(2,un);
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
                        pst.setInt(1, abalance+am);
                        pst.setString(2,aun);
                        pst.executeUpdate();

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    try{
                        String query ="INSERT INTO `user`(`Username`, `Phone`, `Plan Id`, `Type`, `Date`, `Bill Id`) VALUES (?,?,?,?,?,?)";
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Online Mobile Recharge Portal?useTimezone=true&serverTimezone=UTC", "root", "");
                        pst = con.prepareStatement(query);
                        pst.setString(1, un);
                        pst.setString(2, mob);
                        pst.setString(3, Id);
                        pst.setString(4, "Prepaid");
                        pst.setDate(5, Date.valueOf(LocalDate.now()));
                        pst.setInt(6,0);
                        pst.executeUpdate();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    if(bId!=0) {
                        try {
                            String query = "DELETE FROM `bills` WHERE `Id number`=?";
                            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Online Mobile Recharge Portal?useTimezone=true&serverTimezone=UTC", "root", "");
                            pst = con.prepareStatement(query);
                            pst.setInt(1, bId);
                            pst.executeUpdate();

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    try {
                        String query = "INSERT INTO `trans`(`username`, `Amount`) VALUES (?,?)";
                        con = DriverManager.getConnection("jdbc:mysql://localhost/online mobile recharge portal?useTimezone=true&serverTimezone=UTC", "root", "");
                        pst = con.prepareStatement(query);
                        pst.setString(1, un);
                        pst.setInt(2, am);
                        pst.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Payment Successfull!!");
                        paymentFrame.dispose();
                        user u = new user(un);

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex);
                        ex.printStackTrace();
                    }

                }else{
                    JOptionPane.showMessageDialog(null,"Insufficient balance!Please recharge your account");
                    paymentFrame.dispose();
                    user u = new user(un);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paymentFrame.dispose();
                user u = new user(un);
            }
        });

    }


}
