<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글</title>
</head>
<body>
	<jsp:include page="/WEB-INF/inc/top.jsp"></jsp:include>
    <section class="page-section" style="margin-top:100px" id="contact">
        <div class="container">
            <!-- Contact Section Heading-->
            <h2 class="page-section-heading text-center text-uppercase text-secondary mb-0">게시글</h2>
            <!-- Icon Divider-->
            <div class="divider-custom">
                <div class="divider-custom-line"></div>
                <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
                <div class="divider-custom-line"></div>
            </div>
            <!-- Contact Section Form-->
            <div class="row justify-content-center">
                <div class="col-lg-8 col-xl-7">
                        <div class="mb-3">
                        	<label>제목</label>
                        	<h6>${board.boardTitle }</h6>
                        </div>
                        <div class="mb-3">
                        	<label>작성자</label>
                        	<h6>${board.memNm }</h6>
                        </div>
                        <div class="mb-3">
                        	<label>수정일</label>
                        	<h6>${board.updateDt }</h6>
                        </div>
                        <div class="mb-3">
                        	<label>내용</label>
                        	<textarea class="form-control" style="height:20rem" readonly>${board.boardContent }</textarea>
                        </div>
                        <c:if test="${sessionScope.login.memId == board.memId }">
                        	<form action="<c:url value="/boardEditView" />" method="post" style="display: inline-block;">
                        	    <input type="hidden" name="boardNo" value="${board.boardNo }">
                        		<button class="btn btn-warning btn-xl" type="submit">수정</button>
                        	</form>
                        	<button class="btn btn-danger btn-xl" type="submit">삭제</button>
                    	</c:if>
                </div>
            </div>
            
            <div class="divider-custom divider-light">
                 <div class="divider-custom-line"></div>
                 <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
                 <div class="divider-custom-line"></div>
             </div>
             
			<!-- 댓글작성 -->
            <div class="row justify-content-center pt-1">
            	<div class="col-lg-8 col-xl-7 d-flex">
            		<div class="col-lg-10">
            			<input type="text" id="replyInput" class="form-control">
            		</div>
            		<div class="col-lg-2">
            			<button type="button" onclick="fn_write()" class="btn btn-info me-2">등록</button>
            		</div>
            	</div>
            </div>
			<!-- 댓글출력 -->
            <div class="row justify-content-center pt-1">
            	<div class="col-lg-8 col-xl-7">
            		<table class="table">
            			<tbody id="replyBody">
	            			<c:forEach items="${replyList}" var="reply">
	            				<tr id="${reply.replyNo}">
	            					<td>${reply.replyContent}</td>
	            					<td>${reply.memNm}</td>
	            					<td>${reply.replyDate}</td>
	            					<c:if test="${sessionScope.login.memId == reply.memId }">
	            						<td><a onclick="fn_del('${reply.replyNo}')">X</a></td>
	            					</c:if>
	            				</tr>
	            			</c:forEach>
            			</tbody>
            		</table>
            	</div>
            </div>
        </div>
    </section>
    <script type="text/javascript">
    	function fn_write(){
    		let memId = '${sessionScope.login.memId}';
    		let boardNo = '${board.boardNo}';
    		let msg = $("#replyInput").val();
    		if(memId == ''){
    			alert("댓글은 로그인 해야함!!!");
    			return;
    		}
    		if(msg == ''){
    			alert("내용을 작성해주세요!!!");
    			return;
    		}
    		let sendData = JSON.stringify({  "memId":memId
    			                           , "boardNo":boardNo
    			                           , "replyContent":msg });
    		console.log(sendData);
    		$.ajax({
    				 url : '<c:url value="/writeReplyDo" />'
    				,type : 'POST'
    				,contentType: 'application/json'
    				,dataType :'json'
    				,data : sendData
    				,success:function(res){
    					console.log('응답');
    					console.log(res);
    					let str = "";
    					str +="<tr id='"+res.replyNo+"'>";
    					str +="<td>" +res.replyContent + "</td>";
    					str +="<td>" +res.memNm + "</td>";
    					str +="<td>" +res.replyDate + "</td>";
    					str +="<td><a onclick='fn_del(\""+res.replyNo+"\")'>X</a></td>";
    					str +="</tr>";
    					$("#replyBody").append(str);
    				}
    				,error : function(e){
    					console.log(e);
    				}
    		});
    		
    	}
    	function fn_del(p_replyNo){
    		if(confirm("정말로 삭제 하시겠습니까?!")){
    			
    			$.ajax({
    				 url : '<c:url value="/delReplyDo" />'
    				,type: 'POST'
    				,data: JSON.stringify({"replyNo": p_replyNo})
    				,contentType:'application/json'
    				,dataType : "text"
    				,success:function(res){
    					if(res == 'success'){
    						$("#" + p_replyNo).remove();
    					}
    				},error:function(e){
    					console.log(e);
    				}
    			});
    			
    		}
    	}
    	
    </script>
	<jsp:include page="/WEB-INF/inc/footer.jsp"></jsp:include>
</body>
</html>