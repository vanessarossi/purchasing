<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title><fmt:message key="title.page.supplier" /> - <fmt:message key="title.purchasing" /></title>
    <html:head />
    <html:formAssets/>
</head>
<body>
<html:template>
    <div class="container-fluid">
        <div class="page-header">
            <h3><fmt:message key="title.supplier" /></h3>
        </div>
        <div class="row">
            <div class="col-sm-2 col-md-2">
                <div class="form-group">
                    <label class="control-label"><fmt:message key="label.typePerson"></fmt:message></label>
                    <select class="form-control" id="typePerson" name="person.typePerson">
                        <c:forEach items="${typesPerson}" var="typePerson">
                            <option value="${typePerson}" <c:if test="${person.typePerson eq typePerson}">selected</c:if>>${typePerson.description}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-sm-3 col-md-3 ">
                <div class="form-group">
                    <label class="control-label" for="category"><fmt:message key="label.category"></fmt:message></label><span class="required"> *</span>
                    <select class="form-control" id="category" name="supplier.category.id">
                        <option value=""><fmt:message key="label.select"/></option>
                        <c:forEach items="${categories}" var="category">
                            <option value="${category.id}" <c:if test="${category.id eq supplier.category.id}">selected</c:if>>${category.description}</option>
                        </c:forEach>
                    </select>
                    <span class="redText">${errors.from('supplier.category')}</span>
                </div>
            </div>
        </div>
        <div id="divNaturalPerson">
            <div class="row">
                <div class="col-md-2 col-sm-2">
                    <div class="form-group" id="divCpf">
                        <label for="cpf" class="control-label"><fmt:message key="label.cpf"></fmt:message></label><span class="required"> *</span>
                        <input type="text" id="cpf" class="form-control" name="naturalPerson.cpf" value="<c:if test="${person.typePerson eq 'NaturalPerson'}">${naturalPerson.cpf}</c:if>"/>
                        <span class="redText">${errors.from('naturalPerson.cpf')}</span>
                        <span class="redText" id="cpfEquals"><fmt:message key="message.cpfEquals"/> </span>
                    </div>
                </div>
                <div class="col-md-2 col-sm-2">
                    <div class="form-group" id="rgDiv">
                        <label for="rg" class="control-label"><fmt:message key="label.rg"></fmt:message></label><span class="required"> *</span>
                        <input type="text" id="rg" class="form-control" name="naturalPerson.rg" value="<c:if test="${person.typePerson eq 'NaturalPerson'}">${naturalPerson.rg}</c:if>" />
                        <span class="redText">${errors.from('naturalPerson.rg')}</span>
                    </div>
                </div>
                <div class="col-md-2 col-sm-2">
                    <div class="form-group">
                        <label for="emittingOrga" class="control-label"><fmt:message key="label.emittingOrgan"></fmt:message></label><span class="required"> *</span>
                        <input type="text" id=emittingOrga class="form-control" name="naturalPerson.emittingOrgan" value="<c:if test="${person.typePerson eq 'NaturalPerson'}">${naturalPerson.emittingOrgan}</c:if>" />
                        <span class="redText">${errors.from('naturalPerson.emittingOrgan')}</span>
                    </div>
                </div>
            </div>
        </div>
        <div id="divJuristicPerson">
            <div class="row">
                <div class="col-md-4 col-sm-4">
                    <div class="form-group">
                        <label for="companyName" class="control-label"><fmt:message key="label.companyName"></fmt:message></label><span class="required"> *</span>
                        <input type="text" id="companyName" class="form-control" name="juristicPerson.companyName" value="<c:if test="${person.typePerson eq 'JuristicPerson'}">${juristicPerson.companyName}</c:if>" />
                        <span class="redText">${errors.from('juristicPerson.companyName')}</span>
                    </div>
                </div>
                <div class="col-md-2 col-sm-2">
                    <div class="form-group" id="cnpjDiv">
                        <label for="cnpj" class="control-label"><fmt:message key="label.cnpj"></fmt:message></label><span class="required"> *</span>
                        <input type="text" id="cnpj" class="form-control" name="juristicPerson.cnpj" value="<c:if test="${person.typePerson eq 'JuristicPerson'}">${juristicPerson.cnpj}</c:if>" />
                        <span class="redText">${errors.from('juristicPerson.cnpj')}</span>
                        <span class="redText" id="cnpjEquals"><fmt:message key="message.cnpjEquals"/> </span>
                    </div>
                </div>
                <div class="col-md-2 col-sm-2">
                    <div class="form-group">
                        <label for="municipalInscription" class="control-label"><fmt:message key="label.municipalInscription"></fmt:message></label>
                        <input type="text" id="municipalInscription" class="form-control" name="juristicPerson.municipalInscription" value="<c:if test="${person.typePerson eq 'JuristicPerson'}">${juristicPerson.municipalInscription}</c:if>"/>
                    </div>
                </div>
                <div class="col-md-2 col-sm-2">
                    <div class="form-group">
                        <label for="stateInscription" class="control-label"><fmt:message key="label.stateInscription"></fmt:message></label>
                        <input type="text" id="stateInscription" class="form-control" name="juristicPerson.stateInscription" value="<c:if test="${person.typePerson eq 'JuristicPerson'}">${juristicPerson.stateInscription}</c:if>" />
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 col-sm-6">
                <div class="form-group">
                    <label class="control-label" for="name"><fmt:message key="label.name"></fmt:message>/<fmt:message key="label.corporateName"></fmt:message></label><span class="required"> *</span>
                    <input type="text" class="form-control" id="name" name="person.name" value="${person.name}" />
                    <span class="redText">${errors.from('person.name')}</span>
                </div>
            </div>
            <div class="col-md-4 col-sm-4">
                <div class="form-group">
                    <label class="control-label" for="street"><fmt:message key="label.street"></fmt:message></label><span class="required"> *</span>
                    <input type="text" class="form-control" id="street" name="address.street" value="${address.street}"/>
                    <span class="redText">${errors.from('address.street')}</span>
                </div>
            </div>
            <div class="col-md-2 col-sm-2">
                <div class="form-group">
                    <label class="control-label" for="number"><fmt:message key="label.number"></fmt:message></label><span class="required"> *</span>
                    <input type="text" class="form-control" id="number" name="address.number" value="${address.number}" />
                    <span class="redText">${errors.from('address.number')}</span>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4 col-sm-4">
                <div class="form-group">
                    <label class="control-label" for="neighborhood"><fmt:message key="label.neighborhood"></fmt:message></label><span class="required"> *</span>
                    <input type="text" class="form-control" id="neighborhood" name="address.neighborhood" value="${address.neighborhood}" />
                    <span class="redText">${errors.from('address.neighborhood')}</span>
                </div>
            </div>
            <div class="col-md-4 col-sm-4">
                <div class="form-group">
                    <label class="control-label" for="city"><fmt:message key="label.city"></fmt:message></label><span class="required"> *</span>
                    <input type="text" class="form-control" id="city" name="address.city" value="${address.city}" />
                    <span class="redText">${errors.from('address.city')}</span>
                </div>
            </div>
            <div class="col-md-3 col-sm-3">
                <div class="form-group">
                    <label class="control-label" for="state"><fmt:message key="label.state"></fmt:message></label><span class="required"> *</span>
                    <select id="state" name="address.state.id" class="form-control">
                        <option value=""><fmt:message key="label.select" /></option>
                        <c:forEach items="${states}" var="state">
                            <option value="${state.id}" <c:if test="${state.id eq address.state.id}">selected</c:if>>${state.name} - ${state.acronym}</option>
                        </c:forEach>
                    </select>
                    <span class="redText">${errors.from('address.state')}</span>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2 col-sm-2">
                <div class="form-group">
                    <label  class="control-label" for="zipCode"><fmt:message key="label.zipCode"></fmt:message></label><span class="required"> *</span>
                    <input type="text" class="form-control" id="zipCode" name="address.zipCode" value="${address.zipCode}"/>
                    <span class="redText">${errors.from('address.zipCode')}</span>
                </div>
            </div>
            <div class="col-md-2 col-sm-2">
                <div class="form-group">
                    <label class="control-label" for="phone"><fmt:message key="label.phone"></fmt:message></label>
                    <input type="text" class="form-control" id="phone" name="contact.phone" value="${contact.phone}"/>
                    <span class="redText">${errors.from('contact.phone')}</span>
                </div>
            </div>
            <div class="col-md-2 col-sm-2">
                <div class="form-group">
                    <label class="control-label" for="secondaryPhone"><fmt:message key="label.secondaryPhone"></fmt:message></label>
                    <input type="text" class="form-control" id="secondaryPhone" name="contact.secondaryPhone" value="${contact.secondaryPhone}"/>
                    <span class="redText">${errors.from('contact.secondaryPhone')}</span>
                </div>
            </div>
            <div class="col-md-2 col-sm-2">
                <div class="form-group">
                    <label class="control-label" for="cellPhone"><fmt:message key="label.cellPhone"></fmt:message></label>
                    <input type="text" class="form-control" id="cellPhone" name="contact.cellPhone" value="${contact.cellPhone}"/>
                    <span class="redText">${errors.from('contact.cellPhone')}</span>
                </div>
            </div>
            <div class="col-md-2 col-sm-2">
                <div class="form-group">
                    <label class="control-label" for="secondaryCellPhone"><fmt:message key="label.secondaryCellPhone"></fmt:message></label>
                    <input type="text" class="form-control" id="secondaryCellPhone" name="contact.secondaryCellPhone" value="${contact.secondaryCellPhone}"/>
                    <span class="redText">${errors.from('contact.secondaryCellPhone')}</span>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4 col-sm-4">
                <div class="form-group">
                    <label class="control-label" for="email"><fmt:message key="label.email"></fmt:message></label>
                    <input type="text" class="form-control" id="email" name="contact.email" value="${contact.email}" />
                    <span class="redText">${errors.from('contact.email')}</span>
                </div>
            </div>
            <div class="col-md-4 col-sm-4">
                <div class="form-group">
                    <label class="control-label" for="website"><fmt:message key="label.website"></fmt:message></label>
                    <input type="text" class="form-control" id="website" name="contact.website" value="${contact.website}"/>
                    <span class="redText">${errors.from('contact.website')}</span>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-5 col-sm-5">
                <div class="form-group">
                    <label class="control-label" for="contactName"><fmt:message key="label.contactName"></fmt:message></label>
                    <input type="text" class="form-control" id="contactName" name="contact.contactName" value="${contact.contactName}"/>
                    <span class="redText">${errors.from('contact.contactName')}</span>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-offset-9 col-md-offset-9">
                <div class="form-group">
                    <input type="hidden" name="supplier.id" value="${supplier.id}" >
                    <button type="submit" class="btn btn-success"><fmt:message key="button.save" /></button>
                    <a href='<c:url value="/fornecedor"></c:url>' type="button" class="btn btn-danger"><fmt:message key="button.cancel"/></a>
                </div>
            </div>
        </div>
    </div>
</html:template>
</body>
<html:jsAssets/>
<script src="${pageContext.request.contextPath}/asset/js/custom/formPayment.js"></script>
</html>

