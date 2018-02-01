<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@include file="/WEB-INF/common/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta charset="UTF-8">
		<title>选课页面</title>
		<link rel="stylesheet" href="${ctx }/lib/buttons.css" /> 
		<script type="text/javascript" src="${ctx}/lib/jquery-1.11.1.js"></script>
		<script type="text/javascript">
			$(function(){
				<!-- $.post(url, [data], [callback], [type]) -->
			 	$.post(
					"${ctx}/findAllBanji",
					
					function(data){
						var html="<option>--请选择班级--</option>";
						for(var i=0;i<data.length;i++){
							html +="<option value="+data[i].id+">"+data[i].name+"</option>"
						}
						$("#banji").html(html);
					},
					"json"
				); 
			});
			function selectCourse(){
				/*  $("#course option:gt(0)").remove(); */
				var banjiId = $("#banji").val();
				$.post(
					"${ctx}/findBC",
					{"banji_id":banjiId},
					
					function(data){
						var text="";
						for(var i=0;i<data.length;i++){
							text +="<option value="+data[i].id+">"+data[i].name+"</option>"
						}
						$("#course").html(text);
					},
					"json"
				);
			};
			
			
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
							<a href="#"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;&nbsp;学生管理<span class="sr-only">(current)</span></a>
						</li>
						<li>
							<a href="#"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp;&nbsp;班级管理</a>
						</li>
						<li>
							<a href="#"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>&nbsp;&nbsp;课程管理</a>
						</li>
						<li class="active">
							<a href="${ctx }/manager?method=pageManager"><span class="glyphicon glyphicon-tag" aria-hidden="true"></span>&nbsp;&nbsp;教务管理</a>
						</li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li>
							<a href="${ctx}/login?method=logout"><span class="glyphicon glyphicon-off" aria-hidden="true"></span>&nbsp;&nbsp;退出</a>
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
						<a href="${ctx }/manager?method=pageManager" class="list-group-item ">教务列表</a>
						<a href="#" class="list-group-item active">学生选课</a>
					</div>
				</div>
				<div class="col-md-10">
					<table class="table">
					      <thead>
					        <tr>
					          <th>学生信息：</th>
					          <th>姓名：</th>
					          <th>班级：</th>
					          <th>地址：</th>
					        </tr>
					      </thead>
					  </table>
				</div>
			  <form action="${ctx}/manager?method=addcourse" method="post">
				<div class="col-md-2">
			        <label  class="control-label">班级:</label>
				    <select name="bid" class="form-control" id="banji" onchange="selectCourse()"></select>
				</div>
				
				<div class="col-md-2">	      
					<label  class="control-label">课程:</label>
				    <select name = "cid" class="form-control" id="course">
				    <option>--请选择课程--</option>
				    </select>
				    
				</div>
				<div class="col-sm-offset-2 col-sm-5">	
					<br>
					<button type="submit" class="button button-glow button-border button-rounded button-primary ">
					 选课</button>
				</div>
			  </form>
			</div>
		</div>
		<!--内容end-->
	</body>

	</html>
