package seu.edu.server.test;

import javax.swing.SwingUtilities;
import seu.edu.server.view.ServerFrame;

public class VServerTest
{

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new ServerFrame();
			}
		});
	}

}
