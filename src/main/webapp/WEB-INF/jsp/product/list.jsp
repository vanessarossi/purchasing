<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title><fmt:message key="title.page.product" /> - <fmt:message key="title.purchasing" /></title>
    <html:head />
    <html:tableAssets/>
</head>
<body>
<html:template>
    <div class="container-fluid">
        <div class="page-header">
            <h3><fmt:message key="title.products" /></h3>
        </div>
        <a   href="<c:url value="/produto/formulario" ></c:url>" type="button" class="btn btn-primary">
            <fmt:message key="button.newProduct"/>
        </a>
        <br/><br/>
        <table id="productTable" class="table table-striped table-hover table-condensed">
            <thead>
            <tr>
                <th style="width: 3%"><fmt:message key="table.code" /></th>
                <th style="width: 35%" ><fmt:message key="table.description" /></th>
                <th style="width: 35%" ><fmt:message key="table.model" /></th>
                <th style="width: 15%"><fmt:message key="table.mark" /></th>
                <th style="width: 2%" ><fmt:message key="table.##" /></th>
                <th style="width: 2%" ><fmt:message key="table.##" /></th>
                <th style="width: 2%" ><fmt:message key="table.##" /></th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
    <div class="modal fade" id="viewProduct" tabindex="-1" role="dialog" aria-labelledby="viewProduct" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button ype="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title"><fmt:message key="title.product" /></h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-sm-5 col-md-5">
                            <div class="form-group">
                                <label class="control-label"><fmt:message key="label.description"></fmt:message></label><br>
                                <span id="description"></span>
                            </div>
                        </div>
                        <div class="col-sm-5 col-md-5">
                            <div class="form-group">
                                <label class="control-label"><fmt:message key="label.model"></fmt:message></label><br>
                                <span id="model"></span>
                            </div>
                        </div>
                    </div>
                    <div class="row" >
                        <div class="col-sm-4 col-md-4">
                            <div class="form-group">
                                <label class="control-label"><fmt:message key="label.mark"></fmt:message></label><br>
                                <span id="mark"></span>
                            </div>
                        </div>
                        <div class="col-sm-3 col-md-3">
                            <div class="form-group">
                                <label class="control-label"><fmt:message key="label.unit"></fmt:message></label><br>
                                <span id="unit"></span>
                            </div>
                        </div>
                        <div class="col-sm-4 col-md-4">
                            <div class="form-group">
                                <label class="control-label"><fmt:message key="label.category"></fmt:message></label><br>
                                <span id="category"></span>
                            </div>
                        </div>
                    </div>
                    <div class="row" >
                        <div class="col-sm-2 col-md-2">
                            <div class="form-group">
                                <label class="control-label"><fmt:message key="label.minimumStock"></fmt:message></label><br>
                                <span id="minimumStock"></span>
                            </div>
                        </div>
                        <div class="col-sm-4 col-md-4">
                            <div class="form-group">
                                <label class="control-label"><fmt:message key="label.barCode"></fmt:message></label><br>
                                <span id="barCode"></span>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-5 col-md-5">
                            <div class="form-group">
                                <label class="control-label"><fmt:message key="label.observation"></fmt:message></label><br>
                                <span id="observation"></span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="button.cancel" /></button>
                </div>
            </div>
        </div>
    </div>
</html:template>
</body>
<html:notification/>
<html:jsAssets/>
<html:tableJsAssets/>
<script src="${pageContext.request.contextPath}/asset/js/custom/product.js"></script>
</html>