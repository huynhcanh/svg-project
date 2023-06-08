<%@ page import="com.example.svg_project.utils.SecurityUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<html>
<!--Designed By ALpha-->
<head>
    <title><dec:title default="Title" /></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Vendor styles -->
    <link rel="stylesheet" href="/vendors/bower_components/material-design-iconic-font/dist/css/material-design-iconic-font.min.css">
    <link rel="stylesheet" href="/vendors/bower_components/animate.css/animate.min.css">
    <link rel="stylesheet" href="/vendors/bower_components/jquery.scrollbar/jquery.scrollbar.css">

    <!-- App styles -->
    <%-- <link rel="stylesheet" href="/css/app.min.css">--%>
    <link rel="stylesheet" href="/css/app.min.css2.css">

    <%-- jquery --%>
    <script src="http://code.jquery.com/jquery.min.js" type="text/javascript"></script>
    <%-- sweetalert2 --%>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <%-- font-awesome --%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<%-- nếu <link rel="stylesheet" href="/css/app.min.css"> thì <body data-sa-theme="1"> --%>
<body data-sa-theme="4">
    <main class="main">
        <!-- page loader -->
        <%@ include file="/common/page-loader.jsp" %>

        <!-- header -->
        <%@ include file="/common/header.jsp" %>

        <!-- sidebar -->
        <%@ include file="/common/sidebar.jsp" %>

        <section class="content">
            <dec:body/>
            <!-- footer -->
            <%@ include file="/common/footer.jsp" %>
        </section>
    </main>

    <!-- Javascript -->
    <!-- Vendors -->
    <script src="/vendors/bower_components/jquery/dist/jquery.min.js"></script>
    <script src="/vendors/bower_components/popper.js/dist/umd/popper.min.js"></script>
    <script src="/vendors/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="/vendors/bower_components/jquery.scrollbar/jquery.scrollbar.min.js"></script>
    <script src="/vendors/bower_components/jquery-scrollLock/jquery-scrollLock.min.js"></script>

    <!-- Vendors: Data tables -->
    <script src="/vendors/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
    <script src="/vendors/bower_components/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
    <script src="/vendors/bower_components/datatables.net-buttons/js/buttons.print.min.js"></script>
    <script src="/vendors/bower_components/jszip/dist/jszip.min.js"></script>
    <script src="/vendors/bower_components/datatables.net-buttons/js/buttons.html5.min.js"></script>

    <!-- App functions and actions -->
    <script src="/js/app.min.js"></script>
</body>
</html>