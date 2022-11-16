package it.polito.tdp.lab04.DAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class TestDB {

	public static void main(String[] args) {

		/*
		 * 	This is a main to check the DB connection
		 */
	//STUDENTEDAO	
		StudenteDAO sdao = new StudenteDAO();
		
		//getStudente()
		Studente s= sdao.getStudente(146101);
		System.out.println("\n"+s.toString());
		
		System.out.println("\n");
		
		//getCorsiFromStudente
		System.out.println("Elenco corsi seguiti dallo studente"+ s.getMatricola()+": \n");
		List <Corso> corsiStudente=sdao.getCorsiFromStudente(s);
		for(Corso c: corsiStudente)
			System.out.println(c.toString());
		
		System.out.println("\n");
		
	//CORSODAO	
		CorsoDAO cdao = new CorsoDAO();
		
		//getTuttiICorsi()
		System.out.println("Elenco tutti i corsi: \n");
		List <Corso> corsi=cdao.getTuttiICorsi();
		for(Corso c: corsi)
			System.out.println(c.toString());
		
		System.out.println("\n");
		
		//getCorso()
		Corso c= cdao.getCorso(null);
		System.out.println("\n"+c.toString());
		
		System.out.println("\n");
		
		//getStudentiIscrittiAlCorso()
		System.out.println("Elenco studenti iscritti a "+ c.getNome()+": \n");
		List <Studente> studentiIscritti= cdao.getStudentiIscrittiAlCorso(c);
		for(Studente st: studentiIscritti)
			System.out.println(st.toString());
		
		System.out.println("\n");
		
		//iscriviStudenteAlCorso()
		
		boolean ris= cdao.inscriviStudenteACorso(s,c);
		System.out.println("Lo studente "+s.getMatricola()+" è stato iscritto al corso "+ c.getCodins()+" ?\n"+ris);
		
		System.out.println("\n");
		
		//studenteIscrittiACorso()
		boolean ris2= sdao.studenteIscrittoAlCorso(s, c);
		System.out.println("Lo studente "+s.getMatricola()+" è iscritto al corso "+ c.getCodins()+" ?\n"+ris2);
		
		System.out.println("\n");
	
		
		
	}

}
