package tests;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import modele.Adresse;
import modele.Critere;
import modele.Data;
import modele.Logement;
import modele.Critere.TypeCritere;

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
	public void testinsererDansLaBase() throws Exception {
		Logement l = new Logement(new Adresse("", "Rue du test", "31541", "", "", "Pekin"));
		assertTrue(l.insererDansLaBase());
	}
	
	@Test
	public void testGetLogementById() throws Exception {
		Logement l = Logement.getLogementById(1);
		Adresse a = l.getAdresse();
		assertEquals("35 Avenue Rangueil", a.getNumeroEtVoie());
		assertEquals("Toulouse", a.getVille());
	}
	
	@Test
	public void testGetIdImageLogement() throws Exception {
		assertEquals(2, Logement.getLogementById(1).getIdPhotoLogement());
	}
	
	@Test
	public void testSetIdImageLogement() throws Exception {
		Logement l = Logement.getLogementById(2);
		assertTrue(l.setIdImageLogement(2));
	}
	
	@Test
	public void testSetDateToNull() throws Exception {
		Logement l = Logement.getLogementById(3);
		assertTrue(l.setDateToNull());
	}
	
	@Test
	public void testUpdateDates() throws Exception {
		Logement l = Logement.getLogementById(3);
		l.setDateDebutFin(Date.valueOf("2015-06-10"), Date.valueOf("2016-06-10"));
		assertTrue(l.updateDates());
	}
	
	@Test
	public void testUpdateListCriteres() throws Exception {
		Logement l= Logement.getLogementById(2);
		l.addCritere(new Critere(TypeCritere.ANIMAUX,true,"Miaou"));
		assertTrue(l.updateListCritere());
	}
	
	@Test
	public void testDeleteFrombase() throws Exception {
		assertFalse(Logement.deleteFromBase(20));
		assertTrue(Logement.deleteFromBase(2));
	}
}
