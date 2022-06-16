package fxSeurantakaaviot;
	
import javafx.application.Application;
import javafx.stage.Stage;
import seurantakaaviot.Seurantakaaviot;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;
/**
 * @author Henri
 * @version 6.4.2021
 *  Pääohjelma Seurantakaaviot-ohjelman käynnistämiseksi
 */
public class SeurantakaaviotMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
		    final FXMLLoader ldr = new FXMLLoader(getClass().getResource("SeurantakaaviotGUIView.fxml"));
		    final Pane root = (Pane)ldr.load();
		    final SeurantakaaviotGUIController seurantakaaviotCtrl = (SeurantakaaviotGUIController)ldr.getController();
			// BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("SeurantakaaviotGUIView.fxml"));
			Scene scene = new Scene(root,600,400);
			scene.getStylesheets().add(getClass().getResource("seurantakaaviot.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Seurantakaaviot");
			
			Seurantakaaviot seurantakaaviot = new Seurantakaaviot();
			seurantakaaviotCtrl.setSeurantakaaviot(seurantakaaviot);
			
			seurantakaaviot.lueTiedostosta();
			
			seurantakaaviotCtrl.haeAlussa();
			
			
			primaryStage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * @param args ei käytössä
     */
	public static void main(String[] args) {
		launch(args);
	}
}
