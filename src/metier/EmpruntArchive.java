package metier;

import java.util.Date;

public class EmpruntArchive {
	private Date dateRestitution;
	private Date dateEmprunt;
	private Utilisateur utilisateur;
	private Exemplaire exemplaire;
	
	public EmpruntArchive(Date dateRestitution, Date dateEmprunt, 
			Utilisateur u, Exemplaire ex){
		
		this.dateRestitution = dateRestitution;
		this.dateEmprunt = dateEmprunt;
		this.utilisateur = u;
		this.exemplaire = ex;
	}
	
	public String toString(){
		return "Emprunt Archivé"
				+"\n\t\t DATE DE RESTITUTION " + this.dateRestitution
				+"\n\t\t DATE D'EMPRUNT " + this.dateEmprunt
				+"\n\t\t " + this.utilisateur
				+"\n\t\t "  + this.exemplaire
				+"\n";
	}
}
