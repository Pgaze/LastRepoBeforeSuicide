package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import vue.Menu;

public class MenuTest {
	
	Menu test;
	
	@Test
	public void leNom() {
		test = new Menu("menuInvite");
		assertEquals("menuInvite",test.getNom());
	}
	
/*	@Test
	public void lesLiens() {
		test = new Menu("menuInvite");
		test.addLien("Lien1", false);
		String lien = test.getLiensMenu().toString();
		assertEquals("{Lien1=lien1}",lien);
	}
	
	@Test
	public void lesLiens2() {
		test = new Menu("menuInvite");
		test.addLien("Lien1", true);
		assertEquals("{Lien1=#lien1}",test.getLiensMenu().toString());
	}*/


}
