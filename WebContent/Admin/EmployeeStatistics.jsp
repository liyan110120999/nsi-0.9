<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>  
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>员工贡献月统计</title>
</head>
	
	<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
	<script src="../assets/js/public.js"></script>
<body>
<!-- 导入顶栏 -->
<jsp:include page="/Admin/header.jsp"/>

	<button type="button" class="btn btn-primary" id="getSchool">学校录入统计</button>
	<button type="button" class="btn btn-primary" id="getpeople">人才录入统计</button>
	<button type="button" class="btn btn-primary" id="getInstitution">机构录入统计</button>
	<h5 id="testshow">空</h5>

<div class="table" >
	<table class="table1" style="float:left;margin-left:20px; width:150px;">
	  <thead>
	    <tr>
	      <th>姓名</th>
	      <th>贡献量</th>
	    </tr>
	  </thead>
	  <tbody>
	    
	  </tbody>
	</table>
	
	<table class="table2" style="float:left;margin-left:20px;width:150px;">
	  <thead>
	    <tr>
	      <th>姓名</th>
	      <th>贡献量</th>
	    </tr>
	  </thead>
	  <tbody>
	    
	  </tbody>
	</table>
	
	<table class="table3" style="float:left;margin-left:20px; width:150px;">
	  <thead>
	    <tr>
	      <th>姓名</th>
	      <th>贡献量</th>
	    </tr>
	  </thead>
	  <tbody>
	    
	  </tbody>
	</table>
</div>

	


	<script>
	  $('#getSchool').click(function () {
		  $.ajax({
			    type : "get",
			    async:false,
			    url : ApiUrl.address+"/Admin_api?whereFrom=Staff_MonthlyCount_school&Month_Time_Key=2017-09",	
			    dataType : "jsonp",//数据类型为jsonp  
			    jsonp: "Callback",//服务端用于接收callback调用的function名的参数  
			    success : function(data){
// 			        console.log(data);
// 			        $("#testshow").html(JSON.stringify(data))
			    	var item; 
					$.each(data,function(i,result){ 
					item = "<tr><td>"+result['name']+"</td><td>"+result['value']+"</td></tr>"; 
					$('.table1').append(item); 
					}); 
			    },
			    error:function(){
			        alert('fail');
			    }
			});
	  })
	  
	//   人脉信息录入统计
	   $('#getpeople').click(function () {
		  $.ajax({
			    type : "get",
			    async:false,
			    url : ApiUrl.address+"/Admin_api?whereFrom=Staff_MonthlyCount_people&Month_Time_Key=2017-09",
			    dataType : "jsonp",//数据类型为jsonp  
			    jsonp: "Callback",//服务端用于接收callback调用的function名的参数  
			    success : function(data){
// 			        console.log(data);
// 			        $("#testshow").html(JSON.stringify(data))
			    	var item; 
					$.each(data,function(i,result){ 
					item = "<tr><td>"+result['name']+"</td><td>"+result['value']+"</td></tr>"; 
					$('.table2').append(item); 
					}); 
			    },
			    error:function(){
			        alert('fail');
			    }
			});
	  })
	  
	  
	  //   机构信息录入统计
	   $('#getInstitution').click(function () {
		  $.ajax({
			    type : "get",
			    async:false,
			    url : ApiUrl.address+"/Admin_api?whereFrom=Staff_MonthlyCount_institution&Month_Time_Key=2017-08",
			    dataType : "jsonp",//数据类型为jsonp  
			    jsonp: "Callback",//服务端用于接收callback调用的function名的参数  
			    success : function(data){
// 			        console.log(data);
// 			        $("#testshow").html(JSON.stringify(data[1]))
					var item; 
					$.each(data,function(i,result){ 
					item = "<tr><td>"+result['name']+"</td><td>"+result['value']+"</td></tr>"; 
					$('.table3').append(item); 
					}); 
			    },
			    error:function(){
			        alert('fail');
			    }
			});
	  })
	</script>
	
	
</body>
</html>