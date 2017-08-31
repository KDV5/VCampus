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
				//登录
				if("login".equals(reqData.getRequest())){
					new LoginResponseThread(this,reqData).run();
				}
				//注册
				else if("register".equals(reqData.getRequest())){
					new RegisterResponseThread(this,reqData).run();
				}
				//查询银行卡一卡通余额
				else if("balance".equals(reqData.getRequest())){
					new BalanceResponseThread(this,reqData).run();
				}
				//一卡通充值
				else if("charge".equals(reqData.getRequest())){
					new ChargeResponseThread(this,reqData).run();
				}
				//修改银行卡或一卡通密码
				else if("changePass".equals(reqData.getRequest())){
					new ChangePassResponseThread(this,reqData).run();
				}
				//搜索商品
				else if("shopSearch".equals(reqData.getRequest())){
					new ShopSearchResponseThread(this,reqData).run();
				}
				//输出最新20种商品
				else if("OutputNewGoods".equals(reqData.getRequest())){
					new OutputNewGoodsResponseThread(this,reqData).run();
				}
				//购买商品
				else if("buyGoods".equals(reqData.getRequest())){
					new BuyGoodsResponseThread(this,reqData).run();
				}
				//核对银行卡密码
				else if("CheckCardPassword".equals(reqData.getRequest())){
					new CheckCardPasswordResponseThread(this,reqData).run();
				}
				//消费记录查询
				else if("shopHistory".equals(reqData.getRequest())){
					new ShopHistoryResponseThread(this,reqData).run();
				}
				//添加商品
				else if("AddNewGoods".equals(reqData.getRequest())){
					new AddNewGoodsResponseThread(this,reqData).run();
				}
				//删除商品
				else if("DeleteGoods".equals(reqData.getRequest())){
					new DeleteGoodsResponseThread(this,reqData).run();
				}
				//修改商品信息
				else if("UpdateGoods".equals(reqData.getRequest())){
					new UpdateGoodsResponseThread(this,reqData).run();
				}
				//提交投诉
				else if("feedback".equals(reqData.getRequest())){
					new FeedbackResponseThread(this,reqData).run();
				}
				//我的投诉记录查询
				else if("myFeedback".equals(reqData.getRequest())){
					new OutputMyFeedbackResponseThread(this,reqData).run();
				}
				//管理员查看投诉
				else if("administratorCheckFeedback".equals(reqData.getRequest())){
					new AdministratorCheckFeedbackResponseThread(this,reqData).run();
				}
				//处理投诉
				else if("DealWithFeedback".equals(reqData.getRequest())){
					new DealWithFeedbackResponseThread(this,reqData).run();
					
				}
				//未读邮件数查询
				else if("MyUnreadMailNum".equals(reqData.getRequest())){
					new MyUnreadMailNumResponseThread(this,reqData).run();
				}
				//输出收件箱列表
				else if("OutputMyRecieveMail".equals(reqData.getRequest())){
					new MyRecieveMailResponseThread(this,reqData).run();
				}
				//输出发件箱列表
				else if("OutputMySendMail".equals(reqData.getRequest())){
					new MySendMailResponseThread(this,reqData).run();
				}
				//阅读邮件
				else if("readMyRecieveMail".equals(reqData.getRequest())){
					new ReadMyRecieveMailResponseThread(this,reqData).run();
				}
				//搜索联系人
				else if("queryLinkman".equals(reqData.getRequest())){
					new QueryLinkmanResponseThread(this,reqData).run();
				}
				//发送邮件
				else if("sendMail".equals(reqData.getRequest())){
					new SendMailResponseThread(this,reqData).run();
				}
				//实时已选课表
				else if("everChoose".equals(reqData.getRequest())){
					new EverChooseResponseThread(this,reqData).run();
				}
				//取消选课
				else if("quitChooseCourse".equals(reqData.getRequest())){
					new QuitChooseCourseResponseThread(this,reqData).run();
				}
				//查询可选课表
				else if("CourseCanBeChoose".equals(reqData.getRequest())){
					new CourseCanBeChooseResponseThread(this,reqData).run();
				}
				//确认选课
				else if("sureChooseCourse".equals(reqData.getRequest())){
					new SureChooseCourseResponseThread(this,reqData).run();
				}
				//学生课表查询
				else if("outputStuCourList".equals(reqData.getRequest())){
					new OutputStuCourResponseThread(this,reqData).run();
				}
				//学生成绩查询
				else if("queryStuGrade".equals(reqData.getRequest())){
					new QueryStuGradeResponseThread(this,reqData).run();
				}
				//授课课表查询
				else if("OutputTchCourList".equals(reqData.getRequest())){
					new OutputTchCourListResponseThread(this,reqData).run();
				}
				//授课班级管理
				else if("QueryTchCourse".equals(reqData.getRequest())){
					new QueryTchCourseResponseThread(this,reqData).run();
				}
				//显示学生成绩表
				else if("QueryTchCourseGrade".equals(reqData.getRequest())){
					new QueryTchCourseGradeResponseThread(this,reqData).run();
				}
				//录入学生成绩
				else if("UpdateStuGrade".equals(reqData.getRequest())){
					new UpdateStuGradeResponseThread(this,reqData).run();
				}
				//保存学生/教师信息
				else if("SaveStuInfo".equals(reqData.getRequest())){
					new SaveStuInfoResponseThread(this,reqData).run();
				}
				//根据ID查找学生/教师信息
				else if("QueryNameByID".equals(reqData.getRequest())){
					new QueryNameByIDResponseThread(this,reqData).run();
				}
				//查询学生信息
				else if("QueryStuInfo".equals(reqData.getRequest())){
					new QueryStuInfoResponseThread(this,reqData).run();
				}
				//输出所有学生信息
				else if("OutputAllStuInfo".equals(reqData.getRequest())){
					new OutputAllStuInfoResponseThread(this,reqData).run();
				}
				//插入学生/教师信息
				else if("InfoInsert".equals(reqData.getRequest())){
					new InfoInsertResponseThread(this,reqData).run();
				}
				//向服务器发送图片
				else if("SendPicToServer".equals(reqData.getRequest())){
     				new SendPicToServerResponseThread(this,reqData).run();
				}
				//获得商店图片
				else if("GetPicFromServer".equals(reqData.getRequest())){
					new GetPicFromServerResponseThread(this,reqData).run();
				}
				//搜索图书
				else if("Search".equals(reqData.getRequest())){
					new SearchThread(this,reqData).run();
				}
				//个人当前所借图书
				else if("personQuery".equals(reqData.getRequest())){
					new PersonQueryThread(this,reqData).run();
				}
				//还书
				else if("return".equals(reqData.getRequest())){
					new ReturnThread(this,reqData).run();
				}
				//借书
				else if("lend".equals(reqData.getRequest())){
					new LendThread(this,reqData).run();		
				}
				//图书出库
				else if("delete".equals(reqData.getRequest())){
					new DeleteThread(this,reqData).run();
				}
				//图书入库
				else if("insert".equals(reqData.getRequest())){
					new InsertThread(this,reqData).run();
				}
				//图书到期提醒
				else if("libMail".equals(reqData.getRequest())){
					new Taskthread(this,reqData).run();
				}
				/*else if("buySearch".equals(reqData.getRequest())){
					new BuySearchResponseThread(this,reqData).run();
				}*/

				//客户端结束
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
	
	//服务器向客户端传数据
	public void sendDataToClient(RequestData rd){
	    try {
	    	oos.writeObject(rd);
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
   	 
	}
		
	
}
