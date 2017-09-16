package seu.edu.server.srv.thread;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

import seu.edu.common.message.UserMessage;
import seu.edu.server.dao.user.IUserDAO;
import seu.edu.server.srv.RequestThread;
import seu.edu.vo.User;

/**
 * 登陆界面的线程
 * @author Haokai Li
 *
 */
public class UserThread extends Thread{
	private Socket socket;
	private RequestThread rt = null;
	private UserMessage umsg = null;
	public UserThread(RequestThread rt, UserMessage umsg) {
		// TODO Auto-generated constructor stub
		this.rt = rt;
		this.umsg = umsg;
	}

	public void run(){
		IUserDAO iud = new IUserDAO();
		// 登录
		if("Login".equals(umsg.getRequestType())){
			
			User user = new User();
			try {
				user = iud.getUserByID(umsg.getUserID()); // 根据用户ID查询密码
				String name = iud.getNameByID(umsg.getUserID());
				if(user == null){
					umsg.setMatchOrNot(0);
				}else if(user.getPassword().equals(umsg.getPassword()) && user.getIdentity() == umsg.getIdentity()){
					umsg.setMatchOrNot(2); // 把输入进来的umsg传回去了
					umsg.setUserName(name);
				}else if(!user.getPassword().equals(umsg.getPassword()) && user.getIdentity() == umsg.getIdentity()){
					umsg.setMatchOrNot(1); 
				}else if(user.getPassword().equals(umsg.getPassword()) && user.getIdentity() != umsg.getIdentity()){
					umsg.setMatchOrNot(3);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rt.SendToClient(umsg);
			
		// 注册:两次密码不一致返回-1，已有该用户返回0，成功返回1，学籍中不存在返回2
		} else if("Register".equals(umsg.getRequestType())){
			User user = new User();
			user = umsg.setUser();
			boolean flag = false;
			try {
				flag = iud.getUserByID(umsg.getUserID()) == null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!flag){
				umsg.setRegSucc(0);
			} else if(iud.addUser(user)){
				umsg.setRegSucc(1);
			}else
				umsg.setRegSucc(2);
			rt.SendToClient(umsg);
			
		// 重置密码	
		}else if("ResetPassword".equals(umsg.getRequestType())){
			 User user = new User();
			 try {
				user = iud.getUserByID(umsg.getUserID());
			 } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			 }
			 if(user.getPassword().equals(umsg.getPassword())){
				 user.setPassword(umsg.getNewPwd());
				 iud.setPwd(user);
				 umsg.setResetSucc(1);
			 }else {
				 umsg.setResetSucc(0);
			 }
			 rt.SendToClient(umsg);
		}
	}
	
}

