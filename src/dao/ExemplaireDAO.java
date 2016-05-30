package dao;

import java.util.Date;

import metier.Exemplaire;

public class ExemplaireDAO {
	
	private static Exemplaire[] exemplairesDB = {
		new Exemplaire(new Date(),"2811205063"),
		new Exemplaire(new Date(),"2811205063"),
		new Exemplaire(new Date(),"2811205063"),
		new Exemplaire(new Date(),"2811205063"),
		new Exemplaire(new Date(),"2811205063"),
		new Exemplaire(new Date(),"2811205063")};
	
	public Exemplaire findById(int id){
		for(Exemplaire e : exemplairesDB){
			if(e.getIdExemplaire() == id)
				return e;
		}
		 
		return null;
	}
	public static void main(String[] args){
		System.out.println("TEST");
	}
}
