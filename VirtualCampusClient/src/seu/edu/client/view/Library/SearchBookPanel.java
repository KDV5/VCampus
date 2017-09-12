package seu.edu.client.view.Library;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JPopupMenu;

import com.only.OnlyPopupMenu;

import javafx.beans.binding.ListBinding;
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
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class SearchBookPanel extends JPanel {
	private JTextField keyWordField;
	private JTable resultTable;
	private SocketClient socketClient = null;
	Vector headName =new Vector();
    Vector data = new Vector();	

	/**
	 * Create the panel.
	 */
	
	public SearchBookPanel(SocketClient sc) {
		socketClient=sc;
		
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 814, 640);
		add(panel);
		
		//搜索按钮的响应函数
		JButton searchButton = new JButton("");
		searchButton.setBounds(699, 43, 24, 24);

		panel.setLayout(null);
		searchButton.setIcon(new ImageIcon(SearchBookPanel.class.getResource("/UI/Library/放大镜.png")));
		searchButton.setContentAreaFilled(false); // 不绘制按钮底纹
		searchButton.setFocusPainted(false);	// 不绘制焦点
		searchButton.setBorderPainted(false);	// 不绘制按钮边框
		panel.add(searchButton);

		String[] searchType ={"书名","作者","出版社"};
		JComboBox comboBox = new JComboBox(searchType);
		comboBox.setBounds(60, 38, 126, 35);
		panel.add(comboBox);
		
		keyWordField = new JTextField();
		keyWordField.setBounds(207, 32, 529, 47);
		keyWordField.setOpaque(false);
		panel.add(keyWordField);
		keyWordField.setColumns(10);
		
		JTextPane hotWord = new JTextPane();
		hotWord.setBounds(58, 86, 139, 24);
		hotWord.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		hotWord.setText("热搜关键词：");
		hotWord.setOpaque(false);
		panel.add(hotWord);	

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 127, 747, 482);
		panel.add(scrollPane);
		
		String[] tableHead={"书名","作者","出版社","馆藏本数","可借本数"};
		for(int i=0;i<tableHead.length;i++){			
			headName.add(tableHead[i]);		
		}    
	    final DefaultTableModel resultTableModel=new DefaultTableModel(data, headName);
	    resultTable = new JTable(resultTableModel){
	    	public boolean isCellEditable(int row,int col){
	    		return false;
	    	}
	    };	
		//resultTable.setEnabled(false);
		resultTable.setBounds(35, 127, 747, 482);

		//为表格添加点击相应
		resultTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//若鼠标点击的行不为空
				int t= resultTable.getSelectedRow();
				if(resultTable.getValueAt(resultTable.getSelectedRow(), 0)!=null){
					int row=resultTable.getSelectedRow();
					JOptionPane.showMessageDialog(null,resultTable.getValueAt(row, 1));
				}
			}
		});

		scrollPane.setViewportView(resultTable);
		
	
		JLabel backGround = new JLabel("");
		backGround.setBounds(0, 0, 814, 640);
		backGround.setIcon(new ImageIcon(SearchBookPanel.class.getResource("/UI/Library/Panel.png")));
		panel.add(backGround);
		
		//搜索按钮响应函数
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				ListMessage lm=searchByBookName(keyWordField.getText());
				setTableData(lm,resultTableModel);				
				resultTable.validate();
			}
		});		
	}

	
	//根据书名查找书籍
	public ListMessage searchByBookName(String bookName){
		LibraryMessage lb=new LibraryMessage("SEARCH_BY_KEYWORDS","BOOK_NAME",bookName);
		socketClient.sendRequestToServer(lb);	
		ListMessage dataList=(ListMessage) socketClient.receiveDataFromServer();
		return dataList;
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
}


