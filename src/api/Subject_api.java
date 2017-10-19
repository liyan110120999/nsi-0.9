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
			
//			分页参数 ：第几页、每页几个。默认值：1、20；
			Integer pageNum = request.getParameter("pageNum") != null && !request.getParameter("pageNum").equals("") ? Integer.parseInt(request.getParameter("pageNum")) : 1;
			Integer OnePageNum = request.getParameter("OnePageNum") != null && !request.getParameter("OnePageNum").equals("") ? Integer.parseInt(request.getParameter("OnePageNum")) : 20;
			int pageNumX=(pageNum-1)*OnePageNum;
			
			List<Subject_model> list = new ArrayList<Subject_model>();			
			 sql="SELECT * from NSI_project_data WHERE CONCAT(IFNULL(`Id`,''),IFNULL(`SubjectName`,''),IFNULL(`Areas`,''),IFNULL(`Areas02`,''),IFNULL(`Company`,''),IFNULL(`SubjectLabel`,''),IFNULL(`SubjectIntroduction`,''),IFNULL(`DetailInstitution`,'')) like '%"+SearchKey+"%' order by Load_time DESC limit "+pageNumX+","+OnePageNum+"";
				
			list=DB.SearchSubject(sql);		
	    	String jsonList =gson.toJson(list);

	    	String Callback = request.getParameter("Callback");//客户端请求参数
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
			String Callback = request.getParameter("Callback");//客户端请求参数	  	    	
			response.setContentType("text/html;charset=UTF-8");  
			response.getWriter().write(Callback+"("+back+")");		

		
	//	    	 详情页，传入Id 返回list值
		}else if(whereFrom.equals("detail")){
			System.out.println("subject_api:WF======detail");		
		   	Gson gson = new Gson();   	
		   	String Id=request.getParameter("Id");		   					
			List<Subject_model> list = new ArrayList<Subject_model>();			
			String sql="SELECT * from NSI_project_data WHERE Id='"+Id+"' limit 0,1";			 	
			list=DB.SearchSubject(sql);
		   	String jsonList =gson.toJson(list);
		   	String Callback = request.getParameter("Callback");//客户端请求参数
		   	response.setContentType("text/html;charset=UTF-8"); 
		   	response.getWriter().write(Callback+"("+jsonList+")");
		   	
		   	
	//    	待测试
	//    	上传项目信息文件
		}else if(whereFrom.equals("UpSubject")) {
			System.out.println("Subject_api:WF======UpSubject上传项目介绍");
			// 上传文件存储目录
	//	    final String UPLOAD_DIRECTORY = "upload";	 
		    // 上传配置
		    final int MEMORY_THRESHOLD   = 1024 * 1024 * 5;  // 5MB
		    final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
		    final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
	//	    	项目文件名：姓名+邮箱+企业名称+时间
		    	String UserMail=request.getParameter("UserMail");
		    	String User_TureName=request.getParameter("User_TureName");
		    	String SubjectName=request.getParameter("SubjectName");		    	
	//			当前时间
					java.util.Date currentTime = new java.util.Date(); 
			    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		    	String SubmitDate = formatter.format(currentTime);
		    	
		    	 System.out.println("Subject_api:UpSubject：用户邮箱:"+User_TureName+UserMail+SubjectName);
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
	//	        String uploadPath = getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;
		        String uploadPath = "C:" + File.separator+"upFile"+ File.separator+"subject"+File.separator;		     
		        // 如果目录不存在则创建
		        File uploadDir = new File(uploadPath);
		        if (!uploadDir.exists()) {
		            uploadDir.mkdir();
		        }	        
		        int i=-2;
		        try {
		            // 解析请求的内容提取文件数据
	//	        	忽略警告或错误信息
	//	            @SuppressWarnings("unchecked")
		            List<FileItem> formItems = upload.parseRequest(request);
		            if (formItems != null && formItems.size() > 0) {
		                // 迭代表单数据
		                for (FileItem item : formItems) {
		                    // 处理不在表单中的字段
		                    if (!item.isFormField()) {
		                    	
	//	                    	获取上传文件名,FormatName为文件格式
		                    	String fileFormat=item.getName();
		                    	//FormatName带点；FormatName02不带点
		                    	String FormatName = fileFormat.substring(fileFormat.lastIndexOf("."));
		                    	String FormatName02 = fileFormat.substring(fileFormat.lastIndexOf(".")+1);
		                    	
		                        String filePath = uploadPath + UserMail+"_"+User_TureName+"_"+SubjectName+SubmitDate+FormatName;
		                        File storeFile = new File(filePath);                                           
		                        // 在控制台输出文件的上传路径                     
		                        System.out.println("简历上传地址："+filePath);
		                        // 保存文件到硬盘
		                        item.write(storeFile);
		                        i=1;
	//	                                                                        修改 该用户nsi_talent表中的havaTalent字段为1
	//	                        String sql="UPDATE nsi_talent SET HavaTalent = '"+FormatName02+"' WHERE UserMail='"+UserMail+"'; ";
	//	                        DB.alter(sql);
		                    }
		                }
		            }
		        } catch (Exception ex) {
		            request.setAttribute("message","错误信息: " + ex.getMessage());
		            i=-1;
		        }		        
	//			成功
		    	String back="{msg:"+i+"}";	
		    	String Callback = request.getParameter("Callback");//客户端请求参数	  	    	
		    	response.setContentType("text/html;charset=UTF-8");  
		    	response.getWriter().write(Callback+"("+back+")");
		}

    }
}
