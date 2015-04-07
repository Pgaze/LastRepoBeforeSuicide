package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import modele.Data;
import modele.Utilisateur;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utilitaire.ConnectionMySQL;
import utilitaire.Password;

public class TestUtilisateur {

	private Utilisateur dubois;
	
	@Before
	public void setUp() throws Exception {
		ConnectionMySQL.switchBDD_or_BDDTest(true);
		this.dubois=new Utilisateur("duboispaul@mail.com","motDePasse1","Dubois","Paul","Paulo","0621611291");
	}

	@After
	public void tearDown() throws Exception {
		this.dubois=null;
		Data.BDD_Connection.rollback();
	}

	@Test
	public void testGetUtilisateurParMail() throws Exception {
		this.dubois=Utilisateur.getUtilisateurParMail("duboispaul@mail.com");
		assertEquals("Dubois",dubois.getName());
		assertEquals("Paul",dubois.getFirstName());
		assertEquals(Password.encrypt("motDePasse1"),dubois.getPassword());
		assertEquals("Paulo",dubois.getPseudo());
	}
	
	@Test
	public void testGetUtilisateurById() throws Exception {
		this.dubois=Utilisateur.getUtilisateurById(4);
		assertEquals("Dubois",dubois.getName());
	}
	
	@Test
	public void testUpdConfiance() throws Exception {
		this.dubois=Utilisateur.getUtilisateurById(4);
		assertEquals(0,this.dubois.getAvgConfiance());
		this.dubois.voteConfiance(5);
		this.dubois.updateConfiance();
		assertEquals(5,this.dubois.getAvgConfiance());
	}
	
	@Test
	public void testUpdtConfort() throws Exception {
		this.dubois=Utilisateur.getUtilisateurById(4);
		assertEquals(0,this.dubois.getAvgConfort());
		this.dubois.voteConfort(3);
		this.dubois.updateConfort();
		assertEquals(3,this.dubois.getAvgConfort());
	}
	
	@Test
	public void testInsertionBase() throws Exception {
		Utilisateur u = new Utilisateur("everything.isAwesome@mail.com", "motDePasse1", "Nard", "Ken", "Ken à barbie", "0564845784");
		assertTrue(u.insererDansLaBase());
	}
	
	@Test
	public void testGetIdPhotoProfil() throws Exception {
		Utilisateur u = Utilisateur.getUtilisateurById(1);
		assertEquals(2, u.getIdPhotoProfil());
	}
	
	@Test
	public void testSetIdAvatar() throws Exception {
		Utilisateur u= Utilisateur.getUtilisateurById(3);
		assertTrue(u.setIdAvatar(2));
	}
	
	@Test
	public void testGetUtilisateurByIdLogement() throws Exception {
		assertEquals(2, Utilisateur.getUtilisateurByIdLogement(3).getIdUser());
	}
	
	@Test
	public void testAUnLogement() throws Exception {
		assertTrue(Utilisateur.getUtilisateurById(2).aUnLogement());
		assertFalse(Utilisateur.getUtilisateurById(4).aUnLogement());
	}
}
