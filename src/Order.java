import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

public class Order extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public Order() {
		setBorder(new TitledBorder(null, "주문내역", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));
		
		JTextPane textPane = new JTextPane();
		add(textPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.EAST);
		panel.setLayout(new GridLayout(7, 1, 10, 10));
		
		JLabel label = new JLabel("고객명");
		panel.add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		panel.add(textField);
		
		JLabel label_1 = new JLabel("테이블명");
		panel.add(label_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"}));
		panel.add(comboBox);
		
		JButton btnNewButton = new JButton("주문");
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("취소");
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("결제");
		panel.add(btnNewButton_2);

	}

}
