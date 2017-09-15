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
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.TableModel;

public class BorrowedBookPanel extends JPanel {
	private SocketClient socketClient = null;
	Vector headName =new Vector();
    Vector data = new Vector();	
    private JTable borrowedTable;
    String stuID="09015429";


	/**
	 * Create the panel.
	 */
	
	public BorrowedBookPanel(SocketClient sc) {
		socketClient=sc;
		
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 814, 640);
		add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 34, 747, 573);
		panel.add(scrollPane);		
		
		String[] tableHead={"书籍编号","书名","作者","出版社","借书日期",""};
		for(int i=0;i<tableHead.length;i++){			
			headName.add(tableHead[i]);		
		}    
	    final DefaultTableModel borrowedTableModel=new DefaultTableModel(data, headName);
		borrowedTable = new JTable(borrowedTableModel);
		borrowedTable.setBounds(0, 0, 747, 573);
		scrollPane.setViewportView(borrowedTable);

	
		JLabel backGround = new JLabel("");
		backGround.setBounds(0, 0, 814, 640);
		backGround.setIcon(new ImageIcon(BorrowedBookPanel.class.getResource("/UI/Library/PanelBackGround.png")));
		panel.add(backGround);
		
		setTableData(getBorrowedBooks(stuID),borrowedTableModel);
		borrowedTable.validate();
	}
	
	//获取已借图书
	public ListMessage getBorrowedBooks(String stuID){
		
		LibraryMessage lm=new LibraryMessage("GET_BORROWED",stuID);
		socketClient.sendRequestToServer(lm);
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


