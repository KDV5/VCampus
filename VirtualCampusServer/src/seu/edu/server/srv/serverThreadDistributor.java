package seu.edu.server.srv;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import seu.edu.common.*;

//接受来自服务端的请求并为其创建新进程
public class serverThreadDistributor {

	public serverThreadDistributor(){	
		try {
			ServerSocket ss= new ServerSocket(Constant.SERVER_PORT);
			System.out.println("等待连接");
			while(true){
					Socket socket;
					socket = ss.accept();
					
					System.out.println("检测到连接");
					
					new RequestThread(socket).start();
				}
		}catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}

	}	
}

