package cn.seu.edu.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import cn.seu.edu.message.RequestData;

public class Server {
	
	JTextArea t1 = new JTextArea();

	public ServerSocket serverSocket = null;
	public Socket socket = null;
	
	public Server(JFrame t){
		t1.setLineWrap(true);
		t1.setFont(new java.awt.Font("Dialog", 1, 20));
		t1.setText("");
		t1.append("服务器连接状态:\r\n");
		t.repaint();
		JScrollPane scroll = new JScrollPane(t1); 
		scroll.setBounds(0,0,400,560);
		t.getContentPane().add(scroll);
		
		JButton close=new JButton("停止");
		close.setBounds(150,580,100,30);
		t.getContentPane().add(close);
		close.addActionListener(new closeAction());
		
		try {
			serverSocket = new ServerSocket(4567);
			t1.append("服务端等待客户端连接...\r\n");
			t.repaint();
			System.out.println("服务端等待客户端连接...");
			while(true){
				socket = serverSocket.accept();
				t1.append("服务端接收到客户端的请求...\r\n");
				t.repaint();
				System.out.println("服务端接收到客户端的请求...");
				new ServerReaderThread(socket).start();
		
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
	class closeAction implements ActionListener{
		 public void actionPerformed (ActionEvent e){
			 try {
				serverSocket.close();
			} catch (IOException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			 System.exit(0);
		 }
	}
}
