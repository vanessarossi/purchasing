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
      <h3><fmt:message key="title.purchasingOrders"/></h3>
    </div>
    <div class="row">
      <div class="col-md-3 col-sm-3">
        <div class="form-group">
          <label  class="control-label" for="status"><fmt:message key="label.status"/></label></span>
          <select id="status" class="form-control">
            <option value=""><fmt:message key="label.select"/></option>
            <c:forEach items="${status}" var="status">
              <option value="${status}">${status.description}</option>
            </c:forEach>
          </select>
        </div>
      </div>
    </div>
    <table id="purchasingOrderTable" class="table table-striped table-hover table-condensed">
      <thead>
      <tr>
        <th style="width: 5%"><fmt:message key="table.code"/></th>
        <th style="width: 50%"><fmt:message key="table.supplier"/></th>
        <th style="width: 15%"><fmt:message key="table.status"/></th>
        <th style="width: 2%"><fmt:message key="table.##"/></th>
        <th style="width: 2%"><fmt:message key="table.##"/></th>
        <th style="width: 2%"><fmt:message key="table.##"/></th>
      </tr>
      </thead>
      <tbody>
      </tbody>
    </table>
    <form action='<c:url value="/ordemCompra/cancelar"></c:url>' method="post" id="cancellPurchasingOrderForm">
      <div class="panel panel-default">
        <div class="panel-heading">
          <h4 class="panel-title">
            <fmt:message key="title.cancell.purchase.order"/>
          </h4>
        </div>
        <div class="panel-body">
          <div class="row">
            <div class="col-md-12 col-sm-12">
              <div class="form-group">
                <label class="control-label" for="justification"><fmt:message key="label.justification"/></label>
                <textarea rows="4" cols="100" class="form-control" name="purchaseOrder.justificationCancellation" id="justification" required></textarea>
              </div>
            </div>
            <input type="hidden" name="purchaseOrder.id" id="purchaseOrder" value=""/>
          </div>
          <div class="row">
            <div class="col-md-offset-9 col-sm-offset-9">
                <input type="submit" value="<fmt:message key="button.save"/>" class="btn btn-success"/>
                <a  class="btn btn-danger" onclick="cancelForm()"><fmt:message key="button.cancel"/><a/>
            </div>
          </div>
        </div>
      </div>
    </form>
</html:template>
</body>
<html:notification/>
<html:jsAssets/>
<html:tableJsAssets/>
<script src="${pageContext.request.contextPath}/asset/js/custom/purchase-order.js"></script>
</html>