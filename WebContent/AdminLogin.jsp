<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8"%>  
<%@ page contentType="text/html;charset=UTF-8"%>  
<html>
<head>

		<link href="assets/css/bootstrap.css" rel="stylesheet" />
	     <!-- FONTAWESOME STYLE CSS -->
	    <link href="assets/css/font-awesome.css" rel="stylesheet" />
	    <!-- CUSTOM STYLE CSS -->
	    <link href="assets/css/style.css" rel="stylesheet" />
		  <!--     	自定义css -->
	    <link href="assets/css/MyCss.css" rel="stylesheet" />
<!-- 		<script src="assets/js/jquery-1.11.1.js"></script> -->
		<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>		
	    <!-- BOOTSTRAP SCRIPTS  -->
	    <script src="assets/js/bootstrap.js"></script>
	    <!-- CUSTOM SCRIPTS  -->
	    <script src="assets/js/custom.js"></script>
	    
<title>新学说数据库-后台管理-登录</title>
<meta charset="UTF-8">
<!-- 	自适应标签 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />

</head>

	<style type="text/css"> 
		#formDIV
		{		
			background-color:#c7d0d5;
			box-shadow: 5px 5px 3px #333333;
			border-radius:10px;
		}
	</style>

	
<script language="javascript">
  function fcheck()
  {
    var uid = document.all.username.value;
    if(uid=="")
    {
      alert("输入不能为空!!!");
      return;
    }
    document.loginform.submit();
  }
</script>
<body>  
<!-- 		顶栏 -->
		<nav class="navbar navbar-fixed-top my-navbar" role="navigation">  
	        <div class="container-fluid">  
	            <div class="navbar-header l-h-001">  
	                <button type="button" class="navbar-toggle" data-toggle="collapse" 
	                 data-target="#example-navbar-collapse"> 
	                   	<span class="icon-bar"></span> 
                     	<span class="icon-bar"></span> 
                  	 	<span class="icon-bar"></span> 
	                </button>  
	                <a class="navbar-brand l-f24" href="#"><strong>新学说数据系统-后台管理</strong></a>  
	            </div>  
	            <div class="collapse navbar-collapse l-f20" id="example-navbar-collapse">  
	                <ul class="nav navbar-nav navbar-right l-h-002">  
<!-- 		                <li><a href="#"><b>新学说后台登录入口</b></a></li> -->
<!-- 	                    <li><a href="#"><b>机 构</b></a></li> -->
<!-- 	                   	<li><a href="#"><b>人 员</b></a></li> -->
<!-- 	                    <li><a href="about.jsp"><b>关 于</b></a></li> -->
<!-- 	                    <li><a href="login.jsp" class="active-menu-item"><b>登 录</b></a></li> -->
<!-- 		                <li></li> -->
	                </ul>  
	            </div>  
	        </div>  
	    </nav> 

	    
		<script>  
// 		        $(window).scroll(function () {  
// 		            if ($(".navbar").offset().top > 50) {$(".navbar-fixed-top").addClass("top-nav");  
// 		            }else {$(".navbar-fixed-top").removeClass("top-nav");}  
// 		        })
	    </script>  

    <%   
    String loginResult="null";
    String Codefal="Codefal";
    String userfalse="userfal";
    
    String aaa ="kong";
//     loginResult=request.getAttribute("loginResult").toString();   
//     if(loginResult.equals(userfalse)){
	if(request.getAttribute("loginResult")!=null){
		aaa=(String)request.getAttribute("loginResult");
	}else{
		 aaa="kong02";
	}
	
	if(aaa.equals(userfalse)){
    	%>
		<script>
			confirm("账号或密码错误，重新输入"); 
		</script>
		<%
    }else if(aaa.equals(Codefal)){    	
    	%>
		<script>
			confirm("您的账号没有激活或暂未通过审核，请检查邮箱是否收到激活邮件，若已激活请耐心等待审核。"); 
		</script>
		<%
    }else {
    	loginResult="kong";
    }   
    %>
<section>
	<div class="container">
		<div class="row clearfix">		
			<div class="col-md-8 column center-block" style="margin-top:2%;">		
				<img src="assets/img/pic-login.jpg"  width="550" height="450"class="img-responsive" alt="响应式图像"/>				
			</div>
			<br>
				
			<div class="col-md-3 column form-group">
				<br>
				<form role="form" name="loginform" action="AdminUser" method="post" style="padding: 15px;">
					<div class="form-group">
						 <label for="exampleInputEmail1" style="color:#777">联系管理员获取动态码</label>
						 <input name="supercode" type="text" class="form-control" id="username" placeholder="请输入动态验证码" />
					</div>		
<!-- 						type 不能为submit ,会重复提交表单 （js） -->
					<button type="button" class="btn btn-primary form-control" onclick="fcheck()">登 录</button>																		
				</form>				
			</div>
		</div>
	</div>
</section>
   
   
   	<!--     导入底栏jsp文件 -->
	<div><jsp:include page="modular/bottomBar.jsp"/>
	</div>
 
    <script src="assets/js/bootstrap.js"></script>
    <!-- CUSTOM SCRIPTS  -->
    <script src="assets/js/custom.js"></script>
    


	</body>  
</html>