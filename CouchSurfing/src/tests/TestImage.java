package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import modele.Data;
import modele.Image;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utilitaire.ConnectionMySQL;

public class TestImage {
	
	private Image i;

	@Before
	public void setUp() throws Exception {
		this.i = new Image();
		ConnectionMySQL.switchBDD_or_BDDTest(true);
	}

	@After
	public void tearDown() throws Exception {
		Data.BDD_Connection.rollback();
	}

	@Test
	public void testInserer() throws Exception {
		File f= new File("EchangeFichier/pingouin.png");
		this.i.setImage(f);
		this.i.setNom(f.getName());
		assertTrue(this.i.insererDansLaBase());
	}
	
	
	@Test
	public void testRecuperer() throws Exception {
		this.i=Image.getImageById(0);
		assertEquals("pingouinTest",i.getNom());
	}

}
