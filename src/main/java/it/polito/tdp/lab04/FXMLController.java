package it.polito.tdp.lab04;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.DAO.StudenteDAO;
import it.polito.tdp.lab04.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class FXMLController {

	private List<Corso> corsi;
	
	private Model model;
	
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnCercaIscrittiCorso;

    @FXML
    private Button btnIscrivi;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnCercaNome;

    @FXML
    private ComboBox<Corso> cmbCorsi;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextField txtMatricola;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtRisultato;

    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {
    	txtRisultato.clear();
		txtNome.clear();
		txtCognome.clear();
		try {
			Corso c= model.getCorso(cmbCorsi.getValue());
			if(c==null) {
    	    	txtRisultato.appendText("Non è stato trovato nessuno corso chiamato"+cmbCorsi.getValue().getNome());
    	    	return;
			}
        	List<Studente> stResult= model.getStudentiIscrittiAlCorso(c);
        	
        	StringBuilder sb = new StringBuilder();

			for (Studente studente : stResult) {

				sb.append(studente.getMatricola());
				sb.append(studente.getCognome());
				sb.append(studente.getNome());
				sb.append(studente.getCDS());
				sb.append(" \n ");
			}

			txtRisultato.appendText(sb.toString());
        	
        	
		}catch(RuntimeException e) {
			txtRisultato.setText("ERRORE DI CONNESSIONE AL DATABASE!");
		}
    	
    }
    

    @FXML
    void doCercaNome(ActionEvent event) {
    	txtRisultato.clear();
		txtNome.clear();
		txtCognome.clear();

		try {
			int matricola= Integer.parseInt(txtMatricola.getText());
    	    Studente s =model.getStudente(matricola);
    	    if(s==null) {
    	    	txtRisultato.appendText("Non è stato trovato nessuno studente con matricola "+matricola);
    	    	return;
    	    }
    	    
        	txtNome.setText(s.getNome());
        	txtCognome.setText(s.getCognome());
		}catch(NumberFormatException e){
			txtRisultato.setText("Inserire una matricola nel formato corretto.");
		}catch(RuntimeException e){
			txtRisultato.setText("ERRORE DI CONNESSIONE AL DATABASE!");
			}
    }
    
   
    @FXML
    void doCercaCorsi(ActionEvent event) {

    	txtRisultato.clear();
		txtNome.clear();
		txtCognome.clear();
		try {
			int matricola=Integer.parseInt(txtMatricola.getText());
			Studente s= model.getStudente(matricola);
			if(s==null) {
    	    	txtRisultato.appendText("Non è stato trovato nessuno studente con matricola "+matricola);
    	    	return;
			}
        	List<Corso> crResult= model.getCorsiFromStudente(s);
        	
        	StringBuilder sb = new StringBuilder();

			for (Corso c : crResult) {

				sb.append(c.getCodins());
				sb.append(c.getNome());
				sb.append(c.getCrediti());
				sb.append(c.getPd());
				sb.append(" \n ");
			}

			txtRisultato.appendText(sb.toString());
        	
        	
		}catch(RuntimeException e) {
			txtRisultato.setText("ERRORE DI CONNESSIONE AL DATABASE!");
		}
    	
    	
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	txtRisultato.clear();
    	try {
    		if(txtMatricola.getText().isEmpty()) {
    			txtRisultato.setText("Inserire una matricola.");
				return;
    		}
    		if(cmbCorsi.getValue()==null) {
    			txtRisultato.setText("Selezionare uno dei corsi.");
				return;
    		}
    		
    		Studente s = model.getStudente(Integer.parseInt(txtMatricola.getText()));
        	Corso c = model.getCorso(cmbCorsi.getValue());
        	if(s==null) {
        		txtRisultato.appendText("Non è stato trovato nessuno studente con matricola "+Integer.parseInt(txtMatricola.getText()));
    	    	return;
        	}
        	if(c==null) {
        		txtRisultato.appendText("Non è stato trovato nessun corso con nome "+cmbCorsi.getValue().getNome());
    	    	return;
        	}
        	
        	if(model.studenteIscrittoAlCorso(s, c)) {
        		txtRisultato.appendText("Lo studente è già iscritto");
    	    	return;
        	}
        	
        	
        	boolean iscritto=model.inscriviStudenteACorso(s, c);
        	if(iscritto) {
        		txtRisultato.appendText("Iscrizione avvenuta con successo!");
    	    	return;
        	}else {
        		txtRisultato.appendText("ERRORE:non è stato possibile iscrivere lo studente al corso selezionato!");
    	    	return;
        	}
    	}catch(RuntimeException e) {
			txtRisultato.setText("ERRORE DI CONNESSIONE AL DATABASE!");
		}
    	
    }

    
    @FXML
    void doReset(ActionEvent event) {
    	 txtCognome.clear();
    	 txtMatricola.clear();
    	 txtNome.clear();
    	 txtRisultato.clear();
    	 cmbCorsi.getSelectionModel().clearSelection();;
    }
    
    private void setCmbItems() {
    	corsi= model.getTuttiICorsi();
    	Collections.sort(corsi);
    	cmbCorsi.getItems().addAll(corsi);
    }  

    @FXML
    void initialize() {
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaNome != null : "fx:id=\"checkNessunCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbCorsi != null : "fx:id=\"cmbCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
    
    }
    

    public void setModel(Model model) {
    	this.model=model;
    	setCmbItems();
    }
	
    
}
