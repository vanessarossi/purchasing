<!DOCTYPE html>
<html lang="pt-br">
<head>
  <title><fmt:message key="title.page.purchaseOrder"/> - <fmt:message key="title.purchasing"/></title>
  <html:head/>
  <html:tableAssets/>
</head>
<body>
<html:template>
    <div class="page-header">
      <h3><fmt:message key="title.search.purchase.order"/></h3>
    </div>
    <form action='<c:url value="/ordemCompra/pesquisar/fornecedor"></c:url>' method="post" id="purchaseOrderForm">
      <div class="row">
        <div class="col-md-1 col-sm-1">
          <div class="form-group">
            <label class="control-label" for="code"><fmt:message key="label.code"/></label>
            <input type="text" class="form-control" id="code" name="supplier.id">
          </div>
        </div>
        <div class="col-md-7 col-sm-7">
          <div class="form-group">
            <label class="control-label" for="supplier"><fmt:message key="label.supplier"/></label>
            <div class="input-group">
              <input type="text" class="form-control" id="supplier" readonly>
              <span class="input-group-btn"><button class="btn btn-default" type="button" data-toggle="modal" data-target="#searchSupplier"><span class="fa fa-search"></span>
              </button></span>
            </div>
          </div>
        </div>
        <div class="form-group">
          <br>
          <input type="submit" type="button" class="btn btn-primary" value="<fmt:message key="button.search"/>">
        </div>
      </div>
    </form>
    <table class="table table-striped table-hover table-condensed" id="searchPurchaseOrderTable">
      <thead>
        <tr>
          <th><fmt:message key="table.code"/></th>
          <th><fmt:message key="table.date"/></th>
          <th><fmt:message key="table.supplier"/></th>
          <th><fmt:message key="table.status"/></th>
          <th><fmt:message key="table.##"/></th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${purchaseOrders}" var="purchaseOrder">
          <tr>
            <td>${purchaseOrder.id}</td>
            <td><fmt:formatDate value="${purchaseOrder.date}" pattern = "dd/MM/YYYY" /></td>
            <td>${purchaseOrder.budget.supplier.person.name}</td>
            <td>${purchaseOrder.status.description}</td>
            <td><a href="/purchasing/ordemCompra/visualizar/${purchaseOrder.id}/normal"><span class="fa fa-eye btn btn-default btn-xs"></span></a>  </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
</html:template>
</body>
<html:searchSupplier/>
<html:jsAssets/>
<html:tableJsAssets/>
<script src="${pageContext.request.contextPath}/asset/js/custom/search-purchase-order.js"></script>
<script src="${pageContext.request.contextPath}/asset/js/custom/modalSearchSupplier.js"></script>
</html>