

//afficher popup resulat
//si popup resulat = ok redirection
function redirection(){
   var valeur = "${resultat}";
   if(valeur != ""){
	   alert("${resultat}");
	   if(valeur == "Inscription réussie !"){
		   window.location = "accueil#connexion";
	   }
   }
} 
