<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.sql.*" %>  
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加邮件联系人</title>

	<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<script type="text/javascript">		
// 			jq Ajax
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

<% 
	rs.close();	
	stmt.close();	
	conn.close();	
%>	
<div style="margin: 0 auto;width: 400px;height: 500px;text-align:center;">
	<h3>添加邮件联系人</h3>
<form class="bs-example bs-example-form" action="/nsi-0.9/MailSender" method="post" role="form">
	<input type="text" class="form-control" name="name" placeholder="姓名">
	<br>
	<input type="text" class="form-control" name="email" placeholder="邮箱地址"><br>
	
	<input type="hidden" name="whereFrom" value="AddMailUser">
	<input type="submit" class="form-control" value="提交">
</form>
</div>

</body>
</html>