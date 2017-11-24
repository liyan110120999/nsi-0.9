package admin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Model;
import people.DB;
import user.User_DB;
import user.User_model;

@WebServlet("/testapi")
public class testapi extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("get ����");
    	this.doPost(request,response);
    	
}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("post ����");
		
		String whereFrom = null;
		whereFrom = request.getParameter("whereFrom");
		
		
		if(whereFrom.equals("test")){
			String back="{\"name\":\"Brett\"}";
			System.out.println(back);
			
			String Callback = request.getParameter("Callback");//�ͻ����������
			
			
//			jsonpCallback
			System.out.println(Callback);
			
			response.getWriter().write(Callback+"("+back+")");
			
		}else if(whereFrom.equals("testBase64")){
	    	
			System.out.println("test_api:WF======testBase64");	
	    	Gson gson = new Gson();   	
	
	    	String Base64=request.getParameter("Base64");
//	    	String UserMail=request.getParameter("UserMail");
//	    	String User_TureName=request.getParameter("User_TureName");
	    	String sql=request.getParameter("sql");
	    	
	    	int i=-2;
//	    	int i=model.Model.UpBase64(Base64,request);
	    	
//	    	����·��
	    	String loadPath = "C:" + File.separator+"upImage"+ File.separator+"test"+File.separator;
	    	boolean aa= model.Model.GenerateImage(Base64,loadPath);
	    	
	    	System.out.println("ģ������֮��");
	    	System.out.println("test,upload:"+i);
	    	String jsonList ="success";
	    	
	    	String Callback = request.getParameter("Callback");//�ͻ����������
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+jsonList+")");
		
//	    	����΢��ɨ��
		}else if(whereFrom.equals("testWechat")){
	    	
			System.out.println("test_api:WF======testWechat");	
	      		    	
	    	String WechatCode=request.getParameter("WechatCode");
	    	String appid ="wxeec22e42c2dd116e";
	    	String secret ="bcd85602ec69d29a2334221378185dfc";
	    	
	    	String requestUrl ="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appid+"&secret="+secret+"&code="+WechatCode+"&grant_type=authorization_code";
//	    	΢��ɨ��ʵ�ֺ��� ��ͨ��code ��ȡ ΢�� ID
	    	String aaa= Model.WechatHttpRequest(requestUrl, "POST", "");
//	    	�ж��û���
//	    	��û�д�ID�����ؽ������-2,-1,1��
	    	String sql="select * from nsi_user where WechatId='"+aaa+"'; ";
	    	int count=0;
	    	try {
	    		count= DB.count(sql);
	    		System.out.println("������������");
			} catch (Exception e) {
				System.out.println("δ��ѯ����΢��ID");
			}
	    	
	    	
//	    	��ID ���� �û�list
	    	if(count>=1) {
	    		List<User_model> list = new ArrayList<User_model>();
	    		list=User_DB.Search(sql);
		    	String UserMail=list.get(0).getName();
		    	
//				Ĭ��ֵΪ-2 ��ʾû�и��û�
				int member_sign=-2;
				int UserVerifyCode=000000;
				String User_TureName="��";
				Model model=new Model();								
//					��ȡ�û���־λ
					member_sign=model.queryByName(UserMail).getMember_sign();
					System.out.println("User_api:�û���־λ��"+member_sign);						
					UserVerifyCode=UserMail.length()*member_sign+987654;				
					User_TureName =model.queryByName(UserMail).getUser_TureName();
	
				String back="{\"member_sign\":\""+member_sign+"\","
							+ "\"username\":\""+UserMail+"\","
							+ "\"User_TureName\":\""+User_TureName+"\","
							+ "\"UserVerifyCode\":\""+UserVerifyCode+"\"}";

		    	String Callback = request.getParameter("Callback");//�ͻ����������	  	    	
		    	response.setContentType("text/html;charset=UTF-8");  
		    	response.getWriter().write(Callback+"("+back+")");
		    	
//		  	û��ID
	    	}else {
	    		System.out.println("�û�δ�󶨣�����");
	    		String jsonList ="{msg:-1,WechatId:\""+aaa+"\"}";	    	
		    	String Callback = request.getParameter("Callback");//�ͻ����������
		    	response.setContentType("text/html;charset=UTF-8");  
		    	response.getWriter().write(Callback+"("+jsonList+")");
		    	
		    	
		    	System.out.println("jsonList��ֵ��"+jsonList);
	    	}
	    	
	    		    	
//	    	System.out.println("���ֵ��"+aaa);
//	    	String jsonList ="{msg:success}";
//	    	
//	    	String Callback = request.getParameter("Callback");//�ͻ����������
//	    	response.setContentType("text/html;charset=UTF-8");  
//	    	response.getWriter().write(Callback+"("+jsonList+")");	
	    	System.out.println("testWechat:�������");
	    	
		}else{
			System.out.println("NO aaa");
		}
	}
}
