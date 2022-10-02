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
  <script type="text/javascript">
  		function memberInsertC(){
			const form 	= document.memberInsertCoupon;
			let myCouponList = ${myCouponList == null ? []:myCouponList};
			
  			if(!form.coupon_no.value){
  				alert('쿠폰번호를 입력하세요.');
  				return false;
  			} else if(myCouponList.length > 0) {
  				for(var i=0;i<myCouponList.length;i++){
  					if(myCouponList[i] == form.coupon_no.value){
  						alert('이미 등록된 쿠폰입니다.');
  						form.coupon_no.value ="";
  						return false;
  					}
  				}
  			} else {
  				alert('쿠폰 등록이 완료되었습니다.');
  				return true;
  			}
  		}
  </script>
    
    <%@ include file="./component/head.jsp" %>
    <div class="wrap">
    
      <div class="row d-flex" style="height: 1000px;">
		<%@ include file="./component/side.jsp" %>
        <div class="col-md-10 content">
        <!-- [[ 쿠폰페이지 몸통 시작 ]] -->
		  <div class="tab-pane" id="profile" role="tabpanel" aria-labelledby="profile-tab" tabindex="0">
		    <section class="bg-light d-flex justify-content-center">
			    <div class="container px-4 px-lg-5">
				    <div class="mt-3 d-flex justify-content-between">
				    	<h4 class="fw-bolder">🎫쿠폰 목록 보기</h4>
				    </div>
				    <hr>
				    <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
						<c:if test="${ !empty memberListCoupon }">
					    <table class="table">
						  <thead class="table" style="background: #50C2FF">
						    <tr>
						      <th class="col-1" scope="col">쿠폰번호</th>
						      <th class="col-2" scope="col">쿠폰명</th>
						      <th class="col-2" scope="col">유효기간(사용가능기간)</th>
						      <th class="col-6" scope="col">쿠폰내용</th>
						      <th class="col-1" scope="col">할인가격</th>
						    </tr>
						  </thead>
						  <tbody>
						  	<c:forEach var="couponList" items="${memberListCoupon}">
							  	<tr>
							      <td><div>${couponList.getCoupon_no()}</div></td>
							      <td><div>${couponList.getCoupon_name()}</div></td>
							      <td><div>${couponList.getCoupon_date()}</div></td>
							      <td><div>${couponList.getCoupon_text()}</div></td>
							      <td><div>${couponList.getCoupon_price()}</div></td>
							    </tr>
						  	</c:forEach>
						  </tbody>
						</table>
						</c:if>
						<c:if test="${ empty memberListCoupon }">
							<h5 class="m-4 w-100">쿠폰이 없습니다.</h5>
							<div style="height: 400px;"></div>
						</c:if>
					</div>
					<hr style="border: 0; height: 3px; background: black">
					<div class="mt-3 d-flex justify-content-between">
				    	<h4 class="fw-bolder">✖️쿠폰 등록하기</h4>
				    </div>
				    <hr>
				    <form action="./memberInsertCoupon" name="memberInsertCoupon" method="post" onsubmit="return memberInsertC()">
					    <div class="mb-3">
					    	<label class="mb-3">쿠폰 번호: </label>
					    	<div class="row">
					    		<div class="col-6">
						    	<input type="text" class="form-control" name="coupon_no" placeholder="쿠폰 번호 입력해주세요.">
						    	<input type="hidden" name="member_id" value="${mem_id}">
					    		</div>
					    		<div class="col-6">
						    	<button class="btn btn-secondary" type="submit">등록</button>
					    		</div>
					    	</div>
					    </div>
				    </form>
				</div>
			</section>
		  </div>
        <!-- [[ 쿠폰페이지 몸통  끝 ]] -->
        </div>
      </div>
      
    </div>
    
    <!-- footer start -->
	<%@ include file="../../../component/footer.jsp" %>
    <!-- footer end -->
  </body>
</html>