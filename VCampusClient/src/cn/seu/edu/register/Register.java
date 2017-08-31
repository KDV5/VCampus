package cn.seu.edu.register;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import cn.seu.edu.Client.Client;
import cn.seu.edu.message.LoginData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.registerView.RegisterView;
import cn.seu.edu.view.InfoChargeView;
import cn.seu.edu.view.LibChargeView;
import cn.seu.edu.view.ShopChargeView;
import cn.seu.edu.view.mainChargeView;
import cn.seu.edu.view.stuMainView;
import cn.seu.edu.view.tchMainView;

import java.awt.Graphics;  
import java.awt.Graphics2D;  
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.awt.Image;


public class Register extends JFrame{
	public JTextField passname;
	public JPasswordField password;
	public JButton register_button;
	public JButton reinput_button;
	String uName = null;
	String uPassword = null;
	Client client = null;
	
	public Register(Client client1) {
		this.client=client1;

		passname = new JTextField(7);
		password = new JPasswordField(7);
		register_button = new JButton("登录");
		reinput_button = new JButton("注册");
		setLayout(null);
		Container c = getContentPane();
		JPanel[] panels = {new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel()};
		panels[0].add(new JLabel("用户"));
		panels[0].setBounds(26, 19,128 , 100);
		panels[3].add(passname);
		panels[3].setBounds(154, 19,128 ,100 );
		//panels[1].setLayout(new BoxLayout(panels[1], BoxLayout.X_AXIS));
		panels[1].add(new JLabel("密码"));
		panels[1].setBounds(26, 84,128 ,100 );
		panels[4].add(password);
		panels[4].setBounds(154, 84,128 ,100 );
		
		//panels[3].setLayout(new BoxLayout(panels[3], BoxLayout.X_AXIS));
		panels[2].add(register_button);
		panels[2].setBounds(41, 141,128 ,100 );
		panels[5].add(reinput_button);
		panels[5].setBounds(176,141,128 ,100 );
		
		
		
		for (JPanel panel : panels) {
			c.add(panel);
			
		}
		
	     String picPath = "images/兔子.png";
			Icon icon =new ImageIcon(picPath);
			JLabel lab =null;
			lab= new JLabel(icon,JLabel.CENTER);
			add(lab);
			lab.setSize(38,50);
			lab.setLocation(296,64);
			lab.setVisible(true);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUndecorated(true);
		setFocusableWindowState(true);
		JRootPane rp =getRootPane();
		rp.setWindowDecorationStyle(JRootPane.FRAME);
		pack();
		
		register_button.addActionListener(new loginActionPerformed());
		reinput_button.addActionListener(new registerActionPerformed());
        
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				client.sendRequestToServer(new RequestData("exit"));
				System.exit(0);	
			}
		});
			
	}

		public class loginActionPerformed implements ActionListener{
			public void actionPerformed(ActionEvent e)
			{
				uName = passname.getText();
				uPassword = password.getText();
				client.sendRequestToServer(new LoginData("login", uName,uPassword));
				RequestData reqData = (RequestData)client.receiveDataFromServer();
//				System.out.println("request:"+reqData.getRequest());
//				System.out.println("userName:"+login.getUserName());
//				System.out.println("password:"+login.getUserPassword());
				if("true".equals(reqData.getRequest())){
					if(uName.length()==8){
						new stuMainView(client,uName);
					}else if(uName.length()==7){
						new tchMainView(client,uName);
					}else if(uName.length()==6){
						if(uName.startsWith("10")){
							new InfoChargeView(client,uName);
						}else if(uName.startsWith("13")){
							new ShopChargeView(client,uName);
						}else if(uName.startsWith("14")){
							new LibChargeView(client,uName);
						}else{
							new mainChargeView(client,uName);
						}
					}

					dispose();
				}else if("false".equals(reqData.getRequest())){
					JOptionPane.showMessageDialog(null, "密码错误","提示对话框",1);
				}else if("unexist".equals(reqData.getRequest())){
					JOptionPane.showMessageDialog(null, "该用户不存在","提示对话框",1);
				}	
			}
		}
		
		public class registerActionPerformed implements ActionListener{
			public void actionPerformed(ActionEvent e){
				try{
					UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
				}
				catch(Exception e1){
					System.err.println("Oops! Something went wrong!");
				}
				
				RegisterView v = new RegisterView(client);
				dispose();
				v.setSize(360,244);
				v.setLocation(350,250);
		        v.setTitle("虚拟校园系统");
		        v.setVisible(true);
		        int w = (v.getToolkit().getScreenSize().width-v.getWidth()) /2;
				int h = (v.getToolkit().getScreenSize().height-v.getHeight())/2 ;
				v.setLocation(w, h);
			}
		}
		
		
}
