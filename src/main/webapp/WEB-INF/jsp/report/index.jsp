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
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
          <div class="form-group">
              <div class="radio-inline">
                <label class="control-label">
                  <input type="radio" value="financialManagementReport" name="report" id="report"> <fmt:message key="label.financial.management.report"/>
                </label>
              </div>
              <div class="radio-inline">
                <label class="control-label">
                  <input type="radio" value="totalPurchasedProductClassificationReport" name="report" id="report"> <fmt:message key="label.total.purchased.product.classification.report"/>
                </label>
              </div>
              <div class="radio-inline">
                <label class="control-label">
                  <input type="radio" value="totalPurchasedServiceTypeReport" name="report" id="report"> <fmt:message key="label.total.purchased.service.type.report"/>
                </label>
              </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
          <div class="form-group">
            <div class="radio-inline">
              <label class="control-label">
                <input type="radio" value="financialManagementByCostCenterReport" name="report" id="report"> <fmt:message key="label.financial.management.by.cost.center.report"/>
              </label>
            </div>
            <div class="radio-inline">
              <label class="control-label">
                <input type="radio" value="purchaseOrderAndSolicitationReport" name="report" id="report"> <fmt:message key="label.purchase.report"/>
              </label>
            </div>
            <div class="radio-inline">
              <label class="control-label">
                <input type="radio" value="purchaseOrderForSupplierAndExpirationDateReport" name="report" id="report"> <fmt:message key="label.purchase.order.for.supplier.and.expiration.date.report"/>
              </label>
            </div>
          </div>
        </div>
      </div>
      <div class="page-header"></div>
      <form action='' method="post" id="formReportOne">
        <div class="row">
        <div class="col-xs-3 col-sm-3 col-md-1  col-lg-1">
              <div class="form-group">
                <label><fmt:message key="label.year" /></label>
                <input type="text" class="form-control" id="year" name="report.year">
              </div>
        </div>
        <div class="col-xs-4 col-sm-4 col-md-2  col-lg-2">
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
        <div class="col-xs-4 col-sm-4 col-md-2  col-lg-2">
              <div class="form-group">
                <label class="control-label"><fmt:message key="label.final.month" /></label>
                <select class="form-control" id="finalMonth" name="report.finalMonth">
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
        <br>
        <input type="submit" class="btn btn-primary btn-sm" value="<fmt:message key="button.generate" />" />
      </div>
      </form>
      <form action='' method="post" id="formReportTwo">
        <div class="row">
        <div class="col-xs-3 col-sm-3 col-md-1  col-lg-1">
          <div class="form-group">
            <label><fmt:message key="label.supplier" /></label>
            <input type="text" class="form-control" id="supplier" name="report.supplier">
          </div>
        </div>
        <div class="col-xs-4 col-sm-4 col-md-2  col-lg-2">
          <div class="form-group">
            <label><fmt:message key="label.expiration_date" /></label>
            <input type="text" class="form-control date" id="expirationDate" name="report.expirationDate">
          </div>
        </div>
        <br>
        <input type="submit" class="btn btn-primary btn-sm" value="<fmt:message key="button.generate" />" />
      </div>
      </form>
    </div>
  </div>
</html:template>
</body>
<html:jsAssets/>
<script src="${pageContext.request.contextPath}/asset/js/custom/form_report.js"></script>
<script src="${pageContext.request.contextPath}/asset/js/vendor/jquery.mask.min.js"></script>
<script src="${pageContext.request.contextPath}/asset/js/custom/form-contract.js"></script>
</html>
