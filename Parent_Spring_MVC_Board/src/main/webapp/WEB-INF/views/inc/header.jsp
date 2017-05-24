<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1 style="float: left;width: 150px;"><a href="/ShotPlace"><img src="/ShotPlace/resources/images/ci.gif" alt="java-school logo" /></a></h1>
 
<div id="memberMenu" style="float: right;position: relative;top: 7px;">
<c:choose>
    <c:when test="${empty check}">
        <input type="button" value="로그인" onclick="location.href='/ShotPlace/users/login'" />
		<input type="button" value="회원가입" onclick="location.href='/ShotPlace/users/signUp'" />
    </c:when>
    <c:otherwise>
        <input type="button" value="로그아웃" onclick="location.href='/ShotPlace/users/logout'" />
        <input type="button" value="내정보수정" onclick="location.href='/ShotPlace/users/editAccount'" />
    </c:otherwise>
</c:choose>
</div>
</body>
</html>