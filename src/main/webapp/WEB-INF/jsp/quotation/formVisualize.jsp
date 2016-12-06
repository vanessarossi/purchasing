<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title><fmt:message key="title.page.quotation" /> - <fmt:message key="title.purchasing" /></title>
    <html:head />
    <html:formAssets/>
</head>
<body>
<html:template>
        <div class="page-header">
            <h2><fmt:message key="title.quotation" /> - <fmt:message key="title.view.details" /></h2>
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
    <!-- Informações -->
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
    <div class="row">
        <div class="col-md-6 col-sm-6">
            <div class="form-group">
                <label class="control-label" for="number"><fmt:message key="label.description"/></label>
                <input type="text" class="form-control" id="description" readonly="true" value="${quotation.description}"/>
            </div>
        </div>
    </div>
    <div class="row">
        <table id="budgetTable" class="table table-striped table-hover table-condensed">
            <thead>
            <tr>
                <th style="width: 35%"><fmt:message key="table.supplier"/></th>
                <th style="width: 7%"><fmt:message key="table.date"/></th>
                <th style="width: 10%"><fmt:message key="table.number"/> </th>
                <th style="width: 10%"><fmt:message key="table.abbreviated.total.price"/></th>
                <th style="width: 10%"><fmt:message key="table.abbreviated.total.final.price.first.payment.information"/></th>
                <th style="width: 10%"><fmt:message key="table.abbreviated.total.final.price.second.payment.information"/></th>
                <th style="width: 3%"><fmt:message key="table.##"/></th>
                <th style="width: 3%"><fmt:message key="table.##"/></th>
            </tr>
            </thead>
            <tbody>
                <c:forEach items="${quotation.budgets}" var="budget">
                    <tr>
                        <td>${budget.supplier.person.name}</td>
                        <td><fmt:formatDate value="${budget.date}" pattern="dd/MM/YYYY"/></td>
                        <td>${budget.numberBudget}</td>
                        <td>${fn:replace(budget.paymentInformationBudgets[0].paymentInformation.totalPrice,".",",")}</td>
                        <td>${fn:replace(budget.paymentInformationBudgets[0].paymentInformation.totalFinalPrice,".",",")}</td>
                        <td>${fn:replace(budget.paymentInformationBudgets[1].paymentInformation.totalFinalPrice,".",",")}</td>
                        <td><a href="<c:url value="/cotacao/editar/orcamento/${budget.id}"></c:url>"><span class="btn btn-default btn-xs fa fa-edit"></span></a></td>
                        <td><a onclick='viewDetailBudget(${budget.id},"${quotation.type}")'><span class="btn btn-default btn-xs fa fa-eye"></span></a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</html:template>
<div class="modal fade" id="priceBudget" tabindex="-1" role="dialog" aria-labelledby="modalPriceBudget" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"><fmt:message key="title.price.budget"/></h4>
            </div>
            <div class="modal-body">
                <c:if test="${quotation.type == 'Material'}">
                    <table id="bugetQuotationMaterialTable" class="table table-striped table-hover table-condensed">
                        <thead>
                        <tr>
                            <th style="width: 50%"><fmt:message key="table.product"/></th>
                            <th style="width: 5%"><fmt:message key="table.abbreviatedQuantity"/></th>
                            <th style="width: 10%"><fmt:message key="table.unit"/></th>
                            <th style="width: 10%"><fmt:message key="table.unitary.price"/></th>
                            <th style="width: 8%"><fmt:message key="table.total.price"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </c:if>
                <c:if test="${quotation.type == 'Service'}">
                    <table id="bugetQuotationServiceTable" class="table table-striped table-hover table-condensed">
                        <thead>
                        <tr>
                            <th style="width: 80%"><fmt:message key="table.description"/></th>
                            <th style="width: 10%"><fmt:message key="table.unitary.price"/></th>
                            <th style="width: 10%"><fmt:message key="table.total.price"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </c:if>
                <br><br><br>
            </div>
        </div>
    </div>
</div>
</body>
<html:jsAssets/>
<script src="${pageContext.request.contextPath}/asset/js/custom/form-quotation-visualize.js"></script>
</html>
