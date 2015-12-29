<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title><fmt:message key="title.page.home" /> - <fmt:message key="title.purchasing" /></title>
    <html:head />
</head>
<body>
<html:template>
    <c:if test="${environment.isDevelopment()}">
        <a style="color: #ff2f2e; font-weight: bold ; text-align: center"><fmt:message key="alert.test.datatase"/></a>
    </c:if>
    <br/><br/>
    <div class="row">
        <div class="col-xs-offset-4 col-sm-offset-4 col-md-offset-4 col-lg-offset-4">
            <img src='<c:url value="/asset/images/logo/logo_compras.png"></c:url>' width="50%">
        </div>
    </div>

</html:template>
</body>
<html:jsAssets/>
</html>