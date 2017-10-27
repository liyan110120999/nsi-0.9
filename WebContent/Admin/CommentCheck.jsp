<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>  
<!DOCTYPE>
<html>
<head>
	<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="../assets/js/public.js"></script>
	<title>新学说后台管理</title>
	<meta charset="UTF-8">
	<!-- 	自适应标签 -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
</head>

<body>
	<script type="text/javascript">		
// 			jq Ajax
 			var pass_insert= function(aa){			    	
 				$.ajax({
 				    type : "get",
 				    async:true,
 				    url : ApiUrl.address+"/Comment_api?whereFrom=verify_pass",	
 				    data: {"Id":aa},
 				    dataType : "jsonp",//数据类型为jsonp  
 				    jsonp: "Callback",//服务端用于接收callback调用的function名的参数  
 				    success : function(data){
//  				        console.log(data);
//  				        $("#testshow").html(JSON.stringify(data))
 				    	alert('成功：审核已通过');
 				    	window.location.reload();
 				    },
 				    error:function(){
 				        alert('失败：请刷新页面，再尝试操作');
 				    }
 				});
			    };
			
 			var No_insert= function(aa){			    	
 				$.ajax({
 				    type : "get",
 				    async:false,
 				    url : ApiUrl.address+"/Comment_api?whereFrom=verify_NoPass",	
 				    data: {"Id":aa},
 				    dataType : "jsonp",//数据类型为jsonp  
 				    jsonp: "Callback",//服务端用于接收callback调用的function名的参数  
 				    success : function(data){
 				    	alert('成功：已拒绝该请求');
 				    	window.location.reload();
 				    },
 				    error:function(){
 				        alert('失败：请刷新页面，再尝试操作');
 				    }
 				});
			    };
			    
			    
	</script>

<!-- 导入顶栏 -->
<jsp:include page="/Admin/header.jsp"/>
	
<!-- 新增审核 -->
	<%  
	//注册数据库驱动  
	Class.forName("com.mysql.jdbc.Driver");  
	//获取数据库连接  
	Connection conn = DriverManager.getConnection(  
	    "jdbc:mysql://localhost:3306/NSI_DATABASE","root","123456");  
	//创建Statement  
	Statement stmt = conn.createStatement();  
	//执行查询  
	ResultSet rs = stmt.executeQuery("select * from nsi_comment where VerifySign=41 order by ReleaseTimeSecond DESC limit 0,40");  
	%>  
	
	<table class="table">
	<caption><h3>用户评论-审核</h3></caption>
	<thead>
		<tr>
			<th>ID</th>
			<th>评论人</th>
			<th>评论分类</th>
			<th>评论对象ID</th>
			<th>发布时间</th>
			<th>点赞数</th>
			<th>踩数</th>
			<th>内容</th>
			<th>审核标记</th>

			<th>操作</th>
		
		</tr>
	</thead>
	<tbody>
	<%  	//遍历结果集  
		while(rs.next())  
	{%>  
	
		<tr class="active">
			<td><%=rs.getString("Id")%></td>
			<td><%=rs.getString("Reviewer")%></td>
			<td><%=rs.getString("Classify")%></td>
			<td><%=rs.getString("SubjectId")%></td>
			<td><%=rs.getString("ReleaseTime")%></td>
			<td><%=rs.getString("Thumbs_up")%></td>
			<td><%=rs.getString("Thumbs_down")%></td>
			<td><%=rs.getString("Content")%></td>
			<td><%=rs.getString("VerifySign")%></td>

			
			<td>
				<button onclick="pass_insert('<%=rs.getString("Id")%>')">通过</button>
				<button onclick="No_insert('<%=rs.getString("Id")%>')">拒绝</button>
			</td>
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