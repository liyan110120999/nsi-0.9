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
 				    async:true,
 				    url : ApiUrl.address+"/Comment_api?whereFrom=verify_pass",	
 				    data: {"Id":aa},
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
			    
// 				增加积分    
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
				<button type="button" class="btn btn-info" onclick="pass_insert('<%=rs.getString("Id")%>','<%=rs.getString("Reviewer")%>')">通过</button>
				<button type="button" class="btn btn-warning" onclick="No_insert('<%=rs.getString("Id")%>')">拒绝</button>
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

<!-- 加积分模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">获得积分</h4>
            </div>
            <div class="modal-body">
                <p class="getMail">kong</p>
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