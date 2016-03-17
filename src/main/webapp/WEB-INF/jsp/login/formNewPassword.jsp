<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title><fmt:message key="title.page.sign" /> - <fmt:message key="title.purchasing" /></title>
    <html:head />
    <link href="${pageContext.request.contextPath}/asset/css/login.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="card card-container">
        <img id="profile-img" class="profile-img-card" src="${pageContext.request.contextPath}/asset/images/logo/logo_compras.png" />
        <p id="profile-name" class="profile-name-card"></p>
        <form class="form-signin" method="post" action="<c:url value="/login/nova/senha" ></c:url>" id="loginForm">
            <span id="reauth-email" class="reauth-email"></span>
            <input type="text" name="user.email" id="email" class="form-control" placeholder="Por favor, inclua seu e-mail." data-rule-required="true" data-rule-email="true" data-msg-required="Por favor, inclua seu e-mail." />
            <span class="custom-error">${errors.from('message.not.blank.email')}</span>
            <c:if test="${! empty sendEmail}">
                <span class="custom-error"><fmt:message key="${sendEmail}"/></span>
            </c:if>
            <button class="btn btn-lg btn-success btn-block btn-signin" type="submit"><fmt:message key="button.signIn" /></button>
            <a href="<c:url value="/login" ></c:url>" class="submit btn btn-warning btn-block"><span><fmt:message key="button.cancel"/></span></a>
        </form>
    </div>
</div>
</body>
<html:jsAssets/>
<script src="${pageContext.request.contextPath}/asset/js/custom/login.js"></script>
</html>