package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import modele.Data;
import modele.Offre;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utilitaire.ConnectionMySQL;
import utilitaire.CustomDate;
import formulaire.FormulaireRechercheAnnonce;

public class TestFormulaireRechercheAnnonce {
	
	private FormulaireRechercheAnnonce form;

	@Before
	public void setUp() throws Exception {
		ConnectionMySQL.switchBDD_or_BDDTest(true);
	}

	@After
	public void tearDown() throws Exception {
		this.form=null;
		Data.BDD_Connection.rollback();
	}

	@Test
	public void testLogementStd() throws Exception {
		String dateDebut = CustomDate.creerStringDate(1901, 2, 1);
		String dateFin = CustomDate.creerStringDate(2020, 1, 1);
		this.form=new FormulaireRechercheAnnonce("Lyon",dateDebut,dateFin);
		List<Offre> l = this.form.getListeOffre();
		assertEquals(2, l.get(0).getLogement().getIdLogement());
	}
	
	@Test
	public void testLogementPostulePasPlein() throws Exception {
		String dateDebut = CustomDate.creerStringDate(1901, 02, 01);
		String dateFin = CustomDate.creerStringDate(2020, 01, 01);
		this.form=new FormulaireRechercheAnnonce("Toulouse",dateDebut,dateFin);
		List<Offre> l = this.form.getListeOffre();
		assertTrue(l.size() == 2);
		assertEquals(4,l.get(0).getLogement().getIdLogement());
		assertEquals(1,l.get(1).getLogement().getIdLogement());
		assertEquals("2016-06-11",l.get(0).getDateDebut().toString());
		assertEquals("2016-07-02",l.get(0).getDateFin().toString());
	}
	
	@Test(expected = Exception.class)
	public void testLogementInconnu() throws Exception {
		String dateDebut = CustomDate.creerStringDate(1901, 01, 01);
		String dateFin = CustomDate.creerStringDate(2020, 01, 01);
		this.form=new FormulaireRechercheAnnonce("Albi",dateDebut,dateFin);
		this.form.getListeOffre();
	}
}
