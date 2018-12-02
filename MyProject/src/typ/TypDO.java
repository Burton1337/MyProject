package typ;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TypDO {

	private Connection connection;
	
	public TypDO() throws Exception {
		Properties properties = new Properties();
		properties.load(new FileInputStream("MyProject.properties"));
		
		String url = properties.getProperty("url");
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");

		connection = DriverManager.getConnection(url, username, password);
	
		System.out.println("Sucsessfully connected to Database" + url);	
	}

	public List<TypBE> GetAllTyp () throws Exception {
		List<TypBE> list = new ArrayList<>();
		
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from typ");
			
			while (resultSet.next()) {
				TypBE typBE = ConvertRowToTyp(resultSet);
				list.add(typBE);
			}
			return list;
			
		} finally {
			//close(statement, resultSet);
		}	
	}
	
	public List<TypBE> FindTypByModellbez (String modellbez) throws Exception {
		List<TypBE> list = new ArrayList<>();
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = connection.prepareStatement("select * from typ where lower(modellbez) like lower(?)");
			modellbez = "%" + modellbez + "%";
			statement.setString(1,  modellbez);
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				TypBE typBE = ConvertRowToTyp(resultSet);
				list.add(typBE);
			}
			return list;
			
		} finally {
			//close(statement, resultSet); Hilfsmethode mit if statement != null then statement.close(); nicht connection closen!
			statement.close();
			resultSet.close();
		}	
	}
	
	public void AddTyp (TypBE typBE) throws Exception {
		PreparedStatement statement = null;
		try {
		statement = connection.prepareStatement("insert into typ (name, modellbez, entw_baur, antr_arch, marke) values (?, ?, ?, ?, ?)");
		statement.setString(1, typBE.getName());
		statement.setString(2, typBE.getModellbez());
		statement.setString(3, typBE.getEntw_baur());
		statement.setString(4, typBE.getAntr_arch());
		statement.setString(5, typBE.getMarke());
		
		statement.executeUpdate();
		}
		finally {
			statement.close(); // if not null, oder neue Methode close(statement);
		}
	}
	
	
	public TypBE ConvertRowToTyp (ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("id");
		String name = resultSet.getString("name");
		String modellbez = resultSet.getString("modellbez");
		String entw_baur = resultSet.getString("entw_baur");
		String antr_arch = resultSet.getString("antr_arch");
		String marke = resultSet.getString("marke");
		boolean storniert = resultSet.getBoolean("storniert");
		
		TypBE typBE = new TypBE(id, name, modellbez, entw_baur, antr_arch, marke, storniert);
		
		return typBE;
	}
		
	
	public static void main(String[] args) throws Exception {
		TypDO typDO = new TypDO();
		System.out.println(typDO.FindTypByModellbez("140i"));
		System.out.println(typDO.GetAllTyp());
	}

}
