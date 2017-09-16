package seu.edu.server.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import seu.edu.common.Constant;
import seu.edu.server.srv.RequestThread;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.Window.Type;


// 2017.9.4 16:16 by雷仁昊
// 服务器界面
public class ServerFrame extends JFrame
{
	private JPanel contentPane;	
	private JTextField textField= new JTextField();
	
	public ServerFrame()
	{
		setResizable(false);
		try
		{
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(0, 0, 542, 550);
			contentPane = new JPanel();
			contentPane.setBackground(Color.WHITE);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			textField.setText("\u670D\u52A1\u5668\u7B49\u5F85\u5BA2\u6237\u8FDE\u63A5...");
			textField.setEditable(false);
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			textField.setFont(new Font("Dialog", Font.PLAIN, 20));
			textField.setBounds(10, 24, 516, 81);
			contentPane.add(textField);
			textField.setColumns(10);
			
			setLocationRelativeTo(null);			
			this.setVisible(true);				
		} catch (Exception e)
		{
			e.printStackTrace();
		}	
		
		ServerStart();					//启动服务器检测
	}
	
	private void ServerStart()			//接受来自服务端的请求并为其创建新进程	
	{		
		new Thread()
		{
			public void run()
			{
				try
				{
					final ServerSocket ss = new ServerSocket(Constant.SERVER_PORT);
					while (true)
					{
						Socket socket;
						socket = ss.accept();
						textField.setText("检测到连接");
						new RequestThread(socket).start();
					}
				} catch (IOException e)
				{
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
			
		}.start();
		
	}

}
