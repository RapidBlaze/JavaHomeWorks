<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>

<title>Task6</title>

</head>

<body>

<jsp:useBean id="userBean" class="com.itis.UserBean" />

<%! com.itis.UserBean userBean2 = new com.itis.UserBean(); %>

<% userBean2.addUsers(); %>


<table border="1">
<%! com.itis.UserBean.User user; %>
<c:forEach var="user" items="${userBean.getUserList()}">

<tr>

 <td><c:out value="${user.getFirstname()}"/></td>

 <td><c:out value="${user.getLastname()}"/></td>

 <td><c:out value="${user.getAge()}"/></td>

</tr>

</c:forEach>
</table>

</body>

</html>