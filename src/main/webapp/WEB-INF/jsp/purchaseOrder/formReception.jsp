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
                  <td><input type="text" size="10" /></td>
                  <td>${orderRequest.budgetQuotation.quotationRequest.solicitationRequest.product.unit.description}</td>
                  <td><input type="text" type="text" size="10" readonly value="${fn:replace(orderRequest.budgetQuotation.unityPrice,".",",")}"/></td>
                  <td><input type="text" size="10" /></td>
                </tr>
            </c:forEach>
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
      <c:if test="${purchaseOrder.id != null}">
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
              <select class="form-control" id="meanPayment" name="purchaseOrder.paymentInformation.meanPayment">
                <c:forEach items="${meansPayment}" var="meanPayment">
                  <option value="${meanPayment}" <c:if test="${meanPayment eq purchaseOrder.purchaseOrder.paymentInformation.meanPayment}">selected</c:if> >${meanPayment.description}</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <div class="col-md-2 col-sm-3">
            <div class="form-group">
              <label class="control-label" for="contract"><fmt:message key="label.contract"/></label>
              <br>
              <div class="radio-inline">
                <label class="control-label">
                  <input type="radio" value="true" name="purchaseOrder.paymentInformation.hasContract" id="contract"
                  <c:if test="${purchaseOrder.purchaseOrder.paymentInformation.hasContract eq true}"> checked </c:if>> <fmt:message key="label.yes"/>
                </label>
              </div>
              <div class="radio-inline">
                <label class="control-label">
                  <input type="radio" value="false" name="purchaseOrder.paymentInformation.hasContract" id="contract"
                  <c:if test="${purchaseOrder.purchaseOrder.paymentInformation.hasContract eq false}"> checked </c:if>> <fmt:message key="label.no"/>
                </label>
              </div>
              <br>
            </div>
          </div>
          <div class="col-md-2 col-sm-2">
            <div class="form-group">
              <label class="control-label" for="number_contract"><fmt:message key="label.number_contract"/></label>
              <input type="text" class="form-control" id="number_contract" name="purchaseOrder.paymentInformation.contract.id" value="${purchaseOrder.purchaseOrder.paymentInformation.contract.id}"/>
            </div>
          </div>
          <div class="col-md-3 col-sm-3">
            <div class="form-group">
              <label class="control-label" for="formPayment"><fmt:message key="label.formPayment"/></label>
              <select class="form-control" id="formPayment" name="purchaseOrder.paymentInformation.formPayment.id" required>
                <c:forEach items="${formsPayment}" var="formPayment">
                  <option value="${formPayment.id}" <c:if test="${formPayment.id eq purchaseOrder.purchaseOrder.paymentInformation.formPayment.id}"> selected </c:if> >${formPayment.description}</option>
                </c:forEach>
              </select>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-2 col-sm-2">
            <div class="form-group">
              <label class="control-label" for="totalPrice"><fmt:message key="label.totalPrice"/></label>
              <input type="text" class="form-control" id="totalPrice" name="purchaseOrder.paymentInformation.totalPrice" value="${fn:replace(purchaseOrder.purchaseOrder.paymentInformation.totalPrice,"." ,"," )}" readonly>
            </div>
          </div>
          <div class="col-md-2 col-sm-2">
            <div class="form-group">
              <label class="control-label" for="discountPercentage"><fmt:message key="label.discountPercentage"/></label>
              <input type="text" class="form-control" id="discountPercentage" name="purchaseOrder.paymentInformation.discountPercentage" value="${purchaseOrder.purchaseOrder.paymentInformation.discountPercentage}">
            </div>
          </div>
          <div class="col-md-2 col-sm-2">
            <div class="form-group">
              <label class="control-label" for="totalFinalPrice"><fmt:message key="label.totalFinalPrice"/></label>
              <input type="text" class="form-control" id="totalFinalPrice" name="purchaseOrder.paymentInformation.totalFinalPrice" value="${fn:replace(purchaseOrder.purchaseOrder.paymentInformation.totalFinalPrice,"." ,"," )}" readonly>
            </div>
          </div>
          <div class="col-md-2 col-sm-2">
            <div class="form-group">
              <label class="control-label" for="inputPrice"><fmt:message key="label.inputPrice"/></label>
              <input type="text" class="form-control" id="inputPrice" name="purchaseOrder.paymentInformation.inputPrice" value="${fn:replace(purchaseOrder.purchaseOrder.paymentInformation.inputPrice,"." ,"," ) }"/>
            </div>
          </div>
          <div class="col-md-2 col-sm-2">
            <div class="form-group">
              <label class="control-label" for="sharePrice"><fmt:message key="label.sharePrice"/></label>
              <input type="text" class="form-control" id="sharePrice" name="purchaseOrder.paymentInformation.sharePrice" value="${fn:replace(purchaseOrder.purchaseOrder.paymentInformation.sharePrice, ".","," )  }" readonly/>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-2 col-sm-2">
            <div class="form-group">
              <label class="control-label" for="dateInput"><fmt:message key="label.dateInput"/></label>
              <input type="text" class="form-control date" id="dateInput" name="purchaseOrder.paymentInformation.dateInput" value="<fmt:formatDate value="${purchaseOrder.purchaseOrder.paymentInformation.dateInput}" pattern="dd/MM/YYYY"/>">
            </div>
          </div>
          <div class="col-md-2 col-sm-2">
            <div class="form-group">
              <label class="control-label" for="dateFirstInstallment"><fmt:message key="label.dateFirstInstallment"/></label>
              <input type="text" class="form-control date" id="dateFirstInstallment" name="purchaseOrder.paymentInformation.dateFirstInstallment" value="<fmt:formatDate value="${purchaseOrder.purchaseOrder.paymentInformation.dateFirstInstallment}" pattern="dd/MM/YYYY"/>">
            </div>
          </div>
          <div class="col-md-2 col-sm-2">
            <div class="form-group">
              <label class="control-label" for="dateLastInstallment"><fmt:message key="label.dateLastInstallment"/></label>
              <input type="text" class="form-control date" id="dateLastInstallment" name="purchaseOrder.paymentInformation.dateLastInstallment" value="<fmt:formatDate value="${purchaseOrder.purchaseOrder.paymentInformation.dateLastInstallment}" pattern="dd/MM/YYYY"/>">
            </div>
          </div>
          <div class="col-md-2 col-sm-2">
            <div class="form-group">
              <label class="control-label" for="expirationDate"><fmt:message key="label.expiration_date"/></label>
              <input type="text" class="form-control date" id="expirationDate" name="purchaseOrder.paymentInformation.expirationDate" value="<fmt:formatDate value="${purchaseOrder.purchaseOrder.paymentInformation.expirationDate}" pattern="dd/MM/YYYY"/>"/>
            </div>
          </div>
          <div class="col-md-2 col-sm-3">
            <div class="form-group">
              <label class="control-label" for="contract"><fmt:message key="label.alreadyPurchased"/></label>
              <br>
              <div class="radio-inline">
                <label class="control-label">
                  <input type="radio" value="true" name="purchaseOrder.alreadyPurchased" id="alreadyPurchased"
                  <c:if test="${purchaseOrder.purchaseOrder.alreadyPurchased eq true}"> checked </c:if>> <fmt:message key="label.yes"/>
                </label>
              </div>
              <div class="radio-inline">
                <label class="control-label">
                  <input type="radio" value="false" name="purchaseOrder.alreadyPurchased" id="alreadyPurchased"
                  <c:if test="${purchaseOrder.purchaseOrder.alreadyPurchased eq false}"> checked </c:if>> <fmt:message key="label.no"/>
                </label>
              </div>
              <br>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</html:template>
</body>
<html:jsAssets/>
<html:tableJsAssets/>
<script src="${pageContext.request.contextPath}/asset/js/custom/purchase-order.js"></script>
</html>