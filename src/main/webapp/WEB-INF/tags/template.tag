<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@tag pageEncoding="UTF-8" %>
<!--[if lt IE 9]><p class="browsehappy">Você esta usando um navegador <strong>desatualizado</strong> . Por favor <a href="http://browsehappy.com/">atualize seu navegador</a> para melhorar sua usabilidade.</p><![endif]-->
<nav class="navbar navbar-default navbar-static-top">
  <div class="container-fluid">
      <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="<c:url value="/home" ></c:url>"><fmt:message key="menu.home"/></a>
      </div>
      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
            <c:if test="${rules[EighthLevelAccessRule].isAllowed()}">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><fmt:message key="menu.cadastre"/> <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="<c:url value="/categoria" ></c:url>"><fmt:message key="submenu.category"/></a></li>
                        <li><a href="<c:url value="/centroCusto" ></c:url>"><fmt:message key="submenu.costCenter"/></a></li>
                        <li><a href="<c:url value="/contrato" ></c:url>"><fmt:message key="submenu.contract"/></a></li>
                        <li><a href="<c:url value="/formaPagamento" ></c:url>"><fmt:message key="submenu.formPayment"/></a></li>
                        <li><a href="<c:url value="/fornecedor" ></c:url>"><fmt:message key="submenu.supplier"/></a></li>
                        <li><a href="<c:url value="/produto" ></c:url>"><fmt:message key="submenu.product"/></a></li>
                        <li><a href="<c:url value="/tipoServico" ></c:url>"><fmt:message key="submenu.typeService"/></a></li>
                        <li><a href="<c:url value="/unidade" ></c:url>"><fmt:message key="submenu.unit"/></a></li>
                        <c:if test="${rules[TenthLevelAccessRule].isAllowed()}">
                            <li class="divider"></li>
                            <li class="dropdown-header"><fmt:message key="submenu.headerConfiguration"/></li>
                            <li><a href="<c:url value="/usuario" ></c:url>"><fmt:message key="submenu.user"/></a></li>
                        </c:if>
                    </ul>
                </li>
            </c:if>
            <c:if test="${rules[FirstLevelAccessRule].isAllowed()}">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><fmt:message key="menu.solicitation"/> <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="<c:url value="/solicitacao/formulario" ></c:url>"><fmt:message key="submenu.new"/></a></li>
                        <li><a href="<c:url value="/solicitacao/listar/individual" ></c:url>"><fmt:message key="submenu.list"/></a></li>
                        <c:if test="${rules[FifthLevelAccessRule].isAllowed()}">
                            <li><a href="<c:url value="/solicitacao/listar" ></c:url>"><fmt:message key="submenu.generalList"/></a></li>
                        </c:if>
                        <c:if test="${rules[SeventhLevelAccessRule].isAllowed()}">
                            <li><a href="<c:url value="/solicitacao/listar/pendente" ></c:url>"><fmt:message key="submenu.missingList"/></a></li>
                        </c:if>
                        <c:if test="${rules[EighthLevelAccessRule].isAllowed()}">
                            <li class="divider"></li>
                            <li class="dropdown-header"><fmt:message key="submenu.headerFinalizeInternal"/></li>
                            <li><a href="<c:url value="/solicitacao/formulario/finalizacao" ></c:url>"><fmt:message key="submenu.finalize"/></a></li>
                        </c:if>
                    </ul>
                </li>
            </c:if>
            <c:if test="${rules[EighthLevelAccessRule].isAllowed()}">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><fmt:message key="menu.quotation"/> <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="<c:url value="/cotacao/formulario" ></c:url>"><fmt:message key="submenu.new"/></a></li>
                        <li><a href="<c:url value="/cotacao" ></c:url>"><fmt:message key="submenu.list"/></a></li>
                    </ul>
                </li>
            </c:if>
            <c:if test="${rules[FirstLevelAccessRule].isAllowed()}">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><fmt:message key="menu.purchaseOrder"/> <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <c:if test="${rules[ThirdLevelAccessRule].isAllowed()}">
                            <li><a href="<c:url value="/ordemCompra/listagem" ></c:url>"><fmt:message key="submenu.list"/></a></li>
                        </c:if>
                        <c:if test="${rules[FourthLevelAccessRule].isAllowed()}">
                            <li><a href="<c:url value="/ordemCompra/listagem/pendencia" ></c:url>"><fmt:message key="submenu.missingList"/></a></li>
                        </c:if>
                        <c:if test="${rules[SixthLevelAccessRule].isAllowed()}">
                            <li><a href="<c:url value="/ordemCompra/formulario/recepcao" ></c:url>"><fmt:message key="submenu.reception"/></a></li>
                        </c:if>
                        <c:if test="${rules[EighthLevelAccessRule].isAllowed()}">
                            <li class="divider"></li>
                            <li class="dropdown-header"><fmt:message key="submenu.headerPendenceFinalize"/></li>
                            <li><a href="<c:url value="/ordemCompra/listagem/conferida" ></c:url>"><fmt:message key="submenu.searchConfered"/></a></li>
                        </c:if>
                        <c:if test="${rules[SixthLevelAccessRule].isAllowed()}">
                            <li class="divider"></li>
                            <li class="dropdown-header"><fmt:message key="submenu.headerSearch"/></li>
                            <li><a href="<c:url value="/ordemCompra/pesquisar" ></c:url>"><fmt:message key="submenu.searchBySupplier"/></a></li>
                        </c:if>
                    </ul>
                </li>
            </c:if>
            <c:if test="${rules[SecondLevelAccessRule].isAllowed()}">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><fmt:message key="menu.indicators"/> <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="<c:url value="/relatorio" ></c:url>"><fmt:message key="submenu.management"/>/<fmt:message key="submenu.operational"/></a></li>
                        <c:if test="${rules[NinthLevelAccessRule].isAllowed()}">
                            <li><a href="<c:url value="/relatorio/financeiro" ></c:url>"><fmt:message key="submenu.financial"/></a></li>
                        </c:if>
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
<section class="container-fluid">
    <jsp:doBody/>
</section>
