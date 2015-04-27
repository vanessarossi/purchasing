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
    <br>
    <form action='<c:url value="/ordemCompra/"></c:url>' method="post" id="confirmReceptionForm">
      <c:if test="${purchaseOrder.budget.quotation.type eq 'Material'}">
        <div class="panel panel-default">
          <div class="panel-body">
            <table class="table" id="tableProduct">
          <thead>
          <tr>
            <th style="width:20%"><fmt:message key="table.costCenter"/></th>
            <th style="width:50%"><fmt:message key="table.product"/></th>
            <th style="width:5%"><fmt:message key="table.abbreviatedQuantity"/></th>
            <th style="width:6%"><fmt:message key="table.unit"/></th>
            <th style="width:5%"><fmt:message key="table.unitary.price"/></th>
            <th style="width:5%"><fmt:message key="table.total.price"/></th>
          </tr>
          </thead>
          <tbody>
            <c:forEach items="${purchaseOrder.orderRequests}" var="orderRequest">
                <tr>
                  <td>${orderRequest.budgetQuotation.quotationRequest.solicitationRequest.solicitation.costCenter.description}</td>
                  <td>${orderRequest.budgetQuotation.quotationRequest.solicitationRequest.product.description} ${orderRequest.budgetQuotation.quotationRequest.solicitationRequest.product.model} ${orderRequest.budgetQuotation.quotationRequest.solicitationRequest.product.mark}</td>
                  <td><input type="text" size="8" /></td>
                  <td>${orderRequest.budgetQuotation.quotationRequest.solicitationRequest.product.unit.description}</td>
                  <td><input type="text" type="text" size="10" readonly value="${fn:replace(orderRequest.budgetQuotation.unityPrice,".",",")}"/></td>
                  <td><input type="text" size="10" /></td>
                </tr>
            </c:forEach>
          </tbody>
        </table>
          </div>
       </div>
      </c:if>
      <c:if test="${purchaseOrder.budget.quotation.type eq 'Service'}">
      <div class="panel panel-default">
        <div class="panel-body">
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
        </div>
      </div>
      </c:if>
      <c:if test="${purchaseOrder.id != null}">
        <div class="panel panel-default">
          <div class="panel-heading">
            <h4 class="panel-title">
              <fmt:message key="title.information.payment"/>
            </h4>
          </div>
          <div class="panel-body">
            <div class="row">
              <div class="col-md-2 col-sm-2">
                <div class="form-group">
                  <label class="control-label" for="meanPayment"><fmt:message key="label.meanPayment"/></label>
                  <input type="text" class="form-control" id="meanPayment"  readonly value="${purchaseOrder.paymentInformation.meanPayment.description}"/>
                </div>
              </div>
              <div class="col-md-2 col-sm-3">
                <div class="form-group">
                  <label class="control-label" for="contract"><fmt:message key="label.contract"/></label>
                  <input type="text" class="form-control" id="contract" readonly value=" <c:if test="${purchaseOrder.paymentInformation.hasContract eq true}"> <fmt:message key="label.yes"/> </c:if>" />
                </div>
              </div>
              <div class="col-md-2 col-sm-2">
                <div class="form-group">
                  <label class="control-label" for="number_contract"><fmt:message key="label.number_contract"/></label>
                  <input type="text" class="form-control" id="number_contract" readonly value="${purchaseOrder.paymentInformation.contract.id}"/>
                </div>
              </div>
              <div class="col-md-3 col-sm-3">
                <div class="form-group">
                  <label class="control-label" for="formPayment"><fmt:message key="label.formPayment"/></label>
                  <input type="text" class="form-control" id="formPayment" readonly value="${purchaseOrder.paymentInformation.formPayment.description}" />
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-2 col-sm-2">
                <div class="form-group">
                  <label class="control-label" for="totalPrice"><fmt:message key="label.totalPrice"/></label>
                  <input type="text" class="form-control" id="totalPrice"  value="${fn:replace(purchaseOrder.paymentInformation.totalPrice,"." ,"," )}" readonly>
                </div>
              </div>
              <div class="col-md-2 col-sm-2">
                <div class="form-group">
                  <label class="control-label" for="discountPercentage"><fmt:message key="label.discountPercentage"/></label>
                  <input type="text" class="form-control" id="discountPercentage"  value="${purchaseOrder.paymentInformation.discountPercentage}" readonly>
                </div>
              </div>
              <div class="col-md-2 col-sm-2">
                <div class="form-group">
                  <label class="control-label" for="totalFinalPrice"><fmt:message key="label.totalFinalPrice"/></label>
                  <input type="text" class="form-control" id="totalFinalPrice"  value="${fn:replace(purchaseOrder.paymentInformation.totalFinalPrice,"." ,"," )}" readonly>
                </div>
              </div>
              <div class="col-md-2 col-sm-2">
                <div class="form-group">
                  <label class="control-label" for="inputPrice"><fmt:message key="label.inputPrice"/></label>
                  <input type="text" class="form-control" id="inputPrice"  value="${fn:replace(purchaseOrder.paymentInformation.inputPrice,"." ,"," ) }" readonly >
                </div>
              </div>
              <div class="col-md-2 col-sm-2">
                <div class="form-group">
                  <label class="control-label" for="sharePrice"><fmt:message key="label.sharePrice"/></label>
                  <input type="text" class="form-control" id="sharePrice"  value="${fn:replace(purchaseOrder.paymentInformation.sharePrice, ".","," )  }" readonly >
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-2 col-sm-2">
                <div class="form-group">
                  <label class="control-label" for="dateInput"><fmt:message key="label.dateInput"/></label>
                  <input type="text" class="form-control " id="dateInput"  value="<fmt:formatDate value="${purchaseOrder.paymentInformation.dateInput}" pattern="dd/MM/YYYY"/>" readonly>
                </div>
              </div>
              <div class="col-md-2 col-sm-2">
                <div class="form-group">
                  <label class="control-label" for="dateFirstInstallment"><fmt:message key="label.dateFirstInstallment"/></label>
                  <input type="text" class="form-control" id="dateFirstInstallment"  value="<fmt:formatDate value="${purchaseOrder.paymentInformation.dateFirstInstallment}" pattern="dd/MM/YYYY"/>" readonly>
                </div>
              </div>
              <div class="col-md-2 col-sm-2">
                <div class="form-group">
                  <label class="control-label" for="dateLastInstallment"><fmt:message key="label.dateLastInstallment"/></label>
                  <input type="text" class="form-control" id="dateLastInstallment" value="<fmt:formatDate value="${purchaseOrder.paymentInformation.dateLastInstallment}" pattern="dd/MM/YYYY"/>" readonly>
                </div>
              </div>
              <div class="col-md-2 col-sm-2">
                <div class="form-group">
                  <label class="control-label" for="expirationDate"><fmt:message key="label.expiration_date"/></label>
                  <input type="text" class="form-control" id="expirationDate" value="<fmt:formatDate value="${purchaseOrder.paymentInformation.expirationDate}" pattern="dd/MM/YYYY"/>" readonly>
                </div>
              </div>
              <div class="col-md-2 col-sm-3">
                <div class="form-group">
                  <label class="control-label" for="alreadyPurchased"><fmt:message key="label.alreadyPurchased"/></label>
                  <input type="text" class="form-control"  id="alreadyPurchased" value=" <c:if test="${purchaseOrder.alreadyPurchased eq true}"> <fmt:message key="label.yes"/> </c:if>" readonly>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-sm-offset-10 col-md-offset-10">
            <div class="form-group">
              <input type="submit" class="btn btn-primary" value="<fmt:message key="button.confered"/>" >
              <input type="submit" class="btn btn-success" value="<fmt:message key="button.finalize"/>" >
            </div>
          </div>
        </div>
      </c:if>
    </form>
  </div>
</html:template>
</body>
<html:jsAssets/>
<html:tableJsAssets/>
<script src="${pageContext.request.contextPath}/asset/js/custom/purchase-order.js"></script>
</html>