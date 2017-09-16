package seu.edu.client.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Label;

import javax.swing.SwingConstants;

import seu.edu.client.srv.UserClientSrv;

import javax.swing.JPasswordField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChangePassPanel extends JPanel {
	
	private MainFrame mainFrame;
	private JTextField idField;
	private JPasswordField oldPasswordField;
	private JPasswordField newPasswordField;
	private String id;
	private String newPassword;
	private String oldPassword;
	private JLabel oldLabel;
	private JLabel label;
	/**
	 * Create the panel.
	 */
	public ChangePassPanel(MainFrame mf) {
		
		mainFrame=mf;
		setLayout(null);
		
		JButton toLoginbutton = new JButton("");
		toLoginbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainFrame.cToLoginPanel();
			}
		});
		toLoginbutton.setIcon(new ImageIcon(RegisterPanel.class.getResource("/image/Login/转到登陆.png")));
		toLoginbutton.setFocusPainted(false);
		toLoginbutton.setContentAreaFilled(false);
		toLoginbutton.setBorderPainted(false);
		toLoginbutton.setBounds(32, 381, 19, 20);
		add(toLoginbutton);
		
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
		
		oldLabel = new JLabel("");
		oldLabel.setText("旧密码");
		oldLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		oldLabel.setBounds(134, 240, 54, 38);
		add(oldLabel);
		
		label = new JLabel("");
		label.setText("新密码");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label.setBounds(134, 296, 54, 37);
		add(label);
		
		oldPasswordField = new JPasswordField();
		oldPasswordField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				oldLabel.setText("");
			}
		});
		oldPasswordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				oldPassword = oldPasswordField.getText();
				if(oldPassword.equals("")){
					oldLabel.setText("旧密码");
				}
			}
		});
		oldPasswordField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		oldPasswordField.setHorizontalAlignment(SwingConstants.CENTER);
		oldPasswordField.setBounds(53, 240, 217, 38);
		oldPasswordField.setOpaque(false);// 不绘制文本框内容
		oldPasswordField.setBorder(null); // 不绘制文本框边框
		add(oldPasswordField);
		
		newPasswordField = new JPasswordField();
		newPasswordField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				label.setText("");
			}
		});
		newPasswordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				newPassword = newPasswordField.getText();
				if(newPassword.equals("")){
					label.setText("新密码");
				}
			}
		});
		newPasswordField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		newPasswordField.setHorizontalAlignment(SwingConstants.CENTER);
		newPasswordField.setBounds(53, 296, 217, 37);
		newPasswordField.setOpaque(false);// 不绘制文本框内容
		newPasswordField.setBorder(null); // 不绘制文本框边框
		add(newPasswordField);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				id = idField.getText();
				newPassword = newPasswordField.getText();
				oldPassword = oldPasswordField.getText();
				if(id.equals("") || newPassword.equals("") || oldPassword.equals("") || id.equals("学号")){
					JOptionPane.showMessageDialog(null, "学号或密码为空", null, JOptionPane.WARNING_MESSAGE);
				}else {
					UserClientSrv ucs = new UserClientSrv();
					int j = ucs.resetPassword(id, oldPassword, newPassword);
					String str = null;
					switch(j){
					case 0:{str = "原密码错误！";break;}
					case 1:{str = "修改成功！";break;}
					}
					JOptionPane.showMessageDialog(null, str, null, JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		button.setIcon(new ImageIcon(ChangePassPanel.class.getResource("/image/Login/改密 static.png")));
		button.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		button.setBounds(72, 368, 179, 45);
		button.setContentAreaFilled(false); // 不绘制按钮底纹
		button.setFocusPainted(false);	// 不绘制焦点
		button.setBorderPainted(false);	// 不绘制按钮边框
		button.setRolloverIcon(new ImageIcon(ChangePassPanel.class.getResource("/image/Login/改密 hover.png")));
		add(button);
		
		JLabel background = new JLabel("");
		background.setBounds(0, 0, 323, 439);
		background.setIcon(new ImageIcon(ChangePassPanel.class.getResource("/image/Login/C b.png")));
		add(background);

	}
}
