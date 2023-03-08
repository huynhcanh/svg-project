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
    <link rel="stylesheet" href="/vendors/bower_components/fullcalendar/dist/fullcalendar.min.css">
    <!-- App styles -->
    <link rel="stylesheet" href="/css/app.min.css">
    <!-- Demo -->
    <link rel="stylesheet" href="/demo/css/demo.css">

    <%-- jquery --%>
    <script src="http://code.jquery.com/jquery.min.js" type="text/javascript"></script>
    <%-- sweetalert2 --%>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <%-- font-awesome --%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <%-- select2 --%>
    <link href="/vendors/bower_components/select2/dist/css/select2.min.css" rel="stylesheet" />
    <%-- select2 --%>
    <script src="/vendors/bower_components/select2/dist/js/select2.full.min.js"></script>
</head>
<body data-sa-theme="1">
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
<%--    <script src="/vendors/bower_components/jquery/dist/jquery.min.js"></script>--%>
    <script src="/vendors/bower_components/popper.js/dist/umd/popper.min.js"></script>
    <script src="/vendors/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="/vendors/bower_components/jquery.scrollbar/jquery.scrollbar.min.js"></script>
    <script src="/vendors/bower_components/jquery-scrollLock/jquery-scrollLock.min.js"></script>

    <script src="/vendors/bower_components/autosize/dist/autosize.min.js"></script>

    <script src="/vendors/bower_components/salvattore/dist/salvattore.min.js"></script>
    <script src="/vendors/bower_components/flot/jquery.flot.js"></script>
    <script src="/vendors/bower_components/flot/jquery.flot.resize.js"></script>
    <script src="/vendors/bower_components/flot.curvedlines/curvedLines.js"></script>
    <script src="/vendors/bower_components/jqvmap/dist/jquery.vmap.min.js"></script>
    <script src="/vendors/bower_components/jqvmap/dist/maps/jquery.vmap.world.js"></script>
    <script src="/vendors/bower_components/jquery.easy-pie-chart/dist/jquery.easypiechart.min.js"></script>
    <script src="/vendors/bower_components/peity/jquery.peity.min.js"></script>
    <script src="/vendors/bower_components/moment/min/moment.min.js"></script>
    <script src="/vendors/bower_components/fullcalendar/dist/fullcalendar.min.js"></script>

    <!-- Charts and maps-->
    <script src="/demo/js/flot-charts/curved-line.js"></script>
    <script src="/demo/js/flot-charts/line.js"></script>
    <script src="demo/js/flot-charts/dynamic.js"></script>
    <script src="/demo/js/flot-charts/chart-tooltips.js"></script>
    <script src="/demo/js/other-charts.js"></script>
    <script src="/demo/js/jqvmap.js"></script>


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