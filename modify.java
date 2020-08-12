import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class modify {
    private JTextField modifytextfield;
    private JTextField txtoperator;
    private JTextField txtamount;
    private JTextField txtdata;
    private JTextField txtvalidity;
    private JButton insertButton;
    private JButton update_Button;
    private JButton delete_Button;
    private JButton backButton;
    private JPanel modify;
    private JTextField textID;
    private JTable Table;
    private JTextField txttype;
    private JFrame modifyFrame;

    Connection con= null;
    PreparedStatement pst= null;
    Statement st = null;
    ResultSet rs = null;

    public modify() {
        modifyFrame = new JFrame("Modify");
        modifyFrame.setContentPane(modify);
        modifyFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        modifyFrame.pack();
        modifyFrame.setVisible(true);

        showTableData();

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String query = "INSERT INTO `plan`" + "(`Id number`, `Operator`,`Type`, `Amount`, `Data`, `Validity`) " + "VALUES (?,?,?,?,?,?)";

                    con = DriverManager.getConnection("jdbc:mysql://localhost/online mobile recharge portal?useTimezone=true&serverTimezone=UTC","root","");
                    pst = con.prepareStatement(query);
                    pst.setString(1,textID.getText());
                    pst.setString(2,txtoperator.getText());
                    pst.setString(3, txttype.getText());
                    pst.setString(4,txtamount.getText());
                    pst.setString(5,txtdata.getText());
                    pst.setString(6,txtvalidity.getText());

                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(null,"Changes are inserted Successfully!!");

                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                    ex.printStackTrace();
                }

                showTableData();
                setText();
            }
        });

        update_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String op = txtoperator.getText();
                    String type = txttype.getText();
                    String amount = txtamount.getText();
                    String data = txtdata.getText();
                    String val = txtvalidity.getText();
                    String ID = textID.getText();
                    String query = "Update `plan` SET `Operator`=?,`Type`=?,`Amount`=?,`Data`=?,`Validity`=? WHERE `Id number`=?";

                    con = DriverManager.getConnection("jdbc:mysql://localhost/online mobile recharge portal?useTimezone=true&serverTimezone=UTC","root","");
                    pst = con.prepareStatement(query);
                    pst.setString(1,op);
                    pst.setString(2,type);
                    pst.setString(3,amount);
                    pst.setString(4,data);
                    pst.setString(5,val);
                    pst.setString(6,ID);

                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Changes are updated Successfully!!");

                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                    ex.printStackTrace();
                }

                showTableData();
                setText();
            }
        });

        delete_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String ID =textID.getText();
                    String query = ("DELETE FROM `plan` WHERE `Id number`='"+ID+"'");

                    con = DriverManager.getConnection("jdbc:mysql://localhost/online mobile recharge portal?useTimezone=true&serverTimezone=UTC","root","");
                    st = (Statement) con.createStatement();
                    st.executeUpdate(query);

                    JOptionPane.showMessageDialog(null, "Changes are deleted Successfully!!");

                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                    ex.printStackTrace();
                }

                showTableData();
                setText();
            }
        });


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifyFrame.dispose();
                adminedit a = new adminedit();
            }
        });

    }

    public void showTableData(){
        try{
            String query ="SELECT * FROM `plan` ORDER BY `Id number` ASC";

            con = DriverManager.getConnection("jdbc:mysql://localhost/online mobile recharge portal?useTimezone=true&serverTimezone=UTC","root","");
            pst = con.prepareStatement(query);

            rs=pst.executeQuery();

            Table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
            ex.printStackTrace();
        }

    }
    private void setText(){
        textID.setText(null);
        txtoperator.setText(null);
        txttype.setText(null);
        txtamount.setText(null);
        txtdata.setText(null);
        txtvalidity.setText(null);
    }

}