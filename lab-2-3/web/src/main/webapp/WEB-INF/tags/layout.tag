<%@tag description="Layout" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>

<html>
  <head>
    <title>Students DB</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
  </head>
  <body class="container">
    <div id="pageheader">
      <jsp:invoke fragment="header"/>
    </div>
    <div id="body">
      <jsp:doBody/>
    </div>
    <div id="pagefooter" style="position: fixed; bottom: 0; left:0; right:0; background: white; text-align: center;">
      <jsp:invoke fragment="footer"/>
    </div>
  </body>
</html>