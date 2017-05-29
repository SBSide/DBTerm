import java.sql.*;

/**
 * Created by iveci on 2017-05-29.
 */
public class DBConnect {
    private static Connection dbTest;
    DBConnect() {
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
        String sqlStr = "SELECT avg(speed) FROM pc";
        PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
        ResultSet rs = stmt.executeQuery();
        System.out.println("문제1. pc의 평균 속력을 구하라.");
        while(rs.next()) {
            System.out.println("avg(speed): " + rs.getString("AVG(SPEED)"));
        }
        System.out.println("---------------------------");
        sqlStr = "SELECT price FROM pc " +
                "WHERE price >= 2000";
        stmt = dbTest.prepareStatement(sqlStr);
        rs = stmt.executeQuery();
        System.out.println("문제2. pc에서 price가 2000이상인 가격을 구하라.");
        while(rs.next()) {
            System.out.println("price: " + rs.getString("PRICE"));
        }
        System.out.println("---------------------------");
        sqlStr = "SELECT model, speed, hd FROM pc " +
                "WHERE (cd = '6x' or cd = '8x') and price < 2000";
        stmt = dbTest.prepareStatement(sqlStr);
        rs = stmt.executeQuery();
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
