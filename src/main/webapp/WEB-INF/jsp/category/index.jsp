<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title><fmt:message key="title.page.category" /> - <fmt:message key="title.purchasing" /></title>
    <html:head />
    <html:tableAssets/>
</head>
<body>
<html:template>
    <div class="container-fluid">
        <div class="page-header">
                <h3><fmt:message key="title.categories" /></h3>
            </div>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#newCategory">
              <fmt:message key="button.newCategory"/>
            </button>
        <br/>
        <table id="categoryTable" class="table table-striped table-hover table-condensed">
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
    </div>
        <div class="modal fade" id="newCategory" tabindex="-1" role="dialog" aria-labelledby="newCategory" aria-hidden="true">
          <div class="modal-dialog">
            <div class="modal-content">
              <form role="form" id="formCategory" method="post" action='<c:url value="/categoria/salvar" ></c:url>'>
                  <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                      <h4 class="modal-title"><fmt:message key="title.category" /></h4>
                  </div>
                  <div class="modal-body">
                      <div class="row">
                          <div class="col-sm-7 col-md-7">
                              <div class="form-group">
                                  <label class="control-label" for="description"><fmt:message key="label.description"/></label> <span class="required"><fmt:message key="label.asterisk" /></span>
                                  <input type="text" class="form-control" id="description"  name="category.description" />
                                  <input type="hidden" name="category.id" id="id"  value="" />
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
<script src="${pageContext.request.contextPath}/asset/js/custom/category.js"></script>
</html>