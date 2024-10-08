<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<title>test</title>
</head>
<body>
	<jsp:include page="/WEB-INF/inc/top.jsp"></jsp:include>
	<!-- Contact Section-->
	<section class="page-section" id="contact" style="margin-top: 150px">
		<div class="container">
			<!-- Contact Section Form-->
			<div class="row justify-content-center">
				<div class="col-lg-8 col-xl-7">
					<select id="mainSelect">
						<option value="">-- 선택하세요 --</option>
						<c:forEach items ="${comList}" var="code">
							<option value="${code.commCd}">${code.commNm}</option>
						</c:forEach>
					</select>
					
					<select id="subSelect">
						<option value=""> -- 선택하세요 -- </option>
						
					
					</select>
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="/WEB-INF/inc/footer.jsp"></jsp:include>
	<script>
	// 제이쿼리
		 
		
		 $(document).ready(function(){
         $("#mainSelect").change(function() {
            let code =$(this).val();
            if(code ==""){
               $("#subSelect").empty().append('<option value="">-- 선택하세요 --</option>');
               return;
            }
            $.ajax({
               url : '<c:url value="/api/getSubCodes" />'
               ,type :'GET'
               ,data : {"commParent": code}
               ,dataType:'json'
               ,success:function(res) {
                  $("#subSelect").empty().append('<option value="">-- 선택하세요 --</option>');
                  $.each(res, function(idx, item){
                     $("#subSelect").append('<option value="'+ item.commCd +'">'
                            + item.commNm + '</option>');
                  });         
               },error:function(e) {
                  console.log(e);
               }
            });
         });
      });
		
		
	</script>
</body>
</html>