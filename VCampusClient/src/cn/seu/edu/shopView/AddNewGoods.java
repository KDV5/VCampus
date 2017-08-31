package cn.seu.edu.shopView;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cn.seu.edu.Client.Client;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.message.ShopData;


import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import javax.swing.filechooser.FileNameExtensionFilter;
import cn.seu.edu.message.InfoData;

//��������Ʒ
public class AddNewGoods {
	private ImageIcon icon;
	Client client;
	String id;
	JTextField t5;
	JTextArea t6;
	JTextField t7;
	JTextArea t8;
	JPanel p=new JPanel();
	JPanel p1=new JPanel();
	
	private JLabel image;
	private JButton choosePic;
	ShopData sData = new ShopData("AddNewGoods");
	
	public AddNewGoods(Client client ,String id,JPanel jp){
		this.client = client;
		this.id = id;
		this.p=jp;
        
		p.setLayout(null);
		p1.setLayout(null);
		p1.setBounds(120, 40, 200,200);
		image = new JLabel();
		image.setBounds(10, 10, 200, 200);
		p1.add(image);
		p.add(p1);
        choosePic = new JButton("�ϴ�ͼƬ");
	    choosePic.addActionListener(new ChoosePicListener());
	    choosePic.setBounds(150, 270, 100, 30);
	    p.add(choosePic);
        
        JLabel t1=new JLabel("��Ʒ����");
        t1.setFont(new Font("������κ",Font.BOLD, 25));
        t1.setBounds(400, 30, 150, 30);
        JLabel t2=new JLabel("��Ʒ��");
        t2.setFont(new Font("������κ",Font.BOLD, 25));
        t2.setBounds(400, 120, 150, 30);
        JLabel t3=new JLabel("��Ʒ�۸�");
        t3.setFont(new Font("������κ",Font.BOLD, 25));
        t3.setBounds(400, 190, 150, 30);
        JLabel t4=new JLabel("���ҽ���");
        t4.setFont(new Font("������κ",Font.BOLD, 25));
        t4.setBounds(120, 340, 150, 30);
       
      
        t5=new JTextField();
        t5.setFont(new Font("����",Font.BOLD, 18));
        t5.setBounds(500, 60, 240, 40);
        
        t6=new JTextArea();
        t6.setFont(new Font("����",Font.BOLD, 18));
        t6.setBorder (BorderFactory.createLineBorder(Color.lightGray,1));
        t6.setBounds(500, 140, 240, 40);
        t6.setLineWrap(true);
       
        t7=new JTextField();
        t7.setFont(new Font("����",Font.BOLD, 18));
        t7.setBounds(500, 220, 240, 40);
        
        t8=new JTextArea();
        t8.setFont(new Font("����",Font.BOLD, 18));
        t8.setBorder (BorderFactory.createLineBorder(Color.lightGray,1));
        t8.setBounds(220, 380, 600, 180);
        t8.setLineWrap(true);
       
        
        JButton addButton = new JButton("����");
        addButton.setBounds(450, 590, 100, 30);
        addButton.addActionListener(new AddListener());
        
        p.add(t5);
        p.add(t6);
        p.add(t7);
        p.add(t8);
        p.add(t1);
        p.add(t2);
        p.add(t3);
        p.add(t4);
        p.add(addButton);

	}
	public static boolean isValidInt(String value) {  
        try {  
            Integer.parseInt(value);  
        } catch (NumberFormatException e) {  
            return false;  
        }  
        return true;  
    } 
	//������Ʒ��ť��Ӧ
	class AddListener implements ActionListener{
		 public void actionPerformed (ActionEvent e){
			 if("".equals(t5.getText().trim()) || "".equals(t6.getText().trim()) || "".equals(t7.getText().trim())  || "".equals(t8.getText().trim())){
				 JOptionPane.showMessageDialog(null, "��������Ʒ��Ϣ","��ʾ�Ի���",1);
			 }else{
				 int option=JOptionPane.showConfirmDialog(null, "ȷ�����۴���Ʒ", "��ʾ�Ի���", JOptionPane.YES_NO_OPTION);
					switch (option) {
				    case JOptionPane.YES_OPTION: 
				    	
				    	String uStorage = t5.getText().trim();
				    	String uGoodsName = t6.getText().trim();
				    	String uPrice = t7.getText().trim();
				    	String uDetail = t8.getText().trim();
				    	if(isValidInt(uStorage)){
				    		sData.setuStorage(Integer.parseInt(uStorage));
					    	sData.setuGoodsName(uGoodsName);
					    	sData.setuPrice(uPrice);
					    	sData.setuDetail(uDetail);
					    	sData.setuSellerID(id);
					    	if("".equals(icon)||icon==null){
					    		JOptionPane.showMessageDialog(null, "�����ͼƬ","��ʾ�Ի���",1);
					    	}else{
					    		sData.setIcon(icon);
					    		client.sendRequestToServer(sData);
						    	RequestData reqData = (RequestData)client.receiveDataFromServer();
						    	if("true".equals(reqData.getRequest())){
									JOptionPane.showMessageDialog(null, "��ӳɹ�","��ʾ�Ի���",1);
									t5.setText("");
									t6.setText("");
									t7.setText("");
									t8.setText("");
								}else if("false".equals(reqData.getRequest())){
									JOptionPane.showMessageDialog(null, "���ʧ��","��ʾ�Ի���",1);
								}
					    	}
					    	
				    	}else{
				    		JOptionPane.showMessageDialog(null, "�����������Ŀ��","��ʾ�Ի���",1);
				    	}

						break;
				    case JOptionPane.NO_OPTION:
				    	break;				    
				    	}
				    	
			 }
		 }
	}
	//�ϴ�ͼƬ��ť��Ӧ
	class ChoosePicListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JFileChooser chooser = new JFileChooser(); //����ѡ���ļ�����
			chooser.setDialogTitle("��ѡ���ļ�");//���ñ���
			chooser.setMultiSelectionEnabled(true);  //����ֻ��ѡ���ļ�
			FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG file", "jpg", "jpeg");//�����ѡ���ļ�����
			chooser.setFileFilter(filter); //���ÿ�ѡ���ļ�����
			chooser.showOpenDialog(null); //��ѡ���ļ��Ի���,null������Ϊ�㵱ǰ�Ĵ���JFrame��Frame
			File file = chooser.getSelectedFile(); //fileΪ�û�ѡ���ͼƬ�ļ�
			InputStream is;
			try {
				is = new FileInputStream(file);
				BufferedImage bi=ImageIO.read(is);
				Image image1 = (Image)bi;
				icon = new ImageIcon(image1);
				sData.setIcon(icon);
				JLabel pic = new JLabel(icon);
    			pic.setBounds(0,0, 200,200);
    			p1.add(pic);
    			p1.repaint();
			}
				catch (Exception e1) {
					e1.printStackTrace();
				}	
			 
		}
	}
}