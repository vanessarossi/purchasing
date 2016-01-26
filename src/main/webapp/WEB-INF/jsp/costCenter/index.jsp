<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title><fmt:message key="title.page.cost.center" /> - <fmt:message key="title.purchasing" /></title>
    <html:head />
    <html:tableAssets/>
</head>
<body>
<html:template>
    <div class="page-header">
            <h3><fmt:message key="title.costs.center" /></h3>
        </div>
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#newCostCenter">
            <fmt:message key="button.newCostCenter"/>
        </button>
    <br/>
    <table id="costCenterTable" class="table table-striped table-hover table-condensed">
            <thead>
            <tr>
                <th style="width:3%"><fmt:message key="table.code"></fmt:message></th>
                <th style="width:50%"><fmt:message key="table.description"></fmt:message></th>
                <th style="width:25%"><fmt:message key="table.company"></fmt:message></th>
                <th style="width:1%; text-align: center"><fmt:message key="table.##"></fmt:message></th>
                <th style="width:1%; text-align: center"><fmt:message key="table.##"></fmt:message></th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    <div class="modal fade" id="newCostCenter" tabindex="-1" role="dialog" aria-labelledby="newCostCenter" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                  <form role="form" id="formCostCenter" method="post" action='<c:url value="/centroCusto/salvar" ></c:url>'>
                      <div class="modal-header">
                          <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                          <h4 class="modal-title"><fmt:message key="title.costs.center" /></h4>
                      </div>
                      <div class="modal-body">
                          <div class="row">
                              <div class="col-sm-3 col-md-3">
                                  <div class="form-group">
                                      <label class="control-label" for="code"><fmt:message key="label.code"/></label> <span class="required"><fmt:message key="label.asterisk" /></span>
                                      <input type="text" class="form-control" id="code"  name="costCenter.code" />

                                  </div>
                              </div>
                              <div class="col-sm-7 col-md-7">
                                  <div class="form-group">
                                      <label class="control-label" for="description"><fmt:message key="label.description"/></label> <span class="required"><fmt:message key="label.asterisk" /></span>
                                      <input type="text" class="form-control" id="description"  name="costCenter.description" />

                                  </div>
                              </div>
                          </div>
                          <div class="row">
                              <div class="col-sm-6 col-md-6">
                                  <div class="form-group">
                                      <label class="control-label" for="company"><fmt:message key="label.company"/></label> <span class="required"><fmt:message key="label.asterisk" /></span>
                                      <select class="form-control" id="company" name="costCenter.company.id">
                                      </select>
                                  </div>
                              </div>
                          </div>
                      </div>
                      <input type="hidden" name="category.id" id="id"  value="" />
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
<script src="${pageContext.request.contextPath}/asset/js/custom/costCenter.js"></script>
</html>