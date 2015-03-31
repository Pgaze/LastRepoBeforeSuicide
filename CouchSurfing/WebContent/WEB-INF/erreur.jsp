<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="entete.jsp" %>
<%@ include file="menu.jsp" %>	
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Ooups .. !!</title>
</head>


<body style="background-image:url(/CouchSurfing/ServletBackground)">
	<div id="container" class="content-accueil">
		<p id="titre_erreur"> Ooooups... !!</p>
		<div id="containerErreur">
			 Il y a une erreur : <p style="font-weight: bold;">String</p>
		</div>
		<p>
			Retour à <a href="accueil">l'accueil.</a>
		</p>
	</div>
</body>
</html>