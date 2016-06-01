package metier;

import java.util.ArrayList;
import java.util.Date;

public class Exemplaire {
	private int idExemplaire;
	private Date dateAchat;
	private EnumStatusExemplaire status;
	private String isbn;
	private static int noExemplaire = 1;
	private EmpruntEnCours emprunt;
	private ArrayList<EmpruntArchive> listArchive;
	
	public Exemplaire(Date dateAchat, String isbn){
		this.dateAchat = dateAchat;
		this.isbn = isbn;
		this.idExemplaire = noExemplaire++;
		status = EnumStatusExemplaire.DISPONIBLE;
		this.listArchive = new ArrayList<EmpruntArchive>();
	}
	
	public String toString(){
		return "Exemplaire" 
				+"\n\t\t ISBN " + this.isbn
				+"\n\t\t n°Exemplaire " + this.idExemplaire
				+"\n\t\t Date d'achat " + this.dateAchat
				+"\n\t\t status " + this.status
				+"\n";
	}
		
	public void setEmprunt(EmpruntEnCours emprunt){
		if(emprunt == null){
			this.emprunt = null;
			status = EnumStatusExemplaire.DISPONIBLE;
		}else {
			this.emprunt = emprunt;
			status = EnumStatusExemplaire.PRETE;
		}
	}
	
	public void setStatus(EnumStatusExemplaire status){
		this.status = status;
	}
	
	public EnumStatusExemplaire getStatus(){
		return this.status;
	}
	
	public void addEmpruntArchive(EmpruntArchive archive){
		this.listArchive.add(archive);
	}
	
	public EmpruntEnCours getEmprunt(){
		return this.emprunt;
	}
	
	public int getIdExemplaire(){
		return this.idExemplaire;
	}
	
	public void setIdExemplaire(int idExemplaire){
		this.idExemplaire = idExemplaire;
	}
	
	public Date getDateAchat(){
		return dateAchat;
	}
	
	public String getISBN(){
		return isbn;
	}
	
	public void endOfEmprunt(){
		if(emprunt != null){
			emprunt.endOfEmprunt();
		}
	}

}
