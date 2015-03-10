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
                        <input type="text" class="form-control" id="type" readonly="true" value="${quotation.type.description}"/>
                    </div>
                </div>
                <div class="col-md-4 col-sm-4">
                    <label  class="control-label" for="name"><fmt:message key="label.name"/></label>
                    <input type="text" class="form-control" id="name" readonly="true" value="${quotation.user.name}"/>
                </div>
                <div class="col-md-2 col-sm-2">
                    <div class="form-group">
                        <label  class="control-label" for="status"><fmt:message key="label.status"/></label>
                        <input type="text" class="form-control" id="status" readonly="true" value="${quotation.status.description}"/>
                    </div>
                </div>
        </div>
        <form action='<c:url value="/cotacao/salvar"></c:url>' method="post" id="addRequestQuotationMaterialForm">
            <c:if test="${quotation.type == 'Material'}">
                <div  id="divMaterial">
                    <h3><fmt:message key="title.products" /></h3>
                    <div class="row">
                        <div class="col-md-1 col-sm-1">
                            <div class="form-group">
                                <label class="control-label"  for="code"><fmt:message key="label.code"/></label>
                                <input type="text" class="form-control" id="code" >
                            </div>
                        </div>
                        <div class="col-md-7 col-sm-6">
                            <div class="form-group">
                                <label class="control-label" for="product"><fmt:message key="label.product"/></label>
                                <div class="input-group">
                                    <input type="text" class="form-control" id="product" readonly>
                                    <span class="input-group-btn"><button class="btn btn-default" type="button" data-toggle="modal" data-target="#searchProduct"><span class="fa fa-search"></span></button></span>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-2 col-sm-2">
                            <div class="form-group">
                                <label class="control-label"><fmt:message key="label.unit"/></label>
                                <input type="text" class="form-control" id="unit" readonly>
                            </div>
                        </div>
                        <div class="col-sm-offset-9 col-md-offset-10">
                                <div class="form-group">
                                    <br>
                                    <a class="btn btn-primary" onclick="searchProductByProduct()"><fmt:message key="button.search" /></a>
                                </div>
                            </div>
                    </div>
                    <div class="row">
                        <div class="col-md-2 col-sm-2">
                            <div class="form-group">
                                <label class="control-label"  for="solicitationCode"><fmt:message key="label.solicitationCode"/></label>
                                <input type="text" class="form-control" id="solicitationCode" >
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <br>
                                <a class="btn btn-primary" onclick="searchProductBySolicitation()"><fmt:message key="button.search" /></a>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <table id="materialTable" class="table table-striped table-hover table-condensed">
                            <thead>
                            <tr>
                                <th style="width: 3%"><fmt:message key="table.solicitation" /></th>
                                <th style="width: 15%"><fmt:message key="table.costCenter" /></th>
                                <th style="width: 50%" ><fmt:message key="table.product" /></th>
                                <th style="width: 10%" ><fmt:message key="table.quantity" /></th>
                                <th style="width: 10%" ><fmt:message key="table.unit" /></th>
                                <th style="width: 2%" ><fmt:message key="table.##" /></th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                    <div class="row">
                        <div class="col-sm-offset-10 col-md-offset-10">
                            <div class="form-group">
                                <input type="hidden" name="quotation.id" value="${quotation.id}" >
                                <button type="submit" class="btn btn-success"><fmt:message key="button.save" /></button>
                                <a href='<c:url value="/cotacao"></c:url>' type="button" class="btn btn-danger"><fmt:message key="button.cancel"/></a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
        </form>
        <form action='<c:url value="/cotacao/salvar"></c:url>' method="post" id="addRequestQuotationServiceForm">
            <c:if test="${quotation.type == 'Service'}">
                <div  id="divService">
                    <h3><fmt:message key="title.services" /></h3>
                    <div class="row">
                            <div class="col-md-2 col-sm-2">
                                <div class="form-group">
                                    <label class="control-label"  for="solicitationServiceCode"><fmt:message key="label.solicitationCode"/></label>
                                    <input type="text" class="form-control" id="solicitationServiceCode" >
                                </div>
                            </div>
                            <div>
                                <div class="form-group">
                                    <br>
                                    <a class="btn btn-primary" onclick="searchServiceBySolicitation()"><fmt:message key="button.search" /></a>
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
                    <div class="row">
                        <div class="col-sm-offset-10 col-md-offset-10">
                            <div class="form-group">
                                <input type="hidden" name="quotation.id" value="${quotation.id}" >
                                <button type="submit" class="btn btn-success"><fmt:message key="button.save" /></button>
                                <a href='<c:url value="/cotacao"></c:url>' type="button" class="btn btn-danger"><fmt:message key="button.cancel"/></a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
        </form>
    </div>
</html:template>
</body>
<html:jsAssets/>
<html:searchProduct/>
<script src="${pageContext.request.contextPath}/asset/js/custom/form-quotation.js"></script>
<script src="${pageContext.request.contextPath}/asset/js/custom/modalSearchProduct.js"></script>
</html>
