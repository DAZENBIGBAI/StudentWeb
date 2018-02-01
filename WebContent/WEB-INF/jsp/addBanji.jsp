<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@include file="/WEB-INF/common/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta charset="UTF-8">
	<title></title>
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
						<li class="active">
							<a href="${ctx }/banji?method=findAllBanji"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp;&nbsp;班级管理</a>
						</li>
						<li>
							<a href="#"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>&nbsp;&nbsp;课程管理</a>
						</li>
						<li>
							<a href="${ctx }/manager?method=pageManager"><span class="glyphicon glyphicon-tag" aria-hidden="true"></span>&nbsp;&nbsp;教务管理</a>
						</li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li>
							<a href="${ctx}/login?method=logout"><span>用户：${user.username}</span>&nbsp;&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-off" aria-hidden="true"></span>&nbsp;&nbsp;退出</a>
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
				<div class="col-md-2">
					<div class="list-group">
						<a href="${ctx }/banji?method=findAllBanji" class="list-group-item ">班级列表</a>
						<a href="#" class="list-group-item active">班级添加</a>
					</div>
				</div>
				<div class="col-md-10">
					<form class="form-horizontal"action="${ctx }/banji?method=addBanji" method="post">
					  <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">班级名称</label>
					    <div class="col-sm-10">
					      <input type="text" class="form-control" id="inputEmail3" placeholder="班级名称"name="name">
					    </div>
					  </div>
					  
					  <div class="form-group">
					    <div class="col-sm-offset-2 col-sm-10">
					      <button type="submit" class="btn btn-default">添加</button>
					    </div>
					  </div>
					</form>					
				</div>
			</div>
		</div>
		<!--内容end-->
	</body>

	</html>
