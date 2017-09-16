package seu.edu.client.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Window.Type;

public class SearchFrame extends JFrame
{
	private static JPanel contentPane;	
	private static SearchFrame instance =new SearchFrame();	
	private static JTextField textField;
	
	private SearchFrame()
	{
		setType(Type.UTILITY);
		setResizable(false);
		try
		{
			//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(0, 0, 700, 550);
			contentPane = new JPanel();
			contentPane.setBackground(Color.WHITE);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			textField = new JTextField();			
			textField.setEditable(false);
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			textField.setFont(new Font("свт╡", Font.PLAIN, 16));
			textField.setBounds(205, 122, 222, 81);
			contentPane.add(textField);
			textField.setColumns(10);
			
			setLocationRelativeTo(null);			
			this.setVisible(true);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static void getInstance(String str)
	{
		set_search(str);
		instance.setVisible(true);
	}
	
	private static void set_search(String str)
	{
		textField.setText(str);
	}
}
