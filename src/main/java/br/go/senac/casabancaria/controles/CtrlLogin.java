package br.go.senac.casabancaria.controles;

import br.go.senac.casabancaria.entidades.Atendente;
import br.go.senac.casabancaria.exception.NegocioException;
import br.go.senac.casabancaria.repositorios.AtendenteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class CtrlLogin {
	
	@Autowired
	private AtendenteRepositorio repositorio;
	
	
	public boolean cadastrarNovoLogin(Atendente a) {
		//Chama o m�todo de Atendente que escreve no banco
		//boolean salvo = Atendente.criarLogin(a);

	//	return salvo;
		return true;
	}

	public boolean validaUser(String user) {
		
//		boolean valido = Atendente.validaNomeUser(user);
//		//Se true = o usu�rio existe / Se false = o usu�rio n�o existe
//		return valido;
		return true;
	}

	public Atendente authenticar(String usuario, String senha){
		return repositorio.buscarPorUsuarioESenha(usuario,senha).orElseThrow(() -> new NegocioException("Usuário e ou senha inváidos!"));
	}


}
