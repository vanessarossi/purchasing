<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title><fmt:message key="title.page.account" /> - <fmt:message key="title.purchasing" /></title>
    <html:head />
</head>
<body>
<html:template>
    <div class="page-header">
        <h3><fmt:message key="title.posting.accounts" /></h3>
    </div>
    <form role="form" id="formAccount" method="post" action='<c:url value="/conta/salvar" ></c:url>'>
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
        </div>
        <div class="row">
            <div class="col-sm-1 col-md-1">
                <div class="form-group">
                    <label class="control-label"><fmt:message key="label.competence"></fmt:message></label>
                    <input type="text" class="form-control competence" id="competence" name="account.competence" value="${account.competence}"/>
                    <span class="required">${errors.from('account.competence')}</span>
                </div>
            </div>
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
            <div class="col-sm-2 col-md-2">
                <div class="form-group">
                    <label class="control-label"><fmt:message key="label.phone"></fmt:message></label>
                    <input type="text" class="form-control phone" id="phone" name="account.phone" value="${account.phone}"/>
                    <span class="required">${errors.from('account.phone')}</span>
                </div>
            </div>
            <div class="col-sm-3 col-md-3">
                <div class="form-group">
                    <label class="control-label"><fmt:message key="label.signature.type"></fmt:message></label>
                    <input type="text" class="form-control" id="signatureType" name="account.signatureType" value="${account.signatureType}"/>
                    <span class="required">${errors.from('account.signatureType')}</span>
                </div>
            </div>
            <div class="col-sm-4 col-md-4">
                <div class="form-group">
                    <label class="control-label"><fmt:message key="label.typeService"></fmt:message></label>
                    <input type="text" class="form-control" id="typeService" name="account.typeService" value="${account.typeService}"/>
                    <span class="required">${errors.from('account.typeService')}</span>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-2 col-md-2">
                <div class="form-group">
                    <label class="control-label"><fmt:message key="label.value"></fmt:message></label>
                    <input type="text" class="form-control money" id="value" name="account.value" value="${account.value}"/>
                    <span class="required">${errors.from('account.value')}</span>
                </div>
            </div>
            <div class="col-sm-2 col-md-2">
                <div class="form-group">
                    <label class="control-label"><fmt:message key="label.discount"></fmt:message></label>
                    <input type="text" class="form-control money" id="discount" name="account.discount" value="${account.discount}"/>
                    <span class="required">${errors.from('account.discount')}</span>
                </div>
            </div>
            <div class="col-sm-2 col-md-2">
                <div class="form-group">
                    <label class="control-label"><fmt:message key="label.total.value"></fmt:message></label>
                    <input type="text" class="form-control money" id="totalValue" name="account.totalValue" value="${account.totalValue}" readonly/>
                    <span class="required">${errors.from('account.totalValue')}</span>
                </div>
            </div>
            <div class="col-sm-2 col-md-2">
                <div class="form-group">
                    <label class="control-label"><fmt:message key="label.expiration.date"></fmt:message></label>
                    <input type="text" class="form-control date" id="expirationDate" name="account.expirationDate" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${account.expirationDate}"/>"/>
                    <span class="required">${errors.from('account.expirationDate')}</span>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12 col-md-12">
                <div class="form-group">
                    <label class="control-label"><fmt:message key="label.observation"></fmt:message></label>
                    <textarea rows="4" cols="100" class="form-control" id="observation" name="account.observation">${account.observation}</textarea>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-offset-10 col-md-offset-10">
                <div class="form-group">
                    <input type="hidden" name="account.id" value="${account.id}" >
                    <button type="submit" class="btn btn-success"><fmt:message key="button.save" /></button>
                    <a href='<c:url value="/conta/listagem"></c:url>' type="button" class="btn btn-danger"><fmt:message key="button.cancel"/></a>
                </div>
            </div>
        </div>
    </form>
</html:template>
</body>
<html:notification/>
<html:jsAssets/>
<script src="${pageContext.request.contextPath}/asset/js/vendor/jquery.mask.min.js"></script>
<script src="${pageContext.request.contextPath}/asset/js/commons/formCommons.js"></script>
<script src="${pageContext.request.contextPath}/asset/js/custom/account-form.js"></script>
</html>
