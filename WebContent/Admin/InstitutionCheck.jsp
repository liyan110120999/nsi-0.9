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
 			var pass_insert= function(aa,bb){			    	
 				$.ajax({
 				    type : "get",
 				    async:false,
 				    url : ApiUrl.address+"/Institution_api?whereFrom=verify_insert",	
 				    data: {"institution_Id":aa},
 				    dataType : "jsonp",//数据类型为jsonp  
 				    jsonp: "Callback",//服务端用于接收callback调用的function名的参数  
 				    success : function(data){
//				    	触发积分模态框
	 				   	$(function() { $('#myModal').modal({ keyboard: true }) });
			            $('.getMail').text(bb)
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
	 				    url : ApiUrl.address+"/Institution_api?whereFrom=verify_No_insert",	
	 				    data: {"institution_Id":aa},
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
			    
			    
 			var pass_alter= function(aa,bb){			    		 				
 				$.ajax({
 				    type : "get",
 				    async:false,
 				    url : ApiUrl.address+"/Institution_api?whereFrom=verify_alter",	
 				    data: {"institution_Id":aa},
 				    dataType : "jsonp",//数据类型为jsonp  
 				    jsonp: "Callback",//服务端用于接收callback调用的function名的参数  
 				    success : function(data){
//				    	触发积分模态框
	 				   	$(function() { $('#myModal').modal({ keyboard: true }) });
			            $('.getMail').text(bb)
 				    },
 				    error:function(){
 				    	 alert('失败：请刷新页面，再尝试操作');
 				    }
 				});
			    };
			    
			    var No_alter= function(aa){			    		 				
	 				$.ajax({
	 				    type : "get",
	 				    async:false,
	 				    url : ApiUrl.address+"/Institution_api?whereFrom=verify_No_alter",	
	 				    data: {"institution_Id":aa},
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
//	 		增加积分    
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

<!-- 导入顶栏 -->
<jsp:include page="/Admin/header.jsp"/>
	
<!-- 	新增审核 -->
	<%  
	//注册数据库驱动  
	Class.forName("com.mysql.jdbc.Driver");  
	//获取数据库连接  
	Connection conn = DriverManager.getConnection(  
	    "jdbc:mysql://localhost:3306/NSI_DATABASE","root","123456");  
	//创建Statement  
	Statement stmt = conn.createStatement();  
	//执行查询  
	ResultSet rs = stmt.executeQuery("select * from nsi_institution_data_verify where VerifySign=21 order by load_time DESC limit 0,10");  
	%>  
	
	<table class="table">
	<caption><h3>机构新增-审核</h3></caption>
	<thead>
		<tr>
			<th>临时ID</th>
			<th>学校名(英文名)</th>
			<th>性质</th>
			<th>地区</th>
			<th>成立时间</th>
			<th>学制</th>
			<th>课程</th>
			<th>学费</th>
			<th>官网</th>
			<th>电话</th>			
			<th>用户</th>
			<th>提交时间</th>
			<th>操作</th>		
		</tr>
	</thead>
	<tbody>
	<%  	//遍历结果集  
		while(rs.next())  
	{%>  	
		<tr class="active">
			<td><%=rs.getString("Id")%></td>
			<td><%=rs.getString("Name")%></td>
			<td><%=rs.getString("Founded_time")%></td>
			<td><%=rs.getString("Areas")+rs.getString("Areas02")+rs.getString("Areas03")%></td>
			<td><%=rs.getString("Type")%></td>
			<td><%=rs.getString("Label")%></td>
			<td><%=rs.getString("Service")%></td>
			<td><%=rs.getString("ContactPosition")%></td>
			<td><%=rs.getString("ContactName")%></td>
			<td><%=rs.getString("ContactPhone")%></td>				
			<td><%=rs.getString("load_people")%></td>
			<td><%=rs.getString("load_time")%></td>
		
			<td>
				<button type="button" class="btn btn-info" onclick="pass_insert('<%=rs.getString(1)%>','<%=rs.getString("load_people")%>')">通过</button>
				<button type="button" class="btn btn-warning" onclick="No_insert('<%=rs.getString(1)%>')">拒绝</button>
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

<!-- 	修改审核-->
	<%  
	//注册数据库驱动  
	Class.forName("com.mysql.jdbc.Driver");  
	//获取数据库连接  
	Connection conn02 = DriverManager.getConnection(  
	    "jdbc:mysql://localhost:3306/NSI_DATABASE","root","123456");  
	//创建Statement  
	Statement stmt02 = conn02.createStatement();  
	//执行查询  
	ResultSet rs02 = stmt02.executeQuery("select * from nsi_institution_data_verify where VerifySign=22 order by load_time DESC limit 0,10");  
	%>  
	
	<table class="table">
	<caption><h3>机构修改-审核</h3></caption>
	<thead>
		<tr>
			<th>临时ID</th>
			<th>学校名(英文名)</th>
			<th>性质</th>
			<th>地区</th>
			<th>成立时间</th>
			<th>学制</th>
			<th>课程</th>
			<th>学费</th>
			<th>官网</th>
			<th>电话</th>
			
			<th>用户</th>
			<th>提交时间</th>
			<th>操作</th>
		
		</tr>
	</thead>
	<tbody>
	<%  	//遍历结果集  
		while(rs02.next())  
	{%>  
	
		<tr class="active">
			<td><%=rs02.getString("Id")%></td>
			<td><%=rs02.getString("Name")%></td>
			<td><%=rs02.getString("Founded_time")%></td>
			<td><%=rs02.getString("Areas")+rs02.getString("Areas02")+rs02.getString("Areas03")%></td>
			<td><%=rs02.getString("Type")%></td>
			<td><%=rs02.getString("Label")%></td>
			<td><%=rs02.getString("Service")%></td>
			<td><%=rs02.getString("ContactPosition")%></td>
			<td><%=rs02.getString("ContactName")%></td>
			<td><%=rs02.getString("ContactPhone")%></td>				
			<td><%=rs02.getString("load_people")%></td>
			<td><%=rs02.getString("load_time")%></td>
						
			<td>
				<button type="button" class="btn btn-info" onclick="pass_alter('<%=rs02.getString(1)%>','<%=rs02.getString("load_people")%>')">通过</button>
				<button type="button" class="btn btn-warning" onclick="No_alter('<%=rs02.getString(1)%>')">拒绝</button>			
			</td>
		</tr>		
	<%}%>  
	</tbody>
</table>
<% 
	rs02.close();	
	stmt02.close();	
	conn02.close();	
%>	

<!-- 加积分模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">获得积分</h4>
            </div>
            <div class="modal-body">
                <p class="getMail">kong</p >
                <p>审核通过，请选择为该用户增加积分数量</p>
                <button onclick="getScores(10)" class="btn btn-small btn-info">+ 10（评论）</button>
                <button onclick="getScores(30)" class="btn btn-small btn-info">+ 30（建议）</button>
                <button onclick="getScores(50)" class="btn btn-small btn-info">+ 50（机构 || 基本信息）</button>
                <button onclick="getScores(80)" class="btn btn-small btn-info">+ 80（学校）</button>
                <button onclick="getScores(100)" class="btn btn-small btn-info" style="margin-top:8px;">+ 100（完整信息）</button>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


</body>
</html>