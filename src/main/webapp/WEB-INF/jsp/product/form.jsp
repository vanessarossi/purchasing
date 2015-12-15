<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title><fmt:message key="title.page.product" /> - <fmt:message key="title.purchasing" /></title>
    <html:head />
    <html:formAssets/>
</head>
<body>
<html:template>
    <form action='<c:url value="/produto/salvar"></c:url>' method="post" id="productForm">
        <div class="container-fluid">
            <div class="page-header">
                <h3><fmt:message key="title.product" /></h3>
            </div>
            <div class="row">
                <div class="col-sm-5 col-md-5">
                    <div class="form-group">
                        <label class="control-label"><fmt:message key="label.description"></fmt:message></label><span class="required"> *</span>
                        <input type="text" class="form-control" id="description" name="product.description" value="${product.description}"/>
                        <span class="required">${errors.from('product.description')}</span>
                    </div>
                </div>
                <div class="col-sm-5 col-md-5">
                    <div class="form-group">
                        <label class="control-label"><fmt:message key="label.model"></fmt:message></label>
                        <input type="text" class="form-control" id="model" name="product.model" value="${product.model}"/>
                        <span class="required">${errors.from('product.model')}</span>
                    </div>
                </div>
            </div>
            <div class="row" >
                <div class="col-sm-4 col-md-4">
                    <div class="form-group">
                        <label class="control-label"><fmt:message key="label.mark"></fmt:message></label>
                        <input type="text" class="form-control" id="mark" name="product.mark" value="${product.mark}"/>
                        <span class="required">${errors.from('product.mark')}</span>
                    </div>
                </div>
                <div class="col-sm-3 col-md-3">
                    <div class="form-group">
                        <label class="control-label"><fmt:message key="label.unit"></fmt:message></label><span class="required"> *</span>
                        <select class="form-control" id="unit" name="product.unit.id">
                            <option value="" ><fmt:message key="label.select"/></option>
                            <c:forEach items="${units}" var="unit">
                                <option value="${unit.id}" <c:if test="${product.unit.id eq unit.id}">selected</c:if> >${unit.description} </option>
                            </c:forEach>
                        </select>
                        <span class="required">${errors.from('product.unit')}</span>
                    </div>
                </div>
                <div class="col-sm-4 col-md-4">
                    <div class="form-group">
                        <label class="control-label"><fmt:message key="label.category"></fmt:message></label><span class="required"> *</span>
                        <select class="form-control" id="category" name="product.category.id">
                            <option value="" ><fmt:message key="label.select"/></option>
                            <c:forEach items="${categories}" var="category">
                                <option value="${category.id}" <c:if test="${product.category.id eq category.id}">selected</c:if> >${category.description} </option>
                            </c:forEach>
                        </select>
                        <span class="required">${errors.from('product.category')}</span>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-2 col-md-2">
                    <div class="form-group">
                        <label class="control-label"><fmt:message key="label.minimumStock"></fmt:message></label>
                        <c:set var="minimumStock" value="${fn:replace(product.minimumStock,'.', ',')}" />
                        <input type="text" class="form-control" id="minimumStock" name="product.minimumStock" value="${minimumStock}"/>
                        <span class="required">${errors.from('product.minimumStock')}</span>
                    </div>
                </div>
                <div class="col-sm-5 col-md-5">
                    <div class="form-group">
                        <label class="control-label"><fmt:message key="label.barCode"></fmt:message></label>
                        <input type="text" class="form-control" id="barCode" name="product.barCode" value="${product.barCode}"/>
                        <span class="required">${errors.from('product.barCode')}</span>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-5 col-md-5">
                    <div class="form-group">
                        <label class="control-label"><fmt:message key="label.observation"></fmt:message></label>
                        <textarea rows="6" cols="9" class="form-control" id="observation" name="product.observation">${product.observation}</textarea>
                        <span class="required">${errors.from('product.observation')}</span>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-offset-9 col-md-offset-9">
                    <div class="form-group">
                        <input type="hidden" name="product.id" value="${product.id}" >
                        <button type="submit" class="btn btn-success"><fmt:message key="button.save" /></button>
                        <a href='<c:url value="/produto"></c:url>' type="button" class="btn btn-danger"><fmt:message key="button.cancel"/></a>
                    </div>
                </div>
            </div>
        </div>
    </form>
</html:template>
</body>
<html:jsAssets/>
<script src="${pageContext.request.contextPath}/asset/js/custom/form-product.js"></script>
</html>
