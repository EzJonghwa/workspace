<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
  <script>
        $(document).ready(function () {
            const forms = document.querySelectorAll('.needs-validation')
            Array.from(forms).forEach(form => {
                form.addEventListener('submit', event => {
                    if (!form.checkValidity()) {
                        event.preventDefault()
                        event.stopPropagation()
                    }

                    form.classList.add('was-validated')
                }, false)
            });
        
        });
    </script>
</head>
<body>
	<jsp:include page="/WEB-INF/inc/top.jsp"></jsp:include>
	<!--회원가입 -->
	<!-- Contact Section-->
    <section class="page-section" style="margin-top:100px" id="contact">
        <div class="container">
            <!-- Contact Section Heading-->
            <h2 class="page-section-heading text-center text-uppercase text-secondary mb-0">회원가입</h2>
            <!-- Icon Divider-->
            <div class="divider-custom">
                <div class="divider-custom-line"></div>
                <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
                <div class="divider-custom-line"></div>
            </div>
            <!-- Contact Section Form-->
            <div class="row justify-content-center">
                <div class="col-lg-8 col-xl-7">
                    <form id="" action="/registDo" method="post">
                        <!-- Id input-->
                        <div class="form-floating mb-3">
                            <input class="form-control" id="id" name="memId" type="text" placeholder="아이디를 입력하세요..."/>
                            <label for="id">ID</label>
                        </div>
                        <!-- Pw  input-->
                        <div class="form-floating mb-3">
                            <input class="form-control" id="password" name="memPw" type="password" placeholder="비밀번호를 입력하세요..."  />
                            <label for="password">Password</label>
                        </div>
           
                        <!-- Nm input-->
                        <div class="form-floating mb-3">
                            <input class="form-control" id="name" name="memNm" type="text" placeholder="이름을 입력하세요..."  />
                            <label for="id">Name</label>
                        </div>
                        <button class="btn btn-primary btn-xl" id="submitButton" type="submit">가입하기</button>
                    </form>
                </div>
            </div>
        </div>
    </section>
	<!--회원가입 -->
	<jsp:include page="/WEB-INF/inc/footer.jsp"></jsp:include>
</body>
</html>