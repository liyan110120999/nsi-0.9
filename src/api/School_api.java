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
    	
//    	����ͷ-��ɾ��
    		response.setHeader("Access-Control-Allow-Origin", "*"); //���������ʱ��� 

//    	��ǰʱ��
//    	long startTime = System.currentTimeMillis();   	
    	String whereFrom = null;
			whereFrom = request.getParameter("whereFrom");

//  Lee pass 01
		if(whereFrom.equals("insert")){
			System.out.println("school api:WF=====insert");
//			�ж��û���־
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

//			��ǰʱ��
				java.util.Date currentTime = new java.util.Date(); 
		    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    	String SubmitDate = formatter.format(currentTime);

//			��������	
//	    	1����ȡ��Ա�ȼ�����
//	    	2���費��Ҫ��ˣ�
//	    	3�������������
//	    	4��ִ��SQL
	    	System.out.println("member_sign:"+member_sign00);
	    	int member_sign=Integer.parseInt(member_sign00);
	    	String sql=null;
	    	
//	    	test
	    	System.out.println("��Ӫ״̬��"+OperationState);
	    	
//			�ⲿ��Ա ����������ݿ� @�û�Ȩ��
//	    	����1����������ݿⲻһ����2����˱��
//	    	�������
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
//				�ڲ�Ա�������
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
	    	String Callback = request.getParameter("Callback");//�ͻ����������	  	    	
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+back+")");
				
//	    	change
//			������ IDɾ��
		}else if(whereFrom.equals("delete")){
			System.out.println("school api:WF=====delete");
			
	    	Gson gson = new Gson();   	
	    	String Id=request.getParameter("Id");
	    			    	
	    	String sql="DELETE FROM nsi_school_data WHERE Id ='"+Id+"';";    	
			DB.Delete(sql);	
//			�ɹ�
	    	String back="{msg:1}";

	    	String Callback = request.getParameter("Callback");//�ͻ����������	  	    	
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+back+")");
		    	
//	    	������
//	    	�޸Ĳ���
		}else if(whereFrom.equals("alter")){
			System.out.println("school api:WF=====alter");
			
//			�ж��û���־
			String member_sign00=request.getParameter("member_sign");
			int member_sign=Integer.parseInt(member_sign00);
//			�����ݵ�IDֵ
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
//			��ǰʱ��
			java.util.Date currentTime = new java.util.Date(); 
	    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    	String SubmitDate = formatter.format(currentTime);
	    	
//	    	�޸ı��
	    	int VerifySign=12;
	    	String sql=null;
			Gson gson = new Gson();  
//			????--------------------------------------------------------------------------
			List<School_model> list = new ArrayList<School_model>();
						
			if(member_sign<=7){			
//				���뵽������ݱ�
				sql="REPLACE INTO NSI_SCHOOL_data_verify (Id,School_name,School_EnglishName,School_properties,Areas,Areas02,Areas03,Founded_time,OperationState,School_system,Tuition,Website,Telephone,"
						+ "Inter_Course_Founded_time,Course,Authentication,Course_evaluation,Student_Num,Student_Capacity,Graduated_Stu_Num,Stu_Dominant_nationality,Stu_Year_Investment,Club_Num,President_Country,"
						+ "Staff_Num,Teacher_Num,Foreign_Teacher_num,Teacher_Year_Investment,Teacher_Retention,Teacher_Salary,Teacher_Stu_ratio,Covered_Area,Built_Area,Hardware,Investment,Remark,"
						+ "Load_People,Load_Time,VerifySign) "
							
				+ "VALUES ('"+alter_old_School_id+"','"+School_name+"','"+School_EnglishName+"','"+School_properties+"',"
				+ "'"+Areas+"','"+Areas02+"','"+Areas03+"','"+Founded_time+"','"+OperationState+"','"+School_system+"','"+Tuition+"','"+Website+"','"+Telephone+"','"+Inter_Course_Founded_time+"','"+Course+"',"
				+ "'"+Authentication+"','"+Course_evaluation+"','"+Student_Num+"','"+Student_Capacity+"','"+Graduated_Stu_Num+"','"+Stu_Dominant_nationality+"','"+Stu_Year_Investment+"','"+Club_Num+"','"+President_Country+"','"+Staff_Num+"','"+Teacher_Num+"','"+Foreign_Teacher_num+"','"+Teacher_Year_Investment+"',"
				+ "'"+Teacher_Retention+"','"+Teacher_Salary+"','"+Teacher_Stu_ratio+"','"+Covered_Area+"','"+Built_Area+"','"+Hardware+"','"+Investment+"','"+Remark+"','"+Load_People+"','"+SubmitDate+"','"+VerifySign+"')";			
				System.out.print("�޸� sql:"+sql);
				DB.Insert(sql);
				
			}else{
//				�ڲ�Ա������� ���µ����ݱ�
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
	    	String Callback = request.getParameter("Callback");//�ͻ����������	  	    	
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+back+")");

//			����
		}else if(whereFrom.equals("search")){
			System.out.println("school api:WF======search");
			
	    	Gson gson = new Gson();   	
	    	String School_searchKey=request.getParameter("School_searchKey");
	    	String sql=null;		
			String sqlcount=null;
			int countAllRS = 0;
			
//			��ҳ���� ���ڼ�ҳ��ÿҳ������Ĭ��ֵ��1��20��
			Integer pageNum = request.getParameter("pageNum") != null && !request.getParameter("pageNum").equals("") ? Integer.parseInt(request.getParameter("pageNum")) : 1;
			Integer OnePageNum = request.getParameter("OnePageNum") != null && !request.getParameter("OnePageNum").equals("") ? Integer.parseInt(request.getParameter("OnePageNum")) : 20;

			int pageNumX=(pageNum-1)*OnePageNum;
			
			List<School_model> list = new ArrayList<School_model>();			
			 sql="SELECT * from NSI_SCHOOL_data WHERE CONCAT(IFNULL(`Id`,''),IFNULL(`School_name`,''),IFNULL(`School_EnglishName`,''),IFNULL(`Areas`,''),IFNULL(`Areas02`,''),IFNULL(`Founded_time`,'')) like '%"+School_searchKey+"%' order by CONVERT(School_name USING gb2312) limit "+pageNumX+","+OnePageNum+"";
			 sqlcount="SELECT * from NSI_SCHOOL_data WHERE CONCAT(IFNULL(`Id`,''),IFNULL(`School_name`,''),IFNULL(`School_EnglishName`,''),IFNULL(`Areas`,''),IFNULL(`Areas02`,''),IFNULL(`Founded_time`,'')) like '%"+School_searchKey+"%' order by CONVERT(School_name USING gb2312)";
					
			list=School_DB.Search(sql);
			countAllRS=DB.count(sqlcount);
	    	String jsonList =gson.toJson(list);

	    	String Callback = request.getParameter("Callback");//�ͻ����������
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
				String Callback = request.getParameter("Callback");//�ͻ����������	  	    	
				response.setContentType("text/html;charset=UTF-8");  
				response.getWriter().write(Callback+"("+back+")");

				
//		    	 ����ҳ������Id ����listֵ
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
	    	String Callback = request.getParameter("Callback");//�ͻ����������
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+jsonList+")");
	    
///////////////////////////////////////////////////////////////////////////////////			
//	    	�߼�����
//	    	δ�޸�
		}else if(whereFrom.equals("AdvancedSearch")){
			
			System.out.println("school api:WF======AdvancedSearch");		
			Gson gson = new Gson();   	
//			��ȡ����
//	    	String School_searchKey=request.getParameter("School_searchKey");
	    	String[] area = request.getParameterValues("area");
	    	String[] system = request.getParameterValues("system");
	    	String[] course = request.getParameterValues("course");
	    	String Founded_time = request.getParameter("Founded_time");
//	    	������
	    	String[] null4 = {"0","0","0","0"};
	    	String[] null34 = {"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"};
			
	    	System.out.println("������"+area);
//			��ҳ���� ���ڼ�ҳ��ÿҳ������Ĭ��ֵ��1��20��
			Integer pageNum = request.getParameter("pageNum") != null && !request.getParameter("pageNum").equals("") ? Integer.parseInt(request.getParameter("pageNum")) : 1;
			Integer OnePageNum = request.getParameter("OnePageNum") != null && !request.getParameter("OnePageNum").equals("") ? Integer.parseInt(request.getParameter("OnePageNum")) : 20;
			int pageNumX=(pageNum-1)*OnePageNum;
			
			List<School_model> list = new ArrayList<School_model>();		
			StringBuffer StringBuffer = new StringBuffer("SELECT * from NSI_SCHOOL_data WHERE 1=1");
//				����ʱ�䲻Ϊ 0 �� ��Ϊ��
				if(Founded_time.length()!=0 && !Founded_time.equals("0")) {
					StringBuffer.append(" AND ( 1=0 or Founded_time = "+Founded_time+" )"); 	
				}
//				�������鲻Ϊ��
				if(!Arrays.equals(area, null34)){
					StringBuffer.append(" AND ( 1=0");
					for(int i = 0; i<area.length; i++)
					 switch (area[i])
					 {
					  case "0":	break;
					  case "1": StringBuffer.append(" or Areas like '%����%'"); break;
					  case "2": StringBuffer.append(" or Areas like '%�Ϻ�%'"); break;
					  case "3": StringBuffer.append(" or Areas like '%���%'"); break;
					  case "4": StringBuffer.append(" or Areas like '%����%'"); break;
					  case "5": StringBuffer.append(" or Areas like '%�㽭%'"); break;
					  case "6": StringBuffer.append(" or Areas like '%����%'"); break;
					  case "7": StringBuffer.append(" or Areas like '%�㶫%'"); break;
					  case "8": StringBuffer.append(" or Areas like '%����%'"); break;
					  case "9": StringBuffer.append(" or Areas like '%����%'"); break;
					  case "10": StringBuffer.append(" or Areas like '%����%'"); break;
					  case "11": StringBuffer.append(" or Areas like '%����%'"); break;
					  case "12": StringBuffer.append(" or Areas like '%����%'"); break;
					  case "13": StringBuffer.append(" or Areas like '%������%'"); break;
					  case "14": StringBuffer.append(" or Areas like '%�ӱ�%'"); break;
					  case "15": StringBuffer.append(" or Areas like '%����%'"); break;
					  case "16": StringBuffer.append(" or Areas like '%ɽ��%'"); break;
					  case "17": StringBuffer.append(" or Areas like '%����%'"); break;
					  case "18": StringBuffer.append(" or Areas like '%����%'"); break;
					  case "19": StringBuffer.append(" or Areas like '%�½�%'"); break;
					  case "20": StringBuffer.append(" or Areas like '%�ຣ%'"); break;
					  case "21": StringBuffer.append(" or Areas like '%ɽ��%'"); break;
					  case "22": StringBuffer.append(" or Areas like '%�Ĵ�%'"); break;
					  case "23": StringBuffer.append(" or Areas like '%����%'"); break;
					  case "24": StringBuffer.append(" or Areas like '%����%'"); break;
					  case "25": StringBuffer.append(" or Areas like '%����%'"); break;
					  case "26": StringBuffer.append(" or Areas like '%����%'"); break;
					  case "27": StringBuffer.append(" or Areas like '%���ɹ�%'"); break;
					  case "28": StringBuffer.append(" or Areas like '%����%'"); break;
					  case "29": StringBuffer.append(" or Areas like '%����%'"); break;
					  case "30": StringBuffer.append(" or Areas like '%����%'"); break;
					  case "31": StringBuffer.append(" or Areas like '%����%'"); break;
					  case "32": StringBuffer.append(" or Areas like '%���%'"); break;
					  case "33": StringBuffer.append(" or Areas like '%����%'"); break;
					  case "34": StringBuffer.append(" or Areas like '%̨��%'"); break;
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
					  case "1": StringBuffer.append(" or School_system like '%����%'"); break;
					  case "2": StringBuffer.append(" or School_system like '%����%'"); break;
					  case "3": StringBuffer.append(" or School_system like '%Сѧ%'"); break;
					  case "4": StringBuffer.append(" or School_system like '%�׶�԰%'"); break;
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
//			System.out.println("school��������䣺"+StringBuffer);
			
			list=School_DB.Search(StringBuffer.toString());

	    	String jsonList =gson.toJson(list);
	    	String Callback = request.getParameter("Callback");//�ͻ����������
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+jsonList+")");
	       	
   	
///////////////////////////////////////////////////////////////////////////////////	///////////////////////////////////////////////////////////////////////////////////	    	
//	    	�߼����� ����
//	    	δ�޸�
		}else if(whereFrom.equals("AdvancedSearchCount")){
			System.out.println("school api:WF======AdvancedSearchCount");
			Gson gson = new Gson();   	
//			��ȡ����
//	    	String School_searchKey=request.getParameter("School_searchKey");
	    	String[] area = request.getParameterValues("area");
	    	String[] system = request.getParameterValues("system");
	    	String[] course = request.getParameterValues("course");
	    	String Founded_time = request.getParameter("Founded_time");
//	    	������
	    	String[] null4 = {"0","0","0","0"};
	    	String[] null34 = {"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"};
			
	    	int countAllRS = 0;
	    	
	    	StringBuffer StringBuffer = new StringBuffer("SELECT * from NSI_SCHOOL_data WHERE 1=1");
//			����ʱ�䲻Ϊ 0 �� ��Ϊ��
			if(Founded_time.length()!=0 && !Founded_time.equals("0")) {
				StringBuffer.append(" AND ( 1=0 or Founded_time = "+Founded_time+" )"); 	
			}
//			����01��Ϊ��
			if(!Arrays.equals(area, null34)){
				StringBuffer.append(" AND ( 1=0");
				for(int i = 0; i<area.length; i++)
				 switch (area[i])
				 {
				  case "0":	break;
				  
				  case "1": StringBuffer.append(" or Areas like '%����%'"); break;
				  case "2": StringBuffer.append(" or Areas like '%�Ϻ�%'"); break;
				  case "3": StringBuffer.append(" or Areas like '%���%'"); break;
				  case "4": StringBuffer.append(" or Areas like '%����%'"); break;
				  case "5": StringBuffer.append(" or Areas like '%�㽭%'"); break;
				  case "6": StringBuffer.append(" or Areas like '%����%'"); break;
				  case "7": StringBuffer.append(" or Areas like '%�㶫%'"); break;
				  case "8": StringBuffer.append(" or Areas like '%����%'"); break;
				  case "9": StringBuffer.append(" or Areas like '%����%'"); break;
				  case "10": StringBuffer.append(" or Areas like '%����%'"); break;
				  case "11": StringBuffer.append(" or Areas like '%����%'"); break;
				  case "12": StringBuffer.append(" or Areas like '%����%'"); break;
				  case "13": StringBuffer.append(" or Areas like '%������%'"); break;
				  case "14": StringBuffer.append(" or Areas like '%�ӱ�%'"); break;
				  case "15": StringBuffer.append(" or Areas like '%����%'"); break;
				  case "16": StringBuffer.append(" or Areas like '%ɽ��%'"); break;
				  case "17": StringBuffer.append(" or Areas like '%����%'"); break;
				  case "18": StringBuffer.append(" or Areas like '%����%'"); break;
				  case "19": StringBuffer.append(" or Areas like '%�½�%'"); break;
				  case "20": StringBuffer.append(" or Areas like '%�ຣ%'"); break;
				  case "21": StringBuffer.append(" or Areas like '%ɽ��%'"); break;
				  case "22": StringBuffer.append(" or Areas like '%�Ĵ�%'"); break;
				  case "23": StringBuffer.append(" or Areas like '%����%'"); break;
				  case "24": StringBuffer.append(" or Areas like '%����%'"); break;
				  case "25": StringBuffer.append(" or Areas like '%����%'"); break;
				  case "26": StringBuffer.append(" or Areas like '%����%'"); break;
				  case "27": StringBuffer.append(" or Areas like '%���ɹ�%'"); break;
				  case "28": StringBuffer.append(" or Areas like '%����%'"); break;
				  case "29": StringBuffer.append(" or Areas like '%����%'"); break;
				  case "30": StringBuffer.append(" or Areas like '%����%'"); break;
				  case "31": StringBuffer.append(" or Areas like '%����%'"); break;
				  case "32": StringBuffer.append(" or Areas like '%���%'"); break;
				  case "33": StringBuffer.append(" or Areas like '%����%'"); break;
				  case "34": StringBuffer.append(" or Areas like '%̨��%'"); break;
				  
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
				  case "1": StringBuffer.append(" or School_system like '%����%'"); break;
				  case "2": StringBuffer.append(" or School_system like '%����%'"); break;
				  case "3": StringBuffer.append(" or School_system like '%Сѧ%'"); break;
				  case "4": StringBuffer.append(" or School_system like '%�׶�԰%'"); break;
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
//			System.out.println("school��������䣺"+StringBuffer);
			countAllRS=DB.count(StringBuffer.toString());
			
			String back="{\"countAllRS\":\""+countAllRS+"\"}";

	    	String Callback = request.getParameter("Callback");//�ͻ����������	  	    	
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+back+")");
	
					  	
//	    	����echart���� ʡ��ѧУ������ѯ
//	    	���޸�
		}else if(whereFrom.equals("testEchart")){	
			
			System.out.println("school api:WF======testEchart");		
	    	Gson gson = new Gson();   	
	    	String City_searchKey=request.getParameter("City_searchKey");			
			String sqlcount=null;
			int countAllRS = 0;
			String[] china=	{"����", "�Ϻ�", "���", "����", "�㽭", "����", "�㶫", "����", "����", "����", "����", 
				"����", "������", "�ӱ�", "����", "ɽ��", "����", "����", "�½�", "�ຣ", "ɽ��", "�Ĵ�", 
				"����", "����", "����", "����", "���ɹ�", "����", "����", "����", "����", "���", "����", "̨��"};
			
			List<School_Areas_model> list=new ArrayList<School_Areas_model>();
		
			for(int i = 0; i<china.length; i++){
				sqlcount="SELECT * from NSI_SCHOOL_data WHERE Areas like '%"+china[i]+"%' order by CONVERT(School_name USING gb2312)";					
				countAllRS=DB.count(sqlcount);
				
				School_Areas_model School_Areas= new School_Areas_model();
				
				School_Areas.setName(china[i]);
				School_Areas.setValue(countAllRS);

				list.add(School_Areas);
			}
//			��list����
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
	    	String Callback = request.getParameter("Callback");//�ͻ����������	  	    	
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+jsonList+")");
	    
	    	
//	-------------------------------------------------------------��� ���--------------------------------------------------------    	
//	    	������Ϣ���
		}else if(whereFrom.equals("verify_insert")){
//			1�����ܲ��� ID
//			2��ѧУ��˱��У����Ƹ�ID��Ŀ�������ݱ�
//			3���޸���˱��� p11
//			3�����سɹ�
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
		    			    
//	    	��������SQLͬʱ ִ�С��д���ع�
	    	int i=DB.TransactionInsert(sql, sql02);
//			I Ϊִ�н��
	    	
//			DB.Insert(sql);	
//			�ɹ�
	    	String back="{msg:"+i+"}";
	    	System.out.println("school_api:��˲�������"+i+" ");
	    	String Callback = request.getParameter("Callback");//�ͻ����������	  	    	
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+back+")");
	   
//	    	��˲��� ��ͨ��
		}else if(whereFrom.equals("verify_No_insert")){
//			1�����ܲ��� ID
//			2��ѧУ��˱��У����Ƹ�ID��Ŀ�������ݱ�
//			3���޸���˱��� p11
//			3�����سɹ�
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
		    	
//			�ɹ�
	    	String back="{msg:"+i+"}";
	    	System.out.println("school_api:��˲�������"+i+" ");
	    	String Callback = request.getParameter("Callback");//�ͻ����������	  	    	
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+back+")");	
	    
//	    	������
//	    	�޸���Ϣ���
		}else if(whereFrom.equals("verify_alter")){
//			1�����ܲ��� ID
//			2��ѧУ��˱��У����Ƹ�ID��Ŀ�������ݱ�
//			3���޸� ��˱��еĸ����ݵı��
//			3�����سɹ�
			System.out.println("school api:WF=====verify_insert");	
	    	Gson gson = new Gson();   	
	    	String School_ID00=request.getParameter("School_ID");
	    	int School_ID=Integer.parseInt(School_ID00);
	    	
	    	int i=00;
	   
//	    	����REPLACE ���
//	    	ԭΪ ���� ���� ��Ϊ update ����
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
//	    	��������SQLͬʱ ִ�С��д���ع�
	    	i=DB.TransactionInsert(sql, sql02);
//			I Ϊִ�н��
	    	System.out.println("verify_alter��msgֵΪ��"+i);
	    	String back="{msg:"+i+"}";
	    	String Callback = request.getParameter("Callback");//�ͻ����������	  	    	
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+back+")");
	    
//	    	����޸� ��ͨ��
		}else if(whereFrom.equals("verify_No_alter")){
//			1�����ܲ��� ID
//			2��ѧУ��˱��У����Ƹ�ID��Ŀ�������ݱ�
//			3���޸���˱��� p11
//			3�����سɹ�
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
		    	
//			�ɹ�
	    	String back="{msg:"+i+"}";
	    	System.out.println("school_api:��˲�������"+i+" ");
	    	String Callback = request.getParameter("Callback");//�ͻ����������	  	    	
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+back+")");	
	    	
//	    	�ϴ�ѧУlogoͼƬ	
		}else if(whereFrom.equals("logo_upimg")) {
			System.out.println("WF=logo_upimg");
			// �ϴ��ļ��洢Ŀ¼
		    final String UPLOAD_DIRECTORY = "upload";	 
		    // �ϴ�����
		    final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
		    final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
		    final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
		 
		    	String school_id=request.getParameter("UpImage_school_id");
		    	
		    	 System.out.println("ѧУapi:ͼƬ�ϴ����յ���ѧУID:"+school_id);
		    	 System.out.println("ѧУapi:ͼƬ�ϴ���----------------------------------------------------------------------");
				if (!ServletFileUpload.isMultipartContent(request)) {
				    // ���������ֹͣ
				    PrintWriter writer = response.getWriter();
				    writer.println("Error: ��������� enctype=multipart/form-data");
				    writer.flush();				  
				    return;
				}	
		        // �����ϴ�����
		        DiskFileItemFactory factory = new DiskFileItemFactory();
		        // �����ڴ��ٽ�ֵ - �����󽫲�����ʱ�ļ����洢����ʱĿ¼��
		        factory.setSizeThreshold(MEMORY_THRESHOLD);
		        // ������ʱ�洢Ŀ¼
		        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));	 
		        ServletFileUpload upload = new ServletFileUpload(factory);	         
		        // ��������ļ��ϴ�ֵ
		        upload.setFileSizeMax(MAX_FILE_SIZE);	         
		        // �����������ֵ (�����ļ��ͱ�����)
		        upload.setSizeMax(MAX_REQUEST_SIZE);        
		        // ���Ĵ���
		        upload.setHeaderEncoding("UTF-8"); 
		        // ���·����Ե�ǰӦ�õ�Ŀ¼
//		        String uploadPath = getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;
		        String uploadPath = "C:" + File.separator+"upImage"+ File.separator+"upSchoolImg"+File.separator+school_id;		     
		        // ���Ŀ¼�������򴴽�
		        File uploadDir = new File(uploadPath);
		        if (!uploadDir.exists()) {
		            uploadDir.mkdir();
		        }
		        
		        int i=-2;
		        try {
		            // ���������������ȡ�ļ�����
//		        	���Ծ���������Ϣ
//		            @SuppressWarnings("unchecked")
		            List<FileItem> formItems = upload.parseRequest(request);
		            if (formItems != null && formItems.size() > 0) {
		                // ����������
		                for (FileItem item : formItems) {
		                    // �����ڱ��е��ֶ�
		                    if (!item.isFormField()) {	                    	
		                        String filePath = uploadPath + File.separator + school_id+"-logo.jpg";
		                        File storeFile = new File(filePath);                   
		                        //	                        �ϴ�ͼƬ��ַ�����ݿ�
		                        String sql="UPDATE nsi_school_data SET School_logo='/upImage/upSchoolImg/"+school_id+"/"+school_id+"-logo.jpg' WHERE Id='"+school_id+"' ";    			
		        				DB.alter(sql);		                        
		                        // �ڿ���̨����ļ����ϴ�·��                     
		                        System.out.println("ͼƬ�ϴ���ַ��"+filePath);
		                        System.out.println("ͼƬ�ϴ�sql��"+sql);
		                        // �����ļ���Ӳ��
		                        item.write(storeFile);
		                        request.setAttribute("message",
		                            "�ļ��ϴ��ɹ�!");
		                        i=1;
		                    }
		                }
		            }
		        } catch (Exception ex) {
		            request.setAttribute("message",
		                    "������Ϣ: " + ex.getMessage());
		            i=-1;
		        }		        
//				�ɹ�
		    	String back="{msg:"+i+"}";	
		    	String Callback = request.getParameter("Callback");//�ͻ����������	  	    	
		    	response.setContentType("text/html;charset=UTF-8");  
		    	response.getWriter().write(Callback+"("+back+")");
	    
//		    �ҵĹ���01-���������
//		        ���޸�
		}else if(whereFrom.equals("mySubmit")){
			System.out.println("school api:WF======mySubmit");
	    	Gson gson = new Gson();   	

	    	String username=request.getParameter("username");
	    	String sql=null;		
			String sqlcount=null;
			int countAllRS = 0;
			
//			��ҳ���� ���ڼ�ҳ��ÿҳ������Ĭ��ֵ��1��20��
			Integer pageNum = request.getParameter("pageNum") != null && !request.getParameter("pageNum").equals("") ? Integer.parseInt(request.getParameter("pageNum")) : 1;
			Integer OnePageNum = request.getParameter("OnePageNum") != null && !request.getParameter("OnePageNum").equals("") ? Integer.parseInt(request.getParameter("OnePageNum")) : 20;

			int pageNumX=(pageNum-1)*OnePageNum;
			
			List<School_model> list = new ArrayList<School_model>();			
			 sql="SELECT * from NSI_SCHOOL_data WHERE load_People="+username+" order by Load_Time DESC limit "+pageNumX+","+OnePageNum+"";
			 sqlcount="SELECT * from NSI_SCHOOL_data WHERE load_People="+username+" order by Load_Time DESC ";
					
			list=School_DB.Search(sql);
			countAllRS=DB.count(sqlcount);
	    	String jsonList =gson.toJson(list);
	    	String Callback = request.getParameter("Callback");//�ͻ����������
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+jsonList+")");
	    	
	    	
//		    �ҵĹ���02-���������
//	        ���޸�
		}else if(whereFrom.equals("mySubmitCount")){
			System.out.println("school api:WF======mySubmitcount");		
	    	Gson gson = new Gson();   	
	    	String username=request.getParameter("username");		
			String sqlcount=null;
			int countAllRS = 0;						
			sqlcount="SELECT * from NSI_SCHOOL_data WHERE load_people="+username+" order by load_time DESC ";							
			countAllRS=DB.count(sqlcount);
			String back="{countAllRS:"+countAllRS+"}";
	    	String Callback = request.getParameter("Callback");//�ͻ����������	  	    	
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+back+")");
		    
		}else if(whereFrom.equals("Check_SchoolName")){	
			
	    	String SchoolName=request.getParameter("SchoolName");
			String sql="SELECT * FROM NSI_SCHOOL_data WHERE School_name='"+SchoolName+"' ";
			System.out.println("���ѧУ�Ƿ���ڣ�"+SchoolName+sql);
			int a=DB.count(sql);
			int msg=-2;
			if(a<1){
				msg=1;
			}else{
				msg=-1;
			}
			
			String back="{\"msg\":\""+msg+"\"}";
			
	    	String Callback = request.getParameter("Callback");//�ͻ����������	  	    	
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+back+")");
	    	
		}else if(whereFrom.equals("test")){

			System.out.println("school api:WF======test===============================================================");		
	    	Gson gson = new Gson();   	

	    	String School_searchKey=request.getParameter("School_searchKey");
	    	String sql=null;		
			String sqlcount=null;
			int countAllRS = 0;
			
//			��ҳ���� ���ڼ�ҳ��ÿҳ������Ĭ��ֵ��1��20��
			Integer pageNum = request.getParameter("pageNum") != null && !request.getParameter("pageNum").equals("") ? Integer.parseInt(request.getParameter("pageNum")) : 1;
			Integer OnePageNum = request.getParameter("OnePageNum") != null && !request.getParameter("OnePageNum").equals("") ? Integer.parseInt(request.getParameter("OnePageNum")) : 20;

			int pageNumX=(pageNum-1)*OnePageNum;
			
			List<School_model> list = new ArrayList<School_model>();			
			 sql="SELECT * from NSI_SCHOOL_data WHERE CONCAT(IFNULL(`School_name`,''),IFNULL(`Investment`,''),IFNULL(`remark`,''),IFNULL(`Areas`,''),IFNULL(`School_system`,''),IFNULL(`Course`,'')) like '%"+School_searchKey+"%' order by CONVERT(School_name USING gb2312) limit "+pageNumX+","+OnePageNum+"";
			 sqlcount="SELECT * from NSI_SCHOOL_data WHERE CONCAT(IFNULL(`School_name`,''),IFNULL(`Investment`,''),IFNULL(`remark`,''),IFNULL(`Areas`,''),IFNULL(`School_system`,''),IFNULL(`Course`,'')) like '%"+School_searchKey+"%' order by CONVERT(School_name USING gb2312)";
			
			list=School_DB.Search(sql);
			countAllRS=DB.count(sqlcount);

//			���Ӳ���
			Gson gson2 = new Gson(); 
		
	    	String jsonList =gson.toJson(list);
	    	String Callback = request.getParameter("Callback");//�ͻ����������
//	    	����Ĳ���   	    	
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+jsonList+")");
   	
		}else{
			System.out.println("school_api��û�յ�whereFrom����������");
		}
//		���к�ʱ����
//		long endTime = System.currentTimeMillis();    //��ȡ����ʱ��
//	    System.out.println("School_api�����ʱ��" + (endTime-startTime) + "ms");
//	    endTime = 0;
    }
    
    
}
