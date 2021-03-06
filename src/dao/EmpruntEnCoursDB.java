package dao;

import java.util.Date;

import metier.BiblioException;
import metier.EmpruntEnCours;

public class EmpruntEnCoursDB extends EmpruntEnCours{
	private int idUtilisateur;
	private int idExemplaire;
	
	public EmpruntEnCoursDB(Date dateEmprunt, int idUtilisateur, int idExemplaire)
			throws BiblioException {
		super(dateEmprunt, null, null);
		this.idUtilisateur = idUtilisateur;
		this.idExemplaire = idExemplaire;
	}

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public int getIdExemplaire() {
		return idExemplaire;
	}
	
	public String toString(){
		return "EmpruntEnCoursDB \t\n"
				+ "\t\t idUtilisateur: " + idUtilisateur + "\n" 
				+ "\t\t idExemplaire: " + idExemplaire + "\n"
				+ "\t\t DateEmprunt: " + getDateEmprunt() + "\n\n";
	}
}
