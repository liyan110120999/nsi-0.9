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
		
//		可覆盖的添加基本信息
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

			
//			当前时间
				java.util.Date currentTime = new java.util.Date(); 
		    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	String SubmitDate = formatter.format(currentTime);
	    	
//	    	待测试能否覆盖旧信息
	    	String sql="REPLACE INTO nsi_talent (Name,Sex,Phone,"
				+ "Mail,Education,Major,NowWorkplace,WorkYear,ExpectWorkPlace,ExpectWorkPosition,ExpectSalary,Other,WorkExperience,EducationBackground,TrainingBackground,Public,UserMail,Load_time) " 					 		
				+ "VALUES ('"+Name+"','"+Sex+"','"+Phone+"','"+Mail+"','"+Education+"','"+Major+"','"+NowWorkplace+"','"+WorkYear+"','"+ExpectWorkPlace+"' "
						+ ",'"+ExpectWorkPosition+"','"+ExpectSalary+"','"+Other+"','"+WorkExperience+"','"+EducationBackground+"','"+TrainingBackground+"','"+Public+"','"+UserMail+"','"+SubmitDate+"' ) ";		
		    	
	    	DB.Insert(sql);
			String back="{msg:1}";
	    	String Callback = request.getParameter("Callback");//客户端请求参数	  	    	
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+back+")");
	    	
	    	
	    	
	    	
	    	
//	-----------------------------   未完成 	-----------------------------------------
	    	
	    	
//	    	搜索
		}else if(whereFrom.equals("search")){
			System.out.println("talent_api:WF=====search");		
		   	Gson gson = new Gson();   	
		   		
	    	String talent_searchKey=request.getParameter("talent_searchKey");
	    	String sql=null;		
			
//			分页参数 ：第几页、每页几个。默认值：1、20；
			Integer pageNum = request.getParameter("pageNum") != null && !request.getParameter("pageNum").equals("") ? Integer.parseInt(request.getParameter("pageNum")) : 1;
			Integer OnePageNum = request.getParameter("OnePageNum") != null && !request.getParameter("OnePageNum").equals("") ? Integer.parseInt(request.getParameter("OnePageNum")) : 20;
			int pageNumX=(pageNum-1)*OnePageNum;
//			排序方式 sort
			String SortType = request.getParameter("SortType") != null && !request.getParameter("SortType").equals("") ? request.getParameter("SortType") : "DownTime";
			
			
			
			List<School_model> list = new ArrayList<School_model>();			
			 sql="SELECT * from NSI_SCHOOL_data WHERE CONCAT(IFNULL(`Id`,''),IFNULL(`School_name`,''),IFNULL(`School_EnglishName`,''),IFNULL(`Areas`,''),IFNULL(`Areas02`,''),IFNULL(`Founded_time`,'')) like '%"+talent_searchKey+"%' order by Load_Time DESC limit "+pageNumX+","+OnePageNum+"";
//					
			list=School_DB.Search(sql);
//		
	    	String jsonList =gson.toJson(list);

	    	String Callback = request.getParameter("Callback");//客户端请求参数
	    	response.setContentType("text/html;charset=UTF-8");  
	    	response.getWriter().write(Callback+"("+jsonList+")");
	    	
	    
	    	
	    	
	    	
	    	
	    	
    	
	//	   详情,传入用户邮箱，返回简历list值
		}else if(whereFrom.equals("detail")){
			System.out.println("talent_api:WF=====detail");		
		   	Gson gson = new Gson();   	
		   	String UserMail=request.getParameter("UserMail");			
			List<Talent_model> list = new ArrayList<Talent_model>();			
			String sql="SELECT * from nsi_talent WHERE UserMail='"+UserMail+"' order by Load_Time DESC limit 0,1";			 	
			list=DB.SearchTalent(sql);
		   	String jsonList =gson.toJson(list);
		   	String Callback = request.getParameter("Callback");//客户端请求参数
		   	response.setContentType("text/html;charset=UTF-8");  
		   	response.getWriter().write(Callback+"("+jsonList+")");
	    	
	    	
//    	上传简历 
		}else if(whereFrom.equals("UpResume")) {
			System.out.println("talent api:WF======UpResume上传简历");
			// 上传文件存储目录
//		    final String UPLOAD_DIRECTORY = "upload";	 
		    // 上传配置
		    final int MEMORY_THRESHOLD   = 1024 * 1024 * 5;  // 5MB
		    final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
		    final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
//		    	简历文件名：姓名+邮箱
		    	String UserMail=request.getParameter("UserMail");
		    	String User_TureName=request.getParameter("User_TureName");
		    	
		    	System.out.println("talent_api:简历上传，用户邮箱:"+User_TureName+UserMail);
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
		        String uploadPath = "C:" + File.separator+"upFile"+ File.separator+"talent"+File.separator;		     
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
		                    	
//		                    	获取上传文件名,FormatName为文件格式
		                    	String fileFormat=item.getName();
		                    	//FormatName带点；FormatName02不带点
		                    	String FormatName = fileFormat.substring(fileFormat.lastIndexOf("."));
		                    	String FormatName02 = fileFormat.substring(fileFormat.lastIndexOf(".")+1);
		                    	
		                        String filePath = uploadPath + UserMail+User_TureName+FormatName;
		                        File storeFile = new File(filePath);                                           
		                        // 在控制台输出文件的上传路径                     
		                        System.out.println("简历上传地址："+filePath);
		                        // 保存文件到硬盘
		                        item.write(storeFile);
		                        i=1;
//		                        修改 该用户nsi_talent表中的havaTalent字段为1
		                        String sql="UPDATE nsi_talent SET HavaTalent = '"+FormatName02+"' WHERE UserMail='"+UserMail+"'; ";
		                        DB.alter(sql);
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

//		    	判断用户是否上传了简历&判断用户是否上传了简历附件
			}else if(whereFrom.equals("HavaTalent")) {
				String UserMail=request.getParameter("UserMail");
				String sql01="select * from nsi_talent where UserMail ='"+UserMail+"'; ";
				String sql02="select * from nsi_talent where UserMail ='"+UserMail+"' AND HavaTalent != '0' ; ";
//				结果数：
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
					
				System.out.println("i02的值："+i002);
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
		    	String Callback = request.getParameter("Callback");//客户端请求参数	  	    	
		    	response.setContentType("text/html;charset=UTF-8");  
		    	response.getWriter().write(Callback+"("+back+")");

//		    	待测试
//		    	上传企业简历 
			}else if(whereFrom.equals("UpRecruitment")) {
				System.out.println("talent_api:WF======UpRecruitment上传招聘");
				// 上传文件存储目录
//			    final String UPLOAD_DIRECTORY = "upload";	 
			    // 上传配置
			    final int MEMORY_THRESHOLD   = 1024 * 1024 * 5;  // 5MB
			    final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
			    final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
//			    	招聘文件名：姓名+邮箱+企业名称+时间
			    	String UserMail=request.getParameter("UserMail");
			    	String User_TureName=request.getParameter("User_TureName");
			    	String CompanyName=request.getParameter("CompanyName");		    	
//					当前时间
						java.util.Date currentTime = new java.util.Date(); 
				    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			    	String SubmitDate = formatter.format(currentTime);
			    	
			    	 System.out.println("talent_api:上传招聘，用户邮箱:"+User_TureName+UserMail+CompanyName);
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
			        String uploadPath = "C:" + File.separator+"upFile"+ File.separator+"recruitment"+File.separator;		     
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
			                    	
//			                    	获取上传文件名,FormatName为文件格式
			                    	String fileFormat=item.getName();
			                    	//FormatName带点；FormatName02不带点
			                    	String FormatName = fileFormat.substring(fileFormat.lastIndexOf("."));
			                    	String FormatName02 = fileFormat.substring(fileFormat.lastIndexOf(".")+1);
			                    	
			                        String filePath = uploadPath + UserMail+"_"+User_TureName+"_"+CompanyName+SubmitDate+FormatName;
			                        File storeFile = new File(filePath);                                           
			                        // 在控制台输出文件的上传路径                     
			                        System.out.println("简历上传地址："+filePath);
			                        // 保存文件到硬盘
			                        item.write(storeFile);
			                        i=1;
//			                                                                        修改 该用户nsi_talent表中的havaTalent字段为1
//			                        String sql="UPDATE nsi_talent SET HavaTalent = '"+FormatName02+"' WHERE UserMail='"+UserMail+"'; ";
//			                        DB.alter(sql);
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
		    	
//		    未完成
//		    	搜索
			}else if(whereFrom.equals("search")) {
				System.out.println("talent api:WF======search");

				Gson gson = new Gson();   	
		    	String Talent_searchKey=request.getParameter("Talent_searchKey");
		
		    	
//				分页参数 ：第几页、每页几个。默认值：1、20；
				Integer pageNum = request.getParameter("pageNum") != null && !request.getParameter("pageNum").equals("") ? Integer.parseInt(request.getParameter("pageNum")) : 1;
				Integer OnePageNum = request.getParameter("OnePageNum") != null && !request.getParameter("OnePageNum").equals("") ? Integer.parseInt(request.getParameter("OnePageNum")) : 20;
				int pageNumX=(pageNum-1)*OnePageNum;
				

				List<School_model> list = new ArrayList<School_model>();	
//				String sql="SELECT * from NSI_SCHOOL_data WHERE CONCAT(IFNULL(`Id`,''),IFNULL(`School_name`,''),IFNULL(`School_EnglishName`,''),IFNULL(`Areas`,''),IFNULL(`Areas02`,''),IFNULL(`Founded_time`,'')) like '%"+School_searchKey+"%' order by Load_Time DESC limit "+pageNumX+","+OnePageNum+"";
//					
			}
    }

}
