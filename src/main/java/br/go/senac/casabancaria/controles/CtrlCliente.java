package br.go.senac.casabancaria.controles;

import br.go.senac.casabancaria.entidades.Atendente;
import br.go.senac.casabancaria.entidades.Cliente;
import br.go.senac.casabancaria.repositorios.ClienteRepositorio;
import br.go.senac.casabancaria.telas.TelaSimulacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CtrlCliente {
	
	@Autowired
	private ClienteRepositorio repositorio;

//	private static String user = "admin";
//	public static String getUser() {
//		return user;
//	}
//
//	public static void setUser(String user) {
//		CtrlCliente.user = user;
//	}
//
	public static void criarSimulacao(Cliente c, Atendente a) {
		TelaSimulacao.iniciaTela(c, a);
	}
	
	public List<String> listarTipos(){
		List<String> tipos = new ArrayList<>();
		tipos.add("1 - Funcionário Público");
		tipos.add("2 - Aposentado");
		tipos.add("3 - Pensionista");
		return tipos;
	}
	
	public List<String> listarUfs(){
		List<String> ufs = new ArrayList<>();
		ufs.add("AC");
		ufs.add("AL");
		ufs.add("AP");
		ufs.add("AM");
		ufs.add("BA");
		ufs.add("CE");
		ufs.add("DF");
		ufs.add("ES");
		ufs.add("GO");
		ufs.add("MA");
		ufs.add("MT");
		ufs.add("MS");
		ufs.add("MG");
		ufs.add("PA");
		ufs.add("PB");
		ufs.add("PR");
		ufs.add("PE");
		ufs.add("PI");
		ufs.add("RJ");
		ufs.add("RN");
		ufs.add("RS");
		ufs.add("RO");
		ufs.add("RR");
		ufs.add("SC");
		ufs.add("SP");
		ufs.add("SE");
		ufs.add("TO");
		return ufs;
	}
	
	public Cliente salvar(Cliente cliente){
		//repositorio
	}
	
	
//	public static boolean preparaAlteracao(Cliente cl, String cpfAnterior){

//		cl = insereUser(cl);
//
//		boolean b = Cliente.atualizaDados(cl, cpfAnterior);
//
//		return b;\
//	}

//	public static boolean preparaCadastro(Cliente cl){
//
//		cl = insereUser(cl);
//
//		boolean b = Cliente.guardarDados(cl);
//
//		return b;
//	}
//
//	public static Cliente preparaPesquisa(String documento){
//
//		Cliente c  = Cliente.pesquisaCliente(documento);
//
//		return c;
//
//	}
//
//	public static boolean preparaDelete(String documento) {
//
//		boolean b = Cliente.deletaCliente(documento);
//
//		return b;
//
//	}
//
//	private static Cliente insereUser(Cliente c) {
//
//		c.setAtendente(user);
//
//		return c;
//	}



}
