package test;

import java.util.Date;

import metier.Adherent;
import metier.BiblioException;
import metier.Employe;
import metier.EmpruntEnCours;
import metier.Exemplaire;
import dao.AdherentDAO;
import dao.EmployeDAO;
import dao.ExemplaireDAO;

public class TestDeBase {

	public static void main(String[] args) throws BiblioException {
		ExemplaireDAO edao = new ExemplaireDAO();
		Exemplaire ex1 = edao.findById(1);
		Exemplaire ex2 = edao.findById(4);
		System.out.println(ex1);
		System.out.println(ex2);
		
		AdherentDAO adao = new AdherentDAO();
		Adherent ad1 = adao.findById(2);
		System.out.println(ad1);
		
		EmployeDAO emdao = new EmployeDAO();
		Employe em2 = emdao.findById(8);
		System.out.println(em2);
		
		
		EmpruntEnCours emprunt = new EmpruntEnCours(new Date(),ad1,ex1);
		System.out.println(ad1.getEmpruntEnCours().get(0));
		
		EmpruntEnCours emprunt2 = new EmpruntEnCours(new Date(),em2,ex2);
		System.out.println(em2.getEmpruntEnCours().get(0));


		

	}

}
