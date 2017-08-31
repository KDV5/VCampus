/*
 * stuMainView
 * 学生登录主页面
 *
 * @author Wang Zhaoyue
 *
 * @Date 2016/9/8
 *
 */

package cn.seu.edu.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cn.seu.edu.Client.Client;
import cn.seu.edu.EduView.StuCourListView;
import cn.seu.edu.EduView.StuGradeView;
import cn.seu.edu.EduView.stuChooseView;
import cn.seu.edu.InfoView.InfoNewStuView;
import cn.seu.edu.InfoView.InfoStuView;
import cn.seu.edu.LibView.UserQueryView;
import cn.seu.edu.LibView.UserSearchView;
import cn.seu.edu.message.BankData;
import cn.seu.edu.message.IdData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.register.ChangePassword;
import cn.seu.edu.shopView.AddNewGoods;
import cn.seu.edu.shopView.BuyGoodsView;
import cn.seu.edu.shopView.ChangeGoodsInfoView;
import cn.seu.edu.shopView.OutputHistoryView;
import cn.seu.edu.view.tchMainView.shopActionPerformed;
import cn.seu.edu.view.tchMainView.shopActionPerformed.AddNewGoodsListener;
import cn.seu.edu.view.tchMainView.shopActionPerformed.BuyGoodsViewListener;
import cn.seu.edu.view.tchMainView.shopActionPerformed.ChangeGoodsInfoListener;
import cn.seu.edu.view.tchMainView.shopActionPerformed.OutputHistoryListener;


public class stuMainView extends JFrame {
	JFrame tl=new JFrame("虚拟校园");
	JPanel mb=new JPanel();
	JPanel main=new JPanel();
	JLayeredPane jl=new JLayeredPane();
	
	Client client=null;
	String id=null;
	int xOld = 0;
	int yOld = 0;
	
	public stuMainView(Client Client,String id){
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
		mb.setOpaque(false);
		jl.setBounds(170, 0, 1100, 700);
		jl.setLayout(null);
		
		ImageIcon im1 = new ImageIcon("images/xinxi.png");
		JButton m1=new JButton(im1);
		m1.setContentAreaFilled(false);
		m1.setBounds(5, 50, 160, 80);
		m1.setBorder(null);
		menu.add(m1);
		ImageIcon im2 = new ImageIcon("images/jiaowu.png");
		JButton m2=new JButton(im2);
		m2.setContentAreaFilled(false);
		m2.setBounds(5, 130, 160, 80);
		m2.setBorder(null);
		menu.add(m2);
		ImageIcon im3 = new ImageIcon("images/shangdian.png");
		JButton m3=new JButton(im3);
		m3.setContentAreaFilled(false);
		m3.setBounds(5, 210, 160, 80);
		m3.setBorder(null);
		menu.add(m3);
		ImageIcon im4 = new ImageIcon("images/yinghang.png");
		JButton m4=new JButton(im4);
		m4.setContentAreaFilled(false);
		m4.setBounds(5, 290, 160, 80);
		m4.setBorder(null);
		menu.add(m4);
		ImageIcon im5 = new ImageIcon("images/tushu.png");
		JButton m5=new JButton(im5);
		m5.setContentAreaFilled(false);
		m5.setBounds(5, 380, 160, 80);
		m5.setBorder(null);
		menu.add(m5);
		ImageIcon im6 = new ImageIcon("images/quanyi.png");
		JButton m6=new JButton(im6);
		m6.setContentAreaFilled(false);
		m6.setBounds(5, 470, 160, 80);
		m6.setBorder(null);
		menu.add(m6);
		ImageIcon im7 = new ImageIcon("images/geren.png");
		JButton m7=new JButton(im7);
		m7.setContentAreaFilled(false);
		m7.setBounds(5, 560, 160, 80);
		m7.setBorder(null);
		menu.add(m7);
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
		
		//空白处单击移去小菜单
		tl.addMouseListener(new MouseAdapter() {
	    	  public void mouseClicked(MouseEvent e) {
	    	    jl.remove(mb);
	    	    tl.repaint();
	    	    }
	    	  });
		
		m1.addActionListener(new infoActionPerformed());
		m2.addActionListener(new eduActionPerformed());
		m3.addActionListener(new shopActionPerformed());
		m4.addActionListener(new bankActionPerformed());
		m5.addActionListener(new LibActionPerformed());
		m6.addActionListener(new feedbackActionPerformed());
		m7.addActionListener(new mailActionPerformed());
		m8.addActionListener(new changeActionPerformed());
		
		tl.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				client.sendRequestToServer(new RequestData("exit"));
				System.exit(0);	
			}
		});
		
		
	}
	
	//学生信息管理按钮响应
	public class infoActionPerformed implements ActionListener{
		public void actionPerformed(ActionEvent e){
			 mb.removeAll();
			 jl.remove(mb);
			 ImageIcon ic1 = new ImageIcon("images/jiben.png");
			 JButton s1=new JButton(ic1);
			 s1.setContentAreaFilled(false);
			 s1.setBounds(0, 0, 120, 50);
			 s1.setBorder(null);
			 mb.add(s1);
			 ImageIcon ic2 = new ImageIcon("images/weihu.png");
			 JButton s2=new JButton(ic2);
			 s2.setContentAreaFilled(false);
			 s2.setBounds(0, 50, 120, 50);
			 s2.setBorder(null);
			 mb.add(s2);
			 ImageIcon ic3 = new ImageIcon("images/xiugaimima.png");
			 JButton s3=new JButton(ic3);
			 s3.setContentAreaFilled(false);
			 s3.setBounds(0, 100, 120, 50);
			 s3.setBorder(null);
			 mb.add(s3);
			
			 mb.setBounds(0, 40, 120, 150);
			 mb.setLayout(null);
			 jl.add(mb,new Integer(300));
			 tl.repaint();
			 
			 s1.addActionListener(new InfoStuAction());
			 s2.addActionListener(new InfoNewStuAction());
			 s3.addActionListener(new chanPasswordsAction());
		}
		public class InfoStuAction implements ActionListener{
			public void actionPerformed(ActionEvent e){
				 jl.remove(mb);
				 main.removeAll();
				 jl.remove(main);
				 new InfoStuView(client,id,main);
	             main.setBounds(0, 0, 1040, 700);
	             main.setLayout(null);
	             jl.add(main,new Integer(200));
	             tl.repaint();
	             }
			}
		public class InfoNewStuAction implements ActionListener{
			public void actionPerformed(ActionEvent e){
				 jl.remove(mb);
				 main.removeAll();
				 jl.remove(main);
				 new InfoNewStuView(client,id,main);
	             main.setBounds(0, 0, 1040, 700);
	             main.setLayout(null);
	             jl.add(main,new Integer(200));
	             tl.repaint();
	             }
			}
		public class chanPasswordsAction implements ActionListener{
			public void actionPerformed(ActionEvent e){
				 jl.remove(mb);
				 main.removeAll();
				 jl.remove(main);
				 new ChangePassword(client,id,main);
	             main.setBounds(0, 0, 1040, 700);
	             main.setLayout(null);
	             jl.add(main,new Integer(200));
	             tl.repaint();
	             }
			}
	}
	
	//教务处
	 class eduActionPerformed implements ActionListener{
		 public void actionPerformed (ActionEvent e){
			 mb.removeAll();
			 jl.remove(mb);
			 ImageIcon ic1 = new ImageIcon("images/zhengchang.png");
			 JButton s1=new JButton(ic1);
			 s1.setContentAreaFilled(false);
			 s1.setBounds(0, 0, 120, 40);
			 s1.setBorder(null);
			 mb.add(s1);
			 ImageIcon ic2 = new ImageIcon("images/kebiao.png");
			 JButton s2=new JButton(ic2);
			 s2.setContentAreaFilled(false);
			 s2.setBounds(0, 50, 120, 40);
			 s2.setBorder(null);
			 mb.add(s2);
			 ImageIcon ic3 = new ImageIcon("images/chengji.png");
			 JButton s3=new JButton(ic3);
			 s3.setContentAreaFilled(false);
			 s3.setBounds(0, 100, 120, 40);
			 s3.setBorder(null);
			 mb.add(s3);
				
			 mb.setBounds(0, 100, 130, 150);
			 mb.setLayout(null);
			 jl.add(mb,new Integer(300));
			 tl.repaint();
			 
			 s1.addActionListener(new stuChooseListener());
			 s2.addActionListener(new stuCourListListener());
			 s3.addActionListener(new stuGradeListener());
			 
		 }
		 class stuChooseListener implements ActionListener{
			 public void actionPerformed (ActionEvent e){
				 jl.remove(mb);
				 main.removeAll();
				 jl.remove(main);
				 new stuChooseView(client,id,main);
	             main.setBounds(20, 10, 1040, 700);
	             main.setLayout(new BorderLayout());
	             jl.add(main,new Integer(200));
	             tl.repaint();
	        }
		}
		 class stuCourListListener implements ActionListener{
			 public void actionPerformed (ActionEvent e){
				 jl.remove(mb);
				 main.removeAll();
				 jl.remove(main);
				 new StuCourListView(client,id,main);
				 main.setBounds(30, 30, 1040, 700);
				 main.setLayout(null);
	             jl.add(main,new Integer(200));
	             tl.repaint();
	       }
		}
		 class stuGradeListener implements ActionListener{
			 public void actionPerformed (ActionEvent e){
				 jl.remove(mb);
				 main.removeAll();
				 jl.remove(main);
				 new StuGradeView(client,id,main);
				 main.setBounds(30, 30, 1040, 700);
				 main.setLayout(null);
	             jl.add(main,new Integer(200));
	             tl.repaint();
	       }	
		}
	 }
	 
	 //商店
	 public class shopActionPerformed implements ActionListener{
			public void actionPerformed(ActionEvent e){
				 mb.removeAll();
				 jl.remove(mb);
				 ImageIcon ic1 = new ImageIcon("images/zengjia.png");
				 JButton s1=new JButton(ic1);
				 s1.setContentAreaFilled(false);
				 s1.setBounds(0, 0, 120, 40);
				 s1.setBorder(null);
				 mb.add(s1);
				 ImageIcon ic2 = new ImageIcon("images/goumai.png");
				 JButton s2=new JButton(ic2);
				 s2.setContentAreaFilled(false);
				 s2.setBounds(0, 50, 120, 40);
				 s2.setBorder(null);
				 mb.add(s2);
				 ImageIcon ic3 = new ImageIcon("images/xiugai.png");
				 JButton s3=new JButton(ic3);
				 s3.setContentAreaFilled(false);
				 s3.setBounds(0, 100, 120, 40);
				 s3.setBorder(null);
				 mb.add(s3);
				 ImageIcon ic4 = new ImageIcon("images/jiaoyi.png");
				 JButton s4=new JButton(ic4);
				 s4.setContentAreaFilled(false);
				 s4.setBounds(0, 150, 120, 40);
				 s4.setBorder(null);
				 mb.add(s4);
					
				 mb.setBounds(0, 180, 130, 200);
				 mb.setLayout(null);
				 jl.add(mb,new Integer(300));
				 tl.repaint();
				 
				 s1.addActionListener(new AddNewGoodsListener());
				 s2.addActionListener(new BuyGoodsViewListener());
				 s3.addActionListener(new ChangeGoodsInfoListener());
				 s4.addActionListener(new OutputHistoryListener());
				 
			 }
			 class AddNewGoodsListener implements ActionListener{
				 public void actionPerformed (ActionEvent e){
					 jl.remove(mb);
					 main.removeAll();
					 jl.remove(main);
					 new AddNewGoods(client,id,main);
		             main.setBounds(20, 10, 1040, 700);
		             main.setLayout(null);
		             jl.add(main,new Integer(200));
		             tl.repaint();
		        }
			}
			 class BuyGoodsViewListener implements ActionListener{
				 public void actionPerformed (ActionEvent e){
					 jl.remove(mb);
					 main.removeAll();
					 jl.remove(main);
					 new BuyGoodsView(client,id,main);
					 main.setBounds(30, 30, 1040, 700);
					 main.setLayout(null);
		             jl.add(main,new Integer(200));
		             tl.repaint();
		       }
			}
			 class ChangeGoodsInfoListener implements ActionListener{
				 public void actionPerformed (ActionEvent e){
					 jl.remove(mb);
					 main.removeAll();
					 jl.remove(main);
					 new ChangeGoodsInfoView(client,id,main);
					 main.setBounds(30, 30, 1040, 700);
					 main.setLayout(null);
		             jl.add(main,new Integer(200));
		             tl.repaint();
		       }
			}
			 class  OutputHistoryListener implements ActionListener{
				 public void actionPerformed (ActionEvent e){
					 jl.remove(mb);
					 main.removeAll();
					 jl.remove(main);
					 new OutputHistoryView(client,id,main);
					 main.setBounds(60, 30, 1040, 700);
					 main.setLayout(null);
		             jl.add(main,new Integer(200));
		             tl.repaint();
		       }
			 }
	 }
	
	 //银行按钮响应
		public class bankActionPerformed implements ActionListener{
			public void actionPerformed(ActionEvent e)
			{
				 mb.removeAll();
				 jl.remove(mb);
				 ImageIcon ic1 = new ImageIcon("images/yue.png");
				 JButton s1=new JButton(ic1);
				 s1.setContentAreaFilled(false);
				 s1.setBounds(0, 0, 120, 40);
				 s1.setBorder(null);
				 mb.add(s1);
				 ImageIcon ic2 = new ImageIcon("images/chongzhi.png");
				 JButton s2=new JButton(ic2);
				 s2.setContentAreaFilled(false);
				 s2.setBounds(0, 50, 120, 40);
				 s2.setBorder(null);
				 mb.add(s2);
				 ImageIcon ic3 = new ImageIcon("images/mima.png");
				 JButton s3=new JButton(ic3);
				 s3.setContentAreaFilled(false);
				 s3.setBounds(0, 100, 120, 40);
				 s3.setBorder(null);
				 mb.add(s3);
					
				 mb.setBounds(0, 260, 120, 150);
				 mb.setLayout(null);
				 jl.add(mb,new Integer(300));
				 tl.repaint();
				 
				 s1.addActionListener(new balanceActionPerformed());
			     s2.addActionListener(new chargeActionPerformed());
				 s3.addActionListener(new changePassActionPerformed());
			}
				 
				 class balanceActionPerformed implements ActionListener{
						public void actionPerformed(ActionEvent e)
						{
							 jl.remove(mb);
							 main.removeAll();
							 jl.remove(main);
							 new BankView(client,id,main).balance();
						     main.setBounds(10, 0, 1040, 700);
				             main.setLayout(null);
				             jl.add(main,new Integer(200));
				             tl.repaint();
						}
					}
				 public class chargeActionPerformed implements ActionListener{
					 public void actionPerformed(ActionEvent e)
						{
							 jl.remove(mb);
							 main.removeAll();
							 jl.remove(main);
							 new BankView(client,id,main).charge();
						     main.setBounds(10, 0, 1040, 700);
				             main.setLayout(null);
				             jl.add(main,new Integer(200));
				             tl.repaint();
						}
				 }
				 public class changePassActionPerformed implements ActionListener{
					 public void actionPerformed(ActionEvent e)
						{
							 jl.remove(mb);
							 main.removeAll();
							 jl.remove(main);
							 new BankView(client,id,main).charge.doClick();
						     main.setBounds(10, 0, 1040, 700);
				             main.setLayout(null);
				             jl.add(main,new Integer(200));
				             tl.repaint();
						}
				 }
				 
		}
	
		//图书馆
		public class LibActionPerformed implements ActionListener{
			public void actionPerformed(ActionEvent e){
				 mb.removeAll();
				 jl.remove(mb);
				 ImageIcon ic1 = new ImageIcon("images/jiehuan.png");
				 JButton s1=new JButton(ic1);
				 s1.setContentAreaFilled(false);
				 s1.setBounds(0, 0, 120, 50);
				 s1.setBorder(null);
				 mb.add(s1);
				 ImageIcon ic2 = new ImageIcon("images/shuji.png");
				 JButton s2=new JButton(ic2);
				 s2.setContentAreaFilled(false);
				 s2.setBounds(0, 50, 120, 50);
				 s2.setBorder(null);
				 mb.add(s2);
				
				 mb.setBounds(0, 370, 120, 100);
				 mb.setLayout(null);
				 jl.add(mb,new Integer(300));
				 tl.repaint();
				 
				 s1.addActionListener(new UserQueryAction());
				 s2.addActionListener(new UserSearchAction());
			}
			public class UserQueryAction implements ActionListener{
				public void actionPerformed(ActionEvent e){
					 jl.remove(mb);
					 main.removeAll();
					 jl.remove(main);
					 try {
						new UserQueryView(client,id,main);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
		             main.setBounds(0, 0, 1040, 700);
		             main.setLayout(null);
		             jl.add(main,new Integer(200));
		             tl.repaint();
		             }
				}
			public class UserSearchAction implements ActionListener{
				public void actionPerformed(ActionEvent e){
					 jl.remove(mb);
					 main.removeAll();
					 jl.remove(main);
					 try {
						new UserSearchView(client,main);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
		             main.setBounds(0, 0, 1040, 700);
		             main.setLayout(null);
		             jl.add(main,new Integer(200));
		             tl.repaint();
		             }
				}
		}
	 //权益服务
	 public class feedbackActionPerformed implements ActionListener{
			public void actionPerformed(ActionEvent e)
			{
				 jl.remove(mb);
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
				 
				 jl.remove(mb);
				 main.removeAll();
				 jl.remove(main);
				 new MailView(client,id,reqData.getRequest(),main);
	             main.setBounds(10, 0, 1040, 700);
	             main.setLayout(null);
	             jl.add(main,new Integer(200));
	             tl.repaint();
			
			}
	 }
	 
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
