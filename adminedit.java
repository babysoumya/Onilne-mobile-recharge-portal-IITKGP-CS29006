import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class adminedit {
    private JButton planButton;
    private JButton offerButton;
    private JButton backButton;
    private JPanel edit;
    private JFrame admineditFrame;

    public adminedit() {
        admineditFrame =new JFrame("adminedit");
        admineditFrame.setContentPane(edit);
        admineditFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        admineditFrame.pack();
        admineditFrame.setVisible(true);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                admineditFrame.dispose();
                admin a = new admin();
            }
        });

        planButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                admineditFrame.dispose();
                modify m = new modify();
            }
        });

        offerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                offer o = new offer();
                admineditFrame.dispose();
            }
        });
    }
}
