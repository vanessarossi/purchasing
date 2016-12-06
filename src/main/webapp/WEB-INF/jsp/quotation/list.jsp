<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title><fmt:message key="title.page.quotation" /> - <fmt:message key="title.purchasing" /></title>
    <html:head />
    <html:tableAssets/>
</head>
<body>
    <html:template>
        <div class="page-header">
            <h3><fmt:message key="title.quotations" /></h3>
        </div>
        <div class="row">
            <div class="col-md-3 col-sm-3">
                <div class="form-group">
                    <label  class="control-label" for="status"><fmt:message key="label.status"/></label></span>
                    <select id="status" class="form-control">
                        <option value=""><fmt:message key="label.select"/></option>
                        <c:forEach items="${status}" var="status">
                            <option value="${status}">${status.description}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>
        <c:if test="${!empty errorQuotatioFinalized }">
            <div class="row">
                <div class="alert alert-danger alert-dismissible text-center" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong> <fmt:message key="label.atention" /> </strong> <span><fmt:message key="${errorQuotatioFinalized}"/></span>
                </div>
            </div>
        </c:if>
        <br/>

        <table id="quotationTable" class="table table-striped table-hover table-condensed">
            <thead>
                <tr>
                    <th><fmt:message key="table.code"/></th>
                    <th><fmt:message key="table.type"/></th>
                    <th><fmt:message key="table.user"/></th>
                    <th><fmt:message key="table.description"/></th>
                    <th><fmt:message key="table.status"/></th>
                    <th><fmt:message key="table.initialDate"/></th>
                    <th><fmt:message key="table.finalDate"/></th>
                    <th><fmt:message key="table.##"/></th>
                    <th><fmt:message key="table.##"/></th>
                    <th><fmt:message key="table.##"/></th>
                </tr>
            </thead>
            <tbody>
            </tbody>
        </table>

    </html:template>
    <div class="modal fade" id="viewCancellForm" tabindex="-1" role="dialog" aria-labelledby="viewCancellForm" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title"><fmt:message key="title.cancell.quotation" /></h4>
                </div>
                <form action='<c:url value="/cotacao/cancelar"></c:url>' method="post" id="cancellQuotationForm">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12 col-sm-12">
                                <div class="form-group">
                                    <label class="control-label" for="justification"><fmt:message key="label.justification"/></label>
                                    <textarea rows="4" cols="100" class="form-control" name="quotation.justificationCancellation" id="justification" required></textarea>
                                </div>
                            </div>
                            <input type="hidden" name="quotation.id" id="quotation" value=""/>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <input type="submit" value="<fmt:message key="button.save"/>" class="btn btn-success"/>
                        <button type="button" class="btn btn-danger" data-dismiss="modal"><fmt:message key="button.cancel"/></button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="modal fade" id="viewQuick" tabindex="-1" role="dialog" aria-labelledby="viewQuick" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title"><fmt:message key="title.quotation" /></h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                            <div class="form-group">
                                <label  class="control-label"><fmt:message key="label.code"/></label>
                                <input  class="form-control" readonly value="" id="quotationCode">
                            </div>
                        </div>
                        <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
                            <div class="form-group">
                                <label  class="control-label"><fmt:message key="label.user"/></label>
                                <input  class="form-control" readonly value="" id="quotationUser">
                            </div>
                        </div>
                    </div>
                    <div class="row productType">
                        <table id="productTable" class="table table-striped table-hover table-condensed">
                            <thead>
                            <tr>
                                <th><fmt:message key="table.code" /></th>
                                <th><fmt:message key="table.description"/></th>
                                <th><fmt:message key="table.model" /></th>
                                <th><fmt:message key="table.mark" /></th>
                                <th><fmt:message key="table.abbreviatedQuantity" /></th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                    <div class="row serviceType">
                        <table id="serviceTable" class="table table-striped table-hover table-condensed">
                            <thead>
                            <tr>
                                <th><fmt:message key="table.costCenter" /></th>
                                <th><fmt:message key="table.type" /></th>
                                <th><fmt:message key="table.description"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<html:jsAssets/>
<html:tableJsAssets/>
<script src="${pageContext.request.contextPath}/asset/js/custom/quotation.js"></script>
</html>
