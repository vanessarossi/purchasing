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
            <form class="form-signin" method="post" action="<c:url value="/login/autenticar" ></c:url>" id="loginForm">
                <span id="reauth-email" class="reauth-email"></span>
                <input type="text" name="user.username" class="form-control" placeholder="Usuário" autofocus="autofocus" data-rule-required="true" id="username" <c:if test="${userLogged.id != null}">readonly</c:if> value="${userLogged.username}" />
                <input type="password" id="password" name="user.password" class="form-control" placeholder="Senha" data-rule-required="true" <c:if test="${userLogged.id != null}">readonly</c:if>  />

                <span class="custom-error">${errors.from('message.not.blank.login')}</span>

                <c:if test="${! empty errorSign}">
                    <span class="custom-error"><fmt:message key="${errorSign}"/></span>
                </c:if>

                <c:if test="${userLogged.id == null}">
                    <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit"><fmt:message key="button.signIn" /></button>
                </c:if>

                <c:if test="${userLogged.id != null}">
                    <a class="btn btn-lg btn-success btn-block btn-signin" href="<c:url value="/home" ></c:url>">
                        <fmt:message key="button.return" />
                    </a>
                </c:if>
            </form>
            <c:if test="${userLogged.id == null}">
                <a href="<c:url value="/login/esqueceu/senha" ></c:url>" class="forgot-password">
                    <fmt:message key="link.forgot.password" />
                </a>
            </c:if>
        </div>
    </div>
</body>
<html:jsAssets/>
<script src="${pageContext.request.contextPath}/asset/js/custom/login.js"></script>
</html>