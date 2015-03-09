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
        <h2><fmt:message key="title.quotation" /></h2>
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
                    </br>
                    <button type="submit" class="btn btn-success"><fmt:message key="button.save" /></button>
                </div>
            </form>
        </c:if>
        <c:if test="${quotation.id != null}">

        <div class="panel-body well well-sm">
            <div class="col-md-3 col-sm-3">
                <a class="btn btn-default"><fmt:message key="button.menu.quotation" /> </a>
            </div>
            <div class="col-md-3 col-sm-3">
                <a class="btn btn-default"><fmt:message key="button.menu.add.budget" /> </a>
            </div>
            <div class="col-md-3 col-sm-3">
                <a class="btn btn-default"><fmt:message key="button.menu.viewDetails" /> </a>
            </div>
            <div class="col-md-3 col-sm-3">
                <a class="btn btn-default"><fmt:message key="button.menu.init.purchase.order" /> </a>
            </div>
        </div>

        </br>
            <form action='<c:url value="/cotacao/salvar"></c:url>' method="post" id="requestQuotationForm">
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
        </br>
                <c:if test="${quotation.type == 'Material'}">
                    <a href="<c:url value="/cotacao/formulario/adicionar"></c:url>"  class="btn btn-primary" > <fmt:message key="button.addProduct"  /> </a>
                    </br></br>
                    <table id="quotationMaterialTable" class="table table-striped table-hover table-condensed">
                        <thead>
                        <tr>
                            <th style="width: 3%"><fmt:message key="table.product" /></th>
                            <th style="width: 10%" ><fmt:message key="table.quantity" /></th>
                            <th style="width: 18%" ><fmt:message key="table.unit" /></th>
                            <th style="width: 2%" ><fmt:message key="table.##" /></th>
                            <th style="width: 2%" ><fmt:message key="table.##" /></th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </c:if>

                <c:if test="${quotation.type == 'Service'}">
                    <a href="<c:url value="/cotacao/formulario/adicionar"></c:url>"  class="btn btn-primary" > <fmt:message key="button.addService"  /> </a>
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
            </form>
       </c:if>
    </div>
</html:template>
</body>
<html:jsAssets/>
<script src="${pageContext.request.contextPath}/asset/js/custom/form-quotation.js"></script>
</html>
