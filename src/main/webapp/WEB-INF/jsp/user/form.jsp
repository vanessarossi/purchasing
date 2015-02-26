<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title><fmt:message key="title.page.user" /> - <fmt:message key="title.purchasing" /></title>
    <html:head />
    <html:formAssets/>
</head>
<body>
<html:template>
    <form action='<c:url value="/usuario/salvar"></c:url>' method="post" id="userForm">
        <div class="container-fluid">
            <div class="page-header">
                <h3><fmt:message key="title.user" /></h3>
            </div>
            <div class="row">
                <div class="col-sm-4 col-md-4">
                    <div class="form-group">
                        <label class="control-label"><fmt:message key="label.name"></fmt:message></label><span class="required"> *</span>
                        <input type="text" class="form-control" id="name" name="user.name" value="${user.name}" />
                        <span class="redText">${errors.from('user.name')}</span>
                    </div>
                </div>
                <div class="col-sm-3 col-md-3">
                    <div class="form-group">
                        <label class="control-label"><fmt:message key="label.username"></fmt:message></label><span class="required"> *</span>
                        <input type="text" class="form-control" id="username" name="user.username" value="${user.username}" />
                        <span class="redText">${errors.from('user.username')}</span>
                    </div>
                </div>
                <div class="col-sm-3 col-md-3">
                    <div class="form-group">
                        <label class="control-label"><fmt:message key="label.password"></fmt:message></label>
                        <input type="password" class="form-control" id="password" name="user.password"/>
                        <span class="redText">${errors.from('user.password')}</span>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-4 col-md-4">
                    <div class="form-group">
                        <label class="control-label"><fmt:message key="label.email"></fmt:message></label><span class="required"> *</span>
                        <input type="text" class="form-control" id="email" name="user.email" value="${user.email}" />
                        <span class="redText">${errors.from('user.email')}</span>
                    </div>
                </div>
                <div class="col-sm-2 col-md-2">
                    <div class="form-group">
                        <label class="control-label"><fmt:message key="label.active"></fmt:message></label><span class="required"> *</span>
                        <br>
                        <div class="radio-inline">
                            <label class="control-label">
                                <input type="radio" value="true" name="user.active" id="input" <c:if test="${user.active eq true}" > checked </c:if> > <fmt:message key="label.yes"/>
                            </label>
                        </div>
                        <div class="radio-inline">
                            <label class="control-label">
                                <input type="radio" value="false" name="user.active" id="input" <c:if test="${user.active eq false}" > checked </c:if> > <fmt:message key="label.no"/>
                            </label>
                        </div>
                        <br>
                        <span class="redText">${errors.from('user.active')}</span>
                    </div>
                </div>
                <div class="col-sm-3 col-md-3">
                    <div class="form-group">
                        <label class="control-label"><fmt:message key="label.role"></fmt:message></label><span class="required"> *</span>
                        <select id="role" name="user.role.id" class="form-control">
                            <option value=""><fmt:message key="label.select" /></option>
                            <c:forEach items="${roles}" var="role">
                                <option value="${role.id}" <c:if test="${role.id eq user.role.id}">selected</c:if> > ${role.description}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <span class="redText">${errors.from('user.role')}</span>
                </div>
            </div>
            <div class="page-header">
                        <h3><fmt:message key="title.cost.center.user" /></h3>
            </div>
            <div class="row">
                <div class="col-lg-4 col-md-4 col-sm-4">
                        <div class="form-group">
                            <label for="company" class="control-label"> <fmt:message key="label.company"/></label>
                            <select id="company" class="form-control">
                                <option value=""><fmt:message key="label.select"/></option>
                                <c:forEach items="${companies}" var="company">
                                    <option value='<c:out value="${company.id}"></c:out>'><c:out value="${company.companyName}"></c:out></option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-4 col-sm-4">
                        <div class="form-group">
                            <label for="costCenter" class="control-label"><fmt:message key="label.costCenter"/></label>
                            <select  id="costCenter" class="form-control">
                                <option><fmt:message key="label.select"/></option>
                            </select>
                        </div>
                    </div>
                <div class="col-md-offset-8 col-sm-offset-8">
                    </br>
                    <div class="form-group">
                        <a class="btn btn-success btn-sm" onclick="addCostCenter()"><span class="fa fa-plus"></span></a>
                    </div>
                </div>
            </div>
            <div class="row">
                <table id="userCostCenterTable" class="table table-striped table-hover table-condensed">
                    <thead>
                        <tr>
                            <th style="width: 50%"><fmt:message key="table.company" /></th>
                            <th style="width: 50%" ><fmt:message key="table.costCenter"/></th>
                        </tr>
                    </thead>
                    <tbody>
                    <input type="hidden" id="counter" value="${fn:length(user.costCenters)}" />
                    <c:forEach items="${user.costCenters}" var="costCenter" varStatus="i">
                        <tr id="costCenterUser${i.index}">
                            <input type='hidden' name='user.costCenters[${i.index}].id' value="${costCenter.id}">
                            <td><c:out value="${costCenter.company.corporateName}"></c:out></td>
                            <td><c:out value="${costCenter.description}"></c:out></td>
                            <td><a class="btn btn-xs btn-default" onclick="deleteCostCenter(${i.index})" ><span class="fa fa-trash-o"></span></a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="row">
                <div class="col-sm-offset-9 col-md-offset-9">
                    <div class="form-group">
                        <input type="hidden" name="user.id" value="${user.id}" >
                        <button type="submit" class="btn btn-success"><fmt:message key="button.save" /></button>
                        <a href='<c:url value="/usuario"></c:url>' type="button" class="btn btn-danger"><fmt:message key="button.cancel"/></a>
                    </div>
                </div>
            </div>
        </div>
    </form>
</html:template>
</body>
<html:jsAssets/>
<script src="${pageContext.request.contextPath}/asset/js/custom/formUser.js"></script>
</html>

