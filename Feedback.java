import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Feedback {
    private JTextArea textArea1;
    private JTextField textField1;
    private JButton backButton;
    private JPanel Feedback;
    private JFrame feedbackFrame;

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    public Feedback(String Id) {

        feedbackFrame = new JFrame("feedback");
        feedbackFrame.setContentPane(Feedback);
        feedbackFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        feedbackFrame.pack();
        feedbackFrame.setVisible(true);
        try{
            String query ="SELECT * FROM `feedback` WHERE `Id number`=?";

            con = DriverManager.getConnection("jdbc:mysql://localhost/online mobile recharge portal?useTimezone=true&serverTimezone=UTC","root","");
            pst = con.prepareStatement(query);
            pst.setString(1,Id);

            rs=pst.executeQuery();

            rs.next();
            textArea1.setText(rs.getString("Feedback"));
            textField1.setText(rs.getString("Username"));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
            ex.printStackTrace();
        }

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewfeedback v = new viewfeedback();
                feedbackFrame.dispose();
            }
        });
    }
}
