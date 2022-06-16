package seurantakaaviot;

import static kanta.HetuTarkistus.*;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * - tietää datapisteen kentät 
 * - osaa tarkistaa kentän oikeellisuuden
 * - osaa muutta palkillisen merkkijonon datapisteen tiedoiksi
 * - osaa antaa merkkijonona i:n kentän tiedot
 * - osaa laittaa merkkijonon i:neksi kentäksi
 * @author Henri
 * @version 22.4.2021
 *
 */
public class Datapiste implements Cloneable, Comparable<Datapiste> {

    private int tunnusNro;
    private int kaavioNro;
    private int paiva;
    private int kuukausi;
    private int vuosi;
    private double arvo;
    
    private static int seuraavaNro = 1;
    
    /**
     * Alustetaan datapiste. Toistaiseksi ei tarvitse tehdä mitään
     */
    public Datapiste() {
    // Vielä ei tarvita mitään
    }
    
    
    /**
     * Alustetaan tietyn kaavion datapiste
     * @param kaavioNro kaavion viitenumero
     */
    public Datapiste(int kaavioNro) {
        this.kaavioNro = kaavioNro;
    }
    
    
    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot Datapisteelle
     * @param nro viite kaavioon, jonka datapisteestä on kyse
     */
    public void taytaDatapiste(int nro) {
        this.kaavioNro = nro;
        this.paiva = rand(1, 28);
        this.kuukausi = rand(1, 12);
        this.vuosi = rand(2010, 2020);
        this.arvo = rand(80, 90);
        
    }
    
    
    /**
     * Tulostetaan datapisteen tiedot
     * @param out tietovirta, johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println("Datapisteen tunnusnumero on: " + getTunnusNro());
        out.println("Datapiste kuuluu kaaviolle: " + String.format("%03d", kaavioNro, 3));
        out.println("  " + "Aika: " + this.paivamaara());
        out.println("  " + "Arvo: " + String.valueOf(this.arvo));
    }
    
    
    /**
     * Antaa datapisteelle seuraavan rekisterinumeron.
     * @return datapisteen uusi tunnusnumero
     * @example
     * <pre name="test">
     * Datapiste dat1 = new Datapiste();
     * dat1.getTunnusNro() === 0;
     * dat1.rekisteroi();
     * Datapiste dat2 = new Datapiste();
     * dat2.rekisteroi();
     * int n1 = dat1.getTunnusNro();
     * int n2 = dat2.getTunnusNro();
     * n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        this.tunnusNro = seuraavaNro;
        seuraavaNro++;
        return this.tunnusNro;
    }    
     
    /**
     * Palautetaan datapisteen oma id
     * @return datapisteen id
     */
    public int getTunnusNro() {
        return tunnusNro;
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
     * Tulostetaan datapisteen tiedot
     * @param os tietovirta, johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    /**
     * Palauttaa aikatiedoista muodostetun päivämäärän
     * @return päivämäärän merkkijonona
     * * @example
     * <pre name="test">
     * Datapiste datapiste = new Datapiste();
     * datapiste.paivamaara().equals("00.00.0000") === true;
     * datapiste.aseta(1, "31");
     * datapiste.paivamaara().equals("31.00.0000") === true;
     * datapiste.aseta(2, "12");
     * datapiste.paivamaara().equals("31.12.0000") === true;
     * datapiste.aseta(3, "2020");
     * datapiste.paivamaara().equals("31.12.2020") === true;
     * </pre>
     */
    public String paivamaara() {
        return String.format("%02d", paiva, 2) + "." + String.format("%02d", kuukausi, 2) + "." + String.format("%04d", vuosi, 4);
    }
    
    
    /**
     * Palauttaa tunnusnumeron sille kaaviolle, jolle datapiste kuuluu
     * @return kaavion numeron
     */
    public int getKaavioNro() {
        return kaavioNro;
    }
    
    
    /**
     * Palauttaa datapisteen y-arvon.
     * @return arvo
     */
    public double getArvo() {
        return arvo;
    }
    
    /**
     * @return päivä
     */
    public int getPaiva() {
        return paiva;
    }
    
    
    /**
     * @return kuukausi
     */
    public int getKuukausi() {
        return kuukausi;
    }
    
    
    /**
     * @return vuosi
     */
    public int getVuosi() {
        return vuosi;
    }
    
    
    /**
     * Kloonaus
     */
    @Override
    public Datapiste clone() throws CloneNotSupportedException {
    Datapiste uusi;
    uusi = (Datapiste)super.clone();
    return uusi;
    }
    
    
    /**
     * Antaa k:n kentän sisällön merkkijonona
     * @param k monenenko kentän sisältö palautetaan
     * @return kentän sisältö merkkijonona
     */
    public String anna(int k) {
        switch (k) {
        case 1: return "" + paiva;
        case 2: return "" + kuukausi;
        case 3: return "" + vuosi;
        case 4: return "" + arvo;
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
        StringBuilder sb = new StringBuilder(tjono);
        switch (k) {
        case 1:
            try {
            paiva = Mjonot.erotaEx(sb, '§', paiva);
            } catch (NumberFormatException ex) {
                return "Päivä: ei kokonaisluku";
            }
            if(paiva < 1 || paiva > 31) {
                return "Päivän tulee olla välillä 1-31";
            }
            return null;
        case 2:
            try {
                kuukausi = Mjonot.erotaEx(sb, '§', kuukausi);
                } catch (NumberFormatException ex) {
                    return "Kuukausi: ei kokonaisluku";
                }
                if(kuukausi < 1 || kuukausi > 12) {
                    return "Kuukauden tulee olla välillä 1-12";
                }
                return null;
        case 3:
            try {
                vuosi = Mjonot.erotaEx(sb, '§', vuosi);
                } catch (NumberFormatException ex) {
                    return "Vuosi: ei kokonaisluku";
                }
                if(vuosi < 0) {
                return "Vuoden tulee olla postiivinen kokonaisluku";
                }
                return null;
        case 4:
            try {
                arvo = Mjonot.erotaEx(sb, '§', arvo);
                } catch (NumberFormatException ex) {
                    return "Arvo: ei reaaliluku";
                }
                return null;
        default:
            return "";
        }
    }
    
    
    
    /**
     * Palauttaa datapisteen tiedot merkkijonona, jonka voi tallentaa tiedostoon.
     * @return datapiste tolppaeroteltuna merkkijonona
     */
    @Override
    public String toString() {
        return "" +
                getTunnusNro() + "|" +
                kaavioNro + "|" +
                paiva + "|" +
                kuukausi + "|" +
                vuosi + "|" +
                arvo;
    }
    
    
    /**
     * Selvittää datapisteen tiedot | erotellusta merkkijonosta
     * Pitää huolen että seuraavaNro on suurempi kuin tuleva tunnusNro.
     * @param rivi rivi, josta datapisteen tiedot otetaan
     */
    public void parse(String rivi) {
        StringBuilder sb = new StringBuilder(rivi);
        setTunnusNro(Mjonot.erota(sb, '|', getTunnusNro()));
        kaavioNro = Mjonot.erota(sb, '|', kaavioNro);
        paiva = Mjonot.erota(sb, '|', paiva);
        kuukausi = Mjonot.erota(sb, '|', kuukausi);
        vuosi = Mjonot.erota(sb, '|', vuosi);
        arvo = Mjonot.erota(sb, '|', arvo);
    }
    
    
    /**
     *
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Datapiste dat1 = new Datapiste();
        dat1.rekisteroi();
        dat1.taytaDatapiste(1);
        Datapiste dat2 = new Datapiste();
        dat2.rekisteroi();
        dat2.taytaDatapiste(2);
        dat1.tulosta(System.out);
        dat2.tulosta(System.out);
        String s = dat1.toString();
        Datapiste dat3 = new Datapiste();
        dat3.parse(s);
        dat3.tulosta(System.out);
    }


    /**
     * Vertailija
     */
    @Override
    public int compareTo(Datapiste toinenDatapiste) {
        int k;
        k = vuosi - toinenDatapiste.getVuosi();
        if (k != 0) return k;
        k = kuukausi - toinenDatapiste.getKuukausi();
        if (k != 0) return k;
        k = paiva - toinenDatapiste.getPaiva();
        if (k != 0) return k;
        return 0;
    }

}
