package api;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Model;
import people.DB;
import school.School_Areas_model;
import school.School_DB;
import school.School_model;
import user.User_DB;
import user.User_model;

@WebServlet("/Admin_api")
public class Admin_api extends HttpServlet{

	private static final long serialVersionUID = 4592738530208345077L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String whereFrom = null;
		whereFrom = request.getParameter("whereFrom");
    	
    	if(whereFrom.equals("Staff_MonthlyCount_school")){
//    		1��ȡ�� Ҫͳ�Ƶ��·�
//    		2���õ�Ա�������б�
//    		3������ �б� ��ѯ������
    		
    		System.out.println("school api:WF======Staff_MonthlyCount_school");		
	    	Gson gson = new Gson();   	
	
	    	String Month_Time_Key=request.getParameter("Month_Time_Key");
	    	String sql=null;		
			String sqlcount=null;
			int countAllRS = 0;
			
			List<User_model> list = new ArrayList<User_model>();			
			 sql="SELECT * from nsi_user WHERE member_sign > 3";

			System.out.println("Admin_api������Ա����䣺"+sql);
			list=User_DB.Search(sql);
			
//			����School_Areas_model�ļ�ֵģ��
			List<School_Areas_model> list02=new ArrayList<School_Areas_model>();
			
			
			for(int i = 0; i<list.size(); i++){
				String name=list.get(i).getName();
				sqlcount="SELECT * from NSI_SCHOOL_data WHERE load_people ='"+name+"' AND load_time like '%"+Month_Time_Key+"%' ";					
				countAllRS=DB.count(sqlcount);
				
				School_Areas_model School_Areas= new School_Areas_model();
				
				School_Areas.setName(name);
				School_Areas.setValue(countAllRS);

				list02.add(School_Areas);
			}
			
	    	String jsonList =gson.toJson(list02);
	    	String Callback = request.getParameter("Callback");//�ͻ����������
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+jsonList+")");
	    	
//	    ��Ա¼����Ϣͳ��
    	}else if(whereFrom.equals("Staff_MonthlyCount_people")){
//	    		1��ȡ�� Ҫͳ�Ƶ��·�
//	    		2���õ�Ա�������б�
//	    		3������ �б� ��ѯ������
	    		
	    		System.out.println("school api:WF======Staff_MonthlyCount_people");		
		    	Gson gson = new Gson();   	
		
		    	String Month_Time_Key=request.getParameter("Month_Time_Key");
		    	String sql=null;		
				String sqlcount=null;
				int countAllRS = 0;
				
				List<User_model> list = new ArrayList<User_model>();			
				 sql="SELECT * from nsi_user WHERE member_sign > 3";

				System.out.println("Admin_api������Ա����䣺"+sql);
				list=User_DB.Search(sql);
				
//				����School_Areas_model�ļ�ֵģ��
				List<School_Areas_model> list02=new ArrayList<School_Areas_model>();
								
				for(int i = 0; i<list.size(); i++){
					String name=list.get(i).getName();
					sqlcount="SELECT * from NSI_people_data WHERE People_loadPeople ='"+name+"' AND People_loadtime like '%"+Month_Time_Key+"%' ";					
					countAllRS=DB.count(sqlcount);
					
					School_Areas_model School_Areas= new School_Areas_model();
					
					School_Areas.setName(name);
					School_Areas.setValue(countAllRS);

					list02.add(School_Areas);
				}				
		    	String jsonList =gson.toJson(list02);
		    	String Callback = request.getParameter("Callback");//�ͻ����������
		    	response.setContentType("text/html;charset=UTF-8");  
		    	response.getWriter().write(Callback+"("+jsonList+")");
		    	System.out.println(Callback+jsonList);
		
//		  ������Ϣ¼��ͳ��
    	}else if(whereFrom.equals("Staff_MonthlyCount_institution")){
//    		1��ȡ�� Ҫͳ�Ƶ��·�
//    		2���õ�Ա�������б�
//    		3������ �б� ��ѯ������
    		
    		System.out.println("school api:WF======Staff_MonthlyCount_institution");		
	    	Gson gson = new Gson();   	
	
	    	String Month_Time_Key=request.getParameter("Month_Time_Key");
	    	String sql=null;		
			String sqlcount=null;
			int countAllRS = 0;
			
			List<User_model> list = new ArrayList<User_model>();			
			 sql="SELECT * from nsi_user WHERE member_sign > 3";

			System.out.println("Admin_api������Ա����䣺"+sql);
			list=User_DB.Search(sql);
			
//			����School_Areas_model�ļ�ֵģ��
			List<School_Areas_model> list02=new ArrayList<School_Areas_model>();
							
			for(int i = 0; i<list.size(); i++){
				String name=list.get(i).getName();
				sqlcount="SELECT * from NSI_institution_data WHERE Load_people ='"+name+"' AND Load_time like '%"+Month_Time_Key+"%' ";					
				countAllRS=DB.count(sqlcount);
				
				School_Areas_model School_Areas= new School_Areas_model();				
				School_Areas.setName(name);
				School_Areas.setValue(countAllRS);

				list02.add(School_Areas);
			}				
	    	String jsonList =gson.toJson(list02);
	    	String Callback = request.getParameter("Callback");//�ͻ����������
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+jsonList+")");

	    	
//	    ע���û�ͳ��	
	    }else if(whereFrom.equals("RegisterPeopleCount")){	    	
			System.out.println("Admin api:WF======RegisterPeopleCount");		
			
//			��ǰʱ��
				java.util.Date currentTime = new java.util.Date(); 
		    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    	String SubmitDate = formatter.format(currentTime);
//	    	��������
		    	Calendar cal = Calendar.getInstance();
		    	cal.add(Calendar.DATE, -1);
	    	String Yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	    			
			String sqlcount01="SELECT * from NSI_user WHERE `Load_time`= '"+SubmitDate+"' ;";
			String sqlcount02="SELECT * from NSI_user WHERE `Load_time`= '"+Yesterday+"' ;";
			String sqlcount03="SELECT * from NSI_user ;";
				
			int countAllRS01=DB.count(sqlcount01);
			int countAllRS02=DB.count(sqlcount02);
			int countAllRS03=DB.count(sqlcount03);
//			test
			System.out.println("ע���û�ͳ��:"+SubmitDate+";"+Yesterday+";"+countAllRS01+";"+countAllRS02+";"+countAllRS03);
			
			String back="{countAllRS01:"+countAllRS01+",countAllRS02:"+countAllRS02+",countAllRS03:"+countAllRS03+"}";
			String Callback = request.getParameter("Callback");//�ͻ����������	  	    	
			response.setContentType("text/html;charset=UTF-8");  
			response.getWriter().write(Callback+"("+back+")");

	    	
	    }else if(whereFrom.equals("search")){
	    	
			System.out.println("school api:WF======search");
			
	    	Gson gson = new Gson();   	
	
	    	String School_searchKey=request.getParameter("School_searchKey");
	    	String sql=null;		
			String sqlcount=null;
			int countAllRS = 0;
			
	//		��ҳ���� ���ڼ�ҳ��ÿҳ������Ĭ��ֵ��1��20��
			Integer pageNum = request.getParameter("pageNum") != null && !request.getParameter("pageNum").equals("") ? Integer.parseInt(request.getParameter("pageNum")) : 1;
			Integer OnePageNum = request.getParameter("OnePageNum") != null && !request.getParameter("OnePageNum").equals("") ? Integer.parseInt(request.getParameter("OnePageNum")) : 20;
	
			int pageNumX=(pageNum-1)*OnePageNum;
			
			List<School_model> list = new ArrayList<School_model>();			
			 sql="SELECT * from NSI_SCHOOL_data WHERE CONCAT(IFNULL(`School_name`,''),IFNULL(`Investment`,''),IFNULL(`remark`,''),IFNULL(`Areas`,''),IFNULL(`School_system`,''),IFNULL(`Course`,'')) like '%"+School_searchKey+"%' order by CONVERT(School_name USING gb2312) limit "+pageNumX+","+OnePageNum+"";
			 sqlcount="SELECT * from NSI_SCHOOL_data WHERE CONCAT(IFNULL(`School_name`,''),IFNULL(`Investment`,''),IFNULL(`remark`,''),IFNULL(`Areas`,''),IFNULL(`School_system`,''),IFNULL(`Course`,'')) like '%"+School_searchKey+"%' order by CONVERT(School_name USING gb2312)";
			System.out.println("school��������䣺"+sql);
			
			list=School_DB.Search(sql);
			countAllRS=DB.count(sqlcount);
	    	String jsonList =gson.toJson(list);
	
	    	String Callback = request.getParameter("Callback");//�ͻ����������
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+jsonList+")");
	    	System.out.println(Callback+jsonList);
	    	
	    	
//	    	���Խӿڣ����ڲ���ͨ��ͼƬ�ϴ�
	    }else if(whereFrom.equals("test")){
	    	
			System.out.println("school api:WF======test");	
	    	Gson gson = new Gson();   	
	
	    	String FileType=request.getParameter("FileType");
	    	String UserMail=request.getParameter("UserMail");
	    	String User_TureName=request.getParameter("User_TureName");
	    	String sql=request.getParameter("sql");
	    	
	    	int i=model.Model.UpFileTool(FileType, UserMail, User_TureName, sql, request);
	    	System.out.println("test,upload:"+i);
	    	String back="{msg:"+i+"}";
	    	
	    	String Callback = request.getParameter("Callback");//�ͻ����������
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+back+")");

	    }else {
			System.out.println("WF��������δִ��");
		}
    }
    }
