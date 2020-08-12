import com.mysql.cj.xdevapi.Table;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class billlist {
    private JTable table1;
    private JTextField textField1;
    private JButton viewButton;
    private JButton backButton;
    private JPanel billlistPanel;
    private JFrame billistFrame;
    Connection con=null;
    PreparedStatement pst =null;
    ResultSet rs = null;

    public billlist(String un){
        billistFrame = new JFrame("billist");
        billistFrame.setContentPane(billlistPanel);
        billistFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        billistFrame.pack();
        billistFrame.setVisible(true);

        try{
            String query ="SELECT * FROM `bills` WHERE `Username`=?";

            con = DriverManager.getConnection("jdbc:mysql://localhost/online mobile recharge portal?useTimezone=true&serverTimezone=UTC","root","");
            pst = con.prepareStatement(query);
            pst.setString(1,un);
            System.out.println(pst.toString());
            rs=pst.executeQuery();

            table1.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
            ex.printStackTrace();
        }

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user u = new user(un);
                billistFrame.dispose();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view_bill v =new view_bill(textField1.getText(),un);
                billistFrame.dispose();
            }
        });
    }

}
