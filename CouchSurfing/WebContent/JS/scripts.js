

//afficher popup resulat
//si popup resulat = ok redirection
function redirectionAccueil(){
   var valeur = "${resultat}";
   if(valeur != ""){
	   alert("${resultat}");
	   if(valeur == "Inscription réussie !"){
		   window.location = "accueil#connexion";
	   }
   }
} 

function redirectionCriteres(){
	var valeur = "${resultat}";
	if(valeur != ""){
		alert("${resultat}");
		if(valeur == "Logement ajoute"){
			window.location = "criteres";
		}
	}
} 