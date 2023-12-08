package com.sopra;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CompteBancaire {
	
	private int numCompte;
	public LocalDateTime getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(LocalDateTime dateCreate) {
		this.dateCreate = dateCreate;
	}

	private String proprietaire;
	private double solde;
	private String typeCompte;
	
	

	private LocalDateTime dateCreate;
	public CompteBancaire(int numCompte, String proprietaire, double solde, String typeCompte, LocalDateTime dateCreate) {
		super();
		this.numCompte = numCompte;
		this.proprietaire = proprietaire;
		this.solde = solde;
		this.typeCompte = typeCompte;
		this.dateCreate = dateCreate;
	}
	
	public CompteBancaire() {
	}

	public int getNumCompte() {
		return numCompte;
	}

	public void setNumCompte(int numCompte) {
		this.numCompte = numCompte;
	}

	public String getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(String proprietaire) {
		this.proprietaire = proprietaire;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public String getTypeCompte() {
		return typeCompte;
	}

	public void setTypeCompte(String typeCompte) {
		this.typeCompte = typeCompte;
	}

	@Override
	public String toString() {
		return "CompteBancaire [numCompte=" + numCompte + ", proprietaire=" + proprietaire + ", solde=" + solde
				+ ", typeCompte=" + typeCompte + ", dateCreate=" + dateCreate + "]";
	}

	
	

}
