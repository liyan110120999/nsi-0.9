package api;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import people.Talent_model;
import school.School_DB;
import school.School_model;
import sun.security.util.Length;

@WebServlet("/talent_api")
public class talent_api extends HttpServlet{

	private static final long serialVersionUID = 8790136597250852386L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String whereFrom = null;
		whereFrom = request.getParameter("whereFrom");
		
//		�ɸ��ǵ���ӻ�����Ϣ
		if(whereFrom.equals("insert")){
			System.out.println("talent_api:WF=====insert");
		 
			String Name=request.getParameter("Name");
			String Sex=request.getParameter("Sex");
			String Phone=request.getParameter("Phone");
			String Mail=request.getParameter("Mail");
			String Education=request.getParameter("Education");
			String Major=request.getParameter("Major");
			String NowWorkplace=request.getParameter("NowWorkplace");
			String WorkYear=request.getParameter("WorkYear");
			String ExpectWorkPlace=request.getParameter("ExpectWorkPlace");
			String ExpectWorkPosition=request.getParameter("ExpectWorkPosition");
			String ExpectSalary=request.getParameter("ExpectSalary");
			String Other=request.getParameter("Other");
			String WorkExperience=request.getParameter("WorkExperience");
			String EducationBackground=request.getParameter("EducationBackground");
			String TrainingBackground=request.getParameter("TrainingBackground");
			String Public=request.getParameter("Public");
			String UserMail=request.getParameter("UserMail");
//			String Load_time=request.getParameter("Load_time");

			
//			��ǰʱ��
				java.util.Date currentTime = new java.util.Date(); 
		    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	String SubmitDate = formatter.format(currentTime);
	    	
//	    	�������ܷ񸲸Ǿ���Ϣ
	    	String sql="REPLACE INTO nsi_talent (Name,Sex,Phone,"
				+ "Mail,Education,Major,NowWorkplace,WorkYear,ExpectWorkPlace,ExpectWorkPosition,ExpectSalary,Other,WorkExperience,EducationBackground,TrainingBackground,Public,UserMail,Load_time) " 					 		
				+ "VALUES ('"+Name+"','"+Sex+"','"+Phone+"','"+Mail+"','"+Education+"','"+Major+"','"+NowWorkplace+"','"+WorkYear+"','"+ExpectWorkPlace+"' "
						+ ",'"+ExpectWorkPosition+"','"+ExpectSalary+"','"+Other+"','"+WorkExperience+"','"+EducationBackground+"','"+TrainingBackground+"','"+Public+"','"+UserMail+"','"+SubmitDate+"' ) ";		
		    	
	    	DB.Insert(sql);
			String back="{msg:1}";
	    	String Callback = request.getParameter("Callback");//�ͻ����������	  	    	
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+back+")");
	    	
	    	
	    	
	    	
	    	
//	-----------------------------   δ��� 	-----------------------------------------
	    	
	    	
//	    	����
		}else if(whereFrom.equals("search")){
			System.out.println("talent_api:WF=====search");		
		   	Gson gson = new Gson();   	
		   		
	    	String talent_searchKey=request.getParameter("talent_searchKey");
	    	String sql=null;		
			
//			��ҳ���� ���ڼ�ҳ��ÿҳ������Ĭ��ֵ��1��20��
			Integer pageNum = request.getParameter("pageNum") != null && !request.getParameter("pageNum").equals("") ? Integer.parseInt(request.getParameter("pageNum")) : 1;
			Integer OnePageNum = request.getParameter("OnePageNum") != null && !request.getParameter("OnePageNum").equals("") ? Integer.parseInt(request.getParameter("OnePageNum")) : 20;
			int pageNumX=(pageNum-1)*OnePageNum;
//			����ʽ sort
			String SortType = request.getParameter("SortType") != null && !request.getParameter("SortType").equals("") ? request.getParameter("SortType") : "DownTime";
			
			
			
			List<School_model> list = new ArrayList<School_model>();			
			 sql="SELECT * from NSI_SCHOOL_data WHERE CONCAT(IFNULL(`Id`,''),IFNULL(`School_name`,''),IFNULL(`School_EnglishName`,''),IFNULL(`Areas`,''),IFNULL(`Areas02`,''),IFNULL(`Founded_time`,'')) like '%"+talent_searchKey+"%' order by Load_Time DESC limit "+pageNumX+","+OnePageNum+"";
//					
			list=School_DB.Search(sql);
//		
	    	String jsonList =gson.toJson(list);

	    	String Callback = request.getParameter("Callback");//�ͻ����������
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+jsonList+")");
	    	
	    
	    	
	    	
	    	
	    	
	    	
    	
	//	   ����,�����û����䣬���ؼ���listֵ
		}else if(whereFrom.equals("detail")){
			System.out.println("talent_api:WF=====detail");		
		   	Gson gson = new Gson();   	
		   	String UserMail=request.getParameter("UserMail");			
			List<Talent_model> list = new ArrayList<Talent_model>();			
			String sql="SELECT * from nsi_talent WHERE UserMail='"+UserMail+"' order by Load_Time DESC limit 0,1";			 	
			list=DB.SearchTalent(sql);
		   	String jsonList =gson.toJson(list);
		   	String Callback = request.getParameter("Callback");//�ͻ����������
		   	response.setContentType("text/html;charset=UTF-8");  
		   	response.getWriter().write(Callback+"("+jsonList+")");
	    	
	    	
//    	�ϴ����� 
		}else if(whereFrom.equals("UpResume")) {
			System.out.println("talent api:WF======UpResume�ϴ�����");
			// �ϴ��ļ��洢Ŀ¼
//		    final String UPLOAD_DIRECTORY = "upload";	 
		    // �ϴ�����
		    final int MEMORY_THRESHOLD   = 1024 * 1024 * 5;  // 5MB
		    final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
		    final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
//		    	�����ļ���������+����
		    	String UserMail=request.getParameter("UserMail");
		    	String User_TureName=request.getParameter("User_TureName");
		    	
		    	System.out.println("talent_api:�����ϴ����û�����:"+User_TureName+UserMail);
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
	//	        String uploadPath = getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;
		        String uploadPath = "C:" + File.separator+"upFile"+ File.separator+"talent"+File.separator;		     
		        // ���Ŀ¼�������򴴽�
		        File uploadDir = new File(uploadPath);
		        if (!uploadDir.exists()) {
		            uploadDir.mkdir();
		        }	        
		        int i=-2;
		        try {
		            // ���������������ȡ�ļ�����
	//	        	���Ծ���������Ϣ
	//	            @SuppressWarnings("unchecked")
		            List<FileItem> formItems = upload.parseRequest(request);
		            if (formItems != null && formItems.size() > 0) {
		                // ����������
		                for (FileItem item : formItems) {
		                    // �����ڱ��е��ֶ�
		                    if (!item.isFormField()) {
		                    	
//		                    	��ȡ�ϴ��ļ���,FormatNameΪ�ļ���ʽ
		                    	String fileFormat=item.getName();
		                    	//FormatName���㣻FormatName02������
		                    	String FormatName = fileFormat.substring(fileFormat.lastIndexOf("."));
		                    	String FormatName02 = fileFormat.substring(fileFormat.lastIndexOf(".")+1);
		                    	
		                        String filePath = uploadPath + UserMail+User_TureName+FormatName;
		                        File storeFile = new File(filePath);                                           
		                        // �ڿ���̨����ļ����ϴ�·��                     
		                        System.out.println("�����ϴ���ַ��"+filePath);
		                        // �����ļ���Ӳ��
		                        item.write(storeFile);
		                        i=1;
//		                        �޸� ���û�nsi_talent���е�havaTalent�ֶ�Ϊ1
		                        String sql="UPDATE nsi_talent SET HavaTalent = '"+FormatName02+"' WHERE UserMail='"+UserMail+"'; ";
		                        DB.alter(sql);
		                    }
		                }
		            }
		        } catch (Exception ex) {
		            request.setAttribute("message","������Ϣ: " + ex.getMessage());
		            i=-1;
		        }		        
	//			�ɹ�
		    	String back="{msg:"+i+"}";	
		    	String Callback = request.getParameter("Callback");//�ͻ����������	  	    	
		    	response.setContentType("text/html;charset=UTF-8");  
		    	response.getWriter().write(Callback+"("+back+")");

//		    	�ж��û��Ƿ��ϴ��˼���&�ж��û��Ƿ��ϴ��˼�������
			}else if(whereFrom.equals("HavaTalent")) {
				String UserMail=request.getParameter("UserMail");
				String sql01="select * from nsi_talent where UserMail ='"+UserMail+"'; ";
				String sql02="select * from nsi_talent where UserMail ='"+UserMail+"' AND HavaTalent != '0' ; ";
//				�������
				int i01=DB.count(sql01);
				String i02="-2";
				
				List<Talent_model> list = new ArrayList<Talent_model>();			
				list=DB.SearchTalent(sql02);
				
				String i002="0";
				try {
					Talent_model ob1 = list.get(0);
					i002=ob1.getHavaTalent();
				} catch (Exception e) {			
				}
					
				System.out.println("i02��ֵ��"+i002);
				if (i01==0) {
					i01=-1;
				}else{
					i01=1;
					};
					
				if (i002.length()<=1) {
					i02="-1";
				}else{
					i02=i002;
					};	
							
		    	String back="{msg01:"+i01+",msg02:\""+i02+"\"}";	
		    	String Callback = request.getParameter("Callback");//�ͻ����������	  	    	
		    	response.setContentType("text/html;charset=UTF-8");  
		    	response.getWriter().write(Callback+"("+back+")");

//		    	������
//		    	�ϴ���ҵ���� 
			}else if(whereFrom.equals("UpRecruitment")) {
				System.out.println("talent_api:WF======UpRecruitment�ϴ���Ƹ");
				// �ϴ��ļ��洢Ŀ¼
//			    final String UPLOAD_DIRECTORY = "upload";	 
			    // �ϴ�����
			    final int MEMORY_THRESHOLD   = 1024 * 1024 * 5;  // 5MB
			    final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
			    final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
//			    	��Ƹ�ļ���������+����+��ҵ����+ʱ��
			    	String UserMail=request.getParameter("UserMail");
			    	String User_TureName=request.getParameter("User_TureName");
			    	String CompanyName=request.getParameter("CompanyName");		    	
//					��ǰʱ��
						java.util.Date currentTime = new java.util.Date(); 
				    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			    	String SubmitDate = formatter.format(currentTime);
			    	
			    	 System.out.println("talent_api:�ϴ���Ƹ���û�����:"+User_TureName+UserMail+CompanyName);
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
		//	        String uploadPath = getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;
			        String uploadPath = "C:" + File.separator+"upFile"+ File.separator+"recruitment"+File.separator;		     
			        // ���Ŀ¼�������򴴽�
			        File uploadDir = new File(uploadPath);
			        if (!uploadDir.exists()) {
			            uploadDir.mkdir();
			        }	        
			        int i=-2;
			        try {
			            // ���������������ȡ�ļ�����
		//	        	���Ծ���������Ϣ
		//	            @SuppressWarnings("unchecked")
			            List<FileItem> formItems = upload.parseRequest(request);
			            if (formItems != null && formItems.size() > 0) {
			                // ����������
			                for (FileItem item : formItems) {
			                    // �����ڱ��е��ֶ�
			                    if (!item.isFormField()) {
			                    	
//			                    	��ȡ�ϴ��ļ���,FormatNameΪ�ļ���ʽ
			                    	String fileFormat=item.getName();
			                    	//FormatName���㣻FormatName02������
			                    	String FormatName = fileFormat.substring(fileFormat.lastIndexOf("."));
			                    	String FormatName02 = fileFormat.substring(fileFormat.lastIndexOf(".")+1);
			                    	
			                        String filePath = uploadPath + UserMail+"_"+User_TureName+"_"+CompanyName+SubmitDate+FormatName;
			                        File storeFile = new File(filePath);                                           
			                        // �ڿ���̨����ļ����ϴ�·��                     
			                        System.out.println("�����ϴ���ַ��"+filePath);
			                        // �����ļ���Ӳ��
			                        item.write(storeFile);
			                        i=1;
//			                                                                        �޸� ���û�nsi_talent���е�havaTalent�ֶ�Ϊ1
//			                        String sql="UPDATE nsi_talent SET HavaTalent = '"+FormatName02+"' WHERE UserMail='"+UserMail+"'; ";
//			                        DB.alter(sql);
			                    }
			                }
			            }
			        } catch (Exception ex) {
			            request.setAttribute("message","������Ϣ: " + ex.getMessage());
			            i=-1;
			        }		        
		//			�ɹ�
			    	String back="{msg:"+i+"}";	
			    	String Callback = request.getParameter("Callback");//�ͻ����������	  	    	
			    	response.setContentType("text/html;charset=UTF-8");  
			    	response.getWriter().write(Callback+"("+back+")");
		    	
//		    δ���
//		    	����
			}else if(whereFrom.equals("search")) {
				System.out.println("talent api:WF======search");

				Gson gson = new Gson();   	
		    	String Talent_searchKey=request.getParameter("Talent_searchKey");
		
		    	
//				��ҳ���� ���ڼ�ҳ��ÿҳ������Ĭ��ֵ��1��20��
				Integer pageNum = request.getParameter("pageNum") != null && !request.getParameter("pageNum").equals("") ? Integer.parseInt(request.getParameter("pageNum")) : 1;
				Integer OnePageNum = request.getParameter("OnePageNum") != null && !request.getParameter("OnePageNum").equals("") ? Integer.parseInt(request.getParameter("OnePageNum")) : 20;
				int pageNumX=(pageNum-1)*OnePageNum;
				

				List<School_model> list = new ArrayList<School_model>();	
//				String sql="SELECT * from NSI_SCHOOL_data WHERE CONCAT(IFNULL(`Id`,''),IFNULL(`School_name`,''),IFNULL(`School_EnglishName`,''),IFNULL(`Areas`,''),IFNULL(`Areas02`,''),IFNULL(`Founded_time`,'')) like '%"+School_searchKey+"%' order by Load_Time DESC limit "+pageNumX+","+OnePageNum+"";
//					
			}
    }

}
