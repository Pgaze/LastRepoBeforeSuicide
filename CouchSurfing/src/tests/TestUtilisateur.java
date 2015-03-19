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
	private Utilisateur dupont;
	
	@Before
	public void setUp() throws Exception {
		ConnectionMySQL.switchBDD_or_BDDTest(true);

		this.dubois=new Utilisateur("duboispaul@mail.com","motDePasse1","Dubois","Paul","Paulo");
		this.dupont=new Utilisateur("dupont.pierre@mail.com","motDePasse1","Dupont","Pierre","Pierrot");
	}

	@After
	public void tearDown() throws Exception {
		this.dubois=null;
		this.dupont=null;
		Data.BDD_Connection.rollback();
	}

	@Test
	public void testSetID() throws Exception {
		assertEquals(0,this.dubois.getIdUser());
		assertEquals(3,this.dupont.getIdUser());	
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
		this.dubois=Utilisateur.getUtilisateurById(0);
		assertEquals("Dubois",dubois.getName());
	}
	
	@Test
	public void testUpdConfiance() throws Exception {
		this.dubois=Utilisateur.getUtilisateurById(0);
		assertEquals(0,this.dubois.getAvgConfiance());
		this.dubois.voteConfiance(5);
		this.dubois.updateConfiance();
		assertEquals(5,this.dubois.getAvgConfiance());
	}
	
	@Test
	public void testUpdtConfort() throws Exception {
		this.dubois=Utilisateur.getUtilisateurById(0);
		assertEquals(0,this.dubois.getAvgConfort());
		this.dubois.voteConfort(3);
		this.dubois.updateConfort();
		assertEquals(3,this.dubois.getAvgConfort());
	}
	
	
}
