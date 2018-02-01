<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ include file="/WEB-INF/common/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" href="${ctx}/lib/bootstrap-3.3.7-dist/js/jquery.page.css">
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
				<div class="col-md-2">
					<div class="list-group">
						<a href="${ctx }/manager?method=findAll" class="list-group-item active">教务列表</a>
						<a href="${ctx }/manager?method=getSelect" class="list-group-item">选课</a>
					</div>
				</div>
				<div class="col-md-10">
				
				<!--在线列表开始  -->
				
				  <a href="${ctx}/user?method=getOnlinePage" class="button button-raised button-primary button-pill nav navbar-nav navbar-right">
				  当前在线:${onlineCount}人</a>
				 
				    
			  	<!--在线列表结束  -->
					<table class="table  table table-hover">
					      <thead>
					        <tr bgcolor="#4CAF50">
					          <th>学生姓名</th>
					          <th>年龄</th>
					          <th>性别</th>
					          <th>地址</th>
					          <th>班级</th>
					          <th>课程</th>
					          <th>学分</th>
					      </thead>
					      <tbody>
					        <tr class="active">
					     	<c:forEach items="${list}" var="m">
					          <td>${m['s_name']}</td>
						      <td>${m['age']}</td>
						      <td>${m['gender']}</td>
						      <td>${m['adress']}</td>
						      <td>${m['b_name']}</td>
						      <td>${m['c_name']}</td>
						      <td>${m['credit']}</td>
					        </tr>
					      	</c:forEach>
					      </tbody>
					    </table>
					    <!--分页开始  -->
					    	<nav aria-label="Page navigation">
							  <ul class="pagination">
							  <!--上一页开始  -->
							    <c:if test="${pageBean.pageIndex != 1}">
									<li>
										<a href="${ctx}/manager?method=pageManager&index=${pageBean.pageIndex-1}" aria-label="Previous"> 
											<span aria-hidden="true">&laquo;</span>
										</a>
									</li>
								</c:if>
								<c:if test="${pageBean.pageIndex==1}">
							        <li class="disabled">
							          <a href="javascript:void(0)" aria-label="Previous">
							            <span aria-hidden="true">&laquo;</span>
							          </a>
							        </li>
							    </c:if>
							   <!--上一页结束  -->
							    
							    <c:forEach begin="1" end="${pageBean.totalPage}" var="p">
							    	<c:if test="${pageBean.pageIndex == p}">
							    		<li class="active"><a href="${ctx}/manager?method=pageManager&index=${p}">${p}</a></li>
							    	</c:if>
							    	<c:if test="${pageBean.pageIndex != p}">
							    		<li ><a href="${ctx}/manager?method=pageManager&index=${p}">${p}</a></li>
							    	</c:if>
							   	</c:forEach>
							   	
							<!--下一页开始  -->
								<c:if test="${pageBean.pageIndex!=pageBean.totalPage}">
							    <li>
							      <a href="${ctx}/manager?method=pageManager&index=${pageBean.pageIndex+1}" aria-label="Next">
							        <span aria-hidden="true">&raquo;</span>
							      </a>
							    </li>
							    </c:if>
							    <c:if test="${pageBean.pageIndex==pageBean.totalPage}">
								 <li class="disabled">
							        <a href="javascript:void(0)" aria-label="Next">
							           <span aria-hidden="true">&raquo;</span>
							        </a>
							      </li>
								</c:if>
							  	</ul>
								</nav>
						<!--下一页结束  -->
						<!--分页结束  -->
				</div>
			</div>
		</div>
		<!--内容end-->
		<!--分页开始  -->
			<%-- <div id="page"></div>
				<script src="${ctx}/lib/bootstrap-3.3.7-dist/js/jquery.min.js"></script>
				<script type="text/javascript" src="${ctx}/lib/bootstrap-3.3.7-dist/js/jquery.page.js"></script>
				<script type="text/javascript">
					$(function() {
						$("#page").Page({
							totalPages: ${pageBean.totalPage}, //分页总数
							liNums: 3, //分页的数字按钮数(建议取奇数)
							activeClass: 'activP', //active 类样式定义
							callBack: function(page) {
								console.log(page)
							}
						});
					})
				</script> --%>
		
		
	</body>

	</html>