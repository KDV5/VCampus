package seu.edu.client.view.Library;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.only.OnlyFrame;

import seu.edu.common.SlidePanel;
import seu.edu.common.SocketClient;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class ManagerView extends OnlyFrame {
	
	private SocketClient socketClient = null;	

	private JPanel contentPane;
	
	private CardLayout card =null;
	
	private JPanel panel=null;
	
	//private SearchBookPanel seachBookPanel=null;
	//private BorrowedBookPanel borrowedBookPanel =null;
	private AddBookPanel addBookPanel=null;
	private HotBookPanel hotBookPanel=null;
	private ManageBookPanel manageBookPanel=null;
	//private FeedBackUserPanel feedBackUserPanel=null;
	
	private SlidePanel sp=null;
	
    public int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    public int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    // 定义窗体的宽高
    public int windowsWedth = 1030;
    public int windowsHeight = 750;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//MainWindow frame = new MainWindow(socketClient);
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ManagerView(SocketClient sc) {
		setResizable(false);	
		
		socketClient=sc;
		
		card=new CardLayout(0, 0);
		
		panel = new JPanel();
		panel.setForeground(SystemColor.control);
		panel.setBackground(SystemColor.control);
		panel.setBounds(216, 110, 814, 640);
		panel.setLayout(card);
		
		//设置窗口居中显示
		setBounds((width - windowsWedth) / 2,(height - windowsHeight) / 2, 1030, 750);
		
		sp=new SlidePanel(panel,card);		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setForeground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(panel);				
		
		//seachBookPanel=new SearchBookPanel(socketClient);
		//borrowedBookPanel =new BorrowedBookPanel(socketClient);
		addBookPanel =new AddBookPanel(socketClient);
		hotBookPanel=new HotBookPanel(socketClient);
		manageBookPanel=new ManageBookPanel(socketClient);
		//feedBackUserPanel=new FeedBackUserPanel(socketClient);
		
		
		//panel.add(seachBookPanel, "SEARCH_BOOK_PANEL");
		//panel.add(borrowedBookPanel, "BORROWED_BOOK_PANEL");
		panel.add(addBookPanel, "ADD_BOOK_PANEL");
		panel.add(hotBookPanel, "HOT_BOOK_PANEL");
		panel.add(manageBookPanel, "MANAGE_BOOK_PANEL");
		//panel.add(feedBackUserPanel, "FEEDBACK_USER_PANEL");
		
		JPanel Leftsidebar = new JPanel();
		Leftsidebar.setBounds(0, 0, 216, 750);
		contentPane.add(Leftsidebar);
		Leftsidebar.setLayout(null);
		
		//JButton searchBookButton = new JButton("");
		//JButton borrowedBookbutton = new JButton("");
		//JButton hotButton = new JButton("");
		//JButton feedBackButton = new JButton("");
		JButton ManageBookButton = new JButton("");
		JButton addBookButton = new JButton("");

		
//		searchBookButton.setBorderPainted(false);
//		searchBookButton.setIcon(new ImageIcon(ManagerView.class.getResource("/Image/Library/UI/图书查询  static.png")));
//		searchBookButton.setRolloverIcon(new ImageIcon(ManagerView.class.getResource("/Image/Library/UI/图书查询  hover.png")));
//		searchBookButton.setPressedIcon(new ImageIcon(ManagerView.class.getResource("/Image/Library/UI/图书查询  press.png")));
//		searchBookButton.setFocusPainted(false);
//		searchBookButton.setBounds(0, 110, 216, 53);
//		Leftsidebar.add(searchBookButton);
//		
//		borrowedBookbutton.setFocusPainted(false);
//		borrowedBookbutton.setBorderPainted(false);
//		borrowedBookbutton.setBounds(0, 163, 216, 53);
//		borrowedBookbutton.setIcon(new ImageIcon(ManagerView.class.getResource("/Image/Library/UI/已借图书 static.png")));
//		borrowedBookbutton.setRolloverIcon(new ImageIcon(ManagerView.class.getResource("/Image/Library/UI/已借图书 hover.png")));
//		borrowedBookbutton.setPressedIcon(new ImageIcon(ManagerView.class.getResource("/Image/Library/UI/已借图书 press.png")));
//		Leftsidebar.add(borrowedBookbutton);
//		
//		hotButton.setIcon(new ImageIcon(ManagerView.class.getResource("/Image/Library/UI/热门推荐 static.png")));
//		hotButton.setRolloverIcon(new ImageIcon(ManagerView.class.getResource("/Image/Library/UI/热门推荐 hover.png")));
//		hotButton.setPressedIcon(new ImageIcon(ManagerView.class.getResource("/Image/Library/UI/热门推荐 press.png")));
//		hotButton.setBounds(0, 216, 216, 53);
//		Leftsidebar.add(hotButton);
//		hotButton.setFocusPainted(false);
//		hotButton.setBorderPainted(false);
//		
//		feedBackButton.setIcon(new ImageIcon(ManagerView.class.getResource("/Image/Library/UI/读者反馈 static.png")));
//		feedBackButton.setRolloverIcon(new ImageIcon(ManagerView.class.getResource("/Image/Library/UI/读者反馈 hover.png")));
//		feedBackButton.setPressedIcon(new ImageIcon(ManagerView.class.getResource("/Image/Library/UI/读者反馈 press.png")));
//		feedBackButton.setFocusPainted(false);
//		feedBackButton.setBorderPainted(false);
//		feedBackButton.setBounds(0, 269, 216, 53);
//		Leftsidebar.add(feedBackButton);
		
		ManageBookButton.setIcon(new ImageIcon(ManagerView.class.getResource("/Image/Library/UI/图书管理 static.png")));
		ManageBookButton.setRolloverIcon(new ImageIcon(ManagerView.class.getResource("/Image/Library/UI/图书管理 hover.png")));
		ManageBookButton.setPressedIcon(new ImageIcon(ManagerView.class.getResource("/Image/Library/UI/图书管理 press.png")));
		ManageBookButton.setFocusPainted(false);
		ManageBookButton.setBorderPainted(false);
		ManageBookButton.setBounds(0, 110, 216, 53);		
		Leftsidebar.add(ManageBookButton);
		
		addBookButton.setIcon(new ImageIcon(ManagerView.class.getResource("/Image/Library/UI/图书入库 static.png")));
		addBookButton.setRolloverIcon(new ImageIcon(ManagerView.class.getResource("/Image/Library/UI/图书入库 hover.png")));
		addBookButton.setPressedIcon(new ImageIcon(ManagerView.class.getResource("/Image/Library/UI/图书入库 press.png")));
		addBookButton.setFocusPainted(false);
		addBookButton.setBorderPainted(false);
		addBookButton.setBounds(0, 163, 216, 53);		
		Leftsidebar.add(addBookButton);
		
//		//查询图书按钮响应函数
//		searchBookButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				sp.slidePanel("SEARCH_BOOK_PANEL", seachBookPanel);
//			}
//		});
//		
//		
//		
//		//借阅按钮响应函数		
//		borrowedBookbutton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				borrowedBookPanel.refresh();
//				card.show(panel, "BORROWED_BOOK_PANEL");
//			}
//		});
//	
//
//		feedBackButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				card.show(panel, "FEEDBACK_USER_PANEL");
//			}
//		});
//		
//		hotButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				card.show(panel,"HOT_BOOK_PANEL");
//			}
//		});
		
		addBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panel,"ADD_BOOK_PANEL");
			}
		});
		
		ManageBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panel,"MANAGE_BOOK_PANEL");
			}
		});
		

				
		JLabel left = new JLabel("");
		left.setIcon(new ImageIcon(ManagerView.class.getResource("/Image/Library/UI/Left bar.png")));
		left.setBounds(0, 0, 216, 750);
		Leftsidebar.add(left);
		
		JPanel Topsidebar = new JPanel();
		Topsidebar.setBounds(216, 0, 814, 110);
		contentPane.add(Topsidebar);
		Topsidebar.setLayout(null);
		
		JLabel Top = new JLabel("");
		Top.setBounds(0, 0, 814, 110);
		Topsidebar.add(Top);
		Top.setIcon(new ImageIcon(ManagerView.class.getResource("/Image/Library/UI/虚拟图书馆manager .png")));
		
		JLabel BackGround = new JLabel("");
		BackGround.setIcon(new ImageIcon(ManagerView.class.getResource("/Image/Library/UI/BackGround.png")));
		BackGround.setBounds(0, 0, 1030, 750);
		contentPane.add(BackGround);
	}
}
