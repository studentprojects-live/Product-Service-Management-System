successfully logged out
<%

application.removeAttribute(session.getAttribute("uid").toString());


%>Successfully logged out
<a href="./Index.htm">Again Login</a>
