<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@tag pageEncoding="UTF-8" %>
<!--[if lt IE 9]><p class="browsehappy">Você esta usando um navegador <strong>desatualizado</strong> . Por favor <a href="http://browsehappy.com/">atualize seu navegador</a> para melhorar sua usabilidade.</p><![endif]-->
<nav class="navbar navbar-default navbar-static-top">
  <div class="container-fluid">
    <div class="navbar-header">
    </div>
    <div id="navbar" class="navbar-collapse collapse">
        <ul class="nav navbar-nav">
            <li><a href="<c:url value="/home" ></c:url>"><fmt:message key="menu.home"/></a></li>
            <c:if test="${userLogged.role.id == 1 || userLogged.role.id == 4 || userLogged.role.id == 5 || userLogged.role.id == 7}">
                <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><fmt:message key="menu.cadastre"/> <span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                <c:if test="${userLogged.role.id != 4}">
                    <li><a href="<c:url value="/categoria" ></c:url>"><fmt:message key="submenu.category"/></a></li>
                    <li><a href="<c:url value="/centroCusto" ></c:url>"><fmt:message key="submenu.costCenter"/></a></li>
                </c:if>
                    <li><a href="<c:url value="/contrato" ></c:url>"><fmt:message key="submenu.contract"/></a></li>
                <c:if test="${userLogged.role.id != 4}">
                    <li><a href="<c:url value="/formaPagamento" ></c:url>"><fmt:message key="submenu.formPayment"/></a></li>
                </c:if>
                    <li><a href="<c:url value="/fornecedor" ></c:url>"><fmt:message key="submenu.supplier"/></a></li>
                <c:if test="${userLogged.role.id != 4}">
                    <li><a href="<c:url value="/produto" ></c:url>"><fmt:message key="submenu.product"/></a></li>
                    <li><a href="<c:url value="/tipoServico" ></c:url>"><fmt:message key="submenu.typeService"/></a></li>
                    <li><a href="<c:url value="/unidade" ></c:url>"><fmt:message key="submenu.unit"/></a></li>
                 </c:if>
                 <c:if test="${userLogged.role.id == 1}">
                     <li class="divider"></li>
                     <li class="dropdown-header"><fmt:message key="submenu.headerConfiguration"/></li>
                     <li><a href="<c:url value="/usuario" ></c:url>"><fmt:message key="submenu.user"/></a></li>
                 </c:if>
                </ul>
            </li>
            </c:if>

            <c:if test="${userLogged.role.id == 1 || userLogged.role.id == 4 || userLogged.role.id == 5 || userLogged.role.id == 6 || userLogged.role.id == 7 || userLogged.role.id == 8|| userLogged.role.id == 9}">
                <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><fmt:message key="menu.solicitation"/> <span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                    <li><a href="<c:url value="/solicitacao/formulario" ></c:url>"><fmt:message key="submenu.new"/></a></li>
                    <li><a href="<c:url value="/solicitacao/listar/individual" ></c:url>"><fmt:message key="submenu.list"/></a></li>
                    <c:if test="${userLogged.role.id == 1 || userLogged.role.id == 4 || userLogged.role.id == 5 || userLogged.role.id == 7}">
                        <li><a href="<c:url value="/solicitacao/listar" ></c:url>"><fmt:message key="submenu.generalList"/></a></li>
                    </c:if>
                    <c:if test="${userLogged.role.id == 1 || userLogged.role.id == 5 || userLogged.role.id == 6 || userLogged.role.id == 7}">
                        <li><a href="<c:url value="/solicitacao/listar/pendente" ></c:url>"><fmt:message key="submenu.missingList"/></a></li>
                    </c:if>
                    <c:if test="${userLogged.role.id == 1 || userLogged.role.id == 5 || userLogged.role.id == 7}">
                        <li class="divider"></li>
                        <li class="dropdown-header"><fmt:message key="submenu.headerFinalizeInternal"/></li>
                        <li><a href="<c:url value="/solicitacao/formulario/finalizacao" ></c:url>"><fmt:message key="submenu.finalize"/></a></li>
                    </c:if>
                </ul>
            </li>
            </c:if>

            <c:if test="${userLogged.role.id == 1 || userLogged.role.id == 5 || userLogged.role.id == 7}">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><fmt:message key="menu.quotation"/> <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="<c:url value="/cotacao/formulario" ></c:url>"><fmt:message key="submenu.new"/></a></li>
                        <li><a href="<c:url value="/cotacao" ></c:url>"><fmt:message key="submenu.list"/></a></li>
                    </ul>
                </li>
            </c:if>

            <c:if test="${userLogged.role.id == 1 || userLogged.role.id == 2 || userLogged.role.id == 3 || userLogged.role.id == 4 || userLogged.role.id == 5 || userLogged.role.id == 6|| userLogged.role.id == 7 || userLogged.role.id == 8}">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><fmt:message key="menu.purchaseOrder"/> <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                    <c:if test="${userLogged.role.id == 1 || userLogged.role.id == 2 || userLogged.role.id == 3 || userLogged.role.id == 4 || userLogged.role.id == 5 || userLogged.role.id == 7}">
                            <li><a href="<c:url value="/ordemCompra/listagem" ></c:url>"><fmt:message key="submenu.list"/></a></li>
                            <li><a href="<c:url value="/ordemCompra/listagem/pendencia" ></c:url>"><fmt:message key="submenu.missingList"/></a></li>
                    </c:if>
                    <c:if test="${userLogged.role.id == 1 || userLogged.role.id == 5 || userLogged.role.id == 6|| userLogged.role.id == 7 || userLogged.role.id == 8}">
                            <li><a href="<c:url value="/ordemCompra/formulario/recepcao" ></c:url>"><fmt:message key="submenu.reception"/></a></li>
                    </c:if>
                    <c:if test="${userLogged.role.id == 1 || userLogged.role.id == 5 || userLogged.role.id == 7}">
                        <li class="divider"></li>
                            <li class="dropdown-header"><fmt:message key="submenu.headerPendenceFinalize"/></li>
                            <li><a href="<c:url value="/ordemCompra/listagem/conferida" ></c:url>"><fmt:message key="submenu.searchConfered"/></a></li>
                    </c:if>
                    <c:if test="${userLogged.role.id == 1 || userLogged.role.id == 5 || userLogged.role.id == 6|| userLogged.role.id == 7 || userLogged.role.id == 8}">
                            <li class="divider"></li>
                            <li class="dropdown-header"><fmt:message key="submenu.headerSearch"/></li>
                            <li><a href="<c:url value="/ordemCompra/pesquisar" ></c:url>"><fmt:message key="submenu.searchBySupplier"/></a></li>
                    </c:if>
                    </ul>
                </li>
            </c:if>

            <c:if test="${userLogged.role.id == 1 || userLogged.role.id == 2 || userLogged.role.id == 3 || userLogged.role.id == 4 || userLogged.role.id == 5 || userLogged.role.id == 6|| userLogged.role.id == 7 || userLogged.role.id == 8|| userLogged.role.id == 9}">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><fmt:message key="menu.indicators"/> <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#"></a></li>
                        <li><a href="#"></a></li>
                        <li><a href="#"></a></li>
                    </ul>
                </li>
            </c:if>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">${userLogged.username} <span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="<c:url value="/login/meu/perfil/${userLogged.id}" ></c:url>"><span class="fa fa-user"></span> <fmt:message key="submenu.profile"/></a></li>
                <li class="divider"></li>
                <li><a href="<c:url value="/login/logoff" ></c:url>"><span class="fa fa-sign-out"></span> <fmt:message key="submenu.singOut"/></a></li>
              </ul>
            </li>
        </ul>
    </div><!--/.nav-collapse -->
  </div>
</nav>
<section class="container">
    <jsp:doBody/>
</section>
