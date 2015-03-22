<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${pageContext.request.contextPath}/cms/js/jquery-1.5.2.min.js" type="text/javascript"></script>
<title>welcome</title>
<style>
.l-div-welcome{margin-top:10px}
</style>
</head>

	
<body>
	<div class="l-div-welcome" >欢迎进入系统管理,请谨慎操作！！</div>
	<div class="l-div-welcome" >${admin.admUsername },<span id="labelwelcome"></span></div>
	<div class="l-div-welcome" >您上次登录时间：<s:date name="#session.lastLoginTime" format="yyyy-MM-dd HH:mm:ss"></s:date></div>
	<div class="l-div-welcome" >您的登录次数：${admin.admLoginNumbers }</div>
</body>
<script type="text/javascript">
	 var now = new Date(), hour = now.getHours();
     if (hour > 4 && hour < 6) { $("#labelwelcome").html("凌晨好！") }
     else if (hour < 9) { $("#labelwelcome").html("早上好！") }
     else if (hour < 12) { $("#labelwelcome").html("上午好！") }
     else if (hour < 14) { $("#labelwelcome").html("中午好！") }
     else if (hour < 17) { $("#labelwelcome").html("下午好！") }
     else if (hour < 19) { $("#labelwelcome").html("傍晚好！") }
     else if (hour < 22) { $("#labelwelcome").html("晚上好！") }
     else { $("#labelwelcome").html("夜深了，注意休息！") }
</script>
</html>
