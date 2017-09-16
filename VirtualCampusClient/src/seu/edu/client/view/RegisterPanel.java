package seu.edu.client.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import seu.edu.client.srv.UserClientSrv;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisterPanel extends JPanel {

	private MainFrame mainFrame;
	private JTextField idField;
	private JPasswordField oldPasswordField;
	private JPasswordField newPasswordField;
	private String id;
	private String pw1;
	private String pw2;
	private JLabel idLabel;
	private JLabel pwLabel1;
	private JLabel pwLabel2;
	/**
	 * Create the panel.
	 */
	public RegisterPanel(MainFrame mf) {
		mainFrame=mf;
		setLayout(null);
		
		JButton tobtnNewButton = new JButton("");
		tobtnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainFrame.rToLoginPanel();
			}
		});
		tobtnNewButton.setIcon(new ImageIcon(RegisterPanel.class.getResource("/image/Login/转到登陆.png")));
		tobtnNewButton.setFocusPainted(false);
		tobtnNewButton.setContentAreaFilled(false);
		tobtnNewButton.setBorderPainted(false);
		tobtnNewButton.setBounds(32, 381, 19, 20);
		add(tobtnNewButton);
		
//		idLabel = new JLabel(" 学号");
//		idLabel.setFont(new Font("微软雅黑", Font.PLAIN, 17));
//		idLabel.setBounds(139, 182, 54, 38);
//		add(idLabel);
		
		pwLabel1 = new JLabel(" 密码");
		pwLabel1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		pwLabel1.setBounds(139, 239, 54, 38);
		add(pwLabel1);
		
		pwLabel2 = new JLabel("请再次输入密码");
		pwLabel2.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		pwLabel2.setBounds(101, 300, 150, 27);
		add(pwLabel2);
		
		idField = new JTextField();
		idField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				id = idField.getText();
				if(id.equals("")){
					idField.setText("学号");
				}
			}
		});
		idField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				idField.setText("");
			}
		});
		idField.setText("学号");
		idField.setHorizontalAlignment(SwingConstants.CENTER);
		idField.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		idField.setBounds(53, 182, 217, 38);
		idField.setOpaque(false);// 不绘制文本框内容
		idField.setBorder(null); // 不绘制文本框边框
		add(idField);
		idField.setColumns(10);
		
		oldPasswordField = new JPasswordField();
		oldPasswordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				pw1 = oldPasswordField.getText();
				if(pw1.equals("")){
					pwLabel1.setText(" 密码");
				}else {
					pwLabel1.setText("");
				}
			}
		});
		oldPasswordField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pwLabel1.setText("");
			}
		});
		oldPasswordField.setHorizontalAlignment(SwingConstants.CENTER);
		oldPasswordField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		oldPasswordField.setBounds(53, 240, 217, 38);
		oldPasswordField.setOpaque(false);// 不绘制文本框内容
		oldPasswordField.setBorder(null); // 不绘制文本框边框
		add(oldPasswordField);
		
		newPasswordField = new JPasswordField();
		newPasswordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				pw2 = newPasswordField.getText();
				if(pw2.equals("")){
					pwLabel2.setText("请再次输入密码");
				}else {
					pwLabel2.setText("");
				}
			}
		});
		newPasswordField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pwLabel2.setText("");
			}
		});
		newPasswordField.setHorizontalAlignment(SwingConstants.CENTER);
		newPasswordField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		newPasswordField.setBounds(53, 296, 217, 37);
		newPasswordField.setOpaque(false);// 不绘制文本框内容
		newPasswordField.setBorder(null); // 不绘制文本框边框
		add(newPasswordField);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				id = idField.getText();
				pw1 = oldPasswordField.getText();
				pw2 = newPasswordField.getText();
				if(id.equals("") || pw1.equals("") || pw2.equals("") || id.equals("学号")){
					JOptionPane.showMessageDialog(null, "学号或密码为空！", null, JOptionPane.WARNING_MESSAGE);
				}else {
					UserClientSrv ucs = new UserClientSrv();
					int j = ucs.register(id, pw1, pw2, 0);	// 两次密码不一致返回-1，已有该用户返回0，成功返回1，学籍中不存在返回2
					String str = null;
					switch(j){
					case -1:{str = "两次密码不一致！";break;}
					case 0:{str = "该用户已注册！";break;}
					case 1:{str = "注册成功！";break;}
					case 2:{str = "学籍中不存在该学生！";break;}
					default:str = "注册失败！";
					}
					JOptionPane.showMessageDialog(null, str, null, JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(RegisterPanel.class.getResource("/image/Login/注   册 static.png")));
		btnNewButton.setBounds(72, 368, 179, 45);
		btnNewButton.setContentAreaFilled(false); // 不绘制按钮底纹
		btnNewButton.setFocusPainted(false);	// 不绘制焦点
		btnNewButton.setBorderPainted(false);	// 不绘制按钮边框
		btnNewButton.setRolloverIcon(new ImageIcon(RegisterPanel.class.getResource("/image/Login/注   册 hover.png")));
		btnNewButton.setPressedIcon(new ImageIcon(RegisterPanel.class.getResource("/image/Login/注   册 press.png")));
		add(btnNewButton);
		
		JLabel background = new JLabel("");
		background.setBounds(0, 0, 323, 439);
		background.setIcon(new ImageIcon(RegisterPanel.class.getResource("/image/Login/R b.png")));
		add(background);

	}
}
