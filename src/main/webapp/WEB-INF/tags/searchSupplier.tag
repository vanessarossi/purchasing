<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="modal fade" id="searchSupplier" tabindex="-1" role="dialog" aria-labelledby="searchSupplier" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"><fmt:message key="title.search.supplier" /></h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-lg-10 col-md-10 col-sm-10">
                        <div class="form-group">
                            <label class="control-label"><fmt:message key="label.search"/> </label>
                            <input type="text" class="form-control" id="text">
                            <p class="help-block"><fmt:message key="help.searchSupplier" /> </p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <table class="table table-hover" id="tableSearchSupplier">
                        <thead>
                        <tr>
                            <th style="width: 3%"><fmt:message key="table.code"/></th>
                            <th style="width: 40%"><fmt:message key="table.name"/></th>
                            <th style="width: 40%"><fmt:message key="table.companyName"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
                <br><br><br>
            </div>
        </div>
    </div>
</div>