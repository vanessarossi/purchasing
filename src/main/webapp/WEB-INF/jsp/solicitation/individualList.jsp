<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title><fmt:message key="title.page.solicitation" /> - <fmt:message key="title.purchasing" /></title>
    <html:head />
    <html:formAssets/>
</head>
<body>
<html:template>
    <div class="container-fluid">
        <html:template/>
        <div class="page-header">
            <h3><fmt:message key="title.solicitations" /></h3>
        </div>
        <c:if test="${! empty messageErrorEdit}">
            <div class="row">
                <div class="col-md-12 col-sm-12 text-center">
                    <span class="message-error"><fmt:message key="${messageErrorEdit}"/></span>
                </div>
            </div>
        </c:if>
        <br/><br/>
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
    </div>
    <div class="modal fade" id="cancelSolicitation" tabindex="-1" role="dialog" aria-labelledby="cancelSolicitation" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <form role="form" id="cancelSolicitationForm" method="post" action='<c:url value="/solicitacao/pedir/cancelamento" ></c:url>'>
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title"><fmt:message key="title.cancel" /></h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-10 col-sm-10">
                                <div class="form-group">
                                    <label  class="control-label" for="justification"><fmt:message key="label.justification"/></label> <span class="required"><fmt:message key="label.asterisk" /></span>
                                    <textarea rows="4" cols="100" class="form-control" id="justification" name="solicitation.situation.justificationCancellation"></textarea>
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
<html:jsAssets/>
</html>
