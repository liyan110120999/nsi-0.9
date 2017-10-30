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
//					默认值为-2 表示没有该用户
					int member_sign=-2;
					int UserVerifyCode=000000;
					String User_TureName="空";
//					如果用户名,密码都通过，			
					if(model.checkUser(name, pwd)){									
//						获取用户标志位
						member_sign=model.queryByName(name).getMember_sign();
						System.out.println("User_api:用户标志位："+member_sign);						
						UserVerifyCode=name.length()*member_sign+987654;
						
						User_TureName =model.queryByName(name).getUser_TureName();
//					账号密码错误
					}else{
						System.out.println("User_api:密码校验错误");
						UserVerifyCode=0;
						User_TureName="0";
					}

					Gson gson = new Gson();   	
			
					String back="{\"member_sign\":\""+member_sign+"\","
								+ "\"username\":\""+name+"\","
								+ "\"User_TureName\":\""+User_TureName+"\","
								+ "\"UserVerifyCode\":\""+UserVerifyCode+"\"}";

			    	String Callback = request.getParameter("Callback");//客户端请求参数	  	    	
			    	response.setContentType("text/html;charset=UTF-8");  
			    	response.getWriter().write(Callback+"("+back+")");
			   
//			    验证-用户cookie校验	
				}else if(whereFrom.equals("verify")){
					String name = request.getParameter("username");
					String member_sign = request.getParameter("member_sign");
					String UserVerifyCode = request.getParameter("UserVerifyCode");
//					1、用户存不存在？
//					2、权限标记位是否正确？
//					3、校验和是否正确？
					System.out.println("校验账号密码："+name);
					
					Model model=new Model();
					int verifyResult=model.UserVerify(name,member_sign,UserVerifyCode);
					
					Gson gson = new Gson();   					
					String back="{\"verifyResult\":\""+verifyResult+"\"}";

			    	String Callback = request.getParameter("Callback");//客户端请求参数	  	    	
			    	response.setContentType("text/html;charset=UTF-8");  
			    	response.getWriter().write(Callback+"("+back+")");
			    	
//				新用户注册
				}else if(whereFrom.equals("register")){
					String Email=request.getParameter("Email");
					String Name=request.getParameter("Name");
					String company=request.getParameter("company");
					String position=request.getParameter("position");
					String Passwd01=request.getParameter("Passwd01");
					String User_phone=request.getParameter("phone");							
					// 获取时间
					java.util.Date currentTime = new java.util.Date(); 
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					String User_loadTime = formatter.format(currentTime);
					
					int msg=-2;
//					发送邮件
					if(Email!=null){
						User_model user=new User_model();					
						user.setName(Email);						
						String code = RandomCode.getRandomCode();		
						System.out.println(Email+code);
						try {
							//发送
							System.out.println("user.get的邮箱地址："+user.getName());
							Mail.sendMail(user.getName(), code);										
						} catch (MessagingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}			
//						录入数据库  ，"-1"为用户状态标志位
						String sql="INSERT INTO nsi_user (UserName,Password,Member_sign,User_TureName,User_Organization,User_position,User_phone,User_registerCode,Load_time)"
									+ "VALUES ('"+Email+"','"+Passwd01+"','-1','"+Name+"','"+company+"','"+position+"','"+User_phone+"','"+code+"','"+User_loadTime+"')";
						DB.Insert(sql);
					}
					msg=1;
					
					Gson gson = new Gson();   					
					String back="{\"msg\":\""+msg+"\"}";

			    	String Callback = request.getParameter("Callback");//客户端请求参数	  	    	
			    	response.setContentType("text/html;charset=UTF-8");  
			    	response.getWriter().write(Callback+"("+back+")");
			    	System.out.println(Callback+"("+back+")");
			    			    	
//			    	检查邮箱是否注册过
				}else if(whereFrom.equals("checkMail")){
					String checkMail=request.getParameter("checkMail");
					String sql="SELECT * FROM nsi_user WHERE UserName='"+checkMail+"' ";
					System.out.println("检查邮箱是否注册过："+checkMail+sql);
					int a=DB.count(sql);
					System.out.println("结果数："+a);
					int msg=-2;
					if(a<1){
						msg=1;
					}else{
						msg=-1;
					}
					
					String back="{\"msg\":\""+msg+"\"}";
					
			    	String Callback = request.getParameter("Callback");//客户端请求参数	  	    	
			    	response.setContentType("text/html;charset=UTF-8");  
			    	response.getWriter().write(Callback+"("+back+")");
			    	System.out.println(Callback+"("+back+")");
		    	
//				忘记密码01-发送验证码
			    }else if(whereFrom.equals("forgetPW")){
					String mail = request.getParameter("mail");
		//			1、该用户存不存在？
		//			2、发送验证码邮件
		//			3、验证码是否正确？
					int msg=-2;
					System.out.println("User_api:忘记密码："+mail);
					Model model=new Model();
					if (model.UserExistence(mail)>=1) {
//						邮箱存在
						try {
							//发送忘记密码 验证码
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
						System.out.println("User_api:忘记密码:邮箱不存在！！");
						msg=-1;
					}
									
					String back="{\"msg\":\""+msg+"\"}";
		
			    	String Callback = request.getParameter("Callback");//客户端请求参数	  	    	
			    	response.setContentType("text/html;charset=UTF-8");  
			    	response.getWriter().write(Callback+"("+back+")");

			    	
//			    	忘记密码02-验证
				}else if(whereFrom.equals("forgetPWverify")){
					String mail = request.getParameter("mail");
					String code00 = request.getParameter("code");
					int code=Integer.parseInt(code00);
		//			1、获取参数
		//			2、校验 注意“小时数”需要+1判断
		//			3、返回值 原mail ，授权可以改密码
					int msg=-2;
					System.out.println("User_api:忘记密码校验："+mail+"&"+code);
					Model model=new Model();

					//基于时间和邮件地址字符长度的加密算法	
					Calendar time = Calendar.getInstance();
					System.out.println("ForgetPWsendMail:cc加密算法：hour:"+time.get(Calendar.HOUR_OF_DAY));
					int time_hour =time.get(Calendar.HOUR_OF_DAY);
					int cc=mail.length()*time_hour+987654;
					int cc02=mail.length()*(time_hour+1)+987654;
					System.out.println("1111111111 "+mail.length());
					if (code==cc|code==cc02) {
						msg=1;						
					} else {
						System.out.println("User_api:忘记密码验证:验证码不正确！！！");
						msg=-1;
					}
									
					String back="{\"msg\":\""+msg+"\"}";
		
			    	String Callback = request.getParameter("Callback");//客户端请求参数	  	    	
			    	response.setContentType("text/html;charset=UTF-8");  
			    	response.getWriter().write(Callback+"("+back+")");
			    	
//			    	忘记密码03-修改密码
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
					
			    	String Callback = request.getParameter("Callback");//客户端请求参数	  	    	
			    	response.setContentType("text/html;charset=UTF-8");  
			    	response.getWriter().write(Callback+"("+back+")");			    	
//				用户信息
				}else if(whereFrom.equals("UserInfo")){
//					1、获取用户名 2、查询该用户信息 3、
					String UserName=request.getParameter("UserName");
					List<User_model> list = new ArrayList<User_model>();
			    	String sql="SELECT * FROM nsi_user WHERE UserName='"+UserName+"'";
			    	list=User_DB.Search(sql);		    	
			    	Gson gson = new Gson(); 
			    	String jsonList =gson.toJson(list);
			    	String Callback = request.getParameter("Callback");//客户端请求参数
			    	response.setContentType("text/html;charset=UTF-8");  
			    	response.getWriter().write(Callback+"("+jsonList+")");
				
//				登陆后修改密码
				}else if(whereFrom.equals("ModifyPW")){
//						1、获取用户名 2、查询该用户信息 3、
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
			    	String Callback = request.getParameter("Callback");//客户端请求参数	  	    	
			    	response.setContentType("text/html;charset=UTF-8");  
			    	response.getWriter().write(Callback+"("+back+")");
			    	
			    
			    	
//			    	用户反馈
			    }else if(whereFrom.equals("feedback")){
//			    	谁，什么时候，反馈了什么，联系方式，本地联系方式。
			    	String UserName=request.getParameter("UserName");
//			    	反馈内容
					String content = request.getParameter("content");
//					联系方式
					String Contact = request.getParameter("Contact");
//					当前时间
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
		
			    	String Callback = request.getParameter("Callback");//客户端请求参数	  	    	
			    	response.setContentType("text/html;charset=UTF-8");  
			    	response.getWriter().write(Callback+"("+back+")");
			    	
			    
//			    	待测试
//					通用积分增加
				}else if(whereFrom.equals("Score")){
//					给谁，加多少积分
					String UserMail=request.getParameter("UserMail");
					String ScoreNum00=request.getParameter("ScoreNum");
//					String 转 int
					System.out.println("User_api:WF=====Score积分增加"+UserMail+" : "+ScoreNum00+" ");
					int ScoreNum=Integer.parseInt(ScoreNum00);
					int msg=-2;	
//					增加积分
					String sql="UPDATE NSI_user SET User_score=User_score+'"+ScoreNum+"' WHERE UserName='"+UserMail+"'; ";	    	
					DB.alter(sql);
					
//					判断攒够积分 升级
//						获取积分和用户等级
						List<User_model> list = new ArrayList<User_model>();
						String sql02="SELECT * FROM nsi_user WHERE UserName='"+UserMail+"'";
				    	list=User_DB.Search(sql02);
				    	
					    	String User_score00=list.get(0).getUser_score();
					    	int User_score=Integer.parseInt(User_score00);
					    	int Member_sign=list.get(0).getMember_sign();
//					    	积分100升2级
					   if (User_score>=100&&Member_sign<=1) {
						   	String sql03="UPDATE NSI_user SET Member_sign='2' WHERE UserName='"+UserMail+"'; ";	    	
							DB.alter(sql03);		
//							积分300升3级
						} else if(User_score>=300&&Member_sign<=2){
							String sql04="UPDATE NSI_user SET Member_sign='3' WHERE UserName='"+UserMail+"'; ";	    	
							DB.alter(sql04);
						}
					
						msg=1;									
					String back="{\"msg\":\""+msg+"\"}";				
			    	String Callback = request.getParameter("Callback");//客户端请求参数	  	    	
			    	response.setContentType("text/html;charset=UTF-8");  
			    	response.getWriter().write(Callback+"("+back+")");
				    	
				    	
//			    	临时接口：删除测试用邮箱账号 1453485414 
			    }else if(whereFrom.equals("DeleteTestMail")){
//			    	谁，什么时候，反馈了什么，联系方式，本地联系方式。
			    	String UserName=request.getParameter("UserName");

			    	String sql="DELETE FROM nsi_user WHERE UserName ='"+UserName+"';";    	
					DB.Delete(sql);	
//					成功
			    	String back="{msg:1}";
			    	String Callback = request.getParameter("Callback");//客户端请求参数	  	    	
			    	response.setContentType("text/html;charset=UTF-8");  
			    	response.getWriter().write(Callback+"("+back+")");
			    	System.out.println("user_api:删除了测试用户邮箱："+UserName);

					    	
				}else {
					System.out.println("User_api:没有收到WF参数!!!");
				}
				
	    }
}
