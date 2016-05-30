package test;

import java.util.Collections;
import java.util.Date;

import metier.BiblioException;
import metier.Employe;
import metier.EmpruntEnCours;
import metier.Exemplaire;
import dao.ExemplaireDAO;

public class TestRetour {

	public static void main(String[] args) throws BiblioException {
		ExemplaireDAO edao = new ExemplaireDAO();
		Exemplaire ex1 = edao.findById(1);
		Exemplaire ex2 = edao.findById(2);
		Exemplaire ex3 = edao.findById(3);
		Exemplaire ex4 = edao.findById(4);
		
		Employe a1 = new Employe("Roger", "Deri", "M", new Date());
		
		EmpruntEnCours emprunt = new EmpruntEnCours(new Date(),a1,ex1);
		System.out.println(ex1.getStatus());
		EmpruntEnCours emprunt2 = new EmpruntEnCours(new Date(),a1,ex2);
		System.out.println(ex1.getStatus());
		EmpruntEnCours emprunt3 = new EmpruntEnCours(new Date(),a1,ex3);
		EmpruntEnCours emprunt4 = new EmpruntEnCours(new Date(),a1,ex4);
		System.out.println("NbEmprunts: " + a1.getNbEmpruntEnCours());
		emprunt.endOfEmprunt();
		System.out.println("NbEmprunts: " + a1.getNbEmpruntEnCours());
		System.out.println(ex1.getStatus());
		System.out.println(a1.getArchive());
		
		for(int i = 0; i< 100000;i++){
			Employe e = new Employe("Roger", "Deri", "M", new Date());
		}

	}

}
