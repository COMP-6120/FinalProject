import javax.swing.*;
import java.awt.*;

public class DatabaseGui extends JFrame {
	
	private JPanel panelOne = new JPanel();
	private JButton testButton = new JButton("Test");

	DatabaseGui() {
		// main frame configuration
		super("Database Application");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new FlowLayout());

		// Layouts for the panels
		panelOne.setLayout(new FlowLayout());

		// Adding things to panelOne
		panelOne.add(testButton);


		// Adding panels to main frame
		add(panelOne);

		// Frame Size set last
		setSize(600,600);

	}

	public void display() {
		setVisible(true);
	}
}
