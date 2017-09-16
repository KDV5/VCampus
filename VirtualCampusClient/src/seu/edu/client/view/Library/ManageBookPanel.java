package seu.edu.client.view.Library;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JPopupMenu;

import com.only.OnlyPopupMenu;

import seu.edu.common.SocketClient;
import seu.edu.common.message.LibraryMessage;
import seu.edu.common.message.ListMessage;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.Font;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class ManageBookPanel extends JPanel {
	private JTextField keyWordField;
	private JTable resultTable;
	private JComboBox typeComboBox;
	private DefaultTableModel resultTableModel;
	private SocketClient socketClient = null;
	Vector headName =new Vector();
    Vector data = new Vector();	
    private ListMessage dataList=null;//存储搜索结果
    private LibraryMessage libraryMessage=null;// 存储最近一次的请求

	/**
	 * Create the panel.
	 */
	
	public ManageBookPanel(SocketClient sc) {
		socketClient=sc;
		
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 814, 640);
		add(panel);
		
		
		final JButton searchButton = new JButton("");
		searchButton.setBounds(736, 85, 33, 28);

		panel.setLayout(null);
		searchButton.setIcon(new ImageIcon(ManageBookPanel.class.getResource("/Image/Library/UI/放大镜 static.png")));
		searchButton.setContentAreaFilled(false); // 不绘制按钮底纹
		searchButton.setFocusPainted(false);	// 不绘制焦点
		searchButton.setBorderPainted(false);	// 不绘制按钮边框
		panel.add(searchButton);

		String[] searchType ={"书名","作者","出版社"};
		typeComboBox = new JComboBox(searchType);
		typeComboBox.setBounds(39, 84, 126, 35);
		panel.add(typeComboBox);
		
		keyWordField = new JTextField();
		keyWordField.setBounds(178, 80, 595, 39);
		keyWordField.setOpaque(false);
		panel.add(keyWordField);
		keyWordField.setColumns(10);

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 144, 734, 462);
		panel.add(scrollPane);
		
		String[] tableHead={"书名","作者","出版社","馆藏本数","可借本数"};
		for(int i=0;i<tableHead.length;i++){			
			headName.add(tableHead[i]);		
		}    
	    resultTableModel=new DefaultTableModel(data, headName);
	    resultTable = new JTable(resultTableModel){
	    	public boolean isCellEditable(int row,int col){
	    		return false;
	    	}
	    };	
		//resultTable.setEnabled(false);
		resultTable.setBounds(35, 127, 747, 482);

		//为查找结果表格添加点击相应
		resultTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//若鼠标点击的行不为空，则弹出该书详细信息
				int t= resultTable.getSelectedRow();
				if(resultTable.getValueAt(resultTable.getSelectedRow(), 0)!=null){
					int row=resultTable.getSelectedRow();
					EditBookDialog editBookDialog=new EditBookDialog((LibraryMessage)(dataList.getDataList().get(row)), socketClient);
					editBookDialog.setVisible(true);
					refresh();
//					socketClient.sendRequestToServer(libraryMessage);	
//					dataList=(ListMessage) socketClient.receiveDataFromServer();	
//					JOptionPane.showMessageDialog(null,resultTable.getValueAt(row, 1));
				}
			}
		});

		scrollPane.setViewportView(resultTable);
		
	
		JLabel backGround = new JLabel("");
		backGround.setBounds(0, 0, 814, 640);
		backGround.setIcon(new ImageIcon(ManageBookPanel.class.getResource("/Image/Library/UI/图书管理 panel.png")));
		panel.add(backGround);
		
		//搜索按钮响应函数
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				searchByKeyword((String)typeComboBox.getSelectedItem(),keyWordField.getText());				
				setTableData(dataList,resultTableModel);				
				resultTable.validate();
			}
		});		
	}

	
	//根据关键字查找书籍
	public void searchByKeyword(String keyWordsType,String keyword){
		String keyType=null;
		if("书名".equals(keyWordsType)){
			keyType="BOOK_NAME";
		}else if ("作者".equals(keyWordsType)){
			keyType="AUTHOR";
		}
		libraryMessage =new LibraryMessage("SEARCH_BY_KEYWORDS",keyType,keyword);
		socketClient.sendRequestToServer(libraryMessage);	
		dataList=(ListMessage) socketClient.receiveDataFromServer();		
	}
	
	//设置表格内容
	private void setTableData(ListMessage rs,DefaultTableModel dtm){		
		//Vector data =new Vector();
		//循环表格删除行
		int t=dtm.getRowCount();
		for(int i=0;i<t;i++){
			dtm.removeRow(i);
		}
		//dtm.fireTableRowsDeleted(0, dtm.getRowCount());
		Vector row;
		int i=0;
			for(;i<rs.getDataList().size();i++){
				row=new Vector();
				row.add(((LibraryMessage)(rs.getDataList().get(i))).getBookName());
				row.add(((LibraryMessage)(rs.getDataList().get(i))).getAuther());
				row.add(((LibraryMessage)(rs.getDataList().get(i))).getPublisher());
				row.add(((LibraryMessage)(rs.getDataList().get(i))).getTotalNumber());
				row.add(((LibraryMessage)(rs.getDataList().get(i))).getStorage());
				//data.add(row);		
				dtm.addRow(row);
			}
	}
	
	private void refresh(){
		searchByKeyword((String)typeComboBox.getSelectedItem(),keyWordField.getText());				
		setTableData(dataList,resultTableModel);				
		resultTable.validate();
	}
}


