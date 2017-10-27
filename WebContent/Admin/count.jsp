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
<!-- 	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" /> -->
</head>

<body>
	<script type="text/javascript">				    
			    function getRegisterCount() {
			        $.ajax({
			            type :"get",
			            async:true,
			            traditional :true,
			            data:'',
			            url:ApiUrl.address+"/Admin_api?whereFrom=RegisterPeopleCount",
			            dataType:"jsonp",//数据类型为jsonp  
			            jsonp:"Callback",//服务端用于接收callback调用的function名的参数  
			            success : function(msg){
			                console.log(msg)
			                $('#badge01').text(msg.countAllRS01)
			                $('#badge02').text(msg.countAllRS02)
			                $('#badge03').text(msg.countAllRS03)
			            },
			            error:function(){
			                alert('发生错误，请求数据失败！');
			            }
			        });
			    }
	 $(function(){
		 getRegisterCount()
	 })		 
	 
	</script> 

<!-- 导入顶栏 -->
<jsp:include page="/Admin/header.jsp"/>

  
	<div style="text-align:center;">
		<input class="btn btn-primary" type="button" value="官网访问统计" onclick="window.open('https://tongji.baidu.com/web/welcome/ico?s=e898a1b6e73616a0c84313f55a47efd6')">
		<input class="btn btn-primary" type="button" value="数据库访问统计" onclick="window.open('https://tongji.baidu.com')">
		<br><br>
			<!-- 	今日注册用户，昨日注册用户，总注册用户 -->
		<a href="#">今日注册用户 <span class="badge" id="badge01">00</span></a>
		<a href="#">昨日注册用户<span class="badge" id="badge02">00</span></a>
		<a href="#">总注册用户<span class="badge" id="badge03">00</span></a>
	</div>
	

</body>
</html>