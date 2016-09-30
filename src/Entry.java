import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Entry {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		
		Properties props = new Properties();
		FileInputStream fln = new FileInputStream("dbDetails.properties");
		props.load(fln);
		
		
//TODO : GET DATABASE CONNECTION USING JDBC URL
		
		String url=props.getProperty("jdbc.url");
		Connection dbConnection = null;
		
		System.out.println("Connection Successful?"+ (dbConnection != null));
		PreparedStatement insertStatement = null;
		try{
		dbConnection = DriverManager.getConnection(url);
		/*insertStatement = dbConnection.createStatement();
		String insertQuery = props.getProperty("jdbc.query.insert");*/
		insertStatement = dbConnection.prepareStatement(props.getProperty("jdbc.query.insert"));
		String message = "This is JAVA";
		insertStatement.setString(1, message);
		insertStatement.setString(2, message);
		insertStatement.setString(3, message);
		int rows = insertStatement.executeUpdate();
		System.out.println(rows + " records is(are) added successfully");
		/*try(Statement selectStatement = dbConnection.createStatement()){
			ResultSet selectResult = selectStatement.executeQuery(props.getProperty("jdbc.query.select"));
			while(selectResult.next()){
				String message = selectResult.getString(1);
				System.out.println(message);
			}
		}*/
		
		}finally{
		
		insertStatement.close();
		dbConnection.close();
		}
		
	}
	
}
