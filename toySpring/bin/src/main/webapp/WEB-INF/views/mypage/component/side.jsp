<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<c:set var="URL" value="${pageContext.request.requestURI}" />
<c:set var="orderpage" value="/WEB-INF/views/mypage/orderpage.jsp" />
<c:set var="reviewpage" value="/WEB-INF/views/mypage/reviewpage.jsp" />
<c:set var="personal" value="/WEB-INF/views/mypage/personalpage.jsp" />
<c:set var="couponpage" value="/WEB-INF/views/mypage/couponpage.jsp" />
<c:set var="likepage" value="/WEB-INF/views/mypage/likepage.jsp" />

<div class="col-md-2 listContainer list-group border-end border-secondary">
  <a href="./memberListPayment.do" class="item list-group-item ${URL eq orderpage ? 'active' : 'none' }">
   <i class="far fa-comment-dots" style="margin-right: 5px;"></i>구매 내역
  </a>
  <a href="./memberListReview.do" class="item list-group-item ${URL eq reviewpage ? 'active' : 'none' }">
   <i class="far fa-comment-dots" style="margin-right: 5px;"></i>구매 후기
  </a>
  <a href="./memberListP.do" class="item list-group-item ${URL eq personal ? 'active' : 'none' }">
     <i class="far fa-comment-dots" style="margin-right: 5px;"></i>개인 정보 수정
  </a>
  <a href="./memberListCoupon.do" class="item list-group-item ${URL eq couponpage ? 'active' : 'none' }">
   <i class="far fa-comment-dots" style="margin-right: 5px;"></i>보유 쿠폰
  </a>
  <a href="./memberListLike.do" class="item list-group-item ${URL eq likepage ? 'active' : 'none' }">
   <i class="far fa-comment-dots" style="margin-right: 5px;"></i>좋아요 리스트
  </a>
</div>