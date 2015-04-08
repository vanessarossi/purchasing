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
            <h2><fmt:message key="title.quotation" /> - <fmt:message key="title.start.purchase.order" /></h2>
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
        <div class="row">
            <div class="col-md-2 col-sm-2">
                <div class="form-group">
                    <label class="control-label" for="number"><fmt:message key="label.number"/></label>
                    <input type="text" class="form-control" id="number" readonly="true" value="${quotation.id}"/>
                </div>
            </div>
            <div class="col-md-2 col-sm-2">
                <div class="form-group">
                    <label class="control-label" for="type"><fmt:message key="label.type"/></label>
                    <input type="text" class="form-control" id="type" readonly="true"
                           value="${quotation.type.description}"/>
                </div>
            </div>
            <div class="col-md-4 col-sm-4">
                <label class="control-label" for="name"><fmt:message key="label.name"/></label>
                <input type="text" class="form-control" id="name" readonly="true" value="${quotation.user.name}"/>
            </div>
            <div class="col-md-2 col-sm-2">
                <div class="form-group">
                    <label class="control-label" for="status"><fmt:message key="label.status"/></label>
                    <input type="text" class="form-control" id="status" readonly="true"
                           value="${quotation.status.description}"/>
                </div>
            </div>
        </div>
        <form action='<c:url value="/ordemCompra/salvar"></c:url>' method="post" id="initialPurchasingOrderForm">
            <c:forEach items="${budgets}" var="budget" varStatus="i">
                <c:if test="${quotation.type == 'Material'}">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-4 col-sm-4">
                                    <div class="form-group">
                                        <label class="control-label" ><fmt:message key="label.supplier"/></label>
                                        <input type="text" class="form-control"  readonly="true" value="${budget.budget.supplier.person.name}"/>
                                    </div>
                                </div>
                                <div class="col-md-2 col-sm-2">
                                    <div class="form-group">
                                        <label class="control-label" ><fmt:message key="label.date"/></label>
                                        <input type="text" class="form-control"  readonly="true" value="<fmt:formatDate value="${budget.budget.date}" pattern="dd/MM/YYYY" />"/>
                                    </div>
                                </div>
                                <div class="col-md-2 col-sm-2">
                                    <div class="form-group">
                                        <label class="control-label"><fmt:message key="label.number_budget"/></label>
                                        <input type="text" class="form-control" readonly="true" value="${budget.budget.numberBudget}"/>
                                    </div>
                                </div>
                            </div>
                            <table id="budget${i.index}" class="table table-striped table-hover table-condensed">
                                <thead>
                                    <tr>
                                        <th style="width: 50%"><fmt:message key="table.product"/></th>
                                        <th style="width: 10%"><fmt:message key="table.abbreviatedQuantity"/></th>
                                        <th style="width: 10%"><fmt:message key="table.unit"/></th>
                                        <th style="width: 10%"><fmt:message key="table.unitary.price"/></th>
                                        <th style="width: 10%"><fmt:message key="table.total.price"/></th>
                                        <th style="width: 1%"><fmt:message key="table.##"/></th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${budget.budgetsQuotationProduct}" var="budgetsQuotationProduct" varStatus="j">
                                    <tr>
                                        <td>${budgetsQuotationProduct.product.description} ${budgetsQuotationProduct.product.model} ${budgetsQuotationProduct.product.mark}</td>
                                        <td>${budgetsQuotationProduct.quantity}</td>
                                        <td>${budgetsQuotationProduct.product.unit.description}</td>
                                        <td>${fn:replace(budgetsQuotationProduct.unityPrice,"." ,"," )}</td>
                                        <td><input type="text" id="total${i.index}${j.index}" readonly value="${fn:replace(budgetsQuotationProduct.totalPrice,"." , ",")}"/></td>
                                        <td>
                                            <input type="hidden" name="purchaseOrder.orderRequests[${i.index}].budgetQuotation.budget.id" value="${budget.budget.id}">
                                            <input type="hidden" name="purchaseOrder.orderRequests[${i.index}].budgetQuotation.quotationRequest.solicitationRequest.product.id" value="${budgetsQuotationProduct.product.id}" >
                                            <input type="checkbox" id="request${i.index}${budgetsQuotationProduct.product.id}" name="purchaseOrder.orderRequests[${i.index}].budgetQuotation.chosenBudget" class="${i.index}${j.index}"  onclick="checkedRequest(${i.index},${budgetsQuotationProduct.product.id})" value=""/>
                                        </td>
                                    </tr>
                                </c:forEach>
                                    <input type="hidden" id="totalItem" value="${fn:length(budget.budgetsQuotationProduct)}" />
                                </tbody>
                            </table>
                            <div class="row">
                                <div class="col-md-2 col-sm-2 col-md-offset-10 col-sm-offset-10">
                                    <div class="form-group">
                                        <label class="control-label"><fmt:message key="label.totalPrice"/></label>
                                        <input type="text" class="form-control" readonly="true" id="totalPrice${i.index}" value=""/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="panel-footer">
                            <div class="row">
                                <div class="col-md-2 col-sm-2 col-md-offset-10 col-sm-offset-10">
                                    <a  href="<c:url value="/ordemCompra/salvar/unico/${budget.budget.id}"></c:url>" class="btn btn-primary btn-sm"  > <fmt:message key="button.generatePurchaseOrder"/> </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${quotation.type == 'Service'}">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-4 col-sm-4">
                                    <div class="form-group">
                                        <label class="control-label" ><fmt:message key="label.supplier"/></label>
                                        <input type="text" class="form-control"  readonly="true" value="${budget.budget.supplier.person.name}"/>
                                    </div>
                                </div>
                                <div class="col-md-2 col-sm-2">
                                    <div class="form-group">
                                        <label class="control-label" ><fmt:message key="label.date"/></label>
                                        <input type="text" class="form-control"  readonly="true" value="<fmt:formatDate value="${budget.budget.date}" pattern="dd/MM/YYYY" />"/>
                                    </div>
                                </div>
                                <div class="col-md-2 col-sm-2">
                                    <div class="form-group">
                                        <label class="control-label"><fmt:message key="label.number_budget"/></label>
                                        <input type="text" class="form-control"  readonly="true" value="${budget.budget.numberBudget}"/>
                                    </div>
                                </div>
                            </div>
                            <table id="budget${i.index}" class="table table-striped table-hover table-condensed">
                                <thead>
                                <tr>
                                    <th style="width: 50%"><fmt:message key="table.description"/></th>
                                    <th style="width: 10%"><fmt:message key="table.unitary.price"/></th>
                                    <th style="width: 10%"><fmt:message key="table.total.price"/></th>
                                    <th style="width: 1%"><fmt:message key="table.##"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${budget.budgetsQuotationService}" var="budgetsQuotationService" varStatus="j" >
                                    <tr>
                                        <td>${budgetsQuotationService.service.description}</td>
                                        <td>${fn:replace(budgetsQuotationService.unityPrice, ".",",")}</td>
                                        <td><input type="text" readonly id="total${i.index}${j.index}" value="${fn:replace(budgetsQuotationService.unityPrice,"." ,"," )}"/></td>
                                        <td>
                                            <input type="hidden" name="purchaseOrder.orderRequests[${i.index}].budgetQuotation.budget.id" value="${budget.budget.id}">
                                            <input type="hidden" name="purchaseOrder.orderRequests[${i.index}].budgetQuotation.id" value="${budgetsQuotationService.id}">
                                            <input type="checkbox"  id="request${i.index}${budgetsQuotationService.service.id}" name="purchaseOrder.orderRequests[${i.index}].budgetQuotation.chosenBudget"  class="${i.index}${j.index}"  onclick="checkedRequest(${i.index},${budgetsQuotationService.service.id})" value=""/>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <input type="hidden" id="totalItem" value="${fn:length(budget.budgetsQuotationService)}"/>
                                </tbody>
                            </table>
                            <div class="row">
                                <div class="col-md-2 col-sm-2 col-md-offset-10 col-sm-offset-10">
                                    <div class="form-group">
                                        <label class="control-label"><fmt:message key="label.totalPrice"/></label>
                                        <input type="text" class="form-control" readonly="true" value=""/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="panel-footer">
                            <div class="row">
                                <div class="col-md-2 col-sm-2 col-md-offset-10 col-sm-offset-10">
                                    <a href="<c:url value="/ordemCompra/salvar/unico/${budget.budget.id}"></c:url>" class="btn btn-primary btn-sm"> <fmt:message key="button.generatePurchaseOrder"/> </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
            <input type="hidden" id="totalBudget" value="${fn:length(budgets)}">
            <div class="row">
                <div class="col-md-offset-10 col-sm-offset-10">
                    <input type="submit" class="btn btn-primary" value="<fmt:message key="button.generatePurchaseOrders"/> "/>
                </div>
            </div>
        </form>
    </div>
</html:template>
</body>
<html:jsAssets/>
<script src="${pageContext.request.contextPath}/asset/js/custom/form-quotation-purchase-order.js"></script>
</html>