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
		
//		��ǰʱ��-���ָ�ʽ
			java.util.Date currentTime = new java.util.Date(); 
	    	SimpleDateFormat formatter = new SimpleDateFormat("MMdd");
	    	String SubmitDate = formatter.format(currentTime);
    	int DateCode=Integer.parseInt(SubmitDate);
    	DateCode = DateCode+3223;
		System.out.println("����AdminUserʱ���ʽ��"+SubmitDate);
		
		HttpSession session=request.getSession(); 
//		����û��������code��ͨ������ת	
		if(supercode==DateCode){
			System.out.println("��̨-�����û�ͨ��");
			session.setAttribute("Session_AdminUser",supercode);
			response.sendRedirect("http://data.xinxueshuo.cn/nsi-0.9/Admin/count.jsp");		
//			δ���
			
		}else{
			System.out.println("��̨-�����û�δͨ��");
			response.sendRedirect("http://data.xinxueshuo.cn/nsi/other/index.html");	
		} 
	}
	
//	������ ������
//	public static void main(String[] args){
//	   
////		��ǰʱ��-���ָ�ʽ
//			java.util.Date currentTime = new java.util.Date(); 
//	    	SimpleDateFormat formatter = new SimpleDateFormat("MMdd");
//	    String SubmitDate = formatter.format(currentTime);
//		System.out.println("����AdminUserʱ���ʽ��"+SubmitDate);
//
//    }
}
