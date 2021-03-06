<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>  
<!-- 读取Properties 依赖  -->
<%@ page import="java.util.*,java.io.*" %>  
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

	<script>  
// 	测出发送
			function testSend()  
				{  		
					document.getElementById("whereFromID").value="testSend";//设置whereFrom值  为 测试
					document.getElementById("formID").submit();	
				}  
// 			正式发送前确认
			function ConfirmSend(){
				var x;
				var r=confirm("确定要正式发送邮件了吗？建议先测试发送！！！");
				if (r==true){
					document.getElementById("whereFromID").value="sendMail";//设置whereFrom值  为 正式发送
					document.getElementById("formID").submit();	
				}
				else{
					x="你按下了\"取消\"按钮!";
				}			
			}

	</script>
<!-- 	上传图片 -->
	<script type="text/javascript">
	    function imgPreview(fileDom){
	        //判断是否支持FileReader
	        if (window.FileReader) {
	            var reader = new FileReader();
	        } else {
	            alert("您的设备不支持图片预览功能，如需该功能请升级您的设备！");
	        }
	
	        //获取文件
	        var file = fileDom.files[0];
	        var imageType = /^image\//;
	        //是否是图片
	        if (!imageType.test(file.type)) {
	            alert("请选择图片！");
	            return;
	        }
	        //读取完成
	        reader.onload = function(e) {
	            //获取图片dom
	            var img = document.getElementById("preview");
	            //图片路径设置为读取的图片
	            img.src = e.target.result;
	        };
	        reader.readAsDataURL(file);
	// 	        使按钮可用
	        var buttonAbled = document.getElementById('updateButton');
// 	 		移除class
	        buttonAbled.removeAttribute("disabled")
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
	ResultSet rs = stmt.executeQuery("select * from nsi_school_data order by load_time DESC limit 0,10");  
	%>  

<% 
	rs.close();	
	stmt.close();	
	conn.close();	
%>	

<%
	 String path = "/WeeklyMail.properties";  
	 Properties prop = new Properties();  
// 	 注意：绝对路径问题
	 String realpath = request.getRealPath("/properties"); 
	 try{  
		 //读取配置文件
		 FileInputStream in = new FileInputStream(realpath+path);  
		 prop.load(in);  
	 }  
	 catch(FileNotFoundException e){  
	     out.println(e);  
	 }  
	 catch(IOException e){out.println(e);} 
	//通过key获取配置文件
	 String MailNum = prop.getProperty("MailNum"); 

%>

<!-- 	邮件内容 -->
	<div style="margin: 0 auto;width: 1100px;height: 500px;text-align:center;">
<!-- 		预览图片 -->
<!-- 	临时改变图片地址 -->

		<img src="http://47.92.84.36:80/upImage/upMailImg/upload/mail<%=MailNum%>1.jpg" class="img-thumbnail">
		<img src="http://47.92.84.36:80/upImage/upMailImg/upload/mail<%=MailNum%>2.jpg" class="img-thumbnail">
		<img src="http://47.92.84.36:80/upImage/upMailImg/upload/mail<%=MailNum%>3.jpg" class="img-thumbnail">
		<br>
		<img src="http://47.92.84.36:80/upImage/upMailImg/upload/mail<%=MailNum%>4.jpg" class="img-thumbnail">
		<img src="http://47.92.84.36:80/upImage/upMailImg/upload/mail<%=MailNum%>5.jpg" class="img-thumbnail">
		<img src="http://47.92.84.36:80/upImage/upMailImg/upload/mail<%=MailNum%>6.jpg" class="img-thumbnail">
		
		
		<h5>图片尺寸：宽*高：170*120；格式：.jpg</h5>期刊号：<%=MailNum%>
		<!-- 图片上传 -->
		<div style="width:100%;height:200px; margin:auto;text-align:center;">
			<form class="form-horizontal" action="/nsi-0.9/MailUpImg" method="post" enctype="multipart/form-data">
				<input style="float:left;margin-left:20px;" class="btn btn-primary" type="file" name="image01" onchange="imgPreview(this)">
				<input style="float:left;margin-left:20px;" class="btn btn-primary" type="file" name="image02" onchange="imgPreview(this)">
				<input style="float:left;margin-left:20px;" class="btn btn-primary" type="file" name="image03" onchange="imgPreview(this)">
				<br><br>
				<input style="float:left;margin-left:20px;" class="btn btn-primary" type="file" name="image04" onchange="imgPreview(this)">
				<input style="float:left;margin-left:20px;" class="btn btn-primary" type="file" name="image05" onchange="imgPreview(this)">
				<input style="float:left;margin-left:20px;" class="btn btn-primary" type="file" name="image06" onchange="imgPreview(this)">
				
				<input style="float:left;margin-left:20px;padding:8px 12px 8px 12px;" type="submit" disabled="disabled" id="updateButton" class="btn btn-primary ">
			</form>
		</div>
		
		<br>
			<h3>邮件期刊内容</h3>
			<h5>标题：12个字以内 摘要：52个字以内。</h5>
			<h5>标题：24-36字， 不写摘要。</h5>
            <form class="bs-example bs-example-form" action="/nsi-0.9/MailSender" name="form1" id="formID" method="post" role="form">
                <div>
					<!--标题 -->
                    <div style="padding: 50px 30px 10px;float: left;">
                            <div class="input-group">
                                <input type="text" class="form-control" name="title01" placeholder="标题1">
                            </div><br>
                            <div class="input-group">
                                <input type="text" class="form-control" name="title02" placeholder="标题2">
                            </div><br>
                            <div class="input-group">
                                <input type="text" class="form-control" name="title03" placeholder="标题3">
                            </div><br>
                            <div class="input-group">
                                <input type="text" class="form-control" name="title04" placeholder="标题4">
                            </div><br>
                            <div class="input-group">
                                <input type="text" class="form-control" name="title05" placeholder="标题5">
                            </div><br>
                            <div class="input-group">
                                <input type="text" class="form-control" name="title06" placeholder="标题6">
                            </div><br>
                    </div>
                    
                    <!--内容 -->
                    <div style="padding: 50px 30px 10px;float: left;">
                            <div class="input-group">
                                <input type="text" class="form-control" name="content01" placeholder="摘要1">
                            </div><br>
                            <div class="input-group">
                                <input type="text" class="form-control" name="content02" placeholder="摘要2">
                            </div><br>
                            <div class="input-group">
                                <input type="text" class="form-control" name="content03" placeholder="摘要3">
                            </div><br>
                            <div class="input-group">
                                <input type="text" class="form-control" name="content04" placeholder="摘要4">
                            </div><br>
                            <div class="input-group">
                                <input type="text" class="form-control" name="content05" placeholder="摘要5">
                            </div><br>
                            <div class="input-group">
                                <input type="text" class="form-control" name="content06" placeholder="摘要6">
                            </div><br>
                    </div>
                    
                    <!--链接-->
                    <div style="padding: 50px 30px 10px;float: left;margin-left:10px;">

                            <div class="input-group" style="width: 359px;">
                                <input type="text" class="form-control" name="link01" placeholder="链接1">
                            </div><br>
                            <div class="input-group" style="width: 359px;">
                                <input type="text" class="form-control" name="link02" placeholder="链接2">
                            </div><br>
                            <div class="input-group" style="width: 359px;">
                                <input type="text" class="form-control" name="link03" placeholder="链接3">
                            </div><br>
                            <div class="input-group" style="width: 359px;">
                                <input type="text" class="form-control" name="link04" placeholder="链接4">
                            </div><br>
                            <div class="input-group" style="width: 359px;">
                                <input type="text" class="form-control" name="link05" placeholder="链接5">
                            </div><br>
                            <div class="input-group" style="width: 359px;">
                                <input type="text" class="form-control" name="link06" placeholder="链接6">
                            </div><br>
                    </div>
                                       
                    <input id="whereFromID" type="hidden" name="whereFrom" value="sendMail">
                </div>
                <div class="clearfix"></div>
                <!--按钮功能组-->
                <div id="myButtons2" class="bs-example">
                	<h5>若测试发送失败，请检查标题是否包含敏感关键字</h5>
                	<button type="button" class="btn btn-primary" onclick="testSend()">测试发送</button>
                    <button type="button" class="btn btn-warning" onclick="ConfirmSend()">正式发送</button>             
                </div>
            </form>
            
            
        </div>



</body>
</html>