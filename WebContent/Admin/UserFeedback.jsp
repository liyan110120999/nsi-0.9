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
	
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>新学说后台-用户反馈</title>
</head>
<body>
	<script type="text/javascript">	
			
		var pass_insert= function(aa,bb){			
			
//		    	触发积分模态框
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
					<td>操作</td>										
				</tr>
				<tr class="active">
					<td  colspan="6" style="white-space:normal; width:200px;">反馈内容： <%=rs.getString(5)%></td>
					<td>
						<button type="button" class="btn btn-info" onclick="pass_insert('<%=rs.getString(1)%>','<%=rs.getString("index05")%>')">奖励积分</button>
					</td>								
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