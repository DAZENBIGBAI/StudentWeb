<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@include file="/WEB-INF/common/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
		<script type="text/javascript" src="${ctx}/lib/jquery-1.11.1.js"></script>
		<script type="text/javascript">
			function c(){
				$("#codeImage").attr("src","${ctx}/checkImage?"+Math.random());
			}
		</script>
</head>
<body>
	<img src="${ctx }/checkImage" id="codeImage" border="0" onclick="c()">
</body>
</html>