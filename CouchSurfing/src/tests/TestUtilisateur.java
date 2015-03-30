package tests;

import static org.junit.Assert.assertEquals;
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
		this.dubois=new Utilisateur("duboispaul@mail.com","motDePasse1","Dubois","Paul","Paulo");
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
}
