import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class offer {
    private JTextField txtID;
    private JTextArea txtdscrp;
    private JButton insertButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JTable Table;
    private JButton backButton;
    private JPanel offer;
    private JTextField txtdis;
    private JFrame offerFrame;

    Connection con= null;
    PreparedStatement pst= null;


    Connection con1= null;
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst1= null;
    ResultSet rs1 = null;

    Connection con2= null;
    PreparedStatement pst2= null;

    public offer() {
        offerFrame = new JFrame("Modify");
        offerFrame.setContentPane(offer);
        offerFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        offerFrame.pack();
        offerFrame.setVisible(true);

        showTableData();

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String query = "INSERT INTO `offer`" + "(`Id number`,`Description`,`Discount`) " + "VALUES (?,?,?)";

                    con = DriverManager.getConnection("jdbc:mysql://localhost/online mobile recharge portal?useTimezone=true&serverTimezone=UTC","root","");
                    pst = con.prepareStatement(query);
                    pst.setString(1,txtID.getText());
                    pst.setString(2,txtdscrp.getText());
                    pst.setString(3,txtdis.getText());

                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(null,"Changes are inserted Successfully!!");

                    try{
                        String query1 = "SELECT * FROM `registration`";
                        con1 = DriverManager.getConnection("jdbc:mysql://localhost/online mobile recharge portal?useTimezone=true&serverTimezone=UTC","root","");
                        st=(Statement)con1.createStatement();
                        rs = st.executeQuery(query1);

                        while(rs.next()){
                            String user = rs.getString("Username");
                            try{
                                String query2 = "INSERT INTO `notification`(`Username`, `Date`, `Time`, `Subject`, `Description`, `View Status`) VALUES (?,?,?,?,?,?)";
                                con2 = DriverManager.getConnection("jdbc:mysql://localhost/online mobile recharge portal?useTimezone=true&serverTimezone=UTC","root","");
                                pst1 = con2.prepareStatement(query2);
                                pst1.setString(1,user);
                                pst1.setDate(2,Date.valueOf(LocalDate.now()));
                                pst1.setTime(3,Time.valueOf(LocalTime.now()));
                                pst1.setString(4,"New Offer!!");
                                pst1.setString(5,txtID.getText()+"\n"+txtdscrp.getText());
                                pst1.setString(6,"False");

                                pst1.executeUpdate();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }

                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                    ex.printStackTrace();
                }

                showTableData();
                setText();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(txtID.getText());
                    String dscrp = txtdscrp.getText();

                    String query = "Update `offer` SET `Description`=? WHERE `Id number`=?";

                    con = DriverManager.getConnection("jdbc:mysql://localhost/online mobile recharge portal?useTimezone=true&serverTimezone=UTC","root","");
                    pst = con.prepareStatement(query);
                    pst.setString(1,dscrp);
                    pst.setInt(2,id);

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

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int ID =Integer.parseInt(txtID.getText());
                    String query = ("DELETE FROM `offer` WHERE `Id number`='"+ID+"'");

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
                offerFrame.dispose();
                adminedit a = new adminedit();
            }
        });

    }

    public void showTableData(){
        try{
            String query ="SELECT * FROM `offer` ORDER BY `Id number` ASC";

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
        txtID.setText(null);
        txtdscrp.setText(null);
    }
}
