package seurantakaaviot;

import java.util.Collection;
import java.util.List;

/**
 * - huolehtii Henkilot-, Kaaviot- ja Datapisteet-luokkien yhteistyöstä 
 * @author Henri
 * @version 22.4.2021
 *
 */
public class Seurantakaaviot {

    private Henkilot henkilot = new Henkilot();
    private Kaaviot kaaviot = new Kaaviot();
    private Datapisteet datapisteet = new Datapisteet();
    
    
    /**
     * Lisätään uusi henkilö
     * @param henkilo Lisättävä henkilö
     * @example
     * <pre name="test">
     * Seurantakaaviot seurantakaaviot = new Seurantakaaviot();
     * Henkilo henkilo1 = new Henkilo(); henkilo1.aseta(1,"Susi Sepe");
     * Henkilo henkilo2 = new Henkilo(); henkilo2.aseta(1, "Ankka Aku");
     * Henkilo henkilo3 = new Henkilo(); henkilo3.aseta(1, "Ankka Iines");
     * seurantakaaviot.lisaa(henkilo1);
     * seurantakaaviot.lisaa(henkilo2);
     * seurantakaaviot.lisaa(henkilo3);
     * seurantakaaviot.getHenkiloita() === 3;
     * seurantakaaviot.annaHenkilo(0).getNimi().equals("Susi Sepe") === true;
     * seurantakaaviot.annaHenkilo(1).getNimi().equals("Ankka Aku") === true;
     * seurantakaaviot.annaHenkilo(2).getNimi().equals("Ankka Iines") === true;
     * </pre>
     */
    public void lisaa(Henkilo henkilo) {
        henkilot.lisaa(henkilo);
    }
    
    
    /** 
     * Palauttaa "taulukossa" hakuehtoon vastaavien henkilöiden viitteet 
     * @param hakuehto käytettävä hakuehto   
     * @return tietorakenteen löytyneistä henkilöistä
     * @example 
     * <pre name="test">
     * #import java.util.Collection;
     * #THROWS CloneNotSupportedException, SailoException
     * Seurantakaaviot seurantakaaviot = new Seurantakaaviot();
     * Henkilo henkilo1 = new Henkilo(); henkilo1.rekisteroi();
     * Henkilo henkilo2 = new Henkilo(); henkilo2.rekisteroi();
     * Henkilo henkilo3 = new Henkilo(); henkilo3.rekisteroi();
     * henkilo1.aseta(1,"Susi Sepe");
     * henkilo2.aseta(1, "Ankka Aku");
     * henkilo3.aseta(1, "Ankka Iines");
     * seurantakaaviot.lisaa(henkilo1);
     * seurantakaaviot.lisaa(henkilo2);
     * seurantakaaviot.lisaa(henkilo3);
     * Collection<Henkilo> loytyneet = seurantakaaviot.etsi("*Susi*");
     * loytyneet.size() === 1;
     * loytyneet = seurantakaaviot.etsi("*s*");
     * loytyneet.size() === 2;
     * loytyneet = seurantakaaviot.etsi("");
     * loytyneet.size() === 3;
     * </pre>
     */ 
    public Collection<Henkilo> etsi(String hakuehto) { 
        return henkilot.etsi(hakuehto); 
    } 
    
    
    /**
     * Korvaa henkilon tietorakenteesta. Ottaa henkilon omistukseensa.
     * Etsitään samalla tunnusnumerolla oleva henkilö. Jos ei löydy, niin lisätään
     * uutena henkilönä.
     * @param henkilo lisättävän henkilön viite
     * <pre name="test">
     * #THROWS CloneNotSupportedException
     * Seurantakaaviot seurantakaaviot = new Seurantakaaviot();
     * Henkilo henkilo1 = new Henkilo(); henkilo1.rekisteroi();
     * Henkilo henkilo2 = new Henkilo(); henkilo2.rekisteroi();
     * seurantakaaviot.korvaaTaiLisaa(henkilo1); seurantakaaviot.getHenkiloita() === 1;
     * seurantakaaviot.korvaaTaiLisaa(henkilo2); seurantakaaviot.getHenkiloita() === 2;
     * Henkilo henkilo3 = henkilo1.clone();
     * seurantakaaviot.korvaaTaiLisaa(henkilo3); seurantakaaviot.getHenkiloita() === 2;
     * </pre>
     */
    public void korvaaTaiLisaa(Henkilo henkilo) {
        henkilot.korvaaTaiLisaa(henkilo);
    }
    
    
    /**
     * Korvaa kaavion tietorakenteesta. Ottaa kaavion omistukseensa.
     * Etsitään samalla tunnusnumerolla oleva kaavio. Jos ei löydy, niin lisätään
     * uutena kaaviona.
     * @param kaavio lisättävän kaavion viite
     * @example
     * <pre name="test">
     * #THROWS CloneNotSupportedException
     * Seurantakaaviot seurantakaaviot = new Seurantakaaviot();
     * Henkilo henkilo = new Henkilo(); henkilo.rekisteroi();
     * seurantakaaviot.lisaa(henkilo);
     * Kaavio kaavio1 = new Kaavio(henkilo.getTunnusNro()); kaavio1.rekisteroi();
     * Kaavio kaavio2 = new Kaavio(henkilo.getTunnusNro()); kaavio2.rekisteroi();
     * seurantakaaviot.korvaaTaiLisaa(kaavio1); seurantakaaviot.annaKaaviot(henkilo).size() === 1;
     * seurantakaaviot.korvaaTaiLisaa(kaavio2); seurantakaaviot.annaKaaviot(henkilo).size() === 2;
     * Kaavio kaavio3 = kaavio1.clone();
     * seurantakaaviot.korvaaTaiLisaa(kaavio3); seurantakaaviot.annaKaaviot(henkilo).size() === 2;
     * </pre>
     */
    public void korvaaTaiLisaa(Kaavio kaavio) {
        kaaviot.korvaaTaiLisaa(kaavio);
    }
    
    
    /**
     * Korvaa datapisteen tietorakenteesta. Ottaa datapisteen omistukseensa.
     * Etsitään samalla tunnusnumerolla oleva datapiste. Jos ei löydy, niin lisätään
     * uutena datapisteenä.
     * @param datapiste lisättävän datapisteen viite
     * @example
     * <pre name="test">
     * #THROWS CloneNotSupportedException
     * Seurantakaaviot seurantakaaviot = new Seurantakaaviot();
     * Kaavio kaavio = new Kaavio(); kaavio.rekisteroi();
     * Datapiste dat1 = new Datapiste(kaavio.getTunnusNro()); dat1.rekisteroi();
     * Datapiste dat2 = new Datapiste(kaavio.getTunnusNro()); dat2.rekisteroi();
     * seurantakaaviot.korvaaTaiLisaa(dat1); seurantakaaviot.annaDatapisteet(kaavio).size() === 1;
     * seurantakaaviot.korvaaTaiLisaa(dat2); seurantakaaviot.annaDatapisteet(kaavio).size() === 2;
     * Datapiste dat3 = dat1.clone();
     * seurantakaaviot.korvaaTaiLisaa(dat3); seurantakaaviot.annaDatapisteet(kaavio).size() === 2;
     * </pre>
     */
    public void korvaaTaiLisaa(Datapiste datapiste) {
        datapisteet.korvaaTaiLisaa(datapiste);
    }
    
    
    /**
     * Lisätään uusi kaavio seurantakaavioihin
     * @param kaavio lisättävä kaavio
     * @example
     * <pre name="test">
     * #import java.util.*;
     * Seurantakaaviot seurantakaaviot = new Seurantakaaviot();
     * Henkilo henkilo = new Henkilo(); henkilo.rekisteroi();
     * Kaavio kaavio1 = new Kaavio(henkilo.getTunnusNro());
     * Kaavio kaavio2 = new Kaavio(henkilo.getTunnusNro());
     * Kaavio kaavio3 = new Kaavio(henkilo.getTunnusNro());
     * seurantakaaviot.lisaa(kaavio1);
     * seurantakaaviot.lisaa(kaavio2);
     * seurantakaaviot.lisaa(kaavio3);
     * List<Kaavio> loytyneet;
     * loytyneet = seurantakaaviot.annaKaaviot(henkilo);
     * loytyneet.size() === 3;
     * </pre>
     */
    public void lisaa(Kaavio kaavio) {
        kaaviot.lisaa(kaavio);
    }
    
    
    /**
     * Lisätään uusi datapiste
     * @param datapiste listäätävä datapiste
     * @example
     * <pre name="test">
     * #import java.util.*;
     * Seurantakaaviot seurantakaaviot = new Seurantakaaviot();
     * Henkilo henkilo = new Henkilo(); henkilo.taytaAkuAnkanTiedoilla(); henkilo.rekisteroi();
     * Kaavio kaavio = new Kaavio(henkilo.getTunnusNro()); kaavio.rekisteroi();
     * seurantakaaviot.lisaa(kaavio);
     * Datapiste dat1 = new Datapiste(); dat1.taytaDatapiste(kaavio.getTunnusNro()); dat1.rekisteroi();
     * Datapiste dat2 = new Datapiste(); dat2.taytaDatapiste(kaavio.getTunnusNro()); dat2.rekisteroi();
     * Datapiste dat3 = new Datapiste(); dat3.taytaDatapiste(kaavio.getTunnusNro()); dat3.rekisteroi();
     * seurantakaaviot.lisaa(dat1);
     * seurantakaaviot.lisaa(dat2);
     * seurantakaaviot.lisaa(dat3);
     * List<Datapiste> loytyneet;
     * loytyneet = seurantakaaviot.annaDatapisteet(kaavio);
     * loytyneet.size() === 3;
     * </pre>
     */
    public void lisaa(Datapiste datapiste) {
        datapisteet.lisaa(datapiste);
    }
    
    
    /**
     * Poistaa henkilon
     * @param henkilo poistettava henkilo
     * @example
     * <pre name="test">
     * #import java.util.List;
     * Seurantakaaviot seurantakaaviot = new Seurantakaaviot();
     * Henkilo henkilo1 = new Henkilo(); henkilo1.rekisteroi(); seurantakaaviot.lisaa(henkilo1);
     * Henkilo henkilo2 = new Henkilo(); henkilo2.rekisteroi(); seurantakaaviot.lisaa(henkilo2);
     * Henkilo henkilo3 = new Henkilo(); henkilo3.rekisteroi(); seurantakaaviot.lisaa(henkilo3);
     * Kaavio kaavio1 = new Kaavio(henkilo1.getTunnusNro()); kaavio1.rekisteroi();
     * Kaavio kaavio2 = new Kaavio(henkilo1.getTunnusNro()); kaavio2.rekisteroi();
     * Kaavio kaavio3 = new Kaavio(henkilo2.getTunnusNro()); kaavio3.rekisteroi();
     * seurantakaaviot.lisaa(kaavio1);
     * seurantakaaviot.lisaa(kaavio2);
     * seurantakaaviot.lisaa(kaavio3);
     * Datapiste dat1 = new Datapiste(kaavio1.getTunnusNro()); seurantakaaviot.lisaa(dat1);
     * Datapiste dat2 = new Datapiste(kaavio1.getTunnusNro()); seurantakaaviot.lisaa(dat2);
     * Datapiste dat3 = new Datapiste(kaavio3.getTunnusNro()); seurantakaaviot.lisaa(dat3);
     * Datapiste dat4 = new Datapiste(kaavio1.getTunnusNro()); seurantakaaviot.lisaa(dat4);
     * Datapiste dat5 = new Datapiste(kaavio3.getTunnusNro()); seurantakaaviot.lisaa(dat5);
     * seurantakaaviot.getHenkiloita() === 3;
     * seurantakaaviot.getKaavioita() === 3;
     * seurantakaaviot.getDatapisteita() === 5;
     * seurantakaaviot.poista(henkilo1);
     * seurantakaaviot.getHenkiloita() === 2;
     * seurantakaaviot.getKaavioita() === 1;
     * seurantakaaviot.getDatapisteita() === 2;
     * seurantakaaviot.poista(henkilo3);
     * seurantakaaviot.getHenkiloita() === 1;
     * seurantakaaviot.getKaavioita() === 1;
     * seurantakaaviot.getDatapisteita() === 2;
     * </pre>
     */
    public void poista(Henkilo henkilo) {
        List<Kaavio> henkilonKaaviot = annaKaaviot(henkilo);
        for (Kaavio kaavio : henkilonKaaviot) {
            datapisteet.poistaKaavionDatapisteet(kaavio.getTunnusNro());
        }
        kaaviot.poistaHenkilonKaaviot(henkilo.getTunnusNro());
        henkilot.poista(henkilo.getTunnusNro());
    }
    
    
    /**
     * Poistaa datapisteen
     * @param datapiste poistettava datapiste
     * @example
     * <pre name="test">
     * #import java.util.List;
     * Seurantakaaviot seurantakaaviot = new Seurantakaaviot();
     * Datapiste dat1 = new Datapiste(); 
     * Datapiste dat2 = new Datapiste();
     * Datapiste dat3 = new Datapiste();
     * Datapiste dat4 = new Datapiste(); 
     * Datapiste dat5 = new Datapiste();
     * seurantakaaviot.lisaa(dat1);
     * seurantakaaviot.lisaa(dat2);
     * seurantakaaviot.lisaa(dat3);
     * seurantakaaviot.lisaa(dat4);
     * seurantakaaviot.lisaa(dat5);
     * seurantakaaviot.getDatapisteita() === 5;
     * seurantakaaviot.poista(dat1);
     * seurantakaaviot.getDatapisteita() === 4;
     * seurantakaaviot.poista(dat2);
     * seurantakaaviot.poista(dat3);
     * seurantakaaviot.poista(dat5);
     * seurantakaaviot.getDatapisteita() === 1;
     * </pre>
     */
    public void poista(Datapiste datapiste) { 
        datapisteet.poista(datapiste); 
    } 
    
    
    /**
     * Poistaa kaavion ja sille kuuluvat datapisteet
     * @param kaavio poistettava kaavio
     * @example
     * <pre name="test">
     * #import java.util.List;
     * Seurantakaaviot seurantakaaviot = new Seurantakaaviot();
     * Kaavio kaavio1 = new Kaavio(); kaavio1.rekisteroi();
     * Kaavio kaavio2 = new Kaavio(); kaavio2.rekisteroi();
     * seurantakaaviot.lisaa(kaavio1);
     * seurantakaaviot.lisaa(kaavio2);
     * Datapiste dat1 = new Datapiste(kaavio1.getTunnusNro()); 
     * Datapiste dat2 = new Datapiste(kaavio1.getTunnusNro());
     * Datapiste dat3 = new Datapiste(kaavio2.getTunnusNro());
     * Datapiste dat4 = new Datapiste(kaavio1.getTunnusNro()); 
     * Datapiste dat5 = new Datapiste(kaavio2.getTunnusNro());
     * seurantakaaviot.lisaa(dat1);
     * seurantakaaviot.lisaa(dat2);
     * seurantakaaviot.lisaa(dat3);
     * seurantakaaviot.lisaa(dat4);
     * seurantakaaviot.lisaa(dat5);
     * seurantakaaviot.getKaavioita() === 2;
     * seurantakaaviot.getDatapisteita() === 5;
     * seurantakaaviot.poista(kaavio1);
     * seurantakaaviot.getKaavioita() === 1;
     * seurantakaaviot.getDatapisteita() === 2;
     * seurantakaaviot.poista(kaavio2);
     * seurantakaaviot.getKaavioita() === 0;
     * seurantakaaviot.getDatapisteita() === 0;
     * </pre>
     */
    public void poista(Kaavio kaavio) {
        datapisteet.poistaKaavionDatapisteet(kaavio.getTunnusNro());
        kaaviot.poista(kaavio);
    }
    
    
    /**
     * Palauttaa jäsenten lukumäärän
     * @return jäsenten lukumäärä
     */
    public int getHenkiloita() {
        return henkilot.getLkm();
    }
    
    
    /**
     * Palauttaa kaavioiden lukumäärän
     * @return kaavioiden lukumäärä
     */
    public int getKaavioita() {
        return kaaviot.getLkm();
    }
    
    
    /**
     * Palauttaa datapisteiden lukumäärän
     * @return datapisteiden lukumäärä;
     */
    public int getDatapisteita() {
        return datapisteet.getLkm();
    }
    
    
    /**
     * Antaa käsiteltävän i:n henkilön
     * @param i monesko henkilö (alkaa 0:sta)
     * @return henkilö paikasta i
     */
    public Henkilo annaHenkilo(int i) {
        return henkilot.anna(i);
    }
    
    
    /**
     * Haetaan kaikki henkilön kaaviot
     * @param henkilo henkilö, jolle kaavioita haetaan
     * @return tietorakenne, jossa viiteet löydetteyihin kaavioihin
     * @example
     * <pre name="test">
     * #import java.util.*;
     * Seurantakaaviot seurantakaaviot = new Seurantakaaviot();
     * Henkilo henkilo1 = new Henkilo(); henkilo1.rekisteroi();
     * Henkilo henkilo2 = new Henkilo(); henkilo2.rekisteroi();
     * Henkilo henkilo3 = new Henkilo(); henkilo3.rekisteroi();
     * Henkilo henkilo5 = new Henkilo(); henkilo5.rekisteroi();
     * seurantakaaviot.lisaa(henkilo1);
     * seurantakaaviot.lisaa(henkilo2);
     * seurantakaaviot.lisaa(henkilo3);
     * seurantakaaviot.lisaa(henkilo5);
     * Kaavio kaavio21 = new Kaavio(henkilo2.getTunnusNro()); seurantakaaviot.lisaa(kaavio21);
     * Kaavio kaavio11 = new Kaavio(henkilo1.getTunnusNro()); seurantakaaviot.lisaa(kaavio11);
     * Kaavio kaavio22 = new Kaavio(henkilo2.getTunnusNro()); seurantakaaviot.lisaa(kaavio22);
     * Kaavio kaavio12 = new Kaavio(henkilo1.getTunnusNro()); seurantakaaviot.lisaa(kaavio12);
     * Kaavio kaavio23 = new Kaavio(henkilo2.getTunnusNro()); seurantakaaviot.lisaa(kaavio23);
     * Kaavio kaavio51 = new Kaavio(henkilo5.getTunnusNro()); seurantakaaviot.lisaa(kaavio51);
     * 
     * List<Kaavio> loytyneet;
     * loytyneet = seurantakaaviot.annaKaaviot(henkilo3);
     * loytyneet.size() === 0;
     * loytyneet = seurantakaaviot.annaKaaviot(henkilo1);
     * loytyneet.size() === 2;
     * loytyneet = seurantakaaviot.annaKaaviot(henkilo5);
     * loytyneet.size() === 1;
     * loytyneet.get(0) == kaavio51 === true;
     * loytyneet = seurantakaaviot.annaKaaviot(henkilo2);
     * loytyneet.size() === 3;
     * </pre>
     */
    public List<Kaavio> annaKaaviot(Henkilo henkilo) {
        return kaaviot.annaKaaviot(henkilo.getTunnusNro());
    }
    
    
    /**
     * Haetaan kaikki kaavion datapisteet
     * @param kaavio kaavio, jolle datapisteitä haetaan
     * @return tietorakenne, jossa viitteet löydettyihin datapisteisiin
     * @example
     * <pre name="test">
     * #import java.util.List;
     * Seurantakaaviot seurantakaaviot = new Seurantakaaviot();
     * Henkilo henkilo = new Henkilo(); henkilo.rekisteroi();
     * seurantakaaviot.lisaa(henkilo);
     * Kaavio kaavio1 = new Kaavio(henkilo.getTunnusNro()); kaavio1.rekisteroi();
     * Kaavio kaavio2 = new Kaavio(henkilo.getTunnusNro()); kaavio2.rekisteroi();
     * seurantakaaviot.lisaa(kaavio1);
     * seurantakaaviot.lisaa(kaavio2);
     * Datapiste dat1 = new Datapiste(kaavio1.getTunnusNro()); 
     * Datapiste dat2 = new Datapiste(kaavio1.getTunnusNro());
     * Datapiste dat3 = new Datapiste(kaavio2.getTunnusNro());
     * Datapiste dat4 = new Datapiste(kaavio1.getTunnusNro()); 
     * Datapiste dat5 = new Datapiste(kaavio2.getTunnusNro()); 
     * seurantakaaviot.lisaa(dat1);
     * seurantakaaviot.lisaa(dat2);
     * seurantakaaviot.lisaa(dat3);
     * seurantakaaviot.lisaa(dat4);
     * seurantakaaviot.lisaa(dat5);
     * List<Datapiste> d = seurantakaaviot.annaDatapisteet(kaavio1);
     * d.size() === 3;
     * d = seurantakaaviot.annaDatapisteet(kaavio2);
     * d.size() === 2;
     * </pre>
     */
    public List<Datapiste> annaDatapisteet(Kaavio kaavio) {
        return datapisteet.annaDatapisteet(kaavio.getTunnusNro());
    }
    
    
    /**
     * Lukee henkilöt, kaaviot ja datapisteet tiedostosta
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta() throws SailoException {
        henkilot = new Henkilot(); // jos luetaan olemassa olevaan niin helpoin tyhjentää näin
        henkilot.lueTiedostosta();
        kaaviot.lueTiedostosta();
        datapisteet.lueTiedostosta();
        }
    
    
    /**
     * Tallenttaa henkilot, kaaviot ja datapisteet niiden omiin tiedostoihin
     * @throws SailoException jos tallettamisessa ongelmia
     */
    public void tallenna() throws SailoException {
        String virhe = "";
        try {
            henkilot.tallenna();
        } catch ( SailoException ex ) {
            virhe = ex.getMessage();
        }
        try {
            kaaviot.tallenna();
        } catch ( SailoException ex ) {
            virhe += ex.getMessage();
        }
        try {
            datapisteet.tallenna();
        } catch ( SailoException ex ) {
            virhe += ex.getMessage();
        }
        if (!"".equals(virhe)) throw new SailoException(virhe);
    }
    
    
    /**
     * 
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Seurantakaaviot seurantakaaviot = new Seurantakaaviot();
        Henkilo aku = new Henkilo();
        Henkilo aku2 = new Henkilo();
        aku.rekisteroi();
        aku.taytaAkuAnkanTiedoilla();
        aku2.rekisteroi();
        aku2.taytaAkuAnkanTiedoilla();

        seurantakaaviot.lisaa(aku);
        seurantakaaviot.lisaa(aku2);
 
        for (int i = 0; i < seurantakaaviot.getHenkiloita(); i++) {
            Henkilo henkilo = seurantakaaviot.annaHenkilo(i);
            henkilo.tulosta(System.out);
        }

    }

}
