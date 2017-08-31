package cn.seu.edu.register;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cn.seu.edu.Client.Client;
import cn.seu.edu.message.LoginData;
import cn.seu.edu.message.RequestData;
/*
 * ChangePassword
 * 
 *
 * @author Ge Danwei
 *
 * @Date 2016/9/17
 *
 */

public class ChangePassword {
	Client client;
	String id;
	JPanel frame = new JPanel();

	JLabel oldLab = new JLabel("��������룺");
	JLabel newLab1 = new JLabel("���������룺");
	JLabel newLab2 = new JLabel("ȷ�������룺");
	JTextField oldPass = new JPasswordField(15);
	JTextField newPass1 = new JPasswordField(15);
	JTextField newPass2 = new JPasswordField(15);
	JButton sureButton = new JButton("ȷ��");
	
	public ChangePassword(final Client client,final String id,JPanel jp){
		this.client = client;
		this.id = id;
		this.frame=jp;
		
		frame.removeAll();
		frame.setLayout(null);
		
		oldLab.setBounds(300, 150, 300, 30);
		oldLab.setFont(new Font("�����п�",Font.BOLD, 26));
		oldPass.setBounds(440, 200, 200, 30);
		oldPass.setFont(new Font("����",Font.BOLD, 18));
		newLab1.setBounds(300, 250, 300, 30);
		newLab1.setFont(new Font("�����п�",Font.BOLD, 26));
		newPass1.setBounds(440, 300, 200, 30);
		newPass1.setFont(new Font("����",Font.BOLD, 18));
		newLab2.setBounds(300, 350, 300, 30);
		newLab2.setFont(new Font("�����п�",Font.BOLD, 26));
		newPass2.setBounds(440, 400,200, 30);
		newPass2.setFont(new Font("����",Font.BOLD, 18));
		sureButton.setBounds(420, 530, 100, 30);
		
		frame.add(oldLab);
		frame.add(oldPass);
		frame.add(newLab1);
		frame.add(newPass1);
		frame.add(newLab2);
		frame.add(newPass2);
		frame.add(sureButton);
		
		sureButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String old = oldPass.getText().trim();
				String new1 = newPass1.getText().trim();
				String new2 = newPass2.getText().trim();
				if(new1.equals(new2)){
					LoginData loginData = new LoginData("changeLoginPass");
					loginData.setUserName(id);
					loginData.setUserPassword(old);
					loginData.setNewPassword(new1);
					client.sendRequestToServer(loginData);
					RequestData reqData = (RequestData)client.receiveDataFromServer();
					if("true".equals(reqData.getRequest())){
						JOptionPane.showMessageDialog(null, "�޸ĳɹ�","��ʾ�Ի���",1);
						oldPass.setText("");
						newPass1.setText("");
						newPass2.setText("");
					}else if("false".equals(reqData.getRequest())){
						JOptionPane.showMessageDialog(null, "�޸�ʧ��","��ʾ�Ի���",1);
					}			
			     }else{
			    	 JOptionPane.showMessageDialog(null, "�������벻һ�£�����","��ʾ�Ի���",1);
			     }
			}
		});
		}
	
	
}
