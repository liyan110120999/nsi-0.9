package admin;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

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
			
		}else{
			System.out.println("NO aaa");
		}
	}
}
