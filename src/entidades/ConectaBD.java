package entidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaBD {

	public static Connection conectaAoBanco() throws ClassNotFoundException, SQLException {

		Class.forName("org.postgresql.Driver");
		Connection con = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/casabancaria", "postgres","root");
		
		return con;
		
	}

	

}
