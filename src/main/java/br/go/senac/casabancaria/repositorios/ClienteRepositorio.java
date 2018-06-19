package br.go.senac.casabancaria.repositorios;

import br.go.senac.casabancaria.entidades.Cliente;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Log4j
@Repository
public class ClienteRepositorio {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	private SimpleJdbcInsert jdbcInsert;
	
	@Autowired
	public ClienteRepositorio(JdbcTemplate template) {
		jdbcInsert = new SimpleJdbcInsert(template)
				             .withTableName("cliente")
				             .usingColumns ("cpf","nome","prestacao", "renda","email", "cep","logradouro","numero","complemento","bairro","cidade","uf","atendente","tipo","telefone")
				             .usingGeneratedKeyColumns("id");
	}
	
	public void incluir(Cliente cliente){
		Map params = new HashMap();
		params.put("cpf"       , cliente.getCpf());
		params.put("nome"      , cliente.getNome());
		params.put("prestacao" , cliente.getPrestacaoTerceiro());
		params.put("renda"     , cliente.getRenda());
		params.put("email"     , cliente.getEmail());
		params.put("cep"       , cliente.getCep());
		params.put("logradouro", cliente.getLogradouro());
		params.put("numero"    , cliente.getNumero());
		params.put("complemento", cliente.getComplemento());
		params.put("bairro"     , cliente.getBairro());
		params.put("cidade"     , cliente.getCidade());
		params.put("uf"         , cliente.getEstado());
		
		params.put("atendente"  , cliente.getAtendente());
		params.put("uf"         , cliente.getEstado());
		params.put("uf"         , cliente.getEstado());
		
		
		log.debug ("Params " + params.toString());
		person.setId(jdbcInsert.executeAndReturnKey(params).longValue());
		return person;
	}
	
	public void alterar(Cliente cliente){
	
	}
	
	public void excluir(Long id){
	
	}
	
	public Optional<Cliente> buscarPorCpf(String cpf){
		return Optional.empty();
	}
	
	
	
}
