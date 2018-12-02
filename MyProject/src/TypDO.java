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

	public List<Typ> GetAllTyp () throws Exception {
		List<Typ> list = new ArrayList<>();
		
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from typ");
			
			while (resultSet.next()) {
				Typ tempTyp = ConvertRowToTyp(resultSet);
				list.add(tempTyp);
			}
			return list;
			
		} finally {
			//close(statement, resultSet);
		}	
	}
	
	public List<Typ> FindTypByModellbez (String modellbez) throws Exception {
		List<Typ> list = new ArrayList<>();
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = connection.prepareStatement("select * from typ where modellbez like ?");
			modellbez += "%";
			statement.setString(1,  modellbez);
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				Typ tempTyp = ConvertRowToTyp(resultSet);
				list.add(tempTyp);
			}
			return list;
			
		} finally {
			//close(statement, resultSet); Hilfsmethode mit if statement != null then statement.close();
			//statement.close();
			//resultSet.close();
			//connection.close();
		}	
	}
	
	
	public Typ ConvertRowToTyp (ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("id");
		String name = resultSet.getString("name");
		String modellbez = resultSet.getString("modellbez");
		String entw_baur = resultSet.getString("entw_baur");
		String antr_arch = resultSet.getString("antr_arch");
		String marke = resultSet.getString("marke");
		boolean storniert = resultSet.getBoolean("storniert");
		
		Typ tempTyp = new Typ(id, name, modellbez, entw_baur, antr_arch, marke, storniert);
		
		return tempTyp;
	}
		
	
	public static void main(String[] args) throws Exception {
		TypDO typDO = new TypDO();
		System.out.println(typDO.FindTypByModellbez("140i"));
		System.out.println(typDO.GetAllTyp());
	}

}