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
      <form class="form-signin" method="post" action="<c:url value="/login/confirma/nova/senha" ></c:url>" id="loginForm">
        <span id="reauth-email" class="reauth-email"></span>
        <input type="text" id="username" name="user.username" class="form-control"   value="${user.username}"  data-rule-required="true" readonly />
        <input type="text" id="role"  class="form-control"  value="${user.role.description}"  data-rule-required="true" readonly />
        <input type="password" id="password"  name="user.password" class="form-control" placeholder="Senha" data-rule-required="true" required />
        <input type="hidden" name="user.id" value="${user.id}">
        <input type="submit" class="submit btn btn-success btn-block" value="<fmt:message key="button.send"/>" />
        <a href="<c:url value="/home" ></c:url>" class="submit btn btn-warning btn-block"><span><fmt:message key="button.cancel"/></span></a>
      </form>
  </div>
</div>
</body>
<html:jsAssets/>
</html>
