package dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import metier.Exemplaire;
import ping.PingJdbc;

public class CompteExemplaireByLivre {
	
	public int retrieve(Exemplaire exemplaire) throws ClassNotFoundException, SQLException, IOException{
		//Ouvrir la connection
		Connection cnx = PingJdbc.getIntance();
		
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
