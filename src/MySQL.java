import java.sql.*;


class MySQL {
	public static void main(String agrs[]) {
		try {
			Class.forName("com.musql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ltl","username","password"); 
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Account");
			while(rs.next())
				System.out.println(rs.getString(1)+" "+rs.getString(2));
			con.close();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
