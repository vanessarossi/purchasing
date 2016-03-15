<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title><fmt:message key="title.page.solicitation" /> - <fmt:message key="title.purchasing" /></title>
    <html:head />
    <html:tableAssets/>
</head>
<body>
<html:template>
    <div class="page-header">
            <h3><fmt:message key="title.solicitations" /></h3>
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

    <table id="solicitationTable" class="table table-striped table-hover table-condensed">
            <thead>
            <tr>
                <th style="width: 3%"><fmt:message key="table.code" /></th>
                <th style="width: 10%" ><fmt:message key="table.initialDate" /></th>
                <th style="width: 18%" ><fmt:message key="table.costCenter" /></th>
                <th style="width: 22%"><fmt:message key="table.user" /></th>
                <th style="width: 18%"><fmt:message key="table.status" /></th>
                <th style="width: 2%" ><fmt:message key="table.##" /></th>
                <th style="width: 2%" ><fmt:message key="table.##" /></th>
                <th style="width: 2%" ><fmt:message key="table.##" /></th>
                <th style="width: 2%" ><fmt:message key="table.##" /></th>
            </tr>
            </thead>
            <tbody>
            </tbody>
    </table>

    <div class="modal fade" id="reproveSolicitation" tabindex="-1" role="dialog" aria-labelledby="reproveSolicitation" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <form role="form" id="formReproveSolicitation" method="post" action='<c:url value="/solicitacao/cancelar/analise" ></c:url>'>
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title"><fmt:message key="title.reprove" /></h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-10 col-sm-10">
                                <div class="form-group">
                                    <label  class="control-label" for="justification"><fmt:message key="label.justification"/></label> <span class="required"><fmt:message key="label.asterisk" /></span>
                                    <textarea rows="4" cols="100" class="form-control" id="justification" name="solicitation.situation.justificationDisapproval"></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <input type="hidden" name="solicitation.id" id="solicitationId" >
                        <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="button.cancel" /></button>
                        <button type="button" class="btn btn-success" onclick="send()"><fmt:message key="button.save" /></button>
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
                    <h4 class="modal-title"><fmt:message key="title.solicitation" /></h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                            <div class="form-group">
                                <label  class="control-label"><fmt:message key="label.code"/></label>
                                <input  class="form-control" readonly value="" id="solicitationCode">
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
                            <div class="form-group">
                                <label  class="control-label"><fmt:message key="label.costCenter"/></label>
                                <input  class="form-control" readonly value="" id="solicitationCostCenter">
                            </div>
                        </div>
                        <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
                            <div class="form-group">
                                <label  class="control-label"><fmt:message key="label.user"/></label>
                                <input  class="form-control" readonly value="" id="solicitationUser">
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

</html:template>
</body>
<html:notification/>
<html:jsAssets/>
<html:tableJsAssets/>
<script src="${pageContext.request.contextPath}/asset/js/custom/solicitation.js"></script>
</html>

