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
</head>
<body>
	<%@ include file="/WEB-INF/inc/top.jsp"%>
	<section class="page-section" id="contact">
		<div class="container" style="margin-top: 100px;">
                <table class="table table-striped table-bordered">
                    <tr>
                        <th>제목</th>
                        <td><input class="form-control input-sm" type="text" name="boTitle" value="${free.boTitle}" ></td>
                    </tr>
                    <tr>
                        <th>작성자</th>
                        <td><input class="form-control input-sm" type="text" name="boWriter" value="${free.boWriter}" ></td>
                    </tr>
                    <tr>
                    	<th>분류</th>
                    	<td><input class="form-control input-sm" type="text" name="boCategoryNm" value="${free.boCategory}" ></td>
                    </tr>
                    <tr>
                        <th>내용</th>
                        <td>${free.boContent}</td>
                    </tr>
                </table>
		</div>
	</section>
</body>
<script type="text/javascript">
</script>
</html>






