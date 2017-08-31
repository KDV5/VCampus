/*
 * InfoChargeView
 * 信息管理员登录主页面
 *
 * @author Wang Zhaoyue
 *
 * @Date 2016/9/8
 *
 */
package cn.seu.edu.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cn.seu.edu.Client.Client;
import cn.seu.edu.InfoView.InfoInsertView;
import cn.seu.edu.InfoView.InfoOutputAllView;
import cn.seu.edu.InfoView.InfoQueryView;
import cn.seu.edu.message.IdData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.view.tchMainView.changeActionPerformed;

public class InfoChargeView {
	JFrame tl=new JFrame("虚拟校园");
	JPanel main=new JPanel();
	JLayeredPane jl=new JLayeredPane();
	
	Client client=null;
	String id=null;
	int xOld = 0;
	int yOld = 0;
	public InfoChargeView(Client Client,String id){
		this.client=Client;
		this.id=id;
		
		tl.setSize(1200, 700);
		tl.setLayout(null);
		tl.setUndecorated(true);
		tl.setVisible(true);
		tl.setResizable(false);
		int w = (tl.getToolkit().getScreenSize().width-tl.getWidth()) / 2;
		int h = (tl.getToolkit().getScreenSize().height-tl.getHeight()) / 2;
		tl.setLocation(w, h);
		
		//关闭按钮  
	    JButton closeButton = new JButton(new ImageIcon("images/close.png"));
		closeButton.setBounds(1010,0,20,20);
		closeButton.setContentAreaFilled(false);
		closeButton.setBorderPainted(false);
		jl.add(closeButton,new Integer(200));  
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice=JOptionPane.showConfirmDialog(null, "您确认要退出吗", "提示", JOptionPane.YES_NO_OPTION);
				if(choice==0){
					client.sendRequestToServer(new RequestData("exit"));
					System.exit(0);
					}
				}
			});
			    
		//最小化按钮
		JButton minButton = new JButton(new ImageIcon("images/zuixiaohua.png"));
	    minButton.setBounds(980,0,20,20);
	    minButton.setContentAreaFilled(false);
		minButton.setBorderPainted(false);
		jl.add(minButton,new Integer(200));
		minButton.addActionListener(new ActionListener(){
	    public void actionPerformed(ActionEvent e){
	    	tl.setExtendedState(JFrame.ICONIFIED);
	    	}
	    });
				
			    //移动窗口
			    tl.addMouseListener(new MouseAdapter() {
			    	  public void mousePressed(MouseEvent e) {
			    	    xOld = e.getX();//记录鼠标按下时的坐标
			    	    yOld = e.getY();
			    	   }
			    	  });
			    tl.addMouseMotionListener(new MouseMotionAdapter() {
			        @Override
			        public void mouseDragged(MouseEvent e) {
			        int xOnScreen = e.getXOnScreen();
			        int yOnScreen = e.getYOnScreen();
			        int xx = xOnScreen - xOld;
			        int yy = yOnScreen - yOld;
			        tl.setLocation(xx, yy);//设置拖拽后，窗口的位置
			        }
			       });
	
		
		JPanel menu =new JPanel();
		menu.setBounds(0, 0, 170, 700);
		menu.setLayout(null);
		menu.setOpaque(false);
		main.setOpaque(false);
		jl.setBounds(170, 0, 1100, 700);
		jl.setLayout(null);
		
		ImageIcon im1 = new ImageIcon("images/chaxun.png");
		JButton m1=new JButton(im1);
		m1.setContentAreaFilled(false);
		m1.setBounds(5, 50, 160, 80);
		m1.setBorder(null);
		menu.add(m1);
		ImageIcon im2 = new ImageIcon("images/zonglan.png");
		JButton m2=new JButton(im2);
		m2.setContentAreaFilled(false);
		m2.setBounds(5, 130, 160, 80);
		m2.setBorder(null);
		menu.add(m2);
		ImageIcon im3 = new ImageIcon("images/xinjian.png");
		JButton m3=new JButton(im3);
		m3.setContentAreaFilled(false);
		m3.setBounds(5, 210, 160, 80);
		m3.setBorder(null);
		menu.add(m3);
		ImageIcon im4 = new ImageIcon("images/quanyi.png");
		JButton m4=new JButton(im4);
		m4.setContentAreaFilled(false);
		m4.setBounds(5, 290, 160, 80);
		m4.setBorder(null);
		menu.add(m4);
		ImageIcon im5 = new ImageIcon("images/geren.png");
		JButton m5=new JButton(im5);
		m5.setContentAreaFilled(false);
		m5.setBounds(5, 380, 160, 80);
		m5.setBorder(null);
		menu.add(m5);
		ImageIcon im8 = new ImageIcon("images/qiehuan.png");
		JButton m8=new JButton(im8);
		m8.setContentAreaFilled(false);
		m8.setBounds(45, 665, 80, 40);
		m8.setBorder(null);
		menu.add(m8);
		
		JLayeredPane mback=new JLayeredPane();
		mback.setBounds(0, 0, 170, 700);
		mback.setLayout(null);
		
		JLabel menuback=new JLabel(new ImageIcon("images/menu4.png"));
		menuback.setBounds(0, 0, 170, 700);
		mback.add(menuback, new Integer(100));
		mback.add(menu, new Integer(200));
		JLabel mainback=new JLabel(new ImageIcon("images/main5.png"));
		mainback.setBounds(0, 0, 1100, 700);
		jl.add(mainback, new Integer(100));
		
		tl.getContentPane().add(mback);
		tl.getContentPane().add(jl);
		tl.repaint();
		
		m1.addActionListener(new infoQueryActionPerformed());
		m2.addActionListener(new  InfoOutputAllActionPerformed());
		m3.addActionListener(new InfoInsertActionPerformed());
		m4.addActionListener(new feedbackActionPerformed());
		m5.addActionListener(new mailActionPerformed());
		m8.addActionListener(new changeActionPerformed());
		
		tl.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				client.sendRequestToServer(new RequestData("exit"));
				System.exit(0);	
			}
		});
	}
	
	//查询
	public class infoQueryActionPerformed implements ActionListener{
		public void actionPerformed(ActionEvent e){
				 main.removeAll();
				 jl.remove(main);
				 new InfoQueryView(client,main);
	             main.setBounds(0, 0, 1040, 700);
	             main.setLayout(null);
	             jl.add(main,new Integer(200));
	             tl.repaint();
	             }
	}
	
	public class  InfoOutputAllActionPerformed implements ActionListener{
		public void actionPerformed(ActionEvent e){
				 main.removeAll();
				 jl.remove(main);
				 new  InfoOutputAllView(client,main);
	             main.setBounds(0, 0, 1040, 700);
	             main.setLayout(null);
	             jl.add(main,new Integer(200));
	             tl.repaint();
	             }
	}
	
	public class InfoInsertActionPerformed implements ActionListener{
		public void actionPerformed(ActionEvent e){
				 main.removeAll();
				 jl.remove(main);
				 new InfoInsertView(client,main);
	             main.setBounds(0, 0, 1040, 700);
	             main.setLayout(null);
	             jl.add(main,new Integer(200));
	             tl.repaint();
	             }
	}

	
		//权益
	 public class feedbackActionPerformed implements ActionListener{
			public void actionPerformed(ActionEvent e)
			{
				 main.removeAll();
				 jl.remove(main);
				 new FeedbackView(client,id,main);
				 main.setBounds(10, 0, 1040, 700);
	             main.setLayout(null);
	             jl.add(main,new Integer(200));
	             tl.repaint();
			
			}
		}
	 
	 //个人邮箱功能
	 public class mailActionPerformed implements ActionListener{
			public void actionPerformed(ActionEvent e)
			{
			     String mailNum = "0";
				 client.sendRequestToServer(new IdData("MyUnreadMailNum",id));
				 RequestData reqData = (RequestData) client.receiveDataFromServer();
				 
				 main.removeAll();
				 jl.remove(main);
				 new MailView(client,id,reqData.getRequest(),main);
	             main.setBounds(10, 0, 1040, 700);
	             main.setLayout(null);
	             jl.add(main,new Integer(200));
	             tl.repaint();
			
			}
	 }
	 
	//切换用户
		 public class changeActionPerformed implements ActionListener{
				public void actionPerformed(ActionEvent e)
				{
					int choice=JOptionPane.showConfirmDialog(null, "您确认要切换用户吗", "提示", JOptionPane.YES_NO_OPTION);
					if(choice==0){
						client.sendRequestToServer(new RequestData("exit"));
						tl.dispose();
						Client client = new Client();
						client.showLoginView();
					}
				}
		 }


}
