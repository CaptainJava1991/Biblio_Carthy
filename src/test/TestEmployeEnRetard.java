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
import dao.ExemplaireDAO;

public class TestEmployeEnRetard {
	public static void main(String[] args) throws BiblioException {
		ExemplaireDAO edao = new ExemplaireDAO();
		Exemplaire ex1 = edao.findById(1);
		Exemplaire ex2 = edao.findById(4);
		
		Employe a1 = new Employe("Roger", "Deri", "M", new Date());
		System.out.println(a1);
	
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			EmpruntEnCours emprunt = new EmpruntEnCours(df.parse("02/01/2016"),a1,ex1);
			EmpruntEnCours emprunt2 = new EmpruntEnCours(new Date(),a1,ex2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(a1.getEmpruntEnCours().get(0));



		

	}
}
