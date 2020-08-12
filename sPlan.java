import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class sPlan {
    private JButton confirmButton;
    private JTable Table;
    private JTextField txtID;
    private JButton backButton;
    private JPanel splan;
    private JFrame sPlanFrame;
    String mob;

    Connection con= null;
    PreparedStatement pst= null;
    ResultSet rs = null;

    public sPlan(String un,String op,String type) {
        sPlanFrame = new JFrame("Modify");
        sPlanFrame.setContentPane(splan);
        sPlanFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        sPlanFrame.pack();
        sPlanFrame.setVisible(true);

        System.out.println(op);

        try{
            String query ="SELECT * FROM `plan` WHERE `Operator`=? AND Type=? ORDER BY `Id number` ASC";

            con = DriverManager.getConnection("jdbc:mysql://localhost/online mobile recharge portal?useTimezone=true&serverTimezone=UTC","root","");
            pst = con.prepareStatement(query);
            pst.setString(1,op);
            pst.setString(2,type);
            System.out.println(pst.toString());
            rs=pst.executeQuery();

            Table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
            ex.printStackTrace();
        }

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sOffer s =new sOffer(un,mob,txtID.getText(),type);
                sPlanFrame.dispose();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sPlanFrame.dispose();
                Recharge r = new Recharge(un);
            }
        });

    }
}
