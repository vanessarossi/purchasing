<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title><fmt:message key="title.page.quotation" /> - <fmt:message key="title.purchasing" /></title>
    <html:head />
    <html:formAssets/>
</head>
<body>
<html:template>
    <br class="container-fluid">
        <div class="page-header">
            <h2><fmt:message key="title.quotation" /> - <fmt:message key="title.general.information" /></h2>
         </div>
        <c:if test="${quotation.id == null}">
            <form action='<c:url value="/cotacao/salvar"></c:url>' method="post" id="quotationForm">
                <div class="row">
                    <div class="col-sm-3 col-md-3">
                        <div class="form-group">
                            <label class="control-label"><fmt:message key="label.type"></fmt:message></label><span class="required"> *</span>
                            <select class="form-control" id="typeQuotation" name="quotation.type">
                                <c:forEach items="${types}" var="type">
                                    <option value="${type}" <c:if test="${quoation.type eq type}">selected</c:if> >${type.description} </option>
                                </c:forEach>
                            </select>
                            <span class="required">${errors.from('quotation.type')}</span>
                        </div>
                    </div>
                    <div>

                    </div>
                    </br>
                    <button type="submit" class="btn btn-success"><fmt:message key="button.save" /></button>
                </div>
            </form>
        </c:if>
        <c:if test="${quotation.id != null}">
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
            <c:if test="${quotation.type == 'Material'}">
                <c:if test="${quotation.status eq 'Open'}">
                    <a href="<c:url value="/cotacao/formulario/adicionar/${quotation.id}"></c:url>"  class="btn btn-primary" > <fmt:message key="button.addProduct"  /> </a>
                </c:if>
                </br></br>
                <table id="quotationMaterialTable" class="table table-striped table-hover table-condensed">
                    <thead>
                    <tr>
                        <th style="width: 50%"><fmt:message key="table.product" /></th>
                        <th style="width: 10%" ><fmt:message key="table.quantity" /></th>
                        <th style="width: 18%" ><fmt:message key="table.unit" /></th>
                        <th style="width: 2%" ><fmt:message key="table.##" /></th>
                        <th style="width: 2%" ><fmt:message key="table.##" /></th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
                <table id="quotationMaterialDetailTable" class="table table-striped table-hover table-condensed">
                    <thead>
                    <tr>
                        <th style="width: 3%"><fmt:message key="table.solicitation" /></th>
                        <th style="width: 50%"><fmt:message key="table.product" /></th>
                        <th style="width: 10%" ><fmt:message key="table.quantity" /></th>
                        <th style="width: 18%" ><fmt:message key="table.unit" /></th>
                        <th style="width: 2%" ><fmt:message key="table.##" /></th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${quotation.type == 'Service'}">
                <c:if test="${quotation.status eq 'Open'}">
                    <a href="<c:url value="/cotacao/formulario/adicionar/${quotation.id}"></c:url>"  class="btn btn-primary" > <fmt:message key="button.addService"  /> </a>
                </c:if>
                    </br></br>
                <table id="quotationServiceTable" class="table table-striped table-hover table-condensed">
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
            </c:if>

        <form action='<c:url value="/cotacao/salvar/observation"></c:url>' method="post" id="quotationForm">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><fmt:message key="title.observation.quotation"/></h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-sm-12 col-md-12">
                            <div class="form-group">
                                <label class="control-label"><fmt:message key="title.observation"/></label>
                                <input type="hidden" name="quotation.id" value="${quotation.id}">
                                <textarea rows="4" cols="100" class="form-control"  name="quotation.observation" >${quotation.observation}</textarea>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel-footer">
                    <c:if test="${quotation.status eq 'Open'}">
                        <div class="col-sm-offset-11 col-md-offset-11">
                            <button type="submit" class="btn btn-success"><fmt:message key="button.save"/></button>
                        </div>
                    </c:if>
                </div>
            </div>
        </form>
            <div class="col-sm-offset-8 col-md-offset-9">
                <a href="<c:url value="/cotacao/imprimir/pedido/orcamento/${quotation.id}"></c:url>" target='_blank'> <span class="fa fa-print btn btn-default"> <fmt:message key="button.generateOrderBudget" /></span></a>
            </div>
            </br>
       </c:if>
    </div>
</html:template>
</body>
<html:notification/>
<html:jsAssets/>
<c:if test="${quotation.type == 'Material'}">
    <script src="${pageContext.request.contextPath}/asset/js/custom/list_request_quotation_material.js"></script>
</c:if>
<c:if test="${quotation.type == 'Service'}">
    <script src="${pageContext.request.contextPath}/asset/js/custom/list_request_quotation_service.js"></script>
</c:if>
</html>
