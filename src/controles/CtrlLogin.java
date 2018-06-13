package controles;

import entidades.Atendente;
import telas.TelaCliente;

public class CtrlLogin {

	private static Atendente at = new Atendente();

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

	public static boolean  tentarLogin(Atendente a){
		
		at = a;
		
		//Chama o método de Atendente que válida se o user existe
		String nome = Atendente.validaUsuario(a);

		//Se torna vazio = user não existe
		if(nome == null) {
			return false;
		}

		at.setNome(nome);
		return true;

	}

	public static void abrirSistema() {

		TelaCliente.iniciaTela(at);

	}


}
