import java.sql.*;

public class Main {
    static final String DATABASE = "jdbc:mysql://localhost:3307/group10";
    static final String USERNAME = "root";
    static final String PASSWORD = "comp6120";
    static final String QUERY = "SELECT * FROM supplier";

    public static void main(String[] args) {
        // Open connection.
        try (Connection conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QUERY)) {
            // Extract data.
            while (rs.next()) {
                // Print by column name.
                System.out.print("Supplier ID: " + rs.getInt("SupplierID") + "\n");
                System.out.print("Supplier Name: " + rs.getString("CompanyName") + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
