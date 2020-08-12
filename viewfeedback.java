import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class viewfeedback {
    private JPanel viewFeedback;
    private JTable feedbackTable;
    private JButton backButton;
    private JTextField textField1;
    private JButton viewButton;
    private JFrame viewfeedbackFrame;


    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public viewfeedback(){
        viewfeedbackFrame = new JFrame("givefeedback");
        viewfeedbackFrame.setContentPane(viewFeedback);
        viewfeedbackFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        viewfeedbackFrame.pack();
        viewfeedbackFrame.setVisible(true);

        try{
            String query ="SELECT * FROM `feedback`";

            con = DriverManager.getConnection("jdbc:mysql://localhost/online mobile recharge portal?useTimezone=true&serverTimezone=UTC","root","");
            pst = con.prepareStatement(query);

            rs=pst.executeQuery();

            feedbackTable.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
            ex.printStackTrace();
        }

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewfeedbackFrame.dispose();
                admin a = new admin();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Feedback F = new Feedback(textField1.getText());
                viewfeedbackFrame.dispose();
            }
        });
    }

}
