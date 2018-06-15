package controles;

import java.util.ArrayList;

import entidades.Cliente;

public class CtrlCliente {

	private static String user = "admin";
	
	public static String getUser() {
		return user;
	}

	public static void setUser(String user) {
		CtrlCliente.user = user;
	}

	public static boolean preparaAlteracao(Cliente cl, String cpfAnterior){

		cl = insereUser(cl);

		boolean b = Cliente.atualizaDados(cl, cpfAnterior);

		return b;
	}

	public static boolean preparaCadastro(Cliente cl){

		cl = insereUser(cl);

		boolean b = Cliente.guardarDados(cl);

		return b;
	}

	public static Cliente preparaPesquisa(String documento){

		Cliente c  = Cliente.pesquisaCliente(documento);

		return c;

	}
	
	public static boolean preparaDelete(String documento) {		
		
		boolean b = Cliente.deletaCliente(documento);
		
		return b;
		
	}

	private static Cliente insereUser(Cliente c) {

		c.setAtendente(user);

		return c;
	}



}
