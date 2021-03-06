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
  <form action='' method="post" id="confirmReceptionForm">
    <div class="row">
      <div class="col-md-12 col-sm-12 ">
        <label class="control-label"><fmt:message key="message.warming.tax.document"/></label>
        <br>
      </div>
    </div>
      <div class="row">
        <div class="col-md-3 col-sm-3 ">
          <label class="control-label"><fmt:message key="label.tax.document"/></label>
          <input type="text" class="form-control" name="reception.taxDocument" id="taxDocument" onkeyup="validNumber(this)" required/>
        </div>
        <div class="col-md-5 col-sm-5 ">
          <label class="control-label"><fmt:message key="label.bar.codetax.document"/></label>
          <input type="text" class="form-control" name="reception.barCodeTaxDocument"/>
        </div>
        <input type="hidden" name="reception.purchaseOrder.id" value="${purchaseOrder.id}" >
      </div>
      <br>
      <c:if test="${purchaseOrder.budget.quotation.type eq 'Material'}">
        <div class="panel panel-default">
          <div class="panel-body">
            <table class="table" id="tableProduct">
              <thead>
              <tr>
                <th><fmt:message key="table.costCenter"/></th>
                <th><fmt:message key="table.product"/></th>
                <th><fmt:message key="table.abbreviatedQuantity"/></th>
                <th><fmt:message key="table.unit"/></th>
                <th><fmt:message key="table.unitary.price"/></th>
                <th><fmt:message key="table.total.price"/></th>
                <th></th>
              </tr>
              </thead>
                <tbody>
                  <c:forEach items="${purchaseOrder.orderRequests}" var="orderRequest" varStatus="i">
                      <tr>
                        <input type="hidden" name="reception.requestDelivereds[${i.index}].orderRequest.id" id="orderRequestId${i.index}" value="${orderRequest.id}" />
                        <td>${orderRequest.budgetQuotation.quotationRequest.solicitationRequest.solicitation.costCenter.description}</td>
                        <td>${orderRequest.budgetQuotation.quotationRequest.solicitationRequest.product.description} ${orderRequest.budgetQuotation.quotationRequest.solicitationRequest.product.model} ${orderRequest.budgetQuotation.quotationRequest.solicitationRequest.product.mark}</td>
                        <td><input type="text" size="8" name="reception.requestDelivereds[${i.index}].quantity" id="quantity${i.index}"  onblur="calculateTotalPriceMaterial(${i.index},${fn:length(purchaseOrder.orderRequests)} - 1)" /></td>
                        <td>${orderRequest.budgetQuotation.quotationRequest.solicitationRequest.product.unit.description}</td>
                        <td><input type="text" id="unityPrice${i.index}" size="10" readonly value="${fn:replace(orderRequest.budgetQuotation.unityPrice,".",",")}"/></td>
                        <td><input type="text" id="totalPriceMaterial${i.index}" size="10" readonly /></td>
                        <td style="text-align: center" id="check${i.index}"></td>
                      </tr>
                  </c:forEach>
                </tbody>
              <tfoot>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
                <th><input type="text" id="totalFinalPriceMaterial" size="10" value="" readonly /></th>
                <th></th>
              </tfoot>
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
              <th><fmt:message key="table.costCenter"/></th>
              <th><fmt:message key="table.service"/></th>
              <th><fmt:message key="table.service.price"/></th>
              <th><fmt:message key="table.complete.recep"/></th>
              <th><fmt:message key="table.incomplete.recep"/></th>
              <th><fmt:message key="table.imcomplete.service.price"/></th>
            </tr>
            </thead>
            <tbody>
              <c:forEach items="${purchaseOrder.orderRequests}" var="orderRequest" varStatus="i">
                <tr>
                  <td>${orderRequest.budgetQuotation.quotationRequest.solicitationRequest.solicitation.costCenter.description}</td>
                  <td>${orderRequest.budgetQuotation.quotationRequest.solicitationRequest.service.description}</td>
                  <td>
                    ${fn:replace(orderRequest.budgetQuotation.unityPrice,".",",")}
                      <input type="hidden" id="priceService${i.index}" value="${fn:replace(orderRequest.budgetQuotation.unityPrice,".",",")}"  readonly />
                      <input type="hidden" name="reception.requestDelivereds[${i.index}].orderRequest.id" id="orderRequestService${i.index}" value="${orderRequest.id}" />
                  </td>
                  <td><input type="checkbox" id="serviceComplete${i.index}" onclick="desableImcompletePrice(${i.index},${fn:length(purchaseOrder.orderRequests)} - 1)" /></td>
                  <td><input type="checkbox" id="serviceImcomplete${i.index}" onclick="enableImcompletePrice(${i.index})"/></td>
                  <td><input type="text" size="8" name="reception.requestDelivereds[${i.index}].price" id="price${i.index}" onblur="calculateTotalPriceService(${i.index},${fn:length(purchaseOrder.orderRequests)} - 1)"/></td>
                </tr>
              </c:forEach>
            </tbody>
            <tfoot>
              <th></th>
              <th></th>
              <th></th>
              <th></th>
              <th><fmt:message key="table.total.price"/></th>
              <th><input type="text" id="totalFinalPriceService" size="10" value="" readonly /></th>
            </tfoot>
          </table>
          </div>
      </div>
      </c:if>
    <div class="alert alert-info" role="alert">
      <p id="resultMessage"></p>
    </div>
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
                  <input type="text" class="form-control" id="discountPercentage"  value="${fn:replace(purchaseOrder.paymentInformation.discountPercentage,".",",")}" readonly>
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
                  <label class="control-label" for="freight"><fmt:message key="label.freight"/></label>
                  <input type="text" class="form-control" id="freight" value="${purchaseOrder.paymentInformation.freight}" readonly>
                </div>
              </div>
              <div class="col-md-2 col-sm-2">
                <div class="form-group">
                  <label class="control-label" for="totalFinalPrice"><fmt:message key="label.totalFinalPrice"/></label>
                  <input type="text" class="form-control" id="totalFinalPrice"  value="${fn:replace(purchaseOrder.paymentInformation.totalFinalPrice,"." ,"," )}" readonly>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-2 col-sm-2">
                <div class="form-group">
                  <label class="control-label" for="sharePrice"><fmt:message key="label.sharePrice"/></label>
                  <input type="text" class="form-control" id="sharePrice"  value="${fn:replace(purchaseOrder.paymentInformation.sharePrice, ".","," )  }" readonly >
                </div>
              </div>
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
                  <label class="control-label" for="expirationDate"><fmt:message key="label.expiration.date"/></label>
                  <input type="text" class="form-control" id="expirationDate" value="<fmt:formatDate value="${purchaseOrder.paymentInformation.expirationDate}" pattern="dd/MM/YYYY"/>" readonly>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-2 col-sm-3">
                <div class="form-group">
                  <label class="control-label" for="alreadyPurchased"><fmt:message key="label.alreadyPurchased"/></label>
                  <input type="text" class="form-control"  id="alreadyPurchased" value=" <c:if test="${purchaseOrder.alreadyPurchased eq true}"> <fmt:message key="label.yes"/> </c:if>" readonly>
                </div>
              </div>
              <div class="col-md-3 col-sm-4">
                <div class="form-group">
                  <label class="control-label" for="investmentPurchase"><fmt:message key="label.investmentPurchase"/></label>
                  <input type="text" class="form-control"  id="investmentPurchase" value=" <c:if test="${purchaseOrder.investmentPurchase eq true}"> <fmt:message key="label.yes"/> </c:if>" readonly>
                </div>
              </div>
            </div>
          </div>
        </div>
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
                <textarea rows="4" cols="100" class="form-control" id="observation" name="reception.observation">${reception.observation}</textarea>
              </div>
            </div>
          </div>
        </div>
        <c:if test="${purchaseOrder.status != 'Finished'}">
          <div class="row">
            <div class="col-sm-offset-10 col-md-offset-10">
              <div class="form-group">
                <input type="button" class="btn btn-primary" id="confered" value="<fmt:message key="button.confered"/>" >
                <input type="button" class="btn btn-success" id="finalize" value="<fmt:message key="button.finalize"/>" >
              </div>
            </div>
          </div>
        </c:if>
      </c:if>
    </form>
</html:template>
</body>
<html:notification/>
<html:jsAssets/>
<html:tableJsAssets/>
<script src="${pageContext.request.contextPath}/asset/js/vendor/jquery.mask.min.js"></script>
<script src="${pageContext.request.contextPath}/asset/js/vendor/price_format.2.0.min.js"></script>
<script src="${pageContext.request.contextPath}/asset/js/custom/form-purchase-order-reception.js"></script>
</html>