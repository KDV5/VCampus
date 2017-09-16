package seu.edu.client.view;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.only.OnlyFrame;

import seu.edu.vo.User;

public class MainFrame extends OnlyFrame {

	private JPanel contentPane;
	private CardLayout layout =null;
	private LoginPanel loginPanel=null;
	private RegisterPanel registerPanel =null;
	private ChangePassPanel changePassPanel = null;
	
	boolean lFlag=true;// ��� LoginPanel�Ƿ���ӵ�contentpanel��
	boolean rFlag=false;// ���RegisterPanel�Ƿ���ӵ�contentpanel��
	boolean cFlag=false;// ���changePassPanel�Ƿ���ӵ�contentpanel��
	//private JPanel mainPanel = null;
	
	private User user;
	private String name = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setResizable(false);//��ֹ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 323, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//layout �����л���ʾ�ĸ�panel
		//layout = new CardLayout(0, 0);
		//mainPanel.setLayout(layout);
		
		loginPanel=new LoginPanel(this);
		loginPanel.setBounds(0, 0, 323, 439);
		contentPane.add(loginPanel);
		
		registerPanel = new RegisterPanel(this);
		registerPanel.setBounds(0, 0, 323, 439);
		//contentPane.add(registerPanel);
		
		changePassPanel = new ChangePassPanel(this);
		changePassPanel.setBounds(0, 0, 323, 439);
		//contentPane.add(changePassPanel);
		
		
//	turnToRegister.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				toRegisterPanel();
//			}
//		});
		

	}
	
	/*
	 * �����߳�ʵ��Panel�Ļ����л�
	 * 
	 */
	
	// �ӵ�¼����л���ע�����
	public void toRegisterPanel(){	
		
		//��ע�����δ����contentPanel�У������
		//��ʱע�����λ�ڵ�¼����·�
		if(!rFlag){
			contentPane.add(registerPanel);
		}
		
		//����ע�����λ���������������
		registerPanel.setBounds(0, 0, contentPane.getWidth(), contentPane.getHeight());		
		new Thread() {

			public void run() {

				Rectangle rec = contentPane.getBounds();// ��õ�ǰ������λ����Ϣ
				int y = contentPane.getWidth();
				
				//ÿ5ms��ע�����͵�¼���ͬʱ���ƶ�10����
				for (int i = 0; i > -contentPane.getWidth(); i -= 2) {
					// �������λ��
					loginPanel.setBounds(i, 0,
							loginPanel.getWidth(),
							loginPanel.getHeight());
					
					registerPanel.setBounds(y, 0,
							contentPane.getWidth(),
							contentPane.getHeight());
					y -= 2;
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				contentPane.remove(loginPanel);

			}
		}.start();
	}
	
	public void rToLoginPanel(){	
		
		contentPane.add(loginPanel);	
		
		new Thread() {

			public void run() {

				Rectangle rec = contentPane.getBounds();// ��õ�ǰ����λ����Ϣ
				int y = 0;

				for (int i = -contentPane.getWidth(); i < 0; i += 2) {
					// �������λ��
					loginPanel.setBounds(i, 0,
							loginPanel.getWidth(),
							loginPanel.getHeight());
					
					registerPanel.setBounds(y, 0,
							contentPane.getWidth(),
							contentPane.getHeight());
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					y += 2;
				}
				if(rFlag){
					contentPane.remove(registerPanel);
				}
				if(cFlag){
					contentPane.remove(changePassPanel);
				}

			}
		}.start();
		
	}
	
	public void cToLoginPanel(){	
		
		contentPane.add(loginPanel);	
		
		new Thread() {

			public void run() {

				Rectangle rec = contentPane.getBounds();// ��õ�ǰ����λ����Ϣ
				int y = 0;

				for (int i = -contentPane.getWidth(); i < 0; i += 2) {
					// �������λ��
					loginPanel.setBounds(i, 0,
							loginPanel.getWidth(),
							loginPanel.getHeight());
					
					changePassPanel.setBounds(y, 0,
							contentPane.getWidth(),
							contentPane.getHeight());
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					y += 2;
				}
				if(rFlag){
					contentPane.remove(registerPanel);
				}
				if(cFlag){
					contentPane.remove(changePassPanel);
				}

			}
		}.start();
		
	}
	
	public void toChangePassPanel(){
		
		if(!cFlag){
			contentPane.add(changePassPanel);
		}
		
		changePassPanel.setBounds(0, 0, contentPane.getWidth(), contentPane.getHeight());		
		new Thread() {

			public void run() {

				Rectangle rec = contentPane.getBounds();// ��õ�ǰ����λ����Ϣ
				int y = contentPane.getWidth();

				for (int i = 0; i > -contentPane.getWidth(); i -= 2) {
					// �������λ��
					loginPanel.setBounds(i, 0,
							loginPanel.getWidth(),
							loginPanel.getHeight());
					
					changePassPanel.setBounds(y, 0,
							contentPane.getWidth(),
							contentPane.getHeight());
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					y -= 2;
				}
				
				contentPane.remove(loginPanel);

			}
		}.start();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
