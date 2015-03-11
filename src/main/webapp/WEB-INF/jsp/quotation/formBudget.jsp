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
            <h2><fmt:message key="title.quotation" /> - <fmt:message key="title.add.budget" /></h2>
        </div>
        <div class="panel-body well well-sm">
            <div class="col-md-3 col-sm-3">
                <a class="btn btn-default" href="<c:url value="/cotacao/editar/${quotation.id}"></c:url>"><fmt:message key="button.menu.quotation" /> </a>
            </div>
            <div class="col-md-3 col-sm-3">
                <a class="btn btn-default" href="<c:url value="/cotacao/formulario/adicionar/orcamento/${quotation.id}"></c:url>"><fmt:message key="button.menu.add.budget" /> </a>
            </div>
            <div class="col-md-3 col-sm-3">
                <a class="btn btn-default" href="<c:url value="/cotacao/formulario/visualizar/detalhes/${quotation.id}"></c:url>"><fmt:message key="button.menu.viewDetails" /> </a>
            </div>
            <div class="col-md-3 col-sm-3">
                <a class="btn btn-default" href="<c:url value="/cotacao/formulario/iniciar/ordem/${quotation.id}"></c:url>"><fmt:message key="button.menu.init.purchase.order" /> </a>
            </div>
        </div>
        <form action='<c:url value="/cotacao/adicionar/orcamento"></c:url>' method="post" id="budgetForm">
            </br>
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
            <div class="row">
                <div class="col-md-1 col-sm-1">
                    <div class="form-group">
                        <label class="control-label"  for="code"><fmt:message key="label.code"/></label>
                        <input type="text" class="form-control" id="code" name="budget.supplier.id" >
                    </div>
                </div>
                <div class="col-md-6 col-sm-6">
                    <div class="form-group">
                        <label class="control-label" for="supplier"><fmt:message key="label.supplier"/></label>
                        <div class="input-group">
                            <input type="text" class="form-control" id="supplier" readonly>
                            <span class="input-group-btn"><button class="btn btn-default" type="button" data-toggle="modal" data-target="#searchSupplier"><span class="fa fa-search"></span></button></span>
                        </div>
                    </div>
                </div>
                <div class="col-md-2 col-sm-2">
                    <div class="form-group">
                        <label class="control-label" for="date"><fmt:message key="label.date"/></label>
                        <input type="text" class="form-control date" id="date" name="budget.date">
                    </div>
                </div>
                <div class="col-md-2 col-sm-2">
                    <div class="form-group">
                        <label class="control-label" for="number_budget"  ><fmt:message key="label.number_budget"/></label>
                        <input type="text" class="form-control" id="number_budget" name="budget.numberBudget">
                    </div>
                </div>
            </div>
            <c:if test="${quotation.type == 'Material'}">
                <table id="budgetMaterialTable" class="table table-striped table-hover table-condensed">
                    <thead>
                    <tr>
                        <th style="width: 50%"><fmt:message key="table.product" /></th>
                        <th style="width: 10%" ><fmt:message key="table.quantity" /></th>
                        <th style="width: 10%" ><fmt:message key="table.unit" /></th>
                        <th style="width: 10%" ><fmt:message key="table.unitary.price" /></th>
                        <th style="width: 10%" ><fmt:message key="table.total.price##" /></th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${quotationRequests}" var="quotationRequest" varStatus="i">
                            <tr>
                                <td>${quotationRequest.product.description} ${quotationRequest.product.model} ${quotationRequest.product.mark}</td>
                                <td>${quotationRequest.quantity}</td>
                                <td>${quotationRequest.product.unit.description}</td>
                                <td>
                                    <input type="hidden"  name="budget.budgetQuotation[${i.index}].quotationRequest.id" value="${quotationRequest.id}">
                                    <input type="text"  name="budget.budgetQuotation[${i.index}].unityPrice">
                                </td>
                                <td><input type="text" id="totalPrice${i.index}" readonly></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${quotation.type == 'Service'}">
                <table id="quotationServiceDetailTable" class="table table-striped table-hover table-condensed">
                    <thead>
                    <tr>
                        <th style="width: 80%" ><fmt:message key="table.description" /></th>
                        <th style="width: 10%" ><fmt:message key="table.##" /></th>
                        <th style="width: 10%" ><fmt:message key="table.##" /></th>
                    </thead>
                    <tbody>
                        <c:forEach items="${quotationRequests}" var="quotationRequest" varStatus="i">
                            <tr>
                                <td>${quotationRequest.description}</td>
                                <td>
                                    <input type="hidden"  name="budget.budgetQuotation[${i.index}].quotationRequest.id" value="${quotationRequest.id}">
                                    <input type="text"  name="budget.budgetQuotation[${i.index}].unityPrice">
                                </td>
                                <td><input type="text" id="totalPrice${i.index}" readonly></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </form>
    </div>
</html:template>
</body>
<html:searchSupplier/>
<html:jsAssets/>
<script src="${pageContext.request.contextPath}/asset/js/custom/form-quotation-budget.js"></script>
<script src="${pageContext.request.contextPath}/asset/js/custom/modalSearchSupplier.js"></script>
<script src="${pageContext.request.contextPath}/asset/js/vendor/jquery.mask.min.js"></script>
<script src="${pageContext.request.contextPath}/asset/js/commons/formCommons.js"></script>
<script src="${pageContext.request.contextPath}/asset/js/vendor/price_format.2.0.min.js"></script>
</html>
