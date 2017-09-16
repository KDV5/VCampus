package seu.edu.client.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ShopRun extends JFrame
{	
	private JPanel contentPane;	
	JLabel Lab_bkg= new JLabel("");
	int count=1;					//bkg计数器
	JButton btn_back = new JButton("\u2190");
	private JTextField t_str;
	
	/**
	 * Create the frame.
	 */
	
	public ShopRun()
	{
		setTitle("Welcome to the Shop!");
		setResizable(false);
		try
		{
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 793, 709);
			contentPane = new JPanel();
			contentPane.setForeground(Color.WHITE);
			contentPane.setBackground(Color.WHITE);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			setLocationRelativeTo(null);
			
			JButton btn_mes = new JButton("");
			btn_mes.setIcon(new ImageIcon(ShopRun.class.getResource("/img/mes.png")));
			btn_mes.setBounds(720, 410, 60, 60);
			btn_mes.setBorderPainted(false);
			//btn1.setContentAreaFilled(false);
			contentPane.add(btn_mes);
			
			JLabel lbl_mes = new JLabel("\u6D88\u606F");
			lbl_mes.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_mes.setBounds(715, 463, 73, 28);
			contentPane.add(lbl_mes);
			
			JButton btn_car = new JButton("");
			btn_car.setIcon(new ImageIcon(ShopRun.class.getResource("/img/car.png")));
			btn_car.setBounds(722, 585, 60, 60);
			btn_car.setBorderPainted(false);
			//button.setContentAreaFilled(false);
			contentPane.add(btn_car);
			
			JButton btn_sell = new JButton("");
			btn_sell.setIcon(new ImageIcon(ShopRun.class.getResource("/img/sell.png")));
			btn_sell.setBounds(721, 497, 60, 60);
			btn_sell.setBorderPainted(false);
			//button_1.setContentAreaFilled(false);
			contentPane.add(btn_sell);
			
			JLabel lbl_sell = new JLabel("\u5356\u5B9D\u8D1D");
			lbl_sell.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_sell.setBounds(715, 549, 73, 28);
			contentPane.add(lbl_sell);
			
			JLabel lbl_car = new JLabel("\u6211\u7684\u5B9D\u8D1D");
			lbl_car.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_car.setBounds(715, 638, 73, 28);
			contentPane.add(lbl_car);			
			
			btn_back.setForeground(Color.WHITE);
			btn_back.setFont(new Font("宋体", Font.PLAIN, 15));
			btn_back.setBounds(0, 0, 60, 45);
			btn_back.setFocusPainted(false);		//去掉Button的文字边框
			//btnNewButton.setBorderPainted(false);
			btn_back.setContentAreaFilled(false);
			contentPane.add(btn_back);
			
			JButton btn_left = new JButton("<");
			btn_left.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					count--;
					if(count<1)
						count=3;
					if(count==2)
						btn_back.setForeground(Color.black);
					else
						btn_back.setForeground(Color.WHITE);
					
					Lab_bkg.setIcon(new ImageIcon(ShopRun.class.getResource("/img/bkg"+count+".png")));
				}
			});
			btn_left.setForeground(Color.WHITE);
			btn_left.setFont(new Font("宋体", Font.PLAIN, 15));
			btn_left.setFocusPainted(false);
			//button_3.setBorderPainted(false);
			btn_left.setContentAreaFilled(false);
			btn_left.setBounds(122, 353, 42, 40);
			contentPane.add(btn_left);
			
			JButton btn_right = new JButton(">");
			btn_right.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					count++;
					if(count>3)
						count=1;
					if(count==2)
						btn_back.setForeground(Color.black);
					else
						btn_back.setForeground(Color.WHITE);
					
					Lab_bkg.setIcon(new ImageIcon(ShopRun.class.getResource("/img/bkg"+count+".png")));
				}
			});
			btn_right.setForeground(Color.WHITE);
			btn_right.setFont(new Font("宋体", Font.PLAIN, 15));
			btn_right.setFocusPainted(false);
			btn_right.setContentAreaFilled(false);
			//button_4.setBorderPainted(false);
			btn_right.setBounds(647, 353, 42, 40);
			contentPane.add(btn_right);			
			
			Lab_bkg.setIcon(new ImageIcon(ShopRun.class.getResource("/img/bkg1.png")));
			Lab_bkg.setBounds(0, -3, 788, 396);
			contentPane.add(Lab_bkg);
			
			JButton btn_good1 = new JButton("");
			btn_good1.setIcon(new ImageIcon(ShopRun.class.getResource("/img/good1.jpg")));
			btn_good1.setBounds(1, 394, 226, 140);
			contentPane.add(btn_good1);
			
			JButton btn_good3 = new JButton("");
			btn_good3.setIcon(new ImageIcon(ShopRun.class.getResource("/img/good3.jpg")));
			btn_good3.setBounds(0, 537, 153, 140);
			contentPane.add(btn_good3);
			
			JButton btn_good2 = new JButton("");
			btn_good2.setIcon(new ImageIcon(ShopRun.class.getResource("/img/good2.jpg")));
			btn_good2.setBounds(231, 394, 155, 140);
			contentPane.add(btn_good2);
			
			JButton btn_good4 = new JButton("");
			btn_good4.setIcon(new ImageIcon(ShopRun.class.getResource("/img/good4.jpg")));
			btn_good4.setBounds(155, 537, 233, 140);
			contentPane.add(btn_good4);
			
			JButton btn_good6 = new JButton("");
			btn_good6.setIcon(new ImageIcon(ShopRun.class.getResource("/img/good6.jpg")));
			btn_good6.setBounds(552, 472, 160, 205);
			contentPane.add(btn_good6);
			
			JButton btn_good5 = new JButton("");
			btn_good5.setIcon(new ImageIcon(ShopRun.class.getResource("/img/good5.jpg")));
			btn_good5.setBounds(389, 472, 160, 205);
			contentPane.add(btn_good5);
			
			JLabel lbl_paint = new JLabel("");
			lbl_paint.setIcon(new ImageIcon(ShopRun.class.getResource("/img/paint.jpg")));
			lbl_paint.setBounds(713, 394, 75, 287);
			contentPane.add(lbl_paint);
			
			t_str = new JTextField();
			t_str.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e)
				{
					if (e.getKeyChar() == KeyEvent.VK_ENTER)
						SearchFrame.getInstance(t_str.getText());
				}
			});
			t_str.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) 
				{
					t_str.setText("");
				}
			});
			t_str.setFont(new Font("幼圆", Font.PLAIN, 16));
			t_str.setHorizontalAlignment(SwingConstants.CENTER);
			t_str.setText("what do you want?");
			t_str.setBounds(406, 410, 235, 45);
			contentPane.add(t_str);
			t_str.setColumns(10);
			
			JButton btn_search = new JButton("");
			btn_search.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					SearchFrame.getInstance(t_str.getText());
				}
			});
			btn_search.setIcon(new ImageIcon(ShopRun.class.getResource("/img/search.png")));
			btn_search.setBounds(641, 410, 48, 45);
			btn_search.setContentAreaFilled(false);
			btn_search.setFocusPainted(false);			
			contentPane.add(btn_search);
			this.setVisible(true);
			Thread_bkg.start();
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private Thread Thread_bkg = new Thread() 		// 线程对象，定时更换bkg
	{
		public void run()
		{
			final long timeInterval = 4000;			//4s换一次bkg
			while (true)
			{
				try
				{
					Thread.sleep(timeInterval);
					count++;
					if (count > 3)
						count = 1;
					if (count == 2)
						btn_back.setForeground(Color.black);
					else
						btn_back.setForeground(Color.WHITE);
					Lab_bkg.setIcon(new ImageIcon(ShopRun.class.getResource("/img/bkg" + count + ".png")));
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
	};
}
