<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title><fmt:message key="title.page.solicitation" /> - <fmt:message key="title.purchasing" /></title>
    <html:head />
    <html:formAssets/>
</head>
<body>
<html:template>
    <div class="container-fluid">
        <div class="page-header">
            <h3><fmt:message key="title.suppliers" /></h3>
        </div>
        <a type="button" class="btn btn-primary" href="<c:url value="/fornecedor/formulario" ></c:url>">
            <fmt:message key="button.newSupplier"/>
        </a>
        <br/><br/>
        <table id="supplierTable" class="table table-striped table-hover table-condensed">
            <thead>
            <tr>
                <th style="width: 3%"><fmt:message key="table.code" /></th>
                <th style="width: 52%" ><fmt:message key="table.corporateName"/>/<fmt:message key="table.companyName"/></th>
                <th style="width: 18%" ><fmt:message key="table.phone" /></th>
                <th style="width: 25%"><fmt:message key="table.category" /></th>
                <th style="width: 1%" ><fmt:message key="table.##" /></th>
                <th style="width: 1%" ><fmt:message key="table.##" /></th>
                <th style="width: 1%" ><fmt:message key="table.##" /></th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
    <div class="modal fade" id="viewSupplier" tabindex="-1" role="dialog" aria-labelledby="viewSupplier" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title"><fmt:message key="title.supplier" /></h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-sm-2 col-md-2">
                            <div class="form-group">
                                <label class="control-label"><fmt:message key="label.typePerson"></fmt:message></label><br>
                                <span id="typePerson"></span>
                            </div>
                        </div>
                        <div class="col-sm-4 col-md-4 ">
                            <div class="form-group">
                                <label class="control-label"><fmt:message key="label.category"></fmt:message></label><br>
                                <span id="category"></span>
                            </div>
                        </div>
                    </div>
                    <div id="divNaturalPerson">
                        <div class="row">
                            <div class="col-md-2 col-sm-2">
                                <div class="form-group">
                                    <label class="control-label"><fmt:message key="label.cpf"></fmt:message></label><br>
                                    <span id="cpf"></span>
                                </div>
                            </div>
                            <div class="col-md-2 col-sm-2">
                                <div class="form-group">
                                    <label class="control-label"><fmt:message key="label.rg"></fmt:message></label><br>
                                    <span id="rg"></span>
                                </div>
                            </div>
                            <div class="col-md-2 col-sm-2">
                                <div class="form-group">
                                    <label class="control-label"><fmt:message key="label.emittingOrgan"></fmt:message></label><br>
                                    <span id="emittingOrgan"></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="divJuristicPerson">
                        <div class="row">
                            <div class="col-md-6 col-sm-6">
                                <div class="form-group">
                                    <label class="control-label"><fmt:message key="label.companyName"></fmt:message></label><br>
                                    <span id="companyName"></span>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-3">
                                <div class="form-group">
                                    <label class="control-label"><fmt:message key="label.cnpj"></fmt:message></label><br>
                                    <span id="cnpj"></span>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3 col-sm-3">
                                <div class="form-group">
                                    <label class="control-label"><fmt:message key="label.municipalInscription"></fmt:message></label><br>
                                    <span id="municipalInscription"></span>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-3">
                                <div class="form-group">
                                    <label class="control-label"><fmt:message key="label.stateInscription"></fmt:message></label><br>
                                    <span id="stateInscription"></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 col-sm-6">
                            <div class="form-group">
                                <label class="control-label"><fmt:message key="label.name"></fmt:message>/<fmt:message key="label.corporateName"></fmt:message></label><br>
                                <span id="name"></span>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-4">
                            <div class="form-group">
                                <label class="control-label"><fmt:message key="label.street"></fmt:message></label><br>
                                <span id="street"></span>
                            </div>
                        </div>
                        <div class="col-md-2 col-sm-2">
                            <div class="form-group">
                                <label class="control-label"><fmt:message key="label.number"></fmt:message></label><br>
                                <span id="number"></span>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4 col-sm-4">
                            <div class="form-group">
                                <label class="control-label"><fmt:message key="label.neighborhood"></fmt:message></label><br>
                                <span id="neighborhood"></span>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-4">
                            <div class="form-group">
                                <label class="control-label"><fmt:message key="label.city"></fmt:message></label><br>
                                <span id="city"></span>
                            </div>
                        </div>
                        <div class="col-md-3 col-sm-3">
                            <div class="form-group">
                                <label class="control-label"><fmt:message key="label.state"></fmt:message></label><br>
                                <span id="state"></span>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-2 col-sm-2">
                            <div class="form-group">
                                <label  class="control-label"><fmt:message key="label.zipCode"></fmt:message></label><br>
                                <span id="zipCode"></span>
                            </div>
                        </div>
                        <div class="col-md-2 col-sm-2">
                            <div class="form-group">
                                <label class="control-label"><fmt:message key="label.phone"></fmt:message></label><br>
                                <span id="phone"></span>
                            </div>
                        </div>
                        <div class="col-md-3 col-sm-3">
                            <div class="form-group">
                                <label class="control-label"><fmt:message key="label.secondaryPhone"></fmt:message></label><br>
                                <span id="secondaryPhone"></span>
                            </div>
                        </div>
                        <div class="col-md-2 col-sm-2">
                            <div class="form-group">
                                <label class="control-label"><fmt:message key="label.cellPhone"></fmt:message></label><br>
                                <span id="cellPhone"></span>
                            </div>
                        </div>
                        <div class="col-md-3 col-sm-3">
                            <div class="form-group">
                                <label class="control-label"><fmt:message key="label.secondaryCellPhone"></fmt:message></label><br>
                                <span id="secondaryCellPhone"></span>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4 col-sm-4">
                            <div class="form-group">
                                <label class="control-label"><fmt:message key="label.email"></fmt:message></label><br>
                                <span id="email"></span>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-4">
                            <div class="form-group">
                                <label class="control-label"><fmt:message key="label.website"></fmt:message></label><br>
                                <span id="website"></span>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-5 col-sm-5">
                            <div class="form-group">
                                <label class="control-label"><fmt:message key="label.contactName"></fmt:message></label><br>
                                <span id="contactName"></span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="button.cancel" /></button>
                </div>
            </div>
        </div>
    </div>
</html:template>
</body>
<html:jsAssets/>
</html>


