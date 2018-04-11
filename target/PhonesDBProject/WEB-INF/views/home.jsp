<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="true" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"  crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/main.css">
</head>
<body>
<form action="<c:url value="/smartPhone"/>">
    <button class="btn btn-info btn-1">Додати</button>
</form>
<c:forEach var="smartphone" items="${phones}">
    <div class="container-div">
        <h1>${smartphone.vendor.name} ${smartphone.model}</h1>
        <table>
            <tr>
                <th>Основне:</th>
            </tr>
            <tr>
                <td class="c-table">Виробник:</td>
                <td class="c-table">${smartphone.vendor.name}</td>
            </tr>
            <tr>
                <td class="c-table">Модель:</td>
                <td class="c-table">${smartphone.model}</td>
            </tr>
            <tr>
                <td class="c-table">Ємкість акумулятора:</td>
                <td class="c-table">${smartphone.characteristics.batteryVolume}</td>
            </tr>
            <tr>
                <th>Камера:</th>
            </tr>
            <tr>
                <td class="c-table">Кількість мегапікселів:</td>
                <td class="c-table">${smartphone.characteristics.camera.numOfPixels}</td>
            </tr>
            <tr>
                <td class="c-table">Роздільна здатність камери:</td>
                <td class="c-table">${smartphone.characteristics.camera.resolution}</td>
            </tr>
            <tr>
                <th>Процесор:</th>
            </tr>
            <tr>
                <td class="c-table">Модель:</td>
                <td class="c-table">${smartphone.characteristics.processor.model}</td>
            </tr>
            <tr>
                <td class="c-table">Кількість ядер:</td>
                <td class="c-table">${smartphone.characteristics.processor.cores}</td>
            </tr>
            <tr>
                <td class="c-table">Частота ядер:</td>
                <td class="c-table">${smartphone.characteristics.processor.frequency}</td>
            </tr>
            <tr>
                <th>Дисплей:</th>
            </tr>
            <tr>
                <td class="c-table">Модель:</td>
                <td class="c-table">${smartphone.characteristics.display.model}</td>
            </tr>
            <tr>
                <td class="c-table">Розширення:</td>
                <td class="c-table">${smartphone.characteristics.display.resolution}</td>
            </tr>
            <tr>
                <td class="c-table">Розмір:</td>
                <td class="c-table">${smartphone.characteristics.display.size}</td>
            </tr>
            <tr>
                <td class="c-table">Технологія:</td>
                <td class="c-table">${smartphone.characteristics.display.technology}</td>
            </tr>
        </table>
        <div class="div-right">
            <form:form modelAttribute="smartphone" method="post" action="/update" cssClass="inline-btn">
                <form:input path="id" value="${smartphone.id}" type="hidden"/>
                <form:input path="characteristicsId" value="${smartphone.characteristicsId}" type="hidden"/>
                <form:input path="vendorId" value="${smartphone.vendorId}" type="hidden"/>
                <form:input path="model" value="${smartphone.model}" type="hidden"/>
                <button class="btn btn-info btn-2">Змінити</button>
            </form:form>

            <form:form modelAttribute="deletedPhone" method="post" action="/delete" cssClass="inline-btn">
                <form:hidden path="smartphoneId" value="${smartphone.id}"/>
                <button type="submit" class="btn btn-danger btn-2">Видалити</button>
            </form:form>
        </div>
    </div>
</c:forEach>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
