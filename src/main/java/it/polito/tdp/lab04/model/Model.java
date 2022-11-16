package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

	private StudenteDAO studenteDAO;
	private CorsoDAO corsoDAO;
	
	
	public Model() {
		this.studenteDAO =new StudenteDAO();
		this.corsoDAO = new CorsoDAO();
	}


	public List<Corso> getTuttiICorsi() {
		return corsoDAO.getTuttiICorsi();
		
	}
	
	public Corso getCorso(Corso corso) {
		return corsoDAO.getCorso(corso);
	}

	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		return corsoDAO.getStudentiIscrittiAlCorso(corso);
	}
	
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		return corsoDAO.inscriviStudenteACorso(studente, corso);
	}

	public Studente getStudente(int matricola) {
		return studenteDAO.getStudente(matricola);
	}

	public List<Corso> getCorsiFromStudente(Studente s) {
		return studenteDAO.getCorsiFromStudente(s);
	}
	
	public boolean studenteIscrittoAlCorso(Studente s, Corso c) {
		return studenteDAO.studenteIscrittoAlCorso(s, c);
	}
	

	
}
