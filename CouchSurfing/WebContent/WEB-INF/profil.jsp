
<!DOCTYPE html>
<html>
<%@ include file="entete.jsp"%>
<%@ include file="menu.jsp"%>
<body
	style="background-image:url(${pageContext.request.contextPath}/ServletBackground)">

	<div id="container" class="content-accueil">
		<h1 id="titre_Profil">Profil de ${utilisateurProfil.firstName} ${ utilisateurProfil.name }</h1>
		<div id="profile">
			<div id="info">
				<div class="infoSession">
					<div id="infoSession_rigthContainer">
						<div id="container_img_profil">
							<div id="img_profil">
								<form method="post" enctype="multipart/form-data">
									<label for="bt_img_profil"> <img id="img"
										src="${pageContext.request.contextPath}/ServletImageProfil" />
									</label> <input type="file" name="imgProfil" id="bt_img_profil"
										onchange="this.form.submit()">
								</form>
							</div>
						</div>
						<div id="containerNoteHerbergeur">
							<p id="etoile_note_hebergeur" class="icon-star noteHebergeur"></p>
							<p id="note_hebergeur" class="noteHebergeur">13/20</p>
						</div>
						<p id="nbr_avis_hebergeur">( 200 avis )</p>
					</div>
					<div id="infoSession_leftContainer">
						<div class="infoSessionContainer">
							<p class="intitule_profil">Nom</p>
							<p class="info_profil">${utilisateurProfil.name}</p>
						</div>
						<div class="infoSessionContainer">
							<p class="intitule_profil">Prenom</p>
							<p class="info_profil">${ utilisateurProfil.firstName }</p>
						</div>
						<div class="infoSessionContainer">
							<p class="intitule_profil">Email</p>
							<p class="info_profil">${ utilisateurProfil.mail }</p>
						</div>
						<div class="infoSessionContainer">
							<p class="intitule_profil">Pseudo</p>
							<p class="info_profil">${ utilisateurProfil.pseudo }</p>
						</div>
						<div class="infoSessionContainer">
							<p class="intitule_profil">Tel</p>
							<p class="info_profil">0672914568</p>
						</div>
					</div>


				</div>

				<div class="infoSession">
					<div class="infoSessionContainer">

						<div id="serviceAdresse" class="serviceDiv">
							<p class="intitule_profil_service">Adresse</p>
							<p class="info_profil_service">${ adresseLogement }</p>
						</div>
						<p id="intituleService">Service à proximité</p>
						<div id="serviceContainer">
							<c:if test="${crCommerce !=null}">
								<div id="serviceCommerce" class="serviceDiv">
									<p class="icon-basket-1 intitule_profil_service">Commerce</p>
									<p class="info_profil_service">${crCommerce }</p>
								</div>
							</c:if>
							<c:if test="${crSoins !=null}">

								<div id="serviceSoins" class="serviceDiv">
									<p class="icon-h-sigh intitule_profil_service">Soins</p>
									<p class="info_profil_service">${crSoins }</p>
								</div>
							</c:if>
							<c:if test="${crRestaurant !=null}">
								<div id="serviceRestaurant" class="serviceDiv">
									<p class="icon-food intitule_profil_service">Restaurant</p>
									<p class="info_profil_service">${crRestaurant }</p>
								</div>
							</c:if>
							<c:if test="${crTransport !=null}">

								<div id="serviceTransport" class="serviceDiv">
									<p class="icon-bus intitule_profil_service">Transport</p>
									<p class="info_profil_service">${crTransport }</p>
								</div>
							</c:if>
							<c:if test="${crAnimaux !=null}">

								<div id="serviceCAnimaux" class="serviceDiv">
									<p class="icon-paw intitule_profil_service">Animaux</p>
									<p class="info_profil_service">${crAnimaux }</p>
								</div>
							</c:if>
							<c:if test="${crInternet !=null}">

								<div id="serviceInternet" class="serviceDiv">
									<p class="icon-signal intitule_profil_service">Internet</p>
									<p class="info_profil_service">${crInternet }</p>
								</div>
							</c:if>
							<c:if test="${crParking !=null}">

								<div id="serviceParking" class="serviceDiv">
									<p class="icon-cab intitule_profil_service">Parking</p>
									<p class="info_profil_service">${crParking }</p>
								</div>
							</c:if>
							<c:if test="${crFumeur !=null}">

								<div id="serviceParking" class="serviceDiv">
									<p class="icon-fire intitule_profil_service">Fumeur</p>
									<p class="info_profil_service">${crFumeur }</p>
								</div>
							</c:if>
							<c:if test="${crHandicape !=null}">

								<div id="serviceHandicape" class="serviceDiv">
									<p class="icon-wheelchair intitule_profil_service">Handicape</p>
									<p class="info_profil_service">${crHandicape }</p>
								</div>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
