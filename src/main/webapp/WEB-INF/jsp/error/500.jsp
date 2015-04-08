<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="title.page.error.500"/> - <fmt:message key="title.purchasing"/></title>
    <html:head/>
</head>
<body style="text-align: center">
<html:template>
    <div class="row">
        <div>
            <h2><fmt:message key="title.error.500"/></h2>

            <h2>
                <small><fmt:message key="message.error.500"/></small>
            </h2>
        </div>
    </div>
    <div class="row">
        <div>
            <img src="${pageContext.request.contextPath}/asset/images/error/rocket.png" alt=""/>
        </div>
    </div>
</html:template>
<html:jsAssets/>
</body>
</html>
