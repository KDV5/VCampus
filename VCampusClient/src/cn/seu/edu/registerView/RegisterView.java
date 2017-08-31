package cn.seu.edu.registerView;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import cn.seu.edu.Client.Client;
import cn.seu.edu.message.LoginData;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.register.Register;

import javax.swing.JComponent; 

//import cn.seu.edu.Client.Client
//import cn.seu.edu.message.LoginData
//import cn.seu.edu.message.RequestData

public class RegisterView extends JFrame{
	JFrame frame = new JFrame("�û�ע��");
	public JLabel nameLab = new JLabel("�û���");
	public JLabel passLab1 = new JLabel("����");
	public JLabel passLab2 = new JLabel("ȷ������");
	public JTextField nameText = new JTextField(15);;
	public JPasswordField pwdText1 = new JPasswordField(15);
	public JPasswordField pwdText2 = new JPasswordField(15);
	public JButton registerButton = new JButton("ȷ��ע��");
	String uID = null;
	String uPassword = null;
	
	Client client;
	public RegisterView(Client client1){
		
		this.client = client1;

		registerButton.addActionListener(new registerActionPerformed());
					
		nameLab.setBounds(38,19,129,30);
		nameText.setBounds(167, 19, 129, 30);
		passLab1.setBounds(38,64,129,30);		
		pwdText1.setBounds(167, 64, 129, 30);
		passLab2.setBounds(38,109,129,30);		
		pwdText2.setBounds(167, 109, 129, 30);
		
	
		registerButton.setBounds(116, 161, 102, 30);
		
		
	    setLayout(null);
		add(nameLab);
		add(nameText);
		add(passLab1);
		add(pwdText1);
		add(passLab2);
		add(pwdText2);

		add(registerButton);
		
		String picPath ="images/С��.png";
		Icon icon =new ImageIcon(picPath);
		JLabel lab =null;
		lab= new JLabel(icon,JLabel.CENTER);
		add(lab);
		lab.setSize(34,53);
		lab.setLocation(276,145);
		lab.setVisible(true);

	    setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUndecorated(true);
		setFocusableWindowState(true);
		JRootPane rp =getRootPane();
		rp.setWindowDecorationStyle(JRootPane.FRAME);
		pack();
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				client.sendRequestToServer(new RequestData("exit"));
				System.exit(0);	
			}
		});

	}
	
	public class registerActionPerformed implements ActionListener{
		public void actionPerformed(ActionEvent e){
			new RegisterView(client);
			if(pwdText1.getText().trim().equals(pwdText2.getText().trim())){
				uID = nameText.getText();
				uPassword = pwdText1.getText();
				client.sendRequestToServer(new LoginData("register", uID,uPassword));
				RequestData reqData = (RequestData)client.receiveDataFromServer();
				if("true".equals(reqData.getRequest())){
					JOptionPane.showMessageDialog(null, "ע��ɹ�","��ʾ�Ի���",1);
					Register r= new Register(client);
					r.setSize(360,244);
					r.setLocation(350,250);
			        r.setTitle("����У԰ϵͳ");
			        r.setVisible(true);
			        dispose();
				}else if("exist".equals(reqData.getRequest())){
					JOptionPane.showMessageDialog(null, "���û��Ѵ���","��ʾ�Ի���",1);
				}else if("false".equals(reqData.getRequest())){
					JOptionPane.showMessageDialog(null, "ע��ʧ��","��ʾ�Ի���",1);
				}
			}else{
				JOptionPane.showMessageDialog(null, "�������벻һ��","��ʾ�Ի���",1);
			}
		}
	}
     
	
}
