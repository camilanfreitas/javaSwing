package br.go.senac.casabancaria.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Atendente {
	private Long   id;
	private String nome;
	private String login;
	private String senha;	

//	public static boolean validaNomeUser(String user) {
//
//		try {
//			Connection con = ConectaBD.conectaAoBanco();
//			String comando = "SELECT * FROM atendente WHERE  login = (?)";
//			PreparedStatement instrucao = con.prepareStatement(comando);
//
//			instrucao.setString(1, user);
//
//			ResultSet rs = instrucao.executeQuery();
//
//			if (rs.isBeforeFirst()) {
//					return true;
//			}
//
//			con.close();
//		} catch (SQLException | ClassNotFoundException e) {
//			System.out.println(e.getMessage());
//		}
//
//		return false;
//
//	}

	//Cria o novo Login
//	public static boolean criarLogin(Atendente a) {

//		try {
//			//Pega a Conex�o
//			Connection con = ConectaBD.conectaAoBanco();
//
//			//Prepara comando
//			String comando = "INSERT INTO atendente VALUES (?, ?, ?)";
//			PreparedStatement instrucao = con.prepareStatement(comando);
//
//			//Insere os atributos
//			instrucao.setString(1, a.getLogin());
//			instrucao.setString(2, a.getNome());
//			instrucao.setString(3, a.getSenha());
//
//			//Executa o comando e captura a quantidade de linhas codificadas/criadas
//			int affectedRows = instrucao.executeUpdate();
//
//			//Se alguma linha foi atualizada, salva o nome do usu�rio para mensagem
//			if (affectedRows > 0) {
//				return true;
//			}
//
//		} catch (SQLException | ClassNotFoundException e) {
//			System.out.println(e.getMessage()+ " | SQLException");
//		}
//
//		return false;
//	}
//
//	public static String  validaUsuario (Atendente a){
//
//return null;
//	}

//
//	//GETTERS E SETTERS
//	public String getNome() {
//		return nome;
//	}
//
//	public void setNome(String nome) {
//		this.nome = nome;
//	}
//
//	public String getLogin() {
//		return login;
//	}
//
//	public void setLogin(String login) {
//		this.login = login;
//	}
//
//	public String getSenha() {
//		return senha;
//	}
//
//	public void setSenha(String senha) {
//		this.senha = senha;
//	}
//


}
