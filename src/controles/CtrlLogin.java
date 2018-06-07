package controles;

import java.sql.SQLException;

import entidades.Atendente;

public class CtrlLogin {

	public static boolean  tentarLogin(String user, String senha) throws ClassNotFoundException, SQLException {
		
		String nome = Atendente.validaUsuario(user, senha);
		
		if(nome == null) {
			return false;
		}

		return true;
		
	}

}
