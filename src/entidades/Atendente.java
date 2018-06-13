package entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Atendente {

	private String nome;
	private String login;
	private String senha;	

	public static boolean validaNomeUser(String user) {

		try {
			Connection con = ConectaBD.conectaAoBanco();
			String comando = "SELECT * FROM atendente WHERE  login = (?)";
			PreparedStatement instrucao = con.prepareStatement(comando);

			instrucao.setString(1, user);

			ResultSet rs = instrucao.executeQuery();

			if (rs.isBeforeFirst()) { 
					return true;	
			} 

			con.close();	
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
				
		return false;

	}

	//Cria o novo Login
	public static boolean criarLogin(Atendente a) {

		try {
			//Pega a Conexão
			Connection con = ConectaBD.conectaAoBanco();

			//Prepara comando
			String comando = "INSERT INTO atendente VALUES (?, ?, ?)";
			PreparedStatement instrucao = con.prepareStatement(comando);

			//Insere os atributos
			instrucao.setString(1, a.getLogin());
			instrucao.setString(2, a.getNome());
			instrucao.setString(3, a.getSenha());

			//Executa o comando e captura a quantidade de linhas codificadas/criadas
			int affectedRows = instrucao.executeUpdate();

			//Se alguma linha foi atualizada, salva o nome do usuário para mensagem 
			if (affectedRows > 0) {    
				return true;
			}

		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage()+ " | SQLException");
		}

		return false;
	}

	public static String  validaUsuario (Atendente a){


		String comando = "SELECT nome FROM atendente WHERE  login = (?) AND senha = (?)";
		String nome = null;

		try {
			Connection con = ConectaBD.conectaAoBanco();

			PreparedStatement instrucao = con.prepareStatement(comando);

			instrucao.setString(1, a.getLogin());
			instrucao.setString(2, a.getSenha());

			ResultSet rs = instrucao.executeQuery();

			if (rs.isBeforeFirst()) { 
				while(rs.next()){
					nome =rs.getString("nome") ;
				}			
			} 

			con.close();	
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

		return nome;
	}


	//MÉTODOS CONTRUTORES
	public Atendente() {

	}

	public Atendente(String nome, String login, String senha) {
		this.nome = nome;
		this.login = login;
		this.senha = senha;
	}

	public Atendente (String nome, String login) {
		this.nome = nome;
		this.login = login;
	}


	//GETTERS E SETTERS
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}



}
