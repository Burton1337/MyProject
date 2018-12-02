import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class typDO {

	private Connection connection;
	
	public typDO() throws Exception {
		Properties properties = new Properties();
		properties.load(new FileInputStream("MyProject.properties"));
		
		String url = properties.getProperty("url");
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");

		connection = DriverManager.getConnection(url, username, password);
	
		System.out.println("Sucsessfully connected to Database" + url);	
	}

	
	
	public static void main(String[] args) {

	}

}
