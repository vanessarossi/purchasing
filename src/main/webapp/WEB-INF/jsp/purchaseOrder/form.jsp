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
          <input type="text" class="form-control" readonly value="${purchaseOrder.purchaseOrder.id}"/>
        </div>
      </div>
      <div class="col-sm-2 col-md-2">
        <div class="form-group">
          <label class="control-label"><fmt:message key="label.date"/></label>
          <input type="text" class="form-control" readonly
                 value="<fmt:formatDate value="${purchaseOrder.purchaseOrder.date}" pattern="dd/MM/YYYY HH:mm"/>"/>
        </div>
      </div>
      <div class="col-sm-5 col-md-5">
        <div class="form-group">
          <label class="control-label"><fmt:message key="label.supplier"/></label>
          <input type="text" class="form-control" readonly
                 value="${purchaseOrder.purchaseOrder.budget.supplier.person.name}"/>
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
              <input type="text" class="form-control" readonly
                     value="${fn:replace(purchaseOrder.purchaseOrder.paymentInformation.totalPrice,"." ,"," )}"/>
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
                <label class="control-label"><fmt:message key="label.supplier"/></label>
                <input type="text" class="form-control" readonly value="${budget.supplier.person.name}"/>
              </div>
            </div>
            <div class="col-sm-2 col-md-2">
              <div class="form-group">
                <label class="control-label"><fmt:message key="label.date"/></label>
                <input type="text" class="form-control" readonly
                       value="<fmt:formatDate value="${budget.date}" pattern="dd/MM/YYYY"/>"/>
              </div>
            </div>
            <div class="col-sm-2 col-md-2">
              <div class="form-group">
                <label class="control-label"><fmt:message key="label.total.price"/></label>
                <input type="text" class="form-control" readonly
                       value="${fn:replace(budget.paymentInformationBudgets[0].paymentInformation.totalPrice,"." ,"," )}"/>
              </div>
            </div>
          </div>
        </c:forEach>
      </div>
    </div>

    <c:if test="${purchaseOrder.purchaseOrder.approval != null && purchaseOrder.purchaseOrder.approval.justificationDisapproval != null}">
      <div class="row">
        <div class="col-sm-12 col-md-12">
          <div class="form-group">
            <label class="control-label"><fmt:message key="title.justification.disapproval"/></label>
            <textarea rows="4" cols="100"
                      class="form-control">${purchaseOrder.purchaseOrder.approval.justificationDisapproval}</textarea>
          </div>
        </div>
      </div>
    </c:if>

    <c:if test="${purchaseOrder.purchaseOrder.budget.quotation.justification != null}">
      <div class="row">
        <div class="col-md-2 col-sm-2">
          <div class="form-group">
            <label class="control-label"><fmt:message key="label.exclusive"/></label>
            <input type="text" class="form-control" readonly
                   value="<c:if test="${purchaseOrder.purchaseOrder.budget.quotation.exclusiveSupplier eq true}"> <fmt:message key="label.yes" /> </c:if>"/>
          </div>
        </div>
        <div class="col-md-10 col-sm-10">
          <div class="form-group">
            <label class="control-label" for="justification"><fmt:message key="label.justification"/></label>
            <textarea rows="4" cols="100" class="form-control"
                      readonly>${purchaseOrder.purchaseOrder.budget.quotation.justification}</textarea>
          </div>
        </div>
      </div>
    </c:if>

    <c:if test="${purchaseOrder.purchaseOrder.approval.justificationDisapproval != null}">
      <div class="row">
        <div class="col-md-12 col-sm-12">
          <div class="form-group">
            <label class="control-label"><fmt:message key="label.justification"/></label>
            <textarea rows="4" cols="100" class="form-control" readonly>${purchaseOrder.purchaseOrder.approval.justificationDisapproval}</textarea>
          </div>
        </div>
      </div>
    </c:if>

    <c:if test="${purchaseOrder.purchaseOrder.approval != null && purchaseOrder.purchaseOrder.approval.firstApproval == true}">
      <div class="row">
        <div class="col-sm-offset-9 col-md-offset-9">
          <div class="form-group">
            <a href='<c:url value="/ordemCompra/aprovar/${purchaseOrder.purchaseOrder.id}"></c:url>' class="btn btn-success"><fmt:message key="button.approve"/></a>
          </div>
        </div>
      </div>
    </c:if>

  </div>
</html:template>
</body>
<html:notification/>
<html:jsAssets/>
<html:tableJsAssets/>
<script src="${pageContext.request.contextPath}/asset/js/custom/form-purchase-order.js"></script>
</html>