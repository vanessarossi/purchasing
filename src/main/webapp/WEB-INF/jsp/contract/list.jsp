<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title><fmt:message key="title.page.contract" /> - <fmt:message key="title.purchasing" /></title>
    <html:head />
    <html:tableAssets/>
</head>
<body>
<html:template>
    <div class="container-fluid">
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
                <th style="width: 10%" ><fmt:message key="table.initialDate" /></th>
                <th style="width: 10%" ><fmt:message key="table.finalDate" /></th>
                <th style="width: 50%" ><fmt:message key="table.supplier" /></th>
                <th style="width: 2%" ><fmt:message key="table.##" /></th>
                <th style="width: 2%" ><fmt:message key="table.##" /></th>
                <th style="width: 2%" ><fmt:message key="table.##" /></th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
</html:template>
</body>
<html:notification/>
<html:jsAssets/>
<html:tableJsAssets/>
<script src="${pageContext.request.contextPath}/asset/js/custom/contract.js"></script>
</html>