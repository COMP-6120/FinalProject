import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws SQLException {
        // GUI Instance
		DatabaseGui dGui = new DatabaseGui();
		dGui.display();

        // DatabaseInterface example.
        String newQuery = "SELECT * FROM supplier";
        ArrayList<Map<String, Object>> queryResults = DatabaseInterface.retrieveData(newQuery);
    }
}
