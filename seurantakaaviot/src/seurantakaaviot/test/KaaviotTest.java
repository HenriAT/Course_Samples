package seurantakaaviot.test;
// Generated by ComTest BEGIN
import java.util.List;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;
import seurantakaaviot.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.04.23 00:17:56 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class KaaviotTest {



  // Generated by ComTest BEGIN
  /** 
   * testKorvaaTaiLisaa62 
   * @throws CloneNotSupportedException when error
   */
  @Test
  public void testKorvaaTaiLisaa62() throws CloneNotSupportedException {    // Kaaviot: 62
    Kaaviot kaaviot = new Kaaviot(); 
    Kaavio kaavio1 = new Kaavio(); kaavio1.taytaKaavioPainonSeurannalla(1); kaavio1.rekisteroi(); 
    Kaavio kaavio2 = new Kaavio(); kaavio2.taytaKaavioPainonSeurannalla(2); kaavio2.rekisteroi(); 
    kaaviot.korvaaTaiLisaa(kaavio1); assertEquals("From: Kaaviot line: 68", 1, kaaviot.getLkm()); 
    kaaviot.korvaaTaiLisaa(kaavio2); assertEquals("From: Kaaviot line: 69", 2, kaaviot.getLkm()); 
    Kaavio kaavio3 = kaavio1.clone(); 
    kaaviot.korvaaTaiLisaa(kaavio3); assertEquals("From: Kaaviot line: 71", 2, kaaviot.getLkm()); 
    List<Kaavio> k = kaaviot.annaKaaviot(1); 
    assertEquals("From: Kaaviot line: 73", 1, k.size()); 
    assertEquals("From: Kaaviot line: 74", kaavio3, k.get(0)); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testPoista95 */
  @Test
  public void testPoista95() {    // Kaaviot: 95
    Kaaviot kaaviot = new Kaaviot(); 
    Kaavio kaavio1 = new Kaavio(); kaavio1.taytaKaavioPainonSeurannalla(1); 
    Kaavio kaavio2 = new Kaavio(); kaavio2.taytaKaavioPainonSeurannalla(1); 
    Kaavio kaavio3 = new Kaavio(); kaavio3.taytaKaavioPainonSeurannalla(2); 
    Kaavio kaavio4 = new Kaavio(); kaavio4.taytaKaavioPainonSeurannalla(1); 
    Kaavio kaavio5 = new Kaavio(); kaavio5.taytaKaavioPainonSeurannalla(2); 
    kaaviot.lisaa(kaavio1); 
    kaaviot.lisaa(kaavio2); 
    kaaviot.lisaa(kaavio3); 
    kaaviot.lisaa(kaavio5); 
    assertEquals("From: Kaaviot line: 107", false, kaaviot.poista(kaavio4)); assertEquals("From: Kaaviot line: 107", 4, kaaviot.getLkm()); 
    assertEquals("From: Kaaviot line: 108", true, kaaviot.poista(kaavio5)); assertEquals("From: Kaaviot line: 108", 3, kaaviot.getLkm()); 
    List<Kaavio> k = kaaviot.annaKaaviot(2); 
    assertEquals("From: Kaaviot line: 110", 1, k.size()); 
    assertEquals("From: Kaaviot line: 111", kaavio3, k.get(0)); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testPoistaHenkilonKaaviot126 */
  @Test
  public void testPoistaHenkilonKaaviot126() {    // Kaaviot: 126
    Kaaviot kaaviot = new Kaaviot(); 
    Kaavio kaavio1 = new Kaavio(); kaavio1.taytaKaavioPainonSeurannalla(1); 
    Kaavio kaavio2 = new Kaavio(); kaavio2.taytaKaavioPainonSeurannalla(1); 
    Kaavio kaavio3 = new Kaavio(); kaavio3.taytaKaavioPainonSeurannalla(2); 
    Kaavio kaavio4 = new Kaavio(); kaavio4.taytaKaavioPainonSeurannalla(3); 
    Kaavio kaavio5 = new Kaavio(); kaavio5.taytaKaavioPainonSeurannalla(2); 
    kaaviot.lisaa(kaavio1); 
    kaaviot.lisaa(kaavio2); 
    kaaviot.lisaa(kaavio3); 
    kaaviot.lisaa(kaavio4); 
    kaaviot.lisaa(kaavio5); 
    assertEquals("From: Kaaviot line: 139", 2, kaaviot.poistaHenkilonKaaviot(2)); assertEquals("From: Kaaviot line: 139", 3, kaaviot.getLkm()); 
    assertEquals("From: Kaaviot line: 140", 0, kaaviot.poistaHenkilonKaaviot(4)); assertEquals("From: Kaaviot line: 140", 3, kaaviot.getLkm()); 
    List<Kaavio> k = kaaviot.annaKaaviot(1); 
    assertEquals("From: Kaaviot line: 142", 2, k.size()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnnaKaaviot165 */
  @Test
  public void testAnnaKaaviot165() {    // Kaaviot: 165
    Kaaviot kaaviot = new Kaaviot(); 
    Kaavio kaavio21 = new Kaavio(2); kaaviot.lisaa(kaavio21); 
    Kaavio kaavio11 = new Kaavio(1); kaaviot.lisaa(kaavio11); 
    Kaavio kaavio22 = new Kaavio(2); kaaviot.lisaa(kaavio22); 
    Kaavio kaavio12 = new Kaavio(1); kaaviot.lisaa(kaavio12); 
    Kaavio kaavio23 = new Kaavio(2); kaaviot.lisaa(kaavio23); 
    Kaavio kaavio51 = new Kaavio(5); kaaviot.lisaa(kaavio51); 
    List<Kaavio> loytyneet; 
    loytyneet = kaaviot.annaKaaviot(3); 
    assertEquals("From: Kaaviot line: 178", 0, loytyneet.size()); 
    loytyneet = kaaviot.annaKaaviot(1); 
    assertEquals("From: Kaaviot line: 180", 2, loytyneet.size()); 
    assertEquals("From: Kaaviot line: 181", true, loytyneet.get(0) == kaavio11); 
    assertEquals("From: Kaaviot line: 182", true, loytyneet.get(1) == kaavio12); 
    loytyneet = kaaviot.annaKaaviot(5); 
    assertEquals("From: Kaaviot line: 184", 1, loytyneet.size()); 
    assertEquals("From: Kaaviot line: 185", true, loytyneet.get(0) == kaavio51); 
  } // Generated by ComTest END
}