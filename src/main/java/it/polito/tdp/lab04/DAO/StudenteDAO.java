package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	public Studente getStudente(int matricola) {
		final String sql ="SELECT * "
				+ "FROM studente "
				+ "WHERE matricola=?";
		Studente studente= null;
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);

			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				studente= new Studente(matricola, rs.getString("cognome"), rs.getString("nome"), rs.getString("cds"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
			
		return studente;
	}
	
	/*
	 * Ottengo i corsi a cui è iscritto lo studente
	 */
	
	public List<Corso> getCorsiFromStudente(Studente s) {
		final String sql= "SELECT * FROM iscrizione, corso WHERE iscrizione.codins=corso.codins AND matricola=?";
		List <Corso> corsiIscritti= new LinkedList<Corso>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, s.getMatricola());

			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Corso c= new Corso(rs.getString("codins"), rs.getString("nome"), rs.getInt("crediti"), rs.getInt("pd"));
				corsiIscritti.add(c);
			}
			conn.close();
			return corsiIscritti;
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
		
	}
	/*
	 * Ottengo true se lo studente è già iscritto a quel corso
	 */
	public boolean studenteIscrittoAlCorso(Studente s, Corso c) {
		final String sql ="SELECT codins FROM iscrizione, studente "
				+ "WHERE studente.matricola=iscrizione.matricola "
				+ "AND studente.matricola=?";
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, s.getMatricola());

			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("codins").equals(c.getCodins()))
					return true;
			}
			return false;
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
	}
	
	
	
}
