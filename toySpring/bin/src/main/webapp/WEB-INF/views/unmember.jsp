<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta name="description" content="" />
    <meta
      name="author"
      content="Mark Otto, Jacob Thornton, and Bootstrap contributors"
    />
    <meta name="generator" content="Hugo 0.101.0" />
    <title>WorksOut Home</title>
    <link
      rel="canonical"
      href="https://getbootstrap.com/docs/5.2/examples/footers/"
    />
	<%@ include file="../../common/common.jsp" %>
    <meta name="theme-color" content="#712cf9" />
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
    </style>
  </head>
  <body>
    <script type="text/javascript">

  	</script>
    <!-- nav start -->
	<%@ include file="../../component/nav.jsp" %>
    <!-- nav end -->

 	<header class="d-flex justify-content-center mb-5">
		<div class="row">
		  <div class="col-4">
		    <div class="list-group d-flex" id="list-tab" role="tablist">
		      <a class="list-group-item list-group-item-action active" id="list-home-list" data-bs-toggle="list" href="#list-home" role="tab" aria-controls="list-home">주문번호로 조회</a>
		      <a class="list-group-item list-group-item-action" id="list-profile-list" data-bs-toggle="list" href="#list-profile" role="tab" aria-controls="list-profile">전호번호로 조회</a>
		    </div>
		  </div>
		  
		  <div class="col-8 mt-3">
		    <div class="tab-content" id="nav-tabContent">
		      <div class="tab-pane fade show active" id="list-home" role="tabpanel" aria-labelledby="list-home-list">
				<form>
					<div class="input-group input-group-lg">
					  <span class="input-group-text" id="inputGroup-sizing-lg">주문번호</span>
					  <input type="text" name="order_number" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-lg">
					  <button type="submit" class="btn btn-secondary">검색</button>
					</div>
				</form>
			  </div>
		      <div class="tab-pane fade" id="list-profile" role="tabpanel" aria-labelledby="list-profile-list">
		      	<form>
					<div class="input-group input-group-lg">
					  <span class="input-group-text" id="inputGroup-sizing-lg">전화번호</span>
					  <input type="text" name="order_phone" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-lg">
					  <button type="submit" class="btn btn-secondary">검색</button>
					</div>
				</form>
			  </div>
		    </div>
		  </div>
		</div>
 	</header>
 	
 	<c:if test="${ !empty unmemberList }">
 	<div class="container px-4 px-lg-5 mb-5">
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
				    <c:forEach var="item" items="${unmemberList}">
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
		</div>
	</div>
 	</c:if>
 	
 	<!-- 배송정보 모달 -->
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
	
	<!-- 구매 상태 모달 -->
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
	<%@ include file="../../component/footer.jsp" %>
    <!-- footer end -->
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
				stateUpdate.setAttribute('href','./orderUnmemberSelect.do?state=buy&order_number='+Onumber+'&product_no='+Pno+'&point='+point+'&member_id='+id);
				break;
		  case 'exchange': 
				text.innerText = "교환을 신청하시겠습니까?";
				stateUpdate.setAttribute('href','./orderUnmemberSelect.do?state=exchange&order_number='+Onumber+'&product_no='+Pno);
				break;
		  case 'return':
				text.innerText = "반품을 신청하시겠습니까?";
				stateUpdate.setAttribute('href','./orderUnmemberSelect.do?state=return&order_number='+Onumber+'&product_no='+Pno);
				break;
		  default:
				text.innerText = "알수 없음";
				break;
		} 
	}
	
  </script>
  </body>
</html>