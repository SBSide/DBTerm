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
    //창, Frame, Panel
    private JFrame frame = new JFrame(),
                   login = new JFrame();
    private JPanel panel = new JPanel(),
                   ptabl = new JPanel();

    //테이블현황

    //주문내역

    //메뉴

    //등록조회


    //메뉴바
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu = new JMenu("Menu");
    private JMenuItem mopen  = new JMenuItem("Open"),
                      mlogin = new JMenuItem("Login");
    private JFileChooser opener = new JFileChooser();

    //로그인
    private JLabel idLabel  = new JLabel("아이디"),
                   pwdLabel = new JLabel("사원번호");
    private JTextField idInput = new JTextField();
    private JPasswordField pwdInput = new JPasswordField();
    private JButton loginbutton = new JButton("로그인");

    DBConnect() {
        connectDB();//데이터베이스 연결
        //기본 창 설정 부분
        frame.setTitle("식당 관리 시스템");
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        //테이블현황 설정 부분
        ptabl.setLayout(new GridLayout(4,5));


        //주문내역 설정 부분

        //메뉴 설정 부분

        //등록조회 설정 부분


        //기본 창 메뉴 설정 부분
        mopen.addActionListener(this);
        mlogin.addActionListener(this);
        menu.add(mopen);
        menu.add(mlogin);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        //로그인창 설정 부분
        panel.setLayout(null);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginbutton){
            //사원으로 로그인임 dbuser아님
        }
        else if (e.getSource() == mopen){
            if(opener.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                //읽어온 뒤 쿼리화
            }
        }
        else if (e.getSource() == mlogin){
            login.setVisible(true);
        }
    }
}
