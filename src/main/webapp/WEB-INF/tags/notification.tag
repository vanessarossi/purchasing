<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag pageEncoding="UTF-8" %>
<div id="modalError" class="reveal-modal small" data-reveal>
    <h1>Opss!</h1>
    <div class="info alert-box">Ocorreu um erro com a requisição!</div>
    <div class="row">
        <code id="code">
        </code>
    </div>
    <a class="close-reveal-modal">&#215;</a>
</div>
<div id="modalConfirm" class="reveal-modal small" data-reveal>
    <p class="lead text-center">Deseja realmente excluir ?</p>
    <br/>
    <div class="text-center">
        <button type="button" id="btn-confirm" class="tiny success">Sim</button>
        <button type="button" id="btn-cancel" class="tiny alert">Não</button>
    </div>
</div>
<div id="modalSuccess" class="reveal-modal small" data-reveal>
    <div class="alert-box success large">
        Item removido!
    </div>
</div>
