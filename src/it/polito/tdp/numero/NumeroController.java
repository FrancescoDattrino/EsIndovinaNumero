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
    private Button btnProva;

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
    	
    	String pstr = txtProva.getText() ;

    	try {
    		prova = Integer.parseInt(pstr) ;
    	} catch( NumberFormatException e) {
    		lblTentativi.setText("Formato numero errato");
    		return ;
    	}
    	
    	// 'prova' is valid
    	
    	tentativi++ ;
		lblTentativi.setText(
				String.format("Tentativi %d/%d", 
						tentativi,
						maxTentativi));
		pbTentativi.setProgress((double)tentativi/maxTentativi);
    	
    	if( prova == segreto ) {
    		lblVintoPerso.setText("Hai indovinato!");
    		btnStartStop.setText("Inizia");
    		btnProva.setDisable(true);
    		txtProva.setDisable(true);
    		
    		inGame = false ;
    	} else if (tentativi == maxTentativi) {
    		lblVintoPerso.setText("Hai perso!");
    		btnStartStop.setText("Inizia");
    		
    		boxDifficolta.setDisable(false);
    		
    		lblTentativi.setText(String.format("Il numero segreto era: %d", segreto));
    		btnProva.setDisable(true);
    		txtProva.setDisable(true);
    		inGame = false ;
    	} else if (prova < segreto) {
    		lblVintoPerso.setText("Troppo basso...");
    	} else { // prova > segreto
    		lblVintoPerso.setText("Troppo alto...");
    	}

    }

    @FXML
    void doStartStop(ActionEvent event) {
    	
    	if(inGame) {
    		// Abbandona
    		lblVintoPerso.setText("Hai abbandonato");
    		lblVintoPerso.setVisible(true);
    		
    		btnStartStop.setText("Inizia");
    		
    		boxDifficolta.setDisable(false);
    		
    		lblTentativi.setText(String.format("Il numero segreto era: %d", segreto));
    		btnProva.setDisable(true);
    		txtProva.setDisable(true);
    		
    		
    		inGame = false ;
    	} else {
    		// Inizia
    		Integer diff = boxDifficolta.getValue() ;
    		if(diff==null) {
    			lblTentativi.setText("Seleziona un valore");
    			return ;
    		}
    		difficolta = diff ;
    		
    		segreto = (int)(Math.random()*difficolta)+1 ;
    		tentativi = 0 ;
    		maxTentativi = (int)(Math.log(difficolta)/Math.log(2.0)+1) ;
    		
    		lblTentativi.setText(
    				String.format("Tentativi %d/%d", 
    						tentativi,
    						maxTentativi));
    		pbTentativi.setProgress(0.0);
    		
    		lblVintoPerso.setText("");
    		
    		btnStartStop.setText("Abbandona");
    		boxDifficolta.setDisable(true);
    		
    		btnProva.setDisable(false);
    		txtProva.setDisable(false);
    		txtProva.setText("");
    		
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
        
        lblTentativi.setText("");
        lblVintoPerso.setText("");
        
        btnProva.setDisable(true);
        btnStartStop.setDisable(false);
        txtProva.setDisable(true);
        
        pbTentativi.setProgress(0.0);
        
        inGame = false ;
        
    }
}
