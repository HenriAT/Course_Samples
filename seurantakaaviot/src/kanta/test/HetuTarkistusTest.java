package kanta.test;
// Generated by ComTest BEGIN
import kanta.*;
import static org.junit.Assert.*;
import org.junit.*;
import static kanta.HetuTarkistus.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.04.15 12:12:19 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class HetuTarkistusTest {



  // Generated by ComTest BEGIN
  /** testTarkista25 */
  @Test
  public void testTarkista25() {    // HetuTarkistus: 25
    HetuTarkistus hetut = new HetuTarkistus(); 
    assertEquals("From: HetuTarkistus line: 28", "Hetu liian lyhyt", hetut.tarkista("12121")); 
    assertEquals("From: HetuTarkistus line: 29", "Hetu liian lyhyt", hetut.tarkista("k")); 
    assertEquals("From: HetuTarkistus line: 30", "Alkuosassa saa olla vain numeroita", hetut.tarkista("12121k")); 
    assertEquals("From: HetuTarkistus line: 31", null, hetut.tarkista("121212"));  // sallitaan pelkkä syntymäaika
    assertEquals("From: HetuTarkistus line: 32", "Liian pieni päivämäärä", hetut.tarkista("001212")); 
    assertEquals("From: HetuTarkistus line: 33", "Liian suuri päivämäärä", hetut.tarkista("321212")); 
    assertEquals("From: HetuTarkistus line: 34", "Liian suuri päivämäärä", hetut.tarkista("300212")); 
    assertEquals("From: HetuTarkistus line: 35", "Liian suuri päivämäärä", hetut.tarkista("310412")); 
    assertEquals("From: HetuTarkistus line: 36", "Liian suuri kuukausi", hetut.tarkista("121312")); 
    assertEquals("From: HetuTarkistus line: 37", "Liian pieni kuukausi", hetut.tarkista("120012")); 
    assertEquals("From: HetuTarkistus line: 38", "Väärä erotinmerkki", hetut.tarkista("121212B222Q")); 
    assertEquals("From: HetuTarkistus line: 39", "Yksilöosassa kirjaimia", hetut.tarkista("121212-2k2Q")); 
    assertEquals("From: HetuTarkistus line: 40", "Yksilöosa liian lyhyt", hetut.tarkista("121212-2")); 
    assertEquals("From: HetuTarkistus line: 41", "Yksilöosa liian lyhyt", hetut.tarkista("121212-")); 
    assertEquals("From: HetuTarkistus line: 42", "Hetu liian pitkä", hetut.tarkista("121212-12345")); 
    assertEquals("From: HetuTarkistus line: 43", "Tarkistusmerkin kuuluisi olla N", hetut.tarkista("121212-222S")); 
    assertEquals("From: HetuTarkistus line: 44", null, hetut.tarkista("121212-222N")); 
    assertEquals("From: HetuTarkistus line: 45", null, hetut.tarkista("121212-231Y")); 
    assertEquals("From: HetuTarkistus line: 46", null, hetut.tarkista("311212-2317")); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testHetunTarkistusMerkki82 */
  @Test
  public void testHetunTarkistusMerkki82() {    // HetuTarkistus: 82
    assertEquals("From: HetuTarkistus line: 83", 'N', hetunTarkistusMerkki("121212-222")); 
    assertEquals("From: HetuTarkistus line: 84", 'N', hetunTarkistusMerkki("121212-222S")); 
    assertEquals("From: HetuTarkistus line: 85", 'N', hetunTarkistusMerkki("121212-222N")); 
    assertEquals("From: HetuTarkistus line: 86", 'Y', hetunTarkistusMerkki("121212-231Y")); 
    assertEquals("From: HetuTarkistus line: 87", '7', hetunTarkistusMerkki("311212-2317")); 
    assertEquals("From: HetuTarkistus line: 88", '7', hetunTarkistusMerkki("311212-2317XY"));  // vaikka on liikaa merkkejä
    assertEquals("From: HetuTarkistus line: 89", 'F', hetunTarkistusMerkki("999999-9999XY"));  // vaikka on pvm väärin
    try {
    assertEquals("From: HetuTarkistus line: 90", 'N', hetunTarkistusMerkki("12121A-222S")); 
    fail("HetuTarkistus: 90 Did not throw NumberFormatException");
    } catch(NumberFormatException _e_){ _e_.getMessage(); }
    try {
    assertEquals("From: HetuTarkistus line: 91", 'N', hetunTarkistusMerkki("12121A-22")); 
    fail("HetuTarkistus: 91 Did not throw StringIndexOutOfBoundsException");
    } catch(StringIndexOutOfBoundsException _e_){ _e_.getMessage(); }
    try {
    assertEquals("From: HetuTarkistus line: 92", 'N', hetunTarkistusMerkki("121")); 
    fail("HetuTarkistus: 92 Did not throw StringIndexOutOfBoundsException");
    } catch(StringIndexOutOfBoundsException _e_){ _e_.getMessage(); }
  } // Generated by ComTest END
}