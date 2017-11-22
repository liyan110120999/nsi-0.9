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
    	System.out.println("get 请求");
    	this.doPost(request,response);
    	
}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("post 请求");
		
		String whereFrom = null;
		whereFrom = request.getParameter("whereFrom");
		
		
		if(whereFrom.equals("test")){
			String back="{\"name\":\"Brett\"}";
			System.out.println(back);
			
			String Callback = request.getParameter("Callback");//客户端请求参数
			
			
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
	    	
//	    	保存路径
	    	String loadPath = "C:" + File.separator+"upImage"+ File.separator+"test"+File.separator;
	    	boolean aa= model.Model.GenerateImage(Base64,loadPath);
	    	
	    	System.out.println("模块运行之后");
	    	System.out.println("test,upload:"+i);
	    	String jsonList ="success";
	    	
	    	String Callback = request.getParameter("Callback");//客户端请求参数
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+jsonList+")");
		
//	    	测试微信扫码
		}else if(whereFrom.equals("testWechat")){
	    	
			System.out.println("test_api:WF======testWechat");	
	    	Gson gson = new Gson();   	
	    	
	    	String WechatCode=request.getParameter("WechatCode");
	    	String appid ="wxeec22e42c2dd116e";
	    	String secret ="bcd85602ec69d29a2334221378185dfc";
	    	
	    	String requestUrl ="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appid+"&secret="+secret+"&code="+WechatCode+"&grant_type=authorization_code";
//	    	微信扫码实现函数 ：通过code 获取 微信 ID
	    	String aaa= Model.WechatHttpRequest(requestUrl, "POST", "");
//	    	判断用户表，
//	    	有没有此ID，返回结果，（-2,-1,1）
	    	
	    	
	    	System.out.println("输出值："+aaa);
	    	String jsonList ="{msg:success}";
	    	
	    	String Callback = request.getParameter("Callback");//客户端请求参数
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+jsonList+")");	
	    	
		}else{
			System.out.println("NO aaa");
		}
	}
}
