
package seu.edu.server.dao.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import seu.edu.common.DBHelper;
import seu.edu.vo.User;
/**
 * 
 * @author Haokai Li
 *
 */
public class IUserDAO {
	
	/**
	 * User 增
	 * @param user
	 * @return true
	 */
	public boolean addUser(User user){
		System.out.println(DBHelper.getLastRow1("tblUser"));
		String sql = "insert into tblUser(ID, password, identity, userID) values (" + (DBHelper.getLastRow1("tblUser") ) 
		+ ",'" +user.getPassword() + "'," + user.getIdentity() + ",'" + user.getUserID() + "')";
		if(this.getID(user.getUserID())){
			DBHelper.executeUpdate(sql);
			return true;
		} else
			return false;
	}

	/**
	 * User 删
	 * @param user
	 * @return true
	 */
	public boolean delUser(User user) {
		String sql = "delete from tblUser where userID = ?";
		boolean flag = DBHelper.executeUpdate(sql, new String[] {user.getUserID()});
		return flag;
	}
	
	/**
	 * 根据用户ID查询
	 * @param userID
	 * @return User
	 * @throws SQLException
	 */
	public User getUserByID(String userID) throws SQLException{
		String sql = "select * from tblUser where userID = '" + userID + "'";
		new DBHelper();
		ResultSet rs = DBHelper.executeQuery(sql);
		new UserDAOUtil();
		List<User> users = UserDAOUtil.ResultSet2List(rs);
		if (users != null && users.size() > 0){
			return users.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * 根据用户id设置密码
	 * @param user
	 * @return true
	 */
	public boolean setPwd(User user){
		String sql = "update tblUser set password = '" + user.getPassword() +"' where userID = '" + user.getUserID() + "'";
		return DBHelper.executeUpdate(sql);
	}
	
	/**
	 * 从学籍表中获得是否有该用户信息
	 * @param id
	 * @return
	 */
	public boolean getID(String id){
		String sql = "select StudentID from tblStudentStatus";
		new DBHelper();
		ResultSet rs = DBHelper.executeQuery(sql);
		try {
			while(rs.next()){
				if(id.equals(rs.getString(1))){
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public String getNameByID(String id){
		String sql = "select StudentName from tblStudentStatus where StudentID = '" + id + "'";
		new DBHelper();
		ResultSet rs = DBHelper.executeQuery(sql); 
		String name = null;
		try {
			while(rs.next())
				name = rs.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(name);
		return name;
	}
}
