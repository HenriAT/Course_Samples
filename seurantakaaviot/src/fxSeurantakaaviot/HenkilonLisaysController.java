package fxSeurantakaaviot;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import seurantakaaviot.Henkilo;

/**
 * Henkilön lisäysikkunan hallinta
 * @author Henri
 * @version 10.4.2021
 *
 */
public class HenkilonLisaysController implements ModalControllerInterface<Henkilo>, Initializable {
    
    @FXML private Button buttonOK;
    @FXML private TextField editNimi;
    @FXML private TextField editHetu;
    @FXML private TextField editPuhelinnumero;
    @FXML private TextField editKatuosoite;
    @FXML private TextField editPostinumero;
    @FXML private TextField editPostitoimipaikka;
    @FXML private TextField editSahkoposti;
    @FXML private TextField editLisatietoja;
    @FXML private Label labelVirhe;
    
    
    /**
     * OK-napin toiminta
     */
    @FXML private void handleOK() {
        if (henkiloKohdalla != null && henkiloKohdalla.getNimi().trim().equals("")) {
            naytaVirhe("Nimi ei saa olla tyhjä");
            return;
            }
        onMuutoksia = true;
        ModalController.closeStage(labelVirhe);    
    }
    
    
    /**
     * Peruuta-napin toiminta
     */
    @FXML private void handlePeruuta() {
        ModalController.closeStage(labelVirhe);
    }

    
    @Override
    public void handleShown() {
        editNimi.requestFocus();
        
    }


    @Override
    public Henkilo getResult() {
        if (onMuutoksia) return henkiloKohdalla;
        return null;
    }

    
    @Override
    public void setDefault(Henkilo oletus) {
        henkiloKohdalla = oletus;
        naytaHenkilo(henkiloKohdalla);
        
    }

    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alusta();
        
    }

    //=============================================================================================================//
    
    private Henkilo henkiloKohdalla;
    private TextField[] edits;
    private Boolean onMuutoksia = false;
    
    
    /**
     * Alustus
     */
    private void alusta() {
        edits = new TextField[] {editNimi, editHetu, editPuhelinnumero, editKatuosoite, editPostinumero, editPostitoimipaikka, editSahkoposti, editLisatietoja};
        int i = 0;
        for (TextField edit : edits) {
            int k = ++i;
            edit.setOnKeyReleased(e -> kasitteleMuutosJaseneen(k, (TextField)(e.getSource())));
        }
    }
    
    
    /**
     * Käsitellään jäseneen tullut muutos
     * @param k kenttää vastaava tapaus
     * @param edit muuttunut kenttä
     */
    private void kasitteleMuutosJaseneen(int k, TextField edit) {
        if (henkiloKohdalla == null) return;
        String s = edit.getText();
        String virhe = null;
        virhe = henkiloKohdalla.aseta(k, s);
        if (virhe == null) {
            Dialogs.setToolTipText(edit, "");
            edit.getStyleClass().removeAll("virhe");
            naytaVirhe(virhe);
        } else {
            Dialogs.setToolTipText(edit, virhe);
            edit.getStyleClass().add("virhe");
            naytaVirhe(virhe);
        }
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
     * Täyttää henkilönmuokkausikkunan tietokentät
     * @param henkilo henkilö, jota muokataan
     */
    private void naytaHenkilo(Henkilo henkilo) {
        if (henkilo == null) return;
        for (int i = 0; i < edits.length; i++) {
            edits[i].setText(henkilo.anna(i+1));
        }
          
    } 
    
    
    /**
     * Luodaan henkilön kysymysdialogi ja palautetaan sama tietue muutettuna tai null
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @param oletus mitä dataa näytetään oletuksena
     * @return null, jos painetaan Peruuta, muuten täytetty tietue 
     */
    public static Henkilo kysyHenkilo(Stage modalityStage, Henkilo oletus) {
        var resurssi = SeurantakaaviotGUIController.class.getResource("HenkilonLisays.fxml");
        return ModalController.showModal(resurssi, "Henkilö", modalityStage, oletus, null);
    }
    
    
    /**
     * Jos henkilöä ei ole valittu, niin ilmoitetaan tästä
     */
    public static void eiValittu() {
        Dialogs.showMessageDialog("Henkilöä ei ole valittu");
    }
    
    

}
