package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import modele.Data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utilitaire.ConnectionMySQL;
import utilitaire.Password;
import formulaire.FormulaireConnexion;

public class TestFormulaireConnexion {
	
	FormulaireConnexion form;

	@Before
	public void setUp() throws Exception {
		ConnectionMySQL.switchBDD_or_BDDTest(true);

		this.form=new FormulaireConnexion();
	}

	@After
	public void tearDown() throws Exception {
		this.form=null;
		Data.BDD_Connection.rollback();
	}

	@Test
	public void testSHA256(){
		assertEquals("27291b9c93082e19c9072de4d4aba26116cc6707e0c189ae6dddad5afd820207"
					,Password.encrypt("motDePasse1"));
	}
	
	@Test
	public void testTrue() throws SQLException {
		this.form.setLogin("duboispaul@mail.com");
		this.form.setMdp("motDePasse1");
		assertTrue(this.form.verificationCoupleMailMotDePasse());
	}
	
	

}
