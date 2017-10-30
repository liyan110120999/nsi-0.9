package api;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import entity.User;
import model.Model;
import model.dbutil.Dbconn;
import people.DB;
import school.School_DB;
import school.School_model;
import user.Mail;
import user.RandomCode;
import user.User_DB;
import user.User_model;

@WebServlet("/User_api")
public class User_api extends HttpServlet{

	private static final long serialVersionUID = 6311813463986313971L;
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        this.doPost(request,response);
	    }
	    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			    	String whereFrom = null;
					whereFrom = request.getParameter("whereFrom");
				
				if(whereFrom.equals("login")){
					
					String name = request.getParameter("username");
					String pwd = request.getParameter("pwd");
					
					Model model=new Model();
					User user=new User();
//					Ĭ��ֵΪ-2 ��ʾû�и��û�
					int member_sign=-2;
					int UserVerifyCode=000000;
					String User_TureName="��";
//					����û���,���붼ͨ����			
					if(model.checkUser(name, pwd)){									
//						��ȡ�û���־λ
						member_sign=model.queryByName(name).getMember_sign();
						System.out.println("User_api:�û���־λ��"+member_sign);						
						UserVerifyCode=name.length()*member_sign+987654;
						
						User_TureName =model.queryByName(name).getUser_TureName();
//					�˺��������
					}else{
						System.out.println("User_api:����У�����");
						UserVerifyCode=0;
						User_TureName="0";
					}

					Gson gson = new Gson();   	
			
					String back="{\"member_sign\":\""+member_sign+"\","
								+ "\"username\":\""+name+"\","
								+ "\"User_TureName\":\""+User_TureName+"\","
								+ "\"UserVerifyCode\":\""+UserVerifyCode+"\"}";

			    	String Callback = request.getParameter("Callback");//�ͻ����������	  	    	
			    	response.setContentType("text/html;charset=UTF-8");  
			    	response.getWriter().write(Callback+"("+back+")");
			   
//			    ��֤-�û�cookieУ��	
				}else if(whereFrom.equals("verify")){
					String name = request.getParameter("username");
					String member_sign = request.getParameter("member_sign");
					String UserVerifyCode = request.getParameter("UserVerifyCode");
//					1���û��治���ڣ�
//					2��Ȩ�ޱ��λ�Ƿ���ȷ��
//					3��У����Ƿ���ȷ��
					System.out.println("У���˺����룺"+name);
					
					Model model=new Model();
					int verifyResult=model.UserVerify(name,member_sign,UserVerifyCode);
					
					Gson gson = new Gson();   					
					String back="{\"verifyResult\":\""+verifyResult+"\"}";

			    	String Callback = request.getParameter("Callback");//�ͻ����������	  	    	
			    	response.setContentType("text/html;charset=UTF-8");  
			    	response.getWriter().write(Callback+"("+back+")");
			    	
//				���û�ע��
				}else if(whereFrom.equals("register")){
					String Email=request.getParameter("Email");
					String Name=request.getParameter("Name");
					String company=request.getParameter("company");
					String position=request.getParameter("position");
					String Passwd01=request.getParameter("Passwd01");
					String User_phone=request.getParameter("phone");							
					// ��ȡʱ��
					java.util.Date currentTime = new java.util.Date(); 
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					String User_loadTime = formatter.format(currentTime);
					
					int msg=-2;
//					�����ʼ�
					if(Email!=null){
						User_model user=new User_model();					
						user.setName(Email);						
						String code = RandomCode.getRandomCode();		
						System.out.println(Email+code);
						try {
							//����
							System.out.println("user.get�������ַ��"+user.getName());
							Mail.sendMail(user.getName(), code);										
						} catch (MessagingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}			
//						¼�����ݿ�  ��"-1"Ϊ�û�״̬��־λ
						String sql="INSERT INTO nsi_user (UserName,Password,Member_sign,User_TureName,User_Organization,User_position,User_phone,User_registerCode,Load_time)"
									+ "VALUES ('"+Email+"','"+Passwd01+"','-1','"+Name+"','"+company+"','"+position+"','"+User_phone+"','"+code+"','"+User_loadTime+"')";
						DB.Insert(sql);
					}
					msg=1;
					
					Gson gson = new Gson();   					
					String back="{\"msg\":\""+msg+"\"}";

			    	String Callback = request.getParameter("Callback");//�ͻ����������	  	    	
			    	response.setContentType("text/html;charset=UTF-8");  
			    	response.getWriter().write(Callback+"("+back+")");
			    	System.out.println(Callback+"("+back+")");
			    			    	
//			    	��������Ƿ�ע���
				}else if(whereFrom.equals("checkMail")){
					String checkMail=request.getParameter("checkMail");
					String sql="SELECT * FROM nsi_user WHERE UserName='"+checkMail+"' ";
					System.out.println("��������Ƿ�ע�����"+checkMail+sql);
					int a=DB.count(sql);
					System.out.println("�������"+a);
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
			    	System.out.println(Callback+"("+back+")");
		    	
//				��������01-������֤��
			    }else if(whereFrom.equals("forgetPW")){
					String mail = request.getParameter("mail");
		//			1�����û��治���ڣ�
		//			2��������֤���ʼ�
		//			3����֤���Ƿ���ȷ��
					int msg=-2;
					System.out.println("User_api:�������룺"+mail);
					Model model=new Model();
					if (model.UserExistence(mail)>=1) {
//						�������
						try {
							//������������ ��֤��
							Mail.ForgetPWsendMail(mail);
							
						} catch (MessagingException e) {
							e.printStackTrace();
							msg=-2;
						} catch (Exception e) {
							e.printStackTrace();
							msg=-3;
						}	
						msg=1;
						
					} else {
						System.out.println("User_api:��������:���䲻���ڣ���");
						msg=-1;
					}
									
					String back="{\"msg\":\""+msg+"\"}";
		
			    	String Callback = request.getParameter("Callback");//�ͻ����������	  	    	
			    	response.setContentType("text/html;charset=UTF-8");  
			    	response.getWriter().write(Callback+"("+back+")");

			    	
//			    	��������02-��֤
				}else if(whereFrom.equals("forgetPWverify")){
					String mail = request.getParameter("mail");
					String code00 = request.getParameter("code");
					int code=Integer.parseInt(code00);
		//			1����ȡ����
		//			2��У�� ע�⡰Сʱ������Ҫ+1�ж�
		//			3������ֵ ԭmail ����Ȩ���Ը�����
					int msg=-2;
					System.out.println("User_api:��������У�飺"+mail+"&"+code);
					Model model=new Model();

					//����ʱ����ʼ���ַ�ַ����ȵļ����㷨	
					Calendar time = Calendar.getInstance();
					System.out.println("ForgetPWsendMail:cc�����㷨��hour:"+time.get(Calendar.HOUR_OF_DAY));
					int time_hour =time.get(Calendar.HOUR_OF_DAY);
					int cc=mail.length()*time_hour+987654;
					int cc02=mail.length()*(time_hour+1)+987654;
					System.out.println("1111111111 "+mail.length());
					if (code==cc|code==cc02) {
						msg=1;						
					} else {
						System.out.println("User_api:����������֤:��֤�벻��ȷ������");
						msg=-1;
					}
									
					String back="{\"msg\":\""+msg+"\"}";
		
			    	String Callback = request.getParameter("Callback");//�ͻ����������	  	    	
			    	response.setContentType("text/html;charset=UTF-8");  
			    	response.getWriter().write(Callback+"("+back+")");
			    	
//			    	��������03-�޸�����
				}else if(whereFrom.equals("forgetPWAlter")){
					String mail = request.getParameter("mail");
					String password=request.getParameter("password");
					String sql="UPDATE NSI_user SET Password ='"+password+"' WHERE UserName='"+mail+"' ";
					int msg=-2;
					try {
						DB.alter(sql);
						msg=1;
					} catch (Exception e) {
						msg=-1;
					}
									
					String back="{\"msg\":\""+msg+"\"}";
					
			    	String Callback = request.getParameter("Callback");//�ͻ����������	  	    	
			    	response.setContentType("text/html;charset=UTF-8");  
			    	response.getWriter().write(Callback+"("+back+")");			    	
//				�û���Ϣ
				}else if(whereFrom.equals("UserInfo")){
//					1����ȡ�û��� 2����ѯ���û���Ϣ 3��
					String UserName=request.getParameter("UserName");
					List<User_model> list = new ArrayList<User_model>();
			    	String sql="SELECT * FROM nsi_user WHERE UserName='"+UserName+"'";
			    	list=User_DB.Search(sql);		    	
			    	Gson gson = new Gson(); 
			    	String jsonList =gson.toJson(list);
			    	String Callback = request.getParameter("Callback");//�ͻ����������
			    	response.setContentType("text/html;charset=UTF-8");  
			    	response.getWriter().write(Callback+"("+jsonList+")");
				
//				��½���޸�����
				}else if(whereFrom.equals("ModifyPW")){
//						1����ȡ�û��� 2����ѯ���û���Ϣ 3��
					String UserName=request.getParameter("UserName");
					String NewPassword=request.getParameter("NewPassword");
					List<User_model> list = new ArrayList<User_model>();
					int msg=-2;
					
					String sql="UPDATE NSI_user SET Password ='"+NewPassword+"' WHERE UserName='"+UserName+"' ";
//					DB.alter(sql);	    	
					try {
						DB.alter(sql);
						msg=1;
					} catch (Exception e) {
						msg=-1;
					}
									
					String back="{\"msg\":\""+msg+"\"}";				
			    	String Callback = request.getParameter("Callback");//�ͻ����������	  	    	
			    	response.setContentType("text/html;charset=UTF-8");  
			    	response.getWriter().write(Callback+"("+back+")");
			    	
			    
			    	
//			    	�û�����
			    }else if(whereFrom.equals("feedback")){
//			    	˭��ʲôʱ�򣬷�����ʲô����ϵ��ʽ��������ϵ��ʽ��
			    	String UserName=request.getParameter("UserName");
//			    	��������
					String content = request.getParameter("content");
//					��ϵ��ʽ
					String Contact = request.getParameter("Contact");
//					��ǰʱ��
					java.util.Date currentTime = new java.util.Date(); 
			    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			    	String SubmitDate = formatter.format(currentTime);
					
			    	String User_TureName ="undefinedUser";
			    	if(!UserName.equals("undefinedUser")) {
			    		List<User_model> list = new ArrayList<User_model>();
				    	String sql="SELECT * FROM nsi_user WHERE UserName='"+UserName+"'";
				    	list=User_DB.Search(sql);
				    	User_TureName=list.get(0).getUser_TureName();
			    	}    	

			    	String sign="feedback";
			    	String sql02="INSERT INTO NSI_log (sign,index01,index02,index03,index04,index05) "
						+ "VALUES ('"+sign+"','"+User_TureName+"','"+SubmitDate+"','"+content+"','"+Contact+"','"+UserName+"')";
			    	DB.Insert(sql02);

			    	int msg=1;			
					String back="{\"msg\":\""+msg+"\"}";
		
			    	String Callback = request.getParameter("Callback");//�ͻ����������	  	    	
			    	response.setContentType("text/html;charset=UTF-8");  
			    	response.getWriter().write(Callback+"("+back+")");
			    	
			    
//			    	������
//					ͨ�û�������
				}else if(whereFrom.equals("Score")){
//					��˭���Ӷ��ٻ���
					String UserMail=request.getParameter("UserMail");
					String ScoreNum00=request.getParameter("ScoreNum");
//					String ת int
					System.out.println("User_api:WF=====Score��������"+UserMail+" : "+ScoreNum00+" ");
					int ScoreNum=Integer.parseInt(ScoreNum00);
					int msg=-2;	
//					���ӻ���
					String sql="UPDATE NSI_user SET User_score=User_score+'"+ScoreNum+"' WHERE UserName='"+UserMail+"'; ";	    	
					DB.alter(sql);
					
//					�ж��ܹ����� ����
//						��ȡ���ֺ��û��ȼ�
						List<User_model> list = new ArrayList<User_model>();
						String sql02="SELECT * FROM nsi_user WHERE UserName='"+UserMail+"'";
				    	list=User_DB.Search(sql02);
				    	
					    	String User_score00=list.get(0).getUser_score();
					    	int User_score=Integer.parseInt(User_score00);
					    	int Member_sign=list.get(0).getMember_sign();
//					    	����100��2��
					   if (User_score>=100&&Member_sign<=1) {
						   	String sql03="UPDATE NSI_user SET Member_sign='2' WHERE UserName='"+UserMail+"'; ";	    	
							DB.alter(sql03);		
//							����300��3��
						} else if(User_score>=300&&Member_sign<=2){
							String sql04="UPDATE NSI_user SET Member_sign='3' WHERE UserName='"+UserMail+"'; ";	    	
							DB.alter(sql04);
						}
					
						msg=1;									
					String back="{\"msg\":\""+msg+"\"}";				
			    	String Callback = request.getParameter("Callback");//�ͻ����������	  	    	
			    	response.setContentType("text/html;charset=UTF-8");  
			    	response.getWriter().write(Callback+"("+back+")");
				    	
				    	
//			    	��ʱ�ӿڣ�ɾ�������������˺� 1453485414 
			    }else if(whereFrom.equals("DeleteTestMail")){
//			    	˭��ʲôʱ�򣬷�����ʲô����ϵ��ʽ��������ϵ��ʽ��
			    	String UserName=request.getParameter("UserName");

			    	String sql="DELETE FROM nsi_user WHERE UserName ='"+UserName+"';";    	
					DB.Delete(sql);	
//					�ɹ�
			    	String back="{msg:1}";
			    	String Callback = request.getParameter("Callback");//�ͻ����������	  	    	
			    	response.setContentType("text/html;charset=UTF-8");  
			    	response.getWriter().write(Callback+"("+back+")");
			    	System.out.println("user_api:ɾ���˲����û����䣺"+UserName);

					    	
				}else {
					System.out.println("User_api:û���յ�WF����!!!");
				}
				
	    }
}
