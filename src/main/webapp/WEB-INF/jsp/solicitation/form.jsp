<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title><fmt:message key="title.page.solicitation" /> - <fmt:message key="title.purchasing" /></title>
    <html:head />
    <html:formAssets/>
</head>
<body>
<html:template>
<form action='<c:url value="/solicitacao/salvar"></c:url>' method="post" id="solicitationForm">
    <c:if test="${!empty errors.from('message.error.solicitiation.material')}">
            <div class="row">
                <div class="alert alert-danger alert-dismissible text-center" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong> <fmt:message key="label.atention" /> </strong> <span>${errors.from('message.error.solicitiation.material')}</span>
                </div>
            </div>
        </c:if>
    <c:if test="${!empty errors.from('message.error.solicitiation.service')}">
            <div class="alert alert-danger alert-dismissible text-center" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong> <fmt:message key="label.atention" /> </strong> <span>${errors.from('message.error.solicitiation.service')}</span>
            </div>
        </c:if>
    <c:if test="${!empty errors.from('message.error.solicitiation.service.material')}">
            <div class="alert alert-danger alert-dismissible text-center" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong > <fmt:message key="label.atention" /> </strong> <span>${errors.from('message.error.solicitiation.service.material')}</span>
            </div>
        </c:if>
    <div class="page-header">
            <h3><fmt:message key="title.solicitation" /></h3>
        </div>
    <div class="row">
            <div class="col-md-2 col-sm-2">
                <div class="form-group">
                    <label  class="control-label" for="type"><fmt:message key="label.type"/></label>
                    <select class="form-control"  id="type" name="solicitation.type">
                        <c:forEach items="${types}" var="type">
                            <option value="${type}" <c:if test="${solicitation.type == type }"> selected  </c:if> ><c:out value="${type.description}"></c:out> </option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-md-4 col-sm-4">
                <label  class="control-label" for="name"><fmt:message key="label.name"/></label>
                <input type="text" class="form-control" id="name" readonly="true" value="${user.name}"/>
            </div>
            <div class="col-md-3 col-sm-3">
                <label  class="control-label" for="costcenter"><fmt:message key="label.costCenter"/></label>
                <select class="form-control"  id="costcenter" name="solicitation.costCenter.id">
                    <c:forEach items="${user.costCenters}" var="costCenter">
                        <option value="${costCenter.id}" <c:if test="${solicitation.costCenter.id == costCenter.id }"> selected  </c:if> ><c:out value="${costCenter.description}"></c:out> </option>
                    </c:forEach>
                </select>
            </div>
        </div>
    <div class="row">
            <div class="col-md-3 col-sm-3">
                <div class="form-group">
                    <label class="control-label" for="urgency"><fmt:message key="label.urgency"/></label>
                    <br>
                    <div class="radio-inline">
                        <label class="control-label">
                            <input type="radio" value="true" name="solicitation.urgency" id="urgency" <c:if test="${solicitation.urgency eq true}"> checked </c:if> > <fmt:message key="label.yes"/>
                        </label>
                    </div>
                    <div class="radio-inline">
                        <label class="control-label">
                            <input type="radio" value="false" name="solicitation.urgency" id="urgency" <c:if test="${solicitation.urgency eq false}"> checked </c:if> > <fmt:message key="label.no"/>
                        </label>
                    </div>
                    <br>
                </div>
            </div>
            <div class="col-md-3 col-sm-3">
                <div class="form-group">
                    <label class="control-label" for="emergency"><fmt:message key="label.emergency"/></label>
                    <br>
                    <div class="radio-inline">
                        <label class="control-label">
                            <input type="radio" value="true" name="solicitation.emergency" id="emergency" <c:if test="${solicitation.emergency eq true}"> checked </c:if>> <fmt:message key="label.yes"/>
                        </label>
                    </div>
                    <div class="radio-inline">
                        <label class="control-label">
                            <input type="radio" value="false" name="solicitation.emergency" id="emergency" <c:if test="${solicitation.emergency eq false}"> checked </c:if>> <fmt:message key="label.no"/>
                        </label>
                    </div>
                    <br>
                </div>
            </div>
        </div>
    <div  id="divService" class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><fmt:message key="title.solicitation.service" /></h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-3 col-sm-3">
                            <div class="form-group">
                                <label  class="control-label" for="typeService"><fmt:message key="label.typeService"/></label> <span class="required"><fmt:message key="label.asterisk" /></span>
                                <select class="form-control"  id="typeService" name="solicitationRequest.service.typeService.id">
                                    <c:forEach items="${typesService}" var="typeService">
                                        <option value="${typeService.id}" <c:if test="${solicitationRequest.service.typeService.id == typeService.id }"> selected  </c:if> ><c:out value="${typeService.description}"></c:out> </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-9 col-sm-9">
                            <div class="form-group">
                                <label  class="control-label" for="description"><fmt:message key="label.description"/></label> <span class="required"><fmt:message key="label.asterisk" /></span>
                                <textarea rows="4" cols="100" class="form-control" id="description" name="solicitationRequest.service.description">${solicitationRequest.service.description}</textarea>
                            </div>
                        </div>
                        <input type="hidden" name="solicitationRequest.id" value="${solicitationRequest.id}" >
                        <input type="hidden" name="solicitationRequest.service.id" value="${solicitationRequest.service.id}">
                    </div>
                </div>
            </div>
    <div  id="divMaterial" class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title"><fmt:message key="title.solicitation.products" /></h3>
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-md-1 col-sm-1">
                        <div class="form-group">
                            <label class="control-label"  for="code"><fmt:message key="label.code"/></label>
                            <input type="text" class="form-control" id="code" >
                        </div>
                    </div>
                    <div class="col-md-7 col-sm-7">
                        <div class="form-group">
                            <label class="control-label" for="product"><fmt:message key="label.product"/></label>
                            <div class="input-group">
                                <input type="text" class="form-control" id="product" readonly>
                                <span class="input-group-btn"><button class="btn btn-default" type="button" data-toggle="modal" data-target="#searchProduct"><span class="fa fa-search"></span></button></span>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1 col-sm-1">
                        <div class="form-group">
                            <label class="control-label" for="quantity"><fmt:message key="label.abbreviatedQuantity"/></label>
                            <input type="text" class="form-control" id="quantity">
                        </div>
                    </div>
                    <div class="col-md-2 col-sm-2">
                        <div class="form-group">
                            <label class="control-label"><fmt:message key="label.unit"/></label>
                            <input type="text" class="form-control" id="unit" readonly>
                        </div>
                    </div>
                    <div class="col-sm-offset-10 col-md-offset-10">
                        <div class="form-group">
                            <br/>
                            <a class="btn btn-success btn-sm" onclick="addProduct()" id="addProduct"><span class="fa fa-plus"></span></a>
                            <a class="btn btn-success btn-sm" id="confirmUpdate" ><span class="fa fa-check"></span></a>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <table class="table" id="tableProductSolicitation">
                        <thead>
                        <tr>
                            <th style="width:1%"><fmt:message key="table.code"/></th>
                            <th style="width:50%"><fmt:message key="table.product"/></th>
                            <th style="width:5%"><fmt:message key="table.abbreviatedQuantity"/></th>
                            <th style="width:6%"><fmt:message key="table.unit"/></th>
                            <th style="width:1%"> </th>
                            <th style="width:1%"> </th>
                        </tr>
                        </thead>
                        <tbody>
                        <input type="hidden" id="counter" value="${fn:length(solicitation.solicitationRequests)}">
                        <c:forEach items="${solicitation.solicitationRequests}" var="solicitationRequest" varStatus="i">
                            <c:set var="quantity" value="${fn:replace(solicitationRequest.quantity, '.', ',')}"/>
                            <c:if test="${solicitationRequest.service == null}">
                                <tr id="${i.index}">
                                    <input type="hidden" name="solicitation.solicitationRequests[${i.index}].product.id" id="product${i.index}"  value="${solicitationRequest.product.id}" />
                                    <input type="hidden" name="solicitation.solicitationRequests[${i.index}].quantity" id="quantity${i.index}" value="${quantity}"/>
                                    <input type="hidden" name="solicitation.solicitationRequests[${i.index}].id"  id="id${i.index}" value="${solicitationRequest.id}" />
                                    <td>${solicitationRequest.product.id}</td>
                                    <td>${solicitationRequest.product.description} ${solicitationRequest.product.model} ${solicitationRequest.product.mark}</td>
                                    <td>${quantity}</td>
                                    <td>${solicitationRequest.product.unit.description}</td>
                                    <td><a class="btn btn-default btn-xs" onclick="editProduct(${i.index},<c:choose>
                                    <c:when test="${solicitationRequest.id eq null}">0</c:when><c:otherwise>${solicitationRequest.id}</c:otherwise></c:choose>)" ><span class="fa fa-edit"></span></a></td>
                                    <td><a class="btn btn-default btn-xs" onclick="removeProduct(${i.index},<c:choose><c:when test="${solicitationRequest.id eq null}">0</c:when><c:otherwise>${solicitationRequest.id}</c:otherwise></c:choose>)"><span class="fa fa-trash-o"></span></a></td>
                                </tr>
                            </c:if>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    <div class="row">
            <div class="col-md-12 col-sm-12">
                <div class="form-group">
                    <label  class="control-label" for="observation"><fmt:message key="label.observation"/></label>
                    <textarea rows="4" cols="100" class="form-control" id="observation" name="solicitation.observation">${solicitation.observation}</textarea>
                </div>
            </div>
        </div>
    <div class="row">
            <div class="col-sm-offset-10 col-md-offset-10">
                <div class="form-group">
                    <input type="hidden" name="solicitation.id" value="${solicitation.id}" >
                    <button type="submit" class="btn btn-success"><fmt:message key="button.save" /></button>
                    <a href='<c:url value="/home"></c:url>' type="button" class="btn btn-danger"><fmt:message key="button.cancel"/></a>
                </div>
            </div>
        </div>
</form>
</html:template>
</body>
<html:jsAssets/>
<html:searchProduct/>
<script src="${pageContext.request.contextPath}/asset/js/custom/form-solicitation.js"></script>
<script src="${pageContext.request.contextPath}/asset/js/custom/modalSearchProduct.js"></script>
</html>

