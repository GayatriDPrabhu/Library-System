import java.sql.*;


public class trial {
	
	static Connection conn = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try{
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "mojojojo8");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from author_trial"); //author_trial table contained isbn column and a column with multiple authors
			while(rs.next()){
				String isbn=rs.getString(1);
				String s=rs.getString(2);
				String arry[]=s.split(",");
				for(int i=0;i<arry.length;i++)
				{
					
					boolean c=true;
					for(int j=0;j<i;j++)
					{
						if(arry[i].equals(arry[j]))
							c=false;
					}
					if(c)
					{
					 String sql = "INSERT INTO seperated VALUES('"+isbn+"','"+arry[i]+"')"; //seperated table had the authors seperated 
					 Statement stmt1 = conn.createStatement();
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
