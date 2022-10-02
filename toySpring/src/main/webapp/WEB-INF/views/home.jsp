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
      .product-grid {
        font-family: "Poppins", sans-serif;
        text-align: center;
      }
      .product-grid .product-image {
        overflow: hidden;
        position: relative;
        z-index: 1;
      }
      .product-grid .product-image a.image {
        display: block;
      }
      .product-grid .product-image img {
        width: 100%;
        height: auto;
      }
      .product-grid .product-discount-label {
        color: #fff;
        background: lightslategray;
        font-size: 13px;
        font-weight: 600;
        line-height: 25px;
        padding: 0 20px;
        position: absolute;
        top: 10px;
        left: 0;
      }
      .product-grid .product-links {
        padding: 0;
        margin: 0;
        list-style: none;
        position: absolute;
        top: 10px;
        right: -50px;
        transition: all 0.5s ease 0s;
      }
      .product-grid:hover .product-links {
        right: 10px;
      }
      .product-grid .product-links li a {
        color: #333;
        background: transparent;
        font-size: 17px;
        line-height: 38px;
        width: 38px;
        height: 38px;
        border: 1px solid #333;
        border-bottom: none;
        display: block;
        transition: all 0.3s;
      }
      .product-grid .product-links li:last-child a {
        border-bottom: 1px solid #333;
      }
      .product-grid .product-links li a:hover {
        color: #fff;
        background: #333;
      }
      .product-grid .add-to-cart {
        background: lightslategray;
        color: #fff;
        font-size: 16px;
        text-transform: uppercase;
        letter-spacing: 2px;
        width: 100%;
        padding: 10px 26px;
        position: absolute;
        left: 0;
        bottom: -60px;
        transition: all 0.3s ease 0s;
      }
      .product-grid:hover .add-to-cart {
        bottom: 0;
      }
      .product-grid .add-to-cart:hover {
        text-shadow: 4px 4px rgba(0, 0, 0, 0.2);
      }
      .product-grid .product-content {
        background: #fff;
        padding: 15px;
        box-shadow: 0 0 0 5px rgba(0, 0, 0, 0.1) inset;
      }
      .product-grid .title {
        font-size: 16px;
        font-weight: 600;
        text-transform: capitalize;
        margin: 0 0 7px;
      }
      .product-grid .title a {
        color: #777;
        transition: all 0.3s ease 0s;
      }
      .product-grid .title a:hover {
        color: #a5ba8d;
      }
      .product-grid .price {
        color: #0d0d0d;
        font-size: 14px;
        font-weight: 600;
      }
      .product-grid .price span {
        color: #888;
        font-size: 13px;
        font-weight: 400;
        text-decoration: line-through;
      }
      @media screen and (max-width: 990px) {
        .product-grid {
          margin-bottom: 30px;
        }
      }
    </style>
  </head>
  <body>
    <script type="text/javascript">
		function addLike(no){			
			let likeList = ${likeList == null ?[] :likeList};
			
			if (likeList.length === 0){
				alert("로그인이 필요합니다.");
				return;
			}
			
			if(likeList.includes(no)){
				if(confirm("이미 좋아요한 상품입니다. 취소하시겠습니까?")){
					alert('좋아요가 취소됐습니다.');	
					location.href = "./productDeleteLike.do?page=productList.do&product_no="+no
				}
			}else {
				alert("좋아요를 눌렀습니다.");
				location.href = "./productInsertLike.do?page=productList.do&product_no="+no
			}
		}
		function addCart(product_no, product_category,product_img,product_price, product_name){
			alert("장바구니에 담았습니다.");
			console.log("product_no = " + product_no);
			console.log("product_category = " + product_category);
			console.log("product_img = " + product_img);
			console.log("product_price = " + product_price);
			console.log("product_name = " + product_name);
 			let count = 1;
 			let url = "home";
			location.href=`/cart/cartInsert?product_no=${'${product_no}'}
								&product_category=${'${product_category}'}&product_count=${'${count}'}
								&product_img=${'${product_img}'}&product_price=${'${product_price}'}
								&product_name=${'${product_name}'}&url=${'${url}'}
								`;
		}
  	</script>
    <!-- nav start -->
	<%@ include file="../../component/nav.jsp" %>
    <!-- nav end -->

    <!-- 상풍 분류 컨테이너 시작 -->
	<ul class="nav nav-pills mb-3 justify-content-center" id="pills-tab" role="tablist">
	  <li class="nav-item" role="presentation">
	    <button class="nav-link active" id="pills-home-tab" data-bs-toggle="pill" data-bs-target="#pills-home" type="button" role="tab" aria-controls="pills-home" aria-selected="true">전체</button>
	  </li>
	  <li class="nav-item" role="presentation">
	    <button class="nav-link" id="pills-cap-tab" data-bs-toggle="pill" data-bs-target="#pills-cap" type="button" role="tab" aria-controls="pills-cap" aria-selected="false">모자</button>
	  </li>
	  <li class="nav-item" role="presentation">
	    <button class="nav-link" id="pills-shirt-tab" data-bs-toggle="pill" data-bs-target="#pills-shirt" type="button" role="tab" aria-controls="pills-shirt" aria-selected="false">상의</button>
	  </li>
	  <li class="nav-item" role="presentation">
	    <button class="nav-link" id="pills-pants-tab" data-bs-toggle="pill" data-bs-target="#pills-pants" type="button" role="tab" aria-controls="pills-pants" aria-selected="false">하의</button>
	  </li>
	  <li class="nav-item" role="presentation">
	    <button class="nav-link" id="pills-pants-tab" data-bs-toggle="pill" data-bs-target="#pills-pants" type="button" role="tab" aria-controls="pills-pants" aria-selected="false" disabled>신발</button>
	  </li>
	</ul>
	<!-- 상풍 분류 컨테이너 끝 -->
	
	<!-- product content start -->
	<div class="tab-content" id="pills-tabContent">
	
	  <!-- 전체 상품 시작 -->
	  <div class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab" tabindex="0">
	  	<div class="row mt-5">
			<c:forEach var="item" items="${productList}">
				<div class="col-md-3 col-sm-6 border border-secondary rounded-1 border-opacity-50" style="width: 10%">
			        <div class="product-grid">
			          <div class="product-image">
				        <c:if test="${ item.getProduct_no() == 101 }">
				        	<span class="product-discount-label">-20%</span>
						</c:if>
			            <a href="./productDetail.do?product_no=${item.getProduct_no()}&product_category=${item.getProduct_category()}" class="image">
			              <img
			                src="${item.getProduct_img()}"
			              />
			            </a>
			            <ul class="product-links">
			              <li>
			              	<a href="javascript:addLike(${item.getProduct_no()})" ><i class="fa fa-heart" style="color: red;"></i></a>
			              </li>
			              <li>
			                <a href="javascript:addCart(${item.getProduct_no()},'${item.getProduct_category()}','${item.getProduct_img()}',${item.getProduct_price()},'${item.getProduct_name()}')"><i class="fas fa-shopping-cart"></i></a>
			              </li>
			            </ul>
			          </div>
			        </div>
		    	</div>
			</c:forEach>
	    </div>
	  </div>
	  <!-- 전체 상품 끝 -->
	  
	  <!-- 모자 상품 끝 -->
	  <div class="tab-pane fade" id="pills-cap" role="tabpanel" aria-labelledby="pills-cap-tab" tabindex="0">
	  	  	<div class="row mt-5">
				<c:forEach var="item" items="${productList}">
					<c:if test="${ item.getProduct_category() eq 'cap' }">
						<div class="col-md-3 col-sm-6 border border-secondary rounded-1 border-opacity-50" style="width: 10%">
					        <div class="product-grid">
					          <div class="product-image">
						        <c:if test="${ item.getProduct_no() == 101 }">
						        	<span class="product-discount-label">-20%</span>
								</c:if>
					            <a href="./productDetail.do?product_no=${item.getProduct_no()}&product_category=${item.getProduct_category()}" class="image">
					              <img
					                src="${item.getProduct_img()}"
					              />
					            </a>
					            <ul class="product-links">
					              <li>
					                <a href="javascript:addLike(${item.getProduct_no()})" ><i class="fa fa-heart" style="color: red;"></i></a>
					              </li>
					              <li>
					               <a href="javascript:addCart(${item.getProduct_no()},'${item.getProduct_category()}','${item.getProduct_img()}',${item.getProduct_price()},'${item.getProduct_name()}')"><i class="fas fa-shopping-cart"></i></a>
					              </li>
					            </ul>
					          </div>
					        </div>
				    	</div>
			    	</c:if>
				</c:forEach>
		    </div>
	  </div>
	  <!-- 모자 상품 끝 -->
	  
	  <!-- 상의 상품 시작 -->
	  <div class="tab-pane fade" id="pills-shirt" role="tabpanel" aria-labelledby="pills-shirt-tab" tabindex="0">
	  	  	<div class="row mt-5">
				<c:forEach var="item" items="${productList}">
					<c:if test="${ item.getProduct_category() eq 't-shirt' }">
						<div class="col-md-3 col-sm-6 border border-secondary rounded-1 border-opacity-50" style="width: 10%">
					        <div class="product-grid">
					          <div class="product-image">
						        <c:if test="${ item.getProduct_no() == 101 }">
						        	<span class="product-discount-label">-20%</span>
								</c:if>
					            <a href="./productDetail.do?product_no=${item.getProduct_no()}&product_category=${item.getProduct_category()}" class="image">
					              <img
					                src="${item.getProduct_img()}"
					              />
					            </a>
					            <ul class="product-links">
					              <li>
					                <a href="javascript:addLike(${item.getProduct_no()})" ><i class="fa fa-heart" style="color: red;"></i></a>
					              </li>
					              <li>
					                <a href="javascript:addCart(${item.getProduct_no()},'${item.getProduct_category()}','${item.getProduct_img()}',${item.getProduct_price()},'${item.getProduct_name()}')"><i class="fas fa-shopping-cart"></i></a>
					              </li>
					            </ul>
					          </div>
					        </div>
				    	</div>
			    	</c:if>
				</c:forEach>
		    </div>
	  </div>
	  <!-- 상의 상품 끝 -->
	  
	  <!-- 하의 상품 시작 -->
	  <div class="tab-pane fade" id="pills-pants" role="tabpanel" aria-labelledby="pills-pants-tab" tabindex="0">
	  	  	<div class="row mt-5">
				<c:forEach var="item" items="${productList}">
					<c:if test="${ item.getProduct_category() eq 'pants' }">
						<div class="col-md-3 col-sm-6 border border-secondary rounded-1 border-opacity-50" style="width: 10%">
					        <div class="product-grid">
					          <div class="product-image">
						        <c:if test="${ item.getProduct_no() == 101 }">
						        	<span class="product-discount-label">-20%</span>
								</c:if>
					            <a href="./productDetail.do?product_no=${item.getProduct_no()}&product_category=${item.getProduct_category()}" class="image">
					              <img
					                src="${item.getProduct_img()}"
					              />
					            </a>
					            <ul class="product-links">
					              <li>
					                <a href="javascript:addLike(${item.getProduct_no()})" ><i class="fa fa-heart" style="color: red;"></i></a>
					              </li>
					              <li>
					                <a href="javascript:addCart(${item.getProduct_no()},'${item.getProduct_category()}','${item.getProduct_img()}',${item.getProduct_price()},'${item.getProduct_name()}')"><i class="fas fa-shopping-cart"></i></a>
					              </li>
					            </ul>
					          </div>
					        </div>
				    	</div>
			    	</c:if>
				</c:forEach>
		    </div>
	  </div>
	  <!-- 하의 상품 끝 -->
	  
	</div>
	<!-- product content end -->

    <!-- 추가할 레이아웃 시작 -->

    <!-- 추가할 레이아웃 끝 -->

    <!-- footer start -->
	<%@ include file="../../component/footer.jsp" %>
    <!-- footer end -->
  </body>
</html>