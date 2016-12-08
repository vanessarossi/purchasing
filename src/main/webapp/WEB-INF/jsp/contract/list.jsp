<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title><fmt:message key="title.page.contract" /> - <fmt:message key="title.purchasing" /></title>
    <html:head />
    <html:tableAssets/>
</head>
<body>
<html:template>
    <div class="page-header">
        <h3><fmt:message key="title.contracts" /></h3>
    </div>
    <a   href="<c:url value="/contrato/formulario" ></c:url>" type="button" class="btn btn-primary">
        <fmt:message key="button.newContract"/>
    </a>
    <br/><br/>
    <br/>
    <table id="contractTable" class="table table-striped table-hover table-condensed">
        <thead>
        <tr>
            <th><fmt:message key="table.initialDate"/></th>
            <th><fmt:message key="table.finalDate"/></th>
            <th><fmt:message key="table.supplier"/></th>
            <th><fmt:message key="table.##"/></th>
            <th><fmt:message key="table.##"/></th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</html:template>
</body>
<html:notification/>
<html:jsAssets/>
<html:tableJsAssets/>
<script src="${pageContext.request.contextPath}/asset/js/custom/contract.js"></script>
</html>