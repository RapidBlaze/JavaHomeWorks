<html>

<head>

<title>Task5</title>

</head>

<body>

<%! com.itis.UserBean userBean = new com.itis.UserBean(); %>

<%= userBean.toString() %>

<% userBean.addUsers(); %>

<table border="1">

<% for (com.itis.UserBean.User user : userBean.getUserList()) { %>
<tr>
 <td> <%= user.getFirstname() %> </td>

 <td> <%= user.getLastname() %> </td>

 <td> <%= user.getAge() %> </td>
</tr>
<% } %>
</table>


</body>

</html>