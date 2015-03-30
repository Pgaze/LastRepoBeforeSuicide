package tests;

import static org.junit.Assert.assertEquals;
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
	public void testSetIDExistant() throws Exception {
		Logement l= new Logement(new Adresse("1", "35 Avenue Rangueil", "31400","Les Pigeons", "", "Toulouse"));
		assertEquals(1,l.getIdLogement());
	}
	
	@Test
	public void testSetIdNonExistant() throws Exception {
		Logement l2= new Logement(new Adresse("10", "35 Rue Rangueil", "31400","Les oies", "0", "Toulouse"));
		assertEquals(4,l2.getIdLogement());
	}

}
