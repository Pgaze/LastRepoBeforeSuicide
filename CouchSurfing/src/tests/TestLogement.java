package tests;

import static org.junit.Assert.*;
import modele.Adresse;
import modele.Data;
import modele.Logement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utilitaire.ConnectionMySQL;

public class TestLogement {

	
	
	@Before
	public void setUp() throws Exception {
		ConnectionMySQL.switchBDD_or_BDDTest(true);
	}

	@After
	public void tearDown() throws Exception {
		Data.BDD_Connection.rollback();
	}

	@Test
	public void testUpdateListCriteres() throws Exception {
		assertTrue(true);
	}
}
