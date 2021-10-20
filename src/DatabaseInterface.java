import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DatabaseInterface {

	private String user = "";
	private String dbName = "";
	private String port = "";
	private String pwd = "";
	private Connection conn = null;
	private Statement stmt = null;
	ResultSet rs = null;

	DatabaseInterface(String dbNameIn, String portIn, String userIn, String pwdIn) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:" + portIn 
																		 + "/" 
																		 + dbNameIn
																		 + "?user="
																		 + userIn
																		 + "&password="
																		 + pwdIn
																		 + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Unknown Error: " + e.getMessage());
		}
	}

	public void execStatement(String stmtIn) {
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(stmtIn);
			
			while(rs.next()) {
				System.out.println(rs.getString(1));
			}
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VenderError: " + e.getErrorCode());
		} catch (Exception e) {
			System.out.println("Unknown Error: " + e.getMessage());
		}
	}

}
