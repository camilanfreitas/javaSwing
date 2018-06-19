package br.go.senac.casabancaria.repositorios;

import br.go.senac.casabancaria.entidades.Atendente;
import br.go.senac.casabancaria.exception.InfraException;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Log4j
@Repository
public class AtendenteRepositorio {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	private StringBuilder sqlDefault = new StringBuilder().append("select id, login, nome, senha from atendente");
	
	public Optional<Atendente> buscarPorUsuarioESenha(String login, String senha) throws InfraException {
		StringBuilder sql = new StringBuilder(sqlDefault.toString()).append(" where login = :login and senha = :senha");
		Map<String,Object> param = new HashMap();
		param.put("login",login);
		param.put("senha",senha);
		try{
			return Optional.of(jdbc.queryForObject(sql.toString(),param, this::mapRow));
		}catch(EmptyResultDataAccessException e){
			return Optional.empty();
		}
	}
	
	private Atendente mapRow(ResultSet rs, int i) throws SQLException {
		log.trace("Build Row " + i );
		return Atendente
	       .builder()
		   .id(rs.getLong("id"))
	       .login(rs.getString("login"))
	       .nome(rs.getString("nome"))
		   .senha(rs.getString("senha"))
	       .build();
	}
	
	
}