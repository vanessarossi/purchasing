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
      <div class="col-sm-2 col-md-2">
        <div class="form-group">
            <label><fmt:message key="label.quotation" /></label>
            <input type="text" class="form-control" readonly value="${purchaseOrder.purchaseOrder.budget.quotation.id}"/>
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
      <div class="panel panel-default">
        <div class="panel-heading">
          <h4 class="panel-title">
            <fmt:message key="title.justification.disapproval"/>
          </h4>
        </div>
        <div class="panel-body">
          <div class="row">
            <div class="col-sm-12 col-md-12">
              <div class="form-group">
                <label class="control-label"><fmt:message key="title.justification.disapproval"/></label>
                <textarea rows="4" cols="100" class="form-control">${purchaseOrder.purchaseOrder.approval.justificationDisapproval}</textarea>
              </div>
            </div>
          </div>
        </div>
      </div>
    </c:if>
    <c:if test="${purchaseOrder.purchaseOrder.budget.quotation.justification != null}">
      <div class="panel panel-default">
        <div class="panel-heading">
          <h4 class="panel-title">
            <fmt:message key="title.justification.supplier"/>
          </h4>
        </div>
        <div class="panel-body">
          <div class="row">
            <div class="col-md-2 col-sm-2">
              <div class="form-group">
                <label class="control-label"><fmt:message key="label.exclusive"/></label>
                <input type="text" class="form-control" readonly value="<c:if test="${purchaseOrder.purchaseOrder.budget.quotation.exclusiveSupplier eq true}"> <fmt:message key="label.yes" /> </c:if>"/>
              </div>
            </div>
            <div class="col-md-10 col-sm-10">
              <div class="form-group">
                <label class="control-label"><fmt:message key="label.justification"/></label>
                <textarea rows="4" cols="100" class="form-control" readonly>${purchaseOrder.purchaseOrder.budget.quotation.justification}</textarea>
              </div>
            </div>
          </div>
        </div>
      </div>
    </c:if>
    <c:if test="${purchaseOrder.purchaseOrder.approval.justificationDisapproval != null}">
      <div class="panel panel-default">
        <div class="panel-heading">
          <h4 class="panel-title">
            <fmt:message key="title.justification.disapproval"/>
          </h4>
        </div>
        <div class="panel-body">
          <div class="row">
            <div class="col-md-12 col-sm-12">
              <div class="form-group">
                <label class="control-label"><fmt:message key="label.justification"/></label>
                <textarea rows="4" cols="100" class="form-control"
                          readonly>${purchaseOrder.purchaseOrder.approval.justificationDisapproval}</textarea>
              </div>
            </div>
          </div>
        </div>
      </div>
    </c:if>
    <form action='<c:url value="/ordemCompra/adicionar/informacao"></c:url>' method="post" id="updateInformationForm">
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
                    <option value=""><fmt:message key="label.select" /></option>
                    <c:forEach items="${formsPayment}" var="formPayment">
                      <option value="${formPayment.id}" <c:if test="${formPayment.id eq purchaseOrder.purchaseOrder.paymentInformation.formPayment.id && purchaseOrder.purchaseOrder.paymentInformation.totalFinalPrice != null }"> selected </c:if> >${formPayment.description}</option>
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
                  <input type="text" class="form-control" id="discountPercentage" name="purchaseOrder.paymentInformation.discountPercentage" value="${fn:replace(purchaseOrder.purchaseOrder.paymentInformation.discountPercentage,".",",")}">
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
                  <label class="control-label" for="freight"><fmt:message key="label.freight"/></label>
                  <input type="text" class="form-control" id="freight" name="purchaseOrder.paymentInformation.freight" value="${fn:replace(purchaseOrder.purchaseOrder.paymentInformation.freight,".",",")}">
                </div>
              </div>
              <div class="col-md-2 col-sm-2">
                <div class="form-group">
                  <label class="control-label" for="totalFinalPrice"><fmt:message key="label.totalFinalPrice"/></label>
                  <input type="text" class="form-control" id="totalFinalPrice" name="purchaseOrder.paymentInformation.totalFinalPrice" value="${fn:replace(purchaseOrder.purchaseOrder.paymentInformation.totalFinalPrice,"." ,"," )}" readonly>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-2 col-sm-2">
                <div class="form-group">
                  <label class="control-label" for="sharePrice"><fmt:message key="label.sharePrice"/></label>
                  <input type="text" class="form-control" id="sharePrice" name="purchaseOrder.paymentInformation.sharePrice" value="${fn:replace(purchaseOrder.purchaseOrder.paymentInformation.sharePrice, ".","," )  }" readonly/>
                </div>
              </div>
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
            </div>
            <div class="row">
              <div class="col-md-2 col-sm-3">
                <div class="form-group">
                  <label class="control-label" for="alreadyPurchased"><fmt:message key="label.alreadyPurchased"/></label>
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
              <div class="col-md-3 col-sm-4">
                <div class="form-group">
                  <label class="control-label" for="investmentPurchase"><fmt:message key="label.investmentPurchase"/></label>
                  <br>
                  <div class="radio-inline">
                    <label class="control-label">
                      <input type="radio" value="true" name="purchaseOrder.investmentPurchase" id="investmentPurchase"
                      <c:if test="${purchaseOrder.purchaseOrder.investmentPurchase eq true}"> checked </c:if>> <fmt:message key="label.yes"/>
                    </label>
                  </div>
                  <div class="radio-inline">
                    <label class="control-label">
                      <input type="radio" value="false" name="purchaseOrder.investmentPurchase" id=investmentPurchase"
                      <c:if test="${purchaseOrder.purchaseOrder.investmentPurchase eq false}"> checked </c:if>> <fmt:message key="label.no"/>
                    </label>
                  </div>
                  <br>
                </div>
              </div>
              <div class="form-group">
                <br>
                <a onclick="unlockInputs()" class="btn btn-warning" ><fmt:message key="button.open.input"/></a>
              </div>
            </div>
        </div>
      </div>
      <div class="panel panel-default">
        <div class="panel-heading">
          <h4 class="panel-title">
            <fmt:message key="title.information.delivery"/>
          </h4>
        </div>
        <div class="panreceel-body">
          <div class="row">
              <div class="col-md-2 col-sm-2">
                  <div class="form-group">
                      <label class="control-label" for="deliveryTime"><fmt:message key="label.delivery.time"/></label>
                      <input type="text" class="form-control" id="deliveryTime" name="purchaseOrder.deliveryInformation.deliveryTime" value="${purchaseOrder.purchaseOrder.deliveryInformation.deliveryTime}"/>
                  </div>
              </div>
              <div class="col-md-4 col-sm-4">
                  <div class="form-group">
                      <label class="control-label" for="street"><fmt:message key="label.street"/></label>
                      <input type="text" class="form-control" id="street" name="purchaseOrder.deliveryInformation.street" value="${purchaseOrder.purchaseOrder.deliveryInformation.street}"/>
                  </div>
              </div>
              <div class="col-md-4 col-sm-4">
              <div class="form-group">
                <label class="control-label" for="neighborhood"><fmt:message key="label.neighborhood"/></label>
                <input type="text" class="form-control" id="neighborhood" name="purchaseOrder.deliveryInformation.neighborhood" value="${purchaseOrder.purchaseOrder.deliveryInformation.neighborhood}" />
              </div>
            </div>
              <div class="col-md-2 col-sm-2">
              <div class="form-group">
                <label class="control-label" for="number"><fmt:message key="label.number"/></label>
                <input type="text" class="form-control" id="number" name="purchaseOrder.deliveryInformation.number" value="${purchaseOrder.purchaseOrder.deliveryInformation.number}"/>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-md-4 col-sm-4">
              <div class="form-group">
                <label class="control-label" for="city"><fmt:message key="label.city"/></label>
                <input type="text" class="form-control" id="city" name="purchaseOrder.deliveryInformation.city" value="${purchaseOrder.purchaseOrder.deliveryInformation.city}"/>
              </div>
            </div>
            <div class="col-md-2 col-sm-2">
              <div class="form-group">
                <label class="control-label" for="zipCode"><fmt:message key="label.zipCode"/></label>
                <input type="text" class="form-control" id="zipCode" name="purchaseOrder.deliveryInformation.zipCode" value="${purchaseOrder.purchaseOrder.deliveryInformation.zipCode}"/>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-md-4 col-sm-4">
              <div class="form-group">
                <label class="control-label" for="receptorName"><fmt:message key="label.receptorName"/></label>
                <input type="text" class="form-control" id="receptorName" name="purchaseOrder.deliveryInformation.receptorName" value="${purchaseOrder.purchaseOrder.deliveryInformation.receptorName}"/>
              </div>
            </div>
            <div class="col-md-4 col-sm-4">
              <div class="form-group">
                <label class="control-label" for="place"><fmt:message key="label.place"/></label>
                <input type="text" class="form-control" id="place" name="purchaseOrder.deliveryInformation.place" value="${purchaseOrder.purchaseOrder.deliveryInformation.place}"/>
              </div>
            </div>
            <div class="col-md-3 col-sm-3">
              <div class="form-group">
                <label class="control-label" for="helpPlace"><fmt:message key="label.place"/></label>
                <select class="form-control" id="helpPlace">
                    <option value="0"><fmt:message key="label.select"/></option>
                    <option value="1"><fmt:message key="label.administrativeOffice"/></option>
                    <option value="2"><fmt:message key="label.diagnosticCenter"/></option>
                    <option value="3"><fmt:message key="label.drugstore"/></option>
                    <option value="4"><fmt:message key="label.optica"/></option>
                    <option value="5"><fmt:message key="label.referenceCenter"/></option>
                    <option value="6"><fmt:message key="label.hospital"/></option>
                    <option value="7"><fmt:message key="label.departmentOfOccupationalHealth"/></option>
                    <option value="8"><fmt:message key="label.preventiveMedicine"/></option>
                    <option value="9"><fmt:message key="label.expertiseCenter"/></option>
                </select>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
          <div class="col-sm-offset-10 col-md-offset-10">
            <div class="form-group">
              <input type="hidden" name="purchaseOrder.deliveryInformation.id" value="${purchaseOrder.purchaseOrder.deliveryInformation.id}">
              <input type="hidden" name="purchaseOrder.paymentInformation.id" value="${purchaseOrder.purchaseOrder.paymentInformation.id}">
              <input type="hidden" name="purchaseOrder.id" value="${purchaseOrder.purchaseOrder.id}">
              <input type="submit" class="btn btn-success" value="<fmt:message key="button.save"/>" />
            </div>
          </div>
        </div>
    </form>
  </div>
</html:template>
</body>
<html:notification/>
<html:jsAssets/>
<html:tableJsAssets/>
<script src="${pageContext.request.contextPath}/asset/js/custom/form-purchase-order.js"></script>
<script src="${pageContext.request.contextPath}/asset/js/vendor/jquery.mask.min.js"></script>
<script src="${pageContext.request.contextPath}/asset/js/commons/formCommons.js"></script>
<script src="${pageContext.request.contextPath}/asset/js/vendor/price_format.2.0.min.js"></script>
<script src="${pageContext.request.contextPath}/asset/js/vendor/moment.js"></script>
</html>