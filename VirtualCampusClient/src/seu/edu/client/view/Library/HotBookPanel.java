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

public class HotBookPanel extends JPanel {
	private SocketClient socketClient = null;
	Vector headName =new Vector();
    Vector data = new Vector();	
    private JTable borrowedTable;
    String stuID="09015429";
    private ListMessage dataList=null;//存储搜索结果
    

	/**
	 * @author yyl
	 * 热门借阅推荐
	 * 显示借阅次数排在前30的书籍
	 */
	
	public HotBookPanel(SocketClient sc) {
		socketClient=sc;
		
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 814, 640);
		add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 99, 732, 507);
		panel.add(scrollPane);		
		
		String[] tableHead={"书名","作者","出版社","馆藏本数","可借本数"};
		for(int i=0;i<tableHead.length;i++){			
			headName.add(tableHead[i]);		
		}    
	    final DefaultTableModel borrowedTableModel=new DefaultTableModel(data, headName);
		borrowedTable = new JTable(borrowedTableModel);
		borrowedTable.setBounds(0, 0, 747, 573);
		scrollPane.setViewportView(borrowedTable);
		
		borrowedTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//若鼠标点击的行不为空，则弹出该书详细信息
				int t=borrowedTable.getSelectedRow();
				if(borrowedTable.getValueAt(borrowedTable.getSelectedRow(), 0)!=null){
					int row=borrowedTable.getSelectedRow();
					BorrowBookDialog borrowBookDialog=new BorrowBookDialog((LibraryMessage)(dataList.getDataList().get(row)), socketClient);
					//com.sun.awt.AWTUtilities.setWindowOpaque(borrowBookDialog, false);
					borrowBookDialog.setVisible(true);
				}
			}
		});

	
		JLabel backGround = new JLabel("");
		backGround.setBounds(0, 0, 814, 640);
		backGround.setIcon(new ImageIcon(HotBookPanel.class.getResource("/UI/Library/热门推荐 pane.png")));
		panel.add(backGround);
		
		getBorrowedBooks();
		setTableData(dataList,borrowedTableModel);
		borrowedTable.validate();
	}
	
	//获取前30图书
	public void getBorrowedBooks(){
		
		socketClient.sendRequestToServer(new LibraryMessage("GET_HOT_BOOKS", ""));
		ListMessage lm=(ListMessage)(socketClient.receiveDataFromServer());
		this.dataList=lm;
		
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




