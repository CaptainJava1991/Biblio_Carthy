package metier;

import java.util.Date;

public class EmpruntEnCoursDB {
	private Date dateEmprunt;
	private int idUtilisateur;
	private int idExemplaire;
	
	public EmpruntEnCoursDB(Date dateEmprunt,int idUtilisateur,int idExemplaire){
		this.dateEmprunt = dateEmprunt;
		this.idUtilisateur = idUtilisateur;
		this.idExemplaire = idExemplaire;
	}

	public Date getDateEmprunt() {
		return dateEmprunt;
	}

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public int getIdExemplaire() {
		return idExemplaire;
	}
}
