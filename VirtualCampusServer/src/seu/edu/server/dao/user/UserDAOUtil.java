package seu.edu.server.dao.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import seu.edu.vo.User;

public class UserDAOUtil {
	
	public static List<User> ResultSet2List(ResultSet rs) throws SQLException{
		List<User> users = new ArrayList<User>();
		while(rs.next()){
			User u = new User();
			u.setUserID(rs.getString(4));
			u.setIdentity(rs.getInt(3));
			u.setPassword(rs.getString(2));
			
			users.add(u);
		}
		return users;
	}
}
