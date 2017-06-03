import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Table extends JPanel {

	/**
	 * Create the panel.
	 */
	JLabel l1 = new JLabel("1");
	JLabel l2 = new JLabel("2");
	JLabel l3 = new JLabel("3");
	JLabel l4 = new JLabel("4");
	JLabel l5 = new JLabel("5");
	JLabel l6 = new JLabel("6");
	JLabel l7 = new JLabel("7");
	JLabel l8 = new JLabel("8");
	JLabel l9 = new JLabel("9");
	JLabel l10 = new JLabel("10");
	JLabel l11 = new JLabel("11");
	JLabel l12 = new JLabel("12");
	JLabel l13 = new JLabel("13");
	JLabel l14 = new JLabel("14");
	JLabel l15 = new JLabel("15");
	JLabel l16 = new JLabel("16");
	JLabel l17 = new JLabel("17");
	JLabel l18 = new JLabel("18");
	JLabel l19 = new JLabel("19");
	JLabel l20 = new JLabel("20");

	public Table() {
		setBorder(new TitledBorder("테이블 현황"));
		setLayout(new GridLayout(4,5));
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		l1.setBackground(Color.YELLOW);
		add(l1);
		l2.setHorizontalAlignment(SwingConstants.CENTER);
		add(l2);
		l3.setHorizontalAlignment(SwingConstants.CENTER);
		add(l3);
		l4.setHorizontalAlignment(SwingConstants.CENTER);
		add(l4);
		l5.setHorizontalAlignment(SwingConstants.CENTER);
		add(l5);
		l6.setHorizontalAlignment(SwingConstants.CENTER);
		add(l6);
		l7.setHorizontalAlignment(SwingConstants.CENTER);
		add(l7);
		l8.setHorizontalAlignment(SwingConstants.CENTER);
		add(l8);
		l9.setHorizontalAlignment(SwingConstants.CENTER);
		add(l9);
		l10.setHorizontalAlignment(SwingConstants.CENTER);
		add(l10);
		l11.setHorizontalAlignment(SwingConstants.CENTER);
		add(l11);
		l12.setHorizontalAlignment(SwingConstants.CENTER);
		add(l12);
		l13.setHorizontalAlignment(SwingConstants.CENTER);
		add(l13);
		l14.setHorizontalAlignment(SwingConstants.CENTER);
		add(l14);
		l15.setHorizontalAlignment(SwingConstants.CENTER);
		add(l15);
		l16.setHorizontalAlignment(SwingConstants.CENTER);
		add(l16);
		l17.setHorizontalAlignment(SwingConstants.CENTER);
		add(l17);
		l18.setHorizontalAlignment(SwingConstants.CENTER);
		add(l18);
		l19.setHorizontalAlignment(SwingConstants.CENTER);
		add(l19);
		l20.setHorizontalAlignment(SwingConstants.CENTER);
		add(l20);
	}
}
