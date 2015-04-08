package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import modele.Data;
import modele.Offre;
import modele.Postule;
import modele.Utilisateur;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utilitaire.ConnectionMySQL;
import utilitaire.CustomDate;
import formulaire.FormulaireRechercheAnnonce;

public class TestPostule {

	private Utilisateur paul;
	private Utilisateur george;

	
	@Before
	public void setUp() throws Exception {
		ConnectionMySQL.switchBDD_or_BDDTest(true);

		this.paul=Utilisateur.getUtilisateurParMail("duboispaul@mail.com");
		this.george=Utilisateur.getUtilisateurParMail("gg.le.clown@mail.com");
	}

	@After
	public void tearDown() throws Exception {
		this.paul=null;
		this.george=null;
		Data.BDD_Connection.rollback();
	}

	@Test
	public void testPostuler() throws Exception {
		String dateDebut = CustomDate.creerStringDate(1901, 01, 01);
		String dateFin = CustomDate.creerStringDate(2020, 01, 01);
		List<Offre> liste = new FormulaireRechercheAnnonce("Lyon",dateDebut,dateFin).getListeOffre();
		Postule p = new Postule(this.george, liste.get(0).getHebergeur(), liste.get(0).getLogement());
		p.setDateDebut("2015-05-06");
		p.setDateFin("2015-06-07");
		assertTrue(p.postulerAUneOffre());
	}

	@Test
	public void testGetAllPostulationsEnCours() throws Exception {
		ArrayList<Postule> liste = Postule.getDemandeEnvoyeByUser(this.paul);
		assertEquals(1,liste.size());
	}
		
	/*@Test
	public void testDeletePostulationsPerimees() throws Exception {
		ArrayList<Integer> liste = Postule.deletePostulationsPerimees();
		assertEquals(1,liste.size());
	}	*/
}
