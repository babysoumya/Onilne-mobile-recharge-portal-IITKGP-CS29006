import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class givefeedback {
    private JPanel givefeedbackpanel;
    private JTextArea txtfeedback;
    private JButton backButton;
    private JButton saveButton;
    private JFrame givefeedbackFrame;

    Connection con = null;
    PreparedStatement pst = null;

    public givefeedback( String un)

    {
        givefeedbackFrame = new JFrame("givefeedback");
        givefeedbackFrame.setContentPane(givefeedbackpanel);
        givefeedbackFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        givefeedbackFrame.pack();
        givefeedbackFrame.setVisible(true);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String query = "INSERT INTO `feedback`(`Feedback`,`Username`) VALUES (?,?)";

                    con = DriverManager.getConnection("jdbc:mysql://localhost/online mobile recharge portal?useTimezone=true&serverTimezone=UTC","root","");
                    pst = con.prepareStatement(query);
                    pst.setString(1,txtfeedback.getText());
                    pst.setString(2,un);


                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(null,"Your Feedback is saved Successfully!!");
                    givefeedbackFrame.dispose();
                    JOptionPane.showMessageDialog(null, "You are successfully logged out!!");
                    Welcome W = new Welcome();

                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                    ex.printStackTrace();
                }

            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                givefeedbackFrame.dispose();
                user u = new user(un);
            }
        });


    }


}
