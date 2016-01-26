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
            <h3><fmt:message key="title.solicitation" /></h3>
        </div>
    <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title"><fmt:message key="title.general.information" /></h3>
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-md-2 col-sm-2">
                        <div class="form-group">
                            <label  class="control-label"><fmt:message key="label.code"/></label><br>
                            <span id="idSolicitation">${solicitation.id}</span>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-3">
                        <div class="form-group">
                            <label  class="control-label"><fmt:message key="label.type.solicitation"/></label><br>
                            <span>${solicitation.type.description}</span>
                        </div>
                    </div>
                    <div class="col-md-4 col-sm-4">
                        <div class="form-group">
                            <label  class="control-label" ><fmt:message key="label.name"/></label><br>
                            <span>${solicitation.user.name}</span>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-3">
                        <div class="form-group">
                            <label  class="control-label"><fmt:message key="label.costCenter"/></label><br>
                                ${solicitation.costCenter.description}
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3 col-sm-3">
                        <div class="form-group">
                            <label class="control-label"><fmt:message key="label.urgency"/></label><br>
                            <c:choose>
                                <c:when test="${solicitation.urgency== true}"><span>Sim</span>
                                    <br />
                                </c:when>
                                <c:when test="${solicitation.urgency== false}"><span>Não</span>
                                    <br />
                                </c:when>
                                <c:otherwise><span></span>
                                    <br />
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-3">
                        <div class="form-group">
                            <label class="control-label"><fmt:message key="label.emergency"/></label><br>
                            <c:choose>
                                <c:when test="${solicitation.emergency== true}"><span>Sim</span>
                                    <br />
                                </c:when>
                                <c:when test="${solicitation.urgency== false}"><span>Não</span>
                                    <br />
                                </c:when>
                                <c:otherwise><span>Não</span>
                                    <br />
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-3">
                        <div class="form-group">
                            <label class="control-label"><fmt:message key="label.status"/></label><br>
                            <span>${solicitation.situation.status.description}</span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 col-sm-12">
                        <div class="form-group">
                            <label  class="control-label" ><fmt:message key="label.observation"/></label><br>
                            <span>${solicitation.observation}</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    <c:if test="${solicitation.type eq 'Service' or solicitation.type eq 'MaterialService'}">
            <div class="panel panel-default divService">
                <div class="panel-heading">
                    <h3 class="panel-title"><fmt:message key="title.solicitation.service" /></h3>
                </div>
                <div class="panel-body">
                   <div class="row">
                       <div class="col-md-3 col-sm-3">
                                <div class="form-group">
                                    <label  class="control-label"><fmt:message key="label.typeService"/></label><br>
                                    <span>${solicitationRequest.service.typeService.description}</span>
                                </div>
                            </div>
                       <div class="col-md-9 col-sm-9">
                           <div class="form-group">
                               <label  class="control-label"><fmt:message key="label.description"/></label><br>
                               <span>${solicitationRequest.service.description}</span>
                           </div>
                       </div>
                   </div>
                </div>
            </div>
        </c:if>
    <c:if test="${solicitation.type eq 'Material' or solicitation.type eq 'MaterialService'}">
            <div class="panel panel-default divMaterial">
                <div class="panel-heading">
                    <h3 class="panel-title"><fmt:message key="title.solicitation.products" /></h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <table class="table" id="tableProductSolicitation">
                            <thead>
                            <tr>
                                <th style="width:1%"><fmt:message key="table.code"/></th>
                                <th style="width:50%"><fmt:message key="table.product"/></th>
                                <th style="width:5%"><fmt:message key="table.abbreviatedQuantity"/></th>
                                <th style="width:6%"><fmt:message key="table.unit"/></th>
                                <th style="width:1%"><fmt:message key="table.status"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </c:if>
    <c:if test="${solicitation.situation.justificationCancellation != null }">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><fmt:message key="title.justification.cancellation" /></h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-11 col-sm-11">
                            <div class="form-group">
                                <label  class="control-label"><fmt:message key="label.justification"/></label><br>
                                <span>${solicitation.situation.justificationCancellation}</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
    <c:if test="${solicitation.situation.justificationDisapproval != null }">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><fmt:message key="title.justification.disapproval" /></h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-11 col-sm-11">
                            <div class="form-group">
                                <label  class="control-label"><fmt:message key="label.justification"/></label><br>
                                <span>${solicitation.situation.justificationDisapproval}</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
    <c:if test="${solicitation.situation.status eq 'QuotationCanceled' }">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><fmt:message key="title.justifications.quotation.canceled" /></h3>
                </div>
                <div class="panel-body">
                    <c:forEach items="${quotations}" var="quotation">
                        <div class="row">
                            <div class="col-md-11 col-sm-11">
                                <div class="form-group">
                                    <label  class="control-label"><fmt:message key="label.justification"/></label><br>
                                    <span>${quotation.justificationCancellation}</span>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </c:if>
    <c:if test="${solicitation.situation.status eq 'PurchaseOrderCanceled' || solicitation.situation.status eq 'PartiallyQuoteApproved' || solicitation.situation.status eq 'QuoteReject'  }">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><fmt:message key="title.justifications.purchase.order.canceled" /></h3>
                </div>
                <div class="panel-body">
                    <c:forEach items="${purchaseOrders}" var="purchaseOrder">
                        <div class="row">
                            <div class="col-md-3 col-sm-3">
                                <div class="form-group">
                                    <label  class="control-label"><fmt:message key="label.code.purchase.order"/></label><br>
                                    <span>
                                      ${purchaseOrder.id}
                                    </span>
                                </div>
                            </div>
                            <div class="col-md-8 col-sm-8">
                                <div class="form-group">
                                    <label  class="control-label"><fmt:message key="label.justification"/></label><br>
                                    <span>
                                        <c:if test="${purchaseOrder.status eq 'Reject'}">${purchaseOrder.approval.justificationDisapproval}</c:if>
                                        <c:if test="${purchaseOrder.status eq 'Canceled'}">${purchaseOrder.justificationCancellation}</c:if>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </c:if>

    <c:choose>
        <c:when test="${type eq 'pendente' && (userLogged.role.id eq 6 || userLogged.role.id eq 5) && solicitation.situation.status eq 'WaitingApproval'}">
            <form action='<c:url value="/solicitacao/aprovar"></c:url>' method="post" id="solicitationConfirmForm">
                <div class="row">
                    <div class="col-sm-offset-9 col-md-offset-9">
                        <div class="form-group">
                            <input type="hidden" name="solicitation.id" value="${solicitation.id}" >
                            <button type="submit" class="btn btn-primary"><fmt:message key="button.approve" /></button>
                            <a href='#' type="button" class="btn btn-warning" onclick="reproveSolicitation(${solicitation.id})"><fmt:message key="button.repproved"/></a>
                            <a href='<c:url value="/home"></c:url>' type="button" class="btn btn-danger"><fmt:message key="button.cancel"/></a>
                        </div>
                    </div>
                </div>
            </form>
        </c:when>
        <c:when test="${type eq 'pendente' && userLogged.role.id eq 5 && solicitation.situation.status eq 'CancellationRequest'}">
            <form action='<c:url value="/solicitacao/confirmar/cancelamento"></c:url>' method="post" id="solicitationConfirmCancellationForm">
                <div class="row">
                    <div class="col-sm-offset-9 col-md-offset-9">
                        <div class="form-group">
                            <input type="hidden" name="solicitation.id" value="${solicitation.id}" >
                            <button type="submit" class="btn btn-primary"><fmt:message key="button.approve" /></button>
                            <a href='<c:url value="/home"></c:url>' type="button" class="btn btn-danger"><fmt:message key="button.cancel"/></a>
                        </div>
                    </div>
                </div>
            </form>
        </c:when>
        <c:otherwise>
            <div class="row">
                <div class="col-sm-offset-11 col-md-offset-11">
                    <div class="form-group">
                        <a href='<c:url value="/home"></c:url>' type="button" class="btn btn-danger"><fmt:message key="button.cancel"/></a>
                    </div>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
    <div class="modal fade" id="reproveSolicitation" tabindex="-1" role="dialog" aria-labelledby="reproveSolicitation" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <form role="form" id="formReproveSolicitation" method="post" action='<c:url value="/solicitacao/reprovar" ></c:url>'>
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
</html:template>
</body>
<html:notification/>
<html:jsAssets/>
<html:tableJsAssets/>
<script src="${pageContext.request.contextPath}/asset/js/custom/visualize-solicitation.js"></script>
</html>

