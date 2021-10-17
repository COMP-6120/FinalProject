import javax.swing.*;
import java.awt.*;

public class DatabaseGui extends JFrame {

	// Instantiate main panel and subpanels
	private JPanel mainPanel = new JPanel();
	private JPanel titlePanel = new JPanel();
	private JPanel tableSelectPanel = new JPanel();

	// Fonts
	private Font titleFont = new Font(Font.SERIF, 
									  Font.BOLD, 
									  35); // for the app title

	private Font componentFont = new Font(Font.SERIF,
										  Font.PLAIN,
										  17); // normal font for components


	// Things that will go on the titlePanel
	private JLabel appTitle = new JLabel("Database Application");
	
	// Things that will go on the tableSelectPanel
	private String[] dbTables = {"orders", "shipper", "subject",
								 "book", "customer", "employee",
								 "supplier", "order_detail"};
	
	private JComboBox<String> tableSelect = new JComboBox<>(dbTables);


	DatabaseGui() {
		// main frame configuration
		super("Comp 6120 Database Systems I Final Project");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocation(60,60);
		setLayout(new FlowLayout());

		// Layout for the panels
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.LINE_AXIS));

		// Adding things to titlePanel
		appTitle.setFont(titleFont);
		titlePanel.add(appTitle);
		// add glue for spacing between components
		titlePanel.add(Box.createRigidArea(new Dimension(80, 0)));
		tableSelect.setFont(componentFont);
		titlePanel.add(tableSelect);

		// Adding sub panels to main panel
		mainPanel.add(titlePanel);

		// Adding main panel to main frame
		// vertical struct for a bit of space at the top of the window
		add(Box.createRigidArea(new Dimension(600,20)));
		add(mainPanel);

		// Frame Size set last
		setSize(600,600);
	}

	public void display() {
		setVisible(true);
	}
}
