package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import modele.Data;
import modele.Utilisateur;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utilitaire.ConnectionMySQL;
import formulaire.FormulaireProposerLogement;

public class TestFormulaireProposerLogement {

	FormulaireProposerLogement form;
	
	@Before
	public void setUp() throws Exception {
		ConnectionMySQL.switchBDD_or_BDDTest(true);
	}

	@After
	public void tearDown() throws Exception {
		Data.BDD_Connection.rollback();
		this.form=null;
	}

	@Test
	public void testVerificationCPOk() throws SQLException {
		this.form = new FormulaireProposerLogement("1", "35 Avenue Rangueil",
				"31400", "TaMere", "", "Toulouse", 
				Utilisateur.getUtilisateurParMail("pauldubois@mail.com"));
		assertTrue(this.form.verificationCp());
	}

	@Test
	public void testVerificationCPNonOk() throws SQLException {
		this.form = new FormulaireProposerLogement("1", "35 Avenue Rangueil",
				"3140", "TaMere", "", "Toulouse", 
				Utilisateur.getUtilisateurParMail("pauldubois@mail.com"));
		assertFalse(this.form.verificationCp());
	}
	
	@Test
	public void testProcedureAjoutLogement() throws Exception {
		this.form = new FormulaireProposerLogement("5", "38 Avenue Rangueil","42300", "Maaaaah", "", "Paris",
				Utilisateur.getUtilisateurParMail("gg.le.clown@mail.com"));
		assertEquals("Logement ajoute",this.form.procedureAjoutLogement());
	}

	@Test
	public void testAdresseToString() throws SQLException{
		this.form = new FormulaireProposerLogement("1", "35 Avenue Rangueil","3140", "TaMere", "", "Toulouse", 
				Utilisateur.getUtilisateurParMail("pauldubois@mail.com"));
		String expectedStr="35 Avenue Rangueil 3140 Toulouse Residence: TaMere Batiment/Escalier 1";
		String myStr=this.form.getLogement().getAdresse().toString();
		assertEquals(expectedStr,myStr);
	}
}