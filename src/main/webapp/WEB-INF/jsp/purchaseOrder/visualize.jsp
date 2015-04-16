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
      <h3><fmt:message key="title.purchasing.order"/></h3>
    </div>
    <div class="row">
      <div class="col-sm-2 col-md-2">
        <div class="form-group">
         <label class="control-label"><fmt:message key="label.number"/></label>
         <input type="text" class="form-control" readonly value="${purchaseOrder.purchaseOrder.id}" />
        </div>
      </div>
      <div class="col-sm-2 col-md-2">
        <div class="form-group">
          <label class="control-label"><fmt:message key="label.date"/></label>
          <input type="text" class="form-control" readonly value="<fmt:formatDate value="${purchaseOrder.purchaseOrder.date}" pattern="dd/MM/YYYY HH:mm"/>"/>
        </div>
      </div>
      <div class="col-sm-5 col-md-5">
        <div class="form-group">
          <label class="control-label"><fmt:message key="label.supplier"/></label>
          <input type="text" class="form-control" readonly value="${purchaseOrder.purchaseOrder.budget.supplier.person.name}"/>
        </div>
      </div>
    </div>
    <div class="panel panel-default">
      <div class="panel-body">
          <c:if test="${purchaseOrder.purchaseOrder.budget.quotation.type == 'Material'}">
            <table id="orderRequestProductTable" class="table table-striped table-hover table-condensed">
              <thead>
                <tr>
                    <th style="width: 50%"><fmt:message key="table.product"/></th>
                    <th style="width: 10%"><fmt:message key="table.abbreviatedQuantity"/></th>
                    <th style="width: 10%"><fmt:message key="table.unit"/></th>
                    <th style="width: 10%"><fmt:message key="table.unitary.price"/></th>
                    <th style="width: 10%"><fmt:message key="table.total.price"/></th>
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${purchaseOrder.orderRequestProductViews}" var="orderRequest">
                  <tr>
                    <td>${orderRequest.product.description} ${orderRequest.product.model} ${orderRequest.product.mark}</td>
                    <td>${orderRequest.quantity}</td>
                    <td>${orderRequest.product.unit.description}</td>
                    <td>${fn:replace(orderRequest.unityPrice,"." ,"," )}</td>
                    <td>${fn:replace(orderRequest.totalPrice,"." ,"," )}</td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
          </c:if>
          <c:if test="${purchaseOrder.purchaseOrder.budget.quotation.type == 'Service'}">
            <table id="orderRequestServiceTable" class="table table-striped table-hover table-condensed">
              <thead>
                <tr>
                  <th style="width: 50%"><fmt:message key="table.description"/></th>
                  <th style="width: 10%"><fmt:message key="table.unitary.price"/></th>
                  <th style="width: 10%"><fmt:message key="table.total.price"/></th>
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${purchaseOrder.orderRequestServiceViews}" var="orderRequest">
                  <tr>
                    <td>${orderRequest.service.description}</td>
                    <td>${fn:replace(orderRequest.unityPrice,"." ,"," )}</td>
                    <td>${fn:replace(orderRequest.unityPrice,"." ,"," )}</td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
          </c:if>
        <div class="row">
          <div class="col-sm-2 col-md-2 col-md-offset-10 col-sm-offset-10">
            <div class="form-group">
              <label class="control-label"><fmt:message key="label.total.price"/></label>
              <input type="text" class="form-control" readonly value="${fn:replace(purchaseOrder.purchaseOrder.paymentInformation.totalPrice,"." ,"," )}"/>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="panel panel-default">
      <div class="panel-heading">
        <h3 class="panel-title"><fmt:message key="title.budgets"/></h3>
      </div>
      <div class="panel-body">
        <c:forEach items="${purchaseOrder.purchaseOrder.budget.quotation.budgets}" var="budget">
          <div class="row">
            <div class="col-sm-5 col-md-5">
              <div class="form-group">
                  <label class="control-label"><fmt:message key="label.supplier" /></label>
                  <input type="text" class="form-control" readonly value="${budget.supplier.person.name}" />
              </div>
            </div>
            <div class="col-sm-2 col-md-2">
              <div class="form-group">
                <label class="control-label"><fmt:message key="label.date"/></label>
                <input type="text" class="form-control" readonly value="<fmt:formatDate value="${budget.date}" pattern="dd/MM/YYYY"/>"/>
              </div>
            </div>
            <div class="col-sm-2 col-md-2">
              <div class="form-group">
                <label class="control-label"><fmt:message key="label.total.price"/></label>
                <input type="text" class="form-control" readonly value="${budget.paymentInformationBudgets[0].paymentInformation.totalPrice}"/>
              </div>
            </div>
          </div>
        </c:forEach>
      </div>
    </div>
  </div>
</html:template>
</body>
<html:jsAssets/>
<html:tableJsAssets/>
<c:if test="${purchaseOrder.purchaseOrder.budget.quotation.type == 'Material'}">
  <script src="${pageContext.request.contextPath}/asset/js/custom/view-purchase-order-product.js"></script>
</c:if>
<c:if test="${purchaseOrder.purchaseOrder.budget.quotation.type == 'Service'}">
  <script src="${pageContext.request.contextPath}/asset/js/custom/view-purchase-order-service.js"></script>
</c:if>
</html>