package entidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Atendente {

	public static String  validaUsuario (String user, String senha) throws ClassNotFoundException, SQLException 	{

		String comando = "SELECT nome FROM atendente WHERE  login = (?) AND senha = (?)";
		
		Connection con = ConectaBD.conectaAoBanco();
		
		PreparedStatement instrucao = con.prepareStatement(comando);

		instrucao.setString(1, user);
		instrucao.setString(2, senha);

		ResultSet rs = instrucao.executeQuery();
		String nome = null;
				
		if (rs.isBeforeFirst()) { 
			while(rs.next()){
				nome =rs.getString("nome") ;
			}			
		} 

		con.close();	
		return nome;
	}

}
