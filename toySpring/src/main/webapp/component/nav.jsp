<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- nav start -->
<c:set var="loginOutLink" value="${sessionScope.mem_id == null ? '/login/loginForm' : '/login/logout' }"/>
<c:set var="loginOut" value="${sessionScope.mem_id == null ? '로그인' : '로그아웃' }"/>
<c:set var="orderLink" value="${sessionScope.mem_id == null ?'javascript:pageMove()' :'/member/memberListPayment' }"/>
<nav
  class="d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-bottom border-secondary"
>
  <ul class="nav col-md-4 justify-content-start">
    <li class="nav-item">
      <a href="#" class="nav-link px-2 text-muted">응모</a>
    </li>
    <li class="nav-item">
      <a href="#" class="nav-link px-2 text-muted">매장안내</a>
    </li>
  </ul>

  <a
    href="/product/productListㅇ"
    class="col-md-4 d-flex align-items-center justify-content-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none"
  >
    <h1>WORKSOUT</h1>
  </a>

  <ul class="nav col-md-4 justify-content-end">
    <li class="nav-item">
      <button class="nav-link px-2 text-muted border-0" data-bs-toggle="modal" data-bs-target="#exampleModal">
      	<i class="fa fa-search text-muted "></i> 검색
      </button>
    </li>
    <li class="nav-item">
      <a href="/cart/cartList" class="nav-link px-2 text-muted">장바구니</a>
    </li>
    <li class="nav-item">
      <button class="nav-link px-2 text-muted border-0" data-bs-toggle="modal" data-bs-target="#orderModal">주문내역</button>
    </li>
    <li class="nav-item">
      <a href="/member/memberListPayment" class="nav-link px-2 text-muted">마이페이지</a>
    </li>
    <li class="nav-item">
      <a href="${loginOutLink}" class="nav-link px-2 text-muted">${loginOut}</a>
    </li>
  </ul>
</nav>
<!-- nav end -->

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content border border-secondary rounded-3 border-opacity-50">
      <div class="modal-body">
       <form class="d-flex" role="search" action="productSearch" method="post">
          <input name="search" type="search" class="form-control" placeholder="검색어를 입력하세요" aria-label="Search">
          <button type="submit" class="btn btn-warning" style="margin-left: 5px"><i class="fas fa-search"></i></button>
        </form>
      </div>
    </div>
  </div>
</div>
<!-- Modal -->



<!-- Modal -->
<div class="modal fade" id="orderModal" aria-hidden="true" aria-labelledby="exampleModalToggleLabel" tabindex="-1">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
    <div class="modal-header">
		<div>주문내역 확인하기</div>
		<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
    </div>
      <div class="modal-body">
	 	<main class="d-flex justify-content-center align-items-center" style="height: 200px">
	 		<a href="${orderLink}" type="button" class="btn btn-primary btn-lg me-5">회원 주문내역</a>
	 		<a href="/order/orderUnmemberPage" type="button" class="btn btn-danger btn-lg">비회원 주문내역</a>
	 	</main>
      </div>
      <div class="modal-footer">
      </div>
    </div>
  </div>
</div>
<!-- Modal -->

<script>
	function pageMove(){
		alert('로그인이 필요합니다.');
		location.href='/login/loginForm';
	}
</script>