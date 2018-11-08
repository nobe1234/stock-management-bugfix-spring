<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>書籍登録フォーム</title>
</head>
<body>

<h1>書籍登録フォーム</h1>
<form:form modelAttribute="bookForm" action="${pageContext.request.contextPath}/book/update" >

<form:errors path="name" cssStyle="color:red" element="div"></form:errors>
名前<form:input path="name"/><br>

<form:errors path="author" cssStyle="color:red" element="div"></form:errors>
著者<form:input path="author"/><br>

<form:errors path="publisher" cssStyle="color:red" element="div"></form:errors>
出版社<form:input path="publisher"/><br>

<form:errors path="price" cssStyle="color:red" element="div"></form:errors>
価格<form:input path="price"/><br>

<form:errors path="isbncode" cssStyle="color:red" element="div"></form:errors>
ISBNコード<form:input path="isbncode"/><br>

<form:errors path="saledate" cssStyle="color:red" element="div"></form:errors>
発売日<form:input path="saledate"/><br>

<form:errors path="explanation" cssStyle="color:red" element="div"></form:errors>
説明	<form:input path="explanation"/><br>

<%-- <form:errors path="image" cssStyle="color:red" element="div"></form:errors>
画像<form:input path="image"/><br> --%>
<!-- <img alt="" src=""> -->

<form:errors path="stock" cssStyle="color:red" element="div"></form:errors>
在庫数<form:input path="stock"/><br>

<input type="submit" value="登録">

</form:form>


</body>
</html>