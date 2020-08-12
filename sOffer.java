import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class sOffer {
    private JTable Table;
    private JTextField txtId;
    private JButton confirmButton;
    private JButton backButton;
    private JPanel sOffer;
    private JFrame sOfferFrame;
    int amount;
    int discount;

    Connection con= null;
    PreparedStatement pst= null;
    ResultSet rs = null;

    public sOffer(String un,String mob,String Id,String type) {
        sOfferFrame = new JFrame("sOffer");
        sOfferFrame.setContentPane(sOffer);
        sOfferFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        sOfferFrame.pack();
        sOfferFrame.setVisible(true);

        try {
            String query = "SELECT * FROM `offer`ORDER BY `Id number` ASC";
            con = DriverManager.getConnection("jdbc:mysql://localhost/online mobile recharge portal?useTimezone=true&serverTimezone=UTC", "root", "");
            pst = con.prepareStatement(query);
            // System.out.println(pst.toString());
            rs = pst.executeQuery();
            Table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            ex.printStackTrace();
        }

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost/online mobile recharge portal?useTimezone=true&serverTimezone=UTC", "root", "");
                    pst = con.prepareStatement("SELECT * FROM `plan` WHERE `Id number`=?");
                    pst.setString(1, Id);
                    rs = pst.executeQuery();
                    rs.next();
                    amount = Integer.parseInt(rs.getString("Amount"));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    ex.printStackTrace();
                }
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost/online mobile recharge portal?useTimezone=true&serverTimezone=UTC", "root", "");
                    pst = con.prepareStatement("SELECT * FROM `offer` WHERE `Id number`=?");
                    pst.setString(1, txtId.getText());
                    rs = pst.executeQuery();
                    rs.next();
                    System.out.println(rs.getString("Discount"));
                    discount = Integer.parseInt(rs.getString("Discount"));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    ex.printStackTrace();
                }
                amount = amount - ((amount * discount) / 100);
                if (type.contains("Prepaid")) {
                    Payment P = new Payment(txtId.getText(), un, mob, amount,0);
                    sOfferFrame.dispose();
                } else {
                    Bill b = new Bill();
                    b.createBill(un, mob,Id,amount);
                    JOptionPane.showMessageDialog(null, "Recharge Successfull!");
                    user u = new user(un);
                    sOfferFrame.dispose();
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sOfferFrame.dispose();
                Recharge r = new Recharge(un);
            }
        });

    }
}
