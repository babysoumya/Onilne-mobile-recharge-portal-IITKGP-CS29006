import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Stats {
    private JTextField textuser;
    private JTextField textTurn;
    private JButton backButton;
    private JPanel stats;
    private JFrame statsFrame;

    Connection con = null;
    Statement st = null;
    ResultSet rs = null;

    public Stats() {
        statsFrame=new JFrame("Stats");
        statsFrame.setContentPane(stats);
        statsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        statsFrame.pack();
        statsFrame.setVisible(true);
        int user=0;
        int turnover=0;
        try{
            String query ="SELECT * FROM `registration`";
            con = DriverManager.getConnection("jdbc:mysql://localhost/online mobile recharge portal?useTimezone=true&serverTimezone=UTC","root","");
            st = (Statement) con.createStatement();
            rs = st.executeQuery(query);
            // System.out.println(rs);
            while(rs.next()){
                    user++;
                }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try{
            String query ="SELECT * FROM `trans`";
            con = DriverManager.getConnection("jdbc:mysql://localhost/online mobile recharge portal?useTimezone=true&serverTimezone=UTC","root","");
            st = (Statement) con.createStatement();
            rs = st.executeQuery(query);
            // System.out.println(rs);
            while(rs.next()){
                turnover+=rs.getInt("Amount");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        textTurn.setText(String.valueOf(turnover));
        textuser.setText(String.valueOf(user));

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statsFrame.dispose();
                admin a= new admin();

            }
        });
    }
}
