package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import dao.AdherentDAO;
import dao.ExemplaireDAO;
import metier.Adherent;
import metier.BiblioException;
import metier.EmpruntEnCours;
import metier.Exemplaire;

public class TestAdherentTroisEmprunt {
	public static void main(String[] args) throws BiblioException {		
		
		ExemplaireDAO edao = new ExemplaireDAO();
		Exemplaire ex1 = edao.findById(1);
		Exemplaire ex2 = edao.findById(2);
		Exemplaire ex3 = edao.findById(3);
		Exemplaire ex4 = edao.findById(4);
		
		AdherentDAO adao = new AdherentDAO();
		Adherent ad1 = adao.findById(2);
	
		EmpruntEnCours emprunt = new EmpruntEnCours(new Date(),ad1,ex1);
		EmpruntEnCours emprunt2 = new EmpruntEnCours(new Date(),ad1,ex2);
		EmpruntEnCours emprunt3 = new EmpruntEnCours(new Date(), ad1, ex3);
		EmpruntEnCours emprunt4 = new EmpruntEnCours(new Date(), ad1, ex4);
		System.out.println(emprunt);



		

	}
}
