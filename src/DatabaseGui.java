import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class DatabaseGui extends JFrame implements ActionListener {

	// Instance of the DatabaseInterface
	private DatabaseInterface dbInter = null;
	private ResultSet dbResults = null;
	private ResultSetMetaData dbMeta = null;

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
	private String[] dbTables;
	private JComboBox<String> tableSelect;

	// Things that will go on the inputPanel
	private JLabel inputLabel = new JLabel("Input Query");
	private JTextField inputField = new JTextField(20);

	// The input submit button will go on it's own panel so that 
	// we can better control it's positioning
	private JButton inputClear = new JButton("Clear");
	private JButton inputSubmit = new JButton("Submit");

	// Things that will go on the tablePanel
	private JTable guiTable = null;

	// label for tableLabelPanel
	private JLabel tableLabel = new JLabel("Result Table");

	// JScrollPane for the table instead of a JPanel
	private JScrollPane tablePane = new JScrollPane(guiTable);


	DatabaseGui(String dbNameIn, String dbPortIn, String userIn, String pwIn) {
		
		super("Comp 6120 Database Systems I Final Project");

		// initializing database interface
		try {
			dbInter = new DatabaseInterface(dbNameIn,
											dbPortIn,
											userIn,
											pwIn);
		} catch(SQLException e) {
			showErrorMessage(e);
		}

		// main frame configuration
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
	
		// Have the GUI reach out to the database for the table select list
		List<String> resultString = new ArrayList<String>();
		try {
			dbResults = dbInter.execStatement("SHOW TABLES");

			while(dbResults.next()) {
				resultString.add(dbResults.getString(1));
			}
		} catch(SQLException e) {
			showErrorMessage(e);
		}	

		// initialize the dbTables Array
		String[] dbTables = new String[resultString.size()];
		for(int i=0; i<resultString.size(); i++) {
			dbTables[i] = resultString.get(i);
		}
		
		// add the String[] array to the tableSelect gui component
		tableSelect = new JComboBox<>(dbTables);
		// add the component to titlePanel
		tableSelect.setFont(componentFont);

		// add action listener to tableSelect so we can do things with it
		tableSelect.addActionListener(this);
		titlePanel.add(tableSelect);

		// Adding things to inputPanel
		inputLabel.setFont(labelFont);
		inputPanel.add(inputLabel);
		inputPanel.add(inputField);

		// Adding things to inputSubmitPanel
		inputClear.setFont(buttonFont);
		inputClear.addActionListener(this);
		inputSubmit.setFont(buttonFont);
		inputSubmit.addActionListener(this);
		inputSubmitPanel.add(inputClear);
		inputSubmitPanel.add(Box.createRigidArea(new Dimension(10, 0)));
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
		// initially set up the guiTable here with the first db table in the list
		try {
			dbResults = dbInter.execStatement("SELECT * FROM " + dbTables[0]);
			createTable(dbResults);
		} catch (SQLException e) {
			showErrorMessage(e);
		}
		guiTable.setFillsViewportHeight(true);
		mainPanel.add(tablePane);

		// Adding main panel to main frame
		// vertical struct for a bit of space at the top of the window
		add(Box.createRigidArea(new Dimension(600,20)));
		add(mainPanel);

		// Frame Size set last
		setSize(600,480);
	}

	private void createTable(ResultSet rs) {
		
		// Make a new table model
		DefaultTableModel tableModel = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		try {
			dbMeta = dbResults.getMetaData(); // for column headers

			int columnCount = dbMeta.getColumnCount();
			String[] dbTableHeaders = new String[columnCount];

			// set table headers
			for(int i=0; i<columnCount; i++) {
				dbTableHeaders[i] = dbMeta.getColumnName(i+1);
			}

			tableModel.setColumnIdentifiers(dbTableHeaders);

			// get number of rows
			rs.last();
			int rowCount = rs.getRow();
			rs.first();

			// add the data to the table model
			for(int i=0; i<rowCount; i++) {
				String[] tableRow = new String[columnCount];
				for(int h=0; h<columnCount; h++) {
					tableRow[h] = rs.getString(dbTableHeaders[h]);
				}
				tableModel.addRow(tableRow);
				rs.next();
			}
		} catch(SQLException er) {
			showErrorMessage(er);
		}
		guiTable = new JTable(tableModel);
		tablePane.setViewportView(guiTable);
		tablePane.repaint();
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
	
		if(source instanceof JComboBox) {
			// JComboBox Event
			try {
				dbResults = dbInter.execStatement("SELECT * FROM " + ((JComboBox) source).getSelectedItem().toString());

				createTable(dbResults);
			} catch (SQLException er) {
				showErrorMessage(er);
			}

		}else if(source instanceof JButton) {
			// JButton Event
			

			if(((JButton) source).getText().equals("Submit")) {
				// get the text (query) from the input box and send it to the db
				if(!inputField.getText().equals("")) {
					try{  
						dbResults = dbInter.execStatement(inputField.getText());
						createTable(dbResults);
					} catch (SQLException er) {
						showErrorMessage(er);
					}
				}
			} else if(((JButton) source).getText().equals("Clear")) {
				inputField.setText("");
			}
		}
	}

	private void showErrorMessage(SQLException e) {
		JOptionPane.showMessageDialog(this, e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
	}	

	public void display() {
		setVisible(true);
	}
}
