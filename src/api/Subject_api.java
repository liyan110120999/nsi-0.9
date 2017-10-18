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

import people.DB;
import school.School_DB;
import school.School_model;

@WebServlet("/Subject_api")
public class Subject_api extends HttpServlet{

	private static final long serialVersionUID = 2159339373742529569L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String whereFrom = null;
		whereFrom = request.getParameter("whereFrom");
		
		if(whereFrom.equals("insert")){
			System.out.println("Subject_api:WF=====insert");
		 
			String SubjectName=request.getParameter("SubjectName");
			String Areas=request.getParameter("Areas");
			String Areas02=request.getParameter("Areas02");
			String Areas03=request.getParameter("Areas03");
			String Company=request.getParameter("Company");
			String SubjectLabel=request.getParameter("SubjectLabel");
			String Name=request.getParameter("Name");
			String Phone=request.getParameter("Phone");
			String Mail=request.getParameter("Mail");
			String SubjectIntroduction=request.getParameter("SubjectIntroduction");
			String DetailInstitution=request.getParameter("DetailInstitution");
			String Requirement=request.getParameter("Requirement");
			String UserMail=request.getParameter("UserMail");
//			String Load_time=request.getParameter("Load_time");
		
//			当前时间
				java.util.Date currentTime = new java.util.Date(); 
		    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    	String SubmitDate = formatter.format(currentTime);
	    	
//	    	待测试能否覆盖旧信息
	    	String sql="REPLACE INTO nsi_project_data (SubjectName,Areas,Areas02,Areas03,Company,SubjectLabel,Name,Phone,Mail,SubjectIntroduction,DetailInstitution,"
	    			+ "Requirement,UserMail,Load_time) " 					 		
				+ "VALUES ('"+SubjectName+"','"+Areas+"','"+Areas02+"','"+Areas03+"','"+Company+"','"+SubjectLabel+"','"+Name+"','"+Phone+"','"+Mail+"' "
						+ ",'"+SubjectIntroduction+"','"+DetailInstitution+"','"+Requirement+"','"+UserMail+"','"+SubmitDate+"' )";		
		    	
	    	DB.Insert(sql);
			String back="{msg:1}";
	    	String Callback = request.getParameter("Callback");//客户端请求参数	  	    	
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+back+")");
	    	
		}else if (whereFrom.equals("search")) {
			
			System.out.println("Subject_api:WF======search");
			
	    	Gson gson = new Gson();   	
	    	String SearchKey=request.getParameter("SearchKey");
	    	String sql=null;		
//			String sqlcount=null;
//			int countAllRS = 0;
			
//			分页参数 ：第几页、每页几个。默认值：1、20；
			Integer pageNum = request.getParameter("pageNum") != null && !request.getParameter("pageNum").equals("") ? Integer.parseInt(request.getParameter("pageNum")) : 1;
			Integer OnePageNum = request.getParameter("OnePageNum") != null && !request.getParameter("OnePageNum").equals("") ? Integer.parseInt(request.getParameter("OnePageNum")) : 20;

			int pageNumX=(pageNum-1)*OnePageNum;
			
			List<School_model> list = new ArrayList<School_model>();			
			 sql="SELECT * from NSI_SCHOOL_data WHERE CONCAT(IFNULL(`Id`,''),IFNULL(`School_name`,''),IFNULL(`School_EnglishName`,''),IFNULL(`Areas`,''),IFNULL(`Areas02`,''),IFNULL(`Founded_time`,'')) like '%"+SearchKey+"%' order by Load_Time DESC limit "+pageNumX+","+OnePageNum+"";
//					
			list=School_DB.Search(sql);
//		
	    	String jsonList =gson.toJson(list);

	    	String Callback = request.getParameter("Callback");//客户端请求参数
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+jsonList+")");
		}
		
    }
}
