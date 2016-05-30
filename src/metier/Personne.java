package metier;

import java.util.Date;
//Commentaire
public abstract class Personne {
	private String nom;
	private String prenom;
	private String sexe;
	private Date dateNaissance;
	
	public Personne(String nom, String prenom, String sexe, Date date){
		setNom(nom);
		this.prenom = prenom;
		this.sexe = sexe;
		this.dateNaissance = date;
	}
	
	public String toString(){
		return "Personne " 
				+"\n\t\t Nom " + this.nom
				+"\n\t\t Prenom " + this.prenom
				+"\n\t\t Sexe " + this.sexe
				+"\n\t\t Date de naissance " + this.dateNaissance.toString()
				+"\n";
	}
	
	public String getNom(){
		return nom;
	}
	
	private void setNom(String nom){
		for(int i = 0; i < nom.length(); i++){
			if(!Character.isLetter(nom.charAt(i))){
				try {
					throw new BiblioException("Le nom est erroné");
				} catch (BiblioException e) {
					e.printStackTrace();
				}
			}
		}
		this.nom = nom;
	}
	
	public String getPrenom(){
		return prenom;
	}
	
	private void setPrenom(String prenom){
		for(int i = 0; i < prenom.length(); i++){
			if(!Character.isLetter(prenom.charAt(i))){
				try {
					throw new BiblioException("Le prènom est erroné");
				} catch (BiblioException e) {
					e.printStackTrace();
				}
			}
		}
		this.prenom = prenom;
	}
	
	public String getSexe(){
		return sexe;
	}
	
	private void setSexe(String sexe){
		for(int i = 0; i < sexe.length(); i++){
			if(!Character.isLetter(sexe.charAt(i))){
				try {
					throw new BiblioException("Le sexe est erroné");
				} catch (BiblioException e) {
					e.printStackTrace();
				}
			}
		}
		this.sexe = sexe;
	}
	
	public Date getDateDeNaissance(){
		return this.dateNaissance;
	}
}
