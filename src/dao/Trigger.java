package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import metier.Exemplaire;

public class Trigger {
	private Connection cnx;
	
	public Trigger(Connection cnx){
		this.cnx = cnx;
	}
	
	public int delete(Exemplaire exemplaire) throws ClassNotFoundException, SQLException, IOException{
		
		//A la cnx, on demande un statement
		
		PreparedStatement prst = cnx.prepareCall(
				"delete from EmpruntEncours where idExemplaire = ?"  
				);
				
		prst.setInt(1, exemplaire.getIdExemplaire());
		
		int rs = prst.executeUpdate();
		
		prst.close();
		
		return rs;
	}
}
