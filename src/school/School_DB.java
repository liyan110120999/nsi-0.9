package school;

import java.sql.*;
import java.util.*;

import people.People_model;

public class School_DB {
	
//	搜索 返回list
	public static List<School_model> Search(String sql)
	{
		List<School_model> list = new ArrayList<School_model>();
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
				School_model school = new School_model();		
							
				school.setId(rs.getInt("Id"));
				school.setSchool_name(rs.getString("School_name"));
				school.setSchool_EnglishName(rs.getString("School_EnglishName"));
				school.setSchool_properties(rs.getString("School_properties"));
				school.setAreas(rs.getString("Areas"));
				school.setAreas02(rs.getString("Areas02"));
				school.setAreas03(rs.getString("Areas03"));
				school.setFounded_time(rs.getInt("Founded_time"));
				school.setOperationState(rs.getString("OperationState"));
				school.setSchool_system(rs.getString("School_system"));
				school.setTuition01(rs.getString("Tuition01"));
				school.setTuition02(rs.getString("Tuition02"));
				school.setTuition03(rs.getString("Tuition03"));
				school.setTuition04(rs.getString("Tuition04"));	
				school.setTuitionHigh(rs.getString("TuitionHigh"));	
		
				school.setWebsite(rs.getString("Website"));
				school.setTelephone(rs.getString("Telephone"));
				school.setInter_Course_Founded_time(rs.getInt("Inter_Course_Founded_time"));
				school.setCourse(rs.getString("Course"));
				school.setAuthentication(rs.getString("Authentication"));
				school.setCourse_evaluation(rs.getString("Course_evaluation"));
				
				school.setStudent_Num_All(rs.getInt("Student_Num_All"));				
				school.setStudent_Num01(rs.getInt("Student_Num01"));
				school.setStudent_Num02(rs.getInt("Student_Num02"));
				school.setStudent_Num03(rs.getInt("Student_Num03"));
				school.setStudent_Num04(rs.getInt("Student_Num04"));
				
				school.setStudent_Capacity(rs.getInt("Student_Capacity"));
				school.setGraduated_Stu_Num(rs.getInt("Graduated_Stu_Num"));
				school.setStu_Dominant_nationality(rs.getString("Stu_Dominant_nationality"));
				school.setStu_Year_Investment(rs.getString("Stu_Year_Investment"));
				school.setClub_Num(rs.getString("Club_Num"));
				school.setPresident_Country(rs.getString("President_Country"));
				school.setStaff_Num(rs.getInt("Staff_Num"));
				school.setTeacher_Num(rs.getInt("Teacher_Num"));
				school.setForeign_Teacher_num(rs.getInt("Foreign_Teacher_num"));
				school.setTeacher_Year_Investment(rs.getString("Teacher_Year_Investment"));
				school.setTeacher_Retention(rs.getString("Teacher_Retention"));
				school.setTeacher_Salary(rs.getString("Teacher_Salary"));
				school.setTeacher_Stu_ratio(rs.getString("Teacher_Stu_ratio"));
				school.setCovered_Area(rs.getString("Covered_Area"));
				school.setBuilt_Area(rs.getString("Built_Area"));
				school.setHardware(rs.getString("Hardware"));
				school.setInvestment(rs.getString("Investment"));
				school.setRemark(rs.getString("Remark"));			
				school.setRecent_Modifier(rs.getString("Recent_Modifier"));
				school.setLoad_People(rs.getString("Load_People"));
				school.setLoad_Time(rs.getString("Load_Time"));
				school.setUn09(rs.getString("un09"));
				school.setUn10(rs.getString("un10"));	
				school.setSchool_logo(rs.getString("School_logo"));
				school.setImg01(rs.getString("img01"));
				school.setImg02(rs.getString("img02"));
				school.setImg03(rs.getString("img03"));
				school.setImg04(rs.getString("img04"));
				school.setImg05(rs.getString("img05"));
				school.setBatchInput_Sign(rs.getString("BatchInput_Sign"));
				school.setVerifySign(rs.getString("VerifySign"));
				school.setEvaluate(rs.getString("Evaluate"));
				list.add(school);
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
			System.out.println("School_DB.java:搜索sql异常");	
		}
		return list;
	}
	
	
	public static List<School_model> alter(String sql)
	{
		List<School_model> list = new ArrayList<School_model>();
		try
		{	
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/NSI_DATABASE?useSSL=true";
			String username = "root";
			String password = "123456";
			int i=-1;
			Connection conn = DriverManager.getConnection(url,username,password);
			Statement stmt = conn.createStatement();
			i=stmt.executeUpdate(sql);
			
			System.out.println("school_DB.java:修改：修改sql已执行,影响行数："+i);	
            //关闭结果集
           
            stmt.close();
            conn.close();
		}
		catch(Exception e)
		{
			System.out.println("school_DB.java:修改：修改sql异常");	
			e.printStackTrace();
		}
		return list;
	}
}
