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
        <html:template/>
        <div class="page-header">
            <h3><fmt:message key="title.finalization" /></h3>
        </div>
        <form action='<c:url value="/solicitacao/pesquisar/finalizacao"></c:url>' method="post" id="finalizationSolicitationForm">
            <div class="row">
                <div class="col-md-2 col-sm-2 ">
                    <label class="control-label"><fmt:message key="label.code" /></label>
                    <input type="text" class="form-control" name="solicitation.id" value="${solicitation.id}" />
                </div>
                <div class="col-sm-offset-2 col-md-offset-2">
                    <div class="form-group">
                        </br>
                        <button type="submit" class="btn btn-success"><fmt:message key="button.search" /></button>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4 col-sm-4 ">
                    <label class="control-label"><fmt:message key="label.user" /></label></br>
                    <span>${solicitation.user.name}</span>
                </div>
           <div class="col-md-3 col-sm-3 ">
             <label class="control-label"><fmt:message key="label.costCenter" /></label></br>
             <span>${solicitation.costCenter.description}</span>
           </div>
           <div class="col-md-3 col-sm-3 ">
             <label class="control-label"><fmt:message key="label.type" /></label></br>
             <span>${solicitation.type.description}</span>
           </div>
           <div class="col-md-2 col-sm-2 ">
             <label class="control-label"><fmt:message key="label.initialDate" /></label></br>
             <fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${solicitation.initialDate}" var="initialDate"/>
             <span>${initialDate}</span>
           </div>
         </div>
       </form>
       <c:if test="${! empty messageNotSolicitation}">
         <div class="row">
               <div class="col-md-12 col-sm-12 text-center">
                   <span class="message-error"><fmt:message key="${messageNotSolicitation}"/></span>
               </div>
             </div>
       </c:if>
       <form action="<c:url value="/solicitacao/finalizar"></c:url>" method="post" id="finalizationForm">
           <c:if test="${solicitation.type eq 'Service' or solicitation.type eq 'MaterialService'}">
               <div id="divService">
                   <div class="row">
                       <h4><fmt:message key="title.solicitation.service" /></h4>
                       <hr/>
                   </div>
                   <div class="row">
                       <div class="col-md-10 col-sm-10">
                           <div class="form-group">
                               <label  class="control-label" for="description"><fmt:message key="label.description"/></label>
                               <textarea rows="4" cols="100" class="form-control" id="description" name="solicitationRequest.service.description" readonly >${solicitationRequest.service.description}</textarea>
                           </div>
                       </div>
                       <div class="col-md-2 col-sm-2">
                           <div class="form-group">
                               <div class="checkbox-inline">
                                   <label class="control-label">
                                       <input type="checkbox" id="statusSolicitationRequest"  name="solicitationRequest.status" value="${solicitationRequest.status}"  onclick="actionStatusSolicitationRequest()" <c:if test="${solicitationRequest.status eq 'Delivered'}">checked </c:if> /> <fmt:message key="label.delivered"/>
                                       <input type="hidden" name="solicitationRequest.id" value="${solicitationRequest.id}" />
                                   </label>
                               </div>
                           </div>
                       </div>
                   </div>
               </div>
           </c:if>
           <c:if test="${solicitation.type eq 'Material' or solicitation.type eq 'MaterialService'}">
               <div id="divMaterial">
                   <div class="row">
                       <h4><fmt:message key="title.solicitation.products" /></h4>
                       <hr/>
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
                           </tr>
                           </thead>
                           <tbody>
                           <input type="hidden" id="counter" value="${fn:length(solicitation.solicitationRequests)}">
                               <c:forEach items="${solicitation.solicitationRequests}" var="solicitationRequest" varStatus="i">
                                   <c:set var="quantity" value="${fn:replace(solicitationRequest.quantity, '.', ',')}"/>
                                   <c:if test="${solicitationRequest.service == null}">
                                       <tr>
                                           <td>${solicitationRequest.product.id}</td>
                                           <td>${solicitationRequest.product.description} ${solicitationRequest.product.model} ${solicitationRequest.product.mark}</td>
                                           <td>${quantity}</td>
                                           <td>${solicitationRequest.product.unit.description}</td>
                                           <td>
                                               <input type="checkbox" id="statusSolicitationRequest${i.index}"  name="solicitation.solicitationRequests[${i.index}].status" value="${solicitation.solicitationRequests[i.index].status}" onclick="actionChecked(${i.index})" <c:if test="${solicitation.solicitationRequests[i.index].status eq 'Delivered'}">checked </c:if> />
                                               <input type="hidden" name="solicitation.solicitationRequests[${i.index}].id" value="${solicitationRequest.id}" />
                                           </td>
                                       </tr>
                                   </c:if>
                               </c:forEach>
                           </tbody>
                       </table>
                   </div>
               </div>
           </c:if>
           <br><br>
           <hr/>
           <div class="row">
               <div class="col-sm-offset-10 col-md-offset-10">
                   <div class="form-group">
                       <input type="hidden" name="solicitation.id" value="${solicitation.id}" >
                       <button type="submit" class="btn btn-success"><fmt:message key="button.confirm" /></button>
                       <a href='<c:url value="/home"></c:url>' type="button" class="btn btn-danger"><fmt:message key="button.cancel"/></a>
                   </div>
               </div>
           </div>
       </form>
     </div>
</html:template>
</body>
<html:jsAssets/>
<script src="${pageContext.request.contextPath}/asset/js/custom/finalizeSolicitation.js"></script>
</html>

