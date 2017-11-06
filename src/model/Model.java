package model;

import java.io.File;
import java.io.IOException;
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

import model.dbutil.Dbconn;
import people.DB;
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
	
//	�Ƿ�����û�
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
//	����û���־λ�Ƿ� δ����
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
        System.out.println("code��֤��"+checkCode);
    return checkCode;
	}
	
// ���޸� ΪID��ѯ ��ʱ�ò���
//	��ѯ�û��ȼ���־λ
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
        System.out.println("Model ��ѯ�û��ȼ�Ϊ��"+User_Member_sign);
    return User_Member_sign;
	}
	
	
//	�û�cookieУ��
	public int UserVerify(String username,String member_sign,String UserVerifyCode) {
//		1���û��治���ڣ�
//		2��Ȩ�ޱ��λ�Ƿ���ȷ��
//		3��У����Ƿ���ȷ��
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
        
        System.out.println("Model �û�cookieУ��Ϊ��"+CookieVerify);
    return CookieVerify;
	}
	
//	�ж������Ƿ����
	public int UserExistence(String username) {
//		1���û��治���ڣ�
		User user = null; 
		int CookieVerify= -3; 
		String sql = "select * from nsi_user where UserName = '"+username+"'";
		int aa=DB.count(sql);
		if (aa>=1) {
				 CookieVerify=1;
		}else {
			CookieVerify=-1;
		}
        System.out.println("Model �û������Ƿ���ڣ�"+CookieVerify);
    return CookieVerify;
	}
	
	
//	δ���
//	�ļ��ϴ�ͨ�����
	public static int UpFileTool(String FileType,String UserMail,String User_TureName,String sql,HttpServletRequest request) throws ServletException, IOException{
		
//		�ϴ��ļ������������ʲô��ʽ��	
		System.out.println("UpFileToolͨ���ļ��ϴ�������:");
	    // �ϴ�����
	    final int MEMORY_THRESHOLD   = 1024 * 1024 * 5;  // 5MB
	    final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
	    final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
//	    	�ļ���������+����	    	

	        // �����ϴ�����
	        DiskFileItemFactory factory = new DiskFileItemFactory();
	        // �����ڴ��ٽ�ֵ - �����󽫲�����ʱ�ļ����洢����ʱĿ¼��
	        factory.setSizeThreshold(MEMORY_THRESHOLD);
	        // ������ʱ�洢Ŀ¼
	        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));	 
	        ServletFileUpload upload = new ServletFileUpload(factory);	         
	        // ��������ļ��ϴ�ֵ���������ֵ (�����ļ��ͱ�����)�����Ĵ���
	        upload.setFileSizeMax(MAX_FILE_SIZE);	         
	        upload.setSizeMax(MAX_REQUEST_SIZE);        
	        upload.setHeaderEncoding("UTF-8"); 
	        
	        String uploadPath="null";
	        // ���·����Ե�ǰӦ�õ�Ŀ¼
//	       	 �û�ͷ��
	        if (FileType.equals("UserPortrait")) {
	        	uploadPath = "C:" + File.separator+"upImage"+ File.separator+"upUserImg"+File.separator;
			} else if(FileType.equals("aa")){

			}
	        
	        // ���Ŀ¼�������򴴽�
	        File uploadDir = new File(uploadPath);
	        if (!uploadDir.exists()) {
	            uploadDir.mkdir();
	        }	        
	        int i=-2;
	        try {
// 				���������������ȡ�ļ�����
//	        	���Ծ���������Ϣ
//	            @SuppressWarnings("unchecked")
	            List<FileItem> formItems = upload.parseRequest(request);
	            
	            if (formItems != null && formItems.size() > 0) {    
	            	
	            	int j=1;

	                // ����������
	                for (FileItem item : formItems) {	       
	                	
	                	 System.out.println("����j��ֵ��-2λ��"+j);
	                	// �����ڱ��е��ֶ�
	                    if (!item.isFormField()) {
	                    	
//	                    	��ȡ�ϴ��ļ���,FormatNameΪ�ļ���ʽ
	                    	String fileFormat=item.getName();
	                    	//FormatName���㣻FormatName02������
	                    	String FormatName = fileFormat.substring(fileFormat.lastIndexOf("."));
	                    	String FormatName02 = fileFormat.substring(fileFormat.lastIndexOf(".")+1);
	                    	
	                        String filePath = uploadPath + UserMail+User_TureName+"000"+j+FormatName;
	                        File storeFile = new File(filePath);                                           
	                        // �ڿ���̨����ļ����ϴ�·��                     
	                        System.out.println("�ϴ���ַ��"+filePath);
	                        // �����ļ���Ӳ��
	                        item.write(storeFile);
	                        i=1;    
	                        
	                        System.out.println("����j��ֵ��"+j);
                 	                        
	                    }
		                    System.out.println("����j��ֵ02λ�ã�"+j);
		                    j=j+1;
	                }
               //����sql,����00��ִ��     
                  if (sql.length()>5) {
						DB.alter(sql);
					}              
	            }
	        } catch (Exception ex) {
	        	System.err.println("�ļ��ϴ�ģ��-�������飺"+ex.getMessage());
	            i=-1;
	        }  
    return i;
	}
}
