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
      <h3><fmt:message key="title.purchasing.order"/></h3>
    </div>
    <div class="row">
      <div class="col-sm-2 col-md-2">
        <div class="form-group">
         <label class="control-label"><fmt:message key="label.number"/></label>
         <input type="text" class="form-control" readonly value="${purchaseOrder.id}" />
        </div>
      </div>
      <div class="col-sm-2 col-md-2">
        <div class="form-group">
          <label class="control-label"><fmt:message key="label.date"/></label>
          <input type="text" class="form-control" readonly value="<fmt:formatDate value="${purchaseOrder.date}" pattern="dd/MM/YYYY HH:mm"/>"/>
        </div>
      </div>
      <div class="col-sm-5 col-md-5">
        <div class="form-group">
          <label class="control-label"><fmt:message key="label.supplier"/></label>
          <input type="text" class="form-control" readonly value="${purchaseOrder.budget.supplier.person.name}"/>
        </div>
      </div>
    </div>
    <div class="panel panel-default">
      <div class="panel-body">
          <c:if test="${purchaseOrder.budget.quotation.type == 'Material'}">
            <table id="orderRequestProductTable" class="table table-striped table-hover table-condensed">
              <thead>
                <tr>
                    <th style="width: 15%"><fmt:message key="table.costCenter"/></th>
                    <th style="width: 50%"><fmt:message key="table.product"/></th>
                    <th style="width: 5%"><fmt:message key="table.abbreviatedQuantity"/></th>
                    <th style="width: 10%"><fmt:message key="table.unit"/></th>
                    <th style="width: 10%"><fmt:message key="table.unitary.price"/></th>
                    <th style="width: 10%"><fmt:message key="table.total.price"/></th>
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${purchaseOrder.orderRequests}" var="orderRequest">
                  <tr>
                    <td>${orderRequest.budgetQuotation.quotationRequest.solicitationRequest.solicitation.costCenter.description}</td>
                    <td>${orderRequest.budgetQuotation.quotationRequest.solicitationRequest.product.description} ${orderRequest.budgetQuotation.quotationRequest.solicitationRequest.product.model} ${orderRequest.budgetQuotation.quotationRequest.solicitationRequest.product.mark}</td>
                    <td>${orderRequest.budgetQuotation.quotationRequest.solicitationRequest.quantity}</td>
                    <td>${orderRequest.budgetQuotation.quotationRequest.solicitationRequest.product.unit.description}</td>
                    <td>${fn:replace(orderRequest.budgetQuotation.unityPrice,"." ,"," )}</td>
                    <td><fmt:formatNumber type="number" value="${orderRequest.budgetQuotation.quotationRequest.solicitationRequest.quantity * orderRequest.budgetQuotation.unityPrice}" minFractionDigits="2" maxFractionDigits="2" /></td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
          </c:if>
          <c:if test="${purchaseOrder.budget.quotation.type == 'Service'}">
            <table id="orderRequestServiceTable" class="table table-striped table-hover table-condensed">
              <thead>
                <tr>
                  <th style="width: 25%"><fmt:message key="table.costCenter"/></th>
                  <th style="width: 50%"><fmt:message key="table.description"/></th>
                  <th style="width: 10%"><fmt:message key="table.unitary.price"/></th>
                  <th style="width: 10%"><fmt:message key="table.total.price"/></th>
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${purchaseOrder.orderRequests}" var="orderRequest">
                  <tr>
                    <td>${orderRequest.budgetQuotation.quotationRequest.solicitationRequest.solicitation.costCenter.description}</td>
                    <td>${orderRequest.budgetQuotation.quotationRequest.solicitationRequest.service.description}</td>
                    <td>${fn:replace(orderRequest.budgetQuotation.unityPrice,"." ,"," )}</td>
                    <td>${fn:replace(1 * orderRequest.budgetQuotation.unityPrice,"." ,"," )}</td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
          </c:if>
        <div class="row">
          <div class="col-sm-2 col-md-2 col-md-offset-10 col-sm-offset-10">
            <div class="form-group">
              <label class="control-label"><fmt:message key="label.total.price"/></label>
              <input type="text" class="form-control" readonly value="${fn:replace(purchaseOrder.paymentInformation.totalPrice,"." ,"," )}"/>
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
        <c:forEach items="${purchaseOrder.budget.quotation.budgets}" var="budget">
          <div class="row">
            <div class="col-sm-5 col-md-5">
              <div class="form-group <c:if test="${purchaseOrder.budget.id eq budget.id}">has-success</c:if>" >
                  <label class="control-label"><fmt:message key="label.supplier" /> <c:if test="${purchaseOrder.budget.id eq budget.id}"> - <fmt:message key="label.select" /></c:if> </label>
                  <input type="text" class="form-control" readonly value="${budget.supplier.person.name}" />
              </div>
            </div>
            <div class="col-sm-2 col-md-2">
              <div class="form-group <c:if test="${purchaseOrder.budget.id eq budget.id}">has-success</c:if>">
                <label class="control-label"><fmt:message key="label.date"/></label>
                <input type="text" class="form-control" readonly value="<fmt:formatDate value="${budget.date}" pattern="dd/MM/YYYY"/>"/>
              </div>
            </div>
            <div class="col-sm-2 col-md-2">
              <div class="form-group <c:if test="${purchaseOrder.budget.id eq budget.id}">has-success</c:if>">
                <label class="control-label"><fmt:message key="label.total.price"/></label>
                <input type="text" class="form-control" readonly value="<fmt:formatNumber value="${budget.paymentInformationBudgets[0].paymentInformation.totalPrice}" type="currency" currencySymbol="R$" />"/>
              </div>
            </div>
            <div class="col-sm-2 col-md-2">
              <div class="form-group <c:if test="${purchaseOrder.budget.id eq budget.id}">has-success</c:if>">
                <label class="control-label"><fmt:message key="label.totalFinalPrice"/></label>
                <input type="text" class="form-control" readonly value="<fmt:formatNumber value="${budget.paymentInformationBudgets[0].paymentInformation.totalFinalPrice}" type="currency" currencySymbol="R$" />"/>
              </div>
            </div>
          </div>
        </c:forEach>
      </div>
    </div>
    <c:if test="${purchaseOrder.budget.quotation.justification != null}">
      <div class="panel panel-default">
        <div class="panel-heading">
          <h3 class="panel-title"><fmt:message key="title.justification.supplier"/></h3>
        </div>
        <div class="panel-body">
          <div class="row">
            <div class="col-md-2 col-sm-2">
              <div class="form-group">
                <label class="control-label"><fmt:message key="label.exclusive"/></label>
                <input type="text" class="form-control" readonly value="<c:if test="${purchaseOrder.budget.quotation.exclusiveSupplier eq true}"> <fmt:message key="label.yes" /> </c:if> <c:if test="${purchaseOrder.budget.quotation.exclusiveSupplier eq false}"> <fmt:message key="label.no" /> </c:if>"/>
              </div>
            </div>
            <div class="col-md-10 col-sm-10">
              <div class="form-group">
                <label class="control-label" for="justification"><fmt:message key="label.justification"/></label>
                <textarea rows="4" cols="100" class="form-control" readonly>${purchaseOrder.budget.quotation.justification}</textarea>
              </div>
            </div>
          </div>
        </div>
      </div>
    </c:if>
    <c:if test="${purchaseOrder.approval != null && purchaseOrder.approval.justificationDisapproval != null}">
      <div class="panel panel-default">
        <div class="panel-heading">
          <h4 class="panel-title">
            <fmt:message key="title.justification.disapproval"/>
          </h4>
        </div>
        <div class="panel-body">
          <div class="row">
            <div class="col-md-5 col-sm-5">
              <label class="control-label"><fmt:message key="label.username"/></label>
              <input type="text" class="form-control" readonly value="<c:if test="${purchaseOrder.approval.firstApproval == false}">${purchaseOrder.approval.userFirstApproval}</c:if>  <c:if test="${purchaseOrder.approval.secondApproval == false}">${purchaseOrder.approval.userSecondApproval}</c:if>   <c:if test="${purchaseOrder.approval.thirdApproval == false}">${purchaseOrder.approval.userThirdApproval}</c:if>">
            </div>
            <div class="col-md-2 col-sm-2">
              <label class="control-label"><fmt:message key="label.date"/></label>
              <input type="text" class="form-control" readonly value="<c:if test="${purchaseOrder.approval.firstApproval == false}"><fmt:formatDate type="both"  dateStyle="short" timeStyle="short"  value="${purchaseOrder.approval.dateFirstApproval}" /></c:if>  <c:if test="${purchaseOrder.approval.secondApproval == false}"><fmt:formatDate type="both"  dateStyle="short" timeStyle="short"  value="${purchaseOrder.approval.dateSecondApproval}"/></c:if>   <c:if test="${purchaseOrder.approval.thirdApproval == false}"><fmt:formatDate type="both"  dateStyle="short" timeStyle="short"  value="${purchaseOrder.approval.dateThirdApproval}"/></c:if>">
            </div>
          </div>
          <div class="row">
            <div class="col-md-12 col-sm-12">
              <div class="form-group">
                <label class="control-label"><fmt:message key="label.justification"/></label>
                <textarea rows="4" cols="100" class="form-control" readonly>${purchaseOrder.approval.justificationDisapproval}</textarea>
              </div>
            </div>
          </div>
        </div>
      </div>
    </c:if>
    <c:if test="${purchaseOrder.approval != null && purchaseOrder.approval.justificationDisapproval == null}">
      <div class="panel panel-default">
        <div class="panel-heading">
          <h3 class="panel-title"><fmt:message key="title.approvals"/></h3>
        </div>
        <div class="panel-body">
          <table class="table table-striped table-hover table-condensed">
            <thead>
            <tr>
              <th style="width: 15%"><fmt:message key="table.user"/></th>
              <th style="width: 40%"><fmt:message key="table.observation"/></th>
              <th style="width: 10%"><fmt:message key="table.date"/></th>
            </tr>
            </thead>
            <tbody>
                <tr>
                  <td>${purchaseOrder.approval.userFirstApproval}</td>
                  <td>${purchaseOrder.approval.observationFirstApproval}</td>
                  <td><fmt:formatDate value="${purchaseOrder.approval.dateFirstApproval}" pattern="dd/MM/YYYY HH:mm"/></td>
                </tr>
                <tr>
                  <td>${purchaseOrder.approval.userSecondApproval}</td>
                  <td>${purchaseOrder.approval.observationSecondApproval}</td>
                  <td><fmt:formatDate value="${purchaseOrder.approval.dateSecondApproval}" pattern="dd/MM/YYYY HH:mm"/></td>
                </tr>
                <tr>
                  <td>${purchaseOrder.approval.userThirdApproval}</td>
                  <td>${purchaseOrder.approval.observationThirdApproval}</td>
                  <td><fmt:formatDate value="${purchaseOrder.approval.dateThirdApproval}" pattern="dd/MM/YYYY HH:mm"/></td>
                </tr>
                <tr>
                  <td>${purchaseOrder.approval.userFourthApproval}</td>
                  <td>${purchaseOrder.approval.observationFourthApproval}</td>
                  <td><fmt:formatDate value="${purchaseOrder.approval.dateFourthApproval}" pattern="dd/MM/YYYY HH:mm"/></td>
                </tr>
            </tbody>
          </table>
        </div>
      </div>
    </c:if>
    <c:if test="${purchaseOrder.receptions != null && fn:length(purchaseOrder.receptions) > 0}">
      <div class="panel panel-default">
        <div class="panel-heading">
          <h3 class="panel-title"><fmt:message key="title.receptions"/></h3>
        </div>
        <div class="panel-body">
          <table class="table table-striped table-hover table-condensed">
            <thead>
            <tr>
              <th><fmt:message key="table.user"/></th>
              <th><fmt:message key="table.tax.document"/></th>
              <th><fmt:message key="table.date"/></th>
              <th><fmt:message key="table.##"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${purchaseOrder.receptions}" var="reception" >
              <c:if test="${reception.status == 'Finished'}">
                <tr>
                  <td>${reception.user.name}</td>
                  <td>${reception.taxDocument}</td>
                  <td><fmt:formatDate value="${reception.date}" pattern="dd/MM/YYYY HH:mm"/></td>
                  <td><a href="<c:url value="/ordemCompra/imprimir/ordem/${reception.id}"></c:url>" target='_blank'><span class="fa fa-print btn btn-default"></span></a></td>
                </tr>
              </c:if>
            </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </c:if>
    <c:if test="${purchaseOrder.status == 'Canceled'}">
      <div class="panel panel-default">
        <div class="panel-heading">
          <h3 class="panel-title"><fmt:message key="title.justification.cancellation"/></h3>
        </div>
        <div class="panel-body">
          <div class="row">
            <div class="col-md-12 col-sm-12">
              <div class="form-group">
                <label class="control-label" for="justification"><fmt:message key="label.justification"/></label>
                <textarea rows="4" cols="100" class="form-control" readonly>${purchaseOrder.justificationCancellation}</textarea>
              </div>
            </div>
          </div>
        </div>
      </div>
    </c:if>
    <c:if test="${approve == 'true' && purchaseOrder.status == 'Open'}">
      <form action='<c:url value="/ordemCompra/aprovar"></c:url>' method="post" id="approveForm">
        <div class="panel panel-default">
          <div class="panel-heading">
            <h3 class="panel-title"><fmt:message key="title.observation"/></h3>
          </div>
          <div class="panel-body">
            <div class="col-md-12 col-sm-12">
              <div class="form-group">
                <label class="control-label" for="observation"><fmt:message key="label.observation.observation"/></label>
                <input type="hidden" name="purchaseOrder.id" value="${purchaseOrder.id}">
                <textarea rows="4" cols="100" class="form-control" id="observation"  name="observation"></textarea>
              </div>
            </div>
          </div>
          <div class="panel-footer">
            <div class="col-sm-offset-10 col-md-offset-10">
              <div class="form-group">
                <input type="submit" class="btn btn-success" value="<fmt:message key="button.approve"/>" >
                <a onclick="reprove()" class="btn btn-warning"><fmt:message key="button.reprove"/></a>
              </div>
            </div>
          </div>
        </div>
     </form>
    </c:if>
    <form action='<c:url value="/ordemCompra/reprovar"></c:url>' method="post" id="reprovePurchaseOrderForm">
      <div class="panel panel-default">
        <div class="panel-heading">
          <h3 class="panel-title"><fmt:message key="title.justification.reprove"/></h3>
        </div>
        <div class="panel-body">
          <div class="row">
            <div class="col-sm-12 col-md-12">
              <div class="form-group">
                <label class="control-label"><fmt:message key="title.justification.disapproval"/></label>
                <textarea rows="4" cols="100" class="form-control" id="justification" name="justification" required></textarea>
              </div>
            </div>
          </div>
        </div>
        <div class="panel-footer">
          <div class="row">
            <div class="col-sm-offset-11 col-md-offset-11">
              <div class="form-group">
                <input type="hidden" name="purchaseOrder.id" value="${purchaseOrder.id}"/>
                <button type="submit" class="btn btn-primary"><fmt:message key="button.save"/></button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </form>
    <c:if test="${userLogged.role.id eq '7' || userLogged.role.id eq '5' || userLogged.role.id eq '1'}">
      <c:if test="${(purchaseOrder.alreadyPurchased == 'true' &&  purchaseOrder.status eq 'Open') || (purchaseOrder.paymentInformation.hasContract == 'true' && purchaseOrder.status eq 'Open')||(purchaseOrder.status eq 'BuyingProcess') || (purchaseOrder.status eq 'PurchaseMade')|| (purchaseOrder.status eq 'Finished') || (purchaseOrder.status eq 'PartiallyFinished')}">
        <div class="row">
          <div class="col-sm-offset-9 col-md-offset-9">
            <div class="form-group">
              <a href="<c:url value="/ordemCompra/imprimir/pedido/${purchaseOrder.id}"></c:url>" target='_blank'><span class="fa fa-print btn btn-default"> <fmt:message key="button.download.order" />  </span></a>
            </div>
          </div>
        </div>
      </c:if>
    </c:if>

</html:template>
</body>
<html:jsAssets/>
<html:tableJsAssets/>
<c:if test="${purchaseOrder.budget.quotation.type == 'Material'}">
  <script src="${pageContext.request.contextPath}/asset/js/custom/view-purchase-order-product.js"></script>
</c:if>
<c:if test="${purchaseOrder.budget.quotation.type == 'Service'}">
  <script src="${pageContext.request.contextPath}/asset/js/custom/view-purchase-order-service.js"></script>
</c:if>
</html>