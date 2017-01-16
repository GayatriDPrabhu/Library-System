import java.sql.*;

public class trial1 {
	
	static Connection conn = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try{
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "mojojojo8");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from seperated");
			while(rs.next()){
				
				String author=rs.getString(2);
				
				if(!author.equals("none"))
				{
				Statement stmt1 = conn.createStatement();
				String queryCheck = "SELECT * from Authors WHERE Name= '" + author+"';";
				ResultSet rs1 = stmt1.executeQuery(queryCheck);
				if(!(rs1.absolute(1)))
				{
					Statement stmt2 = conn.createStatement();
					ResultSet rs2 = stmt2.executeQuery("select max(author_id) from authors");
					int id=0;
					if(rs2.next())
					{				 	
					 id=rs2.getInt(1)+1;
					}
				  String sql = "INSERT INTO Authors VALUES("+id+",'"+author+"');";
				  stmt1.executeUpdate(sql);
				}
				} 
				  
			
			}
			
		}
		catch(SQLException ex) {
			System.out.println("Error in connection: " + ex.getMessage());
		}

	}

}
