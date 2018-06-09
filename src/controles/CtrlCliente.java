package controles;

import java.sql.ResultSet;
import java.sql.SQLException;
import entidades.Cliente;

public class CtrlCliente {

	private static String user = "admin";
	private static Cliente c;

	public static boolean preparaAlteracao(Cliente cl, String cpfAnterior) throws ClassNotFoundException, SQLException{

		cl = insereUser(cl);

		boolean b = Cliente.atualizaDados(cl, cpfAnterior);

		return b;
	}

	public static boolean preparaCadastro(Cliente cl) throws ClassNotFoundException, SQLException{

		cl = insereUser(cl);

		boolean b = Cliente.guardarDados(cl);

		return b;
	}

	public static Cliente preparaPesquisa(String documento){

		Cliente c  = Cliente.pesquisaCliente(documento);

		return c;

	}

	private static Cliente insereUser(Cliente c) {

		c.setAtendente(user);

		return c;
	}



}
