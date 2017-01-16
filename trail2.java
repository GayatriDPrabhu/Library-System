import java.sql.*;

public class trail2 {
	
	static Connection conn = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try{
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "mojojojo8");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from seperated");
			while(rs.next()){
				
				String isbn=rs.getString(1);
				String author=rs.getString(2);
				
				if(!author.equals("none"))
				{
				Statement stmt1 = conn.createStatement();
				String queryCheck = "SELECT Author_id from Authors WHERE Name= '" + author+"';";
				ResultSet rs1 = stmt1.executeQuery(queryCheck);
				if(rs1.absolute(1))
				{
					Statement stmt2 = conn.createStatement();
					String sql = "INSERT INTO Book_authors VALUES("+rs1.getInt(1)+",'"+isbn+"');";
					stmt2.executeUpdate(sql);  
				}
				} 			
			}
			
		}
		catch(SQLException ex) {
			System.out.println("Error in connection: " + ex.getMessage());
		}
		System.out.println("done");


	}

}
