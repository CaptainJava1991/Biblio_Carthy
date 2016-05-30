package metier;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Adherent extends Utilisateur {
	private String telephone;
	private static int nbMaxPrets = 3;
	private static int dureeMaxPrets = 15;

	public Adherent(String nom, String prenom, String sexe, Date date, String telephone){
		super(nom, prenom, sexe, date);
		setTelephone(telephone);
	}
	
	public String toString(){
		return "Utilisateur " 
				+"\n\t\t Nom " + getNom()
				+"\n\t\t Prenom " + getPrenom()
				+"\n\t\t Pseudonyme " + getPseudonyme()
				+"\n\t\t Sexe " + getSexe()
				+"\n\t\t Telephone " + this.telephone
				+"\n\t\t Date de naissance " + getDateDeNaissance().toString()
				+"\n\t\t n°Utilisateur " +  getIdUtilisateur()
				+"\n";
	}

	public void setTelephone(String telephone) {
		for(int i = 0; i < telephone.length(); i++){
			if(!Character.isDigit(telephone.charAt(i))){
				try {
					throw new BiblioException("Le nunmero de telephone erroné");
				} catch (BiblioException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private boolean isPretEnRetard(EmpruntEnCours ep){
		boolean limite;
		SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
		
			form.format(ep.getDateEmprunt());
	
			Calendar calendar = form.getCalendar();
	
			
			calendar.add(Calendar.DAY_OF_YEAR, dureeMaxPrets);
			Date ajd = new Date();
	
			
			if(ajd.compareTo(calendar.getTime()) >= 0){
				limite = true;
			}else{
				limite = false;
			}		
			return limite;
	}
	
	public int getNbRetards(){
		int nbRetard = 0;
		
		for(int i = 0; i < getEmpruntEnCours().size(); i++){
			if(isPretEnRetard(getEmpruntEnCours().get(i))){
				nbRetard++;
			}
		}
		
		return nbRetard;
	}
	
	@Override
	public boolean isConditionsPretAcceptees(){
		return ((getNbRetards() == 0 && getEmpruntEnCours().size() < nbMaxPrets)? 
				true : false);
	}
	
	
}
