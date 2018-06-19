package gov.goias.sfr.desktop.dao;

import gov.goias.excecao.InfraExcecao;
import gov.goias.sfr.desktop.entidade.Biometria;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Classe de persistência da entidade Biometria.
 *
 * @author  Marcos Fernando Costa.
 */
public class BiometriaDAO {
	private final Logger log = Logger.getLogger(BiometriaDAO.class);
	private static BiometriaDAO instancia = null;

	/**
	 * Metodo estático implementa o padrão singleton e retorna sempre a a mesma instancia da classe.
	 *
	 * @return BiometriaDAO Instancia atual.
	 *
	 * @throws InfraExcecao Possível exceção a ser lançada caso o mesmo não consiga obter uma conexão com o Repositório.
	 */
	public static BiometriaDAO getInstancia() throws InfraExcecao {
		if (instancia == null) {
			instancia = new BiometriaDAO();
		}
		return instancia;
	}

	/**
	 * Método que insere uma nova biometria no banco de dados.
	 *
	 * @param biometria Biometria de uma pessoa.
	 *
	 * @throws InfraExcecao Possível exceção a ser lançada caso ocorra algum problema.
	 */
	public void inserir(final Biometria biometria) throws InfraExcecao {
		final String sql = "INSERT INTO BIOMETRIA(PESS_CPF,PESS_NOME,PESS_DEDO,PESS_DIGITAL)VALUES(?,?,?,?)";
		try {
			final PreparedStatement c = Repositorio.getInstancia().obterConexao().prepareStatement(sql);
			c.setString(1, biometria.getCpf());
			c.setString(2, biometria.getNome());
			c.setInt   (3, biometria.getDedo() != null ? biometria.getDedo() : null );
			c.setBytes (4, biometria.getBiometria() != null ? biometria.getBiometria() : null);
			c.execute();
			c.close();
		} catch (Exception e) {
			log.debug(sql);
			log.error(e);
			throw new InfraExcecao(e);
		}
	}

	/**
	 * Método que limpa a tabela de biometrias.
	 *
	 * @throws InfraExcecao Possivel excecao a ser lançada caso ocorra algum problema ao executar o método.
	 */
	public void limpar() throws InfraExcecao {
		final String sql = "DELETE FROM BIOMETRIA";
		try {
			final PreparedStatement c = Repositorio.getInstancia().obterConexao().prepareStatement(sql);
			c.execute();
			c.close();
		} catch (Exception e) {
			log.debug(sql);
			log.error(e);
			throw new InfraExcecao(e);
		}
	}

	/**
	 * Método que retorna uma biometria pelo CPF.
	 *
	 * @param cpf Numero do cpf desejado.
	 *
	 * @return Biometria Biometria existente.
	 *
	 * @throws InfraExcecao Possivel excecao a ser lançada caso ocorra algum problema ao executar o método.
	 */
	public Biometria obterPorCpf(final String cpf) throws InfraExcecao{
		Biometria retorno = null;
		final String sql = "SELECT PESS_NOME FROM BIOMETRIA WHERE PESS_CPF = ?";
		try {
			final PreparedStatement c = Repositorio.getInstancia().obterConexao().prepareStatement(sql);
			c.setString(1,cpf);
			final ResultSet r = c.executeQuery();
			if(r.next()){
				retorno = new Biometria();
				retorno.setCpf(cpf);
				retorno.setNome(r.getString("PESS_NOME"));
			}
			r.close();
			c.close();
		} catch (Exception e) {
			log.debug(sql);
			log.error(e);
			throw new InfraExcecao(e);
		}
		return retorno;
	}

	/**
	 * Método que lista todas as digitais cadastradas.
	 *
	 * @return List<Biometria> lista de biometrias existentes.
	 *
	 * @throws InfraExcecao Possivel excecao a ser lançada caso ocorra algum problema ao executar o método.
	 */
	public List<Biometria> listarTodos() throws InfraExcecao {
		final String sql = "SELECT PESS_CPF,PESS_DEDO,PESS_DIGITAL FROM BIOMETRIA";
		List<Biometria> digitais;
		try {
			final PreparedStatement c = Repositorio.getInstancia().obterConexao().prepareStatement(sql);
			final ResultSet r = c.executeQuery();
			digitais = new ArrayList();
			while (r.next()) {
				final Biometria d = new Biometria();
				d.setCpf(r.getString("PESS_CPF"));
				d.setDedo(r.getInt("PESS_DEDO"));
				d.setBiometria(r.getBytes("PESS_DIGITAL"));
				digitais.add(d);
			}
			r.close();
			c.close();
			return digitais;
		} catch (Exception e) {
			log.debug(sql);
			log.error(e);
			throw new InfraExcecao(e);
		}
	}

}