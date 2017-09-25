package Institution;
import java.sql.*;
import java.util.*;
import javax.sql.*;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

import people.People_model;
		
	public class Institution_DB {	
		String url = "jdbc:mysql://localhost:3306/NSI_DATABASE?useSSL=true";
		String username = "root";
		String password = "123456";
		
			public static List<Institution_model> Search(String sql)
			{
				List<Institution_model> list = new ArrayList<Institution_model>();
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
						Institution_model Institution = new Institution_model();
						Institution.setId(rs.getInt(1));
						Institution.setName(rs.getString("Name"));
						Institution.setFounded_time(rs.getString("Founded_time"));
						Institution.setAreas(rs.getString("Areas"));
						Institution.setAreas02(rs.getString("areas02"));
						Institution.setAreas03(rs.getString("areas03"));
						Institution.setType(rs.getString("type"));
						Institution.setLabel(rs.getString("label"));		
						Institution.setWebsite(rs.getString("website"));
						Institution.setService(rs.getString("service"));
						Institution.setContactPosition(rs.getString("contactPosition"));
						Institution.setContactName(rs.getString("contactName"));
						Institution.setContactPhone(rs.getString("contactPhone"));
						Institution.setContactMail(rs.getString("contactMail"));
						Institution.setIntroduction(rs.getString("introduction"));
						Institution.setInvestment(rs.getString("investment"));
						Institution.setRemark(rs.getString("remark"));
						Institution.setServedSchool(rs.getString("servedSchool"));
						Institution.setInstitution_logo(rs.getString("institution_logo"));
						Institution.setImg01(rs.getString("img01"));
						Institution.setImg02(rs.getString("img02"));
						Institution.setImg03(rs.getString("img03"));
						Institution.setImg04(rs.getString("img04"));
						Institution.setImg05(rs.getString("img05"));
						Institution.setRecent_Modifier(rs.getString("recent_Modifier"));
						Institution.setLoad_people(rs.getString("load_people"));
						Institution.setLoad_time(rs.getString("load_time"));
						Institution.setBatchInput_Sign(rs.getString("batchInput_Sign"));
						Institution.setVerifySign(rs.getString("verifySign"));
						Institution.setEvaluate(rs.getString("evaluate"));
												
						list.add(Institution);
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
					System.out.println("Institution DB.java:����sql�쳣");	
				}
				return list;
			}
			
			public static List<Institution_model> Insert(String sql)
			{
				List<Institution_model> list = new ArrayList<Institution_model>();
				try
				{	

		            
//		          ����󷵻�����ID
		            Class.forName("com.mysql.jdbc.Driver");
					String url = "jdbc:mysql://localhost:3306/NSI_DATABASE?useSSL=true";
					String username = "root";
					String password = "123456";
					
					int insert_id=00;
					
					Connection conn = DriverManager.getConnection(url,username,password);
					Statement stmt = conn.createStatement();	
					
					stmt.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
					ResultSet rs = stmt.getGeneratedKeys();
					if(rs.next()){
					    insert_id = rs.getInt(1);
						System.out.println("Institution_DB:insert_id:"+insert_id);
					}									
					 rs.close();
					stmt.close();
		            conn.close();	            
//		            �������� ��ѯ������Ŀ������list
		            String SearchSql="select * from nsi_Institution_data where Id ='"+insert_id+"' limit 0,1";
					list =Institution_DB.Search(SearchSql);	
								
				}
				catch(Exception e)
				{
					System.out.println("Institution DB.java:���룺����sql�쳣");	
					e.printStackTrace();
				}
				return list;
			}
			
			public static List<Institution_model> alter(String sql)
			{
				List<Institution_model> list = new ArrayList<Institution_model>();
				try
				{	
					Class.forName("com.mysql.jdbc.Driver");
					String url = "jdbc:mysql://localhost:3306/NSI_DATABASE?useSSL=true";
					String username = "root";
					String password = "123456";
					int i=-1;
					Connection conn = DriverManager.getConnection(url,username,password);
					Statement stmt = conn.createStatement();
//					ResultSet rs = stmt.executeQuery(sql);
					i=stmt.executeUpdate(sql);
					
					System.out.println("Institution_DB.java:�޸ģ��޸�sql��ִ��,Ӱ��������"+i);	
		            //�رս����,���
		           
		            stmt.close();
		            conn.close();
				}
				catch(Exception e)
				{
					System.out.println("Institution_DB.java:�޸ģ��޸�sql�쳣");	
					e.printStackTrace();
				}
				return list;
			}
			
			public static List<Institution_model> Delete(String sql)
			{
				List<Institution_model> list = new ArrayList<Institution_model>();
				try
				{	
					Class.forName("com.mysql.jdbc.Driver");
					String url = "jdbc:mysql://localhost:3306/NSI_DATABASE?useSSL=true";
					String username = "root";
					String password = "123456";
					boolean i=false;
					Connection conn = DriverManager.getConnection(url,username,password);
					Statement stmt = conn.createStatement();
//					ResultSet rs = stmt.executeQuery(sql);
					i=stmt.execute(sql);
					
					System.out.println("Institution_DB.java:ɾ����ɾ��������ִ��");	
		            //�رս����,���
		           
		            stmt.close();
		            conn.close();
				}
				catch(Exception e)
				{
					System.out.println("Institution_DB.java:ɾ��sql�쳣");	
					e.printStackTrace();
				}
				return list;
			}
			
}
