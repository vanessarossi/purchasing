<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title><fmt:message key="title.page.budget" /> - <fmt:message key="title.purchasing" /></title>
    <html:head />
    <html:formAssets/>
</head>
<body>
<html:template>
    <div class="container-fluid">
        <div class="page-header">
            <h3><fmt:message key="title.quotation" /></h3>
        </div>
        <form action='<c:url value="/cotacao/adicionar/orcamento"></c:url>' method="post" id="budgetForm">

        </form>
    </div>
</html:template>
</body>
<html:jsAssets/>
<script src="${pageContext.request.contextPath}/asset/js/custom/form-quotation.js"></script>
</html>
