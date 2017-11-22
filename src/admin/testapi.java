package admin;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Model;

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
	    	Gson gson = new Gson();   	
	    	
	    	String WechatCode=request.getParameter("WechatCode");
	    	String appid ="wxeec22e42c2dd116e";
	    	String secret ="bcd85602ec69d29a2334221378185dfc";
	    	
	    	String requestUrl ="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appid+"&secret="+secret+"&code="+WechatCode+"&grant_type=authorization_code";
//	    	΢��ɨ��ʵ�ֺ��� ��ͨ��code ��ȡ ΢�� ID
	    	String aaa= Model.WechatHttpRequest(requestUrl, "POST", "");
//	    	�ж��û���
//	    	��û�д�ID�����ؽ������-2,-1,1��
	    	
	    	
	    	System.out.println("���ֵ��"+aaa);
	    	String jsonList ="{msg:success}";
	    	
	    	String Callback = request.getParameter("Callback");//�ͻ����������
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+jsonList+")");	
	    	
		}else{
			System.out.println("NO aaa");
		}
	}
}
