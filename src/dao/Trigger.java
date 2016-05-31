package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import metier.Exemplaire;
import ping.PingJdbc;

public class Trigger {
	public int delete(Exemplaire exemplaire) throws ClassNotFoundException, SQLException, IOException{
		//Ouvrir la connection
		Connection cnx = PingJdbc.getIntance();
		
		//A la cnx, on demande un statement
		Statement stmt = cnx.createStatement();
		
		
		PreparedStatement prst = cnx.prepareCall(
				"delete from empruntencours where idexemeplaire = ?"  
				);
				
		prst.setInt(1, exemplaire.getIdExemplaire());
		
		int rs = prst.executeUpdate();
	
		cnx.commit();
		
		prst.close();
		stmt.close();
		cnx.close();
		
		return rs;
	}
}
