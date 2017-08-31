package cn.seu.edu.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import cn.seu.edu.LibraryThread.*;
import cn.seu.edu.bankThread.*;
import cn.seu.edu.eduThread.*;
import cn.seu.edu.feedbackThread.*;
import cn.seu.edu.infoThread.*;
import cn.seu.edu.loginThread.*;
import cn.seu.edu.mailThread.*;
import cn.seu.edu.message.RequestData;
import cn.seu.edu.shopThread.*;

public class ServerReaderThread extends Thread {

	private Socket socket;
 //	private Server server;
    ObjectOutputStream oos = null;

	public ServerReaderThread(Socket socket){
		this.socket = socket;
//		this.server = server;
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public  void run(){		
		try {
			boolean f = true;
			RequestData reqData = null;
			InputStream is = socket.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			while(f){
				reqData = (RequestData) ois.readObject();
				//��¼
				if("login".equals(reqData.getRequest())){
					new LoginResponseThread(this,reqData).run();
				}
				//ע��
				else if("register".equals(reqData.getRequest())){
					new RegisterResponseThread(this,reqData).run();
				}
				//��ѯ���п�һ��ͨ���
				else if("balance".equals(reqData.getRequest())){
					new BalanceResponseThread(this,reqData).run();
				}
				//һ��ͨ��ֵ
				else if("charge".equals(reqData.getRequest())){
					new ChargeResponseThread(this,reqData).run();
				}
				//�޸����п���һ��ͨ����
				else if("changePass".equals(reqData.getRequest())){
					new ChangePassResponseThread(this,reqData).run();
				}
				//������Ʒ
				else if("shopSearch".equals(reqData.getRequest())){
					new ShopSearchResponseThread(this,reqData).run();
				}
				//�������20����Ʒ
				else if("OutputNewGoods".equals(reqData.getRequest())){
					new OutputNewGoodsResponseThread(this,reqData).run();
				}
				//������Ʒ
				else if("buyGoods".equals(reqData.getRequest())){
					new BuyGoodsResponseThread(this,reqData).run();
				}
				//�˶����п�����
				else if("CheckCardPassword".equals(reqData.getRequest())){
					new CheckCardPasswordResponseThread(this,reqData).run();
				}
				//���Ѽ�¼��ѯ
				else if("shopHistory".equals(reqData.getRequest())){
					new ShopHistoryResponseThread(this,reqData).run();
				}
				//�����Ʒ
				else if("AddNewGoods".equals(reqData.getRequest())){
					new AddNewGoodsResponseThread(this,reqData).run();
				}
				//ɾ����Ʒ
				else if("DeleteGoods".equals(reqData.getRequest())){
					new DeleteGoodsResponseThread(this,reqData).run();
				}
				//�޸���Ʒ��Ϣ
				else if("UpdateGoods".equals(reqData.getRequest())){
					new UpdateGoodsResponseThread(this,reqData).run();
				}
				//�ύͶ��
				else if("feedback".equals(reqData.getRequest())){
					new FeedbackResponseThread(this,reqData).run();
				}
				//�ҵ�Ͷ�߼�¼��ѯ
				else if("myFeedback".equals(reqData.getRequest())){
					new OutputMyFeedbackResponseThread(this,reqData).run();
				}
				//����Ա�鿴Ͷ��
				else if("administratorCheckFeedback".equals(reqData.getRequest())){
					new AdministratorCheckFeedbackResponseThread(this,reqData).run();
				}
				//����Ͷ��
				else if("DealWithFeedback".equals(reqData.getRequest())){
					new DealWithFeedbackResponseThread(this,reqData).run();
					
				}
				//δ���ʼ�����ѯ
				else if("MyUnreadMailNum".equals(reqData.getRequest())){
					new MyUnreadMailNumResponseThread(this,reqData).run();
				}
				//����ռ����б�
				else if("OutputMyRecieveMail".equals(reqData.getRequest())){
					new MyRecieveMailResponseThread(this,reqData).run();
				}
				//����������б�
				else if("OutputMySendMail".equals(reqData.getRequest())){
					new MySendMailResponseThread(this,reqData).run();
				}
				//�Ķ��ʼ�
				else if("readMyRecieveMail".equals(reqData.getRequest())){
					new ReadMyRecieveMailResponseThread(this,reqData).run();
				}
				//������ϵ��
				else if("queryLinkman".equals(reqData.getRequest())){
					new QueryLinkmanResponseThread(this,reqData).run();
				}
				//�����ʼ�
				else if("sendMail".equals(reqData.getRequest())){
					new SendMailResponseThread(this,reqData).run();
				}
				//ʵʱ��ѡ�α�
				else if("everChoose".equals(reqData.getRequest())){
					new EverChooseResponseThread(this,reqData).run();
				}
				//ȡ��ѡ��
				else if("quitChooseCourse".equals(reqData.getRequest())){
					new QuitChooseCourseResponseThread(this,reqData).run();
				}
				//��ѯ��ѡ�α�
				else if("CourseCanBeChoose".equals(reqData.getRequest())){
					new CourseCanBeChooseResponseThread(this,reqData).run();
				}
				//ȷ��ѡ��
				else if("sureChooseCourse".equals(reqData.getRequest())){
					new SureChooseCourseResponseThread(this,reqData).run();
				}
				//ѧ���α��ѯ
				else if("outputStuCourList".equals(reqData.getRequest())){
					new OutputStuCourResponseThread(this,reqData).run();
				}
				//ѧ���ɼ���ѯ
				else if("queryStuGrade".equals(reqData.getRequest())){
					new QueryStuGradeResponseThread(this,reqData).run();
				}
				//�ڿοα��ѯ
				else if("OutputTchCourList".equals(reqData.getRequest())){
					new OutputTchCourListResponseThread(this,reqData).run();
				}
				//�ڿΰ༶����
				else if("QueryTchCourse".equals(reqData.getRequest())){
					new QueryTchCourseResponseThread(this,reqData).run();
				}
				//��ʾѧ���ɼ���
				else if("QueryTchCourseGrade".equals(reqData.getRequest())){
					new QueryTchCourseGradeResponseThread(this,reqData).run();
				}
				//¼��ѧ���ɼ�
				else if("UpdateStuGrade".equals(reqData.getRequest())){
					new UpdateStuGradeResponseThread(this,reqData).run();
				}
				//����ѧ��/��ʦ��Ϣ
				else if("SaveStuInfo".equals(reqData.getRequest())){
					new SaveStuInfoResponseThread(this,reqData).run();
				}
				//����ID����ѧ��/��ʦ��Ϣ
				else if("QueryNameByID".equals(reqData.getRequest())){
					new QueryNameByIDResponseThread(this,reqData).run();
				}
				//��ѯѧ����Ϣ
				else if("QueryStuInfo".equals(reqData.getRequest())){
					new QueryStuInfoResponseThread(this,reqData).run();
				}
				//�������ѧ����Ϣ
				else if("OutputAllStuInfo".equals(reqData.getRequest())){
					new OutputAllStuInfoResponseThread(this,reqData).run();
				}
				//����ѧ��/��ʦ��Ϣ
				else if("InfoInsert".equals(reqData.getRequest())){
					new InfoInsertResponseThread(this,reqData).run();
				}
				//�����������ͼƬ
				else if("SendPicToServer".equals(reqData.getRequest())){
     				new SendPicToServerResponseThread(this,reqData).run();
				}
				//����̵�ͼƬ
				else if("GetPicFromServer".equals(reqData.getRequest())){
					new GetPicFromServerResponseThread(this,reqData).run();
				}
				//����ͼ��
				else if("Search".equals(reqData.getRequest())){
					new SearchThread(this,reqData).run();
				}
				//���˵�ǰ����ͼ��
				else if("personQuery".equals(reqData.getRequest())){
					new PersonQueryThread(this,reqData).run();
				}
				//����
				else if("return".equals(reqData.getRequest())){
					new ReturnThread(this,reqData).run();
				}
				//����
				else if("lend".equals(reqData.getRequest())){
					new LendThread(this,reqData).run();		
				}
				//ͼ�����
				else if("delete".equals(reqData.getRequest())){
					new DeleteThread(this,reqData).run();
				}
				//ͼ�����
				else if("insert".equals(reqData.getRequest())){
					new InsertThread(this,reqData).run();
				}
				//ͼ�鵽������
				else if("libMail".equals(reqData.getRequest())){
					new Taskthread(this,reqData).run();
				}
				/*else if("buySearch".equals(reqData.getRequest())){
					new BuySearchResponseThread(this,reqData).run();
				}*/

				//�ͻ��˽���
				else if("exit".equals(reqData.getRequest())){
					ois.close();
					f = false;
					socket.close();
				}
				else if("changeLoginPass".equals(reqData.getRequest())){
					new ChangeLoginPassResponseThread(this,reqData).run();
				}

			}
			
			
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	//��������ͻ��˴�����
	public void sendDataToClient(RequestData rd){
	    try {
	    	oos.writeObject(rd);
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
   	 
	}
		
	
}
