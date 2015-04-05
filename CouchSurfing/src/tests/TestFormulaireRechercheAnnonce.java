package tests;

import static org.junit.Assert.assertEquals;

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
		String dateDebut = CustomDate.creerStringDate(1901, 02, 01);
		String dateFin = CustomDate.creerStringDate(2020, 01, 01);
		this.form=new FormulaireRechercheAnnonce("Lyon",dateDebut,dateFin);
		List<Offre> l = this.form.getListeOffre();
		assertEquals(2, l.get(0).getLogement().getIdLogement());
	}
	
	/*@Test
	public void testLogementPostulePlein() throws Exception {
		String dateDebut = CustomDate.creerStringDate(1901, 02, 01);
		String dateFin = CustomDate.creerStringDate(2020, 01, 01);
		this.form=new FormulaireRechercheAnnonce("Orange",dateDebut,dateFin);
		List<Offre> l = this.form.getListeOffre();
		assertTrue(l.isEmpty());
	}*/
	
	@Test(expected = Exception.class)
	public void testLogementInconnu() throws Exception {
		String dateDebut = CustomDate.creerStringDate(1901, 01, 01);
		String dateFin = CustomDate.creerStringDate(2020, 01, 01);
		this.form=new FormulaireRechercheAnnonce("Albi",dateDebut,dateFin);
		this.form.getListeOffre();
	}
}