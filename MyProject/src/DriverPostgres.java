import java.sql.*; 

public class DriverPostgres {

	private static String url = "jdbc:postgresql://localhost:5432/MyProject";
	private static String username = "postgres";
	private static String password = " ";
	
	public static void main(String[] args) {
		
		
		
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			//CallableStatement statement = connection.prepareCall("call double_deltagewicht (?)");

			
		}
		catch (Exception e) {
			//e.PrintStackTrace();
		}
		finally {
			//close(connection, resultSet)
		}
	}
}