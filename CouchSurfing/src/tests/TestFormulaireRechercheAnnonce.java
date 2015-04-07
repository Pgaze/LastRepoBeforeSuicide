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
		assertTrue(l.size() == 1);
	}
	
	/*@Test
	public void testLogementPostulePlein() throws Exception {
		String dateDebut = CustomDate.creerStringDate(1901, 02, 01);
		String dateFin = CustomDate.creerStringDate(2020, 01, 01);
		this.form=new FormulaireRechercheAnnonce("Toulouse",dateDebut,dateFin);
		List<Offre> l = this.form.getListeOffre();
		Postule p = new Postule(Utilisateur.getUtilisateurParMail("gg.le.clown@mail.com"), l.get(0).getHebergeur(), l.get(0).getLogement());
		p.postulerAUneOffre();//TODO: ajouter les date auxquelle on veut postuler !!
		assertTrue(this.form.getListeOffre().isEmpty());
	}*/
	
	@Test(expected = Exception.class)
	public void testLogementInconnu() throws Exception {
		String dateDebut = CustomDate.creerStringDate(1901, 01, 01);
		String dateFin = CustomDate.creerStringDate(2020, 01, 01);
		this.form=new FormulaireRechercheAnnonce("Albi",dateDebut,dateFin);
		this.form.getListeOffre();
	}
}
