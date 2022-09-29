<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
	<%@ include file="../../../common/common.jsp" %>
    <style>
    	body {
		  padding: 0;
		  margin: 0;
		}
		div {
		  box-sizing: border-box;
		}
		
		/* alert badge */
		.circle {
		  display: inline-block;
		  width: 5px;
		  height: 5px;
		  border-radius: 2.5px;
		  background-color: #ff0000;
		  position: absolute;
		  top: -5px;
		  left: 110%;
		}
		
		/* 녹색 텍스트 */
		.green {
		  color: #24855b;
		}
		
		.wrap {
		  background-color: #f8f8f8;
		}
		/* 녹색배경 */
		.greenContainer {
		  height: 132px;
/* 		  background-color: #aaa; */
		
		  display: flex;
		  align-items: flex-end;
		  padding: 16px;
		}
		
		.greenContainer .name {
		  font-size: 20px;
		  font-weight: bold;
		  color: #ffffff;
		}
		.greenContainer .modify {
		  margin-left: auto;
		}
		
		/* 단골상점 , 상품후기 , 적립금 박스 */
		.summaryContainer {
		  background-color: white;
		  display: flex;
		  padding: 21px 16px;
		  height: 90px;
		  justify-content: space-around;
/* 		  margin-bottom: 10px; */
		}
		/* 단골상점 , 상품후기 , 적립금 */
		/* .summaryContainer .item {
		  flex-grow: 1;
		} */
		/* 녹색 숫자 */
		.summaryContainer .number {
		  font-size: 19px;
		  font-weight: bold;
		  color: black;
		}
		/* 텍스트 */
		.summaryContainer .item > div:nth-child(2) {
		  font-size: 13px;
		}
		
		/*=================== 주문목록 ~ 찜한상품 리스트 ==================*/
		.listContainer {
		  padding: 0;
		  background-color: #ffffff;
		  /* margin-bottom: 10px; */
		}
		.listContainer .item {
		  display: flex;
		  align-items: center;
		  /* padding: 16px; */
		  color: black;
		  text-decoration: none;
		  /* height: 50px; */
		  box-sizing: border-box;
		}
		
		/*=================== 내지갑의 보유 적립금 들어가는 부분 ================*/
		
		.listContainer .right .blct {
		  font-size: 14px;
		  font-weight: bold;
		  margin-right: 5px;
		}
		
		/* 공지사항 이용안내 고객센터 */
		.infoContainer {
		  background-color: white;
		  display: flex;
		  /* height: 100px; */
		  /* margin-bottom: 10px; */
		}
		
		.infoContainer .item {
		  flex-grow: 1;
		  display: flex;
		  align-items: center;
		  justify-content: center;
		  flex-direction: column;
		  font-size: 13px;
		  text-decoration: none;
		  color: black;
		}
		.infoContainer .item > div:first-child {
		  /* margin-bottom: 2px; */
		}
		
		/*  */
		.listContainer .item:hover {
		  /*   background-color: #f8f8f8; */
		}
		.infoContainer .item:hover {
		  /*   background-color: #f8f8f8; */
		}
		
		/* [[최근 구매내역을 마이페이지의 메인에서 보여주기]] */
		.content {
		  background-color: #ddd;
		}
    </style>
  </head>
  <body>
    <!-- nav start -->
	<%@ include file="../../../component/nav.jsp" %>
    <!-- nav end -->
    
    <%@ include file="./component/head.jsp" %>
    <div class="wrap">
    
      <div class="row d-flex" style="height: 1000px;">
		<%@ include file="./component/side.jsp" %>
        <div class="col-md-10 content">
        <!-- [[ 리뷰내역페이지 몸통 시작 ]] -->
        <!-- review start-->
	  <div class="tab-pane" id="profile" role="tabpanel" aria-labelledby="profile-tab" tabindex="0">
	    <section class="bg-light d-flex justify-content-center">
		    <div class="container px-4 px-lg-5">
			    <div class="mt-3 d-flex justify-content-between">
			    	<h4 class="fw-bolder">✏️내가 쓴 상품 후기</h4>
			    </div>
			    <hr>
			    <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
					<c:if test="${ !empty memberListReview }">
				    <table class="table">
					  <thead class="table" style="background: #50C2FF">
					    <tr>
					      <th class="col-1" scope="col">구매상품</th>
					      <th class="col-1" scope="col">상품명</th>
					      <th class="col-1" scope="col">작성일</th>
					      <th class="col-1" scope="col">작성자</th>
					      <th class="col-2" scope="col">별점</th>
					      <th class="col-2" scope="col">첨부사진</th>
					      <th class="col-4" scope="col">후기</th>
					    </tr>
					  </thead>
					  <tbody>
					  	<c:forEach var="reviewList" items="${memberListReview}">
						  	<tr>
						      <td scope="row"><div>
								<img style="width: 100px; height: 100px;" src="${reviewList.PRODUCT_IMG}"/>
						      </div></td>
						      <td><div>${reviewList.PRODUCT_NAME}</div></td>
						      <td><div>${reviewList.REVIEW_DATE}</div></td>
						      <td><div>${reviewList.MEMBER_ID}</div></td>
						      <td>
						      	<c:forEach begin="1" end="${reviewList.REVIEW_SCORE}">
						      		<i class="fas fa-star" style="color: orange;"></i>
							  	</c:forEach>
						      </td>
						      <c:if test="${ !empty reviewList.REVIEW_IMG }">
							      <td>
							      	<a target='_blank' href="http://localhost:8080/reviewimg/${reviewList.REVIEW_IMG}">
							      		<img style="width: 100px; height: 100px;" src="../../../reviewimg/${reviewList.REVIEW_IMG}"/>
							      	</a>
							      </td>
						      </c:if>
						      <c:if test="${ empty reviewList.REVIEW_IMG }">
							      <td>
							      	<img style="width: 100px; height: 100px;" src="../../../reviewimg/null.jpg"/>
							      </td>
						      </c:if>
						      <td><div>${reviewList.REVIEW_CONTENT}</div></td>
						    </tr>
					  	</c:forEach>
					  </tbody>
					</table>
					</c:if>
					<c:if test="${ empty memberListReview }">
						<h5 class="m-5 w-100">구매 후기가 없습니다.</h5>
						<div style="height: 400px;"></div>
					</c:if>
				</div>
			</div>
		</section>
	  </div>
	  <!-- review end-->
        <!-- [[ 리뷰내역페이지 몸통  끝 ]] -->
        </div>
      </div>
      
    </div>
    
    <!-- footer start -->
	<%@ include file="../../../component/footer.jsp" %>
    <!-- footer end -->
  </body>
</html>