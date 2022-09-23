<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>회원가입</title>
	<%@ include file="../../common/common.jsp" %>
	<style>
	 a {
        text-decoration: none;
      }
	body {
		box-sizing: border-box;
	}
	@media (min-width: 1025px) {
	  .h-custom {
	    height: 100vh !important;
	  }
	}
	.card-registration .select-input.form-control[readonly]:not([disabled]) {
	  font-size: 1rem;
	  line-height: 2.15;
	  padding-left: 0.75em;
	  padding-right: 0.75em;
	}
	.card-registration .select-arrow {
	  top: 13px;
	}
	
	.gradient-custom-2 {
	  /* fallback for old browsers */
	  background: #a1c4fd;
	
	  /* Chrome 10-25, Safari 5.1-6 */
	  background: -webkit-linear-gradient(
	    to right,
	    rgba(161, 196, 253, 1),
	    rgba(194, 233, 251, 1)
	  );
	
	  /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
	  background: linear-gradient(
	    to right,
	    rgba(161, 196, 253, 1),
	    rgba(194, 233, 251, 1)
	  );
	}
	
	.bg-indigo {
	  background-color: #4835d4;
	}
	@media (min-width: 992px) {
	  .card-registration-2 .bg-indigo {
	    border-top-right-radius: 15px;
	    border-bottom-right-radius: 15px;
	  }
	}
	@media (max-width: 991px) {
	  .card-registration-2 .bg-indigo {
	    border-bottom-left-radius: 15px;
	    border-bottom-right-radius: 15px;
	  }
	}
	</style>
  </head>
  <body>
  <script type="text/javascript">
  		function idCheck(){
  			const form = document.userForm;
  			const member_id = form.member_id.value;
  			if(!member_id){
  				alert('id를 입력하세요.');
  			} else {
  				window.open("${contextPath}/register/registerSelect.do?member_id="+member_id,"","width=600px, height=450px")
  			}
  		}
  		
  		function signUp() {
  			const form 	= document.userForm;
  			
  			if(!form.member_name.value){
  				alert('이름을 입력하세요.');
  				return false;
  			}
  			if(!form.member_id.value){
  				alert('id를 입력하세요.');
  				return false;
  			}
  			if(!form.member_pw.value){
  				alert('비밀번호를 입력하세요');
  				return false;
  			}
  			if(form.idDuplication.value != "idCheck"){
  				alert('아이디 중복체크 해주세요.');
  				return false;
  			} 
  			if(!form.member_phone.value){
  				alert('전화번호을 입력하세요.');
  				return false;
  			}
  		}
  		
  </script>
    <!-- nav start -->
	<%@ include file="../../component/nav.jsp" %>
    <!-- nav end -->
    <section class="h-100 h-custom gradient-custom-2">
    <form action="./registerInsert.do" method="post" id="f_registerIns" name="userForm" onsubmit="return signUp()">
      <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
          <div class="col-12">
            <div
              class="card card-registration card-registration-2"
              style="border-radius: 15px; border: 1px solid #ddd"
            >
              <div class="card-body p-0">
                <div class="row g-0">
                  <div class="col-lg-6">
                    <div class="p-5">
                      <h3 class="fw-normal mb-5" style="color: #4835d4">
                        회원가입
                      </h3>
                      <!-- [[이름]] -->
                      <div class="mb-4 pb-2">
                        <label class="form-label" for="member_name">이름: </label>
                        <input
                          type="text"
                          id="member_name"
                          name="member_name"
                          class="form-control form-control-lg"
                          placeholder="이름을 입력해주세요"
                        />
                      </div>
                      <!-- [[아이디]] -->
                      <div class="row">
                        <div class="col-md-2">
                          <div
                            class="form-outline"
                            style="margin-top: 15px; text-align: right"
                          >
                            아이디:
                          </div>
                        </div>
                        <div class="col-md-6 mb-4 pb-2">
                          <div class="form-outline">
                            <input
                              type="text"
                              id="member_id"
                              name="member_id"
                              class="form-control form-control-lg"
                              placeholder="아이디를 입력해주세요"
                            />
                          </div>
                        </div>
                        <div class="col-md-3 mb-4 pb-2">
                          <div class="form-outline">
                            <button
                              type="button"
                              id="idCheck"
                              name="idCheck"
                              class="form-control"
                              style="
                                background-color: rgba(161, 196, 253, 1);
                                margin-top: 5px;
                              "
                            ><a href="javascript:idCheck()">
                            중복확인
                            </a>
                            </button>
                            <!-- id중복 체크 여부 -->
                            <input type="hidden" id="idDuplication" value="idUncheck">
                          </div>
                        </div>
                      </div>
                      <!-- [[비밀번호]] -->
                      <div class="mb-4 pb-2">
                        <label class="form-label" for="member_pw"
                          >비밀번호:
                        </label>
                        <input
                          type="password"
                          id="member_pw"
                          name="member_pw"
                          class="form-control form-control-lg"
                          placeholder="비밀번호를 입력해주세요"
                        />
                      </div>
                      <!-- [[전화번호]] -->
                      <div class="mb-4 pb-2">
                        <div class="form-outline">
                          <label class="form-label" for="member_phone"
                            >전화번호:
                          </label>
                          <input
                            type="text"
                            id="member_phone"
                            name="member_phone"
                            class="form-control form-control-lg"
                            placeholder="전화번호를 입력해주세요"
                          />
                        </div>
                      </div>

                      <!-- [[성별체크]] -->
                      <div class="col-md-12 mb-5 form-outline">
                        <label class="form-label" for="gender">성별 </label>
                        <div class="form-check form-check-inline">
                          <input
                            type="radio"
                            id="member_gender"
                            name="member_gender"
                            value="여자"
                            checked
                          />여자
                            <input
                              type="radio"
                              name="member_gender"
                              id="member_gender"
                              value="남자"
                            />남자
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="col-lg-6 bg-indigo text-white">
                    <div class="p-5">
                      <h3 class="fw-normal mb-5">상세주소</h3>

                     <div class="row">
                          <div class="col-md-8 mb-4 pb-2">
                            <div class="form-outline form-white">
                              <label class="form-label" for="member_zipcode"
                                >우편번호:</label
                              >
                              <input
                                type="text"
                                id="member_zipcode"
                                name="member_zipcode"
                                class="form-control form-control-lg"
                                placeholder="우편번호"
                              />
                            </div>
                          </div>
                          <div class="col-md-4 mt-3">
                            <div style="margin: 20px"></div>
                            <input
                              type="button"
                              id="btn_address"
                              onclick="findAddress()"
                              class="form-control"
                              value="우편번호 찾기"
                            />
                          </div>
                        </div>

                        <div class="mb-4 pb-2">
                          <div class="form-outline form-white">
                            <label class="form-label" for="member_address"
                              >주소:
                            </label>
                            <input
                              type="text"
                              id="member_address"
                              name="member_address"
                              class="form-control form-control-lg"
                              placeholder="주소"
                            />
                          </div>
                        </div>

                        <div class="mb-4 pb-2">
                          <div class="form-outline form-white">
                            <input
                              type="text"
                              id="member_address2"
                              name="member_address2"
                              class="form-control form-control-lg"
                              placeholder="상세주소 입력"
                            />
                          </div>
                        </div>

                      <br /><br /><br /><br />
                      <div class="row">
                        <div class="col-md-9"></div>
                        <div class="col-md-3">
                          <button
                            type="submit"
                            id="btn_register"
                            class="btn btn-light btn-lg"
                            data-mdb-ripple-color="dark"
                            style="background-color: #cce1ff"
                          >
                            회원가입
                          </button>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </form>
    </section>
  	<!-- footer start -->
	<%@ include file="../../component/footer.jsp" %>
    <!-- footer end -->
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
		        }
		    }).open();
		}
	</script>
  </body>
</html>
