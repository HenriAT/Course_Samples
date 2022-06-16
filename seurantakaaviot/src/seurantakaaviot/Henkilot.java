package seurantakaaviot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import fi.jyu.mit.ohj2.WildChars;

/**
 * - pitää yllä varsinaista henkilörekisteriä, eli
 *   osaa lisätä ja poistaa henkilön
 * - lukee ja kirjoittaa henkilöstön tiedostoon
 * - osaa etsiä ja lajitella
 * @author Henri
 * @version 22.4.2021
 *
 */
public class Henkilot {
    
    private static final int ALUSTUS_HENKILOITA = 5;
    private int lkm = 0;
    private Henkilo[] alkiot;
    private boolean muutettu = false;
    
    /**
     * Luodaan alustava taulukko
     */
    public Henkilot() {
        alkiot = new Henkilo[ALUSTUS_HENKILOITA];
    }
    
    
    /**
    * Lisää uuden henkilön tietorakenteeseen.  Ottaa jäsenen omistukseensa.
    * @param henkilo lisätävän henkilön viite.  Huom tietorakenne muuttuu omistajaksi
    * @example
    * <pre name="test">
    * Henkilot henkilot = new Henkilot();
    * Henkilo aku1 = new Henkilo(), aku2 = new Henkilo();
    * henkilot.getLkm() === 0;
    * henkilot.lisaa(aku1); henkilot.getLkm() === 1;
    * henkilot.lisaa(aku2); henkilot.getLkm() === 2;
    * henkilot.lisaa(aku1); henkilot.getLkm() === 3;
    * henkilot.anna(0) === aku1;
    * henkilot.anna(1) === aku2;
    * henkilot.anna(2) === aku1;
    * henkilot.anna(1) == aku1 === false;
    * henkilot.anna(1) == aku2 === true;
    * henkilot.anna(3) === aku1; #THROWS IndexOutOfBoundsException 
    * henkilot.lisaa(aku1); henkilot.getLkm() === 4;
    * henkilot.lisaa(aku1); henkilot.getLkm() === 5;
    * henkilot.lisaa(aku1); henkilot.getLkm() === 6
    * </pre>
    */
    public void lisaa(Henkilo henkilo) {
        if (lkm >= alkiot.length) {
            Henkilo[] uusiTaulukko = new Henkilo[lkm + 5];
            int n = alkiot.length;
            for (int i = 0; i < n; i++) {
                uusiTaulukko[i] = alkiot[i];
            }
            alkiot = uusiTaulukko;
        }
        this.alkiot[this.lkm] = henkilo;
        this.lkm++;
        muutettu = true;
    }
    
    
    /**
     * Korvaa henkilön tietorakenteessa.  Ottaa henkilön omistukseensa.
     * Etsitään samalla tunnusnumerolla oleva henkilö. Jos ei löydy,
     * niin lisätään uutena henkilönä.
     * @param henkilo lisättävän henkilön viite (Huom! tietorakenne muuttuu omistajaksi)
     * @example
     * <pre name="test">
     * #THROWS CloneNotSupportedException
     * Henkilot henkilot = new Henkilot();
     * Henkilo henkilo1 = new Henkilo(); henkilo1.taytaAkuAnkanTiedoilla(); henkilo1.rekisteroi();
     * Henkilo henkilo2 = new Henkilo(); henkilo2.taytaAkuAnkanTiedoilla(); henkilo2.rekisteroi();
     * henkilot.korvaaTaiLisaa(henkilo1); henkilot.getLkm() === 1;
     * henkilot.korvaaTaiLisaa(henkilo2); henkilot.getLkm() === 2;
     * Henkilo henkilo3 = henkilo1.clone();
     * henkilot.korvaaTaiLisaa(henkilo3); henkilot.getLkm() === 2;
     * </pre>
     */
    public void korvaaTaiLisaa(Henkilo henkilo) {
        int id = henkilo.getTunnusNro();
        for (int i = 0; i < this.lkm; i++) {
            if (alkiot[i].getTunnusNro() == id) {
                alkiot[i] = henkilo;
                muutettu = true;
                return;
            }
        }
        lisaa(henkilo);
    }
    
    
    
    /** 
     * Poistaa henkilön, jolla on valittu tunnusnumero  
     * @param id poistettavan henkilön tunnusnumero 
     * @return 1 jos poistettiin, 0 jos ei löydy 
     * @example 
     * <pre name="test">   
     * Henkilot henkilot = new Henkilot(); 
     * Henkilo aku1 = new Henkilo(), aku2 = new Henkilo(), aku3 = new Henkilo(); 
     * aku1.rekisteroi(); aku2.rekisteroi(); aku3.rekisteroi(); 
     * int id1 = aku1.getTunnusNro(); 
     * henkilot.lisaa(aku1); henkilot.lisaa(aku2); henkilot.lisaa(aku3); 
     * henkilot.poista(id1+1) === 1; 
     * henkilot.annaId(id1+1) === null; henkilot.getLkm() === 2; 
     * henkilot.poista(id1) === 1; henkilot.getLkm() === 1; 
     * henkilot.poista(id1+3) === 0; henkilot.getLkm() === 1; 
     * </pre> 
     */ 
     public int poista(int id) { 
     int ind = etsiId(id); 
     if (ind < 0) return 0; 
     lkm--; 
     for (int i = ind; i < lkm; i++) 
         alkiot[i] = alkiot[i + 1]; 
         alkiot[lkm] = null; 
         muutettu = true; 
         return 1; 
     } 
    
     
    /** 
     * Etsii henkilön id:n perusteella 
     * @param id tunnusnumero, jonka mukaan etsitään 
     * @return henkilö, jolla etsittävä id tai null 
     * <pre name="test">   
     * Henkilot henkilot = new Henkilot(); 
     * Henkilo aku1 = new Henkilo(), aku2 = new Henkilo(), aku3 = new Henkilo(); 
     * aku1.rekisteroi(); aku2.rekisteroi(); aku3.rekisteroi(); 
     * int id1 = aku1.getTunnusNro(); 
     * henkilot.lisaa(aku1); henkilot.lisaa(aku2); henkilot.lisaa(aku3); 
     * henkilot.annaId(id1) == aku1 === true; 
     * henkilot.annaId(id1+1) == aku2 === true; 
     * henkilot.annaId(id1+2) == aku3 === true; 
     * </pre> 
     */ 
     public Henkilo annaId(int id) { 
         for (int i = 0; i < lkm; i++) { 
             if (alkiot[i].getTunnusNro() == id) return alkiot[i]; 
         } 
         return null; 
     } 
      
      
      
     /** 
      * Etsii henkilön indeksin id:n perusteella 
      * @param id tunnusnumero, jonka mukaan etsitään 
      * @return löytyneen henkilön indeksi tai -1 jos ei löydy 
      * <pre name="test"> 
      * Henkilot henkilot = new Henkilot(); 
      * Henkilo aku1 = new Henkilo(), aku2 = new Henkilo(), aku3 = new Henkilo(); 
      * aku1.rekisteroi(); aku2.rekisteroi(); aku3.rekisteroi(); 
      * int id1 = aku1.getTunnusNro(); 
      * henkilot.lisaa(aku1); henkilot.lisaa(aku2); henkilot.lisaa(aku3); 
      * henkilot.etsiId(id1+1) === 1; 
      * henkilot.etsiId(id1+2) === 2; 
      * </pre> 
      */ 
      public int etsiId(int id) { 
          for (int i = 0; i < lkm; i++) 
              if (alkiot[i].getTunnusNro() == id) return i; 
          return -1; 
      } 
      
    
    
    /**
     * Palauttaa kerhon jäsenten lukumäärän
     * @return jäsenten lukumäärä
     */
    public int getLkm() {
        return this.lkm;
    }
    
    
    /**
    * Luetaan aikaisemmin annetun nimisestä tiedostosta
    * @throws SailoException jos tulee poikkeus
    */
    public void lueTiedostosta() throws SailoException {
        String nimi = "henkilot.dat";
        File ftied = new File(nimi);
        
        try (Scanner fi = new Scanner(new FileInputStream(ftied))) {
            while (fi.hasNext()) {
                String s = "";
                s = fi.nextLine();
                Henkilo henkilo = new Henkilo();
                henkilo.parse(s);
                lisaa(henkilo);
            }
        muutettu = false;
        } catch (FileNotFoundException e) {
            throw new SailoException("Ei saa luettua tiedostoa " + nimi);
        }
    }
    
    
    /**
     * Tallentaa henkilöt tiedostoon
     * @throws SailoException jos tallennus epäonnistuu
     */
    public void tallenna() throws SailoException {
        if (!muutettu) return;
        File ftied = new File("henkilot.dat");
        try (PrintStream fo = new PrintStream(new FileOutputStream(ftied, false))) {
            for (int i = 0; i < getLkm(); i++) {
                Henkilo henkilo = anna(i);
                fo.println(henkilo.toString());
            }
        } catch (FileNotFoundException ex) {
            throw new SailoException("Tiedosto " + ftied.getName() + " ei aukea");
        }
    }
    
    
    /**
     * Palauttaa viitteen i:teen henkilöön
     * @param i monennenko jäsenen viite halutaan
     * @return viite jäseneen, jonka indeksi on i
     * @throws IndexOutOfBoundsException jos i ei ole sallitulla alueella
     */
    public Henkilo anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || this.lkm <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return this.alkiot[i];
    }
    
    
    
    /** 
     * Palauttaa "taulukossa" hakuehtoon vastaavien henkilöiden viitteet 
     * @param hakuehto hakuehto  
     * @return tietorakenteen löytyneistä henkilöistä
     * @example 
     * <pre name="test">
     * #import java.util.ArrayList;
     * Henkilot henkilot = new Henkilot(); 
     * Henkilo henkilo1 = new Henkilo(); henkilo1.parse("1|Ankka Aku|030201-115H|"); 
     * Henkilo henkilo2 = new Henkilo(); henkilo2.parse("2|Ankka Tupu|030552-123B|"); 
     * Henkilo henkilo3 = new Henkilo(); henkilo3.parse("3|Susi Sepe|121237-121V|"); 
     * Henkilo henkilo4 = new Henkilo(); henkilo4.parse("4|Ankka Iines|030245-115V|"); 
     * Henkilo henkilo5 = new Henkilo(); henkilo5.parse("5|Ankka Roope|091007-408U|"); 
     * henkilot.lisaa(henkilo1); henkilot.lisaa(henkilo2); henkilot.lisaa(henkilo3); henkilot.lisaa(henkilo4); henkilot.lisaa(henkilo5);
     * ArrayList<Henkilo> loytyneet;  
     * loytyneet = (ArrayList<Henkilo>)henkilot.etsi("*s*");  
     * loytyneet.size() === 2;  
     * loytyneet = (ArrayList<Henkilo>)henkilot.etsi(null);  
     * loytyneet.size() === 5; 
     *  
     * loytyneet = (ArrayList<Henkilo>)henkilot.etsi(""); 
     * loytyneet.size() === 5; 
     * </pre> 
     */ 
    public Collection<Henkilo> etsi(String hakuehto) {
        String ehto = "*"; 
        if (hakuehto != null && hakuehto.length() > 0) ehto = hakuehto; 
        List<Henkilo> loytyneet = new ArrayList<Henkilo>(); 
        for (int i = 0; i < lkm; i++) { 
            if (alkiot[i] != null && WildChars.onkoSamat(alkiot[i].getNimi(), ehto)) loytyneet.add(alkiot[i]);   
        } 
        Collections.sort(loytyneet); 
        return loytyneet; 
    }
    

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Henkilot henkilot = new Henkilot();
        
        try {
            henkilot.lueTiedostosta();
        } catch (SailoException ex) {
            System.err.println(ex.getMessage());
        }
        
        Henkilo aku = new Henkilo();
        Henkilo aku2 = new Henkilo();
        aku.rekisteroi();
        aku.taytaAkuAnkanTiedoilla();
        aku2.rekisteroi();
        aku2.taytaAkuAnkanTiedoilla();
    
        henkilot.lisaa(aku);
        henkilot.lisaa(aku2);
        
    
        System.out.println("================================ Henkilot testi ================================");
    
        for (int i = 0; i < henkilot.getLkm(); i++) {
            Henkilo henkilo = henkilot.anna(i);
            System.out.println("Jäsen indeksi: " + i);
            henkilo.tulosta(System.out);
        }
        
        try {
            henkilot.tallenna();
        } catch (SailoException e) {
            e.printStackTrace();
        }
    }
}
