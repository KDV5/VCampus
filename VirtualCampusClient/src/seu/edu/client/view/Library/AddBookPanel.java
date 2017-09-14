package seu.edu.client.view.Library;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JPopupMenu;

import com.only.OnlyPopupMenu;
import com.only.OnlyScrollPane;

import javafx.beans.binding.ListBinding;
import seu.edu.common.SocketClient;
import seu.edu.common.message.LibraryMessage;
import seu.edu.common.message.ListMessage;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTable;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.ScrollPane;
import java.awt.TextArea;
import javax.swing.SwingConstants;

public class AddBookPanel extends JPanel {
	private SocketClient socketClient = null;

    private JTextField author;
    private JTextField bookName;
    private JTextField publisher;
    private JTextField type;
    private JTextField bookID;
    private JTextField total;
    private JTextField storage;
    private JTextField place;
    
    private ImageIcon icon;
    private JLabel insertImage=null;

	/**
	 * Create the panel.
	 */
	
	public AddBookPanel(SocketClient sc) {
		try{
			// UIManager.setLookAndFeel("org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF");
		}catch(Throwable e){
			e.printStackTrace();
		}
		
		socketClient=sc;
		
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 814, 640);
		add(panel);

		panel.setLayout(null);
		
		insertImage = new JLabel("");
		insertImage.setHorizontalAlignment(SwingConstants.CENTER);
		insertImage.addMouseListener(new MouseAdapter() {
			@Override
			/*添加图片 鼠标单击响应函数
			 */
			public void mouseClicked(MouseEvent arg0) {
				JFileChooser chooser = new JFileChooser(); //创建选择文件对象
				chooser.setDialogTitle("请选择文件");//设置标题
				chooser.setMultiSelectionEnabled(true);  //设置只能选择文件
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG file", "jpg", "jpeg");//定义可选择文件类型
				chooser.setFileFilter(filter); //设置可选择文件类型
				chooser.showOpenDialog(null); //打开选择文件对话框,null可设置为你当前的窗口JFrame或Frame
				File file = chooser.getSelectedFile(); //file为用户选择的图片文件
				InputStream is;
				try {
					is = new FileInputStream(file);
					BufferedImage bi=ImageIO.read(is);
					Image image1 = (Image)bi;
					icon = new ImageIcon(image1);
					//sData.setIcon(icon);
					//JLabel pic = new JLabel(icon);
	    			//pic.setBounds(0,0, 200,200);
	    			insertImage.setIcon(icon);
	    			repaint();
	    			//p1.add(pic);
	    			//p1.repaint();
				}
					catch (Exception e1) {
						e1.printStackTrace();
					}	
				 
			}
		});
		insertImage.setBackground(Color.WHITE);
		insertImage.setIcon(new ImageIcon(AddBookPanel.class.getResource("/UI/Library/添加图片.png")));
		insertImage.setBounds(555, 58, 200, 271);
		panel.add(insertImage);

		
		bookName = new JTextField();
		setTextField(bookName, 1);
		panel.add(bookName);
		
		author = new JTextField();
		setTextField(author, 2);
		panel.add(author);
		
		publisher = new JTextField();
		setTextField(publisher, 3);
		panel.add(publisher);
		
		type = new JTextField();
		setTextField(type, 4);
		panel.add(type);
		
		bookID = new JTextField();
		setTextField(bookID, 5);
		panel.add(bookID);
		
		place = new JTextField();
		setTextField(place, 6);
		panel.add(place);
		
		total = new JTextField();
		setTextField(total, 7);
		panel.add(total);
		
		storage = new JTextField();
		setTextField(storage, 8);
		panel.add(storage);
		
		JScrollPane introScrollPane = new JScrollPane();
		introScrollPane.setBounds(55, 380, 702, 157);
		panel.add(introScrollPane);
		
		//简介文本框
		final JTextArea introTextArea = new JTextArea();
		introTextArea.setText("aaaaaa");
		introTextArea.setBounds(55, 380, 702, 157);
		introTextArea.setOpaque(false);
		introScrollPane.add(introTextArea);	
		introScrollPane.setViewportView(introTextArea);
		
		JButton ensureButton = new JButton("");
		ensureButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// 确认按钮的响应函数
				//首先判断数据合法性
				/*
				 * 待完成
				 */
				LibraryMessage lm=new LibraryMessage("ADD_BOOK", bookID.getText(), bookName.getText(), author.getText(), 
						place.getText(), total.getColumns(), storage.getColumns(), introTextArea.getText(), publisher.getText(), type.getText());
				if(icon==null){//若未上传图片
					
				}else{
					lm.setIcon(icon);
					socketClient.sendRequestToServer(lm);
					LibraryMessage result=(LibraryMessage)(socketClient.receiveDataFromServer());
					if(result.getOperResult().equals("ADD_BOOK_SUCCEED")){
						JOptionPane.showMessageDialog(null, "入库成功");
					}else{
						JOptionPane.showMessageDialog(null, "入库失败");
					}
				}
			}
		});
		ensureButton.setBounds(587, 558, 171, 42);
		ensureButton.setContentAreaFilled(false); // 不绘制按钮底纹
		ensureButton.setFocusPainted(false);	// 不绘制焦点
		ensureButton.setBorderPainted(false);	// 不绘制按钮边框
		panel.add(ensureButton);
		

		

		JLabel backGround = new JLabel("");
		backGround.setBounds(0, 0, 814, 640);
		backGround.setIcon(new ImageIcon(AddBookPanel.class.getResource("/UI/Library/add_Panel.png")));
		panel.add(backGround);
	}
	
	private void setTextField(JTextField t,int i){
		t.setColumns(10);
		t.setBounds(147, 53+35*(i-1), 351, 27);
		t.setOpaque(false);
		t.setBorder(null); 
	}
}


