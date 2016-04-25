<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title><fmt:message key="title.page.account"/> - <fmt:message key="title.purchasing"/></title>
    <html:head/>
    <html:tableAssets/>
</head>
<body>
<html:template>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title"><fmt:message key="title.search.posting.accounts" /></h3>
        </div>
        <div class="panel-body">
            <form method="post" id="formSearch" role="form">
                <div class="row">
                    <div class="col-sm-2 col-md-2">
                        <div class="form-group">
                            <label class="control-label"><fmt:message key="label.type.account"></fmt:message></label>
                            <select class="form-control" id="type" name="account.type">
                                <option value="" ><fmt:message key="label.select"/></option>
                                <option value="A" <c:if test="${account.type eq 'A'}">selected</c:if> ><fmt:message key="label.water"/></option>
                                <option value="E" <c:if test="${account.type eq 'E'}">selected</c:if> ><fmt:message key="label.energy"/></option>
                                <option value="T" <c:if test="${account.type eq 'T'}">selected</c:if> ><fmt:message key="label.telephony"/></option>
                            </select>
                        </div>
                    </div>
                    <div class="col-sm-1 col-md-1">
                        <div class="form-group">
                            <label class="control-label"><fmt:message key="label.competence"></fmt:message></label>
                            <input type="text" class="form-control competence" id="competence" name="account.competence" value="${account.competence}"/>
                            <span class="required">${errors.from('account.competence')}</span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-2 col-md-2">
                        <div class="form-group">
                            <label class="control-label"><fmt:message key="label.phone"></fmt:message></label>
                            <input type="text" class="form-control phone" id="phone" name="account.phone"/>
                        </div>
                    </div>
                    <div class="col-sm-3 col-md-3">
                        <div class="form-group">
                            <label class="control-label"><fmt:message key="label.signature.type"></fmt:message></label>
                            <input type="text" class="form-control" id="signatureType" name="account.signatureType"/>
                        </div>
                    </div>
                    <div class="col-sm-4 col-md-4">
                        <div class="form-group">
                            <label class="control-label"><fmt:message key="label.typeService"></fmt:message></label>
                            <input type="text" class="form-control" id="typeService" name="account.typeService"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-3 col-md-3">
                        <div class="form-group">
                            <label class="control-label"><fmt:message key="label.account.place"></fmt:message></label>
                            <input type="text" class="form-control" id="place" name="account.place" value="${account.place}" readonly/>
                            <span class="required">${errors.from('account.place')}</span>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-6">
                        <div class="form-group">
                            <label class="control-label"><fmt:message key="label.address"></fmt:message></label>
                            <select class="form-control" id="address" name="account.address">
                                <option value="" ><fmt:message key="label.select"/></option>
                                <c:forEach items="${addresses}" var="adress">
                                    <option value="${adress}" <c:if test="${adress == account.address }"> selected  </c:if> ><c:out value="${adress.address}"></c:out> </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-offset-11 col-md-offset-11">
                        <div class="form-group">
                            <button type="button" class="btn btn-info" onclick="search()"><fmt:message key="button.search" /></button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <div class="page-header">
        <h3><fmt:message key="title.list.posting.accounts"/></h3>
    </div>

    <table id="postingAccountTable" class="table table-striped table-hover table-condensed">
        <thead>
            <tr>
                <th><fmt:message key="table.type" /></th>
                <th><fmt:message key="table.competence" /></th>
                <th><fmt:message key="table.place" /></th>
                <th><fmt:message key="table.signatureType"/></th>
                <th><fmt:message key="table.typeService"/></th>
                <th><fmt:message key="table.value" /></th>
                <th><fmt:message key="table.discount" /></th>
                <th><fmt:message key="table.total.value" /></th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
        </tbody>
        <tfoot>
            <th></th>
            <th></th>
            <th></th>
            <th></th>
            <th></th>
            <th></th>
            <th><fmt:message key="table.total.account" /></th>
            <th><input type="text" id="totalAccount" value="" readonly /></th>
            <th></th>
            <th></th>
        </tfoot>
    </table>

</html:template>
</body>
<html:notification/>
<html:jsAssets/>
<html:tableJsAssets/>
<script src="${pageContext.request.contextPath}/asset/js/vendor/jquery.mask.min.js"></script>
<script src="${pageContext.request.contextPath}/asset/js/commons/formCommons.js"></script>
<script src="${pageContext.request.contextPath}/asset/js/custom/account-list.js"></script>
</html>