package controles;

import java.util.Scanner;

import entidades.Atendente;
import telas.TelaCliente;

public class CtrlLogin {

	private static Scanner leitor = new Scanner(System.in);
	private static String nomeDoUser = null;

	public static boolean cadastrarNovoLogin(Atendente a) {
		//Chama o m�todo de Atendente que escreve no banco
		boolean salvo = Atendente.criarLogin(a);	

		return salvo;
	}

	public static boolean validaUser(String user) {
		
		boolean valido = Atendente.validaNomeUser(user);
		
		//Se true = o usu�rio existe / Se false = o usu�rio n�o existe
		return valido;
	}

	public static boolean  tentarLogin(String user, String senha){
		//Chama o m�todo de Atendente que v�lida se o user existe
		String nome = Atendente.validaUsuario(user, senha);

		//Se torna vazio = user n�o existe
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
