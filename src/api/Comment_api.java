package api;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import comment.Comment_DB;
import comment.Comment_model;
import people.DB;

@WebServlet("/Comment_api")
public class Comment_api extends HttpServlet{
	private static final long serialVersionUID = -3177340854559868488L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String whereFrom = null;
		whereFrom = request.getParameter("whereFrom");
		
//		初始评论列表
	   if(whereFrom.equals("CommentList")){
			System.out.println("Comment_api:WF======CommentList");			
		 	Gson gson = new Gson();   	
	    	String SubjectId=request.getParameter("SubjectId");			
			List<Comment_model> list = new ArrayList<Comment_model>();		
			String sql="SELECT * from nsi_comment WHERE SubjectId = '"+SubjectId+"' ;";						
			list=Comment_DB.Search(sql);
	    	String jsonList =gson.toJson(list);
	    	String Callback = request.getParameter("Callback");//客户端请求参数
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+jsonList+")");
//	    发表评论	
	   }else if(whereFrom.equals("SendComment")){
		   System.out.println("Comment_api:WF======SendComment");
		   String Reviewer=request.getParameter("Reviewer");	
		   String Classify=request.getParameter("Classify");	
		   String SubjectId=request.getParameter("SubjectId");	
		   String Content=request.getParameter("Content");
		   
		   String VerifySign="41";
		   String Thumbs_up ="0";
		   String Thumbs_down ="0";
		   
//			当前时间，单位：日
				java.util.Date currentTime = new java.util.Date(); 
		    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    	String ReleaseTime = formatter.format(currentTime);
//			当前时间，单位：秒  	
		    	java.util.Date currentTimeSecond = new java.util.Date(); 
		    	SimpleDateFormat formatterSecond = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	String ReleaseTimeSecond = formatterSecond.format(currentTimeSecond);
		   
	    System.out.println("检测时间格式："+ReleaseTime+ReleaseTimeSecond);
		   String sql="INSERT INTO NSI_comment (Reviewer,Classify,SubjectId,ReleaseTime,ReleaseTimeSecond,Thumbs_up,Thumbs_down,Content,VerifySign)"	
				   +" VALUES ('"+Reviewer+"','"+Classify+"','"+SubjectId+"','"+ReleaseTime+"','"+ReleaseTimeSecond+"','"+Thumbs_up+"','"+Thumbs_down+"','"+Content+"','"+VerifySign+"') ";
			
		   DB.Insert(sql);		
		   String back="{msg:1}";
		   String Callback = request.getParameter("Callback");//客户端请求参数	  	    	
		   response.setContentType("text/html;charset=UTF-8");  
		   response.getWriter().write(Callback+"("+back+")"); 
//		 点赞  
	   }else if(whereFrom.equals("Thumbs_up")){
		   System.out.println("Comment_api:WF======Thumbs_up");
		   String Id=request.getParameter("Id");		   
		   String sql="UPDATE NSI_comment SET Thumbs_up= Thumbs_up + 1 where Id='"+Id+"' ;";						
		   DB.Insert(sql);		
		   String back="{msg:1}";
		   String Callback = request.getParameter("Callback");//客户端请求参数	  	    	
		   response.setContentType("text/html;charset=UTF-8");  
		   response.getWriter().write(Callback+"("+back+")"); 
//		  踩 
	   }else if(whereFrom.equals("Thumbs_down")){
		   System.out.println("Comment_api:WF======Thumbs_down");
		   String Id=request.getParameter("Id");		   
		   String sql="UPDATE NSI_comment SET Thumbs_down = Thumbs_down+1 where Id='"+Id+"' ;";
		   DB.Insert(sql);		
		   String back="{msg:1}";
		   String Callback = request.getParameter("Callback");//客户端请求参数	  	    	
		   response.setContentType("text/html;charset=UTF-8");  
		   response.getWriter().write(Callback+"("+back+")");
		  
	   }
    }
}
