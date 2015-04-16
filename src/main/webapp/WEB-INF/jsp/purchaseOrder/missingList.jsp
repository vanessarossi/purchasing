<!DOCTYPE html>
<html lang="pt-br">
<head>
  <title><fmt:message key="title.page.purchaseOrder"/> - <fmt:message key="title.purchasing"/></title>
  <html:head/>
  <html:tableAssets/>
</head>
<body>
<html:template>
  <div class="container-fluid">
    <div class="page-header">
      <h3><fmt:message key="title.purchasingOrders"/></h3>
    </div>
    <br/>
    <table id="purchasingOrderMissingTable" class="table table-striped table-hover table-condensed">
      <thead>
      <tr>
        <th style="width: 5%"><fmt:message key="table.code"/></th>
        <th style="width: 80%"><fmt:message key="table.supplier"/></th>
        <th style="width: 2%"><fmt:message key="table.##"/></th>
      </tr>
      </thead>
      <tbody>
      </tbody>
    </table>
  </div>
</html:template>
</body>
<html:notification/>
<html:jsAssets/>
<html:tableJsAssets/>
<script src="${pageContext.request.contextPath}/asset/js/custom/purchase-order-missing.js"></script>
</html>