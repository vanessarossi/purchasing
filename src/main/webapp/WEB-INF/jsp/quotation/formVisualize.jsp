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
        <div class="panel-group" id="accordion">
            <c:forEach items="${quotation.budgets}" var="budget" >
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#${budget.id}">
                                ${budget.supplier.person.name}
                            </a><i class="indicator fa fa-angle-double-up"></i>
                        </h4>
                    </div>
                    <div id="${budget.id}" class="panel-collapse collapse">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-2 col-sm-2">
                                    <div class="form-group">
                                        <label class="control-label" for="date"><fmt:message key="label.date"/></label>
                                        <input type="text" class="form-control" id="date" readonly="true" value="<fmt:formatDate value="${budget.date}" pattern="d/MM/YYYY"  />"/>
                                    </div>
                                </div>
                                <div class="col-md-2 col-sm-2">
                                    <div class="form-group">
                                        <label class="control-label" for="budgetNumber"><fmt:message key="label.number_budget"/></label>
                                        <input type="text" class="form-control" id="budgetNumber" readonly="true" value="${budget.numberBudget}"/>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-3 col-sm-3">
                                    <a class="btn btn-warning"
                                       href="<c:url value="/cotacao/editar/orcamento/${budget.id}"></c:url>"><fmt:message
                                            key="button.edit"/> </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</html:template>
</body>
<html:jsAssets/>
<script src="${pageContext.request.contextPath}/asset/js/custom/form-quotation-visualize.js"></script>
</html>
