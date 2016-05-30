package metier;

import java.util.ArrayList;
import java.util.Date;

public abstract class Utilisateur extends Personne {
	private int idUtilisateur;
	private String pwd;
	private String pseudonyme;
	private static int noUtilisateur = 1;
	private ArrayList<EmpruntEnCours> listEmprunt;
	private ArrayList<EmpruntArchive> listArchive;
		
	public Utilisateur(String nom, String prenom, String sexe, Date date) {
		super(nom, prenom, sexe, date);
		idUtilisateur = noUtilisateur++;
		pseudonyme = nom;
		listEmprunt = new ArrayList<EmpruntEnCours>();
		listArchive = new ArrayList<EmpruntArchive>();
	}
	

	public String toSring(){
		return "Utilisateur " 
				+"\n\t\t Nom " + getNom()
				+"\n\t\t Prenom " + getPrenom()
				+"\n\t\t Pseudonyme " + this.pseudonyme
				+"\n\t\t Sexe " + getSexe()
				+"\n\t\t Date de naissance " + getDateDeNaissance().toString()
				+"\n\t\t n°Utilisateur " +  this.idUtilisateur
				+"\n";
	}

	
	public void setPseudonyme(String pseudonyme){
		this.pseudonyme = pseudonyme;
	}
	
	public String getPseudonyme(){
		return this.pseudonyme;
	}
	
	public void setPwd(String pwd){
		this.pwd = pwd;
	}

	public String getPwd(){
		return this.pwd;
	}
	
	public ArrayList<EmpruntArchive> getArchive(){
		return this.listArchive;
	}
	
	public void addEmpruntArchive(EmpruntArchive archive){
		listArchive.add(archive);
	}
	
	public void addEmpruntEnCours(EmpruntEnCours ep){
		listEmprunt.add(ep);
	}
	
	public ArrayList<EmpruntEnCours> getEmpruntEnCours(){
		return this.listEmprunt;
	}
	
	public int getNbEmpruntEnCours(){
		return this.listEmprunt.size();
	}

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	
	public abstract boolean isConditionsPretAcceptees();
	
}
