import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class Entry2 {

	public static void main(String[] args) throws IOException, SQLException {
		Properties props = new Properties();
		FileInputStream fln = new FileInputStream("dbDetails.properties");
		props.load(fln);
		String url=props.getProperty("jdbc.url");
		Connection dbConnection = null;
		PreparedStatement createStatement = null;
		PreparedStatement insertStatement = null;
		PreparedStatement selectStatement = null;
		ResultSet selectResult = null;
		try{
			dbConnection = DriverManager.getConnection(url);
			createStatement = dbConnection.prepareStatement(props.getProperty("jdbc.query.create"));
			createStatement.executeUpdate();
			String name = "ABCCDE";
			int age = 21;
			insertStatement = dbConnection.prepareStatement(props.getProperty("jdbc.query.insert"));
			insertStatement.setString(1, name);
			insertStatement.setInt(2, age);
			insertStatement.executeUpdate();
			selectStatement = dbConnection.prepareStatement(props.getProperty("jdbc.query.select"),ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
			selectResult = selectStatement.executeQuery();
			selectResult.next();
			selectResult.next();
			selectResult.next();
			selectResult.updateInt("age", 23);
			selectResult.updateRow();
			
		}finally{
			dbConnection.close();
			createStatement.close();
		}
		
		
		
	}
	
	
	
	
	
}
