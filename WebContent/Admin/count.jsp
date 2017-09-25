<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>  
<!DOCTYPE>
<html>
<head>

	<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    
	<title>新学说后台管理</title>
	<meta charset="UTF-8">
	<!-- 	自适应标签 -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
</head>

<body>
	<script type="text/javascript">		
 			var pass= function(aa){			    	
			        $.ajax({
			            url: '/nsi-0.9/Verify',
			            type: 'post',
			            dataType:'json',
			            data: {verify:aa},
			            success: function(msg) {
			            }
			        });				        
			      alert('ajax is ok!'+aa)		    	
			    };
	</script>

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
	ResultSet rs = stmt.executeQuery("select * from nsi_school_data order by load_time DESC limit 0,10");  
	%>  
	<div style="text-align:center;">
		<a href="https://tongji.baidu.com/web/welcome/ico?s=e898a1b6e73616a0c84313f55a47efd6" target="_blank"><h4>官网访问统计</h4></a>
	</div>
		<table class="table">
			<caption><h3>统计信息</h3></caption>
			<thead>
				<tr>
					<th>id</th>
					<th>名称</th>
					<th>英文名</th>	
					<th>性质</th>	
					<th>地区01</th>	
					<th>地区02</th>	
					<th>地区03</th>			
					<th>建校时间</th>	
					<th>运营状态</th>	
						
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
					<td><%=rs.getString(5)%></td>	
					<td><%=rs.getString(6)%></td>	
					<td><%=rs.getString(7)%></td>	
					<td><%=rs.getString(8)%></td>	
					<td><%=rs.getString(9)%></td>
					<td><%=rs.getString(10)%></td>
					<td><%=rs.getString(11)%></td>
					<td><%=rs.getString(12)%></td>
					<td><%=rs.getString(13)%></td>
					<td><%=rs.getString(14)%></td>
					<td><%=rs.getString(15)%></td>
					<td><%=rs.getString(16)%></td>
					<td><%=rs.getString(17)%></td>
					<td><%=rs.getString(18)%></td>
					<td><%=rs.getString(19)%></td>
					<td><%=rs.getString(20)%></td>
					<td><%=rs.getString(21)%></td>
					
												
				</tr>		
			<%}%>  
			</tbody>
		</table>
<% 
	rs.close();	
	stmt.close();	
	conn.close();	
%>	



</body>
</html>