package seurantakaaviot;
import static kanta.HetuTarkistus.*;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * - tietää kaavion perustiedot (otsikko, tyyppi jne.)
 * - osaa tarkistaa kentän oikeellisuuden
 * - osaa muuttaa merkkijonon 1|painon seuranta|..| kaavion tiedoiksi 
 * - osaa antaa merkkijonona i:n kentän tiedot
 * - osaa laittaa merkkijonon i:neksi kentäksi
 * @author Henri
 * @version 22.4.2021
 *
 */
public class Kaavio implements Cloneable, Comparable<Kaavio> {

    private int tunnusNro;
    private int henkiloNro;
    private String otsikko        = "";
    private String tyyppi         = "";
    private String xOtsikko       = "";
    private String yOtsikko       = "";
    
    private static int seuraavaNro = 1;
    
    
    /**
     * Alustetaan kaavio. Toistaiseksi ei tarvitse tehdä mitään
     */
    public Kaavio() {
    // Vielä ei tarvita mitään
    }
    
    
    /**
     * Alustetaan tietyn henkilön kaavio
     * @param henkiloNro henkilön viitenumero
     */
    public Kaavio(int henkiloNro) {
        this.henkiloNro = henkiloNro;
    }
    
    
    /**
     * Kloonaus
     */
    @Override
    public Kaavio clone() throws CloneNotSupportedException {
    Kaavio uusi;
    uusi = (Kaavio) super.clone();
    return uusi;
    }
    
    
    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot Kaaviolle
     * @param nro viite henkilöön, jonka kaaviosta on kyse
     */
    public void taytaKaavioPainonSeurannalla(int nro) {
        this.henkiloNro = nro;
        this.otsikko = "Painon seuranta " + rand(1000, 9999);
        this.tyyppi = "Terveys";
        this.xOtsikko = "Päivä";
        this.yOtsikko = "Paino";
    }
    
    
    /**
     * Tulostetaan kaavion tiedot
     * @param out tietovirta, johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d", tunnusNro, 3) + "  " + otsikko + "  " + tyyppi);
        out.println("  " + "Kaavio kuuluu henkilölle: " + String.format("%03d", henkiloNro, 3));
        out.println("  " + "Vaaka-akselin otsikko: " + xOtsikko);
        out.println("  " + "Pystyakselin otsikko: " + yOtsikko);
    }
    
    
    /**
     * Tulostetaan kaavion tiedot
     * @param os tietovirta, johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    /**
    * Antaa kaaviolle seuraavan rekisterinumeron.
    * @return kaavion uusi tunnusnumero
    * @example
    * <pre name="test">
    * Kaavio kaavio1 = new Kaavio();
    * kaavio1.getTunnusNro() === 0;
    * kaavio1.rekisteroi();
    * Kaavio kaavio2 = new Kaavio();
    * kaavio2.rekisteroi();
    * int n1 = kaavio1.getTunnusNro();
    * int n2 = kaavio2.getTunnusNro();
    * n1 === n2-1;
    * </pre>
    */
    public int rekisteroi() {
    this.tunnusNro = seuraavaNro;
    seuraavaNro++;
    return tunnusNro;
    }
    
    
    /**
     * Palautetaan kaavion oma id
     * @return kaavion id
     */
    public int getTunnusNro() {
        return tunnusNro;
    }
    
    
    /**
     * Palauttaa, mille henkilölle kaavio kuuluu
     * @return henkilön id
     */
    public int getHenkiloNro() {
        return henkiloNro;
    }
    
    
    /**
     * @return Palauttaa kaavion otsikon
     */
    public String getOtsikko() {
        return otsikko;
    }
    
    
    /**
     * @return Palauttaa x-akselin otsikon
     */
    public String getX() {
        return xOtsikko;
    }
    
    
    /**
     * @return Palauttaa y-akselin otsikon
     */
    public String getY() {
        return yOtsikko;
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
        case 1: return "" + otsikko;
        case 2: return "" + tyyppi;
        case 3: return "" + xOtsikko;
        case 4: return "" + yOtsikko;
        default: return "";
        }
    }
    
    
    /**
     * Asettaa k:n kentän arvoksi parametrina tuodun merkkijonon arvon
     * @param k kuinka monennen kentän arvo asetetaan
     * @param jono jonoa joka asetetaan kentän arvoksi
     */
    public void aseta(int k, String jono) {
        String tjono = jono.trim();
        switch (k) {
        case 1:
            otsikko = tjono;
            break;
        case 2:
            tyyppi = tjono;
            break;
        case 3:
            xOtsikko = tjono;
            break;
        case 4:
            yOtsikko = tjono;
            break;
        default:
            break;
        }
    }
    
    
    /**
     * Palauttaa kaavion tiedot merkkijonona, jonka voi tallentaa tiedostoon.
     * @return kaavio tolppaeroteltuna merkkijonona
     */
    @Override
    public String toString() {
        return "" +
                getTunnusNro() + "|" +
                henkiloNro + "|" +
                otsikko + "|" +
                tyyppi + "|" +
                xOtsikko + "|" +
                yOtsikko;
    }
    
    
    
    /**
     * Selvittää kaavion tiedot | erotellusta merkkijonosta
     * Pitää huolen että seuraavaNro on suurempi kuin tuleva tunnusNro.
     * @param rivi rivi, josta kaavion tiedot otetaan
     */
    public void parse(String rivi) {
        StringBuilder sb = new StringBuilder(rivi);
        setTunnusNro(Mjonot.erota(sb, '|', getTunnusNro()));
        henkiloNro = Mjonot.erota(sb, '|', henkiloNro);
        otsikko = Mjonot.erota(sb, '|', otsikko);
        tyyppi = Mjonot.erota(sb, '|', tyyppi);
        xOtsikko = Mjonot.erota(sb, '|', xOtsikko);
        yOtsikko = Mjonot.erota(sb, '|', yOtsikko);
    }
    
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
    Kaavio kaavio = new Kaavio();
    kaavio.tulosta(System.out);
    kaavio.rekisteroi();
    kaavio.taytaKaavioPainonSeurannalla(5);
    kaavio.tulosta(System.out);
    Kaavio kaavio2 = new Kaavio();
    kaavio2.rekisteroi();
    kaavio2.taytaKaavioPainonSeurannalla(6);
    kaavio2.tulosta(System.out);
    }


    /**
     * Vertailija
     */
    @Override
    public int compareTo(Kaavio toinenKaavio) {
        return otsikko.compareTo(toinenKaavio.getOtsikko());
    }

}
