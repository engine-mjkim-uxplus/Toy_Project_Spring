<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	Integer result = (Integer) session.getAttribute("result");
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="member_id" value="${param.member_id}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta name="description" content="" />
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors" />
<meta name="generator" content="Hugo 0.101.0" />
<title>아이디 중복체크</title>
<%@ include file="../../common/common.jsp" %>
<style type="text/css">
.box {
	margin: -150px 0 0 -200px;
	position: absolute;
	text-align: center;
	top: 50%;
	left: 50%;
	width: 400px;
	height: 300px;
	border: 1px solid #999;
	border-radius: 1rem;
}

h3 {
	color: #888;
}
</style>
</head>
<body>
<script type="text/javascript">
	function useID(){
		let form = opener.document.userForm;
		if(document.idCheckForm.isID.value == "N"){ // 아이디가 중복일 때
			alert('중복된 아이디 입니다. 다시 입력해주세요');
			form.member_id.focus();
			
			window.close();
		} else { // 아이디가 없을 때
			form.idCheck.disabled = true;
			form.idDuplication.value = "idCheck";
			
			window.close();
		}
	}
</script>
	<div class="box p-3">
      <h3>아이디 중복검사</h3>
      <hr />
      <br /><br />
      <form name="idCheckForm">
	      <div class="input">
    	    <input type="text" class="form-control" id="member_id" name="member_id" value="${member_id}" disabled/>
    	    <br>
        	
        	<c:choose>
        	<c:when test="${result == 1}">
        		<p style="color: red">이미 존재하는 아이디 입니다.</p>
        		<input type="hidden" name="isID" value="N" />
        	</c:when>
        	<c:when test="${result == 0}">
        		<p style="color: blue">사용가능한 아이디입니다.</p>
        		<input type="hidden" name="isID" value="Y" />
        	</c:when>
        	</c:choose>
      	</div>
      </form>
      <input class="btn btn-outline-secondary" type="button" onclick="window.close()" value="취소" >
      <input class="btn btn-outline-secondary" type="button" onclick="useID()" value="사용하기" >
    </div>
</body>
</html>