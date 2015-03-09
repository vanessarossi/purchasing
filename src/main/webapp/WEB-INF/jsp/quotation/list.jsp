<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title><fmt:message key="title.page.quotation" /> - <fmt:message key="title.purchasing" /></title>
    <html:head />
    <html:tableAssets/>
</head>
<body>
<html:template>
    <div class="container-fluid">
        <div class="page-header">
            <h3><fmt:message key="title.quotations" /></h3>
        </div>
        <a   href="<c:url value="/cotacao/formulario" ></c:url>" type="button" class="btn btn-primary">
            <fmt:message key="button.newQuotation"/>
        </a>
        <br/><br/>

    </div>
    
</html:template>
</body>
<html:notification/>
<html:jsAssets/>
<html:tableJsAssets/>
<script src="${pageContext.request.contextPath}/asset/js/custom/quotation.js"></script>
</html>