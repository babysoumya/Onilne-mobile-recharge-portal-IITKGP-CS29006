import java.sql.*;
import java.time.LocalDate;

public class Bill {
    Connection con =null;
    PreparedStatement pst = null;
    ResultSet rs =null;

    public void createBill(String un,String mob,String pId,int amount){
        try{
            String query = "INSERT INTO `bills`(`Username`, `Phone`, `PlanId`, `Amount`, `Date`, `Deadline`) VALUES (?,?,?,?,?,?)";
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Online Mobile Recharge Portal?useTimezone=true&serverTimezone=UTC", "root", "");
            pst = con.prepareStatement(query);
            pst.setString(1, un);
            pst.setString(2, mob);
            pst.setString(3, pId);
            pst.setInt(4,amount);
            pst.setDate(5, Date.valueOf(LocalDate.now()));
            pst.setDate(6, Date.valueOf(LocalDate.now()));
            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        int bId=0;
        try{
            String query ="SELECT * FROM `bills` WHERE `Id number`=(SELECT MAX(`Id number`) FROM `bills`)";
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Online Mobile Recharge Portal?useTimezone=true&serverTimezone=UTC", "root", "");
            pst = con.prepareStatement(query);
            rs= pst.executeQuery();
            rs.next();
            bId=Integer.parseInt(rs.getString("Id number"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
            String query ="INSERT INTO `user`(`Username`, `Phone`, `Plan Id`, `Type`, `Date`, `Bill Id`) VALUES (?,?,?,?,?,?)";
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Online Mobile Recharge Portal?useTimezone=true&serverTimezone=UTC", "root", "");
            pst = con.prepareStatement(query);
            pst.setString(1, un);
            pst.setString(2, mob);
            pst.setString(3, pId);
            pst.setString(4, "Postpaid");
            pst.setDate(5,Date.valueOf(LocalDate.now()));
            pst.setInt(6,bId);
            pst.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
