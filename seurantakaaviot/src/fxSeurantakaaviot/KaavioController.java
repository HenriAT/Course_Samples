package fxSeurantakaaviot;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import seurantakaaviot.Datapiste;
/**
 * Kaavion piirtoikkunan toiminta
 * @author Henri
 * @version 18.2.2021
 *
 */
public class KaavioController implements ModalControllerInterface<List<Datapiste>>, Initializable {
    
    @FXML private CategoryAxis x;
    @FXML private NumberAxis y;
    @FXML private LineChart<String, Number> kaavio;
    @FXML private Button buttonSulje;
    
    /**
     * Tulosta-napin toiminta
     */
    @FXML private void handleTulosta() {
        Dialogs.showMessageDialog("Ei osata vielä tulostaa");
        // TODO: Muista asettaa tulostustoiminto
    }
    
    /**
     * Sulje-napin toiminta
     */
    @FXML private void handleSulje() {
        ModalController.closeStage(buttonSulje);
    }

    @Override
    public List<Datapiste> getResult() {
        return null;
    }

    @Override
    public void handleShown() {
        //
    }

    @Override
    public void setDefault(List<Datapiste> oletus) {
        datapisteet = oletus;
        alustus();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //
    }
    
    //=============================================================================================================//
    
    private List<Datapiste> datapisteet;
    
    
    /**
     * Alustus
     */
    @SuppressWarnings("unchecked")
    private void alustus() {
        
        kaavio.setLegendVisible(false);
        kaavio.setCreateSymbols(false);

        Series<String, Number> series = new XYChart.Series<String, Number>();
        
        for (Datapiste datapiste : datapisteet) {
            series.getData().add(new Data<String, Number>(datapiste.paivamaara(), datapiste.getArvo()));
        }
        
        kaavio.getData().addAll(series);
    }
    
    /**
     * Luodaan kaavioikkuna
     * @param otsikko kaavion otsikko
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @param oletus mitä dataa näytetään oletuksena
     * @return null
     */
    public static List<Datapiste> avaaKaavio(String otsikko, Stage modalityStage, List<Datapiste> oletus) {
        var resurssi = SeurantakaaviotGUIController.class.getResource("Kaavio.fxml");
        return ModalController.showModal(resurssi, otsikko, modalityStage, oletus, null);
    }
    
    
    /**
     * Jos kaaviota ei ole valittu, niin ilmoitetaan tästä
     */
    public static void eiValittu() {
        Dialogs.showMessageDialog("Kaaviota ei ole valittu");
    }

    
}
