import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class DBConnect implements ActionListener {

	private static Connection dbTest;
	
    //창, Frame, Panel
	private JFrame frame, login;
    private JPanel plogin,pmain;
    private Table ptabl = new Table();
    private Order porder = new Order(); 
    private Menus pmenu = new Menus();
    private Lookup pview = new Lookup();
    
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
    private final JPanel panel = new JPanel();
    private final JLabel label = new JLabel("식당 주문관리");
    private final JPanel pmain2 = new JPanel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DBConnect db = new DBConnect();
					db.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DBConnect() {
		initialize();
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
    
    public void input(String sql) throws SQLException {
        PreparedStatement stmt = dbTest.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        System.out.println("INPUT: "+sql);
        rs.close();
        stmt.close();
    }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        connectDB();//데이터베이스 연결
        //기본 창 설정 부분
        frame.setTitle("식당 관리 시스템");
        frame.setSize(750,750);
        frame.setVisible(true);

        //기본 창 메뉴 설정 부분
        pmain = new JPanel();
        pmain.setLayout(new GridLayout(1,2));
        pmain.add(ptabl);
        pmain.add(porder);
        mopen.addActionListener(this);
        mlogin.addActionListener(this);
        menu.add(mopen);
        menu.add(mlogin);
        menuBar.add(menu);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));
        
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        label.setFont(new Font("굴림", Font.PLAIN, 42));
        
        panel.add(label);
        frame.getContentPane().add(pmain);
        
        frame.getContentPane().add(pmain2, BorderLayout.SOUTH);
        pmain2.setLayout(new GridLayout(1,2));
        
        //메뉴 설정 부분
        pmain2.add(pmenu);
        
        //등록조회 설정 부분
        pmain2.add(pview);
        frame.setJMenuBar(menuBar);

        //로그인창 설정 부분
        login = new JFrame();
        plogin = new JPanel();
        plogin.setLayout(null);
        idLabel.setBounds(20,10,60,30);
        pwdLabel.setBounds(20,50,60,30);
        idInput.setBounds(100,10,80,30);
        pwdInput.setBounds(100,50,80,30);
        loginbutton.setBounds(200,25,80,35);
        loginbutton.addActionListener(this);
        plogin.add(idLabel);
        plogin.add(pwdLabel);
        plogin.add(idInput);
        plogin.add(pwdInput);
        plogin.add(loginbutton);
        login.getContentPane().add(plogin);
        login.setTitle("사원 로그인");
        login.setSize(320,130);
        login.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == loginbutton){
            //사원으로 로그인임 dbuser아님
        }
        else if (e.getSource() == mopen){
            if(opener.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                //읽어온 뒤 쿼리화
            	String s;
            	String[] sarray = null;
            	ArrayList<String> sr = new ArrayList<>();
            	BufferedReader ins;
            	try {
					ins = new BufferedReader(new FileReader(opener.getSelectedFile()));
					while ((s = ins.readLine()) != null) {
						sarray = s.split("\t");
						for (int i = 0; i < sarray.length; i++) {
							sarray[i] = sarray[i].trim();
							sr.add(sarray[i]);
						}
					}
					ins.close();
					int npos = 0, readnum;
					ArrayList<String> writecustomer = new ArrayList<>(), 
							writestaff = new ArrayList<>(), 
							writemenu = new ArrayList<>();
					if (sarray != null) {
						readnum = Integer.parseInt(sr.get(npos));
						npos++;
						for (int i = 0; i < readnum; i++) {
							String total = "0";
							if (sr.get(npos+3).equalsIgnoreCase("gold")) total = "1000000";
							else if (sr.get(npos+3).equalsIgnoreCase("silver")) total = "500000";
							else if (sr.get(npos+3).equalsIgnoreCase("bronze")) total = "300000";
							writecustomer.add("INSERT INTO CUSTOMER VALUES("
							+"'"+sr.get(npos)+"'," //name
							+"1"+String.format("%03d", npos)+"," //id
							+"'"+sr.get(npos+1)+"'," //birth
							+"'"+sr.get(npos+2)+"'," //tel
							+total+"," //total
							+"'"+sr.get(npos+3)+"')"); //grade
							npos= npos + 4;
							input(writecustomer.get(i));//쿼리
						}
						readnum = Integer.parseInt(sr.get(npos));
						npos++;
						for (int i = 0; i < readnum; i++) {
							writestaff.add("INSERT INTO EMPLOYEE VALUES("
							+"'"+sr.get(npos)+"'," //name
							+"5"+String.format("%03d", npos)+"," //id
							+"'"+sr.get(npos+1)+"')"); //grade
							npos= npos + 2;
							input(writestaff.get(i));//쿼리
						}
						readnum = Integer.parseInt(sr.get(npos));
						npos++;
						for (int i = 0; i < readnum; i++) {
							writemenu.add("INSERT INTO MENUS VALUES("
							+"'"+sr.get(npos)+"'," //name
							+sr.get(npos+1)+")"); //price
							npos= npos + 2;
							input(writemenu.get(i));//쿼리
						}
						JOptionPane.showMessageDialog(frame, "파일이 정상적으로 입력되었습니다.");
					}
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(frame, "존재하지 않는 파일입니다.");
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(frame, "파일 I/O오류.\n"+e1.getMessage());
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(frame, "파일을 DB에 입력하지 못했습니다.\n"+e1.getMessage());
				}
            }
        }
        else if (e.getSource() == mlogin){
            login.setVisible(true);
        }
		
	}
}
