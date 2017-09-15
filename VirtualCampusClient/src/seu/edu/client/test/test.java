package seu.edu.client.test;


import seu.edu.client.view.Library.UserView;
import seu.edu.common.SocketClient;
import seu.edu.common.message.LibraryMessage;

public class test {
	public static void main(String[] args){
		SocketClient socketClient=new SocketClient();
		UserView mw=new UserView(socketClient);
		mw.setVisible(true);


	}
}
