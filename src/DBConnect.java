import javax.swing.*;
import java.sql.*;

/**
 * Created by iveci on 2017-05-29.
 */
public class DBConnect {
    private static Connection dbTest;
    private JFrame frame = new JFrame(),
                   login = new JFrame();
    DBConnect() {
        frame.setTitle("식당 관리 시스템");
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        connectDB();
    }

    private void connectDB() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            dbTest = DriverManager.getConnection("jdbc:oracle:thin:" + "@localhost:1521:XE" , "dbuser","dbuser");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection Failed.");
            System.out.println("SQLException:" + e);
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }
    }

    public void execute_query() throws SQLException {
        String sqlStr = "SELECT model, speed, hd FROM pc " +
                "WHERE (cd = '6x' or cd = '8x') and price < 2000";
        PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
        ResultSet rs = stmt.executeQuery();
        System.out.println("문제3. 6배속이나 8배속의 CD를 가지고 있으며 가격이 " +
                "$2000 미만인 PC들의 모델 번호, 속도, 하드 디스크의 용량을 구하라.");
        while(rs.next()) {
            System.out.println("model : " + rs.getString("MODEL") +
                    " | speed : " + rs.getString("SPEED") +
                    " | hd : "    + rs.getString("HD"));
        }
        rs.close();
        stmt.close();
    }

    public static void main(String[] argv) {
        DBConnect db = new DBConnect();
        try {
            db.execute_query();
            dbTest.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLException:" + e);
        }
    }
}
