package it.polito.tdp.lab04.model;

public class Studente {

	private int matricola;
	private String cognome;
	private String nome;
	private String CDS;
	
	public Studente(int matricola) {
		this.matricola = matricola;
	}
	

	public Studente(int matricola, String cognome, String nome, String cDS) {
		super();
		this.matricola = matricola;
		this.cognome = cognome;
		this.nome = nome;
		CDS = cDS;
	}


	public int getMatricola() {
		return matricola;
	}

	public void setMatricola(int matricola) {
		this.matricola = matricola;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCDS() {
		return CDS;
	}

	public void setCDS(String cDS) {
		CDS = cDS;
	}

	@Override
	public String toString() {
		return "Studente cognome=" + cognome + ", nome=" + nome ;
	}
	
	
}
