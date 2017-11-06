package admin;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.User;
import model.Model;

@WebServlet("/AdminUser")
public class AdminUser extends HttpServlet{
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String supercode00 = request.getParameter("supercode");
		int supercode=Integer.parseInt(supercode00);
		
//		当前时间-数字格式
			java.util.Date currentTime = new java.util.Date(); 
	    	SimpleDateFormat formatter = new SimpleDateFormat("MMdd");
	    	String SubmitDate = formatter.format(currentTime);
    	int DateCode=Integer.parseInt(SubmitDate);
    	DateCode = DateCode+3223;
		System.out.println("测试AdminUser时间格式："+SubmitDate);
		
		HttpSession session=request.getSession(); 
//		如果用户名密码和code都通过，跳转	
		if(supercode==DateCode){
			System.out.println("后台-管理用户通过");
			session.setAttribute("Session_AdminUser",supercode);
			response.sendRedirect("http://data.xinxueshuo.cn/nsi-0.9/Admin/count.jsp");		
//			未完成
			
		}else{
			System.out.println("后台-管理用户未通过");
			response.sendRedirect("http://data.xinxueshuo.cn/nsi/other/index.html");	
		} 
	}
	
//	测试用 主函数
//	public static void main(String[] args){
//	   
////		当前时间-数字格式
//			java.util.Date currentTime = new java.util.Date(); 
//	    	SimpleDateFormat formatter = new SimpleDateFormat("MMdd");
//	    String SubmitDate = formatter.format(currentTime);
//		System.out.println("测试AdminUser时间格式："+SubmitDate);
//
//    }
}
