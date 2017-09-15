package seu.edu.client.test;


import seu.edu.MessageDialog.MessageDialog;
import seu.edu.client.view.Library.MainWindow;
import seu.edu.common.SocketClient;
import seu.edu.common.message.LibraryMessage;

public class test {
	public static void main(String[] args){
//		SocketClient socketClient=new SocketClient();
//		MainWindow mw=new MainWindow(socketClient);
//		mw.setVisible(true);
		
		MessageDialog md=new MessageDialog();
		System.out.println(md.showMessage("SUCCEED","aaa"));

	}
}
