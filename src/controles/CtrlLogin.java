package controles;

import entidades.Atendente;
import telas.TelaCliente;

public class CtrlLogin {

	private static Atendente at = new Atendente();

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

	public static boolean  tentarLogin(Atendente a){
		
		at = a;
		
		//Chama o m�todo de Atendente que v�lida se o user existe
		String nome = Atendente.validaUsuario(a);

		//Se torna vazio = user n�o existe
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
