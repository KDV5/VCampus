package seu.edu.client.view;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.only.OnlyBorderFrame;
import com.sun.awt.AWTUtilities;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HomeView extends OnlyBorderFrame {

	private JPanel contentPane;
	
    public int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    public int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    // ���崰��Ŀ��
    public int windowsWedth = 1030;
    public int windowsHeight = 614;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeView frame = new HomeView(null);
					
					JFrame.setDefaultLookAndFeelDecorated(true);  
					AWTUtilities.setWindowShape(frame,  
				            new RoundRectangle2D.Double(0.0D, 0.0D, frame.getWidth(),  
				                frame.getHeight(), 15.0D, 15.0D));  
					
					frame.setVisible(true);		
					frame.repaint();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HomeView(final MainFrame mf) {
		setResizable(false);	

		//���ô��ھ�����ʾ
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds((width - windowsWedth) / 2,(height - windowsHeight) / 2, 1030, 614);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton stu_Button = new JButton("");
		stu_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		stu_Button.setBorderPainted(false);
		stu_Button.setIcon(new ImageIcon(HomeView.class.getResource("/image/home/Stu_b.png")));
		stu_Button.setRolloverIcon(new ImageIcon(HomeView.class.getResource("/image/home/Stu_c.png")));
		stu_Button.setPressedIcon(new ImageIcon(HomeView.class.getResource("/image/home/Stu_P.png")));
		
		stu_Button.setContentAreaFilled(false); // 不绘制按钮底纹
		stu_Button.setFocusPainted(false);	// 不绘制焦点
		stu_Button.setBorderPainted(false);	// 不绘制按钮边框
		
		stu_Button.setBounds(0, 437, 206, 177);
		contentPane.add(stu_Button);
		
		JButton class_Button = new JButton("");
		class_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		class_Button.setBorderPainted(false);
		class_Button.setIcon(new ImageIcon(HomeView.class.getResource("/image/Home/Class_b.png")));
		
		class_Button.setContentAreaFilled(false); // 不绘制按钮底纹
		class_Button.setFocusPainted(false);	// 不绘制焦点
		class_Button.setBorderPainted(false);	// 不绘制按钮边框
		
		class_Button.setRolloverIcon(new ImageIcon(HomeView.class.getResource("/image/home/Class_c.png")));
		class_Button.setPressedIcon(new ImageIcon(HomeView.class.getResource("/image/home/Class_p.png")));
		class_Button.setBounds(206, 437, 206, 177);
		contentPane.add(class_Button);
		
		JButton lib_Button = new JButton("");
		lib_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		lib_Button.setBorderPainted(false);
		lib_Button.setIcon(new ImageIcon(HomeView.class.getResource("/image/home/Lib_b.png")));
		lib_Button.setRolloverIcon(new ImageIcon(HomeView.class.getResource("/image/home/Lib_c.png")));
		lib_Button.setPressedIcon(new ImageIcon(HomeView.class.getResource("/image/home/Lib_p.png")));
		
		lib_Button.setContentAreaFilled(false); // 不绘制按钮底纹
		lib_Button.setFocusPainted(false);	// 不绘制焦点
		lib_Button.setBorderPainted(false);	// 不绘制按钮边框
		
		lib_Button.setBounds(412, 437, 206, 177);
		contentPane.add(lib_Button);
		
		JButton bank_Button = new JButton("");
		bank_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		bank_Button.setBorderPainted(false);
		bank_Button.setIcon(new ImageIcon(HomeView.class.getResource("/image/home/bank_b.png")));
		bank_Button.setRolloverIcon(new ImageIcon(HomeView.class.getResource("/image/home/Bank_c.png")));
		bank_Button.setPressedIcon(new ImageIcon(HomeView.class.getResource("/image/home/Bank_p.png")));
		
		bank_Button.setContentAreaFilled(false); // 不绘制按钮底纹
		bank_Button.setFocusPainted(false);	// 不绘制焦点
		bank_Button.setBorderPainted(false);	// 不绘制按钮边框
		
		bank_Button.setBounds(618, 437, 206, 177);
		contentPane.add(bank_Button);
		
		JButton shop_Button = new JButton("");
		shop_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		shop_Button.setBorderPainted(false);
		shop_Button.setIcon(new ImageIcon(HomeView.class.getResource("/image/home/shop_b.png")));
		shop_Button.setRolloverIcon(new ImageIcon(HomeView.class.getResource("/image/home/shop_c.png")));
		shop_Button.setPressedIcon(new ImageIcon(HomeView.class.getResource("/image/home/shop_p.png")));
		
		shop_Button.setContentAreaFilled(false); // 不绘制按钮底纹
		shop_Button.setFocusPainted(false);	// 不绘制焦点
		shop_Button.setBorderPainted(false);	// 不绘制按钮边框
		
		shop_Button.setBounds(824, 437, 206, 177);
		contentPane.add(shop_Button);
		
		JLabel logoutLabel = new JLabel("退出登录");
		logoutLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//dispose();
				setVisible(false);
				mf.setVisible(true);
			}
		});
		logoutLabel.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		logoutLabel.setBounds(968, 28, 52, 31);
		contentPane.add(logoutLabel);
		
		JLabel welLabel = new JLabel();
		welLabel.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		welLabel.setBounds(856, 28, 102, 31);
		welLabel.setText("欢迎您：" + mf.getName());
		contentPane.add(welLabel);
		
		JLabel BackGround = new JLabel("New label");
		BackGround.setIcon(new ImageIcon(HomeView.class.getResource("/image/home/BackGround.png")));
		BackGround.setBounds(0, 0, 1030, 614);
		contentPane.add(BackGround);

	}
}
