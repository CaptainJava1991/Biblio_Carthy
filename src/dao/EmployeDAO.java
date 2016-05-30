package dao;

import java.util.Date;

import metier.Adherent;
import metier.Employe;
import metier.EnumCategorieEmploye;

public class EmployeDAO {
	
	private static Employe[] employesDB = {
		new Employe("Joel", "Damien", "M", new Date()),
		new Employe("Joel", "valentin", "M", new Date()),
		new Employe("Joel", "eveline", "M", new Date()),
		new Employe("Joel", "sophie", "M", new Date()),
		new Employe("Joel", "john", "M", new Date()),
	};
	
	public Employe findById(int id){
		for (Employe e : employesDB){
			if(e.getIdUtilisateur() == id)
				return e;
		}
		return null;
	}

	public static void main(String[] args) {
		for(Employe e : employesDB){
			System.out.println(e.getIdUtilisateur());
		}
	}

}
