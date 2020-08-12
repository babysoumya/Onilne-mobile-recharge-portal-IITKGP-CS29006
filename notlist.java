import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class notlist{
    private JTable Table;
    private JPanel notlist;
    private JButton backButton;
    private JButton viewButton;
    private JTextField txtID;
    private JFrame notlistFrame;
    private String user;

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public notlist(String un) {
        notlistFrame = new JFrame("notlist");
        notlistFrame.setContentPane(notlist);
        notlistFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        notlistFrame.pack();
        notlistFrame.setVisible(true);

        user = un;

        showTableData();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user u = new user(user);
                notlistFrame.dispose();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = txtID.getText();
                notification n = new notification(user,id);
                notlistFrame.dispose();
            }
        });
    }

    public void showTableData(){
        try{
            String query ="SELECT `Id number`, `Subject`, `Time` FROM `notification` WHERE `Username`=?";

            con = DriverManager.getConnection("jdbc:mysql://localhost/online mobile recharge portal?useTimezone=true&serverTimezone=UTC","root","");
            pst = con.prepareStatement(query);
            pst.setString(1,user);

            rs=pst.executeQuery();

            Table.setModel(DbUtils.resultSetToTableModel(rs));



        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
            ex.printStackTrace();
        }

    }
}
