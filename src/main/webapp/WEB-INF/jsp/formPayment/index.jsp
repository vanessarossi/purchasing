<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title><fmt:message key="title.page.form.payment" /> - <fmt:message key="title.purchasing" /></title>
    <html:head />
    <html:tableAssets/>
</head>
<body>
<html:template>
    <div class="container-fluid">
        <div class="page-header">
            <h3><fmt:message key="title.forms.payment" /></h3>
        </div>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#newFormPayment">
            <fmt:message key="button.newFormPayment"/>
        </button>
        <br/>
        <table class="table table-striped table-hover table-condensed" id="formPaymentTable">
            <thead>
            <tr>
                <th style="width: 50%"><fmt:message key="table.description" /></th>
                <th style="width: 2%" ><fmt:message key="table.##" /></th>
                <th style="width: 2%" ><fmt:message key="table.##" /></th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
    <div class="modal fade" id="newFormPayment" tabindex="-1" role="dialog" aria-labelledby="newFormPayment" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <form role="form" id="formFormPayment" method="post" action='<c:url value="/formaPagamento/salvar" ></c:url>'>
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title"><fmt:message key="title.form.payment" /></h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-sm-7 col-md-7">
                                <div class="form-group">
                                    <label class="control-label" for="description"><fmt:message key="label.description"/></label> <span class="required"><fmt:message key="label.asterisk" /></span>
                                    <input type="text" class="form-control" id="description"  name="formPayment.description" />
                                    <input type="hidden" name="formPayment.id" id="id"  value="" />
                                </div>
                            </div>
                        </div>
                        <div class="row">
                        <div class="col-sm-3 col-md-3">
                            <div class="form-group">
                                <label class="control-label" for="input"><fmt:message key="label.input"/></label> <span class="required"><fmt:message key="label.asterisk" /></span>
                                <br>
                                <div class="radio-inline">
                                    <label class="control-label">
                                        <input type="radio" value="true" name="formPayment.input" id="input"> <fmt:message key="label.yes"/>
                                    </label>
                                </div>
                                <div class="radio-inline">
                                    <label class="control-label">
                                        <input type="radio" value="false" name="formPayment.input" id="input"> <fmt:message key="label.no"/>
                                    </label>
                                </div>
                                <br>
                            </div>
                        </div>
                            <div class="col-sm-4 col-md-4">
                                <div class="form-group">
                                    <label class="control-label" for="parcels"><fmt:message key="label.parcels"/></label> <span class="required"><fmt:message key="label.asterisk" /></span>
                                    <input type="text" class="form-control" id="parcels"  name="formPayment.parcels" />
                                </div>
                            </div>
                            <div class="col-sm-4 col-md-4">
                                <div class="form-group">
                                    <label class="control-label" for="intervalDay"><fmt:message key="label.intervalDay"/></label> <span class="required"><fmt:message key="label.asterisk" /></span>
                                    <input type="text" class="form-control" id="intervalDay"  name="formPayment.intervalDay" />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="button.cancel" /></button>
                        <button type="submit" class="btn btn-success"><fmt:message key="button.save" /></button>
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
<script src="${pageContext.request.contextPath}/asset/js/custom/formPayment.js"></script>
</html>
