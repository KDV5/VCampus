package seu.edu.client.srv;

import seu.edu.common.SocketClient;
import seu.edu.common.message.BasicMessage;
import seu.edu.common.message.UserMessage;
import seu.edu.vo.User;

/**
 * user业务层实现功能：login、register、resetPwd
 * @author Haokai Li
 *
 */
public class UserClientSrv {
	/**
	 * 登录 Login
	 * @param userID
	 * @param pwd
	 * @param identity
	 * @return 不存在该用户返回0，密码错误返回1，成功返回2， 身份不对返回3
	 */
	public UserMessage login(String userID, String pwd, int identity){
		UserMessage umsg = new UserMessage("Login");
		umsg.setUserID(userID);
		umsg.setIdentiy(identity);
		umsg.setPassword(pwd);
		SocketClient sc = new SocketClient();
		sc.sendRequestToServer(umsg);
		umsg = (UserMessage) sc.receiveDataFromServer();
		return umsg;
	}
	
	/**
	 * 注册 Register
	 * @param userID
	 * @param userName
	 * @param pwd1
	 * @param pwd2
	 * @param identity
	 * @return 两次密码不一致返回-1，已有该用户返回0，成功返回1，学籍中不存在返回2
	 */
	public int register(String userID, String pwd1, String pwd2, int identity){
		UserMessage umsg = new UserMessage("Register");
		if(!pwd1.equals(pwd2)){
			return -1;
		} else {
			umsg.setUserID(userID);
			umsg.setPassword(pwd1);
			umsg.setIdentiy(identity);
			SocketClient sc = new SocketClient();
			sc.sendRequestToServer(umsg);
			umsg = (UserMessage) sc.receiveDataFromServer();
			return umsg.getRegSucc();
		}
	}
	
	/**
	 * 重置密码 resetPassword
	 * @param userID
	 * @param oldPwd
	 * @param newPwd
	 * @return 重置密码成功返回1，原密码错误返回0
	 */
	public int resetPassword(String userID, String oldPwd, String newPwd){
		UserMessage umsg = new UserMessage("ResetPassword");
		umsg.setUserID(userID);
		umsg.setPassword(oldPwd);
		umsg.setNewPwd(newPwd);
		SocketClient sc = new SocketClient();
		sc.sendRequestToServer(umsg);
		umsg = (UserMessage) sc.receiveDataFromServer();
		return umsg.getResetSucc();
	}
}


















