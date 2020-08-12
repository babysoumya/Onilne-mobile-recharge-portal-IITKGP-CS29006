import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class OTPreg {
    private JTextField OTPText;
    private JButton confirmButton;
    private JButton resendOTPButton;
    private JPanel OTPPanel;
    public JButton backButton;
    public JFrame OTPFrame;
    int otp;

    Connection con;
    PreparedStatement pst;

    public OTPreg(String n, String mob, String em, String un, String P1){
        OTPFrame= new JFrame("OTP");
        OTPFrame.setContentPane(OTPPanel);
        OTPFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        OTPFrame.pack();
        OTPFrame.setVisible(true);

        Random rand = new Random();
        otp = rand.nextInt(99999);
        try {
            // Construct data
            String apiKey = "apikey=" + "OITFCl/5MTw-go4M4EEkPy94F3ufDBO8aiBo7RPt3w";
            String message = "&message=" + "Your OTP for registering your mobile number into OMRP is: " + otp;
            String sender = "&sender=" + "TXTLCL";
            String numbers = "&numbers=" + mob;
            // Send data
            HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
            String data = apiKey + numbers + message + sender;
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes("UTF-8"));
            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                stringBuffer.append(line);
            }
            rd.close();
            //return stringBuffer.toString();
        } catch (Exception ex) {
            System.out.println("Error SMS " + ex);
            //return "Error "+e;
        }

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String o = OTPText.getText();
                System.out.println(o);
                System.out.println(otp);
                if(o.contains(String.valueOf(otp))){
                    try {
                        String query = "INSERT INTO `registration`(`Username`, `Password`, `emailId`, `Name`, `Phone`) VALUES (?,?,?,?,?)";
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Online Mobile Recharge Portal?useTimezone=true&serverTimezone=UTC", "root", "");
                        pst = con.prepareStatement(query);
                        pst.setString(1, un);
                        pst.setString(2, P1);
                        pst.setString(3, em);
                        pst.setString(4, n);
                        pst.setString(5, mob);
                        pst.executeUpdate();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        String query = "INSERT INTO `login`(`Username`, `Password`, `Type`,`Phone`) VALUES (?,?,?,?)";
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Online Mobile Recharge Portal?useTimezone=true&serverTimezone=UTC", "root", "");
                        pst = con.prepareStatement(query);
                        pst.setString(1, un);
                        pst.setString(2, P1);
                        pst.setString(3, "User");
                        pst.setString(4, mob);
                        pst.executeUpdate();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    try {
                        String query = "INSERT INTO `Amount`(`Username`, `Amount`, `Type`) VALUES (?,?,?)";
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Online Mobile Recharge Portal?useTimezone=true&serverTimezone=UTC", "root", "");
                        pst = con.prepareStatement(query);
                        pst.setString(1, un);
                        pst.setInt(2, 2000);
                        pst.setString(3, "User");
                        pst.executeUpdate();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "You are Successfully registered!");
                    Login L = new Login();
                    OTPFrame.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Invalid OTP.Try again!");
                    OTPText.setText(null);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OTPFrame.dispose();
                SignUp S = new SignUp();
            }
        });

        resendOTPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OTPText.setText(null);
                Random rand = new Random();
                otp = rand.nextInt(99999);
                try {
                    // Construct data
                    String apiKey = "apikey=" + "OITFCl/5MTw-go4M4EEkPy94F3ufDBO8aiBo7RPt3w";
                    String message = "&message=" + "Your OTP for registering your mobile number into OMRP is: "+ otp;
                    String sender = "&sender=" + "TXTLCL";
                    String numbers = "&numbers=" + mob;
                    // Send data
                    HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
                    String data = apiKey + numbers + message + sender;
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
                    conn.getOutputStream().write(data.getBytes("UTF-8"));
                    final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    final StringBuffer stringBuffer = new StringBuffer();
                    String line;
                    while ((line = rd.readLine()) != null) {
                        stringBuffer.append(line);
                    }
                    rd.close();

                    //return stringBuffer.toString();
                } catch (Exception ex) {
                    System.out.println("Error SMS "+ex);
                    //return "Error "+e;
                }
            }
        });
    }
}
