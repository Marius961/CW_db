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
<body>
<form action="<c:url value="/home"/>">
    <button class="btn btn-info btn-1">Назад</button>
</form>
    <div>
        <div class="container-div">
            <form:form modelAttribute="smartPhone" method="post" action="/create-smartphone">
                <h1>Основне</h1>
                <form:label path="model">Модель:</form:label>
                <form:input path="model" cssClass="form-control" type="text"/>
                <form:label path="vendor.name">Виробник:</form:label>
                <form:input path="vendor.name" cssClass="form-control" type="text"/>
                <form:label path="characteristics.batteryVolume">Ємкість батареї:</form:label>
                <form:input path="characteristics.batteryVolume" cssClass="form-control" type="text"/>
                <h1>Камера</h1>
                <form:label path="characteristics.camera.numOfPixels">Кількість мегапікселів:</form:label>
                <form:input path="characteristics.camera.numOfPixels" cssClass="form-control" type="text"/>
                <form:label path="characteristics.camera.resolution">Роздільна здатність камери:</form:label>
                <form:input path="characteristics.camera.resolution" cssClass="form-control" type="text"/>
                <h1>Процесор</h1>
                <form:label path="characteristics.processor.model">Модель:</form:label>
                <form:input path="characteristics.processor.model" cssClass="form-control" type="text"/>
                <form:label path="characteristics.processor.cores">Кількість ядер:</form:label>
                <form:input path="characteristics.processor.cores" cssClass="form-control" type="text"/>
                <form:label path="characteristics.processor.frequency">Частота ядер:</form:label>
                <form:input path="characteristics.processor.frequency" cssClass="form-control" type="text"/>
                <h1>Дисплей</h1>
                <form:label path="characteristics.display.model">Модель:</form:label>
                <form:input path="characteristics.display.model" cssClass="form-control" type="text"/>
                <form:label path="characteristics.display.resolution">Розширення:</form:label>
                <form:input path="characteristics.display.resolution" cssClass="form-control" type="text"/>
                <form:label path="characteristics.display.size">Розмір:</form:label>
                <form:input path="characteristics.display.size" cssClass="form-control" type="text"/>
                <form:label path="characteristics.display.technology">Технологія:</form:label>
                <form:input path="characteristics.display.technology" cssClass="form-control" type="text"/>
                <button type="submit">Додати</button>
            </form:form>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
