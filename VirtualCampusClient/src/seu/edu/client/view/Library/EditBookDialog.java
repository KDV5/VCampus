package seu.edu.client.view.Library;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;

import seu.edu.MessageDialog.MessageDialog;
import seu.edu.common.SocketClient;
import seu.edu.common.message.LibraryMessage;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import java.awt.Font;

public class EditBookDialog extends JDialog {

	private SocketClient socketClient = null;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField nameTextField;
	private JTextField authorTextField;
	private JTextField publicsherTextField;
	private JTextField TypeTextField;
	private JTextField idTextField;
	private JTextField placeTextField;

	private LibraryMessage libraryMessage;
	String stuNumber=null;
	String stuName=null;
	
	private MessageDialog md=new MessageDialog(); 
			
    public int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    public int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    // 定义窗体的宽高
    public int windowsWedth = 701;
    public int windowsHeight = 555;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditBookDialog dialog = new EditBookDialog(new LibraryMessage("aaa"),new SocketClient());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 

	/**
	 * Create the dialog.
	 */
	public EditBookDialog(LibraryMessage lm,SocketClient sc) {
		
		UIManager.getSystemLookAndFeelClassName();
		
		//设置对话框样式
		libraryMessage=lm;
		socketClient=sc;
		setBounds((width - windowsWedth) / 2,(height - windowsHeight) / 2,701, 555);		
		getContentPane().setLayout(null);		
		setModal(true);		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 701, 555);
		panel.setLayout(null);
		this.setUndecorated(true);
		setBackground(new Color(0,0,0,0));
		panel.setOpaque(false);
		setContentPane(panel);
		
		//添加背景图片
		JLabel bookImage = new JLabel("");
		bookImage.setForeground(Color.DARK_GRAY);
		bookImage.setBackground(Color.BLACK);
		bookImage.setBounds(444, 67, 195, 253);
		panel.add(bookImage);
		
		//简介滚动条
		JScrollPane introScrollPane = new JScrollPane();
		introScrollPane.setBounds(55, 361, 584, 117);
		panel.add(introScrollPane);
		
		//简介文本框
		final JTextArea introTextArea = new JTextArea();
		introTextArea.setText("");
		introTextArea.setBounds(55, 380, 702, 157);
		introTextArea.setOpaque(false);
		introScrollPane.add(introTextArea);	
		introScrollPane.setViewportView(introTextArea);
		
		//创建借阅按钮
		JButton editButton = new JButton("");
		editButton.setIcon(new ImageIcon(EditBookDialog.class.getResource("/Image/Library/UI/修改.png")));
		editButton.setBounds(314, 488, 75, 30);
		panel.add(editButton);	
		//删除图书按钮
		JButton deledtButton = new JButton("");
		deledtButton.setIcon(new ImageIcon(EditBookDialog.class.getResource("/Image/Library/UI/删除.png")));
		deledtButton.setBounds(186, 488, 75, 30);
		panel.add(deledtButton);
		//返回按钮
		JButton backButton = new JButton("");
		backButton.setIcon(new ImageIcon(EditBookDialog.class.getResource("/Image/Library/UI/返回.png")));
		backButton.setBounds(442, 488, 75, 30);
		panel.add(backButton);
		
		//创建文本框
		nameTextField = new JTextField();
		setTextField(nameTextField,1);
		panel.add(nameTextField);
		
		authorTextField = new JTextField();
		setTextField(authorTextField,2);
		panel.add(authorTextField);
		
		publicsherTextField = new JTextField();
		setTextField(publicsherTextField,3);
		panel.add(publicsherTextField);
		
		TypeTextField = new JTextField();
		setTextField(TypeTextField,4);
		panel.add(TypeTextField);
		
		idTextField = new JTextField();
		setTextField(idTextField,5);
		panel.add(idTextField);
		
		placeTextField = new JTextField();
		setTextField(placeTextField,6);
		panel.add(placeTextField);
		
		//设置 最小值、初始值为0，最大值为100、间隔为1
		SpinnerModel spm=new SpinnerNumberModel(0,0,100,1);
				
		final JSpinner storageSpinner = new JSpinner(spm);
		storageSpinner.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		storageSpinner.setBounds(135, 270, 241, 30);
		panel.add(storageSpinner);
		
		final JSpinner totalSpinner = new JSpinner(spm);
		totalSpinner.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		totalSpinner.setBounds(135, 306, 241, 30);
		panel.add(totalSpinner);
		
		//设置各个文本框内容
		nameTextField.setText(lm.getBookName());
		authorTextField.setText(lm.getAuther());
		publicsherTextField.setText(lm.getPublisher());
		TypeTextField.setText(lm.getType());
		idTextField.setText(lm.getBookID());
		placeTextField.setText(lm.getPlace());
		storageSpinner.setValue(lm.getStorage());
		totalSpinner.setValue(lm.getTotalNumber());
		introTextArea.setText(lm.getIntroduct());		
		bookImage.setIcon(lm.getIcon()); // 设置封面		
		

		
		//背景图片
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 701, 555);
		panel.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(EditBookDialog.class.getResource("/Image/Library/UI/编辑图书.png")));
		
		//修改 按钮响应函数
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					//判断可借数量是否小于库存，否则错误
					if((int)(storageSpinner.getValue())>(int)(totalSpinner.getValue())){
						//满足条件，修改数据库
						LibraryMessage lm=new LibraryMessage("EDIT_BOOK", idTextField.getText(), nameTextField.getText(), authorTextField.getText(), 
								placeTextField.getText(), (int)totalSpinner.getValue(), (int)storageSpinner.getValue(), introTextArea.getText(),publicsherTextField.getText(), TypeTextField.getText());
						socketClient.sendRequestToServer(lm);
						LibraryMessage result= (LibraryMessage)(socketClient.receiveDataFromServer());
						if(result.getOperResult().equals("EDIT_BOOK_SUCCEED")){
							md.showMessage("SUCCEED", "图书修改成功");
							dispose();
						}else if(result.getOperResult().equals("EDIT_BOOK_FAILED")){
								md.showMessage("ERROR", "图书修改失败");
							}

					}else{
						//输入数据不满足要求
						md.showMessage("ERROR", "图书数量输入有误，请检查");
					}

				
			}
		});
		
		//删除图书按钮响应
		
		deledtButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(md.showMessage("WARNING", "您真的要删除这本书吗？该操作不可恢复")){
					//删除图书
					LibraryMessage lm=new LibraryMessage("DELETE_BOOK",idTextField.getText());
					socketClient.sendRequestToServer(lm);
					if(((LibraryMessage)(socketClient.receiveDataFromServer())).getOperResult().equals("DELETE_BOOK_SUCCEED")){
						md.showMessage("SUCCEED", "图书删除成功");
					}else{
						md.showMessage("ERROR", "图书删除失败");
					}
				}else{
					
				}
				
			}
		});
		
		//返回按钮响应
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		

		

		
		

		
	}
	
	private void setTextField(JTextField t,int i){
		t.setColumns(10);
		t.setBounds(135, 51+36*(i-1), 241,30);
		t.setOpaque(false);
		t.setBorder(null); 
	}
}
