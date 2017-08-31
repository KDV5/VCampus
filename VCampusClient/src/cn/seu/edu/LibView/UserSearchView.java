package cn.seu.edu.LibView;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import cn.seu.edu.Client.Client;
import cn.seu.edu.message.LibraryData;

public class UserSearchView{
    Client client;
    //JPanel f=new JPanel();
	public UserSearchView(Client client,JPanel jp) throws SQLException{
		this.client=client;

		Search s=new Search(client,jp);
		//f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	}

				