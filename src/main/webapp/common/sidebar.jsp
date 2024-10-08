<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<aside class="sidebar">
    <div class="scrollbar-inner">

        <div class="user">
            <div class="user__info" data-toggle="dropdown">
                <img class="user__img" src="demo/img/profile-pics/8.jpg" alt="">
                <div>
                    <div class="user__name">Malinda Hollaway</div>
                    <div class="user__email">malinda-h@gmail.com</div>
                </div>
            </div>

            <div class="dropdown-menu">
                <a class="dropdown-item" href="#">View Profile</a>
                <a class="dropdown-item" href="#">Settings</a>
                <a class="dropdown-item" href="#">Logout</a>
            </div>
        </div>

        <ul class="navigation">
<%--            <li class="@@indexactive"><a href="/home"><i class="zmdi zmdi-home"></i> Home</a></li>--%>

            <li class="@@typeactive"><a href="/classifications"><i class="zmdi zmdi-format-underlined"></i> Classification</a></li>

            <li class="@@widgetactive"><a href="/locations"><i class="zmdi zmdi-widgets"></i> Locations</a></li>



<%--            <li class="navigation__sub @@formactive">--%>
<%--                <a href="#"><i class="zmdi zmdi-collection-text"></i> Forms</a>--%>

<%--                <ul>--%>
<%--                    <li class="@@formelementactive"><a href="form-elements.html">Basic Form Elements</a></li>--%>
<%--                    <li class="@@formcomponentactive"><a href="form-components.html">Form Components</a></li>--%>
<%--                    <li class="@@formvalidationactive"><a href="form-validation.html">Form Validation</a></li>--%>
<%--                </ul>--%>
<%--            </li>--%>

<%--            <li class="navigation__sub @@uiactive">--%>
<%--                <a href="#"><i class="zmdi zmdi-swap-alt"></i> User Interface</a>--%>

<%--                <ul>--%>
<%--                    <li class="@@colorsactive"><a href="colors.html">Colors</a></li>--%>
<%--                    <li class="@@cssanimationsactive"><a href="css-animations.html">CSS Animations</a></li>--%>
<%--                    <li class="@@buttonsactive"><a href="buttons.html">Buttons</a></li>--%>
<%--                    <li class="@@iconsactive"><a href="icons.html">Icons</a></li>--%>
<%--                    <li class="@@listviewactive"><a href="listview.html">Listview</a></li>--%>
<%--                    <li class="@@toolbarsactive"><a href="toolbars.html">Toolbars</a></li>--%>
<%--                    <li class="@@cardsactive"><a href="cards.html">Cards</a></li>--%>
<%--                    <li class="@@alertactive"><a href="alerts.html">Alerts</a></li>--%>
<%--                    <li class="@@badgesactive"><a href="badges.html">Badges</a></li>--%>
<%--                    <li class="@@breadcrumbsactive"><a href="breadcrumbs.html">Bredcrumbs</a></li>--%>
<%--                    <li class="@@jumbotronactive"><a href="jumbotron.html">Jumbotron</a></li>--%>
<%--                    <li class="@@navsactive"><a href="navs.html">Navs</a></li>--%>
<%--                    <li class="@@paginationactive"><a href="pagination.html">Pagination</a></li>--%>
<%--                    <li class="@@progressactive"><a href="progress.html">Progress</a></li>--%>
<%--                </ul>--%>
<%--            </li>--%>

<%--            <li class="navigation__sub @@componentsactive">--%>
<%--                <a href="#"><i class="zmdi zmdi-group-work"></i> Javascript Components</a>--%>

<%--                <ul class="navigation__sub">--%>
<%--                    <li class="@@carouselactive"><a href="carousel.html">Carousel</a></li>--%>
<%--                    <li class="@@collapseactive"><a href="collapse.html">Collapse</a></li>--%>
<%--                    <li class="@@dropdownsactive"><a href="dropdowns.html">Dropdowns</a></li>--%>
<%--                    <li class="@@modalsactive"><a href="modals.html">Modals</a></li>--%>
<%--                    <li class="@@popoveractive"><a href="popover.html">Popover</a></li>--%>
<%--                    <li class="@@tabsactive"><a href="tabs.html">Tabs</a></li>--%>
<%--                    <li class="@@tooltipsactive"><a href="tooltips.html">Tooltips</a></li>--%>
<%--                    <li class="@@notificationsactive"><a href="notifications-alerts.html">Notifications & Alerts</a></li>--%>
<%--                </ul>--%>
<%--            </li>--%>

<%--            <li class="navigation__sub @@chartsactive">--%>
<%--                <a href="#"><i class="zmdi zmdi-trending-up"></i> Charts</a>--%>

<%--                <ul>--%>
<%--                    <li class="@@flotchartsactive"><a href="flot-charts.html">Flot</a></li>--%>
<%--                    <li class="@@otherchartsactive"><a href="other-charts.html">Other Charts</a></li>--%>
<%--                </ul>--%>
<%--            </li>--%>

            <li class="@@photogalleryactive"><a href="/items"><i class="zmdi zmdi-image"></i> Items</a></li>
            <li class="navigation__sub @@tableactive">
                <a href="#"><i class="zmdi zmdi-view-list"></i> Managements</a>
                <ul>
                    <li class="@@normaltableactive"><a href="/moves">Move</a></li>
                    <li class="@@datatableactive"><a href="/history">History</a></li>
                </ul>
            </li>
    <li class="@@calendaractive"><a href="/scan-qr"><i class="zmdi zmdi-calendar"></i> Scan QR</a></li>

    <li class="@@calendaractive">
        <form action="/j_spring_security_logout" method="post">
            <i class="zmdi zmdi-calendar"></i> <input type="submit" value="Logout" />
        </form>
    </li>

<%--            <li class="navigation__sub @@samplepagesactive">--%>
<%--                <a href="#"><i class="zmdi zmdi-collection-item"></i> Sample Pages</a>--%>

<%--                <ul>--%>
<%--                    <li class="@@profileactive"><a href="profile-about.html">Profile</a></li>--%>
<%--                    <li class="@@messagesactive"><a href="messages.html">Messages</a></li>--%>
<%--                    <li class="@@contactsactive"><a href="contacts.html">Contacts</a></li>--%>
<%--                    <li class="@@groupsactive"><a href="groups.html">Groups</a></li>--%>
<%--                    <li class="@@pricingtablesactive"><a href="pricing-tables.html">Pricing Tables</a></li>--%>
<%--                    <li class="@@invoiceactive"><a href="invoice.html">Invoice</a></li>--%>
<%--                    <li class="@@todoactive"><a href="todo-lists.html">Todo Lists</a></li>--%>
<%--                    <li class="@@notesactive"><a href="notes.html">Notes</a></li>--%>
<%--                    <li class="@@loginactive"><a href="login.html">Login & SignUp</a></li>--%>
<%--                    <li class="@@lockscreenactive"><a href="lockscreen.html">Lockscreen</a></li>--%>
<%--                    <li class="@@lockscreenactive"><a href="404.html">404</a></li>--%>
<%--                    <li class="@@emptyactive"><a href="empty.html">Empty Page</a></li>--%>
<%--                </ul>--%>
<%--            </li>--%>
        </ul>
    </div>
</aside>