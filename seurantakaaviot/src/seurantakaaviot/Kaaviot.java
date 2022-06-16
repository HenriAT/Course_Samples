package seurantakaaviot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * - pitää yllä varsinaista kaavioiden kokoelmaa, eli
 *   osaa lisätä ja poistaa kaavioita
 * - lukee ja kirjoittaa kaavioiden tiedostoon
 * @author Henri
 * @version 22.4.2021
 *
 */
public class Kaaviot implements Iterable<Kaavio>{

     private Collection<Kaavio> alkiot = new ArrayList<Kaavio>();
     private boolean muutettu = false;
     
     
     /**
      * Alustaminen
      */
     public Kaaviot() {
         // ei tarvitse tehdä mitään
     }
    
     
     /**
      * 
      * @param kaavio lisättävä kaavio
      */
     public void lisaa(Kaavio kaavio) {
         alkiot.add(kaavio);
         muutettu = true;
     }
     
     
     /**
      * @return alkioiden lukumäärä
      */
     public int getLkm() {
         return alkiot.size();
     }
     
     
     
    /**
     * Korvaa kaavion tietorakenteessa.  Ottaa kaavion omistukseensa.
     * Etsitään samalla tunnusnumerolla oleva kaavio. Jos ei löydy,
     * niin lisätään uutena kaaviona.
     * @param kaavio lisättävän kaavion viite (Huom! tietorakenne muuttuu omistajaksi)
     * @example
     * <pre name="test">
     * #THROWS CloneNotSupportedException
     * #import java.util.List;
     * Kaaviot kaaviot = new Kaaviot();
     * Kaavio kaavio1 = new Kaavio(); kaavio1.taytaKaavioPainonSeurannalla(1); kaavio1.rekisteroi();
     * Kaavio kaavio2 = new Kaavio(); kaavio2.taytaKaavioPainonSeurannalla(2); kaavio2.rekisteroi();
     * kaaviot.korvaaTaiLisaa(kaavio1); kaaviot.getLkm() === 1;
     * kaaviot.korvaaTaiLisaa(kaavio2); kaaviot.getLkm() === 2;
     * Kaavio kaavio3 = kaavio1.clone();
     * kaaviot.korvaaTaiLisaa(kaavio3); kaaviot.getLkm() === 2;
     * List<Kaavio> k = kaaviot.annaKaaviot(1);
     * k.size() === 1;
     * k.get(0) === kaavio3;
     * </pre>
      */
     public void korvaaTaiLisaa(Kaavio kaavio) {
         int id = kaavio.getTunnusNro();
         for (Kaavio k : alkiot) {
             if (k.getTunnusNro() == id) {
                 ((ArrayList<Kaavio>) alkiot).set(((ArrayList<Kaavio>) alkiot).indexOf(k), kaavio);
                 muutettu = true;
                 return;
             }
         }
         lisaa(kaavio);
     }
     
     
     /**
      * Poistaa valitun kaavion
      * @param kaavio poistettava datapiste
      * @return jos löytyi poistettava tietue
      * @example
      * <pre name="test">
      * #import java.util.List;
      * Kaaviot kaaviot = new Kaaviot();
      * Kaavio kaavio1 = new Kaavio(); kaavio1.taytaKaavioPainonSeurannalla(1);
      * Kaavio kaavio2 = new Kaavio(); kaavio2.taytaKaavioPainonSeurannalla(1);
      * Kaavio kaavio3 = new Kaavio(); kaavio3.taytaKaavioPainonSeurannalla(2);
      * Kaavio kaavio4 = new Kaavio(); kaavio4.taytaKaavioPainonSeurannalla(1);
      * Kaavio kaavio5 = new Kaavio(); kaavio5.taytaKaavioPainonSeurannalla(2);
      * kaaviot.lisaa(kaavio1);
      * kaaviot.lisaa(kaavio2);
      * kaaviot.lisaa(kaavio3);
      * kaaviot.lisaa(kaavio5);
      * kaaviot.poista(kaavio4) === false; kaaviot.getLkm() === 4;
      * kaaviot.poista(kaavio5) === true; kaaviot.getLkm() === 3;
      * List<Kaavio> k = kaaviot.annaKaaviot(2);
      * k.size() === 1;
      * k.get(0) === kaavio3;
      * </pre>
      */
     public boolean poista(Kaavio kaavio) {
         boolean ret = alkiot.remove(kaavio);
         if (ret) muutettu = true;
         return ret;
     }
     
     
     /**
      * Poistaa kaikki tietyn henkilön kaaviot
      * @param tunnusNro tunnusnumero henkilölle, jonka kaaviot poistetaan
      * @return kuinka monta kaaviota poistettiin
      * @example
      * <pre name="test">
      * #import java.util.List;
      * Kaaviot kaaviot = new Kaaviot();
      * Kaavio kaavio1 = new Kaavio(); kaavio1.taytaKaavioPainonSeurannalla(1);
      * Kaavio kaavio2 = new Kaavio(); kaavio2.taytaKaavioPainonSeurannalla(1);
      * Kaavio kaavio3 = new Kaavio(); kaavio3.taytaKaavioPainonSeurannalla(2);
      * Kaavio kaavio4 = new Kaavio(); kaavio4.taytaKaavioPainonSeurannalla(3);
      * Kaavio kaavio5 = new Kaavio(); kaavio5.taytaKaavioPainonSeurannalla(2);
      * kaaviot.lisaa(kaavio1);
      * kaaviot.lisaa(kaavio2);
      * kaaviot.lisaa(kaavio3);
      * kaaviot.lisaa(kaavio4);
      * kaaviot.lisaa(kaavio5);
      * kaaviot.poistaHenkilonKaaviot(2) === 2; kaaviot.getLkm() === 3;
      * kaaviot.poistaHenkilonKaaviot(4) === 0; kaaviot.getLkm() === 3;
      * List<Kaavio> k = kaaviot.annaKaaviot(1);
      * k.size() === 2;
      * </pre>
      */
     public int poistaHenkilonKaaviot(int tunnusNro) {
         int n = 0;
         for (Iterator<Kaavio> it = alkiot.iterator(); it.hasNext();) {
             Kaavio kaavio = it.next();
             if (kaavio.getHenkiloNro() == tunnusNro) {
                 it.remove();
                 n++;
             }
         }
         if (n > 0) muutettu = true;
         return n;
     }
     
     
     
     /**
      * Etsitään henkilön kaikki kaaviot
      * @param tunnusNro tunnusnumero henkilölle, jolle kaavioita haetaan
      * @return tietorakenne, jossa on viiteet löydetteyihin kaavioihin
      * @example
      * <pre name="test">
      * #import java.util.*;
      * 
      * Kaaviot kaaviot = new Kaaviot();
      * Kaavio kaavio21 = new Kaavio(2); kaaviot.lisaa(kaavio21);
      * Kaavio kaavio11 = new Kaavio(1); kaaviot.lisaa(kaavio11);
      * Kaavio kaavio22 = new Kaavio(2); kaaviot.lisaa(kaavio22);
      * Kaavio kaavio12 = new Kaavio(1); kaaviot.lisaa(kaavio12);
      * Kaavio kaavio23 = new Kaavio(2); kaaviot.lisaa(kaavio23);
      * Kaavio kaavio51 = new Kaavio(5); kaaviot.lisaa(kaavio51);
      * 
      * List<Kaavio> loytyneet;
      * loytyneet = kaaviot.annaKaaviot(3);
      * loytyneet.size() === 0;
      * loytyneet = kaaviot.annaKaaviot(1);
      * loytyneet.size() === 2;
      * loytyneet.get(0) == kaavio11 === true;
      * loytyneet.get(1) == kaavio12 === true;
      * loytyneet = kaaviot.annaKaaviot(5);
      * loytyneet.size() === 1;
      * loytyneet.get(0) == kaavio51 === true;
      * </pre>
      */
     public List<Kaavio> annaKaaviot(int tunnusNro) {
         List<Kaavio> loydetyt = new ArrayList<Kaavio>();
         for(Kaavio kaavio : alkiot) {
             if (kaavio.getHenkiloNro() == tunnusNro) loydetyt.add(kaavio);
         }
         Collections.sort(loydetyt); 
         return loydetyt;
     }
     
     
     /**
      * Luetaan kaaviot aikaisemmin annetun nimisestä tiedostosta
      * @throws SailoException jos tulee poikkeus
      */
      public void lueTiedostosta() throws SailoException {
          String nimi = "kaaviot.dat";
          File ftied = new File(nimi);
          
          try (Scanner fi = new Scanner(new FileInputStream(ftied))) {
              while (fi.hasNext()) {
                  String s = "";
                  s = fi.nextLine();
                  Kaavio kaavio = new Kaavio();
                  kaavio.parse(s);
                  lisaa(kaavio);
              }
          muutettu = false;
          } catch (FileNotFoundException e) {
              throw new SailoException("Ei saa luettua tiedostoa " + nimi);
          }
      }
     
      
      /**
       * Tallentaa kaaviot tiedostoon
       * @throws SailoException jos tallennus epäonnistuu
       */
      public void tallenna() throws SailoException {
          if (!muutettu) return;
          File ftied = new File("kaaviot.dat");
          try (PrintStream fo = new PrintStream(new FileOutputStream(ftied, false))) {
              for (Kaavio kaavio : alkiot) {
                  fo.println(kaavio.toString());
              }
          } catch (FileNotFoundException ex) {
              throw new SailoException("Tiedosto " + ftied.getName() + " ei aukea");
          }
      }
     
      
      @Override
      public Iterator<Kaavio> iterator() {
          return alkiot.iterator();
      }
     
      
    /**
     * Testiohjelma kaavioille
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Kaaviot kaaviot = new Kaaviot();
        
        try {
            kaaviot.lueTiedostosta();
        } catch (SailoException ex) {
            System.err.println(ex.getMessage());
        }
        
        Kaavio kaavio1 = new Kaavio();
        kaavio1.rekisteroi();
        kaavio1.taytaKaavioPainonSeurannalla(2);
        Kaavio kaavio2 = new Kaavio();
        kaavio2.rekisteroi();
        kaavio2.taytaKaavioPainonSeurannalla(1);
        Kaavio kaavio3 = new Kaavio();
        kaavio3.rekisteroi();
        kaavio3.taytaKaavioPainonSeurannalla(2);
        Kaavio kaavio4 = new Kaavio();
        kaavio4.rekisteroi();
        kaavio4.taytaKaavioPainonSeurannalla(2);
        
        kaaviot.lisaa(kaavio1);
        kaaviot.lisaa(kaavio2);
        kaaviot.lisaa(kaavio3);
        kaaviot.lisaa(kaavio4);
        
        System.out.println("=========================== Kaaviot testi ===========================");
        
        List<Kaavio> kaaviot2 = kaaviot.annaKaaviot(2);
        
        for (Kaavio kaavio : kaaviot2) {
            System.out.print(kaavio.getHenkiloNro() + " ");
            kaavio.tulosta(System.out);
        }
        
        try {
            kaaviot.tallenna();
        } catch (SailoException e) {
            e.printStackTrace();
        }
    }

}
