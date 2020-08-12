import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.Random;

public class FPassword {
    private JTextField usertext;
    private JButton confirmButton;
    private JPanel FpasswordPanel;
    private JButton backButton;
    private JFrame FpasswordFrame;
    private int otp;

    Connection con =null;
    Statement st = null;
    ResultSet rs =null;

    public FPassword() {
        FpasswordFrame = new JFrame("FPassword" );
        FpasswordFrame.setContentPane(FpasswordPanel);
        FpasswordFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FpasswordFrame.pack();
        FpasswordFrame.setVisible(true);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String un = usertext.getText();
                try{
                    int log =1;
                    String query ="SELECT * FROM `login`";
                    con = DriverManager.getConnection("jdbc:mysql://localhost/online mobile recharge portal?useTimezone=true&serverTimezone=UTC","root","");
                    st = (Statement) con.createStatement();
                    rs = st.executeQuery(query);
                    // System.out.println(rs);
                    while(rs.next()){
                        if(rs.getString(1).equals(un)){
                            log = 0;
                            break;
                        }
                    }
                    if(log ==0) {
                        String mob =rs.getString(4);
                        Random rand = new Random();
                        otp = rand.nextInt(99999);
                        try {
                            // Construct data
                            String apiKey = "apikey=" + "OITFCl/5MTw-go4M4EEkPy94F3ufDBO8aiBo7RPt3w";
                            String message = "&message=" + "Your OTP for registering in OMRP is: " + otp;
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
                        OTPfp O = new OTPfp(un);
                        O.otp =otp;
                        FpasswordFrame.dispose();
                    }else{
                        JOptionPane.showMessageDialog(null,"invalid username!");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FpasswordFrame.dispose();
                Login L = new Login();
            }
        });
    }
}
