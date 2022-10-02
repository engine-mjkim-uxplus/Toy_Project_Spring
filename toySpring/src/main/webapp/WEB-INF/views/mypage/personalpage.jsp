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
		 /*  background-color: #ddd; */
		}
    </style>
  </head>
  <body>
  <script>
  function memUpdateP(){
		alert('회원정보가 수정되었습니다.');
	  	$("#f_memform").submit();
	}
  </script>
    <!-- nav start -->
	<%@ include file="../../../component/nav.jsp" %>
    <!-- nav end -->
    
    <%@ include file="./component/head.jsp" %>
    <div class="wrap">
    
      <div class="row d-flex" style="height: 1000px;">
		<%@ include file="./component/side.jsp" %>
			<div class="col-md-10 content p-3">
				<h4 class="font-medium hidden md:block"><b>🔑내 정보 수정</b></h4>
				<hr>
				<h5 class="font-medium mb-4">나의 정보</h5>
				<div class="input-form-backgroud row">
					<div class="input-form col-md-12 mx-auto">
						<form class="validation-form" name="memForm" id="f_memform" method="post" action="./memberUpdateP">
							<div class="row">
								<div class="col-md-6 mb-3">
									<label for="member_name">이름</label> 
									<input type="text" class="form-control" id="member_name" name="member_name" value="${member.getMember_name()}" required>
								</div>
								<div class="col-md-3 mb-3">
									<label for="member_id" style="color: red;">*아이디는 변경이 불가합니다</label> 
									<input type="text" class="form-control" name="member_id" value="${member.getMember_id()}" readonly>
								</div>
								<div class="col-md-3 mt-3 pt-3">
									<label>성별</label> 
									<div class="form-check form-check-inline">
			                          <input
			                            type="radio"
			                            id="member_gender"
			                            name="member_gender"
			                            value="여자"
			                            <c:if test="${member.getMember_gender() eq '여자'}">checked</c:if>
			                          />여자
			                          <input
			                            type="radio"
			                            name="member_gender"
			                            id="member_gender"
			                            value="남자"
			                            <c:if test="${member.getMember_gender() eq '남자'}">checked</c:if>
			                          />남자
			                        </div>
								</div>
							</div>
							<input type="hidden" name="member_pw" value="${member.getMember_pw()}">
							
							<div class="mb-3">
								<label for="member_phone">전화번호</label> <input type="text"
									class="form-control" id="member_phone" name="member_phone" value="${member.getMember_phone()}"
									required>
							</div>
							
							<hr>
							<h5 class="font-medium mb-4">배송 정보</h5>
							<div class="row">
								<div class="col-9 mb-3">
									<label for="member_zipcode">우편번호</label> 
									<input type="text" class="form-control" id="member_zipcode" name="member_zipcode" value="${member.getMember_zipcode()}" >
								</div>
								<div class="col-3">
									<label for="btn_address"> </label> 
									<input type="button" class="form-control" id="btn_address" onclick="findAddress()" value="주소찾기" >
								</div>
							</div>

							<div class="mb-3">
								<label for="member_address">주소</label> 
								<input type="text" class="form-control" id="member_address" name="member_address" value="${member.getMember_address()}">
							</div>
							
							<div class="mb-3">
								<label for="member_address2">상세주소</label> 
								<input type="text" class="form-control" id="member_address2" name="member_address2" value="${member.getMember_address2()}">
							</div>

							<div class="mb-4"></div>
							<input class="btn btn-secondary" onclick="memUpdateP()" type="button" value="수정하기">
							<button class="btn btn-danger" type="button" data-bs-toggle="modal" data-bs-target="#memDelModal">탈퇴하기</button>
						</form>
					</div>
				</div>

			</div>
	<!-- footer start -->
	<%@ include file="../../../component/footer.jsp" %>
    <!-- footer end -->
    
    <!-- 회원탈퇴 모달 -->
    <script type="text/javascript">
		function memDel(){
			let form = document.delform;
			if(!form.member_pw.value) {
				alert('비밀번호를 입력해주세요');
				return false;
			} else if(form.member_pw.value != ${member.getMember_pw()}){
  				alert('비밀번호가 틀렸습니다.');
				return false;
  			} else {
  				alert('탈퇴되었습니다. 이용해주셔서 감사합니다.');
  				window.close();
				return true;
  			}
		}
	</script>
    <div class="modal fade" id="memDelModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-lg modal-dialog-centered">
	    <div class="modal-content border border-secondary rounded-3 border-opacity-50">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalToggleLabel">회원 탈퇴</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
			<form name="delform" role="search" action="./memberDelete" method="post" onsubmit="memDel()">
			 	<div class="box p-3">
				 	<h5>탈퇴를 원하시면 비밀번호를 입력해주세요</h3>
				    <hr />
				    <br />
				    <div class="input-group">
						<input type="hidden" class="form-control" name="member_id" id="member_id" value="${member.getMember_id()}">
				        <input type="password" class="form-control" name="member_pw" id="member_pw" />
				        
				        <button class="btn btn-outline-secondary" type="submit">탈퇴하기</button>
				    </div>
			    </div>
			</form>
	      </div>
	    </div>
	  </div>
	</div>
    <!-- 회원탈퇴 모달 -->
    
    <!-- 다음지도 api때문에 추가한 부분 -->
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
		function findAddress(){
		   new daum.Postcode({
		        oncomplete: function(data) {
		        	// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

	                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var addr = ''; // 주소 변수
	                var extraAddr = ''; // 참고항목 변수

	                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
	                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
	                    addr = data.roadAddress;
	                } else { // 사용자가 지번 주소를 선택했을 경우(J)
	                    addr = data.jibunAddress;
	                }

	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                document.getElementById('member_zipcode').value = data.zonecode;
	                document.getElementById("member_address").value = addr;
	                // 커서를 상세주소 필드로 이동한다.
	                document.getElementById("member_address2").focus();
	                // 상세주소 초기화
	                document.getElementById("member_address2").value = "";
		        }
		    }).open();
		}
	</script>
  </body>
</html>