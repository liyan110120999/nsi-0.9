package people;
import java.sql.*;
import java.util.*;
import javax.sql.*;

import subject.Subject_model;
public class DB {
	
	
	String url = "jdbc:mysql://localhost:3306/NSI_DATABASE?useSSL=true";
	String username = "root";
	String password = "123456";
	
	public static List<People_model> Search(String sql)
	{
		List<People_model> list = new ArrayList<People_model>();
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
				People_model people = new People_model();
				people.setPeople_id(rs.getInt("People_id"));
				people.setPeople_name(rs.getString("People_name"));
				people.setPeople_member(rs.getString("People_member"));
				people.setPeople_dueTime(rs.getString("People_dueTime"));
				people.setPeople_work(rs.getString("People_work"));
				people.setPeople_position(rs.getString("People_position"));
				people.setPeople_phone(rs.getString("People_phone"));
				people.setPeople_mail(rs.getString("People_mail"));
				people.setPeople_telephone(rs.getString("People_telephone"));
				people.setPeople_wechat(rs.getString("People_wechat"));
				people.setPeople_loadPeople(rs.getString("People_loadPeople"));
				people.setPeople_loadTime(rs.getString("People_loadTime"));
				people.setPeople_address(rs.getString("People_address"));
				people.setPeople_introduction(rs.getString("People_introduction"));
				people.setPeople_remark(rs.getString("People_remark"));
				people.setPeople_ImgUrl(rs.getString("People_ImgUrl"));
				list.add(people);
			}
			rs.last();
            //�رս����,���
            rs.close();
            stmt.close();
            conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("People DB.java:����sql�쳣");	
		}
		return list;
	}
	
	public static List<People_model> Insert(String sql)
	{
		List<People_model> list = new ArrayList<People_model>();
		try
		{	
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/NSI_DATABASE?useSSL=true";
			String username = "root";
			String password = "123456";
			boolean i=false;
			Connection conn = DriverManager.getConnection(url,username,password);
			Statement stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery(sql);
			i=stmt.execute(sql);
			
			System.out.println("DB.java:���룺���붯����ִ��");	
            //�رս����,���
           
            stmt.close();
            conn.close();
		}
		catch(Exception e)
		{
			System.out.println("DB.java:���룺����sql�쳣");	
			System.out.println("DB.java:���룺--------------------------------������Ϣ��ʧ---����sql�쳣-------------------------");	
			e.printStackTrace();
		}
		return list;
	}
	
	
	
//	test 
	public static int TransactionInsert(String sql01,String sql02)
	{

		        Connection conn = null;
		        PreparedStatement ps1 = null;
		        PreparedStatement ps2 = null;
		        int i=1;
		        try
		        {
//		        	�����������������
		        	Class.forName("com.mysql.jdbc.Driver");
					String url = "jdbc:mysql://localhost:3306/NSI_DATABASE?useSSL=true";
					String username = "root";
					String password = "123456";
					conn = DriverManager.getConnection(url,username,password);
		            conn.setAutoCommit(false); 
		            
		            ps1 = conn.prepareStatement(sql01);//?��ռλ��
		            ps1.execute();
	            
		            System.out.println("����mysql ���� sql01ִ��");	            
			            try 
			            {
			                Thread.sleep(3000);
			            } 
			            catch (InterruptedException e) 
			            {
			                e.printStackTrace();
			                i=-1;
			            }
		            
		            ps2 = conn.prepareStatement(sql02);
		            ps2.execute();            
		            System.out.println("����mysql ���� sql02ִ��");
//		            -------------------------------------------�ύ����--------------------------------------------------------------------------------------
		            conn.commit();//�ύ����
		         }
		         catch (ClassNotFoundException e)
		         {
		            e.printStackTrace();
			            try 
			            {
	//		            	--------------------------------------------------ʧ�ܻع�---------------------------------------------------------------------------
			                conn.rollback();//ĳһ���������ʧ��ʱ���ع�
			                System.out.println("ִ���˻ع��¼�----------���ش���");
			            } 
			            catch (SQLException e1) 
			            {
			                e1.printStackTrace();
			            }
		            i=-2;
		        } 
		        catch (SQLException e) 
		        {
		            e.printStackTrace();
		            i=-3;
		        }
		        finally
		        {
		            try 
		            {
		                if(ps1!=null)
		                {
		                    ps1.close();
		                }
		            } 
		            catch (SQLException e) 
		            {
		                e.printStackTrace();
		            }
		            
		            try 
		            {
		                if(conn!=null)
		                {
		                    conn.close();
		                }
		            } 
		            catch (SQLException e) 
		            {
		                e.printStackTrace();
		            }
		        }		
		return i;
	}
	
	
	
	public static List<People_model> Delete(String sql)
	{
		List<People_model> list = new ArrayList<People_model>();
		try
		{	
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/NSI_DATABASE?useSSL=true";
			String username = "root";
			String password = "123456";
			boolean i=false;
			Connection conn = DriverManager.getConnection(url,username,password);
			Statement stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery(sql);
			i=stmt.execute(sql);
			
			System.out.println("People DB.java:ɾ����ɾ��������ִ��");	
            //�رս����,���
           
            stmt.close();
            conn.close();
		}
		catch(Exception e)
		{
			System.out.println("People DB.java:ɾ��sql�쳣");	
			e.printStackTrace();
		}
		return list;
	}

	
	public static List<People_model> alter(String sql)
	{
		List<People_model> list = new ArrayList<People_model>();
		try
		{	
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/NSI_DATABASE?useSSL=true";
			String username = "root";
			String password = "123456";
			int i=-1;
			Connection conn = DriverManager.getConnection(url,username,password);
			Statement stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery(sql);
			i=stmt.executeUpdate(sql);
			
			System.out.println("DB.java:�޸ģ��޸�sql��ִ��,Ӱ��������"+i);	
            //�رս����,��
           
            stmt.close();
            conn.close();
		}
		catch(Exception e)
		{
			System.out.println("DB.java:�޸ģ��޸�sql�쳣");	
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static int count(String sql)
	{
//		List<People_model> list = new ArrayList<People_model>();
		int countNum=-1;
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/NSI_DATABASE?useSSL=true";
			String username = "root";
			String password = "123456";
			
			
			Connection conn = DriverManager.getConnection(url,username,password);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.last();
			countNum=rs.getRow();
			System.out.println("People DB.java:������"+countNum);	
                     
			 rs.close();
	         stmt.close();
	         conn.close();
		}
		catch(Exception e)
		{
			System.out.println("People DB.java:����sql�쳣");	
			e.printStackTrace();
		}
		return countNum;
	}
	
//	talent�˲� ��������
	public static List<Talent_model> SearchTalent(String sql)
	{
		List<Talent_model> list = new ArrayList<Talent_model>();
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
				Talent_model talent_model = new Talent_model();
				
				talent_model.setId(rs.getInt("Id"));
				talent_model.setName(rs.getString("Name"));
				talent_model.setSex(rs.getString("Sex"));
				talent_model.setPhone(rs.getString("Phone"));
				talent_model.setMail(rs.getString("Mail"));
				talent_model.setEducation(rs.getString("Education"));
				talent_model.setMajor(rs.getString("Major"));
				talent_model.setNowWorkplace(rs.getString("NowWorkplace"));
				talent_model.setWorkYear(rs.getString("WorkYear"));
				talent_model.setExpectWorkPlace(rs.getString("ExpectWorkPlace"));
				talent_model.setExpectWorkPosition(rs.getString("ExpectWorkPosition"));
				talent_model.setExpectSalary(rs.getString("ExpectSalary"));
				talent_model.setOther(rs.getString("Other"));
				talent_model.setWorkExperience(rs.getString("WorkExperience"));
				talent_model.setEducationBackground(rs.getString("EducationBackground"));
				talent_model.setTrainingBackground(rs.getString("TrainingBackground"));
				talent_model.setPublic(rs.getString("Public"));
				talent_model.setHavaTalent(rs.getString("HavaTalent"));
				talent_model.setUserMail(rs.getString("UserMail"));
				talent_model.setLoad_time(rs.getString("Load_time"));
				
				list.add(talent_model);
			}
			rs.last();
            //�رս����,���
            rs.close();
            stmt.close();
            conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("DB.java:�˲�����sql�쳣");	
		}
		return list;
	}
	
//	��Ŀ ����
	public static List<Subject_model> SearchSubject(String sql)
	{
		List<Subject_model> list = new ArrayList<Subject_model>();
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
				Subject_model subject_model = new Subject_model();
				
				subject_model.setId(rs.getInt("Id"));
				subject_model.setSubjectName(rs.getString("SubjectName"));
				subject_model.setAreas(rs.getString("Areas"));
				subject_model.setAreas02(rs.getString("Areas02"));
				subject_model.setAreas03(rs.getString("Areas03"));
				subject_model.setCompany(rs.getString("Company"));
				subject_model.setSubjectLabel(rs.getString("SubjectLabel"));
				subject_model.setName(rs.getString("Name"));
				subject_model.setPhone(rs.getString("Phone"));
				subject_model.setMail(rs.getString("Mail"));
				subject_model.setSubjectIntroduction(rs.getString("SubjectIntroduction"));
				subject_model.setDetailInstitution(rs.getString("DetailInstitution"));
				subject_model.setRequirement(rs.getString("Requirement"));
				subject_model.setUserMail(rs.getString("UserMail"));
				subject_model.setLoad_time(rs.getString("Load_time"));
			
				list.add(subject_model);
			}
			rs.last();
            //�رս����,���
            rs.close();
            stmt.close();
            conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("DB.java:��Ŀ����sql�쳣");	
		}
		return list;
	}
}
