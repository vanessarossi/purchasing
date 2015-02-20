$(document).ready(function () {
    $(document).on("click", "a.dlink", function (b) {
        b.preventDefault();
        var a = $(this);
        var c = $(this).attr("href") + $(this).attr("data-ref");
        $("#modalConfirm").foundation("reveal", "open");
        $(document).on("click", "button#btn-cancel", function () {
            $("#modalConfirm").foundation("reveal", "close")
        });
        $(document).on("click", "button#btn-confirm", function () {
            $("#modalConfirm").foundation("reveal", "close");
            $.ajax({
                url: c, type: "DELETE", async: true, beforeSend: function (d) {
                    d.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"))
                }, success: function () {
                    $("#modalSuccess").foundation("reveal", "open");
                    setTimeout(function () {
                        var d = a.closest("tr");
                        var f = d.closest("td");
                        d.fadeOut("slow");
                        $("#modalSuccess").foundation("reveal", "close")
                    }, 1400)
                }, error: function (f, d, g) {
                    $("code#code").text("");
                    $("#modalError").foundation("reveal", "open")
                }
            })
        })
    })
});
