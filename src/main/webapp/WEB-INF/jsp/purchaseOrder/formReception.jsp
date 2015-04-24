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
      <h3><fmt:message key="title.purchasing.order.reception"/></h3>
    </div>
    <form action='<c:url value="/ordemCompra/pesquisar/conferencia"></c:url>' method="post" id="receptionForm">
      <div class="row">
        <div class="col-md-2 col-sm-2 ">
          <label class="control-label"><fmt:message key="label.code"/></label>
          <input type="text" class="form-control" name="purchaseOrder.id" value="${purchaseOrder.id}"/>
        </div>
        <div class="col-sm-offset-2 col-md-offset-2">
          <div class="form-group">
            </br>
            <button type="submit" class="btn btn-success"><fmt:message key="button.search"/></button>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-5 col-sm-5 ">
          <label class="control-label"><fmt:message key="label.supplier"/></label></br>
          <span>${purchaseOrder.budget.supplier.person.name}</span>
        </div>
        <div class="col-md-2 col-sm-2 ">
          <label class="control-label"><fmt:message key="label.date"/></label></br>
          <fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${purchaseOrder.dateGenerate}" var="date"/>
          <span>${date}</span>
        </div>
      </div>
    </form>

    <form action='<c:url value="/ordemCompra/"></c:url>' method="post" id="confirmReceptionForm">
      <c:if test="${purchaseOrder.budget.quotation.type eq 'Material'}">
        <table class="table" id="tableProduct">
          <thead>
          <tr>
            <th style="width:10%"><fmt:message key="table.costCenter"/></th>
            <th style="width:50%"><fmt:message key="table.product"/></th>
            <th style="width:5%"><fmt:message key="table.abbreviatedQuantity"/></th>
            <th style="width:6%"><fmt:message key="table.unit"/></th>
            <th style="width:1%"></th>
          </tr>
          </thead>
          <tbody>
            <c:if test="${purchaseOrder.orderRequests}" var="orderRequest">
                <tr>
                  <td>${orderRequest.budgetQuotation.quotationRequest.solicitationRequest.solicitation.costCenter}</td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                </tr>
            </c:if>
          </tbody>
        </table>
      </c:if>
      <c:if test="${purchaseOrder.budget.quotation.type eq 'Service'}">
        <table class="table" id="tableService">
          <thead>
          <tr>
            <th style="width:15%"><fmt:message key="table.costCenter"/></th>
            <th style="width:70%"><fmt:message key="table.service"/></th>
            <th style="width:1%"></th>
          </tr>
          </thead>
          <tbody>
          </tbody>
        </table>
      </c:if>
    </form>
  </div>
</html:template>
</body>
<html:notification/>
<html:jsAssets/>
<html:tableJsAssets/>
<script src="${pageContext.request.contextPath}/asset/js/custom/purchase-order.js"></script>
</html>