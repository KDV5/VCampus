package cn.seu.edu.server;

import java.awt.event.WindowEvent;

import javax.swing.JFrame;


public class Start {
	public static void main(String[] args){
		
		JFrame serverout=new JFrame("服务端连接情况");
		serverout.setSize(400, 700);
		serverout.setVisible(true);
		serverout.setLayout(null);
		
		Server server = new Server(serverout);
		

		
	}
}
