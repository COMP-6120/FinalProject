import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws SQLException {
        // Swing interface goes here.
        JFrame frame = new JFrame("Database Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        JButton button = new JButton("Press");
        // Adds button to content pane of frame.
        frame.getContentPane().add(button);
        frame.setVisible(true);

        // DatabaseInterface example.
        String newQuery = "SELECT * FROM supplier";
        ArrayList<Map<String, Object>> queryResults = DatabaseInterface.retrieveData(newQuery);
    }
}
