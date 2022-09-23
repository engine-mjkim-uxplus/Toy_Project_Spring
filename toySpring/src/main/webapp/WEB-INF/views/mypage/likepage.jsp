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
		 @media (max-width: 767px) {
        .card {
          margin: 3vh auto;
        }
      	}
      	.cart {
	        background-color: #fff;
	        padding: 4vh 5vh;
	        border-bottom-left-radius: 1rem;
	        border-top-left-radius: 1rem;
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
        	<div class="card">
      			<div class="row">
        			<div class="col-md-12 cart">
          				<div class="title">
           					 <div class="row">
             					 <div class="col">
                				<h4><b>❤️좋아요 누른 상품 목록</b></h4>
              					</div>
            				</div>
          				</div>
				<!-- 좋아요 누른 상품 시작 -->
				  <c:choose>
		          	<c:when test="${memberListLike != null && memberListLike.size() > 0}">
			          <c:forEach var="like" items="${memberListLike}">
				          <section class="like_table" style="width: 100%; height: 150px;">
				            <div class="row">
				              <div class="row main align-items-center">
				                <div class="col-2">
				                  <img class="p-3 product_img" style="width: 50%; height: 50%;'" src="${like.getProduct_img()}" />
				                </div>
				                <div class="col-5">
				                  <div class="row text-muted product_category">${like.getProduct_category()}</div>
				                  <div class="row product-name">${like.getProduct_name()}</div>
				                </div>
				                <div class="col-4 product-price">${like.getProduct_price()}</div>
				              </div>
				            </div>
				            <hr/>
				            </section>
						</c:forEach>
					</c:when>
				  </c:choose>
				<!-- 좋아요 누른 상품 끝 -->
          			</div>
          		</div>
	        </div>
		</div>
      </div>
      
    </div>
    
    <!-- footer start -->
	<%@ include file="../../../component/footer.jsp" %>
    <!-- footer end -->
  </body>
</html>