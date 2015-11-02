<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<html>
<head><title>Facebook Feed Data</title></head>
<body>
 <h3>Hello, <span>${facebookProfile.name}</span>!</h3>
 <h4>Here is your home feed:</h4>
 <c:forEach var="post" items="${feed}"  style="width:400px;">
  <b ${post.from.name}>from</b> wrote:
  <p ${post.message}>message text</p>
  <c:if test="${post.picture}">
  	<img src="${post.picture}" style="width:100px;height:100px;"/>
  </c:if>
  
  <hr/>
 </c:forEach>
</body>
</html>