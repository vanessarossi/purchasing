<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@tag pageEncoding="UTF-8" %>
<div class="modal fade" id="modalError">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-body">
                <h1>Opss!</h1>
                <div class="alert alert-danger">Ocorreu um erro com a requisição!</div>
                <div class="row">
                    <code id="code">
                    </code>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modalConfirm">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-body">
                <p class="lead text-center">Deseja realmente excluir ?</p>
                <br/>
                <div class="text-center">
                    <button type="button" id="btn-confirm" class="btn btn-success">Sim</button>
                    <button type="button" id="btn-cancel" class="btn btn-danger">Não</button>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modalSuccess">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-body">
                <div class="alert alert-success">Operação realizada com sucesso!</div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modalErrorReception">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-body">
                <p class="lead text-center">Atenção!</p>
                <h6 class="text-center">A quantidade de item entegue é superior a quantidade do pedido.</h6>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modalErrorQuantityLess">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-body">
                <p class="lead text-center">Atenção!</p>
                <h6 class="text-center">A quantidade de item entegue é inferior a quantidade do pedido.</h6>
            </div>
        </div>
    </div>
</div>

