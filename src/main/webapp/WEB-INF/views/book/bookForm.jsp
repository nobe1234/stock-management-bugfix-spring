<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>書籍登録フォーム</title>
</head>
<body>
	<div class="container">
		<h3>書籍登録フォーム</h3>
		<div class="span8">
			<div class="row">
				<form:form modelAttribute="bookForm" enctype="multipart/form-data"
					action="${pageContext.request.contextPath}/book/upload">
					<table class="table table-striped">
						<%-- 	<tr>
							<td><form:errors path="id" cssStyle="color:red"
									element="div"></form:errors> ID<form:input path="id" /><br>
							</td>
						<tr>
					 --%>
						<tr>
							<td><form:errors path="name" cssStyle="color:red"
									element="div"></form:errors> 名前<form:input path="name" /><br>
							</td>
						<tr>
							<td><form:errors path="author" cssStyle="color:red"
									element="div"></form:errors> 著者<form:input path="author" /><br>
							</td>
						</tr>
						<tr>
							<td><form:errors path="publisher" cssStyle="color:red"
									element="div"></form:errors> 出版社<form:input path="publisher" /><br>
							</td>
						</tr>
						<tr>
							<td><form:errors path="price" cssStyle="color:red"
									element="div"></form:errors> 価格<form:input path="price" /><br>
							</td>
						</tr>

						<tr>
							<td><form:errors path="isbncode" cssStyle="color:red"
									element="div"></form:errors> ISBNコード<form:input path="isbncode" /><br>
							</td>
						</tr>
						<tr>
							<td><form:errors path="saledate" cssStyle="color:red"
									element="div"></form:errors> 発売日<form:input path="saledate" /><br>
							</td>
						</tr>
						<tr>
							<td><form:errors path="explanation" cssStyle="color:red"
									element="div"></form:errors> 説明 <form:input path="explanation" /><br>
							</td>
						</tr>
						<tr>
							<td>商品画像登録：<input type="file" name="image"><br>
							</td>
						</tr>
						<tr>
							<td><form:errors path="stock" cssStyle="color:red"
									element="div"></form:errors> 在庫数<form:input path="stock" /><br>
							</td>
						</tr>
						<tr>
							<td><input type="submit" value="登録"></td>
						</tr>
					</table>
				</form:form>
			</div>
		</div>
	</div>

</body>
</html>