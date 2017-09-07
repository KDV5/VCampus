package seu.edu.client.view.Library;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.only.OnlyFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;

public class MainWindow extends OnlyFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1030, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Top = new JLabel("");
		Top.setIcon(new ImageIcon(MainWindow.class.getResource("/UI/Library/上边栏.psd.png")));
		Top.setBounds(216, 0, 814, 145);
		contentPane.add(Top);
		
		JLabel BackGround = new JLabel("");
		BackGround.setIcon(new ImageIcon(MainWindow.class.getResource("/UI/Library/左边栏.png")));
		BackGround.setBounds(0, 0, 1030, 750);
		contentPane.add(BackGround);
		
		JLabel Left = new JLabel("");
		Left.setBounds(0, 0, 216, 750);
		contentPane.add(Left);
	}
}
