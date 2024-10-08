<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
</head>

<body>
	<jsp:include page="/WEB-INF/inc/top.jsp" ></jsp:include>
	<section class="page-section" id="contact">
		<div class="container" style="margin-top: 100px;">
			<div class="card-body pt-0">
				<!-- START : 검색 폼  -->
				<div class="row g-0 justify-content-md-center">
					<div class="col-auto col-sm-5 mb-3">
					<form name ="search" action ="<c:url value="/free/freeList"/>" method="post">
						<div class="input-group">
							<div class="col-sm-2">
							<select id="id_searchType" name="searchType"
							class="form-control input-sm">
						
							<option value="T" ${searchVO.searchType eq "T" ? "selected ='selected'" : ""}>제목</option>
							<option value="W" ${searchVO.searchType eq "W" ? "selected ='selected'" : ""}>작성자</option>
							<option value="C" ${searchVO.searchType eq "C" ? "selected ='selected'" : ""}>내용</option>
							</select>
							</div>
							<div class="col-sm-2">
								<select id="id_searchCategory" name="searchCategory"
								class="form-control input-sm">
								<option value="">-- 전체 --</option>
									<c:forEach items="${comList}" var="code">
										<option ${searchVO.searchCategory eq code.commCd ? "selected ='selected'" :"" } value="${code.commCd}">${code.commNm }</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-sm-6">
								<input class ="form-control shadow-none search"type ="search" name="searchWord" placeholder="검색어"
								value="${searchVO.searchWord}">
							</div>
							<div class="col-sm-2 text-right">
							<button type="submit" class="btn btn-primary form-control"><i class="fa fa-search"></i>검색</button>
							
							</div>		
						</div>
					</form>
					
					</div>
				</div>
				<!-- END : 검색 폼  -->
				<!-- START : 목록건수 및 새글쓰기 버튼  -->
				<div class="row justify-content-md-end">
					<div class="col-sm-2">
						<div class="input-group">
						</div>	
					</div>
				</div>
				<!-- END : 목록건수 및 새글쓰기 버튼  -->
			</div>
			<table class="table table-striped table-bordered table-hover">
			<colgroup>
			<col width="10%" />
			<col width="15%" />
			<col width="40%" />
			<col width="15%" />
			<col width="10%" />
			<col width="10%" />
			</colgroup>
			<thead>
			<tr>
			<th>글 번호</th><th>분류</th><th>제목</th><th>작성자</th><th>등록일</th><th>조회수</th></tr>
			</thead>
			<tbody>
			</tbody>
			</table>
			<div class="d-grid gap-2 d-md-flex justify-content-md-end">
				<a href="freeForm" class="btn btn-primary">글쓰기</a>
			</div>
			<!-- START : 페이지네이션  -->
			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center">
				</ul>
			</nav>
			<!-- END : 페이지네이션  -->
		</div>
		<!-- container -->
	</section>
	<jsp:include page="/WEB-INF/inc/footer.jsp" ></jsp:include>
</body>
<script type="text/javascript">

	$(document).ready(function(){       
	});

</script>
</html>






