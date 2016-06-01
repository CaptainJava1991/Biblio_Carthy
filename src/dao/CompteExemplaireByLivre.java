package dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import metier.Exemplaire;

public class CompteExemplaireByLivre {
	private Connection cnx;
	
	public CompteExemplaireByLivre(Connection cnx){
		this.cnx = cnx;
	}
	
	
	public int retrieve(Exemplaire exemplaire) throws ClassNotFoundException, SQLException, IOException{		
		//A la cnx, on demande un statement
		Statement stmt = cnx.createStatement();
		
		
		CallableStatement clbs = cnx.prepareCall(
				"{Call f_compte_exemplaire(?)}"  
				);
				
		clbs.setString(1, exemplaire.getISBN());
		
		ResultSet rs = clbs.executeQuery();
	
		cnx.commit();
		
		clbs.close();
		stmt.close();
		cnx.close();
		
		return rs.getInt(1);
	}
}
