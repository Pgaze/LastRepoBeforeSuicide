package tests;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import modele.Data;

import org.junit.Test;

import utilitaire.ConnectionMySQL;

public class TestConnnexionBDD {

	@Test
	public void testConnectionBDD() throws SQLException{
		assertEquals("/CouchSurfing",Data.BDD_NAME);
	}
	
	@Test
	public void testConnectionSwitchToBDDTest_And_Reverse() throws SQLException{
		ConnectionMySQL.switchBDD_or_BDDTest(true);
		assertEquals("/CouchSurfingTestN",Data.BDD_NAME);
		ConnectionMySQL.switchBDD_or_BDDTest(false);
		assertEquals("/CouchSurfing",Data.BDD_NAME);
	}
}