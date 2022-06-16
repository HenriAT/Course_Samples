package seurantakaaviot;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;
import kanta.HetuTarkistus;

import static kanta.HetuTarkistus.*;

/**
 * - tietää henkilön tiedot nimi, hetu, puhnro, jne.
 * - osaa tarkistaa kentän oikeellisuuden 
 * - osaa muuttaa 1|Ankka Aku|..| - merkkijonon henkilön tiedoiksi
 * - osaa antaa merkkijonona i:n kentän tiedot
 * - osaa laittaa merkkijonon i:neksi kentäksi
 * @author Henri
 * @version 22.4.2021
 *
 */
public class Henkilo implements Cloneable, Comparable<Henkilo> {

    private int tunnusNro;
    private String nimi              = "";
    private String hetu              = "";
    private String puhelinnumero     = "";
    private String katuosoite        = "";
    private String postinumero       = "";
    private String postitoimipaikka  = "";
    private String sahkoposti        = "";
    private String lisatietoja       = "";
    
    private static int seuraavaNro = 1;
    
    /**
     * Alustetaan kaikki tyhjäksi
     */
    public Henkilo() {
        // Alustuslauseet hoitaa kaiken
    }
    
    
    /**
     * Tulostetaan henkilön tiedot
     * @param out tietovirta, johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d", tunnusNro, 3) + "  " + nimi + "  " + hetu);
        out.println("  " + "Puhelin: " + puhelinnumero);
        out.println("  " + "Osoite: " + katuosoite + "  " + postinumero + " " + postitoimipaikka);
        out.println("  " + "Sähköposti: " + sahkoposti);
        out.println("  " + "Lisätietoja: " + lisatietoja);
    }
    
    
    /**
     * Tulostetaan henkilön tiedot
     * @param os tietovirta, johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    /**
    * Antaa jäsenelle seuraavan rekisterinumeron.
    * @return jäsenen uusi tunnusNro
    * @example
    * <pre name="test">
    * Henkilo aku1 = new Henkilo();
    * aku1.getTunnusNro() === 0;
    * aku1.rekisteroi();
    * Henkilo aku2 = new Henkilo();
    * aku2.rekisteroi();
    * int n1 = aku1.getTunnusNro();
    * int n2 = aku2.getTunnusNro();
    * n1 === n2-1;
    * </pre>
    */
    public int rekisteroi() {
        tunnusNro = seuraavaNro;
        seuraavaNro++;
        return tunnusNro;
    }
    
    
    /**
     * Palauttaa henkilön nimen
     * @return henkilön nimi
     */
    public String getNimi() {
        return this.nimi;
    }
    
    
    /**
     * Palauttaa henkilön tunnusnumeron
     * @return henkilön tunnusnumero
     */
    public int getTunnusNro() {
        return this.tunnusNro;
    }
    
    
    /**
     * Asettaa tunnusnumeron ja samalla varmistaa että
     * seuraava numero on aina suurempi kuin tähän mennessä suurin.
     * @param nr asetettava tunnusnumero
     */
    private void setTunnusNro(int nr) {
        tunnusNro = nr;
        if (tunnusNro >= seuraavaNro) seuraavaNro = tunnusNro + 1;
    }
    
    
    /**
     * Antaa k:n kentän sisällön merkkijonona
     * @param k monenenko kentän sisältö palautetaan
     * @return kentän sisältö merkkijonona
     */
    public String anna(int k) {
        switch (k) {
        case 1: return "" + nimi;
        case 2: return "" + hetu;
        case 3: return "" + puhelinnumero;
        case 4: return "" + katuosoite;
        case 5: return "" + postinumero;
        case 6: return "" + postitoimipaikka;
        case 7: return "" + sahkoposti;
        case 8: return "" + lisatietoja;
        default: return "";
        }
    }
    
    
    /**
     * Asettaa k:n kentän arvoksi parametrina tuodun merkkijonon arvon
     * @param k kuinka monennen kentän arvo asetetaan
     * @param jono jonoa joka asetetaan kentän arvoksi
     * @return null, jos asettaminen onnistuu, muuten vastaava virheilmoitus
     */
    public String aseta(int k, String jono) {
        String tjono = jono.trim();
        switch (k) {
        case 1:
            nimi = tjono;
            return null;
        case 2:
            HetuTarkistus hetut = new HetuTarkistus();
            String virhe = hetut.tarkista(tjono);
            if ( virhe != null ) return virhe;
            hetu = tjono;
            return null;
        case 3:
            puhelinnumero = tjono;
            return null;
        case 4:
            katuosoite = tjono;
            return null;
        case 5:
            if(!tjono.matches("[0-9]*")) return "Postinumeron on oltava numeerinen";
            postinumero = tjono;
            return null;
        case 6:
            postitoimipaikka = tjono;
            return null;
        case 7:
            sahkoposti = tjono;
            return null;
        case 8:
            lisatietoja = tjono;
            return null;
        default:
            return "";
        }
    }
    
    
    /**
     * Palauttaa henkilön tiedot merkkijonona jonka voi tallentaa tiedostoon.
     * @return henkilö tolppaeroteltuna merkkijonona 
     * @example
     * <pre name="test">
     * Henkilo henkilo = new Henkilo();
     * henkilo.parse("   3  |  Ankka Aku   | 030201-111C");
     * henkilo.toString().startsWith("3|Ankka Aku|030201-111C|") === true; // on enemmäkin kuin 3 kenttää, siksi loppu |
     * </pre>  
     */
    @Override
    public String toString() {
        return "" +
                getTunnusNro() + "|" +
                nimi + "|" +
                hetu + "|" +
                puhelinnumero + "|" +
                katuosoite + "|" +
                postinumero + "|" +
                postitoimipaikka + "|" +
                sahkoposti + "|" +
                lisatietoja;
    }
    
    
    /**
    * Selvittää henkilön tiedot | erotellusta merkkijonosta
    * Pitää huolen että seuraavaNro on suurempi kuin tuleva tunnusNro.
    * @param rivi josta jäsenen tiedot otetaan
    * 
    * @example
    * <pre name="test">
    * Henkilo henkilo = new Henkilo();
    * henkilo.parse("   3  |  Ankka Aku   | 030201-111C");
    * henkilo.getTunnusNro() === 3;
    * henkilo.toString().startsWith("3|Ankka Aku|030201-111C|") === true; // on enemmäkin kuin 3 kenttää, siksi loppu |
    *
    * henkilo.rekisteroi();
    * int n = henkilo.getTunnusNro();
    * henkilo.parse(""+(n+20));       // Otetaan merkkijonosta vain tunnusnumero
    * henkilo.rekisteroi();           // ja tarkistetaan että seuraavalla kertaa tulee yhtä isompi
    * henkilo.getTunnusNro() === n+20+1;
    *    
    * </pre>
    */
    public void parse(String rivi) {
        StringBuilder sb = new StringBuilder(rivi);
        setTunnusNro(Mjonot.erota(sb, '|', getTunnusNro()));
        nimi = Mjonot.erota(sb, '|', nimi);
        hetu = Mjonot.erota(sb, '|', hetu);
        puhelinnumero = Mjonot.erota(sb, '|', puhelinnumero);
        katuosoite = Mjonot.erota(sb, '|', katuosoite);
        postinumero = Mjonot.erota(sb, '|', postinumero);
        postitoimipaikka = Mjonot.erota(sb, '|', postitoimipaikka);
        sahkoposti = Mjonot.erota(sb, '|', sahkoposti);
        lisatietoja = Mjonot.erota(sb, '|', lisatietoja);
    }
    
    
    /**
     * Tehdään identtinen klooni henkilöstä
     * @return Object kloonattu henkilö
     * @example
     * <pre name="test">
     * #THROWS CloneNotSupportedException 
     * Henkilo henkilo = new Henkilo();
     * henkilo.parse("   3  |  Ankka Aku   | 123");
     * Henkilo kopio = henkilo.clone();
     * kopio.toString() === henkilo.toString();
     * henkilo.parse("   4  |  Ankka Tupu   | 123");
     * kopio.toString().equals(henkilo.toString()) === false;
     * </pre>
     */
    @Override
    public Henkilo clone() throws CloneNotSupportedException {
    Henkilo uusi;
    uusi = (Henkilo) super.clone();
    return uusi;
    }
    
    
    /**
    * Apumetodi, jolla saadaan täytettyä testiarvot jäsenelle.
    */
    public void taytaAkuAnkanTiedoilla() {
        nimi = "Ankka Aku " + rand(1000, 9999);
        hetu = arvoHetu();
        puhelinnumero = "050-9999999";
        katuosoite = "Paratiisitie 13";
        postinumero = "12345";
        postitoimipaikka = "ANKKALINNA";
        sahkoposti = "duckface@example.com";
        lisatietoja = "Hankala asiakas";
    }
    
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Henkilo aku = new Henkilo();
        Henkilo aku2 = new Henkilo();
        
        aku.rekisteroi();
        aku2.rekisteroi();
       
        aku.tulosta(System.out);
        aku.taytaAkuAnkanTiedoilla();
        aku.tulosta(System.out);
        
        aku2.tulosta(System.out);
        aku2.taytaAkuAnkanTiedoilla();
        aku2.tulosta(System.out);
    }


    /**
     * Vertailija
     */
    @Override
    public int compareTo(Henkilo toinenHenkilo) {
        return nimi.compareTo(toinenHenkilo.getNimi());
    }

}
