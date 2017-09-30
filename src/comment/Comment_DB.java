package comment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Institution.Institution_model;

public class Comment_DB {
	String url = "jdbc:mysql://localhost:3306/NSI_DATABASE?useSSL=true";
	String username = "root";
	String password = "123456";
	
		public static List<Comment_model> Search(String sql)
		{
			List<Comment_model> list = new ArrayList<Comment_model>();
			try
			{	
				Class.forName("com.mysql.jdbc.Driver");
				String url = "jdbc:mysql://localhost:3306/NSI_DATABASE?useSSL=true";
				String username = "root";
				String password = "123456";

				Connection conn = DriverManager.getConnection(url,username,password);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				
				while(rs.next()){				
					Comment_model comment_model = new Comment_model();
					comment_model.setId(rs.getInt("Id"));
					comment_model.setReviewer(rs.getString("Reviewer"));
					comment_model.setClassify(rs.getString("Classify"));
					comment_model.setSubjectId(rs.getString("SubjectId"));
					comment_model.setReleaseTime(rs.getString("ReleaseTime"));
					comment_model.setReleaseTimeSecond(rs.getString("ReleaseTimeSecond"));
					comment_model.setThumbs_up(rs.getString("Thumbs_up"));
					comment_model.setThumbs_down(rs.getString("Thumbs_down"));
					comment_model.setContent(rs.getString("Content"));
					comment_model.setVerifySign(rs.getString("VerifySign"));
															
					list.add(comment_model);
				}
				rs.last();
	            //关闭结果集,语句
	            rs.close();
	            stmt.close();
	            conn.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("Comment_DB DB.java:搜索sql异常");	
			}
			return list;
		}
}
