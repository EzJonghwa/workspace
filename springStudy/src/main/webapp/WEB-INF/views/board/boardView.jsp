<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<title>회원 게시판</title>
</head>
<body>
	<jsp:include page="/WEB-INF/inc/top.jsp"></jsp:include>
    <section class="page-section" style="margin-top:100px">
    	<div class="container">
    		<div class="d-flex justify-content-end"></div>
    		<table class="table table-hover">
    			<thead>
    				<tr><td>번호</td><td>제목</td><td>작성자</td><td>수정일</td></tr>
    			</thead>
    			<tbody>
    				<c:forEach items="${boardList}" var="board" >
    					<tr>
    						<td>${board.boardNo}</td>
    						<td>
    							<a href="<c:url value="/getBoard?boardNo=${board.boardNo}" /> "> 
    							   ${board.boardTitle}
    							 </a>
    					    </td>
    						<td>${board.memNm}</td>
    						<td>${board.updateDt}</td>
    					</tr>
    				</c:forEach>
    			</tbody>
    		</table>
    		<a href="<c:url value="/boardWriteView" /> ">
    			<button type="button" class="btn btn-primary">글쓰기</button>
    		</a>
    	</div>
    </section>
	<jsp:include page="/WEB-INF/inc/footer.jsp"></jsp:include>
</body>
</html>