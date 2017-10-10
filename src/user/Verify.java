package user;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import people.DB;

@WebServlet("/Verify")
public class Verify extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		注册用户通过审核 ，标志位置为“1”
		String verify = request.getParameter("verify");
		System.out.println("verify:"+verify);		
		String sql="UPDATE nsi_user SET Member_sign='1' WHERE UserName='"+verify+"' and Member_sign<1 ";
		DB.alter(sql);
		
//		测试 用户通知
		try {
			Mail.SendNotifyMail("1453485414@qq.com", "审核通过，您可以登录了");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
