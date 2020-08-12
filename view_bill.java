
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;


public class view_bill {
    private JTextField txtoperator;
    private JTextField txttype;
    private JTextField txtamount;
    private JTextField txtdata;
    private JTextField txtvalidity;
    private JButton payNowButton;
    private JButton backButton;
    private JPanel viewbillpanel;
    private JTextField txtdate;
    private JTextField txtdeadline;
    private JTextField txtmob;
    private JPanel billPanel;
    private JFrame billFrame;
    String pId;
    String mob;
    int amount;

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;


    public view_bill(String Id,String un) {

        billFrame = new JFrame("ViewBills");
        billFrame.setContentPane(viewbillpanel);
        billFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        billFrame.pack();
        billFrame.setVisible(true);

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/online mobile recharge portal?useTimezone=true&serverTimezone=UTC", "root", "");
            pst = con.prepareStatement("SELECT * FROM `bills` WHERE `Id number`=?");
            pst.setString(1,Id);
            rs = pst.executeQuery();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
            ex.printStackTrace();
        }

        String am="Amount";
        String date="Date";
        String deadline="Deadline";

        try{
            if(rs.next())
            {
                pId=rs.getString("planId");
                mob=rs.getString("Phone");
                txtmob.setText(mob);
                txtamount.setText(String.valueOf(rs.getInt(am)));
                amount=rs.getInt(am);
                txtdate.setText(String.valueOf(rs.getDate(date)));
                txtdeadline.setText(String.valueOf(rs.getDate(deadline)));

            }
            else JOptionPane.showMessageDialog(null,"No data for this");
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
            ex.printStackTrace();
        }

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/online mobile recharge portal?useTimezone=true&serverTimezone=UTC", "root", "");
            pst = con.prepareStatement("SELECT * FROM `plan` WHERE `Id number`=?");
            pst.setString(1,pId);
            rs = pst.executeQuery();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
            ex.printStackTrace();
        }

        String op="Operator";
        String type="Type";
        String data="Data";
        String val="Validity";

        try{
            if(rs.next())
            {
                txtoperator.setText(rs.getString(op));
                txttype.setText(rs.getString(type));
                txtdata.setText(rs.getString(data));
                txtvalidity.setText(rs.getString(val));

            }
            else JOptionPane.showMessageDialog(null,"No data for this");
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
            ex.printStackTrace();
        }



        payNowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Payment p= new Payment(pId,un,mob,amount,Integer.parseInt(Id));
                billFrame.dispose();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                billFrame.dispose();
            }
        });
    }
}