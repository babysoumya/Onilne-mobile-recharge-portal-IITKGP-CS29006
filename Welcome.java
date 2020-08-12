import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Welcome {

    private JTextField WELCOMETextField;
    private JButton USERLOGINButton;
    private JButton ADMINLOGINButton;
    private JButton signUpButton;
    private JPanel WelcomePanel;
    private JFrame WelcomeFrame;

    public Welcome(){
        WelcomeFrame = new JFrame("WELCOME");
        WelcomeFrame.setContentPane(WelcomePanel);
        WelcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WelcomeFrame.pack();
        WelcomeFrame.setVisible(true);

        USERLOGINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WelcomeFrame.dispose();
                Login L = new Login();
                L.comboBox1.setSelectedItem("User");
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WelcomeFrame.dispose();
                SignUp S = new SignUp();
            }
        });

        ADMINLOGINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WelcomeFrame.dispose();
                Login A = new Login();
                A.comboBox1.setSelectedItem("Admin");
            }
        });
    }

    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        Welcome W = new Welcome();
    }
}

