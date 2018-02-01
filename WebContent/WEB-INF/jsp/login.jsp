<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@include file="/WEB-INF/common/base.jsp" %>
<html>
	

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		<title>登录页面</title>
		<link rel="stylesheet" href="${ctx }/lib/style.css">
		<script type="text/javascript" src="${ctx}/lib/jquery-1.11.1.js"></script>
		<%-- <script src="${ctx }/lib/modernizr-1.7.min.js"></script> --%>
		<script type="text/javascript">
			function c(){
				$("#codeImage").attr("src","${ctx}/checkImage?"+Math.random());
			}
		</script>
	

	</head>

	<body>

		<div id="container">
			<div id="main" role="main">
				<form method="post" action="${ctx }/login?method=login">
					
 
						<label for="username">用户名 
							<%-- <span class="ico"><img src="${ctx }/img/user.png" alt="Username Icon" border="0"></span> --%>
						</label>

						<input type="text" name="user" id="user" required="" autofocus="">

						<label for="password">密码
<%-- 						 <span class="ico"><img src="${ctx }/img/pass.png" alt="Password Icon" border="0"></span>
 --%>					</label>

						<input type="password" name="password" id="password" required="">
						
						<label >验证码</label>
						<input type="text" name="checkCode" required="" >
						<img src="${ctx }/checkImage" id="codeImage" border="0" onclick="c()">
						<button type="submit">&gt;&gt;&nbsp;&nbsp;GO</button>
					
				</form>

			</div>

			<footer>

			</footer>

		</div>
		<!--! end of #container -->
	</body>

</html>