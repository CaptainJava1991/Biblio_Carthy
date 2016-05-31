package ping;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.imageio.stream.FileImageInputStream;


public class PingJdbc {
	private static Connection cnx = null;
	private Statement st = null;
	private ResultSet rs = null;
	private String driver;
	private String url;
	private String user;
	private String pwd;
	
	private PingJdbc() throws ClassNotFoundException, SQLException, IOException{
		
		Properties properties = new Properties();
		FileInputStream file = new FileInputStream("jdbc.properties");
		properties.load(file);
		driver = properties.getProperty("driver", "vide");
		url = properties.getProperty("url", "vide");
		user = properties.getProperty("user", "vide");
		pwd = properties.getProperty("pwd", "vide");
		
		// Charger la première classe du driver
		Class.forName(driver);
		
		//Ouvrir la connection
		cnx = DriverManager.getConnection(url, user, pwd);
		
		cnx.setAutoCommit(false);
	}
	
	public static Connection getIntance() throws ClassNotFoundException, SQLException, IOException{
		if(cnx == null){
			new PingJdbc();
		}
		
		return cnx;
	}

	
	public String getDriver(){
		return driver;
	}
	
	public String getURL(){
		return url;
	}
	
	public String getUser(){
		return user;
	}
	
	public String getPwd(){
		return pwd;
	}
}
