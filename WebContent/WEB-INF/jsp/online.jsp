<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@include file="/WEB-INF/common/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" type="text/css" href="${ctx }/lib/bootstrap-3.3.7-dist/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${ctx }/lib/buttons.css" />
	<script type="text/javascript">
		function logout(){
			var is = confirm("确认要退出吗？");
			if(is){
				location.href="${ctx}/login?method=logout";
			}
		}
	
	</script>
</head>

	<body>
		<!--导航begin-->
		<nav class="navbar navbar-default">
			<div class="container">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		      </button>
					<a class="navbar-brand" href="#">教务管理系统</a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li >
							<a href="${ctx }/student?method=pageStudent"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;&nbsp;学生管理<span class="sr-only">(current)</span></a>
						</li>
						<li >
							<a href="${ctx }/banji?method=findAllBanji"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp;&nbsp;班级管理</a>
						</li>
						<li>
							<a href="#"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>&nbsp;&nbsp;课程管理</a>
						</li>
						<li class="active">
							<a href="#"><span class="glyphicon glyphicon-tag" aria-hidden="true"></span>&nbsp;&nbsp;教务管理</a>
						</li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li>
							<a href="javascript:logout()"><span>用户：${user.username}</span>&nbsp;&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-off" aria-hidden="true"></span>&nbsp;&nbsp;退出</a>
						</li>
					</ul>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>
		<!--导航end-->
		<!--内容begin-->
		<div class="container">
			<div class="row">
				
				<div class="col-md-11">
					<div class="bs-example" data-example-id="bordered-table">
					    <table class="table table-bordered">
					      <thead>
					        <tr>
					          <th>编号</th>
					          <th>用户名</th>
					          <th>密码</th>
					        </tr>
					      </thead>
					      
					      	<c:forEach items="${ onlineList}" var="user">
					        	<tr>
					          	 <td>${user.id}</td> 
					          	<td>${user.username}</td>
								<td>***</td>
					        	</tr>
					     	</c:forEach>
					     <%-- 	<tr>
					     		<td>${user.id}</td>
					     		<td>${user.username}</td>
					     		<td>${user.password}</td>
					     	</tr> --%>
					      
					    </table>
					  </div>    
				
					
				</div>
			</div>
		</div>
		<!--内容end-->
	</body>
</html>