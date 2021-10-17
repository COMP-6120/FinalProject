import javax.swing.*;
import java.awt.*;

public class DatabaseGui extends JFrame {

	// Instantiate main panel and subpanels
	private JPanel mainPanel = new JPanel();
	private JPanel titlePanel = new JPanel();
	private JPanel inputPanel = new JPanel();

	// Fonts
	private Font titleFont = new Font(Font.SERIF, 
									  Font.BOLD, 
									  35); // for the app title

	private Font componentFont = new Font(Font.SERIF,
										  Font.PLAIN,
										  17); // normal font for components


	// Things that will go on the titlePanel
	private JLabel appTitle = new JLabel("Database Application");
	
	private String[] dbTables = {"orders", "shipper", "subject",
								 "book", "customer", "employee",
								 "supplier", "order_detail"};
	
	private JComboBox<String> tableSelect = new JComboBox<>(dbTables);

	// Things that will go on the inputPanel
	private JTextField inputField = new JTextField(20);

	// Things that will go on the tablePanel
	private String[] dbTableHeaders = {"Test One", "Test Two", "Test Three",
									   "Test Four", "Test Five", "Test Six"};
	
	private Object[][] dbTableData = {
		{"One", "Two", "Three", "Four", "Five", "Six"},
		{"One", "Two", "Three", "Four", "Five", "Six"},
		{"One", "Two", "Three", "Four", "Five", "Six"},
		{"One", "Two", "Three", "Four", "Five", "Six"}
	};
	private JTable guiTable = new JTable(dbTableData, dbTableHeaders);

	// JScrollPane for the table instead of a JPanel
	private JScrollPane tablePane = new JScrollPane(guiTable);


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
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.PAGE_AXIS));

		// Adding things to titlePanel
		appTitle.setFont(titleFont);
		titlePanel.add(appTitle);
		// add glue for spacing between components
		titlePanel.add(Box.createRigidArea(new Dimension(80, 0)));
		tableSelect.setFont(componentFont);
		titlePanel.add(tableSelect);

		// Adding things to inputPanel
		inputPanel.add(inputField);

		// Adding things to table panel
		guiTable.setFillsViewportHeight(true);

		// Adding sub panels to main panel
		mainPanel.add(titlePanel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		mainPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
		mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		mainPanel.add(inputPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		mainPanel.add(tablePane);

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
