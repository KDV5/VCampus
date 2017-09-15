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

import seu.edu.common.SocketClient;
import seu.edu.common.message.LibraryMessage;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class BorrowBookDialog extends JDialog {

	private SocketClient socketClient = null;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField nameTextField;
	private JTextField authorTextField;
	private JTextField publicsherTextField;
	private JTextField TypeTextField;
	private JTextField idTextField;
	private JTextField placeTextField;
	private JTextField storageTextField;

	private LibraryMessage libraryMessage;
	String stuNumber=null;
	String stuName=null;
	
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
			BorrowBookDialog dialog = new BorrowBookDialog(new LibraryMessage("aaa"),new SocketClient());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 

	/**
	 * Create the dialog.
	 */
	public BorrowBookDialog(LibraryMessage lm,SocketClient sc) {
		
		libraryMessage=lm;
		socketClient=sc;
		setBounds((width - windowsWedth) / 2,(height - windowsHeight) / 2,701, 555);
		//setBounds(100, 100, 701, 555);
		getContentPane().setLayout(null);
		
		setModal(true);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 701, 555);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel bookImage = new JLabel("");
		bookImage.setForeground(Color.DARK_GRAY);
		bookImage.setBackground(Color.BLACK);
		bookImage.setBounds(444, 67, 195, 253);
		panel.add(bookImage);
		
		//简介滚动条
		JScrollPane introScrollPane = new JScrollPane();
		introScrollPane.setBounds(55, 333, 584, 145);
		panel.add(introScrollPane);
		
		//简介文本框
		final JTextArea introTextArea = new JTextArea();
		introTextArea.setText("");
		introTextArea.setBounds(55, 380, 702, 157);
		introTextArea.setOpaque(false);
		introScrollPane.add(introTextArea);	
		introScrollPane.setViewportView(introTextArea);
		
		//借阅按钮响应函数
		JButton borrowButton = new JButton("");
		borrowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					//首先判断是否有库存
					if(storageTextField.getColumns()>0){
					LibraryMessage lm=new LibraryMessage("LEND_BOOK",nameTextField.getText(), idTextField.getText(), stuNumber, stuName);
					socketClient.sendRequestToServer(lm);
					LibraryMessage result= (LibraryMessage)(socketClient.receiveDataFromServer());
					if(result.getOperResult().equals("LEND_BOOKS_FULL")){
						JOptionPane.showMessageDialog(null, "结束数量已达到最大值，借书失败");
					}else{
						if(result.getOperResult().equals("LEND_BOOK_TWICE")){
							JOptionPane.showMessageDialog(null, "已借过这本书");
						}
						else if(result.getOperResult().equals("LEND_BOOK_SUCCEED")){
							JOptionPane.showMessageDialog(null, "借书成功");
						}
					}
				}else{
					JOptionPane.showMessageDialog(null, "这本书已经借光了");
				}
				
			}
		});
		borrowButton.setIcon(new ImageIcon(BorrowBookDialog.class.getResource("/UI/Library/借阅.png")));
		borrowButton.setBounds(253, 488, 75, 30);
		panel.add(borrowButton);
		
		JButton backButton = new JButton("");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		backButton.setIcon(new ImageIcon(BorrowBookDialog.class.getResource("/UI/Library/返回.png")));
		backButton.setBounds(381, 488, 75, 30);
		panel.add(backButton);
		
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
		
		storageTextField = new JTextField();
		setTextField(storageTextField,7);
		panel.add(storageTextField);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 701, 555);
		panel.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(BorrowBookDialog.class.getResource("/UI/Library/借书Dialog.png")));
		
		
		contentPanel.setBounds(0, 0, 701, 555);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		this.setUndecorated(true);
		setBackground(new Color(0,0,0,0));
		panel.setOpaque(false);
		setContentPane(panel);
		//this.setBackground (new Color (0, 0, 0, 0));
		
		//设置各个文本框内容
		nameTextField.setText(lm.getBookName());
		authorTextField.setText(lm.getAuther());
		publicsherTextField.setText(lm.getPublisher());
		TypeTextField.setText(lm.getType());
		idTextField.setText(lm.getBookID());
		placeTextField.setText(lm.getPlace());
		storageTextField.setText(String.valueOf(lm.getStorage()));
		introTextArea.setText(lm.getIntroduct());		
		bookImage.setIcon(lm.getIcon()); // 设置封面		
		
	}
	
	private void setTextField(JTextField t,int i){
		t.setColumns(10);
		t.setBounds(135, 51+36*(i-1), 241,30);
		t.setOpaque(false);
		t.setBorder(null); 
	}
}
