package tests;

import static org.junit.Assert.*;
import modele.Data;
import modele.Utilisateur;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utilitaire.ConnectionMySQL;
import formulaire.FormulaireInscription;

public class TestFormulaireInscription {
	
	private FormulaireInscription form;
	
	@Before
	public void setUp() throws Exception {
		ConnectionMySQL.switchBDD_or_BDDTest(true);

		this.form=new FormulaireInscription();
	}
	
	@After
	public void tearDown() throws Exception {
		Data.BDD_Connection.rollback();
		this.form=null;
	}
	

	@Test
	public void testValidationMail() {
		assertTrue(this.form.testMailValide("duboispaul@mail.com"));
		assertFalse(this.form.testMailValide("mailinvalide.com"));
		assertTrue(this.form.testMailValide("dubois.paul@mail.com"));
	}
	
	@Test
	public void testValidationMDP() throws Exception {
		assertTrue(this.form.testMotDePasseValide("motDePasse56"));
		assertFalse(this.form.testMotDePasseValide("motdepassetoutenminuscules"));
		assertFalse(this.form.testMotDePasseValide("MOTDEPASSETOUTENMAJUSCULES"));
		assertFalse(this.form.testMotDePasseValide("court"));
		assertFalse(this.form.testMotDePasseValide("motDePasseBeaucoupBeacoupBeaucoupTropLong"));
		
	}
	
	@Test
	public void testProcedureInscription() throws Exception {
		this.form.setPrenom("Louis");
		this.form.setNom("Ouistiti");
		this.form.setMdp("lm");
		this.form.setMail("louisouistiti@mail.com");
		this.form.setConfirmMdp("lm");
		assertEquals("Mot de passe invalide",this.form.procedureInscription());
	}
	
	@Test
	public void testTelValide() throws Exception {
		assertTrue(this.form.testTelValide("0621611191"));
		assertFalse(this.form.testTelValide("06RE547"));
	}

}
