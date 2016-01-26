<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title><fmt:message key="title.page.user" /> - <fmt:message key="title.purchasing" /></title>
    <html:head />
    <html:tableAssets/>
</head>
<body>
<html:template>
    <div class="page-header">
            <h3><fmt:message key="title.users" /></h3>
        </div>
    <a type="button" class="btn btn-primary" href="<c:url value="/usuario/formulario" ></c:url>">
            <fmt:message key="button.newUser"/>
        </a>
    <br/><br/>
    <table id="userTable" class="table table-striped table-hover table-condensed">
            <thead>
            <tr>
                <th style="width: 30%"><fmt:message key="table.name" /></th>
                <th style="width: 30%" ><fmt:message key="table.username"/></th>
                <th style="width: 20%" ><fmt:message key="table.role" /></th>
                <th style="width: 5%" ><fmt:message key="table.active" /></th>
                <th style="width: 1%" ><fmt:message key="table.##" /></th>
                <th style="width: 1%" ><fmt:message key="table.##" /></th>
                <th style="width: 1%" ><fmt:message key="table.##" /></th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    <div class="modal fade" id="viewUser" tabindex="-1" role="dialog" aria-labelledby="viewUser" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title"><fmt:message key="title.user" /></h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-sm-5 col-md-5">
                            <div class="form-group">
                                <label class="control-label"><fmt:message key="label.name"></fmt:message></label><br/>
                                <span id="name"></span>
                            </div>
                        </div>
                        <div class="col-sm-3 col-md-3">
                            <div class="form-group">
                                <label class="control-label"><fmt:message key="label.username"></fmt:message></label><br/>
                                <span id="username"></span>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6 col-md-6">
                            <div class="form-group">
                                <label class="control-label"><fmt:message key="label.email"></fmt:message></label><br/>
                                <span id="email"></span>
                            </div>
                        </div>
                        <div class="col-sm-2 col-md-2">
                            <div class="form-group">
                                <label class="control-label"><fmt:message key="label.active"></fmt:message></label><br/>
                                <span id="active"></span>
                            </div>
                        </div>
                        <div class="col-sm-3 col-md-3">
                            <div class="form-group">
                                <label class="control-label"><fmt:message key="label.role"></fmt:message></label><br/>
                                <span id="role"></span>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <h4 class="text-center"><fmt:message key="title.cost.center.user" /></h4>
                        </hr>
                    </div>
                    <div class="row ">
                        <div class="col-sm-12 col-md-12 ">
                            <table id="costCenterUserTable" class="table table-striped table-hover table-condensed">
                                <thead>
                                <tr>
                                    <th style="width: 50%"><fmt:message key="table.company" /></th>
                                    <th style="width: 50%" ><fmt:message key="table.costCenter"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
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
<script src="${pageContext.request.contextPath}/asset/js/custom/user.js"></script>
</html>