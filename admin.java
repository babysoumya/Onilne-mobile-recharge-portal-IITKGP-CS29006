import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class admin {

    private JButton editButton;
    private JButton viewFeedbackButton;
    private JButton viewBillsButton;
    private JButton sendNotificationsButton;
    private JButton logoutButton;
    private JPanel adminPanel;
    private JButton viewStatisticsButton;
    private JFrame adminFrame;

    public admin(){
        adminFrame=new JFrame("admin");
        adminFrame.setContentPane(adminPanel);
        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminFrame.pack();
        adminFrame.setVisible(true);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Are you sure ;to logout?");
                adminFrame.dispose();
                JOptionPane.showMessageDialog(null,"Successfully logged out!!");
                Welcome W = new Welcome();

            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminFrame.dispose();
                adminedit a = new adminedit();
            }
        });

        sendNotificationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminFrame.dispose();
                sendnot s = new sendnot();

            }
        });

        viewStatisticsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Stats s = new Stats();
                adminFrame.dispose();
            }
        });

        viewFeedbackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewfeedback v = new viewfeedback();
                adminFrame.dispose();
            }
        });
    }

}
