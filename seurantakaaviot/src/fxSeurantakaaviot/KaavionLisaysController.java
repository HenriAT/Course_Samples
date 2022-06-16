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
import seurantakaaviot.Kaavio;

/**
 * Kaavionlisäysikkunan hallinta
 * @author Henri
 * @version 14.4.2021
 *
 */
public class KaavionLisaysController implements ModalControllerInterface<Kaavio>, Initializable {
    
    @FXML private TextField editOtsikko;
    @FXML private TextField editTyyppi;
    @FXML private TextField editVaaka;
    @FXML private TextField editPysty;
    @FXML private Label labelVirhe;
    
    
    /**
     * OK-napin toiminta
     */
    @FXML private void handleOK() {
        if (kaavioKohdalla != null && 
           (kaavioKohdalla.getOtsikko().trim().equals("") || kaavioKohdalla.getX().trim().equals("") || kaavioKohdalla.getY().trim().equals(""))
           ) {
            naytaVirhe("Ei saa olla tyhjä");
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
    public Kaavio getResult() {
        if (onMuutoksia) return kaavioKohdalla;
        return null;
    }

    
    @Override
    public void handleShown() {
        editOtsikko.requestFocus();
    }

    
    @Override
    public void setDefault(Kaavio oletus) {
        kaavioKohdalla = oletus;
        naytaKaavio(kaavioKohdalla);
    }
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alusta(); 
    }
    
  //=============================================================================================================//
    
    private Kaavio kaavioKohdalla;
    private TextField[] edits;
    private boolean onMuutoksia = false;

    /**
    * Alustus
    */
   private void alusta() {
       edits = new TextField[] {editOtsikko, editTyyppi, editVaaka, editPysty};
       int i = 0;
       for (TextField edit : edits) {
           int k = ++i;
           edit.setOnKeyReleased(e -> kasitteleMuutosKaavioon(k, (TextField)(e.getSource())));
       }
   }
    
   
   /**
    * Käsitellään kaavioon tullut muutos
    * @param k kenttää vastaava tapaus
    * @param edit muuttunut kenttä
    */
   private void kasitteleMuutosKaavioon(int k, TextField edit) {
       if (kaavioKohdalla == null) return;
       String s = edit.getText();
       kaavioKohdalla.aseta(k, s);
       edit.getStyleClass().removeAll("virhe");
       naytaVirhe(null);
   }
   
   
   /**
    * Täyttää kaavionmuokkausikkunan tietokentät
    * @param henkilo henkilö, jota muokataan
    */
   private void naytaKaavio(Kaavio kaavio) {
       if (kaavio == null) return;
       for (int i = 0; i < edits.length; i++) {
           edits[i].setText(kaavio.anna(i+1));
       }
         
   } 
   
   
   /**
    * Luodaan kaavion kysymysdialogi ja palautetaan sama tietue muutettuna tai null
    * @param modalityStage mille ollaan modaalisia, null = sovellukselle
    * @param oletus mitä dataa näytetään oletuksena
    * @return null, jos painetaan Peruuta, muuten täytetty tietue 
    */
   public static Kaavio kysyKaavio(Stage modalityStage, Kaavio oletus) {
       var resurssi = SeurantakaaviotGUIController.class.getResource("KaavionLisays.fxml");
       return ModalController.showModal(resurssi, "Kaavio", modalityStage, oletus, null);
   }
   
   
   /**
    * Jos kaaviota ei ole valittu, niin ilmoitetaan tästä
    */
   public static void eiValittu() {
       Dialogs.showMessageDialog("Kaaviota ei ole valittu");
   }
   
   
   /**
    * Jos henkilöä ei ole valittu, niin ilmoitetaan tästä
    */
   public static void eiValittuHenkiloa() {
       Dialogs.showMessageDialog("Valitse henkilö, jolle kaavio lisätään");
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
    
}
