<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="true" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Головна</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"  crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/main.css">
</head>
<body>
<nav class="navbar navbar-light bg-light justify-content-between" style="height: 10%">
    <a class="navb" style="font-size: 4vh" href="<c:url value="/"/>">Смарфони</a>
    <form:form action="/search" method="post" modelAttribute="searchedPhone" cssClass="form-inline" cssStyle="margin-top: 2%">
        <form:input path="smartPhoneModel" type="search" class="form-control mr-sm-2" placeholder="Пошук" aria-label="Пошук"/>
        <br>
        <form:button class="btn btn-outline-success my-2 my-sm-0" type="submit">Знайти</form:button>
    </form:form>
</nav>
<main>
    <form action="<c:url value="/smartPhone"/>">
        <button class="btn btn-info btn-1">Додати</button>
    </form>
    <div class="info-1 form-group">
        <c:forEach items="${stat}" var="entry">
            <a href="<c:url value="/search/${entry.key}"/>">${entry.key}(${entry.value})</a>
            <br>
        </c:forEach>
        <br>
        <a href="<c:url value="/"/>" style="color: black; padding: 1%; background: rgba(128,128,119,0.44); display: inline-block">Скинути результати</a>
    </div>
    <c:if test="${empty phones}">
        <div class="container-div">
            <h1>Список пустий</h1>
        </div>
    </c:if>
    <c:if test="${not empty phones}">
        <c:forEach var="smartphone" items="${phones}">
            <div class="container-div" >
                <div style="display: table">
                    <h1 class="phone-title" onclick="showHide(${smartphone.id})">${smartphone.vendor.name} ${smartphone.model} (${smartphone.characteristics.display.size}″ / ${smartphone.characteristics.batteryVolume}mAh / ${smartphone.characteristics.processor.cores}x${smartphone.characteristics.processor.frequency} гГц)</h1>
                    <form:form modelAttribute="smartphone" method="post" action="/update" cssClass="inline-btn">
                        <form:input path="id" value="${smartphone.id}" type="hidden"/>
                        <form:input path="characteristicsId" value="${smartphone.characteristicsId}" type="hidden"/>
                        <form:input path="vendorId" value="${smartphone.vendorId}" type="hidden"/>
                        <form:input path="model" value="${smartphone.model}" type="hidden"/>
                        <button class="btn-edit">Змінити</button>
                    </form:form>

                    <form:form modelAttribute="deletedPhone" method="post" action="/delete" cssClass="inline-btn">
                        <form:hidden path="smartphoneId" value="${smartphone.id}"/>
                        <button type="submit" class="btn-delete">Видалити</button>
                    </form:form>
                </div>


                <div class="hidden" id="smartphone${smartphone.id}">
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
                </div>
            </div>
        </c:forEach>
    </c:if>
</main>


<script rel="script" src="<%=request.getContextPath()%>/resources/js/main.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
