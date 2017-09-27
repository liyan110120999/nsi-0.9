 <%@ page contentType="text/html;charset=UTF-8"%>  
	<!-- 	顶栏 -->
<div class="container">
	<div class="row ">
		<div class="col-md-12 column">
			<nav class="navbar navbar-default" role="navigation">
				<div class="navbar-header">
					 <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand" href="#">新学说后台</a>
				</div>
				
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li>
							 <a href="./count.jsp">概览</a>				 
						</li>
						<li>
							 <a href="./UserCheck.jsp">用户审核</a>				 
						</li>
					
						<li class="dropdown">
							 <a href="#" class="dropdown-toggle" data-toggle="dropdown">信息审核<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									 <a href="./SchoolCheck.jsp">学校 审核</a>
								</li>
								<li>
									<a href="./InstitutionCheck.jsp">机构 审核</a>
								</li>
																
							</ul>
						</li>
						
						<li class="dropdown">
							 <a href="#" class="dropdown-toggle" data-toggle="dropdown">邮件周刊<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									 <a href="./addMailUser.jsp">添加联系人</a>
								</li>
								<li>
									<a href="./Mailuser_list.jsp">联系人列表</a>
								</li>
								<li>
									<a href="./WeeklyMail.jsp">发送邮件内容</a>
								</li>													
							</ul>
						</li>
						
						<li class="dropdown">
							 <a href="#" class="dropdown-toggle" data-toggle="dropdown">菜单<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									 <a href="#">用户操作日志</a>
								</li>
								<li class="divider">
								</li>
								<li>
									<a href="#">系统运行警告</a>
								</li>
								<li>
									<a href="#">用户反馈</a>
								</li>						
								<li>
									<a href="#">员工操作日志</a>
								</li>							
								<li>
									<a href="#">功能05</a>
								</li>
							</ul>
						</li>
					</ul>			
					<ul class="nav navbar-nav navbar-right">
						<li>
							 <a href="http://data.xinxueshuo.cn" target="_blank">前台首页</a>
						</li>
						<li>
							 <a href="#">用户：</a>
						</li>
						<li class="dropdown">
							 <a href="#" class="dropdown-toggle" data-toggle="dropdown">菜单<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									 <a href="#">Action</a>
								</li>
								<li>
									 <a href="#">action</a>
								</li>
								<li>
									 <a href="#">Something</a>
								</li>
								<li class="divider">
								</li>
								<li>
									 <a href="#">link</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</nav>
		</div>
	</div>
</div>
