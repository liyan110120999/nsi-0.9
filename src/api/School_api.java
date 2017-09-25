package api;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;

import people.DB;
import school.School_Areas_model;
import school.School_DB;
import school.School_model;

@WebServlet("/School_api")
public class School_api extends HttpServlet{
	
	private static final long serialVersionUID = 2251864747506072591L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
        this.doPost(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
//    	请求头-待删除
    		response.setHeader("Access-Control-Allow-Origin", "*"); //解决跨域访问报错 

//    	当前时间
//    	long startTime = System.currentTimeMillis();   	
    	String whereFrom = null;
			whereFrom = request.getParameter("whereFrom");

//  Lee pass 01
		if(whereFrom.equals("insert")){
			System.out.println("school api:WF=====insert");
//			判断用户标志
			String member_sign00=request.getParameter("member_sign");
		 
//			String Id=request.getParameter("Id");
			String School_name=request.getParameter("School_name");
			String School_EnglishName=request.getParameter("School_EnglishName");
			String School_properties=request.getParameter("School_properties");
			String Areas=request.getParameter("Areas");
			String Areas02=request.getParameter("Areas02");
			String Areas03=request.getParameter("Areas03");
			String Founded_time=request.getParameter("Founded_time");
			String OperationState=request.getParameter("OperationState");
			String School_system=request.getParameter("School_system");
			String Tuition=request.getParameter("Tuition");
			String Website=request.getParameter("Website");
			String Telephone=request.getParameter("Telephone");

			String Inter_Course_Founded_time=request.getParameter("Inter_Course_Founded_time");
			String Course=request.getParameter("Course");
			String Authentication=request.getParameter("Authentication");
			String Course_evaluation=request.getParameter("Course_evaluation");
			String Student_Num=request.getParameter("Student_Num");
			String Student_Capacity=request.getParameter("Student_Capacity");
			String Graduated_Stu_Num=request.getParameter("Graduated_Stu_Num");
			String Stu_Dominant_nationality=request.getParameter("Stu_Dominant_nationality");
			String Stu_Year_Investment=request.getParameter("Stu_Year_Investment");
			String Club_Num=request.getParameter("Club_Num");

			String President_Country=request.getParameter("President_Country");
			String Staff_Num=request.getParameter("Staff_Num");
			String Teacher_Num=request.getParameter("Teacher_Num");
			String Foreign_Teacher_num=request.getParameter("Foreign_Teacher_num");
			String Teacher_Year_Investment=request.getParameter("Teacher_Year_Investment");
			String Teacher_Retention=request.getParameter("Teacher_Retention");
			String Teacher_Salary=request.getParameter("Teacher_Salary");
			String Teacher_Stu_ratio=request.getParameter("Teacher_Stu_ratio");

			String Covered_Area=request.getParameter("Covered_Area");
			String Built_Area=request.getParameter("Built_Area");
			String Hardware=request.getParameter("Hardware");
			String Investment=request.getParameter("Investment");
			String Remark=request.getParameter("Remark");
		
//			String Recent_Modifier=request.getParameter("Recent_Modifier");
			String Load_People=request.getParameter("Load_People");
//			String Load_Time=request.getParameter("Load_Time");

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
	    	
//	    	test
	    	System.out.println("运营状态："+OperationState);
	    	
//			外部人员 存入审核数据库 @用户权限
//	    	区别：1、存入的数据库不一样，2、审核标记
//	    	新增标记
	    	int VerifySign=11;
			 if(member_sign<=7){			
				sql="REPLACE INTO NSI_SCHOOL_data_verify (School_name,School_EnglishName,School_properties,"
						+ "Areas,Areas02,Areas03,Founded_time,OperationState,School_system,Tuition,Website,Telephone,Inter_Course_Founded_time,Course,"
				 		+ "Authentication,Course_evaluation,Student_Num,Student_Capacity,Graduated_Stu_Num,Stu_Dominant_nationality,Stu_Year_Investment,Club_Num,President_Country,Staff_Num,Teacher_Num,Foreign_Teacher_num,Teacher_Year_Investment,"
				 		+ "Teacher_Retention,Teacher_Salary,Teacher_Stu_ratio,Covered_Area,Built_Area,Hardware,Investment,Remark,Load_People,Load_Time,VerifySign) "
				 					 		
						+ "VALUES ('"+School_name+"','"+School_EnglishName+"','"+School_properties+"',"
						+ "'"+Areas+"','"+Areas02+"','"+Areas03+"','"+Founded_time+"','"+OperationState+"','"+School_system+"','"+Tuition+"','"+Website+"','"+Telephone+"','"+Inter_Course_Founded_time+"','"+Course+"',"
						+ "'"+Authentication+"','"+Course_evaluation+"','"+Student_Num+"','"+Student_Capacity+"','"+Graduated_Stu_Num+"','"+Stu_Dominant_nationality+"','"+Stu_Year_Investment+"','"+Club_Num+"','"+President_Country+"','"+Staff_Num+"','"+Teacher_Num+"','"+Foreign_Teacher_num+"','"+Teacher_Year_Investment+"',"
						+ "'"+Teacher_Retention+"','"+Teacher_Salary+"','"+Teacher_Stu_ratio+"','"+Covered_Area+"','"+Built_Area+"','"+Hardware+"','"+Investment+"','"+Remark+"','"+Load_People+"','"+SubmitDate+"','"+VerifySign+"')";			
			 }else{
//				内部员工免审核
				sql="INSERT INTO NSI_SCHOOL_data (School_name,School_EnglishName,School_properties,"
						+ "Areas,Areas02,Areas03,Founded_time,OperationState,School_system,Tuition,Website,Telephone,Inter_Course_Founded_time,Course,"
				 		+ "Authentication,Course_evaluation,Student_Num,Student_Capacity,Graduated_Stu_Num,Stu_Dominant_nationality,Stu_Year_Investment,Club_Num,President_Country,Staff_Num,Teacher_Num,Foreign_Teacher_num,Teacher_Year_Investment,"
				 		+ "Teacher_Retention,Teacher_Salary,Teacher_Stu_ratio,Covered_Area,Built_Area,Hardware,Investment,Remark,Load_People,Load_Time,VerifySign) "
				 					 		
						+ "VALUES ('"+School_name+"','"+School_EnglishName+"','"+School_properties+"',"
						+ "'"+Areas+"','"+Areas02+"','"+Areas03+"','"+Founded_time+"','"+OperationState+"','"+School_system+"','"+Tuition+"','"+Website+"','"+Telephone+"','"+Inter_Course_Founded_time+"','"+Course+"',"
						+ "'"+Authentication+"','"+Course_evaluation+"','"+Student_Num+"','"+Student_Capacity+"','"+Graduated_Stu_Num+"','"+Stu_Dominant_nationality+"','"+Stu_Year_Investment+"','"+Club_Num+"','"+President_Country+"','"+Staff_Num+"','"+Teacher_Num+"','"+Foreign_Teacher_num+"','"+Teacher_Year_Investment+"',"
						+ "'"+Teacher_Retention+"','"+Teacher_Salary+"','"+Teacher_Stu_ratio+"','"+Covered_Area+"','"+Built_Area+"','"+Hardware+"','"+Investment+"','"+Remark+"','"+Load_People+"','"+SubmitDate+"','"+VerifySign+"')";			
}
			
			DB.Insert(sql);
			
			String back="{msg:1}";
	    	String Callback = request.getParameter("Callback");//客户端请求参数	  	    	
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+back+")");
				
//	    	change
//			待测试 ID删除
		}else if(whereFrom.equals("delete")){
			System.out.println("school api:WF=====delete");
			
	    	Gson gson = new Gson();   	
	    	String Id=request.getParameter("Id");
	    			    	
	    	String sql="DELETE FROM nsi_school_data WHERE Id ='"+Id+"';";    	
			DB.Delete(sql);	
//			成功
	    	String back="{msg:1}";

	    	String Callback = request.getParameter("Callback");//客户端请求参数	  	    	
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+back+")");
		    	
//	    	待测试
//	    	修改操作
		}else if(whereFrom.equals("alter")){
			System.out.println("school api:WF=====alter");
			
//			判断用户标志
			String member_sign00=request.getParameter("member_sign");
			int member_sign=Integer.parseInt(member_sign00);
//			旧数据的ID值
			String alter_old_School_id00=request.getParameter("alter_old_School_id");
			int alter_old_School_id=Integer.parseInt(alter_old_School_id00);
			
			String School_name=request.getParameter("School_name");
			String School_EnglishName=request.getParameter("School_EnglishName");
			String School_properties=request.getParameter("School_properties");
			String Areas=request.getParameter("Areas");
			String Areas02=request.getParameter("Areas02");
			String Areas03=request.getParameter("Areas03");
			String Founded_time=request.getParameter("Founded_time");
			String OperationState=request.getParameter("OperationState");
			String School_system=request.getParameter("School_system");
			String Tuition=request.getParameter("Tuition");
			String Website=request.getParameter("Website");
			String Telephone=request.getParameter("Telephone");

			String Inter_Course_Founded_time=request.getParameter("Inter_Course_Founded_time");
			String Course=request.getParameter("Course");
			String Authentication=request.getParameter("Authentication");
			String Course_evaluation=request.getParameter("Course_evaluation");
			String Student_Num=request.getParameter("Student_Num");
			String Student_Capacity=request.getParameter("Student_Capacity");
			String Graduated_Stu_Num=request.getParameter("Graduated_Stu_Num");
			String Stu_Dominant_nationality=request.getParameter("Stu_Dominant_nationality");
			String Stu_Year_Investment=request.getParameter("Stu_Year_Investment");
			String Club_Num=request.getParameter("Club_Num");

			String President_Country=request.getParameter("President_Country");
			String Staff_Num=request.getParameter("Staff_Num");
			String Teacher_Num=request.getParameter("Teacher_Num");
			String Foreign_Teacher_num=request.getParameter("Foreign_Teacher_num");
			String Teacher_Year_Investment=request.getParameter("Teacher_Year_Investment");
			String Teacher_Retention=request.getParameter("Teacher_Retention");
			String Teacher_Salary=request.getParameter("Teacher_Salary");
			String Teacher_Stu_ratio=request.getParameter("Teacher_Stu_ratio");

			String Covered_Area=request.getParameter("Covered_Area");
			String Built_Area=request.getParameter("Built_Area");
			String Hardware=request.getParameter("Hardware");
			String Investment=request.getParameter("Investment");
			String Remark=request.getParameter("Remark");
			
			String Load_People=request.getParameter("Load_People");
//			当前时间
			java.util.Date currentTime = new java.util.Date(); 
	    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    	String SubmitDate = formatter.format(currentTime);
	    	
//	    	修改标记
	    	int VerifySign=12;
	    	String sql=null;
			Gson gson = new Gson();  
//			????--------------------------------------------------------------------------
			List<School_model> list = new ArrayList<School_model>();
						
			if(member_sign<=7){			
//				插入到审核数据表
				sql="REPLACE INTO NSI_SCHOOL_data_verify (Id,School_name,School_EnglishName,School_properties,Areas,Areas02,Areas03,Founded_time,OperationState,School_system,Tuition,Website,Telephone,"
						+ "Inter_Course_Founded_time,Course,Authentication,Course_evaluation,Student_Num,Student_Capacity,Graduated_Stu_Num,Stu_Dominant_nationality,Stu_Year_Investment,Club_Num,President_Country,"
						+ "Staff_Num,Teacher_Num,Foreign_Teacher_num,Teacher_Year_Investment,Teacher_Retention,Teacher_Salary,Teacher_Stu_ratio,Covered_Area,Built_Area,Hardware,Investment,Remark,"
						+ "Load_People,Load_Time,VerifySign) "
							
				+ "VALUES ('"+alter_old_School_id+"','"+School_name+"','"+School_EnglishName+"','"+School_properties+"',"
				+ "'"+Areas+"','"+Areas02+"','"+Areas03+"','"+Founded_time+"','"+OperationState+"','"+School_system+"','"+Tuition+"','"+Website+"','"+Telephone+"','"+Inter_Course_Founded_time+"','"+Course+"',"
				+ "'"+Authentication+"','"+Course_evaluation+"','"+Student_Num+"','"+Student_Capacity+"','"+Graduated_Stu_Num+"','"+Stu_Dominant_nationality+"','"+Stu_Year_Investment+"','"+Club_Num+"','"+President_Country+"','"+Staff_Num+"','"+Teacher_Num+"','"+Foreign_Teacher_num+"','"+Teacher_Year_Investment+"',"
				+ "'"+Teacher_Retention+"','"+Teacher_Salary+"','"+Teacher_Stu_ratio+"','"+Covered_Area+"','"+Built_Area+"','"+Hardware+"','"+Investment+"','"+Remark+"','"+Load_People+"','"+SubmitDate+"','"+VerifySign+"')";			
				System.out.print("修改 sql:"+sql);
				DB.Insert(sql);
				
			}else{
//				内部员工免审核 更新到数据表
				sql="UPDATE NSI_SCHOOL_data SET School_name ='"+School_name+"',School_EnglishName ='"+School_EnglishName+"',"
				+ "School_properties ='"+School_properties+"',Areas ='"+Areas+"',Areas02 ='"+Areas02+"',Areas03 ='"+Areas03+"',"
				+ "Founded_time ='"+Founded_time+"',OperationState ='"+OperationState+"',School_system ='"+School_system+"',"
				+ "Tuition ='"+Tuition+"',Website ='"+Website+"',Telephone ='"+Telephone+"',"
				+ "Inter_Course_Founded_time ='"+Inter_Course_Founded_time+"',Course ='"+Course+"',"
				+ "Authentication ='"+Authentication+"',Course_evaluation ='"+Course_evaluation+"',"
				+ "Student_Num ='"+Student_Num+"',Student_Capacity ='"+Student_Capacity+"',"
				+ "Graduated_Stu_Num ='"+Graduated_Stu_Num+"',Stu_Dominant_nationality ='"+Stu_Dominant_nationality+"',"
				+ "Stu_Year_Investment ='"+Stu_Year_Investment+"',Club_Num ='"+Club_Num+"',"
				+ "President_Country ='"+President_Country+"',Staff_Num ='"+Staff_Num+"',Teacher_Num ='"+Teacher_Num+"',"
				+ "Foreign_Teacher_num ='"+Foreign_Teacher_num+"',Teacher_Year_Investment ='"+Teacher_Year_Investment+"',"
				+ "Teacher_Retention ='"+Teacher_Retention+"',Teacher_Salary ='"+Teacher_Salary+"',Teacher_Stu_ratio ='"+Teacher_Stu_ratio+"',"
				+ "Covered_Area ='"+Covered_Area+"',Built_Area ='"+Built_Area+"',Hardware ='"+Hardware+"',Investment ='"+Investment+"',Remark ='"+Remark+"',"
				+ "Load_People ='"+Load_People+"',Load_Time ='"+SubmitDate+"',"
				+ "VerifySign ='"+VerifySign+"' "
				
				+" WHERE Id ='"+alter_old_School_id+"'";
							
				list=School_DB.alter(sql);	
			}
			String back="{msg:1}";
	    	String Callback = request.getParameter("Callback");//客户端请求参数	  	    	
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+back+")");

//			搜索
		}else if(whereFrom.equals("search")){
			System.out.println("school api:WF======search");
			
	    	Gson gson = new Gson();   	
	    	String School_searchKey=request.getParameter("School_searchKey");
	    	String sql=null;		
			String sqlcount=null;
			int countAllRS = 0;
			
//			分页参数 ：第几页、每页几个。默认值：1、20；
			Integer pageNum = request.getParameter("pageNum") != null && !request.getParameter("pageNum").equals("") ? Integer.parseInt(request.getParameter("pageNum")) : 1;
			Integer OnePageNum = request.getParameter("OnePageNum") != null && !request.getParameter("OnePageNum").equals("") ? Integer.parseInt(request.getParameter("OnePageNum")) : 20;

			int pageNumX=(pageNum-1)*OnePageNum;
			
			List<School_model> list = new ArrayList<School_model>();			
			 sql="SELECT * from NSI_SCHOOL_data WHERE CONCAT(IFNULL(`Id`,''),IFNULL(`School_name`,''),IFNULL(`School_EnglishName`,''),IFNULL(`Areas`,''),IFNULL(`Areas02`,''),IFNULL(`Founded_time`,'')) like '%"+School_searchKey+"%' order by CONVERT(School_name USING gb2312) limit "+pageNumX+","+OnePageNum+"";
			 sqlcount="SELECT * from NSI_SCHOOL_data WHERE CONCAT(IFNULL(`Id`,''),IFNULL(`School_name`,''),IFNULL(`School_EnglishName`,''),IFNULL(`Areas`,''),IFNULL(`Areas02`,''),IFNULL(`Founded_time`,'')) like '%"+School_searchKey+"%' order by CONVERT(School_name USING gb2312)";
					
			list=School_DB.Search(sql);
			countAllRS=DB.count(sqlcount);
	    	String jsonList =gson.toJson(list);

	    	String Callback = request.getParameter("Callback");//客户端请求参数
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+jsonList+")");

	    
	    
		///////////////////////////////////////////////////////////////////////////////////	///////////////////////////////////////////////////////////////////////////////////			
		}else if(whereFrom.equals("count")){
		
				System.out.println("school api:WF======count");		
				Gson gson = new Gson();   	
				String School_searchKey=request.getParameter("School_searchKey");			
				String sqlcount=null;
				int countAllRS = 0;
			
				 sqlcount="SELECT * from NSI_SCHOOL_data WHERE CONCAT(IFNULL(`Id`,''),IFNULL(`School_name`,''),IFNULL(`School_EnglishName`,''),IFNULL(`Areas`,''),IFNULL(`Areas02`,''),IFNULL(`Founded_time`,'')) like '%"+School_searchKey+"%' order by CONVERT(School_name USING gb2312)";
								
				countAllRS=DB.count(sqlcount);
				
				String back="{countAllRS:"+countAllRS+"}";
				String Callback = request.getParameter("Callback");//客户端请求参数	  	    	
				response.setContentType("text/html;charset=UTF-8");  
				response.getWriter().write(Callback+"("+back+")");

				
//		    	 详情页，传入Id 返回list值
		}else if(whereFrom.equals("detail")){
			System.out.println("school api:WF======detail");		
	    	Gson gson = new Gson();   	
	    	String Id=request.getParameter("Id");
	    	String sql=null;		

			
			List<School_model> list = new ArrayList<School_model>();			
			 sql="SELECT * from NSI_SCHOOL_data WHERE "
			 		+ "Id='"+Id+"' order by CONVERT(School_name USING gb2312) limit 0,1";
			 	
			list=School_DB.Search(sql);
	    	String jsonList =gson.toJson(list);
	    	String Callback = request.getParameter("Callback");//客户端请求参数
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+jsonList+")");
	    
///////////////////////////////////////////////////////////////////////////////////			
//	    	高级搜索
//	    	未修改
		}else if(whereFrom.equals("AdvancedSearch")){
			
			System.out.println("school api:WF======AdvancedSearch");		
			Gson gson = new Gson();   	
//			获取参数
//	    	String School_searchKey=request.getParameter("School_searchKey");
	    	String[] area = request.getParameterValues("area");
	    	String[] system = request.getParameterValues("system");
	    	String[] course = request.getParameterValues("course");
	    	String Founded_time = request.getParameter("Founded_time");
//	    	空数组
	    	String[] null4 = {"0","0","0","0"};
	    	String[] null34 = {"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"};
			
	    	System.out.println("地区："+area);
//			分页参数 ：第几页、每页几个。默认值：1、20；
			Integer pageNum = request.getParameter("pageNum") != null && !request.getParameter("pageNum").equals("") ? Integer.parseInt(request.getParameter("pageNum")) : 1;
			Integer OnePageNum = request.getParameter("OnePageNum") != null && !request.getParameter("OnePageNum").equals("") ? Integer.parseInt(request.getParameter("OnePageNum")) : 20;
			int pageNumX=(pageNum-1)*OnePageNum;
			
			List<School_model> list = new ArrayList<School_model>();		
			StringBuffer StringBuffer = new StringBuffer("SELECT * from NSI_SCHOOL_data WHERE 1=1");
//				成立时间不为 0 且 不为空
				if(Founded_time.length()!=0 && !Founded_time.equals("0")) {
					StringBuffer.append(" AND ( 1=0 or Founded_time = "+Founded_time+" )"); 	
				}
//				地区数组不为空
				if(!Arrays.equals(area, null34)){
					StringBuffer.append(" AND ( 1=0");
					for(int i = 0; i<area.length; i++)
					 switch (area[i])
					 {
					  case "0":	break;
					  case "1": StringBuffer.append(" or Areas like '%北京%'"); break;
					  case "2": StringBuffer.append(" or Areas like '%上海%'"); break;
					  case "3": StringBuffer.append(" or Areas like '%天津%'"); break;
					  case "4": StringBuffer.append(" or Areas like '%重庆%'"); break;
					  case "5": StringBuffer.append(" or Areas like '%浙江%'"); break;
					  case "6": StringBuffer.append(" or Areas like '%江苏%'"); break;
					  case "7": StringBuffer.append(" or Areas like '%广东%'"); break;
					  case "8": StringBuffer.append(" or Areas like '%福建%'"); break;
					  case "9": StringBuffer.append(" or Areas like '%湖南%'"); break;
					  case "10": StringBuffer.append(" or Areas like '%湖北%'"); break;
					  case "11": StringBuffer.append(" or Areas like '%辽宁%'"); break;
					  case "12": StringBuffer.append(" or Areas like '%吉林%'"); break;
					  case "13": StringBuffer.append(" or Areas like '%黑龙江%'"); break;
					  case "14": StringBuffer.append(" or Areas like '%河北%'"); break;
					  case "15": StringBuffer.append(" or Areas like '%河南%'"); break;
					  case "16": StringBuffer.append(" or Areas like '%山东%'"); break;
					  case "17": StringBuffer.append(" or Areas like '%陕西%'"); break;
					  case "18": StringBuffer.append(" or Areas like '%甘肃%'"); break;
					  case "19": StringBuffer.append(" or Areas like '%新疆%'"); break;
					  case "20": StringBuffer.append(" or Areas like '%青海%'"); break;
					  case "21": StringBuffer.append(" or Areas like '%山西%'"); break;
					  case "22": StringBuffer.append(" or Areas like '%四川%'"); break;
					  case "23": StringBuffer.append(" or Areas like '%贵州%'"); break;
					  case "24": StringBuffer.append(" or Areas like '%安徽%'"); break;
					  case "25": StringBuffer.append(" or Areas like '%江西%'"); break;
					  case "26": StringBuffer.append(" or Areas like '%云南%'"); break;
					  case "27": StringBuffer.append(" or Areas like '%内蒙古%'"); break;
					  case "28": StringBuffer.append(" or Areas like '%西藏%'"); break;
					  case "29": StringBuffer.append(" or Areas like '%广西%'"); break;
					  case "30": StringBuffer.append(" or Areas like '%宁夏%'"); break;
					  case "31": StringBuffer.append(" or Areas like '%海南%'"); break;
					  case "32": StringBuffer.append(" or Areas like '%香港%'"); break;
					  case "33": StringBuffer.append(" or Areas like '%澳门%'"); break;
					  case "34": StringBuffer.append(" or Areas like '%台湾%'"); break;
					  default:	break;
					 }
					StringBuffer.append(")");
					}
				
				if(!Arrays.equals(system, null4)){
					StringBuffer.append(" AND ( 1=0");
					for(int i = 0; i<system.length; i++)
					 switch (system[i])
					 {
					  case "0": break;
					  case "1": StringBuffer.append(" or School_system like '%高中%'"); break;
					  case "2": StringBuffer.append(" or School_system like '%初中%'"); break;
					  case "3": StringBuffer.append(" or School_system like '%小学%'"); break;
					  case "4": StringBuffer.append(" or School_system like '%幼儿园%'"); break;
					  default: break;
					 }
					StringBuffer.append(")");
					}
				
				if(!Arrays.equals(course, null4)){
					StringBuffer.append(" AND ( 1=0");
					for(int i = 0; i<course.length; i++)
					 switch (course[i])
					 {
					  case "0": break;
					  case "1": StringBuffer.append(" or Course like '%AP%'"); break;
					  case "2": StringBuffer.append(" or Course like '%PYP%'"); break;
					  case "3": StringBuffer.append(" or Course like '%IMYC%'"); break;
					  case "4": StringBuffer.append(" or Course like '%A-LEVEL%'"); break;  
					  default: break;
					 }
					StringBuffer.append(")");
					}
			
			StringBuffer.append(" order by CONVERT(School_name USING gb2312)");
			StringBuffer.append("limit "+pageNumX+","+OnePageNum+"");
//			 order by CONVERT(School_name USING gb2312) limit "+pageNumX+","+OnePageNum+"
//			System.out.println("school：搜索语句："+StringBuffer);
			
			list=School_DB.Search(StringBuffer.toString());

	    	String jsonList =gson.toJson(list);
	    	String Callback = request.getParameter("Callback");//客户端请求参数
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+jsonList+")");
	       	
   	
///////////////////////////////////////////////////////////////////////////////////	///////////////////////////////////////////////////////////////////////////////////	    	
//	    	高级搜索 计数
//	    	未修改
		}else if(whereFrom.equals("AdvancedSearchCount")){
			System.out.println("school api:WF======AdvancedSearchCount");
			Gson gson = new Gson();   	
//			获取参数
//	    	String School_searchKey=request.getParameter("School_searchKey");
	    	String[] area = request.getParameterValues("area");
	    	String[] system = request.getParameterValues("system");
	    	String[] course = request.getParameterValues("course");
	    	String Founded_time = request.getParameter("Founded_time");
//	    	空数组
	    	String[] null4 = {"0","0","0","0"};
	    	String[] null34 = {"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"};
			
	    	int countAllRS = 0;
	    	
	    	StringBuffer StringBuffer = new StringBuffer("SELECT * from NSI_SCHOOL_data WHERE 1=1");
//			成立时间不为 0 且 不为空
			if(Founded_time.length()!=0 && !Founded_time.equals("0")) {
				StringBuffer.append(" AND ( 1=0 or Founded_time = "+Founded_time+" )"); 	
			}
//			数组01不为空
			if(!Arrays.equals(area, null34)){
				StringBuffer.append(" AND ( 1=0");
				for(int i = 0; i<area.length; i++)
				 switch (area[i])
				 {
				  case "0":	break;
				  
				  case "1": StringBuffer.append(" or Areas like '%北京%'"); break;
				  case "2": StringBuffer.append(" or Areas like '%上海%'"); break;
				  case "3": StringBuffer.append(" or Areas like '%天津%'"); break;
				  case "4": StringBuffer.append(" or Areas like '%重庆%'"); break;
				  case "5": StringBuffer.append(" or Areas like '%浙江%'"); break;
				  case "6": StringBuffer.append(" or Areas like '%江苏%'"); break;
				  case "7": StringBuffer.append(" or Areas like '%广东%'"); break;
				  case "8": StringBuffer.append(" or Areas like '%福建%'"); break;
				  case "9": StringBuffer.append(" or Areas like '%湖南%'"); break;
				  case "10": StringBuffer.append(" or Areas like '%湖北%'"); break;
				  case "11": StringBuffer.append(" or Areas like '%辽宁%'"); break;
				  case "12": StringBuffer.append(" or Areas like '%吉林%'"); break;
				  case "13": StringBuffer.append(" or Areas like '%黑龙江%'"); break;
				  case "14": StringBuffer.append(" or Areas like '%河北%'"); break;
				  case "15": StringBuffer.append(" or Areas like '%河南%'"); break;
				  case "16": StringBuffer.append(" or Areas like '%山东%'"); break;
				  case "17": StringBuffer.append(" or Areas like '%陕西%'"); break;
				  case "18": StringBuffer.append(" or Areas like '%甘肃%'"); break;
				  case "19": StringBuffer.append(" or Areas like '%新疆%'"); break;
				  case "20": StringBuffer.append(" or Areas like '%青海%'"); break;
				  case "21": StringBuffer.append(" or Areas like '%山西%'"); break;
				  case "22": StringBuffer.append(" or Areas like '%四川%'"); break;
				  case "23": StringBuffer.append(" or Areas like '%贵州%'"); break;
				  case "24": StringBuffer.append(" or Areas like '%安徽%'"); break;
				  case "25": StringBuffer.append(" or Areas like '%江西%'"); break;
				  case "26": StringBuffer.append(" or Areas like '%云南%'"); break;
				  case "27": StringBuffer.append(" or Areas like '%内蒙古%'"); break;
				  case "28": StringBuffer.append(" or Areas like '%西藏%'"); break;
				  case "29": StringBuffer.append(" or Areas like '%广西%'"); break;
				  case "30": StringBuffer.append(" or Areas like '%宁夏%'"); break;
				  case "31": StringBuffer.append(" or Areas like '%海南%'"); break;
				  case "32": StringBuffer.append(" or Areas like '%香港%'"); break;
				  case "33": StringBuffer.append(" or Areas like '%澳门%'"); break;
				  case "34": StringBuffer.append(" or Areas like '%台湾%'"); break;
				  
				  default:	break;
				 }
				StringBuffer.append(")");
				}
			
			if(!Arrays.equals(system, null4)){
				StringBuffer.append(" AND ( 1=0");
				for(int i = 0; i<system.length; i++)
				 switch (system[i])
				 {
				  case "0": break;
				  case "1": StringBuffer.append(" or School_system like '%高中%'"); break;
				  case "2": StringBuffer.append(" or School_system like '%初中%'"); break;
				  case "3": StringBuffer.append(" or School_system like '%小学%'"); break;
				  case "4": StringBuffer.append(" or School_system like '%幼儿园%'"); break;
				  default: break;
				 }
				StringBuffer.append(")");
				}
			
			if(!Arrays.equals(course, null4)){
				StringBuffer.append(" AND ( 1=0");
				for(int i = 0; i<course.length; i++)
				 switch (course[i])
				 {
				  case "0": break;
				  case "1": StringBuffer.append(" or Course like '%AP%'"); break;
				  case "2": StringBuffer.append(" or Course like '%PYP%'"); break;
				  case "3": StringBuffer.append(" or Course like '%IMYC%'"); break;
				  case "4": StringBuffer.append(" or Course like '%A-LEVEL%'"); break;  
				  default: break;
				 }
				StringBuffer.append(")");
				}
			
			StringBuffer.append(" order by CONVERT(School_name USING gb2312)");

//			 order by CONVERT(School_name USING gb2312) limit "+pageNumX+","+OnePageNum+"
//			System.out.println("school：搜索语句："+StringBuffer);
			countAllRS=DB.count(StringBuffer.toString());
			
			String back="{\"countAllRS\":\""+countAllRS+"\"}";

	    	String Callback = request.getParameter("Callback");//客户端请求参数	  	    	
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+back+")");
	
					  	
//	    	测试echart数据 省份学校数量查询
//	    	待修改
		}else if(whereFrom.equals("testEchart")){	
			
			System.out.println("school api:WF======testEchart");		
	    	Gson gson = new Gson();   	
	    	String City_searchKey=request.getParameter("City_searchKey");			
			String sqlcount=null;
			int countAllRS = 0;
			String[] china=	{"北京", "上海", "天津", "重庆", "浙江", "江苏", "广东", "福建", "湖南", "湖北", "辽宁", 
				"吉林", "黑龙江", "河北", "河南", "山东", "陕西", "甘肃", "新疆", "青海", "山西", "四川", 
				"贵州", "安徽", "江西", "云南", "内蒙古", "西藏", "广西", "宁夏", "海南", "香港", "澳门", "台湾"};
			
			List<School_Areas_model> list=new ArrayList<School_Areas_model>();
		
			for(int i = 0; i<china.length; i++){
				sqlcount="SELECT * from NSI_SCHOOL_data WHERE Areas like '%"+china[i]+"%' order by CONVERT(School_name USING gb2312)";					
				countAllRS=DB.count(sqlcount);
				
				School_Areas_model School_Areas= new School_Areas_model();
				
				School_Areas.setName(china[i]);
				School_Areas.setValue(countAllRS);

				list.add(School_Areas);
			}
//			对list排序
			Collections.sort(list,new Comparator<School_Areas_model>() {
	        
				@Override
				public int compare(School_Areas_model arg0, School_Areas_model arg1) {
					// TODO Auto-generated method stub
					 int hits0 = arg0.getValue();  
		             int hits1 = arg1.getValue(); 
		             if (hits1 > hits0) {  
		                    return 1;  
		                } else if (hits1 == hits0) {  
		                    return 0;  
		                } else {  
		                    return -1;  
		                }  				
					}
	        });

			String jsonList =gson.toJson(list);	
	    	String Callback = request.getParameter("Callback");//客户端请求参数	  	    	
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+jsonList+")");
	    
	    	
//	-------------------------------------------------------------审核 相关--------------------------------------------------------    	
//	    	新增信息审核
		}else if(whereFrom.equals("verify_insert")){
//			1、接受参数 ID
//			2、学校审核表中，复制该ID条目，到数据表
//			3、修改审核表标记 p11
//			3、返回成功
			System.out.println("school api:WF=====verify_insert");	
	    	Gson gson = new Gson();   	
	    	String School_ID00=request.getParameter("School_ID");
	    	int School_ID=Integer.parseInt(School_ID00);
	    	
		    	String sql="INSERT INTO nsi_school_data (School_name,School_EnglishName,School_properties," 
		    							+ "Areas,Areas02,Areas03,Founded_time,OperationState,School_system,Tuition,Website,Telephone,Inter_Course_Founded_time,Course," 
		    					 		+ "Authentication,Course_evaluation,Student_Num,Student_Capacity,Graduated_Stu_Num,Stu_Dominant_nationality,Stu_Year_Investment,Club_Num,President_Country,Staff_Num,Teacher_Num,Foreign_Teacher_num,Teacher_Year_Investment," 
		    					 		+ "Teacher_Retention,Teacher_Salary,Teacher_Stu_ratio,Covered_Area,Built_Area,Hardware,Investment,Remark,Load_People,Load_Time,VerifySign) "
		    	
		    			+ " SELECT `School_name`,`School_EnglishName`,`School_properties`,`Areas`,`Areas02`,`Areas03`,`Founded_time`,`OperationState`,`School_system`,`Tuition`,`Website`,`Telephone`,`Inter_Course_Founded_time`,`Course`,`Authentication`,`Course_evaluation`,`Student_Num`,`Student_Capacity`,`Graduated_Stu_Num`,`Stu_Dominant_nationality`,`Stu_Year_Investment`,`Club_Num`,`President_Country`,`Staff_Num`,`Teacher_Num`,`Foreign_Teacher_num`,`Teacher_Year_Investment`,`Teacher_Retention`,`Teacher_Salary`,`Teacher_Stu_ratio`,`Covered_Area`,`Built_Area`,`Hardware`,`Investment`,`Remark`,`Load_People`,`Load_Time`,`VerifySign` "
		    			+ " FROM nsi_school_data_verify where Id = '"+School_ID+"' ";
		    	
		    	String sql02="UPDATE nsi_school_data_verify SET VerifySign='p11' where Id= '"+School_ID+"' ;";
		    			    
//	    	事务：两条SQL同时 执行。有错误回滚
	    	int i=DB.TransactionInsert(sql, sql02);
//			I 为执行结果
	    	
//			DB.Insert(sql);	
//			成功
	    	String back="{msg:"+i+"}";
	    	System.out.println("school_api:审核插入结果："+i+" ");
	    	String Callback = request.getParameter("Callback");//客户端请求参数	  	    	
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+back+")");
	   
//	    	审核插入 不通过
		}else if(whereFrom.equals("verify_No_insert")){
//			1、接受参数 ID
//			2、学校审核表中，复制该ID条目，到数据表
//			3、修改审核表标记 p11
//			3、返回成功
			System.out.println("school api:WF=====verify_insert");	
	    	Gson gson = new Gson();   	
	    	String School_ID00=request.getParameter("School_ID");
	    	int School_ID=Integer.parseInt(School_ID00);
	    	int i=-2;
	    			    	
		    	String sql="UPDATE nsi_school_data_verify SET VerifySign='n11' where Id= '"+School_ID+"' ;";    	
			try {
				DB.Insert(sql);
				i=1;
			} catch (Exception e) {
				i=-1;
			}
		    	
//			成功
	    	String back="{msg:"+i+"}";
	    	System.out.println("school_api:审核插入结果："+i+" ");
	    	String Callback = request.getParameter("Callback");//客户端请求参数	  	    	
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+back+")");	
	    
//	    	待测试
//	    	修改信息审核
		}else if(whereFrom.equals("verify_alter")){
//			1、接受参数 ID
//			2、学校审核表中，复制该ID条目，到数据表
//			3、修改 审核表中的该数据的标记
//			3、返回成功
			System.out.println("school api:WF=====verify_insert");	
	    	Gson gson = new Gson();   	
	    	String School_ID00=request.getParameter("School_ID");
	    	int School_ID=Integer.parseInt(School_ID00);
	    	
	    	int i=00;
	   
//	    	可用REPLACE 语句
//	    	原为 事务 处理 改为 update 方法
//	    	String sql="DELETE FROM nsi_school_data WHERE Id ='"+School_ID+"';";
//
//	    	String sql02="INSERT INTO nsi_school_data (`Id`,`School_name`,`School_EnglishName`,`School_properties`,`Areas`,`Areas02`,`Areas03`,`Founded_time`,`OperationState`,`School_system`,`Tuition`,`Website`,`Telephone`,`Un01`,`Inter_Course_Founded_time`,`Course`,`Authentication`,`Course_evaluation`,`Student_Num`,`Student_Capacity`,`Graduated_Stu_Num`,`Stu_Dominant_nationality`,`Stu_Year_Investment`,`Club_Num`,`Un02`,`President_Country`,`Staff_Num`,`Teacher_Num`,`Foreign_Teacher_num`,`Teacher_Year_Investment`,`Teacher_Retention`,`Teacher_Salary`,`Teacher_Stu_ratio`,`Un03`,`Covered_Area`,`Built_Area`,`Hardware`,`Investment`,`Remark`,`Un04`,`Recent_Modifier`,`Load_People`,`Load_Time`,`Un05`,`Un06`,`Un07`,`Un08`,`Un09`,`Un10`,`School_logo`,`Img01`,`Img02`,`Img03`,`Img04`,`Img05`,`BatchInput_Sign`,`VerifySign`,`Evaluate`) "
//	    			+ "SELECT `Id`,`School_name`,`School_EnglishName`,`School_properties`,`Areas`,`Areas02`,`Areas03`,`Founded_time`,`OperationState`,`School_system`,`Tuition`,`Website`,`Telephone`,`Un01`,`Inter_Course_Founded_time`,`Course`,`Authentication`,`Course_evaluation`,`Student_Num`,`Student_Capacity`,`Graduated_Stu_Num`,`Stu_Dominant_nationality`,`Stu_Year_Investment`,`Club_Num`,`Un02`,`President_Country`,`Staff_Num`,`Teacher_Num`,`Foreign_Teacher_num`,`Teacher_Year_Investment`,`Teacher_Retention`,`Teacher_Salary`,`Teacher_Stu_ratio`,`Un03`,`Covered_Area`,`Built_Area`,`Hardware`,`Investment`,`Remark`,`Un04`,`Recent_Modifier`,`Load_People`,`Load_Time`,`Un05`,`Un06`,`Un07`,`Un08`,`Un09`,`Un10`,`School_logo`,`Img01`,`Img02`,`Img03`,`Img04`,`Img05`,`BatchInput_Sign`,`VerifySign`,`Evaluate` "
//	    			+ " FROM nsi_school_data_verify where Id = '"+School_ID+"' ";
	    	
//	    	DB.Insert(sql02);	
	    	
	    	String sql=" REPLACE into nsi_school_data (`Id`,`School_name`,`School_EnglishName`,`School_properties`,`Areas`,`Areas02`,`Areas03`,`Founded_time`,`OperationState`,`School_system`,`Tuition`,`Website`,`Telephone`,`Inter_Course_Founded_time`,`Course`,`Authentication`,`Course_evaluation`,`Student_Num`,`Student_Capacity`,`Graduated_Stu_Num`,`Stu_Dominant_nationality`,`Stu_Year_Investment`,`Club_Num`,`President_Country`,`Staff_Num`,`Teacher_Num`,`Foreign_Teacher_num`,`Teacher_Year_Investment`,`Teacher_Retention`,`Teacher_Salary`,`Teacher_Stu_ratio`,`Covered_Area`,`Built_Area`,`Hardware`,`Investment`,`Remark`,`Load_People`,`Load_Time`,`VerifySign` )" 
	    				+"SELECT  `Id`,`School_name`,`School_EnglishName`,`School_properties`,`Areas`,`Areas02`,`Areas03`,`Founded_time`,`OperationState`,`School_system`,`Tuition`,`Website`,`Telephone`,`Inter_Course_Founded_time`,`Course`,`Authentication`,`Course_evaluation`,`Student_Num`,`Student_Capacity`,`Graduated_Stu_Num`,`Stu_Dominant_nationality`,`Stu_Year_Investment`,`Club_Num`,`President_Country`,`Staff_Num`,`Teacher_Num`,`Foreign_Teacher_num`,`Teacher_Year_Investment`,`Teacher_Retention`,`Teacher_Salary`,`Teacher_Stu_ratio`,`Covered_Area`,`Built_Area`,`Hardware`,`Investment`,`Remark`,`Load_People`,`Load_Time`,`VerifySign` "
	    				+"FROM nsi_school_data_verify where Id = '"+School_ID+"'  ";
	    	
	    	String sql02="UPDATE nsi_school_data_verify SET VerifySign='p12' where Id= '"+School_ID+"' ;";
//	    	事务：两条SQL同时 执行。有错误回滚
	    	i=DB.TransactionInsert(sql, sql02);
//			I 为执行结果
	    	System.out.println("verify_alter的msg值为："+i);
	    	String back="{msg:"+i+"}";
	    	String Callback = request.getParameter("Callback");//客户端请求参数	  	    	
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+back+")");
	    
//	    	审核修改 不通过
		}else if(whereFrom.equals("verify_No_alter")){
//			1、接受参数 ID
//			2、学校审核表中，复制该ID条目，到数据表
//			3、修改审核表标记 p11
//			3、返回成功
			System.out.println("school api:WF=====verify_No_alter");	
	    	Gson gson = new Gson();   	
	    	String School_ID00=request.getParameter("School_ID");
	    	int School_ID=Integer.parseInt(School_ID00);
	    	int i=-2;
	    			    	
		    	String sql="UPDATE nsi_school_data_verify SET VerifySign='n12' where Id= '"+School_ID+"' ;";    	
			try {
				DB.Insert(sql);
				i=1;
			} catch (Exception e) {
				i=-1;
			}
		    	
//			成功
	    	String back="{msg:"+i+"}";
	    	System.out.println("school_api:审核插入结果："+i+" ");
	    	String Callback = request.getParameter("Callback");//客户端请求参数	  	    	
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+back+")");	
	    	
//	    	上传学校logo图片	
		}else if(whereFrom.equals("logo_upimg")) {
			System.out.println("WF=logo_upimg");
			// 上传文件存储目录
		    final String UPLOAD_DIRECTORY = "upload";	 
		    // 上传配置
		    final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
		    final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
		    final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
		 
		    	String school_id=request.getParameter("UpImage_school_id");
		    	
		    	 System.out.println("学校api:图片上传，收到的学校ID:"+school_id);
		    	 System.out.println("学校api:图片上传，----------------------------------------------------------------------");
				if (!ServletFileUpload.isMultipartContent(request)) {
				    // 如果不是则停止
				    PrintWriter writer = response.getWriter();
				    writer.println("Error: 表单必须包含 enctype=multipart/form-data");
				    writer.flush();				  
				    return;
				}	
		        // 配置上传参数
		        DiskFileItemFactory factory = new DiskFileItemFactory();
		        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
		        factory.setSizeThreshold(MEMORY_THRESHOLD);
		        // 设置临时存储目录
		        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));	 
		        ServletFileUpload upload = new ServletFileUpload(factory);	         
		        // 设置最大文件上传值
		        upload.setFileSizeMax(MAX_FILE_SIZE);	         
		        // 设置最大请求值 (包含文件和表单数据)
		        upload.setSizeMax(MAX_REQUEST_SIZE);        
		        // 中文处理
		        upload.setHeaderEncoding("UTF-8"); 
		        // 这个路径相对当前应用的目录
//		        String uploadPath = getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;
		        String uploadPath = "C:" + File.separator+"upImage"+ File.separator+"upSchoolImg"+File.separator+school_id;		     
		        // 如果目录不存在则创建
		        File uploadDir = new File(uploadPath);
		        if (!uploadDir.exists()) {
		            uploadDir.mkdir();
		        }
		        
		        int i=-2;
		        try {
		            // 解析请求的内容提取文件数据
//		        	忽略警告或错误信息
//		            @SuppressWarnings("unchecked")
		            List<FileItem> formItems = upload.parseRequest(request);
		            if (formItems != null && formItems.size() > 0) {
		                // 迭代表单数据
		                for (FileItem item : formItems) {
		                    // 处理不在表单中的字段
		                    if (!item.isFormField()) {	                    	
		                        String filePath = uploadPath + File.separator + school_id+"-logo.jpg";
		                        File storeFile = new File(filePath);                   
		                        //	                        上传图片地址至数据库
		                        String sql="UPDATE nsi_school_data SET School_logo='/upImage/upSchoolImg/"+school_id+"/"+school_id+"-logo.jpg' WHERE Id='"+school_id+"' ";    			
		        				DB.alter(sql);		                        
		                        // 在控制台输出文件的上传路径                     
		                        System.out.println("图片上传地址："+filePath);
		                        System.out.println("图片上传sql："+sql);
		                        // 保存文件到硬盘
		                        item.write(storeFile);
		                        request.setAttribute("message",
		                            "文件上传成功!");
		                        i=1;
		                    }
		                }
		            }
		        } catch (Exception ex) {
		            request.setAttribute("message",
		                    "错误信息: " + ex.getMessage());
		            i=-1;
		        }		        
//				成功
		    	String back="{msg:"+i+"}";	
		    	String Callback = request.getParameter("Callback");//客户端请求参数	  	    	
		    	response.setContentType("text/html;charset=UTF-8");  
		    	response.getWriter().write(Callback+"("+back+")");
	    
//		    我的贡献01-搜索结果集
//		        待修改
		}else if(whereFrom.equals("mySubmit")){
			System.out.println("school api:WF======mySubmit");
	    	Gson gson = new Gson();   	

	    	String username=request.getParameter("username");
	    	String sql=null;		
			String sqlcount=null;
			int countAllRS = 0;
			
//			分页参数 ：第几页、每页几个。默认值：1、20；
			Integer pageNum = request.getParameter("pageNum") != null && !request.getParameter("pageNum").equals("") ? Integer.parseInt(request.getParameter("pageNum")) : 1;
			Integer OnePageNum = request.getParameter("OnePageNum") != null && !request.getParameter("OnePageNum").equals("") ? Integer.parseInt(request.getParameter("OnePageNum")) : 20;

			int pageNumX=(pageNum-1)*OnePageNum;
			
			List<School_model> list = new ArrayList<School_model>();			
			 sql="SELECT * from NSI_SCHOOL_data WHERE load_People="+username+" order by Load_Time DESC limit "+pageNumX+","+OnePageNum+"";
			 sqlcount="SELECT * from NSI_SCHOOL_data WHERE load_People="+username+" order by Load_Time DESC ";
					
			list=School_DB.Search(sql);
			countAllRS=DB.count(sqlcount);
	    	String jsonList =gson.toJson(list);
	    	String Callback = request.getParameter("Callback");//客户端请求参数
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+jsonList+")");
	    	
	    	
//		    我的贡献02-结果集计数
//	        待修改
		}else if(whereFrom.equals("mySubmitCount")){
			System.out.println("school api:WF======mySubmitcount");		
	    	Gson gson = new Gson();   	
	    	String username=request.getParameter("username");		
			String sqlcount=null;
			int countAllRS = 0;						
			sqlcount="SELECT * from NSI_SCHOOL_data WHERE load_people="+username+" order by load_time DESC ";							
			countAllRS=DB.count(sqlcount);
			String back="{countAllRS:"+countAllRS+"}";
	    	String Callback = request.getParameter("Callback");//客户端请求参数	  	    	
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+back+")");
		    
		}else if(whereFrom.equals("Check_SchoolName")){	
			
	    	String SchoolName=request.getParameter("SchoolName");
			String sql="SELECT * FROM NSI_SCHOOL_data WHERE School_name='"+SchoolName+"' ";
			System.out.println("检查学校是否存在："+SchoolName+sql);
			int a=DB.count(sql);
			int msg=-2;
			if(a<1){
				msg=1;
			}else{
				msg=-1;
			}
			
			String back="{\"msg\":\""+msg+"\"}";
			
	    	String Callback = request.getParameter("Callback");//客户端请求参数	  	    	
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+back+")");
	    	
		}else if(whereFrom.equals("test")){

			System.out.println("school api:WF======test===============================================================");		
	    	Gson gson = new Gson();   	

	    	String School_searchKey=request.getParameter("School_searchKey");
	    	String sql=null;		
			String sqlcount=null;
			int countAllRS = 0;
			
//			分页参数 ：第几页、每页几个。默认值：1、20；
			Integer pageNum = request.getParameter("pageNum") != null && !request.getParameter("pageNum").equals("") ? Integer.parseInt(request.getParameter("pageNum")) : 1;
			Integer OnePageNum = request.getParameter("OnePageNum") != null && !request.getParameter("OnePageNum").equals("") ? Integer.parseInt(request.getParameter("OnePageNum")) : 20;

			int pageNumX=(pageNum-1)*OnePageNum;
			
			List<School_model> list = new ArrayList<School_model>();			
			 sql="SELECT * from NSI_SCHOOL_data WHERE CONCAT(IFNULL(`School_name`,''),IFNULL(`Investment`,''),IFNULL(`remark`,''),IFNULL(`Areas`,''),IFNULL(`School_system`,''),IFNULL(`Course`,'')) like '%"+School_searchKey+"%' order by CONVERT(School_name USING gb2312) limit "+pageNumX+","+OnePageNum+"";
			 sqlcount="SELECT * from NSI_SCHOOL_data WHERE CONCAT(IFNULL(`School_name`,''),IFNULL(`Investment`,''),IFNULL(`remark`,''),IFNULL(`Areas`,''),IFNULL(`School_system`,''),IFNULL(`Course`,'')) like '%"+School_searchKey+"%' order by CONVERT(School_name USING gb2312)";
			
			list=School_DB.Search(sql);
			countAllRS=DB.count(sqlcount);

//			附加参数
			Gson gson2 = new Gson(); 
		
	    	String jsonList =gson.toJson(list);
	    	String Callback = request.getParameter("Callback");//客户端请求参数
//	    	额外的参数   	    	
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+jsonList+")");
   	
		}else{
			System.out.println("school_api：没收到whereFrom参数！！！");
		}
//		运行耗时计算
//		long endTime = System.currentTimeMillis();    //获取结束时间
//	    System.out.println("School_api程序耗时：" + (endTime-startTime) + "ms");
//	    endTime = 0;
    }
    
    
}
