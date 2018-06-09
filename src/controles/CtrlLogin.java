package controles;

import java.util.Scanner;

import entidades.Atendente;
import telas.TelaCliente;

public class CtrlLogin {

	private static Scanner leitor = new Scanner(System.in);
	private static String nomeDoUser = null;

	public static boolean cadastrarNovoLogin(Atendente a) {
		//Chama o método de Atendente que escreve no banco
		boolean salvo = Atendente.criarLogin(a);	

		return salvo;
	}

	public static boolean validaUser(String user) {
		
		boolean valido = Atendente.validaNomeUser(user);
		
		//Se true = o usuário existe / Se false = o usuário não existe
		return valido;
	}

	public static boolean  tentarLogin(String user, String senha){
		//Chama o método de Atendente que válida se o user existe
		String nome = Atendente.validaUsuario(user, senha);

		//Se torna vazio = user não existe
		if(nome == null) {
			return false;
		}

		setNomeDoUser(nome);
		return true;

	}

	public static void abrirSistema() {

		TelaCliente.opcoes();

	}

	public static String getNomeDoUser() {
		return nomeDoUser;
	}

	public static void setNomeDoUser(String nomeDoUser) {
		CtrlLogin.nomeDoUser = nomeDoUser;
	}



}
