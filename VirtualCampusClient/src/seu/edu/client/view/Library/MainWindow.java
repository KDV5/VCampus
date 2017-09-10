package seu.edu.client.view.Library;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.only.OnlyFrame;

import seu.edu.common.SocketClient;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JButton;

public class MainWindow extends OnlyFrame {
	
	private SocketClient socketClient = null;	

	private JPanel contentPane;

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
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1030, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
				
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(216, 110, 814, 640);
		contentPane.add(panel);
		panel.setLayout(null);
		
		SearchBookPanel seachBookPanel=new SearchBookPanel(socketClient);
		seachBookPanel.setBounds(0, 0, 814, 640);
		panel.add(seachBookPanel);
		//SearchBookPanel.set
		
		JPanel Leftsidebar = new JPanel();
		Leftsidebar.setBounds(0, 0, 216, 750);
		contentPane.add(Leftsidebar);
		Leftsidebar.setLayout(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBorderPainted(false);
		btnNewButton.setIcon(new ImageIcon(MainWindow.class.getResource("/UI/Library/hover.png")));
		btnNewButton.setBounds(0, 110, 216, 53);
		
		Leftsidebar.add(btnNewButton);
		
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
