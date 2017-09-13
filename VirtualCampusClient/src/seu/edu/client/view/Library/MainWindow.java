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

public class MainWindow extends OnlyFrame {
	
	private SocketClient socketClient = null;	

	private JPanel contentPane;
	
	private CardLayout card =null;
	
	private JPanel panel=null;
	
	private SearchBookPanel seachBookPanel=null;
	private BorrowedBookPanel borrowedBookPanel =null;
	
	private SlidePanel sp=null;

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
	public MainWindow(SocketClient sc) {
		
		socketClient=sc;
		
		card=new CardLayout(0, 0);
		
		panel = new JPanel();
		panel.setForeground(SystemColor.control);
		panel.setBackground(SystemColor.control);
		panel.setBounds(216, 110, 814, 640);
		panel.setLayout(card);
		
		sp=new SlidePanel(panel,card);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1030, 750);
		contentPane = new JPanel();
		contentPane.setForeground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(panel);				
		
		seachBookPanel=new SearchBookPanel(socketClient);
		borrowedBookPanel =new BorrowedBookPanel(socketClient);
		
		panel.add(seachBookPanel, "SEARCH_BOOK_PANEL");
		panel.add(borrowedBookPanel, "BORROWED_BOOK_PANEL");
		
		JPanel Leftsidebar = new JPanel();
		Leftsidebar.setBounds(0, 0, 216, 750);
		contentPane.add(Leftsidebar);
		Leftsidebar.setLayout(null);
		
		//查询图书按钮响应函数
		JButton searchBookButton = new JButton("");
		searchBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sp.slidePanel("SEARCH_BOOK_PANEL", seachBookPanel);
			}
		});
		searchBookButton.setBorderPainted(false);
		searchBookButton.setIcon(new ImageIcon(MainWindow.class.getResource("/UI/Library/图书查询  static.png")));
		searchBookButton.setRolloverIcon(new ImageIcon(MainWindow.class.getResource("/UI/Library/图书查询  hover.png")));
		searchBookButton.setPressedIcon(new ImageIcon(MainWindow.class.getResource("/UI/Library/图书查询  press.png")));
		searchBookButton.setFocusPainted(false);
		searchBookButton.setBounds(0, 110, 216, 53);
		
		Leftsidebar.add(searchBookButton);
		
		//借阅按钮响应函数
		JButton borrowedBookbutton = new JButton("");
		borrowedBookbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panel, "BORROWED_BOOK_PANEL");
			}
		});
		borrowedBookbutton.setFocusPainted(false);
		borrowedBookbutton.setBorderPainted(false);
		borrowedBookbutton.setBounds(0, 163, 216, 53);
		borrowedBookbutton.setIcon(new ImageIcon(MainWindow.class.getResource("/UI/Library/已借图书 static.png")));
		borrowedBookbutton.setRolloverIcon(new ImageIcon(MainWindow.class.getResource("/UI/Library/已借图书 hover.png")));
		borrowedBookbutton.setPressedIcon(new ImageIcon(MainWindow.class.getResource("/UI/Library/已借图书 press.png")));
		Leftsidebar.add(borrowedBookbutton);
		
		JButton hotButton = new JButton("");
		hotButton.setIcon(new ImageIcon(MainWindow.class.getResource("/UI/Library/热门推荐 static.png")));
		hotButton.setRolloverIcon(new ImageIcon(MainWindow.class.getResource("/UI/Library/热门推荐 hover.png")));
		hotButton.setPressedIcon(new ImageIcon(MainWindow.class.getResource("/UI/Library/热门推荐 press.png")));
		hotButton.setBounds(0, 216, 216, 53);
		Leftsidebar.add(hotButton);
		hotButton.setFocusPainted(false);
		hotButton.setBorderPainted(false);
		
		JButton feedBackButton = new JButton("");
		feedBackButton.setIcon(new ImageIcon(MainWindow.class.getResource("/UI/Library/读者反馈 static.png")));
		feedBackButton.setRolloverIcon(new ImageIcon(MainWindow.class.getResource("/UI/Library/读者反馈 hover.png")));
		feedBackButton.setPressedIcon(new ImageIcon(MainWindow.class.getResource("/UI/Library/读者反馈 press.png")));
		feedBackButton.setFocusPainted(false);
		feedBackButton.setBorderPainted(false);
		feedBackButton.setBounds(0, 269, 216, 53);
		Leftsidebar.add(feedBackButton);
		
		JLabel left = new JLabel("");
		left.setIcon(new ImageIcon(MainWindow.class.getResource("/UI/Library/左边栏.png")));
		left.setBounds(0, 0, 216, 750);
		Leftsidebar.add(left);
		
		JPanel Topsidebar = new JPanel();
		Topsidebar.setBounds(216, 0, 814, 110);
		contentPane.add(Topsidebar);
		Topsidebar.setLayout(null);
		
		JLabel Top = new JLabel("");
		Top.setBounds(0, 0, 814, 110);
		Topsidebar.add(Top);
		Top.setIcon(new ImageIcon(MainWindow.class.getResource("/UI/Library/上边栏.psd.png")));
		
		JLabel BackGround = new JLabel("");
		BackGround.setIcon(new ImageIcon(MainWindow.class.getResource("/UI/Library/BackGround.png")));
		BackGround.setBounds(0, 0, 1030, 750);
		contentPane.add(BackGround);
	}
}
