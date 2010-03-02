
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="home"/></title>
    </head>
    <body>
      <h2><g:message code="home"/></h2>
      <br />
      <div id="home_panel">
        <g:set var="num" value="${1}" />
        <g:while test="${num < 10 }">
        <div class="home_block yui-skin-sam">
            <gui:expandablePanel title="${message(code:'offers.lasts')}" expanded="true" bounce="false">
            ${num}
            </gui:expandablePanel>
        </div>
        <% num++ %>
        </g:while>
      </div>

    </body>
</html>
