package metier;

import java.util.Date;

public class Employe extends Utilisateur {
	private String codeMatricule;
	private EnumCategorieEmploye categorieEmploye;
	
	public Employe(String nom, String prenom, String sexe, Date date){
		super(nom, prenom, sexe, date);
	}
	
	public String toString(){
		return "Utilisateur " 
				+"\n\t\t Nom " + getNom()
				+"\n\t\t Prenom " + getPrenom()
				+"\n\t\t Pseudonyme " + getPseudonyme()
				+"\n\t\t Sexe " + getSexe()
				+"\n\t\t Date de naissance " + getDateDeNaissance().toString()
				+"\n\t\t Code Matricule " + this.codeMatricule
				+"\n\t\t Categorie Employe " + this.categorieEmploye
				+"\n\t\t n°Utilisateur " +  getIdUtilisateur()
				+"\n";
	}

	@Override
	public boolean isConditionsPretAcceptees() {
		return true;
	}

}
