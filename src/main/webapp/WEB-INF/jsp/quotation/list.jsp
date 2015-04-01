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
        <c:if test="${!empty errorQuotatioFinalized }">
            <div class="row">
                <div class="alert alert-danger alert-dismissible text-center" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong> <fmt:message key="label.atention" /> </strong> <span><fmt:message key="${errorQuotatioFinalized}"/></span>
                </div>
            </div>
        </c:if>
        <br/>
        <table id="quotationTable" class="table table-striped table-hover table-condensed">
            <thead>
                <tr>
                    <th style="width:2%"><fmt:message key="table.code"/></th>
                    <th style="width:10%"><fmt:message key="table.type"/></th>
                    <th style="width:30%"><fmt:message key="table.user"/></th>
                    <th style="width:10%"><fmt:message key="table.status"/></th>
                    <th style="width:15%"><fmt:message key="table.initialDate"/></th>
                    <th style="width:15%"><fmt:message key="table.finalDate"/></th>
                    <th style="width:2%"><fmt:message key="table.##"/></th>
                </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
</html:template>
</body>
<html:jsAssets/>
<html:tableJsAssets/>
<script src="${pageContext.request.contextPath}/asset/js/custom/quotation.js"></script>
</html>