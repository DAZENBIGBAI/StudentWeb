<%@page import="com.situ.student.entity.Student"%>
<%@page import="java.util.List"%>
<%@page import="com.situ.student.dao.impl.StudentDaoImpl"%>
<%@page import="com.situ.student.dao.IStudentDao"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

 <%@include file="/WEB-INF/common/base.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta charset="UTF-8">
	<title>学生列表</title>
	<script type="text/javascript" src="${ctx}/lib/jquery-1.11.1.js"></script>
	<script type="text/javascript">
		function deleteById(id){
			var is = confirm("确认要删除么？");
			if(is){
				location.href="${ctx}/student?method=delete&id="+id;
			}
		}
		//退出
		function logout(){
			var is = confirm("确认要退出吗？");
			if(is){
				location.href="${ctx}/login?method=logout"
			}
		}
	
		function selectAll() {
			$("input[name=selectIds]").prop("checked", $("#selectAlls").is(":checked"));
		}
		function deleteAll(){
			var is = confirm("确认要删除么？");
			if(is){
				$("#mainForm").attr("action","${ctx}/student?method=deleteAll");
				$("#mainForm").submit();
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
						<li class="active">
							<a href="#"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;&nbsp;学生管理<span class="sr-only">(current)</span></a>
						</li>
						<li>
							<a href="${ctx }/banji?method=findAllBanji"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp;&nbsp;班级管理</a>
						</li>
						<li>
							<a href="#"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>&nbsp;&nbsp;课程管理</a>
						</li>
						<li>
							<a href="${ctx }/manager?method=pageManager"><span class="glyphicon glyphicon-tag" aria-hidden="true"></span>&nbsp;&nbsp;教务管理</a>
						</li>
					</ul>
					<!-- 显示用户名字 -->	
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
						<a href="#" class="list-group-item active">学生列表</a>
						<a href="${ctx }/student?method=getAddStudent" class="list-group-item">学生添加</a>
					</div>
				</div>
				<div class="col-md-10 ">
				<!-- 搜索表单begin -->
					<form action="${ctx}/student?method=findById">
					编号<input type="text" name="id">
					姓名<input type="text" name ="name"> &nbsp;&nbsp;&nbsp;&nbsp;
					<button type="submit" class="btn btn-primary">搜索</button>
					</form>
				<!-- 搜索表单end -->
				
				<form action="" id="mainForm" method="post">
					<table class="table">
					      <thead>
					        <tr bgcolor="#4CAF50">
						         <button class="btn btn-danger" onclick="deleteAll()">批量删除</button>
					           <th>
						         <input type="checkbox" id="selectAlls" onclick="selectAll()"/>
						      </th>
					          <th>编号</th>
					          <th>姓名</th>
					          <th>年龄</th>
					          <th>性别</th>
					          <th>地址</th>
					          <th>删除</th>
					          <th>修改</th>
					        </tr>
					      </thead>
					    
					      <c:forEach items="${pageList}" var="s">
					      <tbody>
					        <tr class="active">
					         <td>
						     	<input type="checkbox" name="selectIds" value="${s.id}"/>
						     </td>
					         <td>${s.id} </td>
					         <td>${s.name}</td>
					         <td>${s.age}</td>
					         <td>${s.gender }</td>
					         <td>${s.adress}</td>
					         <td><a href = "javascript:deleteById(${s.id})" class="btn btn-danger">删除</a></td>
					         <td><a href ="${ctx}/student?method=findById&id=${s.id}" class="btn btn-primary">修改</a></td>
					        </tr>
					      </tbody>
					      </c:forEach>
					      
					   </table>
				</form>
						<!--分页begin  -->
							<nav aria-label="Page navigation">
							<ul class="pagination">
						<!--上一页 begin  -->
								<c:if test="${pageBean.pageIndex != 1}">
									<li>
										<a href="${ctx}/student?method=pageStudent&index=${pageBean.pageIndex-1}" aria-label="Previous"> 
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
							<!--上一页 end -->
								<c:forEach begin="1" end="${pageBean.totalPage}" var="p">
									<c:if test="${pageBean.pageIndex == p}">
										<li class="active"><a href="${ctx}/student?method=pageStudent&index=${p}">${p}</a></li>
									</c:if>
									<c:if test="${pageBean.pageIndex != p }">
										<li ><a href="${ctx}/student?method=pageStudent&index=${p}">${p}</a></li>
									</c:if>
								</c:forEach>
								<!--下一页begin  -->
								 <c:if test="${pageBean.pageIndex != pageBean.totalPage}">
									<li>
										<a href="${ctx}/student?method=pageStudent&index=${pageBean.pageIndex+1}" aria-label="Next">
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
								<!--下一页 end  -->	
							</ul>
							</nav>
						<!--分页end-->
				</div>
			</div>
		</div>
		<!--内容end-->
	
	</body>

	</html>
