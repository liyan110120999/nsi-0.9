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

import Institution.Institution_DB;
import Institution.Institution_model;
import people.DB;
import school.School_DB;
import school.School_model;

@WebServlet("/Institution_api")
public class Institution_api extends HttpServlet{

	private static final long serialVersionUID = 8453496975387951011L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String whereFrom = null;
		whereFrom = request.getParameter("whereFrom");
    	
//		待测试
    	if(whereFrom.equals("insert")){
    		System.out.println("institution_api:WF======insert");
//			判断用户标志
			String member_sign00=request.getParameter("member_sign");
//			获取参数	
//			String Id=request.getParameter("Id");
			String Name=request.getParameter("Name");
			String Founded_time=request.getParameter("Founded_time");
			String Areas=request.getParameter("Areas");
			String Areas02=request.getParameter("Areas02");
			String Areas03=request.getParameter("Areas03");
			String Type=request.getParameter("Type");
			String Label=request.getParameter("Label");
			String Website=request.getParameter("Website");
			String Service=request.getParameter("Service");
			String ContactPosition=request.getParameter("ContactPosition");
			String ContactName=request.getParameter("ContactName");
			String ContactPhone=request.getParameter("ContactPhone");
			String ContactMail=request.getParameter("ContactMail");
			String Introduction=request.getParameter("Introduction");
			String Investment=request.getParameter("Investment");
			String Remark=request.getParameter("Remark");
			String ServedSchool=request.getParameter("ServedSchool");
		
			String load_people=request.getParameter("load_people");
//			String load_time=request.getParameter("load_time");
//			String BatchInput_Sign=request.getParameter("BatchInput_Sign");
//			String VerifySign=request.getParameter("VerifySign");
//			String Evaluate=request.getParameter("Evaluate");
			
//			当前时间
				java.util.Date currentTime = new java.util.Date(); 
		    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    	String SubmitDate = formatter.format(currentTime);

//			新增操作	
//	    	1、获取会员等级参数
//	    	2、需不需要审核？
//	    	3、插入新增标记
//	    	4、执行SQL
	    	System.out.println("新增用户等级member_sign:"+member_sign00);
	    	int member_sign=Integer.parseInt(member_sign00);
	    	String sql=null;
	
//			外部人员 存入审核数据库 @用户权限
//	    	区别：1、存入的数据库不一样，2、审核标记
//	    	新增标记
	    	int VerifySign=21;
			 if(member_sign<=7){
//				 注册用户需审核
				sql="REPLACE INTO NSI_institution_data_verify (Name, Founded_time, Areas, Areas02, Areas03, Type, Label, Website, Service, "
						+ "ContactPosition, ContactName, ContactPhone, ContactMail, Introduction, Investment, Remark, ServedSchool,load_people, load_time)"
						+ "VALUES ('"+Name+"','"+Founded_time+"','"+Areas+"','"+Areas02+"','"+Areas03+"','"+Type+"','"+Label+"','"+Website+"','"+Service+"' "
						+ " ,'"+ContactPosition+"','"+ContactName+"','"+ContactPhone+"','"+ContactMail+"','"+Introduction+"','"+Investment+"','"+Remark+"','"+ServedSchool+"','"+load_people+"','"+SubmitDate+"')";	 
				
				 
			}else{
//				内部员工免审核	
				sql="INSERT INTO NSI_institution_data (Name, Founded_time, Areas, Areas02, Areas03, Type, Label, Website, Service, "
						+ "ContactPosition, ContactName, ContactPhone, ContactMail, Introduction, Investment, Remark, ServedSchool,load_people, load_time)"
						+ "VALUES ('"+Name+"','"+Founded_time+"','"+Areas+"','"+Areas02+"','"+Areas03+"','"+Type+"','"+Label+"','"+Website+"','"+Service+"' "
						+ " ,'"+ContactPosition+"','"+ContactName+"','"+ContactPhone+"','"+ContactMail+"','"+Introduction+"','"+Investment+"','"+Remark+"','"+ServedSchool+"','"+load_people+"','"+SubmitDate+"')";	 
				
			}
			
			DB.Insert(sql);		
			String back="{msg:1}";
	    	String Callback = request.getParameter("Callback");//客户端请求参数	  	    	
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+back+")");
	    	
//    	待测试
    	}else if(whereFrom.equals("delete")) {
    		System.out.println("institution_api:WF======delete");
    		Gson gson = new Gson();   	
	    	String Id=request.getParameter("Id");
	    			    	
	    	String sql="DELETE FROM nsi_institution_data WHERE Id ='"+Id+"';";
	    	System.out.println(sql);
	    	
			DB.Delete(sql);	
//			成功
	    	String back="{msg:1}";

	    	String Callback = request.getParameter("Callback");//客户端请求参数	  	    	
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+back+")");
	    	System.out.println(Callback+"("+back+")");
    		
	    	
    	}else if(whereFrom.equals("alter")){
    		System.out.println("institution_api:WF======alter");
//			判断用户标志
			String member_sign00=request.getParameter("member_sign");
			int member_sign=Integer.parseInt(member_sign00);
//			旧数据的ID值 和 接收参数

			String alter_old_Institution_id=request.getParameter("alter_old_Institution_id");
			String Name=request.getParameter("Name");
			String Founded_time=request.getParameter("Founded_time");
			String Areas=request.getParameter("Areas");
			String Areas02=request.getParameter("Areas02");
			String Areas03=request.getParameter("Areas03");
			String Type=request.getParameter("Type");
			String Label=request.getParameter("Label");
			String Website=request.getParameter("Website");
			String Service=request.getParameter("Service");
			String ContactPosition=request.getParameter("ContactPosition");
			String ContactName=request.getParameter("ContactName");
			String ContactPhone=request.getParameter("ContactPhone");
			String ContactMail=request.getParameter("ContactMail");
			String Introduction=request.getParameter("Introduction");
			String Investment=request.getParameter("Investment");
			String Remark=request.getParameter("Remark");
			String ServedSchool=request.getParameter("ServedSchool");
		
			String load_people=request.getParameter("load_people");
			
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
				sql="";
				
				DB.Insert(sql);
			}else{
//				内部员工免审核 更新到数据表
				sql="";
				
				list=School_DB.alter(sql);	
			}
			String back="{msg:1}";
	    	String Callback = request.getParameter("Callback");//客户端请求参数	  	    	
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+back+")");
    		
	    	
//	    	完成
    	}else if(whereFrom.equals("search")){
    		System.out.println("institution_api:WF======search");	
    		
    	 	Gson gson = new Gson();   	
	    	String Institution_searchKey=request.getParameter("Institution_searchKey");
	    	String sql=null;		
			String sqlcount=null;
			int countAllRS = 0;
			
//			分页参数 ：第几页、每页几个。默认值：1、20；
			Integer pageNum = request.getParameter("pageNum") != null && !request.getParameter("pageNum").equals("") ? Integer.parseInt(request.getParameter("pageNum")) : 1;
			Integer OnePageNum = request.getParameter("OnePageNum") != null && !request.getParameter("OnePageNum").equals("") ? Integer.parseInt(request.getParameter("OnePageNum")) : 20;

			int pageNumX=(pageNum-1)*OnePageNum;
			
			List<Institution_model> list = new ArrayList<Institution_model>();			
			 sql="SELECT * from NSI_Institution_data WHERE CONCAT(IFNULL(`Id`,''),IFNULL(`Name`,''),IFNULL(`Label`,''),IFNULL(`Areas`,''),IFNULL(`Areas02`,''),IFNULL(`Areas03`,''),IFNULL(`Type`,''),IFNULL(`Service`,'')) like '%"+Institution_searchKey+"%' order by CONVERT(Name USING gb2312) limit "+pageNumX+","+OnePageNum+"";
//			 sqlcount="SELECT * from NSI_Institution_data WHERE CONCAT(IFNULL(`Id`,''),IFNULL(`Name`,''),IFNULL(`Label`,''),IFNULL(`Areas`,''),IFNULL(`Areas02`,''),IFNULL(`Areas03`,''),IFNULL(`Type`,''),IFNULL(`Service`,'')) like '%"+Institution_searchKey+"%' order by CONVERT(Name USING gb2312)";
									
			list=Institution_DB.Search(sql);
//			countAllRS=DB.count(sqlcount);
	    	String jsonList =gson.toJson(list);

	    	String Callback = request.getParameter("Callback");//客户端请求参数
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+jsonList+")");
    		
		}else if(whereFrom.equals("count")){
			System.out.println("institution_api:WF======count");
			
			Gson gson = new Gson();   	
	    	String Institution_searchKey=request.getParameter("Institution_searchKey");	
			String sqlcount=null;
			int countAllRS = 0;
						
			 sqlcount="SELECT * from NSI_Institution_data WHERE CONCAT(IFNULL(`Id`,''),IFNULL(`Name`,''),IFNULL(`Label`,''),IFNULL(`Areas`,''),IFNULL(`Areas02`,''),IFNULL(`Areas03`,''),IFNULL(`Type`,''),IFNULL(`Service`,'')) like '%"+Institution_searchKey+"%' order by CONVERT(Name USING gb2312)";
							
			countAllRS=DB.count(sqlcount);
			String back="{countAllRS:"+countAllRS+"}";
			String Callback = request.getParameter("Callback");//客户端请求参数	  	    	
			response.setContentType("text/html;charset=UTF-8");  
			response.getWriter().write(Callback+"("+back+")");
		
//			完成
		}else if(whereFrom.equals("detail")){
			System.out.println("institution_api:WF======detail");
			
			Gson gson = new Gson();   	
	    	String Id=request.getParameter("Id");
	    	String sql=null;		
		
			List<Institution_model> list = new ArrayList<Institution_model>();			
			 sql="SELECT * from NSI_Institution_data WHERE ID= '"+Id+"' ";
			 
			System.out.println("institution_api：detail语句：机构Id:"+Id);		
			list=Institution_DB.Search(sql);
	    	String jsonList =gson.toJson(list);
	    	String Callback = request.getParameter("Callback");//客户端请求参数
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+jsonList+")");
    	}
    	
    }
}
