<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title><fmt:message key="title.page.contract" /> - <fmt:message key="title.purchasing" /></title>
    <html:head />
    <html:formAssets/>
</head>
<body>
<html:template>
    <form action='<c:url value="/contrato/salvar"></c:url>' method="post" id="contractForm" enctype="multipart/form-data">
        <div class="page-header">
                <h3><fmt:message key="title.contract" /></h3>
            </div>
        <div class="row">
                <div class="col-md-1 col-sm-1">
                    <div class="form-group">
                        <label class="control-label"  for="code"><fmt:message key="label.code"/></label>
                        <input type="text" class="form-control" id="code" name="contract.supplier.id" value="${contract.supplier.id}" >
                    </div>
                </div>
                <div class="col-md-7 col-sm-7">
                    <div class="form-group">
                        <label class="control-label" for="supplier"><fmt:message key="label.supplier"/></label><span class="required"> *</span>
                        <div class="input-group">
                            <input type="text" class="form-control" id="supplier" value="${contract.supplier.person.name}" readonly>
                            <span class="input-group-btn"><button class="btn btn-default" type="button" data-toggle="modal" data-target="#searchSupplier"><span class="fa fa-search"></span></button></span>
                        </div>
                        <span class="required">${errors.from('contract.supplier')}</span>
                    </div>
                </div>
            </div>
        <div class="row">
                <div class="col-lg-2 col-md-2 col-sm-2">
                    <div class="form-group">
                        <label class="control-label" for="initialDate"><fmt:message key="label.initialDate"></fmt:message></label><span class="required"> *</span>
                        <input type="text" class="form-control date" name="contract.initialDate" id="initialDate" value="<fmt:formatDate value="${contract.initialDate}" pattern="dd/MM/YYYY"/>">
                        <span class="required">${errors.from('contract.initialDate')}</span>
                    </div>
                </div>
                <div class="col-lg-2 col-md-2 col-sm-2">
                    <div class="form-group">
                        <label class="control-label" for="finalDate"><fmt:message key="label.finalDate"></fmt:message></label><span class="required"> *</span>
                        <input type="text" class="form-control date" name="contract.finalDate" id="finalDate" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${contract.finalDate}"/>">
                        <span class="required">${errors.from('contract.finalDate')}</span>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-11 col-md-11 col-sm-11">
                    <div class="form-group">
                        <label class="control-label" for="observation"><fmt:message key="label.observation"></fmt:message></label>
                        <textarea rows="5" cols="1" class="form-control" name="contract.observation" id="observation">${contract.observation}</textarea>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title"><fmt:message key="title.renewals" /></h3>
            </div>
            <div class="panel-body">
                <table id="renewalContract" class="table table-striped table-hover table-condensed">
                    <thead>
                    <tr>
                        <th><fmt:message key="table.initialDate" /></th>
                        <th><fmt:message key="table.finalDate" /></th>
                        <th><fmt:message key="table.##" /></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${renewalContracts}" var="renewal">
                        <tr>
                            <td><fmt:formatDate pattern="dd/MM/yyyy" value="${renewal.initialDate}"/></td>
                            <td><fmt:formatDate pattern="dd/MM/yyyy" value="${renewal.finalDate}"/></td>
                            <td><a href="/purchasing/contrato/renovacao/download/${renewal.id}"  target="_blank"><span class="fa fa-print btn btn-default btn-xs"></span></a></td>
                            <td><a onclick=confirmDetele(${renewal.id})><span class="fa fa-trash-o btn btn-default btn-xs"></span></a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row">
                <div class="col-sm-offset-7 col-md-offset-8">
                    <div class="form-group">
                        <input type="hidden" name="contract.id" value="${contract.id}" >
                        <a onclick="renewal()" type="button" class="btn btn-primary"><fmt:message key="button.renewal"/></a>
                        <button type="submit" class="btn btn-success"><fmt:message key="button.save" /></button>
                        <a href='<c:url value="/contrato"></c:url>' type="button" class="btn btn-danger"><fmt:message key="button.cancel"/></a>
                    </div>
                </div>
            </div>
    </form>
    <form action='<c:url value="/contrato/renovacao/salvar"></c:url>' method="post" id="renewalForm" enctype="multipart/form-data">
        <div class="container-fluid">
            <div class="page-header">
                <h3><fmt:message key="title.renewal" /></h3>
            </div>
            <div class="row">
                <div class="col-md-2 col-sm-2">
                    <div class="form-group">
                        <label class="control-label"><fmt:message key="label.contract"/></label><span class="required"> *</span>
                        <input type="text" class="form-control"  name="renewalContract.contract.id" value="${contract.id}"  readonly >
                        <span class="required">${errors.from('renewalContract.contract')}</span>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-1 col-sm-1">
                    <div class="form-group">
                        <label class="control-label"><fmt:message key="label.code"/></label>
                        <input type="text" class="form-control" value="${contract.supplier.id}"  readonly >
                    </div>
                </div>
                <div class="col-md-7 col-sm-7">
                    <div class="form-group">
                        <label class="control-label" for="supplier"><fmt:message key="label.supplier"/></label>
                        <input type="text" class="form-control" value="${contract.supplier.person.name}"  readonly>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-2 col-md-2 col-sm-2">
                    <div class="form-group">
                        <label class="control-label" for="initialDateRenewal"><fmt:message key="label.initialDate"></fmt:message></label><span class="required"> *</span>
                        <input type="text" class="form-control date" name="renewalContract.initialDate" id="initialDateRenewal" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${renewalContract.initialDate}" />">
                        <span class="required">${errors.from('renewalContract.initialDate')}</span>
                    </div>
                </div>
                <div class="col-lg-2 col-md-2 col-sm-2">
                    <div class="form-group">
                        <label class="control-label" for="finalDateRenewal"><fmt:message key="label.finalDate"></fmt:message></label><span class="required"> *</span>
                        <input type="text" class="form-control date" name="renewalContract.finalDate" id="finalDateRenewal" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${renewalContract.finalDate}"/>">
                        <span class="required">${errors.from('renewalContract.finalDate')}</span>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-offset-8 col-md-offset-8">
                    <div class="form-group">
                        <input type="hidden" name="contract.id" value="${contract.id}" >
                        <button type="submit" class="btn btn-success"><fmt:message key="button.save" /></button>
                        <a onclick="cancelRenewal()" type="button" class="btn btn-danger"><fmt:message key="button.cancel"/></a>
                    </div>
                </div>
            </div>
        </div>
    </form>
</html:template>
</body>
<html:jsAssets/>
<html:searchSupplier/>
<html:notification/>
<script src="${pageContext.request.contextPath}/asset/js/vendor/jquery.mask.min.js"></script>
<script src="${pageContext.request.contextPath}/asset/js/custom/form-contract.js"></script>
<script src="${pageContext.request.contextPath}/asset/js/custom/modalSearchSupplier.js"></script>
</html>

