<!DOCTYPE html>
<html lang="pt-br">
<head>
  <title><fmt:message key="title.page.purchaseOrder.conference"/> - <fmt:message key="title.purchasing"/></title>
  <html:head/>
  <html:tableAssets/>
</head>
<body>
<html:template>
    <div class="page-header">
      <h3><fmt:message key="title.purchasing.order.conference"/></h3>
    </div>
    <c:if test="${reception.purchaseOrder.budget.quotation.type == 'Material'}">
      <table class="table table-striped table-hover table-condensed">
        <thead>
        <tr>
          <th><fmt:message key="table.product"/></th>
          <th><fmt:message key="table.costCenter"/></th>
          <th><fmt:message key="table.abbreviatedQuantity"/></th>
          <th><fmt:message key="table.abbreviatedQuantityDelivered"/></th>
          <th><fmt:message key="table.unitary.price"/></th>
        </tr>
        </thead>
        <tbody>
          <c:forEach items="${reception.requestDelivereds}" var="requestDelivered">
            <tr>
              <td>${requestDelivered.orderRequest.budgetQuotation.quotationRequest.solicitationRequest.product.description} ${requestDelivered.orderRequest.budgetQuotation.quotationRequest.solicitationRequest.product.model} ${requestDelivered.orderRequest.budgetQuotation.quotationRequest.solicitationRequest.product.mark}</td>
              <td>${requestDelivered.orderRequest.budgetQuotation.quotationRequest.solicitationRequest.solicitation.costCenter.description}</td>
              <td>${requestDelivered.orderRequest.budgetQuotation.quotationRequest.solicitationRequest.quantity}</td>
              <td>${requestDelivered.quantity}</td>
              <td>${fn:replace(requestDelivered.orderRequest.budgetQuotation.unityPrice,".",",")}</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </c:if>

  <c:if test="${reception.purchaseOrder.budget.quotation.type == 'Service'}">
      <table class="table table-striped table-hover table-condensed">
        <thead>
        <tr>
          <th><fmt:message key="table.description"/></th>
          <th><fmt:message key="table.unitary.price"/></th>
          <th><fmt:message key="table.imcomplete.service.price"/></th>
        </tr>
        </thead>
        <tbody>
          <c:forEach items="${reception.requestDelivereds}" var="requestDelivered">
            <tr>
              <td>${requestDelivered.orderRequest.budgetQuotation.quotationRequest.solicitationRequest.service.description}</td>
              <td>${fn:replace(requestDelivered.orderRequest.budgetQuotation.unityPrice,".",",")}</td>
              <td>${fn:replace(requestDelivered.price,".",",")}</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </c:if>

    <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <fmt:message key="title.observation.reception"/>
        </h4>
      </div>
      <div class="panel-body">
        <div class="col-md-12 col-sm-12">
          <div class="form-group">
            <label  class="control-label" for="observation"><fmt:message key="label.observation"/></label>
            <textarea rows="4" cols="100" class="form-control" id="observation" name="reception.observation" readonly>${reception.observation}</textarea>
          </div>
        </div>
      </div>
    </div>

    <form action='<c:url value="/ordemCompra/finalizar/conferencia"></c:url>' method="post" id="finishedConferenceForm">
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
                <select class="form-control" id="meanPayment" name="reception.paymentInformation.meanPayment">
                  <c:forEach items="${meansPayment}" var="meanPayment">
                    <option value="${meanPayment}">${meanPayment.description}</option>
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
                    <input type="radio" value="true" name="reception.paymentInformation.hasContract" id="contract"><fmt:message key="label.yes"/>
                  </label>
                </div>
                <div class="radio-inline">
                  <label class="control-label">
                    <input type="radio" value="false" name="reception.paymentInformation.hasContract" id="contract"> <fmt:message key="label.no"/>
                  </label>
                </div>
                <br>
              </div>
            </div>
            <div class="col-md-2 col-sm-2">
              <div class="form-group">
                <label class="control-label" for="number_contract"><fmt:message key="label.number_contract"/></label>
                <input type="text" class="form-control" id="number_contract" name="reception.paymentInformation.contract.id"/>
              </div>
            </div>
            <div class="col-md-3 col-sm-3">
              <div class="form-group">
                <label class="control-label" for="formPayment"><fmt:message key="label.formPayment"/></label>
                <select class="form-control" id="formPayment" name="reception.paymentInformation.formPayment.id" required>
                  <option value=""><fmt:message key="label.select"/></option>
                  <c:forEach items="${formsPayment}" var="formPayment">
                    <option value="${formPayment.id}">${formPayment.description}</option>
                  </c:forEach>
                </select>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-md-2 col-sm-2">
              <div class="form-group">
                <label class="control-label" for="totalPrice"><fmt:message key="label.totalPrice"/></label>
                <input type="text" class="form-control" id="totalPrice" name="reception.paymentInformation.totalPrice" value="${fn:replace(totalPrice,".","," )}" readonly>
              </div>
            </div>
            <div class="col-md-2 col-sm-2">
              <div class="form-group">
                <label class="control-label" for="discountPercentage"><fmt:message key="label.discountPercentage"/></label>
                <input type="text" class="form-control" id="discountPercentage" name="reception.paymentInformation.discountPercentage">
              </div>
            </div>
            <div class="col-md-2 col-sm-2">
              <div class="form-group">
                <label class="control-label" for="inputPrice"><fmt:message key="label.inputPrice"/></label>
                <input type="text" class="form-control" id="inputPrice" name="reception.paymentInformation.inputPrice">
              </div>
            </div>
            <div class="col-md-2 col-sm-2">
              <div class="form-group">
                <label class="control-label" for="freight"><fmt:message key="label.freight"/></label>
                <input type="text" class="form-control" id="freight" name="reception.paymentInformation.freight">
              </div>
            </div>
            <div class="col-md-2 col-sm-2">
              <div class="form-group">
                <label class="control-label" for="totalFinalPrice"><fmt:message key="label.totalFinalPrice"/></label>
                <input type="text" class="form-control" id="totalFinalPrice" name="reception.paymentInformation.totalFinalPrice" readonly>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-md-2 col-sm-2">
              <div class="form-group">
                <label class="control-label" for="sharePrice"><fmt:message key="label.sharePrice"/></label>
                <input type="text" class="form-control" id="sharePrice" name="reception.paymentInformation.sharePrice" readonly>
              </div>
            </div>
            <div class="col-md-2 col-sm-2">
              <div class="form-group">
                <label class="control-label" for="dateInput"><fmt:message key="label.dateInput"/></label>
                <input type="text" class="form-control date" id="dateInput" name="reception.paymentInformation.dateInput">
              </div>
            </div>
            <div class="col-md-2 col-sm-2">
              <div class="form-group">
                <label class="control-label" for="dateFirstInstallment"><fmt:message key="label.dateFirstInstallment"/></label>
                <input type="text" class="form-control date" id="dateFirstInstallment" name="reception.paymentInformation.dateFirstInstallment">
              </div>
            </div>
            <div class="col-md-2 col-sm-2">
              <div class="form-group">
                <label class="control-label" for="dateLastInstallment"><fmt:message key="label.dateLastInstallment"/></label>
                <input type="text" class="form-control date" id="dateLastInstallment" name="reception.paymentInformation.dateLastInstallment">
              </div>
            </div>
            <div class="col-md-2 col-sm-2">
              <div class="form-group">
                <label class="control-label" for="expirationDate"><fmt:message key="label.expiration.date"/></label>
                <input type="text" class="form-control date" id="expirationDate" name="reception.paymentInformation.expirationDate">
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-sm-offset-11 col-md-offset-11">
              <div class="form-group">
                <input type="hidden" name="reception.purchaseOrder.id" value="${reception.purchaseOrder.id}">
                <input type="hidden" name="reception.id" value="${reception.id}">
                <button type="submit" class="btn btn-success"><fmt:message key="button.save" /></button>
              </div>
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
<script src="${pageContext.request.contextPath}/asset/js/custom/form-purchase-order-confirm-conference.js"></script>
<script src="${pageContext.request.contextPath}/asset/js/vendor/jquery.mask.min.js"></script>
<script src="${pageContext.request.contextPath}/asset/js/commons/formCommons.js"></script>
<script src="${pageContext.request.contextPath}/asset/js/vendor/price_format.2.0.min.js"></script>
<script src="${pageContext.request.contextPath}/asset/js/vendor/moment.js"></script>
</html>