<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Đăng nhập</title>
</head>
<body>
<div class="container">
    <!-- <h1 class="form-heading">login Form</h1> -->
    <div class="login-form">
        <div class="main-div">
            <c:if test="${param.incorrectAccount != null}">
                <div class="alert alert-danger">
                    Username or password incorrect
                </div>
            </c:if>
            <c:if test="${param.accessDenied != null}">
                <div class="alert alert-danger">
                    You not authorize
                </div>
            </c:if>
            <c:if test="${param.logout != null}">
                <div class="alert alert-success">
                    Logout success
                </div>
            </c:if>
            <form id="formLogin">
                <div class="form-group">
                    <input type="text" value="dvtien" class="form-control" name="username" placeholder="Tên đăng nhập">
                </div>
                <div class="form-group">
                    <input type="password" value="123456" class="form-control" name="password" placeholder="Mật khẩu">
                </div>
                <button id="btnSubmit" type="button" class="btn btn-primary" >Đăng nhập</button>
            </form>
        </div>
    </div>
</div>
<%-- sweetalert2 --%>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="/mylib/js/call-ajax.js"></script>
<script>
    handleClick("#formLogin", "#btnSubmit",
        "/api/auth/signin", "post", function (result) {
            if(result.status){
                var token = result.data.accessToken;
                // nếu save ở sessionStorage thì token không thể tồn tại ở nhiều tag
                localStorage.setItem('token', token); // save token ở localStorage f12 web
                window.location.href = '/classifications';
            }else{
                swal('Login fail! Try again ...');
            }
        });
</script>
</body>
</html>