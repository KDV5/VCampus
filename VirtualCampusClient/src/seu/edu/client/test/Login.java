package seu.edu.client.test;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import seu.edu.client.thread.LibraryThread;
import seu.edu.common.Constant;

public class Login {
	public static void main(String[] args){
		System.out.println("请输入任意用户名");
		Scanner can= new Scanner (System.in);
		can.nextLine();
		System.out.println("欢迎进入图书馆系统");
		try {
			Socket socket = new Socket(Constant.SERVER_ADDRESS,Constant.SERVER_PORT);
			LibraryThread lt = new LibraryThread(socket);
			lt.start();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		
	}
	
}
