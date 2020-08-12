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

public class OTPfp {

    private JTextField OTPText;
    private JButton confirmButton;
    private JButton resendOTPButton;
    private JPanel OTPPanel;
    private JButton backButton;
    private JFrame OTPFrame;
    int otp;

    Connection con;
    PreparedStatement pst;

    public OTPfp(String un){
        OTPFrame= new JFrame("OTP");
        OTPFrame.setContentPane(OTPPanel);
        OTPFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        OTPFrame.pack();
        OTPFrame.setVisible(true);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String o = OTPText.getText();
                System.out.println(o);
                System.out.println(otp);
                if(o.contains(String.valueOf(otp))){
                    NPassword N = new NPassword();
                    N.un = un;
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
                    String message = "&message=" + "Your OTP for changing password in OMRP is: "+ otp;
                    String sender = "&sender=" + "TXTLCL";
                    String numbers = "&numbers=" ;
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
