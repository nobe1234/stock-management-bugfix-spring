<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/common.jsp"  %>
<body>
<div class="container">
	<h3>メンバー登録画面</h3>
	<div class="span8">
		<div class="row">
		<form:form modelAttribute="memberForm" action="${pageContext.request.contextPath}/member/create">
			<table class="table table-striped">
			  <tr>
			    <th>
			     	 氏名
			    </th>
			    <td>
			        <form:errors path="name" cssStyle="color:red" element="div" ></form:errors>
			    	<form:input path="name"  placeholder="Name"/>
			    </td>
			  </tr>
			  <tr>
			    <th>
			      	メールアドレス
			    </th>
			    <td>
<%-- 			    <c:if test="${validateMailAddressMessege != null}">
			    <c:out  value="${validateMailAddressMessege}" ></c:out><br>
			    </c:if>
 --%>			        <form:errors path="mailAddress" cssStyle="color:red" element="div" ></form:errors>
			    	<form:input path="mailAddress" placeholder="Email"/>
			    </td>
			  </tr>
			  <tr>
			    <th>
			     	 パスワード
			    </th>
			    <td>
			   <%--  <c:if test="${validatePasswordMessege != null}">
			    <c:out  value="${validatePasswordMessege}" ></c:out><br>
			    </c:if> --%>
			        <form:errors path="password" cssStyle="color:red" element="div" ></form:errors>
			    	<form:password path="password" placeholder="Password"/>
			    </td>
			  </tr>
			  <tr>
			    <th>
			     	 確認用パスワード
			    </th>
			    <td>
			    <!-- 確認 -->
			        <form:errors path="retypePassword" cssStyle="color:red" element="div" ></form:errors>
			    	<form:password path="retypePassword" placeholder="retypePassword"/>
			    </td>
			  </tr>
			  <tr>
			  	<td></td>
			    <td>
					<input class="btn" type="submit" value="登録">
			    </td>
			  </tr>
			</table>
		  </form:form>
		</div>
	</div>
</div>
</body>
</html>