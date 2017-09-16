package seu.edu.client.view.Library;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JPopupMenu;

import com.only.OnlyPopupMenu;

import seu.edu.MessageDialog.MessageDialog;
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
    private MessageDialog md=new MessageDialog();
    private DefaultTableModel borrowedTableModel=null;
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
	    borrowedTableModel=new DefaultTableModel(data, headName);
		borrowedTable = new JTable(borrowedTableModel);
		borrowedTable.setBounds(0, 0, 747, 573);
		
		//为借书表格添加点击相应
		borrowedTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//若鼠标点击的行不为空，则弹出该书详细信息
				int t= borrowedTable.getSelectedRow();
				if(borrowedTable.getValueAt(borrowedTable.getSelectedRow(), 0)!=null){
					int row=borrowedTable.getSelectedRow();
					String bookName=(String) borrowedTable.getValueAt(row, 2);
					if(md.showMessage("YES_NO", "您确定要归还 "+bookName+" 吗？")){
						LibraryMessage lm=new LibraryMessage("RETURN_BOOK",(String) borrowedTable.getValueAt(row, 1), stuID);
						socketClient.sendRequestToServer(lm);
						LibraryMessage result=(LibraryMessage)socketClient.receiveDataFromServer();
						if(result.getOperResult().equals("RETURN_BOOK_SUCCEED")){
							md.showMessage("SUCCEED", "还书成功");
						}else{
							md.showMessage("ERROR", "还书失败");
						}
					}else{
						
					}
//					BorrowBookDialog borrowBookDialog=new BorrowBookDialog((LibraryMessage)(dataList.getDataList().get(row)), socketClient);
//					//com.sun.awt.AWTUtilities.setWindowOpaque(borrowBookDialog, false);
//					borrowBookDialog.setVisible(true);
//					socketClient.sendRequestToServer(libraryMessage);	
//					dataList=(ListMessage) socketClient.receiveDataFromServer();	
//					JOptionPane.showMessageDialog(null,resultTable.getValueAt(row, 1));
				}
			}
		});
		
		scrollPane.setViewportView(borrowedTable);		
	
		JLabel backGround = new JLabel("");
		backGround.setBounds(0, 0, 814, 640);
		backGround.setIcon(new ImageIcon(BorrowedBookPanel.class.getResource("/Image/Library/UI/PanelBackGround.png")));
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
				row.add(((LibraryMessage)(rs.getDataList().get(i))).getBookID());
				row.add(((LibraryMessage)(rs.getDataList().get(i))).getBookName());
				row.add(((LibraryMessage)(rs.getDataList().get(i))).getAuther());
				row.add(((LibraryMessage)(rs.getDataList().get(i))).getPublisher());
				row.add(((LibraryMessage)(rs.getDataList().get(i))).getLendDate());
				//data.add(row);		
				dtm.addRow(row);
			}

	}
	
	//刷新该面板内容
	public void refresh(){
		setTableData(getBorrowedBooks(stuID),borrowedTableModel);
		borrowedTable.validate();
	}
}


