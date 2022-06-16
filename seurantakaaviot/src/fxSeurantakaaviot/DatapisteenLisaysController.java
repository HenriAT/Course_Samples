package fxSeurantakaaviot;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import seurantakaaviot.Datapiste;

/**
 * Datapisteenlisäysikkunen hallinointi
 * @author Henri
 * @version 15.4.2021
 *
 */
public class DatapisteenLisaysController implements ModalControllerInterface<Datapiste>, Initializable {

    @FXML private TextField editPaiva;
    @FXML private TextField editKuukausi;
    @FXML private TextField editVuosi;
    @FXML private TextField editArvo;
    @FXML private Label labelVirhe;
    
    
    /**
     * OK-napin toiminta
     */
    @FXML private void handleOK() {
        if(labelVirhe.getText().equals("") == false) return;
        if (onkoJokuKenttaTyhja()) {
            naytaVirhe("Ei saa olla tyhjä");
            return;
        }
        onMuutettu = true;
        ModalController.closeStage(labelVirhe); 
    }
    
    
    /**
     * Peruuta-napin toiminta
     */
    @FXML private void handlePeruuta() {
        ModalController.closeStage(labelVirhe);
    }
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alusta();  
    }
    
    
    @Override
    public Datapiste getResult() {
        if (onMuutettu) return datapisteKohdalla;
        return null;
    }
    
    
    @Override
    public void handleShown() {
        editPaiva.requestFocus();
    }
    
    
    @Override
    public void setDefault(Datapiste oletus) {
        datapisteKohdalla = oletus;
        naytaDatapiste(datapisteKohdalla);
    }
    
//=============================================================================================================//
    
    private Datapiste datapisteKohdalla;
    private TextField[] edits;
    private Boolean onMuutettu = false;
    
    
    /**
     * Alustus
     */
    private void alusta() {
        edits = new TextField[] {editPaiva, editKuukausi, editVuosi, editArvo};
        int i = 0;
        for (TextField edit : edits) {
            int k = ++i;
            edit.setOnKeyReleased(e -> kasitteleMuutosDatapisteeseen(k, (TextField)(e.getSource())));
        }
    }
    
    
    /**
     * Käsitellään datapisteeseen tullut muutos
     * @param k kenttää vastaava tapaus
     * @param edit muuttunut kenttä
     */
    private void kasitteleMuutosDatapisteeseen(int k, TextField edit) {
        if (datapisteKohdalla == null) return;
        String s = edit.getText();
        String virhe = null;
        virhe = datapisteKohdalla.aseta(k, s);
        if (virhe == null) {
            edit.getStyleClass().removeAll("virhe");
            naytaVirhe(virhe);
        } else {
            edit.getStyleClass().add("virhe");
            naytaVirhe(virhe);
        }
    }
    
    
    /**
     * Täyttää datapisteenmuokkausikkunan tietokentät
     * @param datapiste datapiste, jota muokataan
     */
    private void naytaDatapiste(Datapiste datapiste) {
        if (datapiste == null) return;
        for (int i = 0; i < edits.length; i++) {
            edits[i].setText(datapiste.anna(i+1));
        }
          
    } 
    
    
    /**
     * Luodaan datapisteen kysymysdialogi ja palautetaan sama tietue muutettuna tai null
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @param oletus mitä dataa näytetään oletuksena
     * @return null, jos painetaan Peruuta, muuten täytetty tietue 
     */
    public static Datapiste kysyDatapiste(Stage modalityStage, Datapiste oletus) {
        var resurssi = SeurantakaaviotGUIController.class.getResource("DatapisteenLisays.fxml");
        return ModalController.showModal(resurssi, "Datapiste", modalityStage, oletus, null);
    }
    
    
    /**
     * Näyttää virheen
     * @param virhe havaittu virhe
     */
    private void naytaVirhe(String virhe) {
        if (virhe == null || virhe.isEmpty()) {
            labelVirhe.setText("");
            labelVirhe.getStyleClass().removeAll("virhe");
            return;
        }
        labelVirhe.setText(virhe);
        labelVirhe.getStyleClass().add("virhe");
    }
    
    
    /**
     * Jos datapistettä ei ole valittu, niin ilmoitetaan tästä
     */
    public static void eiValittu() {
        Dialogs.showMessageDialog("Datapistettä ei ole valittu");
    }
    
    
    /**
     * Tarkistaa, onko joku tietokenttä tyhjä
     * @return totuusarvon
     */
    private boolean onkoJokuKenttaTyhja() {
        for (TextField edit : edits) {
            if (edit.getText().equals("")) return true;
        }
        return false;
    }
    
    
    /**
     * Jos kaaviota ei ole valittu, niin ilmoitetaan tästä
     */
    public static void eiValittuKaaviota() {
        Dialogs.showMessageDialog("Valitse kaavio, jolle datapiste lisätään");
    }
    
}
