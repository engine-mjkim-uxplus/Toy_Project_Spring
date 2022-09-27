<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="greenContainer bg-primary bg-opacity-50">
  <div>
    <div class="grade">COMMON</div>
    <div class="name">${member.getMember_id()}</div>
  </div>
  <div class="modify">
	<button class="btn btn-secondary btn-sm" type="button" data-bs-toggle="modal" data-bs-target="#pwUpdModal">비밀번호 변경</button>
  </div>
</div>


<div class="summaryContainer border-bottom border-secondary">
  <div class="item">
    <div class="number">${member.getMember_name()}</div>
    <div>회원이름</div>
  </div>
  <div class="item">
    <div class="number">${member.getMember_point()}</div>
    <div>적립금(KRW)</div>
  </div>
  <div class="item">
    <div class="number">${member.getMember_coupon()}</div>
    <div>보유 쿠폰</div>
  </div>
</div>

<!-- 회원탈퇴 모달 -->
    <script type="text/javascript">
		function changePW(){
			let form = document.updform;
			if(!form.member_pw.value) {
				alert('변경할 비밀번호를 입력해주세요');
				return false;
			} else if(form.member_pw.value == ${member.getMember_pw()}){
  				alert('비밀번호가 현재 비밀번호와 일지합니다. 다시 입력해주세요.');
  				form.member_pw.value = "";
				return false;
  			} else {
  				alert('비밀번호가 변경되었습니다.');
  				window.close();
				return true;
  			}
		}
	</script>
    <div class="modal fade" id="pwUpdModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-lg modal-dialog-centered">
	    <div class="modal-content border border-secondary rounded-3 border-opacity-50">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalToggleLabel">비밀번호 변경하기</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
			<form name="updform"  role="search" action="./memberUpdateP.do" method="post" onsubmit="return changePW()">
			 	<div class="box p-3">
				 	<h5>변경할 비밀번호를 입력해주세요</h5>
				    <hr />
				    <br />
				    <div class="input-group">
						<input type="hidden" class="form-control" name="member_id" value="${member.getMember_id()}">
						<input type="hidden" class="form-control" name="member_name"  value="${member.getMember_name()}">
						<input type="hidden" class="form-control" name="member_phone"  value="${member.getMember_phone()}">
						<input type="hidden" class="form-control" name="member_zipcode" value="${member.getMember_zipcode()}">
						<input type="hidden" class="form-control" name="member_address" value="${member.getMember_address()}">
						<input type="hidden" class="form-control" name="member_address2" value="${member.getMember_address2()}">
						<input type="hidden" class="form-control" name="member_gender" value="${member.getMember_gender()}">
				        <input type="password" class="form-control" name="member_pw" id="member_pw" />
				        
				        <button class="btn btn-outline-secondary" type="submit">변경하기</button>
				    </div>
			    </div>
			</form>
	      </div>
	    </div>
	  </div>
	</div>
    <!-- 회원탈퇴 모달 -->