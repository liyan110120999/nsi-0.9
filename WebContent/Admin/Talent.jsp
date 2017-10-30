<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<meta charset="UTF-8">
	<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<!-- 	自适应标签 -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
<title>人才简历</title>
</head>
<body>

<!-- 导入顶栏 -->
<jsp:include page="/Admin/header.jsp"/>

	<script type="text/javascript">	
// 			激活模态框
			var talent_pass= function(bb){	
				//	触发积分模态框
			   	$(function() { $('#myModal').modal({ keyboard: true }) });
		    	$('.getMail').text(bb)	   	
		  	};
		  	
			//增加积分    
		    function getScores(scores) {
		        $.ajax({
		            type : "get",
		            async:false,
		            url : ApiUrl.address+"/User_api?whereFrom=Score",
		            data: {
		                "UserMail":$('.getMail').text(),
		                "ScoreNum":scores
		            },
		            dataType : "jsonp",//数据类型为jsonp  
		            jsonp: "Callback",//服务端用于接收callback调用的function名的参数  
		            success : function(data){
		                alert('成功：积分加'+scores);
		                window.location.reload();
		            },
		            error:function(){
		                alert('失败：请刷新页面，再尝试操作');
		            }
		        });
		    }	
	</script>
	<%  
	//注册数据库驱动  
	Class.forName("com.mysql.jdbc.Driver");  
	//获取数据库连接  
	Connection conn = DriverManager.getConnection(  
	    "jdbc:mysql://localhost:3306/NSI_DATABASE?useSSL=true","root","123456");  
	//创建Statement  
	Statement stmt = conn.createStatement();  
	//执行查询  
	ResultSet rs = stmt.executeQuery("select * from nsi_talent order by Load_time DESC limit 0,60");  
	%>  
	
	<table class="table">
	<caption><h3>提交的简历</h3></caption>
	<thead>
		<tr>
			<th>ID</th>
			<th>姓名</th>
			<th>电话</th>
			<th>邮件</th>
			<th>最高学历</th>
			<th>专业</th>
			<th>积分</th>
			<th>注册时间</th>
			<th>操作</th>
		
		</tr>
	</thead>
	<tbody>
	<%  	//遍历结果集  
		while(rs.next())  
	{%>  
		<tr class="active">
			<td><%=rs.getString(1)%></td>
			<td><%=rs.getString(2)%></td>
			<td><%=rs.getString(4)%></td>
			<td><%=rs.getString(5)%></td>
			<td><%=rs.getString(6)%></td>
			<td><%=rs.getString(7)%></td>
			<td><%=rs.getString(9)%></td>
			<td><%=rs.getString(10)%></td>
			
			<td><button onclick="talent_pass('<%=rs.getString("UserMail")%>')">赠送积分</button></td>
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