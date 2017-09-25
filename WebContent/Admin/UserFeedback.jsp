<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>  
<!DOCTYPE>
<html>
<head>
	<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>新学说后台-用户反馈</title>
</head>
<body>
<!-- 导入顶栏 -->
<jsp:include page="/Admin/header.jsp"/>

	<%  
	//注册数据库驱动  
	Class.forName("com.mysql.jdbc.Driver");  
	//获取数据库连接  
	Connection conn = DriverManager.getConnection(  
	    "jdbc:mysql://localhost:3306/NSI_DATABASE","root","123456");  
	//创建Statement  
	Statement stmt = conn.createStatement();  
	//执行查询  
	ResultSet rs = stmt.executeQuery("select * from nsi_log order by index02 DESC limit 0,20");  
	%>
	<div class="table-responsive">  
		<table class="table">
			<caption><h3>用户反馈信息</h3></caption>
			<thead>
				<tr>
					<th>ID</th>
					<th>标记</th>
					<th>用户</th>
					<th>时间</th>
					<th>姓名</th>
<!-- 					<th>内容</th> -->
					<th>联系方式</th>	
				</tr>
			</thead>
			<tbody>
			<%  	//遍历结果集  
				while(rs.next())  
			{%>  	
				<tr class="active">
					<td><%=rs.getString(1)%></td>
					<td><%=rs.getString(2)%></td>
					<td><%=rs.getString(3)%></td>	
					<td><%=rs.getString(4)%></td>
					<td><%=rs.getString(7)%></td>
					<td><%=rs.getString(6)%></td>											
				</tr>
				<tr class="active">
					<td  colspan="6" style="white-space:normal; width:200px;">反馈内容： <%=rs.getString(5)%></td>											
				</tr>
			<%}%>  
			</tbody>
		</table>
	</div>
<% 
	rs.close();	
	stmt.close();	
	conn.close();	
%>	
</body>
</html>