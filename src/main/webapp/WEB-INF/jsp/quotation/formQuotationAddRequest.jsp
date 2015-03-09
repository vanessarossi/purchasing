<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title><fmt:message key="title.page.quotation" /> - <fmt:message key="title.purchasing" /></title>
    <html:head />
    <html:formAssets/>
</head>
<body>
<html:template>
    <div class="container-fluid">
        <div class="page-header">
            <h3><fmt:message key="title.quotation" /></h3>
        </div>
        <form action='<c:url value="/cotacao/salvar"></c:url>' method="post" id="addRequestQuotationForm">
            <div class="row">
                <div class="col-md-2 col-sm-2">
                    <div class="form-group">
                        <label  class="control-label" for="number"><fmt:message key="label.number"/></label>
                        <input type="text" class="form-control" id="number" readonly="true" value="${quotation.id}"/>
                    </div>
                </div>
                <div class="col-md-2 col-sm-2">
                    <div class="form-group">
                        <label  class="control-label" for="type"><fmt:message key="label.type"/></label>
                        <input type="text" class="form-control" id="type" readonly="true" value="${quotation.type.decriprion}"/>
                    </div>
                </div>
                <div class="col-md-4 col-sm-4">
                    <label  class="control-label" for="name"><fmt:message key="label.name"/></label>
                    <input type="text" class="form-control" id="name" readonly="true" value="${quotation.user.name}"/>
                </div>
                <div class="col-md-2 col-sm-2">
                    <div class="form-group">
                        <label  class="control-label" for="status"><fmt:message key="label.status"/></label>
                        <input type="text" class="form-control" id="status" readonly="true" value="${quotation.status}"/>
                    </div>
                </div>
            </div>

            <c:if test="${quotation.type == 'Material'}">
                <div  id="divMaterial" class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title"><fmt:message key="title.products" /></h3>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-1 col-sm-1">
                                <div class="form-group">
                                    <label class="control-label"  for="code"><fmt:message key="label.code"/></label>
                                    <input type="text" class="form-control" id="code" >
                                </div>
                            </div>
                            <div class="col-md-7 col-sm-7">
                                <div class="form-group">
                                    <label class="control-label" for="product"><fmt:message key="label.product"/></label>
                                    <div class="input-group">
                                        <input type="text" class="form-control" id="product" readonly>
                                        <span class="input-group-btn"><button class="btn btn-default" type="button" data-toggle="modal" data-target="#searchProduct"><span class="fa fa-search"></span></button></span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-1 col-sm-1">
                                <div class="form-group">
                                    <label class="control-label" for="quantity"><fmt:message key="label.abbreviatedQuantity"/></label>
                                    <input type="text" class="form-control" id="quantity" readonly>
                                </div>
                            </div>
                            <div class="col-md-2 col-sm-2">
                                <div class="form-group">
                                    <label class="control-label"><fmt:message key="label.unit"/></label>
                                    <input type="text" class="form-control" id="unit" readonly>
                                </div>
                            </div>
                            <div class="col-sm-offset-10 col-md-offset-10">
                                <div class="form-group">

                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-2 col-sm-2">
                                <div class="form-group">
                                    <label class="control-label"  for="solicitationCode"><fmt:message key="label.code"/></label>
                                    <input type="text" class="form-control" id="solicitationCode" >
                                </div>
                            </div>
                            <div>
                                <div class="form-group">
                                    <a class="btn btn-primary"><fmt:message key="button.search" /></a>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <table id="materialTable" class="table table-striped table-hover table-condensed">
                                <thead>
                                <tr>
                                    <th style="width: 3%"><fmt:message key="table.product" /></th>
                                    <th style="width: 10%" ><fmt:message key="table.quantity" /></th>
                                    <th style="width: 18%" ><fmt:message key="table.unit" /></th>
                                    <th style="width: 2%" ><fmt:message key="table.##" /></th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </c:if>

            <c:if test="${quotation.type == 'Service'}">
                <div  id="divService" class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title"><fmt:message key="title.services" /></h3>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-2 col-sm-2">
                                <div class="form-group">
                                    <label class="control-label"  for="solicitationServiceCode"><fmt:message key="label.code"/></label>
                                    <input type="text" class="form-control" id="solicitationServiceCode" >
                                </div>
                            </div>
                            <div>
                                <div class="form-group">
                                    <a class="btn btn-primary"><fmt:message key="button.search" /></a>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <table id="serviceTable" class="table table-striped table-hover table-condensed">
                                <thead>
                                <tr>
                                    <th style="width: 3%"><fmt:message key="table.solicitation" /></th>
                                    <th style="width: 20%" ><fmt:message key="table.type" /></th>
                                    <th style="width: 20%" ><fmt:message key="table.costCenter" /></th>
                                    <th style="width: 53%" ><fmt:message key="table.description" /></th>
                                    <th style="width: 2%" ><fmt:message key="table.##" /></th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </c:if>
        </form>
    </div>
</html:template>
</body>
<html:jsAssets/>
<script src="${pageContext.request.contextPath}/asset/js/custom/form-quotation.js"></script>
</html>
