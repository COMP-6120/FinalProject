import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseInterface {
    public static ArrayList<Map<String, Object>> retrieveData(String QUERY) {
        final String DATABASE = "jdbc:mysql://localhost:3307/group10";
        final String USERNAME = "root";
        final String PASSWORD = "comp6120";

        // Open connection.
        try (Connection conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QUERY)) {
            ArrayList<Map<String, Object>> resultList = new ArrayList<>();
            Map<String, Object> row;

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(metaData.getColumnName(i), rs.getObject(i));
                }
                resultList.add(row);
            }

            return resultList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
