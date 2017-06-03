import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * Created by iveci on 2017-05-29.
 */
public class DBConnect implements ActionListener{
    private static Connection dbTest;
    private JFrame frame = new JFrame(),
                   login = new JFrame();
    private Container container = frame.getContentPane();
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu = new JMenu("Menu");
    private JMenuItem mopen  = new JMenuItem("Open"),
                      mlogin = new JMenuItem("Login");
    private JFileChooser opener = new JFileChooser();
    private JPanel panel = new JPanel();
    private JLabel idLabel  = new JLabel("아이디"),
                   pwdLabel = new JLabel("사원번호");
    private JTextField idInput = new JTextField();
    private JPasswordField pwdInput = new JPasswordField();
    private JButton loginbutton = new JButton("로그인");
    private String user, passwd;
    DBConnect() {
        //기본 창 설정 부분
        frame.setTitle("식당 관리 시스템");
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        panel.setLayout(null);
        //기본 창 메뉴 설정 부분
        mopen.addActionListener(this);
        mlogin.addActionListener(this);
        menu.add(mopen);
        menu.add(mlogin);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        //로그인창 설정 부분
        idLabel.setBounds(20,10,60,30);
        pwdLabel.setBounds(20,50,60,30);
        idInput.setBounds(100,10,80,30);
        pwdInput.setBounds(100,50,80,30);
        loginbutton.setBounds(200,25,80,35);
        loginbutton.addActionListener(this);
        panel.add(idLabel);
        panel.add(pwdLabel);
        panel.add(idInput);
        panel.add(pwdInput);
        panel.add(loginbutton);
        login.add(panel);
        login.setTitle("사원 로그인");
        login.setSize(320,130);
        login.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//        login.setVisible(true);
    }

    private void connectDB() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            dbTest = DriverManager.getConnection("jdbc:oracle:thin:" + "@localhost:1521:XE" , user,passwd);
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
            while (dbTest!=null) {
                db.execute_query();
                dbTest.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLException:" + e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginbutton){
            user = idInput.getText();
            passwd = new String(pwdInput.getPassword());
            connectDB();
            try {
                if (!dbTest.isClosed()) login.setVisible(false);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        else if (e.getSource() == mopen){
            if(opener.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
            }
        }
        else if (e.getSource() == mlogin){
            login.setVisible(true);
        }
    }
}
