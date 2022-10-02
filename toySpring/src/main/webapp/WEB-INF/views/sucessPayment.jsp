<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.URLDecoder" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../../common/common.jsp" %>
    <style>
      @import url("https://fonts.googleapis.com/css2?family=Montserrat&display=swap");
    </style>
</head>
<style>
	.body{
	 box-sizing: border-box;
	}
	.btn{
		margin-top:200px;
		width: 20rem;
		height: 50px;
	}
	
	.specification{
		padding: 2rem;
		margin-top: 10rem;
		height:30rem;
		width: 60rem;
		background-color: #ddd;
	}
	
	footer{
		margin-top: 280px;
	}
</style>
<body>
<!-- nav -->
<%@ include file="../../component/nav.jsp" %>
<section class="specification container-fluid">
	<div>  	
		<h2 class="text-center mt-5">${URLDecoder.decode(param.name)} <b>님의 주문이 완료 되었습니다</b></h2>
		<h4 class="text-center mt-5"><i class="fas fa-truck fs-1"></i><b> 주문 번호 : </b> ${param.orderNumber}</h4>
	</div>
	<div class="text-center">
		<!-- 비회원인 경우 -->
		<c:if test="${ empty mem_id}">
			<a class="btn btn-danger col-6" href="/order/orderUnmemberPage?order_number=${orderNumber}">주문내역 조회</a>
		</c:if>
		
		<!-- 회원인 경우 -->
		<c:if test="${ !empty mem_id}">
			<a class="btn btn-danger col-6" href="/member/memberListPayment">주문내역 조회</a>
		</c:if>
		<button class="btn btn-secondary col-6" onClick="location.href='/product/productList';">메인으로</button> 
	</div>
</section>
<!-- footer -->
<%@ include file="../../component/footer.jsp" %>
</body>
</html>