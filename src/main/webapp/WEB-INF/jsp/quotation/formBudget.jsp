<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title><fmt:message key="title.page.budget" /> - <fmt:message key="title.purchasing" /></title>
    <html:head />
    <html:formAssets/>
</head>
<body>
<html:template>
    <div class="container-fluid">
        <div class="page-header">
            <h2><fmt:message key="title.quotation" /> - <fmt:message key="title.add.budget" /></h2>
        </div>
        <div class="panel-body well well-sm">
            <div class="col-md-3 col-sm-3">
                <a class="btn btn-default" href="<c:url value="/cotacao/editar/${quotation.id}"></c:url>"><fmt:message key="button.menu.quotation" /> </a>
            </div>
            <div class="col-md-3 col-sm-3">
                <a class="btn btn-default" href="<c:url value="/cotacao/formulario/adicionar/orcamento/${quotation.id}"></c:url>"><fmt:message key="button.menu.add.budget" /> </a>
            </div>
            <div class="col-md-3 col-sm-3">
                <a class="btn btn-default" href="<c:url value="/cotacao/formulario/visualizar/detalhes/${quotation.id}"></c:url>"><fmt:message key="button.menu.viewDetails" /> </a>
            </div>
            <div class="col-md-3 col-sm-3">
                <a class="btn btn-default" href="<c:url value="/cotacao/formulario/iniciar/ordem/${quotation.id}"></c:url>"><fmt:message key="button.menu.init.purchase.order" /> </a>
            </div>
        </div>
        <form action='<c:url value="/cotacao/salvar/orcamento"></c:url>' method="post" id="budgetForm">
            </br>
            <!--  Informações -->
            <div class="row">
                <div class="col-md-2 col-sm-2">
                    <div class="form-group">
                        <label  class="control-label" for="number"><fmt:message key="label.number"/></label>
                        <input type="text" class="form-control" id="number" readonly="true" value="${quotation.id}"/>
                    </div>
                </div>
                <div class="col-md-2 col-sm-2">
                    <div class="form-group">
                        <label  class="control-label" for="type"><fmt:message key="label.type"/></label>
                        <input type="text" class="form-control" id="type" readonly="true" value="${quotation.type.description}"/>
                    </div>
                </div>
                <div class="col-md-4 col-sm-4">
                    <label  class="control-label" for="name"><fmt:message key="label.name"/></label>
                    <input type="text" class="form-control" id="name" readonly="true" value="${quotation.user.name}"/>
                </div>
                <div class="col-md-2 col-sm-2">
                    <div class="form-group">
                        <label  class="control-label" for="status"><fmt:message key="label.status"/></label>
                        <input type="text" class="form-control" id="status" readonly="true" value="${quotation.status.description}"/>
                    </div>
                </div>
            </div>
            <!--  Inicio do orçamento-->
            <div class="row">
                <div class="col-md-1 col-sm-1">
                    <div class="form-group">
                        <label class="control-label"  for="code"><fmt:message key="label.code"/></label>
                        <input type="text" class="form-control" id="code" name="budget.supplier.id" >
                    </div>
                </div>
                <div class="col-md-6 col-sm-6">
                    <div class="form-group">
                        <label class="control-label" for="supplier"><fmt:message key="label.supplier"/></label>
                        <div class="input-group">
                            <input type="text" class="form-control" id="supplier" readonly>
                            <span class="input-group-btn"><button class="btn btn-default" type="button" data-toggle="modal" data-target="#searchSupplier"><span class="fa fa-search"></span></button></span>
                        </div>
                    </div>
                </div>
                <div class="col-md-2 col-sm-2">
                    <div class="form-group">
                        <label class="control-label" for="date"><fmt:message key="label.date"/></label>
                        <input type="text" class="form-control date" id="date" name="budget.date">
                    </div>
                </div>
                <div class="col-md-2 col-sm-2">
                    <div class="form-group">
                        <label class="control-label" for="number_budget"  ><fmt:message key="label.number_budget"/></label>
                        <input type="text" class="form-control" id="number_budget" name="budget.numberBudget">
                    </div>
                </div>
            </div>
            <c:if test="${quotation.type == 'Material'}">
                <table id="budgetMaterialTable" class="table table-striped table-hover table-condensed">
                    <thead>
                    <tr>
                        <th style="width: 50%"><fmt:message key="table.product" /></th>
                        <th style="width: 10%" ><fmt:message key="table.quantity" /></th>
                        <th style="width: 10%" ><fmt:message key="table.unit" /></th>
                        <th style="width: 8%" ><fmt:message key="table.unitary.price" /></th>
                        <th style="width: 8%" ><fmt:message key="table.total.price" /></th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${quotationRequests}" var="quotationRequest" varStatus="i">
                            <tr>
                                <td>${quotationRequest.product.description} ${quotationRequest.product.model} ${quotationRequest.product.mark}</td>
                                <td>
                                    <input type="text" value="${quotationRequest.quantity}" id="quantity${i.index}" readonly>
                                </td>
                                <td>${quotationRequest.product.unit.description}</td>
                                <td>
                                    <input type="hidden"  name="budget.budgetQuotations[${i.index}].quotationRequest.id" value="${quotationRequest.id}">
                                    <input type="text"  name="budget.budgetQuotations[${i.index}].unityPrice"  id="unityPrice${i.index}" onclick="calculateTotalPriceMaterial(${i.index})">
                                </td>
                                <td><input type="text" id="totalPriceMaterial${i.index}" readonly></td>
                            </tr>
                        </c:forEach>
                        <tfoot>
                        <tr>
                            <th style="width: 50%"></th>
                            <th style="width: 10%"><input type="hidden" id="numberRequestMaterial" value="${fn:length(quotationRequests)}" ></th>
                            <th style="width: 10%"></th>
                            <th style="width: 10%"></th>
                            <th style="width: 10%"><input type="text" id="totalFinalPriceMaterial"readonly></th>
                        </tr>
                        </tfoot>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${quotation.type == 'Service'}">
                <table id="quotationServiceDetailTable" class="table table-striped table-hover table-condensed">
                    <thead>
                    <tr>
                        <th style="width: 80%" ><fmt:message key="table.description" /></th>
                        <th style="width: 10%" ><fmt:message key="table.##" /></th>
                        <th style="width: 10%" ><fmt:message key="table.##" /></th>
                    </thead>
                    <tbody>
                        <c:forEach items="${quotationRequests}" var="quotationRequest" varStatus="i">
                            <tr>
                                <td>${quotationRequest.description}</td>
                                <td>
                                    <input type="hidden"  name="budget.budgetQuotations[${i.index}].quotationRequest.id" value="${quotationRequest.id}">
                                    <input type="text"  name="budget.budgetQuotations[${i.index}].unityPrice">
                                </td>
                                <td><input type="text" id="totalPrice${i.index}" readonly></td>
                            </tr>
                    <tfoot>
                    <tr>
                        <th style="width: 80%" ></th>
                        <th style="width: 10%"><input type="text" class="totalUnitPriceMaterial" readonly></th>
                        <th style="width: 10%"><input type="text" class="finalPriceMaterial"readonly></th>
                    </tr>
                    </tfoot>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <hr>
                <h2><fmt:message key="title.information.payment" /></h2>
            <hr>
            <div class="row">
                <div class="panel-group" id="accordion">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                                    <fmt:message key="title.list.information.payment.one" />
                                </a><i class="indicator fa fa-angle-double-down"></i>
                            </h4>
                        </div>
                        <div id="collapseOne" class="panel-collapse collapse in">
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-md-2 col-sm-2">
                                        <div class="form-group">
                                            <label  class="control-label" for="meanPayment"><fmt:message key="label.meanPayment"/></label>
                                            <select class="form-control" id="meanPayment" name="budget.paymentInformationBudgets[0].paymentInformation.meanPayment">
                                                    <c:forEach items="${meansPayment}" var="meanPayment">
                                                        <option value="${meanPayment}" >${meanPayment.description}</option>
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
                                                    <input type="radio" value="true" name="budget.paymentInformationBudgets[0].paymentInformation.hasContract" id="contract" <c:if test="${budget.contract[0] eq true}"> checked </c:if>> <fmt:message key="label.yes"/>
                                                </label>
                                            </div>
                                            <div class="radio-inline">
                                                <label class="control-label">
                                                    <input type="radio" value="false" name="budget.paymentInformationBudgets[0].paymentInformation.hasContract" id="contract" <c:if test="${budget.contract[0] eq false}"> checked </c:if>> <fmt:message key="label.no"/>
                                                </label>
                                            </div>
                                            <br>
                                        </div>
                                    </div>
                                    <div class="col-md-2 col-sm-2">
                                        <div class="form-group">
                                            <label  class="control-label" for="number_contract"><fmt:message key="label.number_contract"/></label>
                                            <input type="text" class="form-control" id="number_contract" name="budget.paymentInformationBudgets[0].paymentInformation.contract.id" value="${budget.contract[0].id}"/>
                                        </div>
                                    </div>
                                    <div class="col-md-3 col-sm-3">
                                        <div class="form-group">
                                            <label  class="control-label" for="formPayment"><fmt:message key="label.formPayment"/></label>
                                            <select class="form-control" id="formPayment" name="budget.paymentInformationBudgets[0].paymentInformation.formPayment.id">
                                                <option><fmt:message key="label.select" /> </option>
                                                <c:forEach items="${formsPayment}" var="formPayment">
                                                    <option value="${formPayment.id}" >${formPayment.description}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-2 col-sm-2">
                                        <div class="form-group">
                                            <label  class="control-label" for="totalPrice"><fmt:message key="label.totalPrice"/></label>
                                            <input  type="text" class="form-control" id="totalPrice" name="budget.paymentInformationBudgets[0].paymentInformation.totalPrice" readonly>
                                        </div>
                                    </div>
                                    <div class="col-md-2 col-sm-2">
                                        <div class="form-group">
                                            <label  class="control-label" for="discountPercentage"><fmt:message key="label.discountPercentage"/></label>
                                            <input  type="text" class="form-control" id="discountPercentage" name="budget.paymentInformationBudgets[0].paymentInformation.discountPercentage">
                                        </div>
                                    </div>
                                    <div class="col-md-2 col-sm-2">
                                        <div class="form-group">
                                            <label  class="control-label" for="totalFinalPrice"><fmt:message key="label.totalFinalPrice"/></label>
                                            <input  type="text" class="form-control" id="totalFinalPrice" name="budget.paymentInformationBudgets[0].paymentInformation.totalFinalPrice" readonly>
                                        </div>
                                    </div>
                                    <div class="col-md-2 col-sm-2">
                                        <div class="form-group">
                                            <label  class="control-label" for="inputPrice"><fmt:message key="label.inputPrice"/></label>
                                            <input type="text" class="form-control" id="inputPrice" name="budget.paymentInformationBudgets[0].paymentInformation.inputPrice"/>
                                        </div>
                                    </div>
                                    <div class="col-md-2 col-sm-2">
                                        <div class="form-group">
                                            <label  class="control-label" for="sharePrice"><fmt:message key="label.sharePrice"/></label>
                                            <input type="text" class="form-control" id="sharePrice" name="budget.paymentInformationBudgets[0].paymentInformation.sharePrice" readonly/>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-2 col-sm-2">
                                        <div class="form-group">
                                            <label  class="control-label" for="dateInput"><fmt:message key="label.dateInput"/></label>
                                            <input  type="text" class="form-control date" id="dateInput" name="budget.paymentInformationBudgets[0].paymentInformation.dateInput">
                                        </div>
                                    </div>
                                    <div class="col-md-2 col-sm-2">
                                        <div class="form-group">
                                            <label  class="control-label" for="dateFirstInstallment"><fmt:message key="label.dateFirstInstallment"/></label>
                                            <input  type="text" class="form-control date" id="dateFirstInstallment" name="budget.paymentInformationBudgets[0].paymentInformation.dateFirstInstallment">
                                        </div>
                                    </div>
                                    <div class="col-md-2 col-sm-2">
                                        <div class="form-group">
                                            <label  class="control-label" for="dateLastInstallment"><fmt:message key="label.dateLastInstallment"/></label>
                                            <input  type="text" class="form-control date" id="dateLastInstallment" name="budget.paymentInformationBudgets[0].paymentInformation.dateLastInstallment">
                                        </div>
                                    </div>
                                    <div class="col-md-2 col-sm-2">
                                        <div class="form-group">
                                            <label  class="control-label" for="expirationDate"><fmt:message key="label.expiration_date"/></label>
                                            <input type="text" class="form-control date" id="expirationDate" name="budget.paymentInformationBudgets[0].paymentInformation.expirationDate"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
                                    <fmt:message key="title.list.information.payment.two" />
                                </a><i class="indicator fa fa-angle-double-up"></i>
                            </h4>
                        </div>
                        <div id="collapseTwo" class="panel-collapse collapse">
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-md-2 col-sm-2">
                                        <div class="form-group">
                                            <label  class="control-label" for="meanPaymentTwo"><fmt:message key="label.meanPayment"/></label>
                                            <select class="form-control" id="meanPaymentTwo" name="budget.paymentInformationBudgets[1].paymentInformation.meanPayment">
                                                <c:forEach items="${meansPayment}" var="meanPayment">
                                                    <option value="${meanPayment}" >${meanPayment.description}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-2 col-sm-3">
                                        <div class="form-group">
                                            <label class="control-label" for="contractTwo"><fmt:message key="label.contract"/></label>
                                            <br>
                                            <div class="radio-inline">
                                                <label class="control-label">
                                                    <input type="radio" value="true" name="budget.paymentInformationBudgets[1].paymentInformation.hasContract" id="contractTwo" <c:if test="${budget.hasContract[1] eq true}"> checked </c:if>> <fmt:message key="label.yes"/>
                                                </label>
                                            </div>
                                            <div class="radio-inline">
                                                <label class="control-label">
                                                    <input type="radio" value="false" name="budget.paymentInformationBudgets[1].paymentInformation.hasContract" id="contractTwo" <c:if test="${budget.hasContract[1] eq false}"> checked </c:if>> <fmt:message key="label.no"/>
                                                </label>
                                            </div>
                                            <br>
                                        </div>
                                    </div>
                                    <div class="col-md-2 col-sm-2">
                                        <div class="form-group">
                                            <label  class="control-label" for="number_contractTwo"><fmt:message key="label.number_contract"/></label>
                                            <input type="text" class="form-control" id="number_contractTwo" name="budget.paymentInformationBudgets[1].paymentInformation.contract.id" value="${budget.contract[1].id}"/>
                                        </div>
                                    </div>
                                    <div class="col-md-3 col-sm-3">
                                        <div class="form-group">
                                            <label  class="control-label" for="formPaymentTwo"><fmt:message key="label.formPayment"/></label>
                                            <select class="form-control" id="formPaymentTwo" name="budget.paymentInformationBudgets[1].paymentInformation.formPayment.id">
                                                <option><fmt:message key="label.select" /> </option>
                                                <c:forEach items="${formsPayment}" var="formPayment">
                                                    <option value="${formPayment.id}" >${formPayment.description}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-2 col-sm-2">
                                        <div class="form-group">
                                            <label  class="control-label" for="totalPriceTwo"><fmt:message key="label.totalPrice"/></label>
                                            <input  type="text" class="form-control" id="totalPriceTwo" name="budget.paymentInformationBudgets[1].paymentInformation.totalPrice" readonly>
                                        </div>
                                    </div>
                                    <div class="col-md-2 col-sm-2">
                                        <div class="form-group">
                                            <label  class="control-label" for="discountPercentageTwo"><fmt:message key="label.discountPercentage"/></label>
                                            <input  type="text" class="form-control" id="discountPercentageTwo" name="budget.paymentInformationBudgets[1].paymentInformation.discountPercentage">
                                        </div>
                                    </div>
                                    <div class="col-md-2 col-sm-2">
                                        <div class="form-group">
                                            <label  class="control-label" for="totalFinalPriceTwo"><fmt:message key="label.totalFinalPrice"/></label>
                                            <input  type="text" class="form-control" id="totalFinalPriceTwo" name="budget.paymentInformationBudgets[1].paymentInformation.totalFinalPrice" readonly>
                                        </div>
                                    </div>
                                    <div class="col-md-2 col-sm-2">
                                        <div class="form-group">
                                            <label  class="control-label" for="inputPriceTwo"><fmt:message key="label.inputPrice"/></label>
                                            <input type="text" class="form-control" id="inputPriceTwo" name="budget.pmentInformationBudgets[1].paymentInformation.inputPrice"/>
                                        </div>
                                    </div>
                                    <div class="col-md-2 col-sm-2">
                                        <div class="form-group">
                                            <label  class="control-label" for="sharePriceTwo"><fmt:message key="label.sharePrice"/></label>
                                            <input type="text" class="form-control" id="sharePriceTwo" name="budget.paymentInformationBudgets[1].paymentInformation.sharePrice" readonly/>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-2 col-sm-2">
                                        <div class="form-group">
                                            <label  class="control-label" for="dateInputTwo"><fmt:message key="label.dateInput"/></label>
                                            <input  type="text" class="form-control date" id="dateInputTwo" name="budget.paymentInformationBudgets[1].paymentInformation.dateInput">
                                        </div>
                                    </div>
                                    <div class="col-md-2 col-sm-2">
                                        <div class="form-group">
                                            <label  class="control-label" for="dateFirstInstallmentTwo"><fmt:message key="label.dateFirstInstallment"/></label>
                                            <input  type="text" class="form-control date" id="dateFirstInstallmentTwo" name="budget.pmentInformationBudgets[1].paymentInformation.dateFirstInstallment">
                                        </div>
                                    </div>
                                    <div class="col-md-2 col-sm-2">
                                        <div class="form-group">
                                            <label  class="control-label" for="dateLastInstallmentTwo"><fmt:message key="label.dateLastInstallment"/></label>
                                            <input  type="text" class="form-control date" id="dateLastInstallmentTwo" name="budget.paymentInformationBudgets[1].paymentInformation.dateLastInstallment">
                                        </div>
                                    </div>
                                    <div class="col-md-2 col-sm-2">
                                        <div class="form-group">
                                            <label  class="control-label" for="expirationDateTwo"><fmt:message key="label.expiration_date"/></label>
                                            <input type="text" class="form-control date" id="expirationDateTwo" name="budget.paymentInformationBudgets[1].paymentInformation.expirationDate"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
                                    <fmt:message key="title.list.information.payment.three" />
                                </a><i class="indicator fa fa fa-angle-double-up"></i>
                            </h4>
                        </div>
                        <div id="collapseThree" class="panel-collapse collapse">
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-md-2 col-sm-2">
                                        <div class="form-group">
                                            <label  class="control-label" for="meanPaymentThree"><fmt:message key="label.meanPayment"/></label>
                                            <select class="form-control" id="meanPaymentThree" name="budget.paymentInformationBudgets[2].paymentInformation.meanPayment">
                                                <c:forEach items="${meansPayment}" var="meanPayment">
                                                    <option value="${meanPayment}" >${meanPayment.description}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-2 col-sm-3">
                                        <div class="form-group">
                                            <label class="control-label" for="contractThree"><fmt:message key="label.contract"/></label>
                                            <br>
                                            <div class="radio-inline">
                                                <label class="control-label">
                                                    <input type="radio" value="true" name="budget.paymentInformationBudgets[2].paymentInformation.hasContract" id="contractThree" <c:if test="${budget.hasContract[0] eq true}"> checked </c:if>> <fmt:message key="label.yes"/>
                                                </label>
                                            </div>
                                            <div class="radio-inline">
                                                <label class="control-label">
                                                    <input type="radio" value="false" name="budget.paymentInformationBudgets[2].paymentInformation.hasContract" id="contractThree" <c:if test="${budget.hasContract[0] eq false}"> checked </c:if>> <fmt:message key="label.no"/>
                                                </label>
                                            </div>
                                            <br>
                                        </div>
                                    </div>
                                    <div class="col-md-2 col-sm-2">
                                        <div class="form-group">
                                            <label  class="control-label" for="number_contractThree"><fmt:message key="label.number_contract"/></label>
                                            <input type="text" class="form-control" id="number_contractThree" name="budget.paymentInformationBudgets[2].paymentInformation.contract.id" value="${budget.contract[0].id}"/>
                                        </div>
                                    </div>
                                    <div class="col-md-3 col-sm-3">
                                        <div class="form-group">
                                            <label  class="control-label" for="formPaymentThree"><fmt:message key="label.formPayment"/></label>
                                            <select class="form-control" id="formPaymentThree" name="budget.paymentInformationBudgets[2].paymentInformation.formPayment.id">
                                                <option><fmt:message key="label.select" /> </option>
                                                <c:forEach items="${formsPayment}" var="formPayment">
                                                    <option value="${formPayment.id}" >${formPayment.description}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-2 col-sm-2">
                                        <div class="form-group">
                                            <label  class="control-label" for="totalPriceThree"><fmt:message key="label.totalPrice"/></label>
                                            <input  type="text" class="form-control" id="totalPriceThree" name="budget.paymentInformationBudgets[2].paymentInformation.totalPrice" readonly>
                                        </div>
                                    </div>
                                    <div class="col-md-2 col-sm-2">
                                        <div class="form-group">
                                            <label  class="control-label" for="discountPercentageThree"><fmt:message key="label.discountPercentage"/></label>
                                            <input  type="text" class="form-control" id="discountPercentageThree" name="budget.paymentInformationBudgets[2].paymentInformation.discountPercentage">
                                        </div>
                                    </div>
                                    <div class="col-md-2 col-sm-2">
                                        <div class="form-group">
                                            <label  class="control-label" for="totalFinalPriceThree"><fmt:message key="label.totalFinalPrice"/></label>
                                            <input  type="text" class="form-control" id="totalFinalPriceThree" name="budget.paymentInformationBudgets[2].paymentInformation.totalFinalPrice" readonly>
                                        </div>
                                    </div>
                                    <div class="col-md-2 col-sm-2">
                                        <div class="form-group">
                                            <label  class="control-label" for="inputPriceThree"><fmt:message key="label.inputPrice"/></label>
                                            <input type="text" class="form-control" id="inputPriceThree" name="budget.paymentInformationBudgets[2].paymentInformation.inputPrice"/>
                                        </div>
                                    </div>
                                    <div class="col-md-2 col-sm-2">
                                        <div class="form-group">
                                            <label  class="control-label" for="sharePriceThree"><fmt:message key="label.sharePrice"/></label>
                                            <input type="text" class="form-control" id="sharePriceThree" name="budget.paymentInformationBudgets[2].paymentInformation.sharePrice" readonly/>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-2 col-sm-2">
                                        <div class="form-group">
                                            <label  class="control-label" for="dateInputThree"><fmt:message key="label.dateInput"/></label>
                                            <input  type="text" class="form-control date" id="dateInputThree" name="budget.paymentInformationBudgets[2].paymentInformation.dateInput">
                                        </div>
                                    </div>
                                    <div class="col-md-2 col-sm-2">
                                        <div class="form-group">
                                            <label  class="control-label" for="dateFirstInstallmentThree"><fmt:message key="label.dateFirstInstallment"/></label>
                                            <input  type="text" class="form-control date" id="dateFirstInstallmentThree" name="budget.paymentInformationBudgets[2].paymentInformation.dateFirstInstallment">
                                        </div>
                                    </div>
                                    <div class="col-md-2 col-sm-2">
                                        <div class="form-group">
                                            <label  class="control-label" for="dateLastInstallmentThree"><fmt:message key="label.dateLastInstallment"/></label>
                                            <input  type="text" class="form-control date" id="dateLastInstallmentThree" name="budget.paymentInformationBudgets[2].paymentInformation.dateLastInstallment">
                                        </div>
                                    </div>
                                    <div class="col-md-2 col-sm-2">
                                        <div class="form-group">
                                            <label  class="control-label" for="expirationDateThree"><fmt:message key="label.expiration_date"/></label>
                                            <input type="text" class="form-control date" id="expirationDateThree" name="budget.paymentInformationBudgets[2].paymentInformation.expirationDate"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <c:if test="${quotation.status eq 'Open'}">
                <div class="row">
                    <div class="col-sm-offset-11 col-md-offset-11">
                        <div class="form-group">
                            <input type="hidden" name="budget.id" value="${budget.id}" >
                            <input type="hidden" name="budget.quotation.id" value="${quotation.id}" >
                            <button type="submit" class="btn btn-success"><fmt:message key="button.save" /></button>
                        </div>
                    </div>
                </div>
            </c:if>
        </form>
    </div>
</html:template>
</body>
<html:searchSupplier/>
<html:jsAssets/>
<script src="${pageContext.request.contextPath}/asset/js/vendor/jquery.mask.min.js"></script>
<script src="${pageContext.request.contextPath}/asset/js/custom/modalSearchSupplier.js"></script>
<script src="${pageContext.request.contextPath}/asset/js/commons/formCommons.js"></script>
<script src="${pageContext.request.contextPath}/asset/js/vendor/price_format.2.0.min.js"></script>
<script src="${pageContext.request.contextPath}/asset/js/vendor/moment.js"></script>
<script src="${pageContext.request.contextPath}/asset/js/custom/form-quotation-budget.js"></script>
</html>
