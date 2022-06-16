package fxSeurantakaaviot;

import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.StringGrid;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import seurantakaaviot.Datapiste;
import seurantakaaviot.Henkilo;
import seurantakaaviot.Kaavio;
import seurantakaaviot.SailoException;
import seurantakaaviot.Seurantakaaviot;

/**
 * Pääikkunan hallinta
 * @author Henri
 * @version 22.4.2021
 *
 */
public class SeurantakaaviotGUIController implements Initializable {
    
    @FXML private ListChooser<Henkilo> chooserHenkilot;
    @FXML private ListChooser<Kaavio> chooserKaaviot;
    @FXML private StringGrid<Datapiste> tableDatapisteet;
    @FXML private TextField hakuehto;
 
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();      
    }
    
    
    @FXML private void handleHakuehto() {
        hae(0); 
    }
    
    
    /**
     * Käsitellään uuden datapisteen lisääminen.
     */
    @FXML private void handleUusiDatapiste() {
       uusiDatapiste();
       // uusiTestidatapiste();
    }
    
    
    /**
     * Avaa ikkunan, jossa voidaan lisätä uusi henkilö.
     */
    @FXML private void handleUusiHenkilo() {
       uusiHenkilo();
       // uusiTestihenkilo();
     }
    
    
    /**
     * Avaa ikkunan, jossa voidaan lisätä uusi kaavio henkilölle.
     */
    @FXML private void handleUusiKaavio() {
        uusiKaavio();
        // uusiTestikaavio();
     }
    
    
    /**
     * Avaa ikkunan, jossa on datapisteistä piirretty viivadiagrammi.
     */
    @FXML private void handlePiirraKaavio() {
        // var resurssi = SeurantakaaviotGUIController.class.getResource("Kaavio.fxml");
        // ModalController.showModal(resurssi, "Seurantakaavio", null, "");
        piirraKaavio();
     }
    
    /**
     * Avaa henkilön tietojen muokkausikkunan.
     */
    @FXML private void handleMuokkaaHenkiloa() {
        muokkaa();
    }
    
    
    /**
     * Avaa kaavion tietojen muokkausikkunan.
     */
    @FXML private void handleMuokkaaKaaviota() {
        muokkaaKaaviota();
    }
    
    
    /**
     * Avaa datapisteen muokkausdialogin
     */
    @FXML private void handleMuokkaaDatapistetta() {
        muokkaaDatapistetta();
    }
    
    
    /**
     * Avaa dialogin, jossa kysytään, poistetaanko henkilö.
     */
    @FXML private void handleHenkilonPoisto() {
        poistaHenkilo();
    }
    
    
    /**
     * Avaa dialogin, jossa kysytään, poistetaanko seurantakaavio.
     */
    @FXML private void handleKaavionPoisto() {
        poistaKaavio();
    }
    
    
    /**
     * Avaa dialogin, jossa kysytään, poistetaanko datapiste.
     */
    @FXML private void handleDatapisteenPoisto() {
        poistaDatapiste();
    }
    
    
    /**
     * Avaa ikkunan, josta saa apua.
     */
    @FXML private void handleApua() {
        Dialogs.showMessageDialog("Ei osata vielä antaa apua");
        // TODO: Lisää toiminto, jolla saa apua.
    }
    
    
    /**
     * Avaa ikkunan, josta saa tietoja.
     */
    @FXML private void handleTietoja() {
        var resurssi = SeurantakaaviotGUIController.class.getResource("Tietoja.fxml");
        ModalController.showModal(resurssi, "Tietoja", null, "");
     }
    
    
    /**
     * Tallentaa kaikki tehdyt muutokset.
     */
    @FXML private void handleTallenna() {
        tallenna();
    }
    
    
    /**
     * Sulkee ohjelman.
     */
    @FXML private void handleLopeta() {
        tallenna();
        Platform.exit();
    }
    
    
    // ===================================================================================================================
    // Tästä eteenpäin ei käyttöliittymään suoraan liittyvää koodia
    
    private Seurantakaaviot seurantakaaviot;
    private Henkilo henkiloKohdalla;
    private Kaavio kaavioKohdalla;
    
    
    /**
    * Tekee tarvittavat muut alustukset
    * Alustetaan myös jäsenlistan kuuntelija 
    */
    protected void alusta() {
        chooserHenkilot.clear();
        chooserKaaviot.clear();
        chooserHenkilot.addSelectionListener(e -> naytaHenkilo());
        chooserKaaviot.addSelectionListener(e -> naytaKaavio());
    }
    
    
    /**
     * Asetetaan käytettävä seurantakaaviot
     * @param seurantakaaviot Asetettava seurantakaaviot
     */
    public void setSeurantakaaviot(Seurantakaaviot seurantakaaviot) {
        this.seurantakaaviot = seurantakaaviot;
    }
    
    
    /**
     * Asetetaan valittu henkilö
     */
    protected void naytaHenkilo() {
        kaavioKohdalla = null;
        henkiloKohdalla = chooserHenkilot.getSelectedObject();
        
        chooserKaaviot.clear();
        tableDatapisteet.initTable();
        
        List<Kaavio> henkilonKaaviot = seurantakaaviot.annaKaaviot(henkiloKohdalla);
        for (Kaavio kaavio : henkilonKaaviot) {
            chooserKaaviot.add(kaavio.getOtsikko(), kaavio);
        }
        
    }
    
    
    /**
     * Näyttää valitun kaavion datapisteet
     */
    protected void naytaKaavio() {
        kaavioKohdalla = chooserKaaviot.getSelectedObject();
        
        tableDatapisteet.clear();
        String[] headings = new String[] {kaavioKohdalla.getX(), kaavioKohdalla.getY()};
        tableDatapisteet.initTable(headings);
        tableDatapisteet.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableDatapisteet.setEditable(false);
        List<Datapiste> kaavionDatapisteet = seurantakaaviot.annaDatapisteet(kaavioKohdalla);
        for (Datapiste datapiste : kaavionDatapisteet) {
            tableDatapisteet.add(datapiste, datapiste.paivamaara(), String.valueOf(datapiste.getArvo()));
        }
    }
    
    
    /**
     * Listataan tiedostosta luetut henkilöt ohjelman aukaisemisen yhteydessä
     */
    public void haeAlussa() {
       hae(0);
    }
    
    
    /**
     * Hakee henkilön tiedot listaan
     * @param hnro henkilön numero, joka aktivoidaan haun jälkeen
     */
    private void hae(int hnr) {
        int hnro = hnr; // hnro henkilön numero, joka aktivoidaan haun jälkeen 
        if (hnro <= 0) { 
            Henkilo kohdalla = henkiloKohdalla; 
            if (kohdalla != null) hnro = kohdalla.getTunnusNro(); 
        }
        
        String ehto = hakuehto.getText();
        if (ehto.indexOf('*') < 0) ehto = "*" + ehto + "*"; 
        
        chooserHenkilot.clear();
        
        Collection<Henkilo> henkilot;
        henkilot = seurantakaaviot.etsi(ehto);
        int index = -1;
        int i = 0;
        for (Henkilo henkilo : henkilot) {
            if (henkilo.getTunnusNro() == hnro) index = i;
            chooserHenkilot.add(henkilo.getNimi(), henkilo);
            i++;
            }
        chooserHenkilot.setSelectedIndex(index);
        if (index == -1) {
            chooserKaaviot.clear();
            tableDatapisteet.clear();
        }
    }
    
    
    /**
     * Hakee henkilön kaaviot listaan
     * @param knro kaavion numero, joka aktivoidaan haun jälkeen
     */
    private void haeKaaviot(int knro) {
        chooserKaaviot.clear();
        List<Kaavio> henkilonKaaviot = seurantakaaviot.annaKaaviot(henkiloKohdalla);
        int index = 0;
        int i = 0;
        
        for (Kaavio kaavio : henkilonKaaviot) {
            chooserKaaviot.add(kaavio.getOtsikko(), kaavio);
            if (kaavio.getTunnusNro() == knro) index = i;
            i++;
        }
        
        chooserKaaviot.setSelectedIndex(index);
    }
    
    
    /**
     * Luo uuden kaavion
     */
    private void uusiKaavio() {
        if (henkiloKohdalla == null) {
            KaavionLisaysController.eiValittuHenkiloa();
            return;
        }
        Kaavio uusi = new Kaavio(henkiloKohdalla.getTunnusNro());
        uusi = KaavionLisaysController.kysyKaavio(null, uusi);
        if (uusi == null) return;
        uusi.rekisteroi();
        seurantakaaviot.lisaa(uusi);
        haeKaaviot(uusi.getTunnusNro()); 
    }
    
    
    /**
     * Luo valitulle henkilölle uuden testikaavion
     */
    public void uusiTestikaavio() {
        if (henkiloKohdalla == null) {
            KaavionLisaysController.eiValittuHenkiloa();
            return;
        }
        Kaavio kaavio = new Kaavio();
        kaavio.rekisteroi();
        kaavio.taytaKaavioPainonSeurannalla(henkiloKohdalla.getTunnusNro());
        seurantakaaviot.lisaa(kaavio);
        haeKaaviot(kaavio.getTunnusNro());
    }
    
    
    /**
     * Luo uuden henkilon
     */
    private void uusiHenkilo() {
        Henkilo uusi = new Henkilo();
        uusi = HenkilonLisaysController.kysyHenkilo(null, uusi);
        if (uusi == null) return;
        uusi.rekisteroi();
        seurantakaaviot.lisaa(uusi);
        hae(uusi.getTunnusNro());
    }
    
    
    /**
     * Lisätään seurantakaavioihin uusi testihenkilö
     */
    @SuppressWarnings("unused")
    private void uusiTestihenkilo() {
        kaavioKohdalla = null;
        Henkilo henkilo = new Henkilo();
        henkilo.rekisteroi();
        henkilo.taytaAkuAnkanTiedoilla();
        seurantakaaviot.lisaa(henkilo);
        hae(henkilo.getTunnusNro());
    }
    
    
    
    /**
     * Lisää valittuun kaavioon uuden datapisteen.
     */
    public void uusiTestidatapiste() {
        if(kaavioKohdalla == null) {
            DatapisteenLisaysController.eiValittuKaaviota();
            return;
        }
        Datapiste datapiste = new Datapiste();
        datapiste.rekisteroi();
        datapiste.taytaDatapiste(kaavioKohdalla.getTunnusNro());
        seurantakaaviot.lisaa(datapiste);
        haeDatapisteet(datapiste.getTunnusNro());
    }

    
    /**
     * Hakee kaavion datapisteet
     */
    private void haeDatapisteet(int dnro) {
        String[] headings = new String[] {kaavioKohdalla.getX(), kaavioKohdalla.getY()};
        tableDatapisteet.initTable(headings);
        List<Datapiste> kaavionDatapisteet = seurantakaaviot.annaDatapisteet(kaavioKohdalla);
        int index = 0;
        int i = 0;
        for (Datapiste datapiste : kaavionDatapisteet) {
            tableDatapisteet.add(datapiste, datapiste.paivamaara(), String.valueOf(datapiste.getArvo()));
            if(datapiste.getTunnusNro() == dnro) index = i;
            i++;
        }
        tableDatapisteet.selectRow(index);
    }
    
    
    /**
     * Luo uuden datapisteen
     */
    private void uusiDatapiste() {
        if (kaavioKohdalla == null) {
            DatapisteenLisaysController.eiValittuKaaviota();
            return;
        }
        Datapiste uusi = new Datapiste(kaavioKohdalla.getTunnusNro());
        uusi = DatapisteenLisaysController.kysyDatapiste(null, uusi);
        if (uusi == null) return;
        uusi.rekisteroi();
        seurantakaaviot.lisaa(uusi);
        haeDatapisteet(uusi.getTunnusNro());
    }
    
    
    /**
     * Tietojen tallennus
     * @return null jos onnistuu, muuten virhe tekstinä
     */
    private String tallenna() {
        try {
            seurantakaaviot.tallenna();
            return null;
        } catch (SailoException ex) {
              Dialogs.showMessageDialog("Tallennuksessa ongelmia! " + ex.getMessage());
              return ex.getMessage();
        }
    }
   
    
    /**
     * Avaa henkilön muokkausikkunan
     */
    private void muokkaa() {
        if (henkiloKohdalla == null) {
            HenkilonLisaysController.eiValittu();
            return;
        }
        Henkilo henkilo;
        try {
            henkilo = henkiloKohdalla.clone();
            henkilo = HenkilonLisaysController.kysyHenkilo(null, henkilo);
            if(henkilo == null) return;
            seurantakaaviot.korvaaTaiLisaa(henkilo);
            hae(henkilo.getTunnusNro());
        } catch (CloneNotSupportedException e) {
            //
        }
    }
    
    
    /**
     * Kaavion muokkaus
     */
    private void muokkaaKaaviota() {
        if (kaavioKohdalla == null) {
            KaavionLisaysController.eiValittu();
            return;
        }
        Kaavio kaavio;
        try {
            kaavio = kaavioKohdalla.clone();
            kaavio = KaavionLisaysController.kysyKaavio(null, kaavio);
            if(kaavio == null) return;
            seurantakaaviot.korvaaTaiLisaa(kaavio);
            haeKaaviot(kaavio.getTunnusNro());
        } catch (CloneNotSupportedException e) {
            //
        }
    }
    
    
    /**
     * Datapisteen muokkaus
     */
    private void muokkaaDatapistetta() {
        int r = tableDatapisteet.getRowNr();
        if (r < 0) {
            DatapisteenLisaysController.eiValittu();
            return;
        }
        Datapiste alkuperainen = tableDatapisteet.getObject();
        try {
            Datapiste datapiste = alkuperainen.clone();
            if (datapiste == null) return;
            datapiste = DatapisteenLisaysController.kysyDatapiste(null, datapiste);
            if (datapiste == null) return;
            seurantakaaviot.korvaaTaiLisaa(datapiste);
            haeDatapisteet(datapiste.getTunnusNro());
        } catch (CloneNotSupportedException e) {
            //
        }
        
    }
    
    
    /**
     * Avaa kaavionpiirtoikkunan
     */
    private void piirraKaavio() {
        if (kaavioKohdalla == null) {
            KaavioController.eiValittu();
            return;
        }
        KaavioController.avaaKaavio(kaavioKohdalla.getOtsikko(), null, seurantakaaviot.annaDatapisteet(kaavioKohdalla));
    }
    
    
    /**
     * Poistaa datapisteen
     */
    private void poistaDatapiste() {
        int r = tableDatapisteet.getRowNr();
        if (r < 0) {
            DatapisteenLisaysController.eiValittu();
            return;
        }
        Datapiste datapiste = tableDatapisteet.getObject();
        seurantakaaviot.poista(datapiste);
        naytaKaavio();
        int datapisteita = tableDatapisteet.getItems().size(); 
        if ( r >= datapisteita ) r = datapisteita - 1;
        tableDatapisteet.getFocusModel().focus(r);
        tableDatapisteet.getSelectionModel().select(r);
    }
    
    /**
     * Poistaa kaavion
     */
    private void poistaKaavio() {
        Kaavio kaavio = kaavioKohdalla;
        if ( kaavio == null ) {
            KaavionLisaysController.eiValittu();
            return;
        }
        if ( !Dialogs.showQuestionDialog("Poisto", "Poistetaanko kaavio: " + kaavio.getOtsikko(), "Kyllä", "Ei")) return;
        seurantakaaviot.poista(kaavio);
        tableDatapisteet.initTable();
        int index = chooserKaaviot.getSelectedIndex();
        if (index == chooserKaaviot.getItems().size() - 1) index -= 1;
        haeKaaviot(-1);
        chooserKaaviot.setSelectedIndex(index);
    }
    
    
    /**
     * Poistaa henkilön
     */
    private void poistaHenkilo() {
        Henkilo henkilo = henkiloKohdalla;
        if ( henkilo == null ) {
            HenkilonLisaysController.eiValittu();
            return;
        }
        if ( !Dialogs.showQuestionDialog("Poisto", "Poistetaanko henkilö: " + henkilo.getNimi(), "Kyllä", "Ei")) return;
        seurantakaaviot.poista(henkilo);
        chooserKaaviot.clear();
        tableDatapisteet.initTable();
        int index = chooserHenkilot.getSelectedIndex();
        if (index == chooserHenkilot.getItems().size() - 1) index -= 1;
        hae(0);
        henkiloKohdalla = null;
    }
    
    
}
