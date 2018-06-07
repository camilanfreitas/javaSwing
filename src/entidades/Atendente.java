package entidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Atendente {
	
	private String login;
	private String nome;
	private String senha;
	
	public String validaUsuario(String user, String senha) {
		
		return "";
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
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Scanner leitor = new Scanner(System.in);

		System.out.println("Digite o login: ");
		String l = leitor.next();
		System.out.println("Digite a senha: ");
		String u = leitor.next();
		
		boolean userValido = conectarBanco(l, u);
		
		if(userValido) {
			System.out.println("Conectou");
		}else {
			System.out.println("Falhou");
		}
		
	}
	
	public static boolean conectarBanco (String l, String u) throws ClassNotFoundException, SQLException {
		
		Class.forName("org.postgresql.Driver");
		Connection con = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/casabancaria", "postgres","root");
		
		String comando = "SELECT nome FROM atendente WHERE senha = (?) AND login = (?) ";
		PreparedStatement instrucao = con.prepareStatement(comando);
				
		instrucao.setString(1, l);
		instrucao.setString(2, u);			
		
		ResultSet rsMed = instrucao.executeQuery();
		
		System.out.println(rsMed);
		/*while( rsMed.next() ){
			System.out.print(rsMed.getString("login") );
			System.out.print("   " + rsMed.getString("nome") );
			System.out.println("  " + rsMed.getString("senha") );
		}*/
		
		con.close();		
		return true;
		
	}
	
}
