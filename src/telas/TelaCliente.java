package telas;

import java.sql.SQLException;

import controles.CtrlCliente;
import entidades.Cliente;

public class TelaCliente {
	

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Cliente cl = new Cliente();
		
		cl.setCpf("123132");
		cl.setNome("Camila");
		cl.setBairro("Jd Vitoria");
		cl.setCep(74748456);
		cl.setCidade("Goiania");
		cl.setComplemento(null);
		cl.setEmail(null);
		cl.setEstado("GO");
		cl.setLogradouro("Rua A");
		cl.setNumero(2);
		cl.setPrestacaoTerceiro(0);
		cl.setRenda(3000.50);
		cl.setTipo(2);

		boolean b = CtrlCliente.preparaCadastro(cl);
		
		if(b) {
			System.out.println("Gravou");
		}else {
			System.out.println("Não gravou");
		}
		
		
		
		/*String cpf = "10010051";
		pesquisaCliente(cpf);*/

	}

	public static void pesquisaCliente (String cpf) throws ClassNotFoundException, SQLException {

		Cliente c = CtrlCliente.preparaPesquisa(cpf);

		if(c!=null) {
			System.out.println(c.getNome());
		}else {
			System.out.println("Cliente nao encontrado");
		}

	}

}
