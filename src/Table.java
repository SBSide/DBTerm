import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class Table extends JPanel {

	/**
	 * Create the panel.
	 */
	JTextArea l1 = new JTextArea("1");
	JTextArea l2 = new JTextArea("2");
	JTextArea l3 = new JTextArea("3");
	JTextArea l4 = new JTextArea("4");
	JTextArea l5 = new JTextArea("5");
	JTextArea l6 = new JTextArea("6");
	JTextArea l7 = new JTextArea("7");
	JTextArea l8 = new JTextArea("8");
	JTextArea l9 = new JTextArea("9");
	JTextArea l10 = new JTextArea("10");
	JTextArea l11 = new JTextArea("11");
	JTextArea l12 = new JTextArea("12");
	JTextArea l13 = new JTextArea("13");
	JTextArea l14 = new JTextArea("14");
	JTextArea l15 = new JTextArea("15");
	JTextArea l16 = new JTextArea("16");
	JTextArea l17 = new JTextArea("17");
	JTextArea l18 = new JTextArea("18");
	JTextArea l19 = new JTextArea("19");
	JTextArea l20 = new JTextArea("20");

	public Table() {
		setBorder(new TitledBorder("테이블 현황"));
		setLayout(new GridLayout(4,5,15,15));
		l1.setEditable(false);
		l1.setBackground(Color.WHITE);
		add(l1);
		l2.setEditable(false);
		add(l2);
		l3.setEditable(false);
		add(l3);
		l4.setEditable(false);
		add(l4);
		l5.setEditable(false);
		add(l5);
		l6.setEditable(false);
		add(l6);
		l7.setEditable(false);
		add(l7);
		add(l8);
		add(l9);
		add(l10);
		add(l11);
		add(l12);
		add(l13);
		add(l14);
		add(l15);
		add(l16);
		add(l17);
		add(l18);
		add(l19);
		add(l20);
	}
	
}
