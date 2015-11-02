<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	
	
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>



<form action="/connect/facebook" method="post" name="form">
	<input type="hidden" name="scope" value="email,public_profile,user_friends" >
	<button onclick="form.submit();">Login With Facebook</button>

</form>

</body>
</html>
