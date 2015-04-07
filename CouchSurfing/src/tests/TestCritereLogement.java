package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import modele.Critere;
import modele.Critere.TypeCritere;
import modele.Data;
import modele.Logement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utilitaire.ConnectionMySQL;

public class TestCritereLogement {
	
	private Logement l;

	@Before
	public void setUp() throws Exception {
		this.l=new Logement();
		ConnectionMySQL.switchBDD_or_BDDTest(true);
	}

	@After
	public void tearDown() throws Exception {
		this.l=null;
		Data.BDD_Connection.rollback();
	}

	@Test
	public void testAddCritere() {
		this.l.addCritere(new Critere(TypeCritere.FUMEUR, true, null));
		assertTrue(this.l.getCritere(TypeCritere.FUMEUR).getValeur());
	}
	
	@Test
	public void testSetCritere() throws Exception {
		this.l.addCritere(new Critere(TypeCritere.FUMEUR, true, null));
		this.l.setCritereValue(new Critere(TypeCritere.FUMEUR, false, null));
		assertFalse(this.l.getCritere(TypeCritere.FUMEUR).getValeur());
	}
	
	@Test
	public void testRemoveCritere(){
		this.l.addCritere(new Critere(TypeCritere.FUMEUR, true, null));
		this.l.removeCritere(TypeCritere.FUMEUR);
		assertNull(this.l.getCritere(TypeCritere.FUMEUR));
	}

}
