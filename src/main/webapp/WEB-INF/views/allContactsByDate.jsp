<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sbt-kuzmin-da
  Date: 29.09.2017
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Заказы на - ${orderDate}</title>
</head>
<body>
    <table style="display: block; margin: auto; width: 60%; text-align: center">
        <h1 style="text-align: center">Заказы на - ${orderDate}</h1>
        <div style="margin: auto; text-align: center"><a href="/buuzes/">Перейти на главную</a></div>
        <tr style="border: double">
            <td><p>Имя бузиста<p/></td>
            <td><p>Кол-во чуда<p/></td>
            <td><p>Цена за кайф<p/></td>
            <td><p>Дата заказа<p/></td>
        </tr>
        <c:if test="${not empty contacts}">
            <c:forEach var="contact" items="${contacts}">
                <tr style="height: 42px">
                    <td><p>${contact.name}<p/></td>
                    <td><p>${contact.count}<p/></td>
                    <td><p>${contact.price}<p/></td>
                    <td><p>${orderDate}<p/></td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
</body>
</html>
