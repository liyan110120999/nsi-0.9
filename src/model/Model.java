package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import model.dbutil.Dbconn;
import people.DB;
import sun.misc.BASE64Decoder;
import entity.User;

public class Model {
	User user =new User();
	public boolean checkUser(String username, String password) {
		user =this.queryByName(username);
		if (user!=null) {
			return (username.equals(user.getName()) && password.equals(user
					.getPassword()));}
		else 
			return false;
	}
	
	private PreparedStatement  ps;
	private ResultSet rs;
	Dbconn s=new Dbconn();
	
//	是否存在用户
	public User queryByName(String name) {
		User user = null; 
		String sql = "select * from nsi_user where UserName = ? ";
        try {
    		Connection conn=s.getConnection();
        	ps = conn.prepareStatement(sql);
        	ps.setString(1, name);
            rs = ps.executeQuery();
           if(rs.next()){
                user = new User();
                user.setName(rs.getString("UserName"));
                user.setPassword(rs.getString("Password"));
                user.setUser_TureName(rs.getString("User_TureName"));
                user.setMember_sign(Integer.parseInt(rs.getString("Member_sign")));
               
            }
            s.closeAll(conn,ps,rs);   
        } catch (Exception e) {
            e.printStackTrace();
        }
    return user;
	}
//	检查用户标志位是否 未激活
	public boolean queryByCode(String name) {
		User user = null; 
		boolean checkCode = false; 
		String sql = "select * from nsi_user where UserName = ? AND Member_sign > 0";
        try {
    		Connection conn=s.getConnection();
        	ps = conn.prepareStatement(sql);
        	ps.setString(1, name);
            rs = ps.executeQuery();
           if(rs.next()){
        	   checkCode=true;
            }
            s.closeAll(conn,ps,rs);   
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("code验证："+checkCode);
    return checkCode;
	}
	
// 待修改 为ID查询 暂时用不到
//	查询用户等级标志位
	public int queryById(String name) {
		User user = null; 
		int User_Member_sign= -2; 
		String sql = "select * from nsi_user where UserName = ?";
        try {
    		Connection conn=s.getConnection();
        	ps = conn.prepareStatement(sql);
        	ps.setString(1, name);
            rs = ps.executeQuery();
           if(rs.next()){
        	   user = new User();
               user.setName(rs.getString("UserName"));
               user.setMember_sign(Integer.parseInt(rs.getString("Member_sign")));
            }
            s.closeAll(conn,ps,rs);   
        } catch (Exception e) {
            e.printStackTrace();
        }
        User_Member_sign=user.getMember_sign();
        System.out.println("Model 查询用户等级为："+User_Member_sign);
    return User_Member_sign;
	}
	
	
//	用户cookie校验
	public int UserVerify(String username,String member_sign,String UserVerifyCode) {
//		1、用户存不存在？
//		2、权限标记位是否正确？
//		3、校验和是否正确？
		User user = null; 
		int CookieVerify= -3; 
		String sql = "select * from nsi_user where UserName = '"+username+"'";
		int aa=DB.count(sql);
		if (aa>=1) {
			 try {
		    		Connection conn=s.getConnection();
		        	ps = conn.prepareStatement(sql);	        
		            rs = ps.executeQuery();
		           if(rs.next()){
		        	   user = new User();
		               user.setName(rs.getString("UserName"));
		               user.setMember_sign(Integer.parseInt(rs.getString("Member_sign")));
		            }
		            s.closeAll(conn,ps,rs);   
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
			 String bb=String.valueOf(user.getMember_sign());
			
			 if(member_sign.equals(bb)){
				 int cc=username.length()*Integer.parseInt(member_sign)+987654;
				 if (cc==Integer.parseInt(UserVerifyCode)) {
					 CookieVerify=1;
				 }
			 }else {
				 CookieVerify=-1;
			 }
			 
		}else {
			CookieVerify=-2;
		}
        
        System.out.println("Model 用户cookie校验为："+CookieVerify);
    return CookieVerify;
	}
	
//	判断邮箱是否存在
	public int UserExistence(String username) {
//		1、用户存不存在？
		User user = null; 
		int CookieVerify= -3; 
		String sql = "select * from nsi_user where UserName = '"+username+"'";
		int aa=DB.count(sql);
		if (aa>=1) {
				 CookieVerify=1;
		}else {
			CookieVerify=-1;
		}
        System.out.println("Model 用户邮箱是否存在："+CookieVerify);
    return CookieVerify;
	}
	
	
//	未完成
//	文件上传通用组件
	public static int UpFileTool(String FileType,String UserMail,String User_TureName,String sql,HttpServletRequest request) throws ServletException, IOException{
		
//		上传文件，保存在哪里？什么格式？	
		System.out.println("UpFileTool通用文件上传工具类:");
	    // 上传配置
	    final int MEMORY_THRESHOLD   = 1024 * 1024 * 5;  // 5MB
	    final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
	    final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
//	    	文件名：姓名+邮箱	    	

	        // 配置上传参数
	        DiskFileItemFactory factory = new DiskFileItemFactory();
	        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
	        factory.setSizeThreshold(MEMORY_THRESHOLD);
	        // 设置临时存储目录
	        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));	 
	        ServletFileUpload upload = new ServletFileUpload(factory);	         
	        // 设置最大文件上传值、最大请求值 (包含文件和表单数据)、中文处理
	        upload.setFileSizeMax(MAX_FILE_SIZE);	         
	        upload.setSizeMax(MAX_REQUEST_SIZE);        
	        upload.setHeaderEncoding("UTF-8"); 
	        
	        String uploadPath="null";
	        // 这个路径相对当前应用的目录
//	       	 用户头像
	        if (FileType.equals("UserPortrait")) {
	        	uploadPath = "C:" + File.separator+"upImage"+ File.separator+"upUserImg"+File.separator;
			} else if(FileType.equals("aa")){

			}
	        
	        // 如果目录不存在则创建
	        File uploadDir = new File(uploadPath);
	        if (!uploadDir.exists()) {
	            uploadDir.mkdir();
	        }	        
	        int i=-2;
	        try {
// 				解析请求的内容提取文件数据
//	        	忽略警告或错误信息
//	            @SuppressWarnings("unchecked")
	            List<FileItem> formItems = upload.parseRequest(request);
	            
	            if (formItems != null && formItems.size() > 0) {    
	            	
	            	int j=1;

	                // 迭代表单数据
	                for (FileItem item : formItems) {	       
	                	
	                	 System.out.println("参数j的值：-2位置"+j);
	                	// 处理不在表单中的字段
	                    if (!item.isFormField()) {
	                    	
//	                    	获取上传文件名,FormatName为文件格式
	                    	String fileFormat=item.getName();
	                    	//FormatName带点；FormatName02不带点
	                    	String FormatName = fileFormat.substring(fileFormat.lastIndexOf("."));
	                    	String FormatName02 = fileFormat.substring(fileFormat.lastIndexOf(".")+1);
	                    	
	                        String filePath = uploadPath + UserMail+User_TureName+"000"+j+FormatName;
	                        File storeFile = new File(filePath);                                           
	                        // 在控制台输出文件的上传路径                     
	                        System.out.println("上传地址："+filePath);
	                        // 保存文件到硬盘
	                        item.write(storeFile);
	                        i=1;    
	                        
	                        System.out.println("参数j的值："+j);
                 	                        
	                    }
		                    System.out.println("参数j的值02位置："+j);
		                    j=j+1;
	                }
               //传入sql,传入00不执行     
                  if (sql.length()>5) {
						DB.alter(sql);
					}              
	            }
	        } catch (Exception ex) {
	        	System.err.println("文件上传模块-错误详情："+ex.getMessage());
	            i=-1;
	        }  
    return i;
	}
	
//	Base64转文件
	public static boolean GenerateImage(String imgStr, String imgFilePath) {// 对字节数组字符串进行Base64解码并生成图片
		
		if (imgStr == null) // 图像数据为空
		 return false;
		BASE64Decoder decoder = new BASE64Decoder();
	    try{
		    // Base64解码
		      byte[] bytes = decoder.decodeBuffer(imgStr);
		      for (int i = 0; i < bytes.length; ++i) {
		    	  if (bytes[i] < 0) {// 调整异常数据
		    		  bytes[i] += 256;
		    	  }
		      }
		      // 生成jpeg图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(bytes);
			out.flush();
			out.close();
			return true;
	    }catch (Exception e) {
	    	return false;
		}
 
	}
}
