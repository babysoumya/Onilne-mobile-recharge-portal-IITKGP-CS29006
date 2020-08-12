import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class sendnot {
    private JTextArea txtdescrp;
    private JTextField txtsub;
    private JLabel datelab;
    private JPanel notification;
    private JButton sendButton;
    private JButton backButton;
    private JLabel timelab;
    private JTextField txtuser;
    private JButton viewBillsButton;
    private  JFrame sendnotFrame;
    String user;

    Connection con = null;
    PreparedStatement pst = null;

    public sendnot() {
        sendnotFrame = new JFrame("sendnot");
        sendnotFrame.setContentPane(notification);
        sendnotFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        sendnotFrame.pack();
        sendnotFrame.setVisible(true);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String date =String.valueOf(Date.valueOf(LocalDate.now()));
                    String time =String.valueOf(Time.valueOf(LocalTime.now()));
                    String query = "INSERT INTO `notification`(`Username`, `Date`, `Time`, `Subject`, `Description`, `View Status`)"+
                            " VALUES (?,?,?,?,?,?)";
                    con = DriverManager.getConnection("jdbc:mysql://localhost/online mobile recharge portal?useTimezone=true&serverTimezone=UTC","root","");
                    pst = con.prepareStatement(query);
                    pst.setString(1,txtuser.getText());
                    pst.setString(2,date);
                    pst.setString(3,time);
                    pst.setString(4,txtsub.getText());
                    pst.setString(5,txtdescrp.getText());
                    pst.setString(6,"False");
                    pst.executeUpdate();
                    txtuser.setText(null);
                    txtdescrp.setText(null);
                    txtsub.setText(null);
                    JOptionPane.showMessageDialog(null,"Notification sent!");

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendnotFrame.dispose();
                admin a = new admin();

            }
        });
    }
}
