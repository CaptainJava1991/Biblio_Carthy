package dao;

import java.util.Date;

import metier.Adherent;

public class AdherentDAO {
	
	private static Adherent[] adherentsDB = {
		new Adherent("Cornet","Stephen","M",new Date(),"0147841254"),
		new Adherent("Dornet","Stophen","M",new Date(),"0147841254"),
		new Adherent("Bornet","Stiphen","M",new Date(),"0147841254"),
		new Adherent("Tornet","Stuphen","M",new Date(),"0147841254"),
		new Adherent("Fornet","StAphen","M",new Date(),"0147841254")};
	
	public Adherent findById(int id){
		for (Adherent a : adherentsDB){
			if(a.getIdUtilisateur() == id)
				return a;
		}
		return null;
	}

	public static void main(String[] args) {
		
	}

}
