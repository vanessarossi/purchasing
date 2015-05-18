
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <title><fmt:message key="title.page.report" /> - <fmt:message key="title.purchasing" /></title>
  <html:head />
  <html:formAssets/>
</head>
<body>
<html:template>
  <form action='<c:url value="/relatorio/recepcao/data/resultado"></c:url>' method="post" target="_blank">
    <div class="container-fluid">
      <div class="page-header">
        <h3><fmt:message key="title.report.reception.date" /></h3>
      </div>
      <div class="row">
        <div class="col-lg-2 col-md-2 col-sm-2">
          <div class="form-group">
            <label class="control-label" for="initialDate"><fmt:message key="label.initialDate"></fmt:message></label><span class="required"> *</span>
            <input type="text" class="form-control date" name="initialDate" id="initialDate">
          </div>
        </div>
        <div class="col-lg-2 col-md-2 col-sm-2">
          <div class="form-group">
            <label class="control-label" for="finalDate"><fmt:message key="label.finalDate"></fmt:message></label><span class="required"> *</span>
            <input type="text" class="form-control date" name="finalDate" id="finalDate">
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-sm-offset-8 col-md-offset-9">
          <div class="form-group">
            <button type="submit" class="btn btn-default"><fmt:message key="button.generate" /></button>
            <a href='<c:url value="/home"></c:url>' type="button" class="btn btn-danger"><fmt:message key="button.cancel"/></a>
          </div>
        </div>
      </div>
    </div>
  </form>
</html:template>
</body>
<html:jsAssets/>
<script src="${pageContext.request.contextPath}/asset/js/vendor/jquery.mask.min.js"></script>
<script src="${pageContext.request.contextPath}/asset/js/commons/formCommons.js"></script>
</html>

