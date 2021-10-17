import javax.swing.*;
import java.awt.*;

public class DatabaseGui extends JFrame {

	// Instantiate main panel and subpanels
	private JPanel mainPanel = new JPanel();
	private JPanel titlePanel = new JPanel();
	private JPanel inputPanel = new JPanel();
	private JPanel inputSubmitPanel = new JPanel();
	private JPanel tableLabelPanel = new JPanel();

	// Fonts
	private Font titleFont = new Font(Font.SERIF, 
									  Font.BOLD, 
									  35); // for the app title

	private Font labelFont = new Font(Font.SERIF,
									  Font.BOLD,
									  18);
	
	private Font buttonFont = new Font(Font.SERIF,
									   Font.BOLD,
									   14);

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
	private JLabel inputLabel = new JLabel("Input Query");
	private JTextField inputField = new JTextField(20);

	// The input submit button will go on it's own panel so that 
	// we can better control it's positioning
	private JButton inputSubmit = new JButton("Submit Query");

	// Things that will go on the tablePanel
	// The data here is just for testing purposes
	// TODO: Have Gui reach out to database to pull headers for current table
	private String[] dbTableHeaders = {"OrderID", "CustomerID", "EmployeeID",
									   "OrderDate", "ShippedDate", "ShipperID"};
	
	// The data here is just for testing purposes
	// TODO: Have Gui reach out to database for data
	private Object[][] dbTableData = {
		{1, 1, 1, "8/1/2016", "8/3/2016", 1},
		{2, 1, 2, "8/4/2016", "NULL", "NULL"},
		{3, 2, 1, "8/1/2016", "8/4/2016", 2},
		{4, 4, 2, "8/4/2016", "8/4/2016", 1},
		{5, 1, 1, "8/4/2016", "8/5/2016", 1},
		{6, 4, 2, "8/4/2016", "8/5/2016", 1},
		{7, 3, 1, "8/4/2016", "8/5/2016", 1}
	};
	private JTable guiTable = new JTable(dbTableData, dbTableHeaders);

	// label for tableLabelPanel
	private JLabel tableLabel = new JLabel("Result Table");

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
		inputSubmitPanel.setLayout(new BoxLayout(inputSubmitPanel,
												 BoxLayout.LINE_AXIS));
		tableLabelPanel.setLayout(new FlowLayout());

		// Adding things to titlePanel
		appTitle.setFont(titleFont);
		titlePanel.add(appTitle);
		// add glue for spacing between components
		titlePanel.add(Box.createRigidArea(new Dimension(80, 0)));
		tableSelect.setFont(componentFont);
		titlePanel.add(tableSelect);

		// Adding things to inputPanel
		inputLabel.setFont(labelFont);
		inputPanel.add(inputLabel);
		inputPanel.add(inputField);

		// Adding things to inputSubmitPanel
		inputSubmit.setFont(buttonFont);
		inputSubmitPanel.add(inputSubmit);

		// Adding label to tableLabelPanel
		tableLabel.setFont(labelFont);
		tableLabelPanel.add(tableLabel);

		// Adding sub panels to main panel
		mainPanel.add(titlePanel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		mainPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		mainPanel.add(inputPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		mainPanel.add(inputSubmitPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		mainPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		mainPanel.add(tableLabelPanel);
		/* set the preferred and maximum size for the tablePane
		 * so that the pane doesn't grab and take over too much space */
		tablePane.setPreferredSize(new Dimension(550, 200));
		tablePane.setMaximumSize(new Dimension(550, 200));
		guiTable.setFillsViewportHeight(true);
		mainPanel.add(tablePane);

		// Adding main panel to main frame
		// vertical struct for a bit of space at the top of the window
		add(Box.createRigidArea(new Dimension(600,20)));
		add(mainPanel);

		// Frame Size set last
		setSize(600,480);
	}

	public void display() {
		setVisible(true);
	}
}
