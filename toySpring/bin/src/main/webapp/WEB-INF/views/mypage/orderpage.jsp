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
    	table {
	      width: 100%;
	      border-top: 1px solid #444444;
	      border-collapse: collapse;
	      text-align: center;
	    }
	    th, td {
	      border-bottom: 1px solid #444444;
	      padding: 10px;
	      vertical-align: middle;
	    }
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
		  
		}
    </style>
  </head>
  <body>

    <!-- nav start -->
	<%@ include file="../../../component/nav.jsp" %>
    <!-- nav end -->
    
    <%@ include file="./component/head.jsp" %>
    <div class="wrap">
      <div class="row d-flex">
		<%@ include file="./component/side.jsp" %>
        <div class="col-md-10 content">
			
			<c:forEach items="${orderList}" var="item">
				<c:if test="${ item.get('BUYSTATE') eq '주문완료'}">
					<c:set var="ing_count" value="${ing_count + 1}" />
				</c:if>
				<c:if test="${ item.get('BUYSTATE') eq '구매확정' }">
					<c:set var="complete_count" value="${complete_count + 1}" />
				</c:if>
				<c:if test="${ item.get('BUYSTATE') eq '교환' || item.get('BUYSTATE') eq '반품'}">
					<c:set var="cancel_count" value="${cancel_count + 1}" />
				</c:if>
			</c:forEach>
			
			<ol class="list-group d-flex flex-row justify-content-center mt-5">
			  <li class="list-group-item d-flex justify-content-center align-items-start col-3">
			    <div class="d-flex justify-content-center align-items-center flex-column">
			      <div class="fw-bold border border-secondary rounded-circle d-flex justify-content-center align-items-center mb-2" style="width: 100px; height: 100px;">
			      	<i class="fas fa-truck fs-1"></i>
			      </div>
			      <div class="fs-5 mb-2">배송중</div>
			      <div id="state_ing" class="badge bg-primary rounded-pill fs-5">${ empty ing_count ?0 :ing_count }</div>
			    </div>
			  </li>
			  <li class="list-group-item d-flex justify-content-center align-items-start col-3">
			    <div class="d-flex justify-content-center align-items-center flex-column">
			      <div class="fw-bold border border-secondary rounded-circle d-flex justify-content-center align-items-center mb-2" style="width: 100px; height: 100px;">
			      	<i class="fas fa-box fs-1"></i>
			      </div>
			      <div class="fs-5 mb-2">배송완료</div>
			      <div id="state_complete" class="badge bg-primary rounded-pill fs-5">${ empty complete_count ?0 :complete_count }</div>
			    </div>
			  </li>
			  <li class="list-group-item d-flex justify-content-center align-items-start col-3">
			    <div class="d-flex justify-content-center align-items-center flex-column">
			      <div class="fw-bold border border-secondary rounded-circle d-flex justify-content-center align-items-center mb-2" style="width: 100px; height: 100px;">
			      	<i class="fas fa-undo-alt fs-1"></i>
			      </div>
			      <div class="fs-5 mb-2">취소/반품/교환</div>
			      <div id="state_trade" class="badge bg-primary rounded-pill fs-5">${ empty cancel_count ?0 :cancel_count }</div>
			    </div>
			  </li>
			</ol>

			<jsp:useBean id="time" class="com.vo.DateVO" />
			<section class="w-100 bg-light d-flex flex-column mt-5 mb-5 align-items-center">
				<div class="input-group mb-3 w-50">
				  <a href="./memberListPayment.do?date=<jsp:getProperty name="time" property="threeMonthsAgo"/>" class="btn btn-outline-secondary" type="button">최근 3개월</a>
				  <a href="./memberListPayment.do?date=<jsp:getProperty name="time" property="aMonthAgo"/>" class="btn btn-outline-secondary" type="button">최근 1개월</a>
				  <a href="./memberListPayment.do?date=<jsp:getProperty name="time" property="aWeekAgo"/>" class="btn btn-outline-secondary" type="button">최근 1주일</a>
				  <input id="searchDate" type="date" class="form-control" max="<jsp:getProperty name="time" property="today"/>">
				  <button class="btn btn-outline-secondary" type="button" onclick='searchDate()'>검색</button>
				</div>
			    <div class="container px-4 px-lg-5">
				    <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
					    <table class="table">
						  <thead class="table-dark">
						    <tr>
						      <th class="col-1" scope="col">구매날짜</th>
						      <th class="col-1" scope="col">주문번호</th>
						      <th class="col-4" scope="col">상품정보</th>
						      <th class="col-1" scope="col">수량</th>
						      <th class="col-1" scope="col">가격</th>
						      <th class="col-1" scope="col">배송정보</th>
						      <th class="col-1" scope="col"></th>
						    </tr>
						  </thead>
						  <tbody id="tbody">
							    <c:forEach var="item" items="${orderList}">
								  	<tr>
								      <td scope="row">${item.get('ORDER_DATE')}</td>
								      <td>${item.get('ORDER_NUMBER')}</td>
									  <td>
									  	<img src="${item.get('PRODUCT_IMG')}" style="width: 100px; height: auto;">
									  	<span class="fs-5">${item.get('PRODUCT_NAME')}</span>
									  </td>
								      <td>${item.get('ORDER_COUNT')}</td>
								      <td>${item.get('ORDER_PRICE')}</td>
								      <td>
								      	<button 
								      	class="btn btn-primary mb-2"
								      	data-bs-toggle="modal" data-bs-target="#locationModal"
								      	data-address="${item.get('ORDER_NAME')},${item.get('ORDER_ADDRESS')},${item.get('ORDER_ADDRESS2')},${item.get('ORDER_ZIPCODE')},${item.get('ORDER_PHONE')},${item.get('ORDER_MEMO')}">
								      		배송정보
								      	</button>
								      </td>
								      <td class="">
								      <!-- default:주문완료 -> 구매확정/반품/교환 --> 
								      	<c:if test="${item.get('BUYSTATE') eq '주문완료' }">
									      	<button type="button" class="btn btn-primary mb-2" onclick="buystate()" data-bs-toggle="modal" data-bs-target="#StateModal" 
									      	data-state="buy" 
									      	data-ordernumber="${ item.get('ORDER_NUMBER')}" 
									      	data-productno="${ item.get('PRODUCT_NO')}" 
									      	data-price="${item.get('ORDER_PRICE')}"
									      	>구매확정</button><br/>
									      	
									      	<button type="button" class="btn btn-primary mb-2" onclick="buystate()" data-bs-toggle="modal" data-bs-target="#StateModal"
									      	data-state="exchange"
									      	data-ordernumber="${ item.get('ORDER_NUMBER')}" 
									      	data-productno="${ item.get('PRODUCT_NO')}" 
									      	>교환요청</button><br/>
									      	
									      	<button type="button" class="btn btn-primary" onclick="buystate()" data-bs-toggle="modal" data-bs-target="#StateModal"
									      	data-state="return"
									      	data-ordernumber="${ item.get('ORDER_NUMBER')}" 
									      	data-productno="${ item.get('PRODUCT_NO')}" 
									      	>반품요청</button>
								      	</c:if>
								      	<c:if test="${item.get('BUYSTATE') eq '구매확정' }">
											<div>구매확정</div>
								      	</c:if>
								      	<c:if test="${item.get('BUYSTATE') eq '반품' }">
											<div>반품처리중입니다.</div>
								      	</c:if>
								      	<c:if test="${item.get('BUYSTATE') eq '교환' }">
											<div>교환처리중입니다.</div>
								      	</c:if>
								      </td>
								    </tr>
						  		</c:forEach>
						  </tbody>
						</table>
						<c:if test="${ empty orderList }">
							<h3 class="mt-5">구매내역이 없습니다</h3>
						</c:if>
					</div>
				</div>
			</section>
			
        </div>
      </div>
    </div>
    
    <div class="modal fade" id="locationModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-lg modal-dialog-centered">
	    <div class="modal-content border border-secondary rounded-3 border-opacity-50">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalToggleLabel">배송 정보</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	      	<form id="infoChange">
				<div class="input-group mb-3">
				  <span class="input-group-text" id="basic-addon3">수령자 이름</span>
				  <input type="text" class="form-control" id="locationName" aria-describedby="basic-addon3" value="">
				</div>
				<div class="input-group mb-3">
				  <span class="input-group-text" id="basic-addon3">수령자 우편번호</span>
				  <input type="text" class="form-control" id="locationZipcode" aria-describedby="basic-addon3" value="">
				</div>
				<div class="input-group mb-3">
				  <span class="input-group-text" id="basic-addon3">수령자 상세주소1</span>
				  <input type="text" class="form-control" id="locationAddress" aria-describedby="basic-addon3" value="">
				</div>
				<div class="input-group mb-3">
				  <span class="input-group-text" id="basic-addon3">수령자 상세주소2</span>
				  <input type="text" class="form-control" id="locationAddress2" aria-describedby="basic-addon3" value="">
				</div>
				<div class="input-group mb-3">
				  <span class="input-group-text" id="basic-addon3">수령자 전화번호</span>
				  <input type="text" class="form-control" id="locationPhone" aria-describedby="basic-addon3" value="">
				</div>
				<div class="input-group mb-3">
				  <span class="input-group-text" id="basic-addon3">배송 메모</span>
				  <input type="text" class="form-control" id="locationMemo" aria-describedby="basic-addon3" value="">
				</div>
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
      	  </div>
	    </div>
	  </div>
	</div>
	
	 <div class="modal fade" id="StateModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-lg modal-dialog-centered">
	    <div class="modal-content border border-secondary rounded-3 border-opacity-50">
	      <div class="modal-body d-flex justify-content-between align-items-center">
			<div id="infoText"></div>
			<div>
				<a id="stateUpdate" class="btn btn-primary">확인</a>
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
			</div>
	      </div>
	    </div>
	  </div>
	</div>
	
	
    <!-- footer start -->
	<%@ include file="../../../component/footer.jsp" %>
    <!-- footer end -->
  </body>
  <script type="text/javascript">
	let tbody = document.getElementById('tbody');
	let locationInfo = []
	
	let Name = document.getElementById('locationName');
	let Zipcode = document.getElementById('locationZipcode');
	let Address = document.getElementById('locationAddress');
	let Address2 = document.getElementById('locationAddress2');
	let Phone = document.getElementById('locationPhone');
	let Memo = document.getElementById('locationMemo');
	 
	tbody.addEventListener('click',function(e){
		let address = e.target.dataset.address;
		if(address != null){
			locationInfo = address.split(',');
		}
		<!-- ['백종국', '경기도 구리시 수택동', '빌라 마동 302호', '46978', '01033992301', '빨리 갖다주세요'] -->
		
		Name.value = locationInfo[0];
		Address.value = locationInfo[1];
		Address2.value = locationInfo[2];
		Zipcode.value = locationInfo[3];
		Phone.value = locationInfo[4];
		Memo.value = locationInfo[5];
	});
	
	function searchDate(){
		const date = document.getElementById('searchDate');
		const selectDate = date.value;
		
		if (selectDate != null && selectDate.length > 0){
			location.href="./memberListPayment.do?today="+selectDate;
		}else {
			alert('날짜를 입력해주세요.');
		}
	}
	
	function buystate(){
		const text = document.getElementById('infoText');
		const stateUpdate = document.getElementById('stateUpdate');
		
		const state = event.currentTarget.dataset.state;
		const Onumber = event.currentTarget.dataset.ordernumber;
		const Pno = event.currentTarget.dataset.productno;
		const price = event.currentTarget.dataset.price;
		const point = parseInt(price)/10;
		const id = '${ mem_id }';
		console.log(id);
		
		switch(state) {
		  case 'buy':  
				text.innerText = "구매를 확정하시면 더이상 반품 및 교환이 불가합니다. 그래도 확정하시겠습니까?";
				stateUpdate.setAttribute('href','./memberUpdateState.do?state=buy&order_number='+Onumber+'&product_no='+Pno+'&point='+point+'&member_id='+id);
				break;
		  case 'exchange': 
				text.innerText = "교환을 신청하시겠습니까?";
				stateUpdate.setAttribute('href','./memberUpdateState.do?state=exchange&order_number='+Onumber+'&product_no='+Pno);
				break;
		  case 'return':
				text.innerText = "반품을 신청하시겠습니까?";
				stateUpdate.setAttribute('href','./memberUpdateState.do?state=return&order_number='+Onumber+'&product_no='+Pno);
				break;
		  default:
				text.innerText = "알수 없음";
				break;
		} 
	}
  </script>
</html>
