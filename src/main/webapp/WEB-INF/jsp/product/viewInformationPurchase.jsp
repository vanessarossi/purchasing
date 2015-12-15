<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title><fmt:message key="title.page.information.purchase"/> - <fmt:message key="title.purchasing"/></title>
    <html:head/>
    <html:tableAssets/>
</head>
<body>
<html:template>
    <div class="container-fluid">
        <div class="page-header">
            <h3><fmt:message key="title.information.purchase"/></h3>
        </div>
        <div class="row">
            <div class="col-sm-5 col-md-5">
                <div class="form-group">
                    <label class="control-label"><fmt:message key="label.code"></fmt:message></label>
                    <input type="text" class="form-control" id="code" name="product.id" value="${product.id}" readonly/>
                </div>
            </div>
            <div class="col-sm-5 col-md-5">
                <div class="form-group">
                    <label class="control-label"><fmt:message key="label.description"></fmt:message></label>
                    <input type="text" class="form-control" id="description" name="product.description" value="${product.description}" readonly/>
                </div>
            </div>
            <div class="col-sm-5 col-md-5">
                <div class="form-group">
                    <label class="control-label"><fmt:message key="label.model"></fmt:message></label>
                    <input type="text" class="form-control" id="model" name="product.model" value="${product.model}" readonly/>
                </div>
            </div>
        </div>
        <div class="row" >
            <div class="col-sm-4 col-md-4">
                <div class="form-group">
                    <label class="control-label"><fmt:message key="label.mark"></fmt:message></label>
                    <input type="text" class="form-control" id="mark" name="product.mark" value="${product.mark}" readonly/>
                </div>
            </div>
            <div class="col-sm-3 col-md-3">
                <div class="form-group">
                    <label class="control-label"><fmt:message key="label.unit"></fmt:message></label>
                    <input type="text" class="form-control" id="unit" name="product.unit.description" value="${product.unit.description}" readonly/>
                </div>
            </div>
            <div class="col-sm-4 col-md-4">
                <div class="form-group">
                    <label class="control-label"><fmt:message key="label.category"></fmt:message></label>
                    <input type="text" class="form-control" id="category" name="product.category.description" value="${product.category.description}" readonly/>
                </div>
            </div>
        </div>
        <table id="informationPurchaseTable" class="table table-striped table-hover table-condensed">
            <thead>
            <tr>
                <th><fmt:message key="table.date"/></th>
                <th><fmt:message key="table.supplier"/></th>
                <th><fmt:message key="table.quantity"/></th>
                <th><fmt:message key="table.price"/></th>
                <th><fmt:message key="table.delivered.quantity"/></th>
                <th><fmt:message key="table.costCenter"/></th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
        <br>
        <div class="row">
            <div class="col-sm-offset-11 col-md-offset-11">
                <a href="<c:url value="/produto" ></c:url>" class="btn btn-danger"> <fmt:message key="button.cancel"/> </a>
            </div>
        </div>
    </div>
</html:template>
</body>
<html:jsAssets/>
<html:tableJsAssets/>
<script src="${pageContext.request.contextPath}/asset/js/custom/information_purchase_product.js"></script>
</html>