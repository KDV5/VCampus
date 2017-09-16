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

public class FeedBackUserPanel extends JPanel {
	private SocketClient socketClient = null;    

	/**
	 * @author yyl
	 * 反馈面板用户端
	 * 显示借阅次数排在前30的书籍
	 */
	
	public FeedBackUserPanel(SocketClient sc) {
		socketClient=sc;
		
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 814, 640);
		add(panel);
		panel.setLayout(null);
	}
		

}




