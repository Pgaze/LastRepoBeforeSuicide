package tests;

import static org.junit.Assert.assertEquals;

import java.sql.Date;

import modele.Logement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utilitaire.ConnectionMySQL;
import formulaire.FormulaireCritere;

public class TestFormulaireCritere {

	private FormulaireCritere form;
	
	@Before
	public void setUp() throws Exception {
		ConnectionMySQL.switchBDD_or_BDDTest(true);
	}

	@After
	public void tearDown() throws Exception {
		this.form = null;
	}

	@Test
	public void testSetCritereOnLogement() throws Exception {
		Logement l= Logement.getLogementById(4);
		this.form = new FormulaireCritere("Superette", "200m", "", "", "Chat uniquement", "", "", "", "", "", "");
		this.form.setCritereOnLogement(l);
		assertEquals(3, l.getLesCriteres().size());
	}
	
	@Test
	public void testSetDateOnLogement() throws Exception {
		Logement l= Logement.getLogementById(4);
		this.form = new FormulaireCritere("", "", "", "", "", "", "", "", "", "2015-05-25", "2015-06-25");
		this.form.setDateOnLogement(l);
		assertEquals(Date.valueOf("2015-05-25"), l.getDateDebut());
		assertEquals(Date.valueOf("2015-06-25"),l.getDateFin());
	}

}
