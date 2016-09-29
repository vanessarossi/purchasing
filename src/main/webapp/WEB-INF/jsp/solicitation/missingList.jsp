<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title><fmt:message key="title.page.solicitation" /> - <fmt:message key="title.purchasing" /></title>
    <html:head />
    <html:tableAssets/>
</head>
<body>
<html:template>
    <div class="page-header">
            <h3><fmt:message key="title.solicitations" /></h3>
        </div>
    <br/><br/>
    <table id="solicitationTable" class="table table-striped table-hover table-condensed">
            <thead>
            <tr>
                <th><fmt:message key="table.code" /></th>
                <th><fmt:message key="table.initialDate" /></th>
                <th><fmt:message key="table.costCenter" /></th>
                <th><fmt:message key="table.user" /></th>
                <th><fmt:message key="table.status" /></th>
                <th><fmt:message key="table.##" /></th>
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
<script src="${pageContext.request.contextPath}/asset/js/custom/solicitation-missing.js"></script>
</html>

