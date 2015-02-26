<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title><fmt:message key="title.page.solicitation" /> - <fmt:message key="title.purchasing" /></title>
    <html:head />
    <html:formAssets/>
</head>
<body>
<html:template>
    <div class="container-fluid">
        <div class="page-header">
            <h3><fmt:message key="title.solicitations" /></h3>
        </div>
        <br/><br/>
        <table id="solicitationTable" class="table table-striped table-hover table-condensed">
            <thead>
            <tr>
                <th style="width: 3%"><fmt:message key="table.code" /></th>
                <th style="width: 10%" ><fmt:message key="table.initialDate" /></th>
                <th style="width: 18%" ><fmt:message key="table.costCenter" /></th>
                <th style="width: 22%"><fmt:message key="table.user" /></th>
                <th style="width: 18%"><fmt:message key="table.status" /></th>
                <th style="width: 2%" ><fmt:message key="table.##" /></th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
</html:template>
</body>
<html:jsAssets/>
<script src="${pageContext.request.contextPath}/asset/js/custom/solicitationMissing.js"></script>
</html>

