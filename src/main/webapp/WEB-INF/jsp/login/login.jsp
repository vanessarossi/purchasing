<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title><fmt:message key="title.page.sign" /> - <fmt:message key="title.purchasing" /></title>
    <html:head />
    <link href="${pageContext.request.contextPath}/asset/css/custom/login.css" rel="stylesheet">
</head>
<body class="login">
<div class="logo">
    <img alt='logoEmpresa' src='<c:url value="/asset/images/logo/logo_compras.png"></c:url>' width="15%">
</div>
<div class="box">
    <div class="content">
        <form class="form-vertical login-form form" id="login" method="post" action='<c:url value="login/autenticar" ></c:url>'>
            <h3 class="form-title"><fmt:message key="title.sign" /> </h3>
            <div class="form-group">
                <div class="input-icon">
                    <input type="text" name="user.username" class="form-control" placeholder="Usuário" autofocus="autofocus" data-rule-required="true" id="username" />
                </div>
            </div>
            <div class="form-group">
                <div class="input-icon">
                    <input type="password" name="user.password" class="form-control" placeholder="Senha" data-rule-required="true" id="password" />
                </div>
            </div>
            <div class="form-group ">
                <span class="messageNullLogin redText"></span>
            </div>
            <div class="form-actions">
                <a onclick="submitLogin()" class="submit btn btn-success pull-right">
                    <fmt:message key="button.signIn" /> <i class="icon-angle-right"></i>
                </a>
            </div>
            <br>
            <br>
            <c:if test="${! empty errorSign}">
                <div class="form-group">
                    <span class="redText"><fmt:message key="${errorSign}"/></span>
                </div>
            </c:if>
        </form>
    </div>
    <div class="inner-box">
        <div class="content">
            <i class="icon-remove close hide-default"></i>
            <a href="#" class="forgot-password-link"><fmt:message key="link.forgotPassword" /></a>
            <form class="form-vertical forgot-password-form hide-default" id="newPassword" method="post" action='<c:url value="login/nova/senha" ></c:url>'>
                <div class="form-group">
                    <div class="input-icon">
                        <i class="icon-envelope"></i>
                        <input type="text" name="user.email" id="email" class="form-control" placeholder="Adicione seu e-mail" data-rule-required="true" data-rule-email="true" data-msg-required="Please enter your email." />
                    </div>
                </div>
                <div class="form-group ">
                    <span class="messageNullNewPassword redText"></span>
                </div>
                <a onclick="submitNewPassword()" class="submit btn btn-default btn-block">
                    <fmt:message key="button.newPassword"/>
                </a>
                <a onclick="returnLogin()"><span><fmt:message key="link.return"/></span></a>
            </form>
            <div class="forgot-password-done hide-default">
                <i class="icon-ok success-icon"></i>
                <span><fmt:message key="message.sentEmail"/></span>
                <a onclick="returnLogin()"><span><fmt:message key="link.return"/></span></a>
            </div>
        </div>
    </div>
</div>
</body>
<html:jsAssets/>
<script src="${pageContext.request.contextPath}/asset/js/custom/login.js"></script>
</html>