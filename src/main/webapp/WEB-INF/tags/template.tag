<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@tag pageEncoding="UTF-8" %>
<!--[if lt IE 9]><p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgradeyour browser</a> to improve your experience.</p><![endif]-->
<nav class="top-bar" data-topbar role="navigation">
    <ul class="title-area">
        <li class="name">
            <h1><a href="#">My Site</a></h1>
        </li>
        <!-- Remove the class "menu-icon" to get rid of menu icon. Take out "Menu" to just have icon alone -->
        <li class="toggle-topbar menu-icon">
            <a href="#"><span>Menu</span></a>
        </li>
    </ul>

    <section class="top-bar-section"> <!-- Right Nav Section -->
        <ul class="right">
            <li class="active">
                <a href="#">Right Button Active</a>
            </li>
            <li class="has-dropdown">
                <a href="#">Right Button Dropdown</a>
                <ul class="dropdown">
                    <li>
                        <a href="#">First link in dropdown</a>
                    </li>
                    <li>
                        <a href="#">Active link in dropdown</a>
                    </li>
                </ul>
            </li>
        </ul> <!-- Left Nav Section -->
        <ul class="left">
            <li>
                <a href="#">Left Nav Button</a>
            </li>
        </ul>
    </section>
</nav>
<a class="exit-off-canvas"></a>
<section class="main-section">
    <jsp:doBody/>
</section>
