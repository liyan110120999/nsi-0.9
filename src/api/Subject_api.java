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
import subject.Subject_model;

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
		
//			��ǰʱ��
				java.util.Date currentTime = new java.util.Date(); 
		    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    	String SubmitDate = formatter.format(currentTime);
	    	
//	    	�������ܷ񸲸Ǿ���Ϣ
	    	String sql="REPLACE INTO nsi_project_data (SubjectName,Areas,Areas02,Areas03,Company,SubjectLabel,Name,Phone,Mail,SubjectIntroduction,DetailInstitution,"
	    			+ "Requirement,UserMail,Load_time) " 					 		
				+ "VALUES ('"+SubjectName+"','"+Areas+"','"+Areas02+"','"+Areas03+"','"+Company+"','"+SubjectLabel+"','"+Name+"','"+Phone+"','"+Mail+"' "
						+ ",'"+SubjectIntroduction+"','"+DetailInstitution+"','"+Requirement+"','"+UserMail+"','"+SubmitDate+"' )";		
		    	
	    	DB.Insert(sql);
			String back="{msg:1}";
	    	String Callback = request.getParameter("Callback");//�ͻ����������	  	    	
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+back+")");
	    
	    	
		}else if (whereFrom.equals("search")) {
			
			System.out.println("Subject_api:WF======search");		
	    	Gson gson = new Gson();   	
	    	String SearchKey=request.getParameter("SearchKey");
	    	String sql=null;		
			
//			��ҳ���� ���ڼ�ҳ��ÿҳ������Ĭ��ֵ��1��20��
			Integer pageNum = request.getParameter("pageNum") != null && !request.getParameter("pageNum").equals("") ? Integer.parseInt(request.getParameter("pageNum")) : 1;
			Integer OnePageNum = request.getParameter("OnePageNum") != null && !request.getParameter("OnePageNum").equals("") ? Integer.parseInt(request.getParameter("OnePageNum")) : 20;
			int pageNumX=(pageNum-1)*OnePageNum;
			
			List<Subject_model> list = new ArrayList<Subject_model>();			
			 sql="SELECT * from NSI_project_data WHERE CONCAT(IFNULL(`Id`,''),IFNULL(`SubjectName`,''),IFNULL(`Areas`,''),IFNULL(`Areas02`,''),IFNULL(`Company`,''),IFNULL(`SubjectLabel`,''),IFNULL(`SubjectIntroduction`,''),IFNULL(`DetailInstitution`,'')) like '%"+SearchKey+"%' order by Load_time DESC limit "+pageNumX+","+OnePageNum+"";
				
			list=DB.SearchSubject(sql);		
	    	String jsonList =gson.toJson(list);

	    	String Callback = request.getParameter("Callback");//�ͻ����������
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+jsonList+")");
		
	    	
	    }else if(whereFrom.equals("count")){		
			System.out.println("Subject_api:WF======count");			 	
			String SearchKey=request.getParameter("SearchKey");			
			String sqlcount=null;
			int countAllRS = 0;
			 sqlcount="SELECT * from NSI_project_data WHERE CONCAT(IFNULL(`Id`,''),IFNULL(`SubjectName`,''),IFNULL(`Areas`,''),IFNULL(`Areas02`,''),IFNULL(`Company`,''),IFNULL(`SubjectLabel`,''),IFNULL(`SubjectIntroduction`,''),IFNULL(`DetailInstitution`,'')) like '%"+SearchKey+"%' order by Load_time DESC; ";
			countAllRS=DB.count(sqlcount);			
			String back="{countAllRS:"+countAllRS+"}";
			String Callback = request.getParameter("Callback");//�ͻ����������	  	    	
			response.setContentType("text/html;charset=UTF-8");  
			response.getWriter().write(Callback+"("+back+")");		

		
	//	    	 ����ҳ������Id ����listֵ
		}else if(whereFrom.equals("detail")){
			System.out.println("subject_api:WF======detail");		
		   	Gson gson = new Gson();   	
		   	String Id=request.getParameter("Id");		   					
			List<Subject_model> list = new ArrayList<Subject_model>();			
			String sql="SELECT * from NSI_project_data WHERE Id='"+Id+"' limit 0,1";			 	
			list=DB.SearchSubject(sql);
		   	String jsonList =gson.toJson(list);
		   	String Callback = request.getParameter("Callback");//�ͻ����������
		   	response.setContentType("text/html;charset=UTF-8"); 
		   	response.getWriter().write(Callback+"("+jsonList+")");
		   	
		   	
	//    	������
	//    	�ϴ���Ŀ��Ϣ�ļ�
		}else if(whereFrom.equals("UpSubject")) {
			System.out.println("Subject_api:WF======UpSubject�ϴ���Ŀ����");
			// �ϴ��ļ��洢Ŀ¼
	//	    final String UPLOAD_DIRECTORY = "upload";	 
		    // �ϴ�����
		    final int MEMORY_THRESHOLD   = 1024 * 1024 * 5;  // 5MB
		    final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
		    final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
	//	    	��Ŀ�ļ���������+����+��ҵ����+ʱ��
		    	String UserMail=request.getParameter("UserMail");
		    	String User_TureName=request.getParameter("User_TureName");
		    	String SubjectName=request.getParameter("SubjectName");		    	
	//			��ǰʱ��
					java.util.Date currentTime = new java.util.Date(); 
			    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		    	String SubmitDate = formatter.format(currentTime);
		    	
		    	 System.out.println("Subject_api:UpSubject���û�����:"+User_TureName+UserMail+SubjectName);
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
		        String uploadPath = "C:" + File.separator+"upFile"+ File.separator+"subject"+File.separator;		     
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
		                    	
	//	                    	��ȡ�ϴ��ļ���,FormatNameΪ�ļ���ʽ
		                    	String fileFormat=item.getName();
		                    	//FormatName���㣻FormatName02������
		                    	String FormatName = fileFormat.substring(fileFormat.lastIndexOf("."));
		                    	String FormatName02 = fileFormat.substring(fileFormat.lastIndexOf(".")+1);
		                    	
		                        String filePath = uploadPath + UserMail+"_"+User_TureName+"_"+SubjectName+SubmitDate+FormatName;
		                        File storeFile = new File(filePath);                                           
		                        // �ڿ���̨����ļ����ϴ�·��                     
		                        System.out.println("�����ϴ���ַ��"+filePath);
		                        // �����ļ���Ӳ��
		                        item.write(storeFile);
		                        i=1;
	//	                                                                        �޸� ���û�nsi_talent���е�havaTalent�ֶ�Ϊ1
	//	                        String sql="UPDATE nsi_talent SET HavaTalent = '"+FormatName02+"' WHERE UserMail='"+UserMail+"'; ";
	//	                        DB.alter(sql);
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
		}

    }
}
