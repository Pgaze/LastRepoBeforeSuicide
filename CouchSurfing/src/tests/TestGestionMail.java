package tests;
import static org.junit.Assert.assertTrue;
import modele.Data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utilitaire.ConnectionMySQL;
import utilitaire.GestionMail;

public class TestGestionMail {
	
	@Before
	public void setUp() throws Exception {
		ConnectionMySQL.switchBDD_or_BDDTest(true);
	}

	@After
	public void tearDown() throws Exception {
		Data.BDD_Connection.rollback();
	}
	
	@Test
	public void testMail (){	
		String from = "Le programme de test";
		String to = "clicknsleep@gmail.com";
		String objet = "Surprise Party";
		String text = "Viens avec nous, on va chasser des bananes !";
		
		assertTrue(GestionMail.send(from, to, objet, text));
	}
}
