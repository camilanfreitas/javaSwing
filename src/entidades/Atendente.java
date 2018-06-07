package entidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Atendente {

	private String login;
	private String nome;
	private String senha;


	public static String  validaUsuario (String user, String senha) throws ClassNotFoundException, SQLException 	{

		Class.forName("org.postgresql.Driver");
		Connection con = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/casabancaria", "postgres","root");

		String comando = "SELECT nome FROM atendente WHERE  login = (?) AND senha = (?)";
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
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}


}
