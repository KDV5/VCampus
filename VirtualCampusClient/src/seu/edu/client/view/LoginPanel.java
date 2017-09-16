package seu.edu.client.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.only.OnlyRadioButton;

import seu.edu.client.srv.UserClientSrv;
import seu.edu.common.message.UserMessage;
import seu.edu.vo.User;

public class LoginPanel extends JPanel {
	private JTextField idField;
	private JPasswordField passwordField;
	private JLabel lblNewLabel;
	private MainFrame mainFrame;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private boolean isTeacher;// stu:false, teacher:true
	private String id;
	private String password;
	private String name;

	/**
	 * Create the panel.
	 */
	public LoginPanel(final MainFrame mf) {
		mainFrame=mf;
		
		
		setBackground(Color.BLACK);
		setLayout(null);
		
		idField = new JTextField();
		idField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				id = idField.getText();
				if(id.equals("")){
					idField.setText("学号");
				}
			}
		});
		idField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				idField.setText("");
			}
		});
		idField.setText("学号");
		idField.setHorizontalAlignment(SwingConstants.CENTER);
		idField.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		idField.setBounds(54, 193, 217, 38);
		idField.setOpaque(false);// 不绘制文本框内容
		idField.setBorder(null); // 不绘制文本框边框
		add(idField);
		idField.setColumns(10);
		
		final JLabel lblNewLabel = new JLabel();
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblNewLabel.setText("                  密码");
		lblNewLabel.setBounds(54, 263, 217, 38);
		add(lblNewLabel);
		
		
		passwordField = new JPasswordField();
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				password = passwordField.getText();
				if(password.equals("")){
					lblNewLabel.setText("                  密码");
				}
			}
		});
		passwordField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lblNewLabel.setText("");
			}
		});
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		passwordField.setBounds(54, 262, 217, 38);
		passwordField.setOpaque(false);// 不绘制文本框内容
		passwordField.setBorder(null); // 不绘制文本框边框
		add(passwordField);
		
		//继承自only-feel jar包中的单选按钮
		JRadioButton studentRadioButton = new OnlyRadioButton("\u5B66\u751F");
		studentRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isTeacher = false;
			}
		});
		buttonGroup.add(studentRadioButton);
		studentRadioButton.setBackground(Color.BLUE);
		studentRadioButton.setOpaque(false);// 不显示底纹
		studentRadioButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		studentRadioButton.setBounds(93, 328, 59, 27);
		add(studentRadioButton);
		
		JRadioButton teacherButton = new OnlyRadioButton("\u8001\u5E08");
		teacherButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isTeacher = true;
			}
		}); 
		teacherButton.setBackground(Color.BLUE);
		buttonGroup.add(teacherButton);
		teacherButton.setOpaque(false);
		teacherButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		teacherButton.setBounds(193, 328, 59, 27);
		add(teacherButton);
		
		JButton changePasswButton = new JButton("");
		changePasswButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainFrame.toChangePassPanel();
			}
		});
		changePasswButton.setIcon(new ImageIcon(LoginPanel.class.getResource("/image/Login/修改密码 static.png")));
		changePasswButton.setBounds(197, 301, 74, 27);
		add(changePasswButton);
		changePasswButton.setContentAreaFilled(false); // 不绘制按钮底纹
		changePasswButton.setFocusPainted(false);	// 不绘制焦点
		changePasswButton.setBorderPainted(false);	// 不绘制按钮边框
		changePasswButton.setRolloverIcon(new ImageIcon(LoginPanel.class.getResource("/image/Login/修改密码 hover.png")));
		changePasswButton.setPressedIcon(new ImageIcon(LoginPanel.class.getResource("/image/Login/修改密码 static.png")));
		
		JButton loginButton = new JButton("");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i, j;
				String str = null;
				id = idField.getText();
				password = passwordField.getText();
				if(isTeacher){
					i = 1;
				} else{
					i = 0;
				}
				if(password.equals("") || id.equals("") || id.equals("学号")){
					JOptionPane.showMessageDialog(null, "用户名或密码不能为空！", null, JOptionPane.WARNING_MESSAGE);
				} else{
					UserClientSrv ucs = new UserClientSrv();
					UserMessage usmg = ucs.login(id, password, i);		// 根据返回值显示提示语句
					j = usmg.getMatchOrNot();
					mf.setName(usmg.getUserName());
					if(j == 2){
						mf.setUser(new User(id, mf.getName(), password, i));
						passwordField.setText("");
						mf.setVisible(false);
						HomeView hv = new HomeView(mf);
						hv.setVisible(true);
					}
					switch(j){
					case 0:{str = "不存在该用户！";JOptionPane.showMessageDialog(null, str, null, JOptionPane.WARNING_MESSAGE);break;}
					case 1:{str = "密码错误！";JOptionPane.showMessageDialog(null, str, null, JOptionPane.WARNING_MESSAGE);break;}
					case 2:{break;}
					case 3:{str = "身份信息不对！";JOptionPane.showMessageDialog(null, str, null, JOptionPane.WARNING_MESSAGE);break;}
					default:str = "登陆失败！";JOptionPane.showMessageDialog(null, str, null, JOptionPane.WARNING_MESSAGE);
					}
					
					
				}
			}
		});
		loginButton.setIcon(new ImageIcon(LoginPanel.class.getResource("/image/Login/登   录 static.png")));
		loginButton.setBounds(73, 368, 179, 45);
		loginButton.setContentAreaFilled(false); // 不绘制按钮底纹
		loginButton.setFocusPainted(false);	// 不绘制焦点
		loginButton.setBorderPainted(false);	// 不绘制按钮边框
		loginButton.setRolloverIcon(new ImageIcon(LoginPanel.class.getResource("/image/Login/\u767B   \u5F55 hover.png")));
		loginButton.setPressedIcon(new ImageIcon(LoginPanel.class.getResource("/image/Login/\u767B   \u5F55 press.png")));
		add(loginButton);
		
		JButton turnToRegisterButton = new JButton("");
		turnToRegisterButton.setSelectedIcon(new ImageIcon(LoginPanel.class.getResource("/image/Login/\u8F6C\u5230\u6CE8\u518C.png")));
		turnToRegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.toRegisterPanel();
			}
		});
		turnToRegisterButton.setIcon(new ImageIcon(LoginPanel.class.getResource("/image/Login/\u8F6C\u5230\u6CE8\u518C.png")));
		turnToRegisterButton.setBounds(274, 381, 19, 20);		
		turnToRegisterButton.setContentAreaFilled(false); // 不绘制按钮底纹
		turnToRegisterButton.setFocusPainted(false);	// 不绘制焦点
		turnToRegisterButton.setBorderPainted(false);	// 不绘制按钮边框
		turnToRegisterButton.setRolloverIcon(new ImageIcon(LoginPanel.class.getResource("/image/Login/\u8F6C\u5230\u6CE8\u518C.png")));
		turnToRegisterButton.setPressedIcon(new ImageIcon(LoginPanel.class.getResource("/image/Login/\u8F6C\u5230\u6CE8\u518C.png")));
		add(turnToRegisterButton);
		
		JLabel backGround = new JLabel("New label");
		backGround.setIcon(new ImageIcon(LoginPanel.class.getResource("/image/Login/L b.png")));
		backGround.setBounds(0, 0, 323, 439);
		
		add(backGround);

	}

	public boolean isTeacher() {
		return isTeacher;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
}