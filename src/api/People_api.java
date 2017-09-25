package api;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import people.DB;

@WebServlet("/People_api")
public class People_api extends HttpServlet{

	private static final long serialVersionUID = 5212268326198311857L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String whereFrom = null;
		whereFrom = request.getParameter("whereFrom");
		
		if(whereFrom.equals("insert")){
			System.out.println("People_api:WF=====insert");
//			判断用户标志
			String member_sign00=request.getParameter("member_sign");
		 
			String People_id=request.getParameter("People_id");
			String People_name=request.getParameter("People_name");
			String People_member=request.getParameter("People_member");
			String People_dueTime=request.getParameter("People_dueTime");
			String People_work=request.getParameter("People_work");
			String People_position=request.getParameter("People_position");
			String People_phone=request.getParameter("People_phone");
			String People_mail=request.getParameter("People_mail");
			String People_telephone=request.getParameter("People_telephone");
			String People_wechat=request.getParameter("People_wechat");
			String People_loadPeople=request.getParameter("People_loadPeople");
//			String People_loadTime=request.getParameter("People_loadTime");
			String People_address=request.getParameter("People_address");
			String People_introduction=request.getParameter("People_introduction");
			String People_remark=request.getParameter("People_remark");
			String People_ImgUrl=request.getParameter("People_ImgUrl");
				
//			当前时间
				java.util.Date currentTime = new java.util.Date(); 
		    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    	String SubmitDate = formatter.format(currentTime);

//			新增操作	
//	    	1、获取会员等级参数
//	    	2、需不需要审核？
//	    	3、插入新增标记
//	    	4、执行SQL
	    	System.out.println("member_sign:"+member_sign00);
	    	int member_sign=Integer.parseInt(member_sign00);
	    	String sql=null;
	
//			外部人员 存入审核数据库 @用户权限
//	    	区别：1、存入的数据库不一样，2、审核标记
//	    	新增标记
	    	int VerifySign=31;
			 if(member_sign<=7){			
//				sql="REPLACE INTO NSI_SCHOOL_data_verify (School_name,School_EnglishName,School_properties,"
//						+ "Areas,Areas02,Areas03,Founded_time,OperationState,School_system,Tuition,Website,Telephone,Inter_Course_Founded_time,Course,"
//				 		+ "Authentication,Course_evaluation,Student_Num,Student_Capacity,Graduated_Stu_Num,Stu_Dominant_nationality,Stu_Year_Investment,Club_Num,President_Country,Staff_Num,Teacher_Num,Foreign_Teacher_num,Teacher_Year_Investment,"
//				 		+ "Teacher_Retention,Teacher_Salary,Teacher_Stu_ratio,Covered_Area,Built_Area,Hardware,Investment,Remark,Load_People,Load_Time,VerifySign) "
//				 					 		
//						+ "VALUES ('"+School_name+"','"+School_EnglishName+"','"+School_properties+"',"
//						+ "'"+Areas+"','"+Areas02+"','"+Areas03+"','"+Founded_time+"','"+OperationState+"','"+School_system+"','"+Tuition+"','"+Website+"','"+Telephone+"','"+Inter_Course_Founded_time+"','"+Course+"',"
//						+ "'"+Authentication+"','"+Course_evaluation+"','"+Student_Num+"','"+Student_Capacity+"','"+Graduated_Stu_Num+"','"+Stu_Dominant_nationality+"','"+Stu_Year_Investment+"','"+Club_Num+"','"+President_Country+"','"+Staff_Num+"','"+Teacher_Num+"','"+Foreign_Teacher_num+"','"+Teacher_Year_Investment+"',"
//						+ "'"+Teacher_Retention+"','"+Teacher_Salary+"','"+Teacher_Stu_ratio+"','"+Covered_Area+"','"+Built_Area+"','"+Hardware+"','"+Investment+"','"+Remark+"','"+Load_People+"','"+SubmitDate+"','"+VerifySign+"')";			
			 }else{
//				内部员工免审核
//				sql="INSERT INTO NSI_SCHOOL_data (School_name,School_EnglishName,School_properties,"
//						+ "Areas,Areas02,Areas03,Founded_time,OperationState,School_system,Tuition,Website,Telephone,Inter_Course_Founded_time,Course,"
//				 		+ "Authentication,Course_evaluation,Student_Num,Student_Capacity,Graduated_Stu_Num,Stu_Dominant_nationality,Stu_Year_Investment,Club_Num,President_Country,Staff_Num,Teacher_Num,Foreign_Teacher_num,Teacher_Year_Investment,"
//				 		+ "Teacher_Retention,Teacher_Salary,Teacher_Stu_ratio,Covered_Area,Built_Area,Hardware,Investment,Remark,Load_People,Load_Time,VerifySign) "
//				 					 		
//						+ "VALUES ('"+School_name+"','"+School_EnglishName+"','"+School_properties+"',"
//						+ "'"+Areas+"','"+Areas02+"','"+Areas03+"','"+Founded_time+"','"+OperationState+"','"+School_system+"','"+Tuition+"','"+Website+"','"+Telephone+"','"+Inter_Course_Founded_time+"','"+Course+"',"
//						+ "'"+Authentication+"','"+Course_evaluation+"','"+Student_Num+"','"+Student_Capacity+"','"+Graduated_Stu_Num+"','"+Stu_Dominant_nationality+"','"+Stu_Year_Investment+"','"+Club_Num+"','"+President_Country+"','"+Staff_Num+"','"+Teacher_Num+"','"+Foreign_Teacher_num+"','"+Teacher_Year_Investment+"',"
//						+ "'"+Teacher_Retention+"','"+Teacher_Salary+"','"+Teacher_Stu_ratio+"','"+Covered_Area+"','"+Built_Area+"','"+Hardware+"','"+Investment+"','"+Remark+"','"+Load_People+"','"+SubmitDate+"','"+VerifySign+"')";			
			 }
			
			DB.Insert(sql);			
			String back="{msg:1}";
	    	String Callback = request.getParameter("Callback");//客户端请求参数	  	    	
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+back+")");
	    	
	    	
		}else if(whereFrom.equals("delete")) {
			
		}else{
			System.out.println("school_api：没收到whereFrom参数！！！");
		}
    }
}
