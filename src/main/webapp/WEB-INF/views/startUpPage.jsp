<%--
  Created by IntelliJ IDEA.
  User: sbt-kuzmin-da
  Date: 28.09.2017
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <spring:url value="/resources/css/style.css" var="styleCSS"/>
    <%--<link href="${basePath}/web-resources/css/style.css" rel="stylesheet"/>--%>
    <link href="${styleCSS}" rel="stylesheet"/>
</head>
<body>
<h1>Хочешь буузы?</h1>

<div style="float: left">
    <form:form method="POST" action="/buuzes/order" modelAttribute="contact" >
        <h2>Заказать бузики!</h2>
        <table>
            <tr>
                <td><form:label path="name">Имя бузмэна</form:label></td>
                <td><form:input path="name"/></td>
            </tr>
            <tr>
                <td><form:label path="count">Кол-во буз</form:label></td>
                <%--<td><form:input path="count" pattern="[0-9]"/></td>--%>
                <td><input name="count" pattern="[1-9][0-9]{0,3}" required/></td>
            </tr>
            <tr>
                <td><input id="order" type="submit" value="Заказать"/></td>
            </tr>
        </table>
    </form:form>

</div>

<div style="float: left; margin-left: 100px">
    <form method="post" action="/buuzes/date" class="orderForm">
        <h2>Посмотреть заказы на дату</h2>
        <table>
            <tr>
                <td><label>Date:</label></td>
                <td><input type="date" name="date"></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Посмотреть на дату"></td>
            </tr>
        </table>
    </form>
</div>

</body>
<script type="javascript">
    window.onload = function () {

        alert("11");
    }
</script>
</html>
