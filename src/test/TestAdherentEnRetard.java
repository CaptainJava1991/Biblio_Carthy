package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import metier.Adherent;
import metier.BiblioException;
import metier.Employe;
import metier.EmpruntEnCours;
import metier.Exemplaire;
import dao.AdherentDAO;
import dao.EmployeDAO;
import dao.ExemplaireDAO;

public class TestAdherentEnRetard {
	public static void main(String[] args) throws BiblioException {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		ExemplaireDAO edao = new ExemplaireDAO();
		Exemplaire ex1 = edao.findById(1);
		Exemplaire ex2 = edao.findById(2);
		
		AdherentDAO adao = new AdherentDAO();
		Adherent ad1 = adao.findById(2);
	
		
		
		try {
			EmpruntEnCours emprunt = new EmpruntEnCours(df.parse("02/01/2016"),ad1,ex1);
			EmpruntEnCours emprunt2 = new EmpruntEnCours(new Date(),ad1,ex2);
		} catch (ParseException e) {
			e.printStackTrace();
		}



		

	}
}
