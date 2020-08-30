<%--
  Created by IntelliJ IDEA.
  User: phoenix
  Date: 5/5/2019
  Time: 2:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <script type="text/javascript">
        window.onload=function (ev) {
          document.getElementById("myul").getElementsByTagName("li")[0].innerHTML="程序员";
          //document.getElementById("myul").getElementsByTagName("li")[0].innerText="程序员"
        }
    </script>
  </head>
  <body>
    <ul id="myul">
      <li>first1</li>
      <li>first2</li>
      <li>first3</li>
    </ul>
  </body>
</html>
