package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Date;

import javax.naming.directory.InvalidAttributeValueException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utilitaire.CustomDate;

public class TestCustomDate {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreerStringDateValide() {
		String p = null;
		try {
			p = CustomDate.creerStringDate(2000,01,02);
		} catch (InvalidAttributeValueException e) {
			e.printStackTrace();
			fail("Exception innatendue");
		}
		assertEquals("2000-1-2",p);
	}
	
	@Test (expected = InvalidAttributeValueException.class)
	public void testcreerStringDateInvalideParametersMonthInfZero() throws InvalidAttributeValueException {
		CustomDate.creerStringDate(2010,00,02);
	}
	
	@Test (expected = InvalidAttributeValueException.class)
	public void testcreerStringDateInvalideParametersMonthSup12() throws InvalidAttributeValueException {
		CustomDate.creerStringDate(2010,13,02);
	}
	
	@Test (expected = InvalidAttributeValueException.class)
	public void testcreerStringDateInvalideParameterDaySup31() throws InvalidAttributeValueException {
		CustomDate.creerStringDate(2010,01,32);
	}
	
	@Test (expected = InvalidAttributeValueException.class)
	public void testCreerStringDateInvalide() throws InvalidAttributeValueException {
		CustomDate.creerStringDate(01,201,02);
	}
	
	@Test
	public void testCheckFormatDateNothingToDo() {
		String date = "2010-10-12";
		String newDate = "tatayoyo";
		newDate = CustomDate.checkFormatDate(date);
		assertEquals("2010-10-12",newDate);
	}
	
	@Test
	public void testCheckFormatDateMMDDYYYY() {
		String date = "10/12/2010";
		String newDate = "tatayoyo";
		newDate = CustomDate.checkFormatDate(date);
		assertEquals("2010-10-12",newDate);
	}
	
	@Test
	public void testCheckFormatDateVide() {
		String date = "";
		String newDate = "tatayoyo";
		newDate = CustomDate.checkFormatDate(date);
		assertEquals(null,newDate);
	}
	
	@Test
	public void testCheckIntegriteDatesValide() {
		try {
			Date date1 = Date.valueOf("2016-02-02");
			Date date2 = Date.valueOf("2016-02-03");
			CustomDate.checkIntegriteDates(date1,date2);
		} catch (InvalidAttributeValueException e) {
			e.printStackTrace();
			fail("Echec");
		}
		assertTrue(true);
	}
	
	@Test (expected = InvalidAttributeValueException.class)
	public void testCheckIntegriteDatesInvalideBeforeToday() throws InvalidAttributeValueException{
		Date date1 = Date.valueOf("2010-02-02");
		Date date2 = Date.valueOf("2010-02-03");
		CustomDate.checkIntegriteDates(date1,date2);
	}
	
	@Test (expected = InvalidAttributeValueException.class)
	public void testCheckIntegriteDatesInvalideEgale() throws InvalidAttributeValueException {
		Date date1 = Date.valueOf("2010-01-01");
		Date date2 = Date.valueOf("2010-01-01");
		CustomDate.checkIntegriteDates(date1,date2);
	}

	@Test (expected = InvalidAttributeValueException.class)
	public void testCheckIntegriteDatesInvalideEndBefore() throws InvalidAttributeValueException {
		Date date1 = Date.valueOf("2010-02-02");
		Date date2 = Date.valueOf("2010-02-01");
		CustomDate.checkIntegriteDates(date1,date2);
	}
	
}
