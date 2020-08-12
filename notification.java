import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class notification {
    private JTextArea txtdescrp;
    private JTextField txtsub;
    private JButton backButton;
    private JLabel datelab;
    private JPanel notification;
    private JButton viewBillsButton;
    private JLabel timelab;
    private  JFrame notFrame;
    String user;

    Connection con = null;
    Statement st = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public notification(String un,String id) {
        notFrame = new JFrame("notlist");
        notFrame.setContentPane(notification);
        notFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        notFrame.pack();
        notFrame.setVisible(true);

        user = un;

        try {
            String query = "SELECT `Subject`, `Description`, `Date`, `Time` FROM `notification` WHERE `Username`=? AND `Id number`=?";
            con = DriverManager.getConnection("jdbc:mysql://localhost/online mobile recharge portal?useTimezone=true&serverTimezone=UTC","root","");
            pst = con.prepareStatement(query);
            pst.setString(1,user);
            pst.setString(2,id);
            rs=pst.executeQuery();

            if(rs.next()){
                datelab.setText(String.valueOf(rs.getDate("Date")));
                timelab.setText(String.valueOf(rs.getTime("Time")));
                txtsub.setText(rs.getString("Subject"));
                txtdescrp.setText(rs.getString("Description"));
            }else{
                JOptionPane.showMessageDialog(null,"Enter valid ID!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
            String query = "UPDATE `notification` SET `View Status`=? WHERE `Username`=? AND `Id number`=?";
            con = DriverManager.getConnection("jdbc:mysql://localhost/online mobile recharge portal?useTimezone=true&serverTimezone=UTC","root","");
            pst = con.prepareStatement(query);
            pst.setString(1,"True");
            pst.setString(2,user);
            pst.setString(3,id);

            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notlist n = new notlist(user);
                notFrame.dispose();
            }
        });

        viewBillsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }
}
