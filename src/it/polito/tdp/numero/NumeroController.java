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
    private Button btnProva;

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
    
    /**
     * Modifica lo stato della partita da attiva a non-attiva
     * e viceversa, aggiornando di conseguenza tutti gli elementi
     * dell'interfaccia utente (label, choice box, bottoni, ecc).
     * 
     * @param state {@code true} per iniziare la partita, {@code false} per terminarla
     */
    private void changeGameState(boolean state) {
    	
    	
    	inGame = state ;
    	
    	if(inGame==false) {
    		boxDifficolta.setDisable(false);
    		btnStartStop.setText("Inizia");
    		txtProva.setDisable(true);
    		btnProva.setDisable(true);
    	} else {
    		lblTentativi.setText(
    				String.format("Tentativi %d/%d", 
    						tentativi,
    						maxTentativi));
    		pbTentativi.setProgress(0.0);
    		
    		lblVintoPerso.setText("");
    		
    		btnStartStop.setText("Abbandona");
    		boxDifficolta.setDisable(true);
    		
    		txtProva.setDisable(false);
    		txtProva.setText("");
    		btnProva.setDisable(false);
    	}
    }

    @FXML
    void doProva(ActionEvent event) {
    	
    	try {
    		prova = Integer.parseInt( txtProva.getText() ) ;
    	} catch(NumberFormatException e) {
    		lblTentativi.setText("Formato errato") ;
    		return ;
    	}
    	
    	tentativi++ ;
		lblTentativi.setText(
				String.format("Tentativi %d/%d", 
						tentativi, maxTentativi));
		pbTentativi.setProgress((double)tentativi/maxTentativi);

    	
    	if( prova==segreto ) {
    		// hai vinto
    		lblVintoPerso.setText("Hai vinto");
    		changeGameState(false);
    		
    	} else if( tentativi == maxTentativi ) {
    		// hai perso, esauriti tentativi
    		lblVintoPerso.setText("Hai perso");
    		lblTentativi.setText(String.format(
    				"Il numero era %d", segreto));
    		
    		changeGameState(false);
    	} else if( prova<segreto ) {
    		// troppo basso
    		lblVintoPerso.setText("Troppo basso");
    	} else { // (prova>segreto)
    		// troppo alto
    		lblVintoPerso.setText("Troppo alto");
    	}

    }

    @FXML
    void doStartStop(ActionEvent event) {
    	
    	if(inGame) {
    		// Abbandona
    		lblVintoPerso.setText("Hai abbandonato");
    		lblTentativi.setText(String.format(
    				"Il numero era %d", segreto));
    		
    		changeGameState(false);
    	} else {
    		// Inizia
    		if(boxDifficolta.getValue()==null) {
    			lblTentativi.setText("Seleziona un valore");
    			return ;
    		}
    		difficolta = boxDifficolta.getValue() ;
    		segreto = (int)(Math.random()*difficolta)+1 ;
    		tentativi = 0 ;
    		maxTentativi = (int)(Math.log(difficolta)/Math.log(2.0)+1) ;
    		
    		changeGameState(true);
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
