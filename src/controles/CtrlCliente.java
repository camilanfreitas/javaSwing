package controles;

import java.sql.ResultSet;
import java.sql.SQLException;
import entidades.Cliente;

public class CtrlCliente {

	private static String user;
	private static Cliente c;

	public static boolean preparaCadastro(Cliente cl) throws ClassNotFoundException, SQLException{

		user = "admin";
		
		cl.setAtendente(user);
		
		boolean b = Cliente.guardarDados(cl);

		return b;
	}

	public static Cliente preparaPesquisa(String documento) throws ClassNotFoundException, SQLException {

		ResultSet rs = Cliente.pesquisaCliente(documento);

		c = new Cliente();
		c = null;

		if (rs.isBeforeFirst()) { 
			rs.next();

			c.setCpf(rs.getString("cpf"));
			c.setNome(rs.getString("nome") );
			c.setTipo(rs.getInt("tipo"));
			c.setPrestacaoTerceiro(rs.getDouble("prestacaoTerceiro"));
			c.setRenda(rs.getDouble("renda"));
			c.setEmail(rs.getString("email"));
			c.setCep(rs.getInt("cep"));
			c.setLogradouro(rs.getString("logradouro"));
			c.setNumero(rs.getInt("numero"));
			c.setComplemento(rs.getString("complemento"));
			c.setBairro(rs.getString("bairro"));
			c.setCidade(rs.getString("cidade"));
			c.setEstado(rs.getString("estado"));
			c.setAtendente(rs.getString("atendente"));

		} 

		return c;

	}

	public static String getUser() {
		return user;
	}

	public static void setUser(String user) {
		CtrlCliente.user = user;
	}

	public static Cliente getC() {
		return c;
	}

	public static void setC(Cliente c) {
		CtrlCliente.c = c;
	}

	
	
}
