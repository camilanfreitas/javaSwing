package gov.goias.sfr.desktop.dao;

import gov.goias.excecao.InfraExcecao;
import gov.goias.sfr.desktop.entidade.Frequencia;
import java.util.Date;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class FrequenciaDAO {
	private final Logger log = Logger.getLogger(FrequenciaDAO.class);
	private static FrequenciaDAO instancia = null;

	public static FrequenciaDAO getInstancia(){
		if (instancia == null) {
			instancia = new FrequenciaDAO();
		}
		return instancia;
	}

	public void incluir(final Frequencia frequencia) throws InfraExcecao {
		final String sql = "INSERT INTO FREQUENCIA(FREQ_DATA, FREQ_CPF)VALUES(?,?)";
		try {
			final PreparedStatement c = Repositorio.getInstancia().obterConexaoFrequencia().prepareStatement(sql);
			c.setTimestamp(1, new Timestamp(frequencia.getData().getTime()));
			c.setString   (2, frequencia.getCpf());
			c.execute();
			c.close();
		} catch (Exception e) {
			log.debug(sql);
			log.error(e);
			throw new InfraExcecao(e);
		}
	}

	public void marcarEnvio(final Frequencia frequencia) throws InfraExcecao {
		final String sql = "UPDATE FREQUENCIA SET FREQ_ENVIO = ? WHERE FREQ_ID  = ?";
		try {
			final PreparedStatement c = Repositorio.getInstancia().obterConexao().prepareStatement(sql);
			c.setTimestamp(1, new Timestamp(frequencia.getEnvio().getTime()));
			c.setLong(2, frequencia.getId());
			c.execute();
			c.close();
		} catch (Exception e) {
			log.debug(sql);
			log.error(e);
			throw new InfraExcecao(e);
		}
	}

	public List<Frequencia> listarNaoEnviadas() throws InfraExcecao {
		final String sql = "SELECT FREQ_ID,FREQ_CPF,FREQ_DATA FROM FREQUENCIA WHERE FREQ_ENVIO IS NULL;";
		List<Frequencia> frequencias = new ArrayList();
		try {
			final PreparedStatement c = Repositorio.getInstancia().obterConexao().prepareStatement(sql);
			final ResultSet r = c.executeQuery();
			while (r.next()) {
				final Frequencia f = new Frequencia();
				f.setId(r.getLong       ("FREQ_ID"));
				f.setCpf(r.getString    ("FREQ_CPF"));
				f.setData(r.getTimestamp("FREQ_DATA"));
				frequencias.add(f);
			}
			r.close();
			c.close();
			return frequencias;
		} catch (Exception e) {
			log.debug(sql);
			log.error(e);
			throw new InfraExcecao(e);
		}
	}

	public void apagarEnviadas(final Date dataMinima) throws InfraExcecao {
		final String sql = "DELETE FROM FREQUENCIA WHERE FREQ_ENVIO IS NOT NULL AND FREQ_ENVIO <= ?";
		try {
			final PreparedStatement c = Repositorio.getInstancia().obterConexao().prepareStatement(sql);
			c.setTimestamp(1, new Timestamp(dataMinima.getTime()));
			c.execute();
			c.close();
		} catch (Exception e) {
			log.debug(sql);
			log.error(e);
			throw new InfraExcecao(e);
		}
	}

	public Date obterUltimaDataFrequenciaPorCPF(final String cpf) throws InfraExcecao{
		final String sql = "SELECT MAX(FREQ_DATA) MAIOR  FROM FREQUENCIA WHERE FREQ_CPF = ?";
		Date retorno = null;
		try {
			final PreparedStatement c = Repositorio.getInstancia().obterConexao().prepareStatement(sql);
			c.setString(1,cpf);
			final ResultSet r = c.executeQuery();
			if(r.next()) {
				retorno = r.getTimestamp("MAIOR");
			}
			r.close();
			c.close();
			return retorno;
		} catch (Exception e) {
			log.debug(sql);
			log.error(e);
			throw new InfraExcecao(e);
		}
	}

}