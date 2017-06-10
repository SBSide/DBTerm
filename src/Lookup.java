import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;

public class Lookup extends JPanel {

	/**
	 * Create the panel.
	 */
	public Lookup() {
		setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane);
		
		JPanel pcustom = new JPanel();
		tabbedPane.addTab("고객", null, pcustom, null);
		
		JPanel ppr = new JPanel();
		tabbedPane.addTab("매출", null, ppr, null);
		
		JPanel pemplo = new JPanel();
		tabbedPane.addTab("직원", null, pemplo, null);
		
		JPanel pmenus = new JPanel();
		tabbedPane.addTab("메뉴", null, pmenus, null);

	}

}
