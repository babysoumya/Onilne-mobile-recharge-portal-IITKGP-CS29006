import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Recharge {
    private JPanel recharge;
    private JTextField txtmob;
    private JComboBox comboBox1;
    private JButton postpaidbutton;
    private JButton backButton;
    private JButton resetButton;
    private JLabel moblab;
    private JButton prepaidButton;
    private JFrame rechargeFrame;

    public Recharge(String un) {
        rechargeFrame = new JFrame("Modify");
        rechargeFrame.setContentPane(recharge);
        rechargeFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        rechargeFrame.pack();
        rechargeFrame.setVisible(true);

        prepaidButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mob = txtmob.getText();
                String op = String.valueOf(comboBox1.getSelectedItem());
                sPlan s = new sPlan(un,op,"Prepaid");
                s.mob = mob;
                rechargeFrame.dispose();
            }
        });

        postpaidbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mob = txtmob.getText();
                String op = String.valueOf(comboBox1.getSelectedItem());
                sPlan s = new sPlan(un,op,"Postpaid");
                s.mob = mob;
                rechargeFrame.dispose();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rechargeFrame.dispose();
                user u = new user(un);
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtmob.setText(null);
                comboBox1.setSelectedIndex(0);
            }
        });

        txtmob.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                String P = "^[0-9]{10}$";
                Pattern patt = Pattern.compile(P);
                Matcher match = patt.matcher(txtmob.getText());
                if(!match.matches()){
                    moblab.setText("Mobile number is incorrect!");
                }
            }
        });

    }
}
