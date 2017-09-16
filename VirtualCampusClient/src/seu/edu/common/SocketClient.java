package seu.edu.common;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import seu.edu.client.thread.LibraryThread;
import seu.edu.common.message.BasicMessage;

/*
 * 创建socket
 * @auther YYL
 * @date 9.7
 * 该类
 */
public class SocketClient {
    public Socket socket = null;
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;
    
	public SocketClient(){
		try {
			socket = new Socket(Constant.SERVER_ADDRESS,Constant.SERVER_PORT);			
			ois = new ObjectInputStream(socket.getInputStream());			
			oos = new ObjectOutputStream(socket.getOutputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 这里写显示登陆界面的函数
	 */
	public void ShowLoginView(){
		System.out.println("请输入任意用户名");
		Scanner can= new Scanner (System.in);
		can.nextLine();
		System.out.println("欢迎进入图书馆系统");
		try {
			LibraryThread lt = new LibraryThread(this);
			lt.start();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	//向服务器发送请求
	public void sendRequestToServer(BasicMessage rd){
		try {
			oos.writeObject(rd);
//			System.out.println("发送请求成功");
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	//从服务器接收数据
	public Object receiveDataFromServer(){
		Object obj = null;
			try {
				obj = ois.readObject();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		return obj;
	}
	
}
