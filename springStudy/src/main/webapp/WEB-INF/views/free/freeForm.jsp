<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<script src = "${pageContext.request.contextPath}/resources/smarteditor2-2.8.2.3/js/HuskyEZCreator.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/inc/top.jsp"%>
	<section class="page-section" id="contact">
		<div class="container" style="margin-top: 100px;">
		  <form action="freeBoardWriteDo" method="post" onsubmit="return fn_check()">
                <table class="table table-striped table-bordered">
                    <tr>
                        <th>제목</th>
                        <td><input name="boTitle" class="form-control input-sm" type="text"  required="required"></td>
                    </tr>
                    <tr>
                        <th>작성자</th>
                        <td><input name="boWriter" class="form-control input-sm" type="text"  required="required"></td>
                    </tr>
                    <tr>
                        <th>비밀번호</th>
                        <td><input name="boPass" class="form-control input-sm" type="password" required="required"></td>
                    </tr>
                    <tr>
                    	<th>분류</th>
                    	<td>
                    		<select name="boCategory" class="form-control input-sm">
                    			<option value="">---선택---</option>
                    			<c:forEach items="${comList }" var="code">
                    			<option value="${code.commCd }">${code.commNm }</option>
                    			</c:forEach>
                    		</select>
                    	</td>
                    </tr>
                    <tr>
                        <th>내용</th>
                        <td><textarea name="boContent" id="bo_content" class="form-control input-sm"></textarea></td>
                    </tr>
                    <tr>
                     	<td colspan="2">
                     		<button type="submit" class="btn btn-primary">전송</button>
                     	</td>
                    </tr>
                    
                </table>
            </form>
		</div>
	</section>
</body>
<script type="text/javascript">

	var oEditors=[];
	
	$(document).ready(function(){
		nhn.husky.EZCreator.createInIFrame({
			oAppRef: oEditors,
			elPlaceHolder: "bo_content",
			sSkinURI: "<c:url value='/resources/smarteditor2-2.8.2.3/SmartEditor2Skin.html'/>",	
			htParams : {
				bUseToolbar : true,				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
				bUseVerticalResizer : true,		// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
				bUseModeChanger : true,			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
				bSkipXssFilter : true,		// client-side xss filter 무시 여부 (true:사용하지 않음 / 그외:사용)
				//aAdditionalFontList : aAdditionalFontSet,		// 추가 글꼴 목록
				fOnBeforeUnload : function(){
					//alert("완료!");
				}
			}, //boolean
			fOnAppLoad : function(){
				//예제 코드
				//oEditors.getById["ir1"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
			},
			fCreator: "createSEditor2"
		});

	});
	function fn_check(){
		//textarea 값
		//
		oEditors.getById['bo_content'].exec("UPDATE_CONTENTS_FIELD",[]);
		var content = document.getElementById("bo_content").value;
		if(content =='' || content ==null 
						|| content =="&nbsp;" 
						|| content=='<p>&nbsp;</p>'){
			
			alert("내용을 입력하세요!");
			return false;
		}else {
			if(confirm("진짜 저장?")){
				return true;
			}else{
				return false;
			}
		}
	}
</script>
</html>






