package metier;

import java.util.Date;

public class EmpruntEnCours {
	private Date dateEmprunt;
	private Utilisateur utilisateur;
	private Exemplaire exemplaire;
	
	
	public EmpruntEnCours(Date dateEmprunt, Utilisateur u, Exemplaire ex) 
			throws BiblioException{
		this.dateEmprunt = dateEmprunt;
		setUtilisateur(u);
		setExemplaire(ex);
	}
	
	public Utilisateur getUtlisateur(){
		return utilisateur;
	}
	
	public Exemplaire getExmeplaire(){
		return exemplaire;
	}
	
	public void setDateEmprunt(Date dateEmprunt){
		this.dateEmprunt = dateEmprunt;
	}
	
	public Date getDateEmprunt(){
		return this.dateEmprunt;
	}
	
	public void setUtilisateur(Utilisateur u) throws BiblioException{
		
			if(u.isConditionsPretAcceptees()){
				this.utilisateur = u;
				this.utilisateur.addEmpruntEnCours(this);
			}else{
				throw new BiblioException("Adherent ne peut pas emprunter");
			}
		
	}
	
	public void setExemplaire(Exemplaire ex) throws BiblioException{
		if(ex.getEmprunt() == null 
				&& ex.getStatus() == EnumStatusExemplaire.DISPONIBLE){
			this.exemplaire = ex;
			this.exemplaire.setEmprunt(this);
		}else{
			throw new BiblioException("Ce Livre ne pas etre emprunter");
		}
	}
	
	public void endOfEmprunt(){
		EmpruntArchive archive = new EmpruntArchive(new Date(), dateEmprunt, utilisateur, exemplaire);
		this.utilisateur.addEmpruntArchive(archive);
		this.exemplaire.addEmpruntArchive(archive);
		this.utilisateur.getEmpruntEnCours().remove(this);
		this.exemplaire.setEmprunt(null);
	}
	@Override
	protected void finalize() throws Throwable {
		System.out.println("Je suis collecté!");
		super.finalize();
	}
	
	public String toString(){
		return "EMPRUNT EN COURS"
				+"\n\t\t DATE D'EMPRUNT " + this.dateEmprunt
				+"\n\t\t UTILISATEUR " + this.utilisateur
				+"\n\t\t EXEMPLAIRE " + this.exemplaire
				+"\n";
	}
}
