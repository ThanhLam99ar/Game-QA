import java.sql.Connection;
import java.sql.DriverManager;

public class JVConnected
{
	Connection conn= null;
	public static Connection getConnection()
	{
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ltl","root","laithanhlam");
			return con;
			
		} catch (Exception e) {}
		return null; 
	}
}

