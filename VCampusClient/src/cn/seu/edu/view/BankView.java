package cn.seu.edu.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

import cn.seu.edu.Client.Client;
import cn.seu.edu.helper.Helper;
import cn.seu.edu.message.BankData;
import cn.seu.edu.message.IdData;
import cn.seu.edu.message.RequestData;

public class BankView {
	Client client;
	String id;
	JPanel frame = new JPanel();
	
	
	JLabel chargeLab = new JLabel("请输入充值金额：");
    JTextField chargeText = new JTextField(15);
	JButton sureButton = new JButton("确认");
	JLabel passLab = new JLabel("请输入一卡通密码：");
	JTextField passText = new JPasswordField(15);
	JButton charge=new JButton();
	
	JRadioButton bankButton = new JRadioButton("银行卡");
	JRadioButton cardButton = new JRadioButton("一卡通");
	JLabel oldLab = new JLabel("输入旧密码：");
	JLabel newLab1 = new JLabel("输入新密码：");
	JLabel newLab2 = new JLabel("确认新密码：");
	JTextField oldPass = new JPasswordField(15);
	JTextField newPass1 = new JPasswordField(15);
	JTextField newPass2 = new JPasswordField(15);
	JTextArea outputText1;
	JTextArea outputText2;
	Boolean judge=false;
	
	public BankView(Client client,String id,JPanel jp){
		this.client = client;
		this.id = id;
		this.frame=jp;
		
		outputText1 = new JTextArea(20,30);
		outputText2 = new JTextArea(20,30);
		charge.addActionListener(new changePassqstionPerformed());


	}

	public void balance()
		{
			outputText1.setText("");
			outputText1.setBounds(380, 260, 200, 40);
			outputText1.setFont(new Font("宋体",Font.BOLD, 30));
			outputText2.setText("");
			outputText2.setBounds(380, 460, 200, 40);
			outputText2.setFont(new Font("宋体",Font.BOLD, 30));
			frame.removeAll();			
			outputText1.setLineWrap(true);
			outputText1.setEditable(false);
			frame.add(outputText1);
			outputText2.setLineWrap(true);
			outputText2.setEditable(false);
			frame.add(outputText2);
			client.sendRequestToServer(new IdData("balance",id));
			BankData bankData = (BankData)client.receiveDataFromServer();
			
			JLabel output1=new JLabel("一卡通余额");
			output1.setFont(new Font("华文行楷",Font.BOLD, 26));
			output1.setBounds(260, 160, 200, 30);
			frame.add(output1);
			JLabel output2=new JLabel("银行卡余额");
			output2.setFont(new Font("华文行楷",Font.BOLD, 26));
			output2.setBounds(260, 360, 200, 30);
			frame.add(output2);
			outputText1.append(bankData.getCardBalance()+"元\n");
			outputText2.append(bankData.getbBalance()+"元\n");
			frame.repaint();
		}
	
		public void charge()
		{
			
			frame.removeAll();
			chargeLab.setBounds(300, 200, 300, 30);
			chargeLab.setFont(new Font("华文行楷",Font.BOLD, 26));
			chargeText.setBounds(400, 260, 240, 30);
			chargeText.setFont(new Font("宋体",Font.BOLD, 18));
			passLab.setBounds(300, 330, 300, 30);
			passLab.setFont(new Font("华文行楷",Font.BOLD, 26));
			passText.setBounds(400, 390, 240, 30);
			passText.setFont(new Font("宋体",Font.BOLD, 18));
			sureButton.setBounds(600, 460, 100, 30);
			frame.add(chargeLab);
			frame.add(chargeText);
			frame.add(passLab);
			frame.add(passText);
			frame.add(sureButton);
			sureButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					String account = chargeText.getText().trim();
					String cardPass = passText.getText().trim();
					if(Helper.isNumeric(account)){
						BankData bankData = new BankData("charge");
						bankData.setId(id);
						bankData.setbBalance(account);
						bankData.setCardPassword(cardPass);
						client.sendRequestToServer(bankData);
						RequestData reqData = (RequestData)client.receiveDataFromServer();
						if("true".equals(reqData.getRequest())){
							JOptionPane.showMessageDialog(null, "充值成功","提示对话框",1);
							chargeText.setText("");
							passText.setText("");
						}else if("false".equals(reqData.getRequest())){
							JOptionPane.showMessageDialog(null, "充值失败","提示对话框",1);
							chargeText.setText("");
							passText.setText("");
						}else if("wrong".equals(reqData.getRequest())){
							JOptionPane.showMessageDialog(null, "密码错误","提示对话框",1);
						}
					}else{
						JOptionPane.showMessageDialog(null, "输入金额有误","提示对话框",1);
					}
				}
			});
			frame.repaint();
	}
	
	public class changePassqstionPerformed implements ActionListener, ItemListener{
		String uName = "card";
		public void actionPerformed(ActionEvent e)
		{
			frame.removeAll();
			oldLab.setBounds(300, 150, 300, 30);
			oldLab.setFont(new Font("华文行楷",Font.BOLD, 26));
			oldPass.setBounds(440, 200, 200, 30);
			oldPass.setFont(new Font("宋体",Font.BOLD, 18));
			newLab1.setBounds(300, 250, 300, 30);
			newLab1.setFont(new Font("华文行楷",Font.BOLD, 26));
			newPass1.setBounds(440, 300, 200, 30);
			newPass1.setFont(new Font("宋体",Font.BOLD, 18));
			newLab2.setBounds(300, 350, 300, 30);
			newLab2.setFont(new Font("华文行楷",Font.BOLD, 26));
			newPass2.setBounds(440, 400,200, 30);
			newPass2.setFont(new Font("宋体",Font.BOLD, 18));
			bankButton.setBounds(340, 450, 100, 30);
			bankButton.setOpaque(false);
			cardButton.setBounds(500, 450, 100, 30);
			cardButton.setOpaque(false);
			sureButton.setBounds(420, 530, 100, 30);
			
			ButtonGroup group = new ButtonGroup();
			group.add(bankButton);
			group.add(cardButton);
			bankButton.addItemListener(this);
			cardButton.addItemListener(this);
			cardButton.setSelected(true);
			
			frame.add(oldLab);
			frame.add(oldPass);
			frame.add(newLab1);
			frame.add(newPass1);
			frame.add(newLab2);
			frame.add(newPass2);
			frame.add(bankButton);
			frame.add(cardButton);
			frame.add(sureButton);
			
			sureButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					String old = oldPass.getText().trim();
					String new1 = newPass1.getText().trim();
					String new2 = newPass2.getText().trim();
					if(new1.equals(new2)){
						BankData bankData = new BankData("changePass");
						bankData.setId(id);
						bankData.setbName(uName);
						bankData.setCardPassword(old);
						bankData.setbPassword(new1);
						client.sendRequestToServer(bankData);
						RequestData reqData = (RequestData)client.receiveDataFromServer();
						if("true".equals(reqData.getRequest())){
							JOptionPane.showMessageDialog(null, "修改成功","提示对话框",1);
							oldPass.setText("");
							newPass1.setText("");
							newPass2.setText("");
						}else if("false".equals(reqData.getRequest())){
							JOptionPane.showMessageDialog(null, "修改失败","提示对话框",1);
						}
					
					
				     }else{
				    	 JOptionPane.showMessageDialog(null, "密码不一致，请检查","提示对话框",1);
				     }
				}
			});
			frame.repaint();
		}

		
		public void itemStateChanged(ItemEvent e) {
			if(e.getSource() == bankButton){
			    uName = "bank";
			}
			else{
				uName = "card";
			}
			
		}
	}

	
}
