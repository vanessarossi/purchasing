<!DOCTYPE html>
<html lang="pt-br">
<head>
  <title><fmt:message key="title.page.sign" /> - <fmt:message key="title.purchasing" /></title>
  <html:head />
  <link href="${pageContext.request.contextPath}/asset/css/login.css" rel="stylesheet">
</head>
<body class="login">
<div class="logo">
  <img alt='logoEmpresa' src='<c:url value="/asset/images/logo/logo_compras.png"></c:url>' width="15%">
</div>
<div class="box">
  <div class="content">
    <form class="form-vertical login-form form" id="login" method="post" action='<c:url value="/login/confirma/nova/senha" ></c:url>'>
      <h3 class="form-title"><fmt:message key="title.perfil" /> </h3>
      <div class="form-group">
        <div class="input-icon">
          <input type="text" name="user.username" class="form-control"  readonly value="${user.username}"  data-rule-required="true" id="username" />
        </div>
      </div>
      <div class="form-group">
        <div class="input-icon">
          <input type="text" class="form-control"  readonly value="${user.role.description}"  data-rule-required="true" id="role" />
        </div>
      </div>
      <div class="form-group">
        <div class="input-icon">
          <input type="password" name="user.password" class="form-control" placeholder="Senha" data-rule-required="true" id="password" required />
        </div>
      </div>
      <input type="hidden" name="user.id" value="${user.id}">
      <input type="submit" class="submit btn btn-default btn-block" value="<fmt:message key="button.send"/>" />
      <a href="<c:url value="/home" ></c:url>" class="submit btn btn-warning btn-block"><span><fmt:message key="button.cancel"/></span></a>
      <br>
    </form>
  </div>
</div>
</body>
<html:jsAssets/>
</html>
