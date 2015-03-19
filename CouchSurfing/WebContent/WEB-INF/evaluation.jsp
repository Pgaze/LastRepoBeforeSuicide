
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Evaluation</title>
</head>
<body style="background-image:url(${pageContext.request.contextPath}/ServletBackground)">
	<%@ include file="entete.jsp" %>
	<%@ include file="menu.jsp" %>
	
	<div id="container">
		<div id="contentEvaluation">
			
			<div id="containerIntitulePage">
				<div id="imgPersonneNotee"></div>
				<div id="intitulePage">
					<p id="intitule">PATRICK vous a hebergé, notée le pour aider les futurs utilisateurs à choisir !</p>
				</div>
			</div>
			<form id="form_evaluation" method="post" action="nouvelle">
				<div id="evaluation">
					<div id="containerNote" class="containerEvaluation">
						<p id="intituleNoteEvaluation">Entrer une note :</p>
						<input id="ipt_note" type="number" name="note" min="0" max="20" step="0.5">
						<span id="star" class="icon-star"></span>
					</div>
					<div id="containerCommentaire" class="containerEvaluation">
						<p id="intituleCommentaireEvaluation">Laisser un commentaire à propos de Patrick</p>
						<textarea id="ipt_comm" name="commentaire"></textarea>
					</div>
					<div id="containerValider" class="containerEvaluation">
						<button id="validerNote" type="submit"><span class="icon-ok"></span> Noter </button>
					</div>
				</div>
				
			</form>
			
		</div>
		
	</div>
	
	
	<%@ include file="basdepage.jsp" %>
</body>

</html>
