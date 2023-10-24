package technical_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import technical_model.user;

public class UserDao {
    
	 private Connection con;
	 private ResultSet rs;
	 private String query;
	 private PreparedStatement pst;
	 
	public UserDao(Connection con) {
		this.con = con;
	
	}
	 public user userLogin(String email,String password) {
		 user user = null;
		 try {
			 query = "select * from users where email=? and password=?";
			 pst = this.con.prepareStatement(query);
			 pst.setString(1, email);
			 pst.setString(2, password);
			 rs = (ResultSet)pst.executeQuery();
			 
			 
			 if(((ResultSet) rs).next()) {
				 user = new user();
				 user.setId(((ResultSet) rs).getInt("id"));
				 user.setName(((ResultSet) rs).getString("name"));
				 user.setEmail(((ResultSet) rs).getString("email"));
			 } 
			 }catch(Exception e) {
			  e.printStackTrace();
			  System.out.print(e.getMessage());
		 }
		 return user;
	 }
}
