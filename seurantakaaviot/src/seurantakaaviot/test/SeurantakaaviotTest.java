package seurantakaaviot.test;
// Generated by ComTest BEGIN
import java.util.Collection;
import java.util.*;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.*;
import seurantakaaviot.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.04.23 01:29:14 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class SeurantakaaviotTest {



  // Generated by ComTest BEGIN
  /** testLisaa23 */
  @Test
  public void testLisaa23() {    // Seurantakaaviot: 23
    Seurantakaaviot seurantakaaviot = new Seurantakaaviot(); 
    Henkilo henkilo1 = new Henkilo(); henkilo1.aseta(1,"Susi Sepe"); 
    Henkilo henkilo2 = new Henkilo(); henkilo2.aseta(1, "Ankka Aku"); 
    Henkilo henkilo3 = new Henkilo(); henkilo3.aseta(1, "Ankka Iines"); 
    seurantakaaviot.lisaa(henkilo1); 
    seurantakaaviot.lisaa(henkilo2); 
    seurantakaaviot.lisaa(henkilo3); 
    assertEquals("From: Seurantakaaviot line: 31", 3, seurantakaaviot.getHenkiloita()); 
    assertEquals("From: Seurantakaaviot line: 32", true, seurantakaaviot.annaHenkilo(0).getNimi().equals("Susi Sepe")); 
    assertEquals("From: Seurantakaaviot line: 33", true, seurantakaaviot.annaHenkilo(1).getNimi().equals("Ankka Aku")); 
    assertEquals("From: Seurantakaaviot line: 34", true, seurantakaaviot.annaHenkilo(2).getNimi().equals("Ankka Iines")); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testEtsi47 */
  @Test
  public void testEtsi47() {    // Seurantakaaviot: 47
    Seurantakaaviot seurantakaaviot = new Seurantakaaviot(); 
    Henkilo henkilo1 = new Henkilo(); henkilo1.rekisteroi(); 
    Henkilo henkilo2 = new Henkilo(); henkilo2.rekisteroi(); 
    Henkilo henkilo3 = new Henkilo(); henkilo3.rekisteroi(); 
    henkilo1.aseta(1,"Susi Sepe"); 
    henkilo2.aseta(1, "Ankka Aku"); 
    henkilo3.aseta(1, "Ankka Iines"); 
    seurantakaaviot.lisaa(henkilo1); 
    seurantakaaviot.lisaa(henkilo2); 
    seurantakaaviot.lisaa(henkilo3); 
    Collection<Henkilo> loytyneet = seurantakaaviot.etsi("*Susi*"); 
    assertEquals("From: Seurantakaaviot line: 61", 1, loytyneet.size()); 
    loytyneet = seurantakaaviot.etsi("*s*"); 
    assertEquals("From: Seurantakaaviot line: 63", 2, loytyneet.size()); 
    loytyneet = seurantakaaviot.etsi(""); 
    assertEquals("From: Seurantakaaviot line: 65", 3, loytyneet.size()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testKorvaaTaiLisaa78 
   * @throws CloneNotSupportedException when error
   */
  @Test
  public void testKorvaaTaiLisaa78() throws CloneNotSupportedException {    // Seurantakaaviot: 78
    Seurantakaaviot seurantakaaviot = new Seurantakaaviot(); 
    Henkilo henkilo1 = new Henkilo(); henkilo1.rekisteroi(); 
    Henkilo henkilo2 = new Henkilo(); henkilo2.rekisteroi(); 
    seurantakaaviot.korvaaTaiLisaa(henkilo1); assertEquals("From: Seurantakaaviot line: 83", 1, seurantakaaviot.getHenkiloita()); 
    seurantakaaviot.korvaaTaiLisaa(henkilo2); assertEquals("From: Seurantakaaviot line: 84", 2, seurantakaaviot.getHenkiloita()); 
    Henkilo henkilo3 = henkilo1.clone(); 
    seurantakaaviot.korvaaTaiLisaa(henkilo3); assertEquals("From: Seurantakaaviot line: 86", 2, seurantakaaviot.getHenkiloita()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testKorvaaTaiLisaa100 
   * @throws CloneNotSupportedException when error
   */
  @Test
  public void testKorvaaTaiLisaa100() throws CloneNotSupportedException {    // Seurantakaaviot: 100
    Seurantakaaviot seurantakaaviot = new Seurantakaaviot(); 
    Henkilo henkilo = new Henkilo(); henkilo.rekisteroi(); 
    seurantakaaviot.lisaa(henkilo); 
    Kaavio kaavio1 = new Kaavio(henkilo.getTunnusNro()); kaavio1.rekisteroi(); 
    Kaavio kaavio2 = new Kaavio(henkilo.getTunnusNro()); kaavio2.rekisteroi(); 
    seurantakaaviot.korvaaTaiLisaa(kaavio1); assertEquals("From: Seurantakaaviot line: 107", 1, seurantakaaviot.annaKaaviot(henkilo).size()); 
    seurantakaaviot.korvaaTaiLisaa(kaavio2); assertEquals("From: Seurantakaaviot line: 108", 2, seurantakaaviot.annaKaaviot(henkilo).size()); 
    Kaavio kaavio3 = kaavio1.clone(); 
    seurantakaaviot.korvaaTaiLisaa(kaavio3); assertEquals("From: Seurantakaaviot line: 110", 2, seurantakaaviot.annaKaaviot(henkilo).size()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testKorvaaTaiLisaa124 
   * @throws CloneNotSupportedException when error
   */
  @Test
  public void testKorvaaTaiLisaa124() throws CloneNotSupportedException {    // Seurantakaaviot: 124
    Seurantakaaviot seurantakaaviot = new Seurantakaaviot(); 
    Kaavio kaavio = new Kaavio(); kaavio.rekisteroi(); 
    Datapiste dat1 = new Datapiste(kaavio.getTunnusNro()); dat1.rekisteroi(); 
    Datapiste dat2 = new Datapiste(kaavio.getTunnusNro()); dat2.rekisteroi(); 
    seurantakaaviot.korvaaTaiLisaa(dat1); assertEquals("From: Seurantakaaviot line: 130", 1, seurantakaaviot.annaDatapisteet(kaavio).size()); 
    seurantakaaviot.korvaaTaiLisaa(dat2); assertEquals("From: Seurantakaaviot line: 131", 2, seurantakaaviot.annaDatapisteet(kaavio).size()); 
    Datapiste dat3 = dat1.clone(); 
    seurantakaaviot.korvaaTaiLisaa(dat3); assertEquals("From: Seurantakaaviot line: 133", 2, seurantakaaviot.annaDatapisteet(kaavio).size()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testLisaa145 */
  @Test
  public void testLisaa145() {    // Seurantakaaviot: 145
    Seurantakaaviot seurantakaaviot = new Seurantakaaviot(); 
    Henkilo henkilo = new Henkilo(); henkilo.rekisteroi(); 
    Kaavio kaavio1 = new Kaavio(henkilo.getTunnusNro()); 
    Kaavio kaavio2 = new Kaavio(henkilo.getTunnusNro()); 
    Kaavio kaavio3 = new Kaavio(henkilo.getTunnusNro()); 
    seurantakaaviot.lisaa(kaavio1); 
    seurantakaaviot.lisaa(kaavio2); 
    seurantakaaviot.lisaa(kaavio3); 
    List<Kaavio> loytyneet; 
    loytyneet = seurantakaaviot.annaKaaviot(henkilo); 
    assertEquals("From: Seurantakaaviot line: 157", 3, loytyneet.size()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testLisaa169 */
  @Test
  public void testLisaa169() {    // Seurantakaaviot: 169
    Seurantakaaviot seurantakaaviot = new Seurantakaaviot(); 
    Henkilo henkilo = new Henkilo(); henkilo.taytaAkuAnkanTiedoilla(); henkilo.rekisteroi(); 
    Kaavio kaavio = new Kaavio(henkilo.getTunnusNro()); kaavio.rekisteroi(); 
    seurantakaaviot.lisaa(kaavio); 
    Datapiste dat1 = new Datapiste(); dat1.taytaDatapiste(kaavio.getTunnusNro()); dat1.rekisteroi(); 
    Datapiste dat2 = new Datapiste(); dat2.taytaDatapiste(kaavio.getTunnusNro()); dat2.rekisteroi(); 
    Datapiste dat3 = new Datapiste(); dat3.taytaDatapiste(kaavio.getTunnusNro()); dat3.rekisteroi(); 
    seurantakaaviot.lisaa(dat1); 
    seurantakaaviot.lisaa(dat2); 
    seurantakaaviot.lisaa(dat3); 
    List<Datapiste> loytyneet; 
    loytyneet = seurantakaaviot.annaDatapisteet(kaavio); 
    assertEquals("From: Seurantakaaviot line: 183", 3, loytyneet.size()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testPoista195 */
  @Test
  public void testPoista195() {    // Seurantakaaviot: 195
    Seurantakaaviot seurantakaaviot = new Seurantakaaviot(); 
    Henkilo henkilo1 = new Henkilo(); henkilo1.rekisteroi(); seurantakaaviot.lisaa(henkilo1); 
    Henkilo henkilo2 = new Henkilo(); henkilo2.rekisteroi(); seurantakaaviot.lisaa(henkilo2); 
    Henkilo henkilo3 = new Henkilo(); henkilo3.rekisteroi(); seurantakaaviot.lisaa(henkilo3); 
    Kaavio kaavio1 = new Kaavio(henkilo1.getTunnusNro()); kaavio1.rekisteroi(); 
    Kaavio kaavio2 = new Kaavio(henkilo1.getTunnusNro()); kaavio2.rekisteroi(); 
    Kaavio kaavio3 = new Kaavio(henkilo2.getTunnusNro()); kaavio3.rekisteroi(); 
    seurantakaaviot.lisaa(kaavio1); 
    seurantakaaviot.lisaa(kaavio2); 
    seurantakaaviot.lisaa(kaavio3); 
    Datapiste dat1 = new Datapiste(kaavio1.getTunnusNro()); seurantakaaviot.lisaa(dat1); 
    Datapiste dat2 = new Datapiste(kaavio1.getTunnusNro()); seurantakaaviot.lisaa(dat2); 
    Datapiste dat3 = new Datapiste(kaavio3.getTunnusNro()); seurantakaaviot.lisaa(dat3); 
    Datapiste dat4 = new Datapiste(kaavio1.getTunnusNro()); seurantakaaviot.lisaa(dat4); 
    Datapiste dat5 = new Datapiste(kaavio3.getTunnusNro()); seurantakaaviot.lisaa(dat5); 
    assertEquals("From: Seurantakaaviot line: 212", 3, seurantakaaviot.getHenkiloita()); 
    assertEquals("From: Seurantakaaviot line: 213", 3, seurantakaaviot.getKaavioita()); 
    assertEquals("From: Seurantakaaviot line: 214", 5, seurantakaaviot.getDatapisteita()); 
    seurantakaaviot.poista(henkilo1); 
    assertEquals("From: Seurantakaaviot line: 216", 2, seurantakaaviot.getHenkiloita()); 
    assertEquals("From: Seurantakaaviot line: 217", 1, seurantakaaviot.getKaavioita()); 
    assertEquals("From: Seurantakaaviot line: 218", 2, seurantakaaviot.getDatapisteita()); 
    seurantakaaviot.poista(henkilo3); 
    assertEquals("From: Seurantakaaviot line: 220", 1, seurantakaaviot.getHenkiloita()); 
    assertEquals("From: Seurantakaaviot line: 221", 1, seurantakaaviot.getKaavioita()); 
    assertEquals("From: Seurantakaaviot line: 222", 2, seurantakaaviot.getDatapisteita()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testPoista239 */
  @Test
  public void testPoista239() {    // Seurantakaaviot: 239
    Seurantakaaviot seurantakaaviot = new Seurantakaaviot(); 
    Datapiste dat1 = new Datapiste(); 
    Datapiste dat2 = new Datapiste(); 
    Datapiste dat3 = new Datapiste(); 
    Datapiste dat4 = new Datapiste(); 
    Datapiste dat5 = new Datapiste(); 
    seurantakaaviot.lisaa(dat1); 
    seurantakaaviot.lisaa(dat2); 
    seurantakaaviot.lisaa(dat3); 
    seurantakaaviot.lisaa(dat4); 
    seurantakaaviot.lisaa(dat5); 
    assertEquals("From: Seurantakaaviot line: 252", 5, seurantakaaviot.getDatapisteita()); 
    seurantakaaviot.poista(dat1); 
    assertEquals("From: Seurantakaaviot line: 254", 4, seurantakaaviot.getDatapisteita()); 
    seurantakaaviot.poista(dat2); 
    seurantakaaviot.poista(dat3); 
    seurantakaaviot.poista(dat5); 
    assertEquals("From: Seurantakaaviot line: 258", 1, seurantakaaviot.getDatapisteita()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testPoista270 */
  @Test
  public void testPoista270() {    // Seurantakaaviot: 270
    Seurantakaaviot seurantakaaviot = new Seurantakaaviot(); 
    Kaavio kaavio1 = new Kaavio(); kaavio1.rekisteroi(); 
    Kaavio kaavio2 = new Kaavio(); kaavio2.rekisteroi(); 
    seurantakaaviot.lisaa(kaavio1); 
    seurantakaaviot.lisaa(kaavio2); 
    Datapiste dat1 = new Datapiste(kaavio1.getTunnusNro()); 
    Datapiste dat2 = new Datapiste(kaavio1.getTunnusNro()); 
    Datapiste dat3 = new Datapiste(kaavio2.getTunnusNro()); 
    Datapiste dat4 = new Datapiste(kaavio1.getTunnusNro()); 
    Datapiste dat5 = new Datapiste(kaavio2.getTunnusNro()); 
    seurantakaaviot.lisaa(dat1); 
    seurantakaaviot.lisaa(dat2); 
    seurantakaaviot.lisaa(dat3); 
    seurantakaaviot.lisaa(dat4); 
    seurantakaaviot.lisaa(dat5); 
    assertEquals("From: Seurantakaaviot line: 287", 2, seurantakaaviot.getKaavioita()); 
    assertEquals("From: Seurantakaaviot line: 288", 5, seurantakaaviot.getDatapisteita()); 
    seurantakaaviot.poista(kaavio1); 
    assertEquals("From: Seurantakaaviot line: 290", 1, seurantakaaviot.getKaavioita()); 
    assertEquals("From: Seurantakaaviot line: 291", 2, seurantakaaviot.getDatapisteita()); 
    seurantakaaviot.poista(kaavio2); 
    assertEquals("From: Seurantakaaviot line: 293", 0, seurantakaaviot.getKaavioita()); 
    assertEquals("From: Seurantakaaviot line: 294", 0, seurantakaaviot.getDatapisteita()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnnaKaaviot345 */
  @Test
  public void testAnnaKaaviot345() {    // Seurantakaaviot: 345
    Seurantakaaviot seurantakaaviot = new Seurantakaaviot(); 
    Henkilo henkilo1 = new Henkilo(); henkilo1.rekisteroi(); 
    Henkilo henkilo2 = new Henkilo(); henkilo2.rekisteroi(); 
    Henkilo henkilo3 = new Henkilo(); henkilo3.rekisteroi(); 
    Henkilo henkilo5 = new Henkilo(); henkilo5.rekisteroi(); 
    seurantakaaviot.lisaa(henkilo1); 
    seurantakaaviot.lisaa(henkilo2); 
    seurantakaaviot.lisaa(henkilo3); 
    seurantakaaviot.lisaa(henkilo5); 
    Kaavio kaavio21 = new Kaavio(henkilo2.getTunnusNro()); seurantakaaviot.lisaa(kaavio21); 
    Kaavio kaavio11 = new Kaavio(henkilo1.getTunnusNro()); seurantakaaviot.lisaa(kaavio11); 
    Kaavio kaavio22 = new Kaavio(henkilo2.getTunnusNro()); seurantakaaviot.lisaa(kaavio22); 
    Kaavio kaavio12 = new Kaavio(henkilo1.getTunnusNro()); seurantakaaviot.lisaa(kaavio12); 
    Kaavio kaavio23 = new Kaavio(henkilo2.getTunnusNro()); seurantakaaviot.lisaa(kaavio23); 
    Kaavio kaavio51 = new Kaavio(henkilo5.getTunnusNro()); seurantakaaviot.lisaa(kaavio51); 
    List<Kaavio> loytyneet; 
    loytyneet = seurantakaaviot.annaKaaviot(henkilo3); 
    assertEquals("From: Seurantakaaviot line: 365", 0, loytyneet.size()); 
    loytyneet = seurantakaaviot.annaKaaviot(henkilo1); 
    assertEquals("From: Seurantakaaviot line: 367", 2, loytyneet.size()); 
    loytyneet = seurantakaaviot.annaKaaviot(henkilo5); 
    assertEquals("From: Seurantakaaviot line: 369", 1, loytyneet.size()); 
    assertEquals("From: Seurantakaaviot line: 370", true, loytyneet.get(0) == kaavio51); 
    loytyneet = seurantakaaviot.annaKaaviot(henkilo2); 
    assertEquals("From: Seurantakaaviot line: 372", 3, loytyneet.size()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnnaDatapisteet385 */
  @Test
  public void testAnnaDatapisteet385() {    // Seurantakaaviot: 385
    Seurantakaaviot seurantakaaviot = new Seurantakaaviot(); 
    Henkilo henkilo = new Henkilo(); henkilo.rekisteroi(); 
    seurantakaaviot.lisaa(henkilo); 
    Kaavio kaavio1 = new Kaavio(henkilo.getTunnusNro()); kaavio1.rekisteroi(); 
    Kaavio kaavio2 = new Kaavio(henkilo.getTunnusNro()); kaavio2.rekisteroi(); 
    seurantakaaviot.lisaa(kaavio1); 
    seurantakaaviot.lisaa(kaavio2); 
    Datapiste dat1 = new Datapiste(kaavio1.getTunnusNro()); 
    Datapiste dat2 = new Datapiste(kaavio1.getTunnusNro()); 
    Datapiste dat3 = new Datapiste(kaavio2.getTunnusNro()); 
    Datapiste dat4 = new Datapiste(kaavio1.getTunnusNro()); 
    Datapiste dat5 = new Datapiste(kaavio2.getTunnusNro()); 
    seurantakaaviot.lisaa(dat1); 
    seurantakaaviot.lisaa(dat2); 
    seurantakaaviot.lisaa(dat3); 
    seurantakaaviot.lisaa(dat4); 
    seurantakaaviot.lisaa(dat5); 
    List<Datapiste> d = seurantakaaviot.annaDatapisteet(kaavio1); 
    assertEquals("From: Seurantakaaviot line: 405", 3, d.size()); 
    d = seurantakaaviot.annaDatapisteet(kaavio2); 
    assertEquals("From: Seurantakaaviot line: 407", 2, d.size()); 
  } // Generated by ComTest END
}