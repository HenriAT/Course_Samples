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
 * - pitää yllä varsinaista datapisteiden kokoelmaa,
 * - lukee ja kirjoittaa datapisteiden tiedostoon
 * @author Henri
 * @version 22.4.2021
 *
 */
public class Datapisteet implements Iterable<Datapiste> {

    private Collection<Datapiste> alkiot = new ArrayList<Datapiste>();
    private boolean muutettu = false;
    
    /**
     * Alustaminen
     */
    public Datapisteet() {
        // ei tarvitse tehdä mitään
    }
    
    
    /**
     * @return alkoiden määrä
     */
    public int getLkm() {
        return alkiot.size();
    }
    
    
    /**
     * Lisää uuden datapisteet 
     * @param datapiste lisättävä datapiste
     * @example
     * <pre name="test">
     * Datapisteet datapisteet = new Datapisteet();
     * Datapiste dat1 = new Datapiste(1);
     * Datapiste dat2 = new Datapiste(1);
     * Datapiste dat3 = new Datapiste(2);
     * Datapiste dat4 = new Datapiste(1);
     * Datapiste dat5 = new Datapiste(2);
     * datapisteet.lisaa(dat1);
     * datapisteet.lisaa(dat2);
     * datapisteet.getLkm() === 2;
     * datapisteet.lisaa(dat3);
     * datapisteet.lisaa(dat4);
     * datapisteet.lisaa(dat5);
     * datapisteet.getLkm() === 5;
     * </pre>
     */
    public void lisaa(Datapiste datapiste) {
        alkiot.add(datapiste);
        muutettu = true;
    }
    
    
    /**
     * Korvaa datapisteen tietorakenteessa.  Ottaa datapisteen omistukseensa.
     * Etsitään samalla tunnusnumerolla oleva datapiste. Jos ei löydy,
     * niin lisätään uutena datapisteenä.
     * @param datapiste lisättävän datapisteen viite (Huom! tietorakenne muuttuu omistajaksi)
     * @example
     * <pre name="test">
     * #THROWS CloneNotSupportedException
     * #import java.util.List;
     * Datapisteet datapisteet = new Datapisteet();
     * Datapiste dat1 = new Datapiste(1); dat1.rekisteroi();
     * Datapiste dat2 = new Datapiste(2); dat2.rekisteroi();
     * datapisteet.korvaaTaiLisaa(dat1); datapisteet.getLkm() === 1;
     * datapisteet.korvaaTaiLisaa(dat2); datapisteet.getLkm() === 2;
     * Datapiste dat3 = dat1.clone();
     * datapisteet.korvaaTaiLisaa(dat3); datapisteet.getLkm() === 2;
     * List<Datapiste> d = datapisteet.annaDatapisteet(1);
     * d.size() === 1;
     * d.get(0) === dat3;
     * </pre>
     */
    public void korvaaTaiLisaa(Datapiste datapiste) {
        int id = datapiste.getTunnusNro();
        for (Datapiste d : alkiot) {
            if (d.getTunnusNro() == id) {
                ((ArrayList<Datapiste>) alkiot).set(((ArrayList<Datapiste>) alkiot).indexOf(d), datapiste);
                muutettu = true;
                return;
            }
        }
        lisaa(datapiste);
    }
    
    
    /**
     * Poistaa valitun datapisteen
     * @param datapiste poistettava datapiste
     * @return jos löytyi poistettava tietue
     * @example
     * <pre name="test">
     * #import java.util.List;
     * Datapisteet datapisteet = new Datapisteet();
     * Datapiste dat1 = new Datapiste(1);
     * Datapiste dat2 = new Datapiste(1);
     * Datapiste dat3 = new Datapiste(2);
     * Datapiste dat4 = new Datapiste(1);
     * Datapiste dat5 = new Datapiste(2);
     * datapisteet.lisaa(dat1);
     * datapisteet.lisaa(dat2);
     * datapisteet.lisaa(dat3);
     * datapisteet.lisaa(dat5);
     * datapisteet.poista(dat4) === false; datapisteet.getLkm() === 4;
     * datapisteet.poista(dat1) === true; datapisteet.getLkm() === 3;
     * List<Datapiste> d = datapisteet.annaDatapisteet(1);
     * d.size() === 1;
     * d.get(0) === dat2;
     * </pre>
     */
    public boolean poista(Datapiste datapiste) {
        boolean ret = alkiot.remove(datapiste);
        if (ret) muutettu = true;
        return ret;
    }
    
    
    /**
     * Poistaa kaikki tietyn kaavion datapisteet
     * @param tunnusNro tunnusnumero kaaviolle, jonka datapisteet poistetaan
     * @return kuinka monta datapistettä poistettiin
     * @example
     * <pre name="test">
     * #import java.util.List;
     * Datapisteet datapisteet = new Datapisteet();
     * Datapiste dat1 = new Datapiste(1);
     * Datapiste dat2 = new Datapiste(1);
     * Datapiste dat3 = new Datapiste(2);
     * Datapiste dat4 = new Datapiste(1);
     * Datapiste dat5 = new Datapiste(2); 
     * datapisteet.lisaa(dat1);
     * datapisteet.lisaa(dat2);
     * datapisteet.lisaa(dat3);
     * datapisteet.lisaa(dat4);
     * datapisteet.lisaa(dat5);
     * datapisteet.poistaKaavionDatapisteet(1) === 3; datapisteet.getLkm() === 2;
     * datapisteet.poistaKaavionDatapisteet(3) === 0; datapisteet.getLkm() === 2;
     * List<Datapiste> d = datapisteet.annaDatapisteet(2);
     * d.size() === 2;
     * </pre>
     */
    public int poistaKaavionDatapisteet(int tunnusNro) {
        int n = 0;
        for (Iterator<Datapiste> it = alkiot.iterator(); it.hasNext();) {
            Datapiste dat = it.next();
            if (dat.getKaavioNro() == tunnusNro) {
                it.remove();
                n++;
            }
        }
        if (n > 0) muutettu = true;
        return n;
    }
    
    
    /**
     * Etsitään kaavion kaikki datapisteet
     * @param tunnusNro tunnusnumero kaaviolle, jonka datapisteitä haetaan
     * @return tietorakenne, jossa on viiteet löydetteyihin datapisteisiin
     * <pre name="test">
     * #import java.util.List;
     * Datapisteet datapisteet = new Datapisteet();
     * Datapiste dat1 = new Datapiste(); dat1.taytaDatapiste(1);
     * Datapiste dat2 = new Datapiste(); dat2.taytaDatapiste(1);
     * Datapiste dat3 = new Datapiste(); dat3.taytaDatapiste(2);
     * Datapiste dat4 = new Datapiste(); dat4.taytaDatapiste(1);
     * Datapiste dat5 = new Datapiste(); dat5.taytaDatapiste(2);
     * datapisteet.lisaa(dat1);
     * datapisteet.lisaa(dat2);
     * datapisteet.lisaa(dat3);
     * datapisteet.lisaa(dat4);
     * datapisteet.lisaa(dat5);
     * List<Datapiste> d = datapisteet.annaDatapisteet(1);
     * d.size() === 3;
     * d = datapisteet.annaDatapisteet(2);
     * d.size() === 2;
     * </pre>
     */
    public List<Datapiste> annaDatapisteet(int tunnusNro) {
        List<Datapiste> loydetyt = new ArrayList<Datapiste>();
        for (Datapiste datapiste : alkiot) {
            if (datapiste.getKaavioNro() == tunnusNro) loydetyt.add(datapiste);
        }
        Collections.sort(loydetyt);
        return loydetyt;
    }
    
    
    /**
     * Luetaan datapisteet aikaisemmin annetun nimisestä tiedostosta
     * @throws SailoException jos tulee poikkeus
     */
     public void lueTiedostosta() throws SailoException {
         String nimi = "datapisteet.dat";
         File ftied = new File(nimi);
         
         try (Scanner fi = new Scanner(new FileInputStream(ftied))) {
             while (fi.hasNext()) {
                 String s = "";
                 s = fi.nextLine();
                 Datapiste datapiste = new Datapiste();
                 datapiste.parse(s);
                 lisaa(datapiste);
             }
         muutettu = false;
         } catch (FileNotFoundException e) {
             throw new SailoException("Ei saa luettua tiedostoa " + nimi);
         }
     }
    
     
     /**
      * Tallentaa datapisteet tiedostoon
      * @throws SailoException jos tallennus epäonnistuu
      */
     public void tallenna() throws SailoException {
         if (!muutettu) return;
         File ftied = new File("datapisteet.dat");
         try (PrintStream fo = new PrintStream(new FileOutputStream(ftied, false))) {
             for (Datapiste datapiste : alkiot) {
                 fo.println(datapiste.toString());
             }
         } catch (FileNotFoundException ex) {
             throw new SailoException("Tiedosto " + ftied.getName() + " ei aukea");
         }
     }
    
     
     @Override
     public Iterator<Datapiste> iterator() {
         return alkiot.iterator();
     }
    
     
    /**
     * 
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Datapisteet datapisteet = new Datapisteet();
        Datapiste dat1 = new Datapiste();
        dat1.taytaDatapiste(1);
        Datapiste dat2 = new Datapiste();
        dat2.taytaDatapiste(1);
        Datapiste dat3 = new Datapiste();
        dat3.taytaDatapiste(2);
        Datapiste dat4 = new Datapiste();
        dat4.taytaDatapiste(1);
        
        datapisteet.lisaa(dat1);
        datapisteet.lisaa(dat2);
        datapisteet.lisaa(dat3);
        datapisteet.lisaa(dat4);
        
        System.out.println("=========================== Datapisteet testi ===========================");
        
        List<Datapiste> datapisteet2 = datapisteet.annaDatapisteet(1);
        
        for (Datapiste datapiste : datapisteet2) {
            System.out.print(datapiste.getKaavioNro() + " ");
            datapiste.tulosta(System.out);
        }
        
        try {
            datapisteet.tallenna();
        } catch (SailoException e) {
            e.printStackTrace();
        }
        
        
    }


}
