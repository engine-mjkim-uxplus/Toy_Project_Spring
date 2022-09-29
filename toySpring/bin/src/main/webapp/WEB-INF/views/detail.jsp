<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Detail Page</title>
    <%@ include file="../../common/common.jsp" %>
    <style type="text/css">
    	a{
    	  text-decoration: none;
    	}
    	#icontext{
		  position: relative;
		  right: 38px;
		  bottom: 5px;
    	}
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

	    #myform fieldset{
		    display: inline-block;
		    direction: rtl;
		    border:0;
		}
		#myform fieldset legend{
		    text-align: right;
		}
		#myform input[type=radio]{
		    display: none;
		}
		#myform label{
		    font-size: 2em;
		    color: transparent;
		    text-shadow: 0 0 0 #f0f0f0;
		}
		#myform label:hover{
			/* text-shadow: 0 0 0 rgba(250, 208, 0, 0.99); */
		    text-shadow: 0 0 0 orange;
		}
		#myform label:hover ~ label{
		    /* text-shadow: 0 0 0 rgba(250, 208, 0, 0.99); */
		    text-shadow: 0 0 0 orange;
		}
		#myform input[type=radio]:checked ~ label{
		    /* text-shadow: 0 0 0 rgba(250, 208, 0, 0.99); */
		    text-shadow: 0 0 0 orange;
		}
		#reviewContents {
		    width: 100%;
		    height: 150px;
		    padding: 10px;
		    box-sizing: border-box;
		    border: solid 1.5px #D3D3D3;
		    border-radius: 5px;
		    font-size: 16px;
		    resize: none;
		    
		}
    </style>
  </head>
  <body>

<script type="text/javascript">		
  		function cartCount(result){
  			let count = document.getElementById('inputQuantity');
  			let countNum = parseInt(count.value);
  			
  			if (result === '+'){
  				count.value = ++countNum;
  			}else {
  				countNum = countNum > 1 ? countNum-1 : countNum;
  				count.value = countNum;
  			}
  		}
  		
		function addLike(no,category){
			let likeList = ${likeList == null ?[] :likeList};
			
			if (likeList.length === 0){
				alert("로그인이 필요합니다.");
				return;
			}
			
			if(likeList.includes(no)){
				if(confirm("이미 좋아요한 상품입니다. 취소하시겠습니까?")){
					alert('좋아요가 취소됐습니다.');	
					location.href = "./productDeleteLike.do?page=productDetail.do&product_no="+no+"&product_category="+category;
				}
			}else {
				alert("좋아요를 눌렀습니다.");
				location.href = "./productInsertLike.do?page=productDetail.do&product_no="+no+"&product_category="+category;
			}
		}
		
		function addCart(product_no, product_category,product_img,product_price, product_name){
			let count = $("#inputQuantity").val();
			alert("장바구니에 담았습니다.");
			let url = "detail";
			location.href=`/cart/cartInsert.do?product_no=${'${product_no}'}
								&product_category=${'${product_category}'}&product_count=${'${count}'}
								&product_img=${'${product_img}'}&product_price=${'${product_price}'}
								&product_name=${'${product_name}'}&url=${'${url}'}
								`;
		}
  	</script>
    <!-- nav start -->
	<%@ include file="../../component/nav.jsp" %>
    <!-- nav end -->

    <!-- Product section-->
    <c:set var="item" value="${productList.get(0)}"/>
	<c:if test="${ productList.size() > 5 }">
		<c:set var="review" value="${productList.get(5)}"/>
	</c:if>
    <section class="bg-light">
      <div class="container px-4 px-lg-5 my-5">
        <div class="row gx-4 gx-lg-5 align-items-center">
          <div class="col-md-6">
            <img
              class="card-img-top mb-5 mb-md-0"
              src="${ item.get('PRODUCT_IMG') }"
              alt="..."
            />
          </div>
          <div class="col-md-6">
            <div class="small mb-1">${ item.get('PRODUCT_CATEGORY') }</div>
            <h1 class="display-5 fw-bolder">${ item.get('PRODUCT_NAME') }</h1>
            <div class="fs-5 mb-3">
              <!-- <span class="text-decoration-line-through">$45.00</span> -->
              <span class="mb-3">${ item.get('PRODUCT_PRICE') }\</span>
	          <h4>
			   <a href="javascript:addLike(${ item.get('PRODUCT_NO') },'${ item.get('PRODUCT_CATEGORY') }')">
			      <i class="fa fa-heart" style="color: red;"> ${ item.get('PRODUCT_LIKE') }</i>
			   </a>
		          /
              	<i class="fas fa-star" style="color: orange;">
              		<c:if test="${ !empty review }">${ review.get("REVIEW_AVG") }</c:if>
              		<c:if test="${ empty review }">0</c:if>
              	</i>
	          </h4>
            </div>
            <p class="lead">
              Lorem ipsum dolor sit amet consectetur adipisicing elit.
              Praesentium at dolorem quidem modi. Nam sequi consequatur
              obcaecati excepturi alias magni, accusamus eius blanditiis
              delectus ipsam minima ea iste laborum vero?
            </p>
            <div class="d-flex mb-2">
              <input
                class="form-control text-center me-3"
                id="inputQuantity"
                type="num"
                value="1"
                style="max-width: 3rem"
              />
              <button onclick="cartCount('+')" class="btn btn-outline-dark me-1" type="button"><i class="fas fa-plus-circle"></i></button>
              <button onclick="cartCount('-')" class="btn btn-outline-dark" type="button"><i class="fas fa-minus-circle"></i></button>
            </div>
            <button onclick='addCart(${ item.get('PRODUCT_NO')}, "${ item.get('PRODUCT_CATEGORY')}","${ item.get('PRODUCT_IMG')}",${ item.get('PRODUCT_PRICE')},"${ item.get('PRODUCT_NAME')}")' class="btn btn-outline-dark flex-shrink-0" type="button">
              <i class="bi-cart-fill me-1">Add to cart</i>
            </button>
          </div>
        </div>
      </div>
    </section>
    <!-- Product section end -->
    
	<!-- Nav tabs -->
	<ul class="nav nav-pills justify-content-center mb-5" id="myTab" role="tablist">
	  <li class="nav-item w-25" role="presentation">
	    <button class="nav-link active w-75" id="home-tab" data-bs-toggle="tab" data-bs-target="#home" type="button" role="tab" aria-controls="home" aria-selected="true">관련 상품</button>
	  </li>
	  <li class="nav-item w-25" role="presentation">
	    <button class="nav-link w-75" id="profile-tab" data-bs-toggle="tab" data-bs-target="#profile" type="button" role="tab" aria-controls="profile" aria-selected="false">상품 리뷰</button>
	  </li>
	</ul>

	<!-- Tab panes start -->
	<div class="tab-content">
	  <!-- Related items section-->
	  <div class="tab-pane active" id="home" role="tabpanel" aria-labelledby="home-tab" tabindex="0">	    
		<section class="bg-light mt-5">
			<div class="container px-4 px-lg-5 mt-5">
				<h2 class="fw-bolder mb-4">Related Products</h2>
				<div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
					<c:forEach begin="1" end="4" var="product" items="${productList}">
						<div class="col mb-5">
							<div class="card h-100">
								<c:if test="${ product.get('PRODUCT_NO') == 101 }">
									<!-- Sale badge-->
									<div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale</div>
								</c:if>
								<!-- Product image-->
								<a href="./productDetail.do?product_no=${product.get('PRODUCT_NO')}&product_category=${product.get('PRODUCT_CATEGORY')}">
									<img
										class="card-img-top"
										src="${product.get('PRODUCT_IMG')}"
										 alt="..."
									/>
								</a>
								<!-- Product details-->
								<div class="card-body p-4">
									<div class="text-center">
										<!-- Product name-->
										<h5 class="fw-bolder">${product.get('PRODUCT_NAME')}</h5>
										<!-- Product price-->
										${product.get('PRODUCT_PRICE')}\
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>    
			</div>
		</section>
	  </div>
	  <!-- Related items end-->
	  
	  <!-- review start-->
	  <div class="tab-pane" id="profile" role="tabpanel" aria-labelledby="profile-tab" tabindex="0">
	    <section class="bg-light d-flex justify-content-center mt-5">
		    <div class="container px-4 px-lg-5">
			    <div class="mb-4 d-flex justify-content-between">
			    	<h2 class="fw-bolder">Product Reviews</h2>
			    	<c:if test="${ !empty mem_id }">
			    		<button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#starModal">리뷰 작성하기</button>
			    	</c:if>
			    </div>
			    <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
					<c:if test="${ !empty review }">
				    <table class="table">
					  <thead class="table-dark">
					    <tr>
					      <th class="col-1" scope="col">작성일</th>
					      <th class="col-1" scope="col">작성자</th>
					      <th class="col-2" scope="col">별점</th>
					      <th class="col-2" scope="col">첨부사진</th>
					      <th class="col-6" scope="col">후기</th>
					    </tr>
					  </thead>
					  <tbody id="tbody">
					  	<c:forEach begin="5" var="reviewList" items="${productList}">
						  	<tr>
						      <td scope="row"><div>${reviewList.get("REVIEW_DATE")}</div></td>
						      <td><div>${reviewList.get("MEMBER_ID")}</div></td>
						      <td>
						      	<c:forEach begin="1" end="${reviewList.get('REVIEW_SCORE')}">
						      		<i class="fas fa-star" style="color: orange;"></i>
							  	</c:forEach>
						      </td>
						      <c:if test="${ !empty reviewList.get('REVIEW_IMG') }">
							      <td>
							      	<a href="#" data-bs-toggle="modal" data-bs-target="#imgModal">
							      		<img style="width: 100px; height: 100px;" src="../../../reviewimg/${reviewList.get('REVIEW_IMG')}" 
							      			data-img="${reviewList.get('REVIEW_IMG')}"
							      		/>
							      	</a>
							      </td>
						      </c:if>
						      <c:if test="${ empty reviewList.get('REVIEW_IMG') }">
							      <td>
							      	<img style="width: 100px; height: 100px;" src="../../../reviewimg/null.jpg"/>
							      </td>
						      </c:if>
						      <td><div>${reviewList.get("REVIEW_CONTENT")}</div></td>
						    </tr>
					  	</c:forEach>
					  </tbody>
					</table>
					</c:if>
					<c:if test="${ empty review }">
						<h1 class="m-5 w-100">구매 후기가 없습니다. 리뷰를 작성해주세요.</h1>
						<div style="height: 400px;"></div>
					</c:if>
				</div>
			</div>
		</section>
	  </div>
	  <!-- review end-->
	</div>
	<!-- Tab panes end -->
	
    
    
    <!-- footer start -->
	<%@ include file="../../component/footer.jsp" %>
    <!-- footer end -->
    
    <!-- 리뷰 모달 -->
    <jsp:useBean id="now" class="java.util.Date" />
    <fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="now" />
    <div class="modal fade" id="starModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-lg modal-dialog-centered">
	    <div class="modal-content border border-secondary rounded-3 border-opacity-50">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalToggleLabel">리뷰 작성하기</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
			<form id="myform" enctype="multipart/form-data" role="search" action="productInsertReview.do" method="post">
				<fieldset>
					<input type="radio" name="review_score" value="5" id="rate1">
					<label for="rate1">
						<i class="fas fa-star"></i>
					</label>
					<input type="radio" name="review_score" value="4" id="rate2">
					<label for="rate2">
						<i class="fas fa-star"></i>
					</label>
					<input type="radio" name="review_score" value="3" id="rate3">
					<label for="rate3">
						<i class="fas fa-star"></i>
					</label>
					<input type="radio" name="review_score" value="2" id="rate4">
					<label for="rate4">
						<i class="fas fa-star"></i>
					</label>
					<input type="radio" name="review_score" value="1" id="rate5">
					<label for="rate5">
						<i class="fas fa-star"></i>
					</label>
				 </fieldset>
				<div class="input-group">
					<input type="file" class="form-control" name="review_img">
				</div>
			 	<div class="input-group">
				  <input type="text" class="form-control" name="review_content" placeholder="리뷰 내용을 입력하세요." >
				  <input type="hidden" class="form-control" name="review_date" value="${now}">
				  <input type="hidden" class="form-control" name="member_id" value="${mem_id}">
				  <input type="hidden" class="form-control" name="product_no" value="${item.get('PRODUCT_NO')}">
				  <input type="hidden" class="form-control" name="product_category" value="${item.get('PRODUCT_CATEGORY')}">
				  <button class="btn btn-outline-primary" type="submit">등록</button>
				</div>
			</form>
	      </div>
	    </div>
	  </div>
	</div>
	
	<div class="modal fade" id="imgModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-lg modal-dialog-centered">
	    <div class="modal-content border border-secondary rounded-3 border-opacity-50">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalToggleLabel">첨부사진</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
			<img id="modalImg" style="width: 100%; height: 500px;">
	      </div>
	    </div>
	  </div>
	</div>
	<script type="text/javascript">
 		let tbody = document.getElementById('tbody');
 		let modalImg = document.getElementById('modalImg');
 		
 		tbody.addEventListener('click',function(e){
			const imgName = e.target.dataset.img;
			const imgPath = "../../../reviewimg/"+imgName;
			modalImg.setAttribute('src',imgPath);
		});
	</script>
  </body>
</html>