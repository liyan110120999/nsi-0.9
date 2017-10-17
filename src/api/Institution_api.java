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


@WebServlet("/Institution_api")
public class Institution_api extends HttpServlet{

	private static final long serialVersionUID = 8453496975387951011L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String whereFrom = null;
		whereFrom = request.getParameter("whereFrom");
    	
//		������
    	if(whereFrom.equals("insert")){
    		System.out.println("institution_api:WF======insert");
//			�ж��û���־
			String member_sign00=request.getParameter("member_sign");
//			��ȡ����	
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
			
//			��ǰʱ��
				java.util.Date currentTime = new java.util.Date(); 
		    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    	String SubmitDate = formatter.format(currentTime);

//			��������	
//	    	1����ȡ��Ա�ȼ�����
//	    	2���費��Ҫ��ˣ�
//	    	3�������������
//	    	4��ִ��SQL
	    	System.out.println("�����û��ȼ�member_sign:"+member_sign00+"label:"+Label);
	    	int member_sign=Integer.parseInt(member_sign00);
	    	String sql=null;
	
//			�ⲿ��Ա ����������ݿ� @�û�Ȩ��
//	    	����1����������ݿⲻһ����2����˱��
//	    	�������
	    	int VerifySign=21;
			 if(member_sign<=7){
//				 ע���û������
				sql="REPLACE INTO NSI_institution_data_verify (Name, Founded_time, Areas, Areas02, Areas03, Type, Label, Website, Service, "
						+ "ContactPosition, ContactName, ContactPhone, ContactMail, Introduction, Investment, Remark, ServedSchool,load_people, load_time, VerifySign)"
						+ "VALUES ('"+Name+"','"+Founded_time+"','"+Areas+"','"+Areas02+"','"+Areas03+"','"+Type+"','"+Label+"','"+Website+"','"+Service+"' "
						+ " ,'"+ContactPosition+"','"+ContactName+"','"+ContactPhone+"','"+ContactMail+"','"+Introduction+"','"+Investment+"','"+Remark+"','"+ServedSchool+"','"+load_people+"','"+SubmitDate+"','"+VerifySign+"')";	 
							 
			}else{
//				�ڲ�Ա�������	
				sql="INSERT INTO NSI_institution_data (Name, Founded_time, Areas, Areas02, Areas03, Type, Label, Website, Service, "
						+ "ContactPosition, ContactName, ContactPhone, ContactMail, Introduction, Investment, Remark, ServedSchool,load_people, load_time, VerifySign)"
						+ "VALUES ('"+Name+"','"+Founded_time+"','"+Areas+"','"+Areas02+"','"+Areas03+"','"+Type+"','"+Label+"','"+Website+"','"+Service+"' "
						+ " ,'"+ContactPosition+"','"+ContactName+"','"+ContactPhone+"','"+ContactMail+"','"+Introduction+"','"+Investment+"','"+Remark+"','"+ServedSchool+"','"+load_people+"','"+SubmitDate+"','"+VerifySign+"')";	 
				
			}
			
			DB.Insert(sql);		
			String back="{msg:1}";
	    	String Callback = request.getParameter("Callback");//�ͻ����������	  	    	
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+back+")");
	    	
//    	������
    	}else if(whereFrom.equals("delete")) {
    		System.out.println("institution_api:WF======delete");
    		Gson gson = new Gson();   	
	    	String Id=request.getParameter("Id");
	    			    	
	    	String sql="DELETE FROM nsi_institution_data WHERE Id ='"+Id+"';";
	    	System.out.println(sql);
	    	
			DB.Delete(sql);	
//			�ɹ�
	    	String back="{msg:1}";

	    	String Callback = request.getParameter("Callback");//�ͻ����������	  	    	
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+back+")");
	    	System.out.println(Callback+"("+back+")");
    		
//	    	������
    	}else if(whereFrom.equals("alter")){
    		System.out.println("institution_api:WF======alter");
//			�ж��û���־
			String member_sign00=request.getParameter("member_sign");
			int member_sign=Integer.parseInt(member_sign00);
//			�����ݵ�IDֵ �� ���ղ���

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
			
//			��ǰʱ��
				java.util.Date currentTime = new java.util.Date(); 
		    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    	String SubmitDate = formatter.format(currentTime);
	    	
//	    	�޸ı��
	    	int VerifySign=22;
	    	String sql=null;
			Gson gson = new Gson();  
//			????--------------------------------------------------------------------------
			List<Institution_model> list = new ArrayList<Institution_model>();
						
			if(member_sign<=7){			
//				���뵽������ݱ�
				sql="REPLACE INTO NSI_institution_data_verify (Id, Name, Founded_time, Areas, Areas02, Areas03, Type, Label, Website, Service, ContactPosition, ContactName, ContactPhone, ContactMail, Introduction, Investment, Remark, ServedSchool,load_people, load_time,VerifySign)"
					+" VALUES (  '"+alter_old_Institution_id+"','"+Name+"','"+Founded_time+"','"+Areas+"','"+Areas02+"','"+Areas03+"','"+Type+"','"+Label+"','"+Website+"','"+Service+"','"+ContactPosition+"','"+ContactName+"','"+ContactPhone+"','"+ContactMail+"','"+Introduction+"','"+Investment+"','"+Remark+"','"+ServedSchool+"','"+load_people+"','"+SubmitDate+"','"+VerifySign+"' )";
				DB.Insert(sql);
			}else{
//				�ڲ�Ա������� ���µ����ݱ�
				sql="UPDATE NSI_institution_data SET Name ='"+Name+"',Founded_time ='"+Founded_time+"',Areas ='"+Areas+"',Areas02 ='"+Areas02+"',Areas03 ='"+Areas03+"',Type ='"+Type+"',Label ='"+Label+"',Website ='"+Website+"',Service ='"+Service+"',ContactPosition ='"+ContactPosition+"',ContactName ='"+ContactName+"',ContactPhone ='"+ContactPhone+"',ContactMail ='"+ContactMail+"',"
						+ " Introduction ='"+Introduction+"',Investment ='"+Investment+"',Remark ='"+Remark+"',ServedSchool ='"+ServedSchool+"',load_people ='"+load_people+"',load_time ='"+SubmitDate+"',VerifySign ='"+VerifySign+"' WHERE Id ='"+alter_old_Institution_id+"'; ";
				
				list=Institution_DB.alter(sql);	
			}
			String back="{msg:1}";
	    	String Callback = request.getParameter("Callback");//�ͻ����������	  	    	
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+back+")");
    		
	    	
//	    	���
    	}else if(whereFrom.equals("search")){
    		System.out.println("institution_api:WF======search");	
    		
    	 	Gson gson = new Gson();   	
	    	String Institution_searchKey=request.getParameter("Institution_searchKey");
	    	String sql=null;		
			String sqlcount=null;
			int countAllRS = 0;
			
//			��ҳ���� ���ڼ�ҳ��ÿҳ������Ĭ��ֵ��1��20��
			Integer pageNum = request.getParameter("pageNum") != null && !request.getParameter("pageNum").equals("") ? Integer.parseInt(request.getParameter("pageNum")) : 1;
			Integer OnePageNum = request.getParameter("OnePageNum") != null && !request.getParameter("OnePageNum").equals("") ? Integer.parseInt(request.getParameter("OnePageNum")) : 20;

			int pageNumX=(pageNum-1)*OnePageNum;
			
			List<Institution_model> list = new ArrayList<Institution_model>();			
			 sql="SELECT * from NSI_Institution_data WHERE CONCAT(IFNULL(`Id`,''),IFNULL(`Name`,''),IFNULL(`Label`,''),IFNULL(`Areas`,''),IFNULL(`Areas02`,''),IFNULL(`Areas03`,''),IFNULL(`Type`,''),IFNULL(`Service`,'')) like '%"+Institution_searchKey+"%' order by load_time DESC limit "+pageNumX+","+OnePageNum+"";
//			 sqlcount="SELECT * from NSI_Institution_data WHERE CONCAT(IFNULL(`Id`,''),IFNULL(`Name`,''),IFNULL(`Label`,''),IFNULL(`Areas`,''),IFNULL(`Areas02`,''),IFNULL(`Areas03`,''),IFNULL(`Type`,''),IFNULL(`Service`,'')) like '%"+Institution_searchKey+"%' order by CONVERT(Name USING gb2312)";
									
			list=Institution_DB.Search(sql);
//			countAllRS=DB.count(sqlcount);
	    	String jsonList =gson.toJson(list);

	    	String Callback = request.getParameter("Callback");//�ͻ����������
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
			String Callback = request.getParameter("Callback");//�ͻ����������	  	    	
			response.setContentType("text/html;charset=UTF-8");  
			response.getWriter().write(Callback+"("+back+")");
		
//			���
		}else if(whereFrom.equals("detail")){
			System.out.println("institution_api:WF======detail");
			
			Gson gson = new Gson();   	
	    	String Id=request.getParameter("Id");
			
			List<Institution_model> list = new ArrayList<Institution_model>();			
			String sql="SELECT * from NSI_Institution_data WHERE ID= '"+Id+"' ";
			 
			System.out.println("institution_api��detail��䣺����Id:"+Id);		
			list=Institution_DB.Search(sql);
	    	String jsonList =gson.toJson(list);
	    	String Callback = request.getParameter("Callback");//�ͻ����������
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+jsonList+")");

	//    	������Ϣ���  ������
		}else if(whereFrom.equals("verify_insert")){
	//		1�����ܲ��� ID
	//		2��������˱��У����Ƹ�ID��Ŀ�������ݱ�
	//		3���޸���˱��� p21
	//		3�����سɹ�
			System.out.println("institution api:WF=====verify_insert");	
	    	Gson gson = new Gson();   	
	    	String institution_Id00=request.getParameter("institution_Id");
	    	int institution_Id=Integer.parseInt(institution_Id00);
	    	
	    	System.out.println("���ܵĲ�����"+institution_Id);
	    	
		    	String sql="INSERT INTO nsi_institution_data ( Name, Founded_time, Areas, Areas02, Areas03, Type, Label, Website, Service, ContactPosition, ContactName, ContactPhone, ContactMail, Introduction, Investment, Remark, ServedSchool, load_people, load_time, BatchInput_Sign, VerifySign ) "
		    	
		    			+ " SELECT  `Name`,`Founded_time`,`Areas`,`Areas02`,`Areas03`,`Type`,`Label`,`Website`,`Service`,`ContactPosition`,`ContactName`,`ContactPhone`,`ContactMail`,`Introduction`,`Investment`,`Remark`,`ServedSchool`,`load_people`,`load_time`,`BatchInput_Sign`,`VerifySign` "
		    			+ " FROM nsi_institution_data_verify where Id = '"+institution_Id+"' ";
		    	
		    	String sql02="UPDATE nsi_institution_data_verify SET VerifySign='p21' where Id= '"+institution_Id+"' ;";
		    			    
	//    	��������SQLͬʱ ִ�С��д���ع�
	    	int i=DB.TransactionInsert(sql, sql02);
	//		�ɹ�
	    	String back="{msg:"+i+"}";
	    	System.out.println("institution_api:��˲�������"+i+" ");
	    	String Callback = request.getParameter("Callback");//�ͻ����������	  	    	
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+back+")");
	   
	//    	��˲��� ��ͨ��
		}else if(whereFrom.equals("verify_No_insert")){
	//		1�����ܲ��� ID
	//		2��ѧУ��˱��У����Ƹ�ID��Ŀ�������ݱ�
	//		3���޸���˱��� n21
	//		3�����سɹ�
			System.out.println("institution api:WF=====verify_insert");	
	    	Gson gson = new Gson();   	
	    	String institution_Id00=request.getParameter("institution_Id");
	    	int institution_Id=Integer.parseInt(institution_Id00);
	    	int i=-2;
	    			    	
		    	String sql="UPDATE nsi_institution_data_verify SET VerifySign='n21' where Id= '"+institution_Id+"' ;";    	
			try {
				DB.Insert(sql);
				i=1;
			} catch (Exception e) {
				i=-1;
			}
		    	
	//		�ɹ�
	    	String back="{msg:"+i+"}";
	    	System.out.println("institution_api:��˲�������"+i+" ");
	    	String Callback = request.getParameter("Callback");//�ͻ����������	  	    	
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+back+")");	
	     
	    	
	    	
//	    	�޸���Ϣ��� wait for test
		}else if(whereFrom.equals("verify_alter")){
//			1�����ܲ��� ID
//			2��������˱��У����Ƹ�ID��Ŀ�������ݱ�
//			3���޸� ��˱��еĸ����ݵı��
//			3�����سɹ�
			System.out.println("Institution api:WF=====verify_insert");	
	    	Gson gson = new Gson();   	
	    	String institution_Id00=request.getParameter("institution_Id");
	    	int institution_Id=Integer.parseInt(institution_Id00);
	    	
	    	int i=00;
	       	
	    	String sql=" REPLACE into nsi_institution_data ( `Id`,`Name`,`Founded_time`,`Areas`,`Areas02`,`Areas03`,`Type`,`Label`,`Website`,`Service`,`ContactPosition`,`ContactName`,`ContactPhone`,`ContactMail`,`Introduction`,`Investment`,`Remark`,`ServedSchool`,`load_people`,`load_time`,`VerifySign` )"
	    				+"SELECT `Id`,`Name`,`Founded_time`,`Areas`,`Areas02`,`Areas03`,`Type`,`Label`,`Website`,`Service`,`ContactPosition`,`ContactName`,`ContactPhone`,`ContactMail`,`Introduction`,`Investment`,`Remark`,`ServedSchool`,`load_people`,`load_time`,`VerifySign` "
	    				+"FROM nsi_institution_data_verify where Id = '"+institution_Id+"'  ";
	    	
	    	String sql02="UPDATE nsi_institution_data_verify SET VerifySign='p22' where Id= '"+institution_Id+"' ;";
//	    	��������SQLͬʱ ִ�С��д���ع�
	    	i=DB.TransactionInsert(sql, sql02);
//			I Ϊִ�н��
	    	System.out.println("verify_alter��msgֵΪ��"+i);
	    	String back="{msg:"+i+"}";
	    	String Callback = request.getParameter("Callback");//�ͻ����������	  	    	
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+back+")");
	    
//	    	����޸� ��ͨ�� wait for test 
		}else if(whereFrom.equals("verify_No_alter")){
//			1�����ܲ��� ID
//			2��������˱��У����Ƹ�ID��Ŀ�������ݱ�
//			3���޸���˱��� p22
//			3�����سɹ�
			System.out.println("institution api:WF=====verify_No_alter");	
	    	Gson gson = new Gson();   	
	    	String institution_Id00=request.getParameter("institution_Id");
	    	int institution_Id=Integer.parseInt(institution_Id00);
	    	int i=-2;
	    			    	
		    	String sql="UPDATE nsi_institution_data_verify SET VerifySign='n22' where Id= '"+institution_Id+"' ;";    	
			try {
				DB.Insert(sql);
				i=1;
			} catch (Exception e) {
				i=-1;
			}
		    	
//			�ɹ�
	    	String back="{msg:"+i+"}";
	    	System.out.println("institution_api:��˲�������"+i+" ");
	    	String Callback = request.getParameter("Callback");//�ͻ����������	  	    	
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+back+")");	
	    
//	    	���������Ƿ��ظ�
		}else if(whereFrom.equals("Check_InstitutionName")){	
			
	    	String Name=request.getParameter("Name");
			String sql="SELECT * FROM NSI_institution_data WHERE Name='"+Name+"' ";
			System.out.println("�������Ƿ���ڣ�"+Name);
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
	    	
	    	
		}else {
			System.out.println("institution_api:û���յ�WF����");
		}
    }
}
