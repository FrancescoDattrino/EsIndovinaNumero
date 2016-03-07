package it.polito.tdp.numero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

public class NumeroController {
	
	private int difficolta ; // scelto dalla tendina
	private int segreto ; // da indovinare
	private int prova ; // tentativo utente
	private int tentativi ; // già fatti
	private int maxTentativi ; 
	
	private boolean inGame ; // partita iniziata?
	

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Integer> boxDifficolta;

    @FXML
    private Button btnStartStop;

    @FXML
    private TextField txtProva;

    @FXML
    private Label lblVintoPerso;

    @FXML
    private Label lblTentativi;

    @FXML
    private ProgressBar pbTentativi;

    @FXML
    void doProva(ActionEvent event) {

    }

    @FXML
    void doStartStop(ActionEvent event) {
    	
    	if(inGame) {
    		// Abbandona
    		inGame = false ;
    	} else {
    		// Inizia
    		difficolta = boxDifficolta.getValue() ;
    		segreto = (int)(Math.random()*difficolta)+1 ;
    		tentativi = 0 ;
    		maxTentativi = (int)(Math.log(difficolta)/Math.log(2.0)+1) ;
    		
    		lblTentativi.setText(
    				String.format("Tentativi %d/%d", 
    						tentativi,
    						maxTentativi));
    		
    		btnStartStop.setText("Abbandona");
    		boxDifficolta.setDisable(true);
    		
    		inGame = true ;
    	}
    	

    }

    @FXML
    void initialize() {
        assert boxDifficolta != null : "fx:id=\"boxDifficolta\" was not injected: check your FXML file 'Numero.fxml'.";
        assert btnStartStop != null : "fx:id=\"btnStartStop\" was not injected: check your FXML file 'Numero.fxml'.";
        assert txtProva != null : "fx:id=\"txtProva\" was not injected: check your FXML file 'Numero.fxml'.";
        assert lblVintoPerso != null : "fx:id=\"lblVintoPerso\" was not injected: check your FXML file 'Numero.fxml'.";
        assert lblTentativi != null : "fx:id=\"lblTentativi\" was not injected: check your FXML file 'Numero.fxml'.";
        assert pbTentativi != null : "fx:id=\"pbTentativi\" was not injected: check your FXML file 'Numero.fxml'.";

        boxDifficolta.getItems().addAll(10,100,1000) ;
        inGame = false ;
        
    }
}
