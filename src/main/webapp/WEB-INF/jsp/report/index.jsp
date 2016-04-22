<!DOCTYPE html>
<html lang="pt-br">
<head>
  <title><fmt:message key="title.page.report" /> - <fmt:message key="title.purchasing" /></title>
  <html:head />
</head>
<body>
<html:template>
  <div class="panel panel-default">
    <div class="panel-body">
      <div class="row">
        <div class="form-group">
          <h2 class="lead text-center"><fmt:message key="label.choose.report"></fmt:message> </h2>
        </div>
      </div>
      <div class="page-header"></div>
      <div class="row">
        <div class="col-xs-6 col-sm-6 col-md-6  col-lg-6">
          <div class="form-group">
            <c:if test="${rules[EighthLevelAccessRule].isAllowed()}">
              <div class="radio">
              <label class="control-label">
                <input type="radio" value="purchaseOrderAndSolicitationReport" name="report" id="report"> <fmt:message key="label.purchase.report"/>
              </label>
            </div>
            </c:if>
            <c:if test="${rules[EighthLevelAccessRule].isAllowed()}">
              <div class="radio">
              <label class="control-label">
                <input type="radio" value="totalPurchasedProductClassificationReport" name="report" id="report"> <fmt:message key="label.total.purchased.product.classification.report"/>
              </label>
            </div>
            </c:if>

            <c:if test="${rules[EighthLevelAccessRule].isAllowed()}">
              <div class="radio">
                <label class="control-label">
                  <input type="radio" value="totalPurchasedProductClassificationByCostCenterReport" name="report" id="report"> <fmt:message key="label.total.purchased.product.classification.cost.center.report"/>
                </label>
              </div>
            </c:if>


            <c:if test="${rules[EighthLevelAccessRule].isAllowed()}">
              <div class="radio">
              <label class="control-label">
                <input type="radio" value="totalPurchasedServiceTypeReport" name="report" id="report"> <fmt:message key="label.total.purchased.service.type.report"/>
              </label>
            </div>
            </c:if>

            <c:if test="${rules[EighthLevelAccessRule].isAllowed()}">
              <div class="radio">
                <label class="control-label">
                  <input type="radio" value="totalPurchasedServiceTypeByCostCenterReport" name="report" id="report"> <fmt:message key="label.total.purchased.service.type.cost.center.report"/>
                </label>
              </div>
            </c:if>

            <c:if test="${rules[SeventhLevelAccessRule].isAllowed()}">
              <div class="radio">
              <label class="control-label">
                <input type="radio" value="financialManagementByCostCenterReport" name="report" id="report"> <fmt:message key="label.financial.management.by.cost.center.report"/>
              </label>
            </div>
            </c:if>
            <c:if test="${rules[ThirdLevelAccessRule].isAllowed()}">
              <div class="radio">
                <label class="control-label">
                  <input type="radio" value="financialManagementReport" name="report" id="report"> <fmt:message key="label.financial.management.report"/>
                </label>
            </div>
            </c:if>
            <c:if test="${rules[EighthLevelAccessRule].isAllowed()}">
              <div class="radio">
              <label class="control-label">
                <input type="radio" value="purchaseOrderForSupplierAndExpirationDateReport" name="report" id="report"> <fmt:message key="label.purchase.order.for.supplier.and.expiration.date.report"/>
              </label>
            </div>
            </c:if>
          </div>
        </div>
        <div class="col-xs-6 col-sm-6 col-md-6  col-lg-6">
          <div class="page-header"></div>
          <form action='' method="post" id="formReportOne">
            <div class="row">
              <div class="col-xs-3 col-sm-3 col-md-2  col-lg-2">
                <div class="form-group">
                  <label><fmt:message key="label.year" /></label>
                  <input type="text" class="form-control" id="year" name="report.year">
                </div>
              </div>
              <div class="col-xs-4 col-sm-4 col-md-4  col-lg-4">
                <div class="form-group">
                  <label class="control-label"><fmt:message key="label.initial.month" /></label>
                  <select class="form-control" id="initialMonth" name="report.initialMonth">
                    <option value=""><fmt:message key="label.select" /></option>
                    <option value="01"><fmt:message key="label.month.january" /></option>
                    <option value="02"><fmt:message key="label.month.february" /></option>
                    <option value="03"><fmt:message key="label.month.march" /></option>
                    <option value="04"><fmt:message key="label.month.april" /></option>
                    <option value="05"><fmt:message key="label.month.may" /></option>
                    <option value="06"><fmt:message key="label.month.june" /></option>
                    <option value="07"><fmt:message key="label.month.july" /></option>
                    <option value="08"><fmt:message key="label.month.august" /></option>
                    <option value="09"><fmt:message key="label.month.september" /></option>
                    <option value="10"><fmt:message key="label.month.october" /></option>
                    <option value="11"><fmt:message key="label.month.november" /></option>
                    <option value="12"><fmt:message key="label.month.december" /></option>
                  </select>
                </div>
              </div>
              <div class="col-xs-4 col-sm-4 col-md-4  col-lg-4">
                <div class="form-group">
                  <label class="control-label"><fmt:message key="label.final.month" /></label>
                  <select class="form-control" id="lastMonth" name="report.lastMonth">
                    <option value=""><fmt:message key="label.select" /></option>
                    <option value="01"><fmt:message key="label.month.january" /></option>
                    <option value="02"><fmt:message key="label.month.february" /></option>
                    <option value="03"><fmt:message key="label.month.march" /></option>
                    <option value="04"><fmt:message key="label.month.april" /></option>
                    <option value="05"><fmt:message key="label.month.may" /></option>
                    <option value="06"><fmt:message key="label.month.june" /></option>
                    <option value="07"><fmt:message key="label.month.july" /></option>
                    <option value="08"><fmt:message key="label.month.august" /></option>
                    <option value="09"><fmt:message key="label.month.september" /></option>
                    <option value="10"><fmt:message key="label.month.october" /></option>
                    <option value="11"><fmt:message key="label.month.november" /></option>
                    <option value="12"><fmt:message key="label.month.december" /></option>
                  </select>
                </div>
              </div>
              <div class="col-xs-offset-10 col-sm-offset-10 col-md-offset-10  col-lg-offset-10">
                <br>
                <input type="submit" class="btn btn-primary" value="<fmt:message key="button.generate" />" />
              </div>
            </div>
          </form>
          <form action='' method="post" id="formReportTwo">
            <div class="row">
              <div class="col-xs-4 col-sm-4 col-md-3  col-lg-3">
                <div class="form-group">
                  <label><fmt:message key="label.supplier" /></label>
                  <input type="text" class="form-control" id="supplier" name="report.supplier">
                </div>
              </div>
              <div class="col-xs-8 col-sm-8 col-md-4  col-lg-4">
                <div class="form-group">
                  <label><fmt:message key="label.expiration.date" /></label>
                  <input type="text" class="form-control date" id="expirationDate" name="report.expirationDate">
                </div>
              </div>
              <div class="col-xs-offset-10 col-sm-offset-10 col-md-offset-10  col-lg-offset-10">
                <br>
                <input type="submit" class="btn btn-primary" value="<fmt:message key="button.generate" />" />
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</html:template>
</body>
<html:jsAssets/>
<script src="${pageContext.request.contextPath}/asset/js/vendor/jquery.mask.min.js"></script>
<script src="${pageContext.request.contextPath}/asset/js/commons/formCommons.js"></script>
<script src="${pageContext.request.contextPath}/asset/js/custom/form_report.js"></script>
</html>
