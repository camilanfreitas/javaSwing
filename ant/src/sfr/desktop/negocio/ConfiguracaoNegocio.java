package gov.goias.sfr.desktop.negocio;

import gov.goias.excecao.InfraExcecao;
import gov.goias.sfr.desktop.dao.Repositorio;
import gov.goias.sfr.desktop.dao.ConfiguracaoDAO;
import gov.goias.sfr.desktop.excecao.InconsistenciaExcecao;
import gov.goias.sfr.desktop.util.DataUtil;
import gov.goias.sfr.desktop.util.RedeUtil;
import gov.goias.sfr.excecao.NaoEncontradoExcecao;
import gov.goias.sfr.service.ServicoProxy;
import gov.goias.util.CircuitBreakerOpenException;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 * <bold>ConfiguracaoNegocio</bold> Classe que contem o negcio de manipulaes de configuraes
 * que o sistema necessita para seu funcionamento.
 *
 * @author Marcos Fernando.
 */
public class ConfiguracaoNegocio{
	private static ConfiguracaoNegocio instancia = null;
	private final Logger log = Logger.getLogger(ConfiguracaoNegocio.class);
	public static final String MODO_OPERACAO      = "MODO_OPERACAO";
	public static final String MODO_TECLADO       = "S";
	public static final String MODO_BIOMETRICO    = "B";
	public static final String MAC_LOCAL          = "MAC_LOCAL";
	public static final String ULTIMA_DATA        = "ULTIMA_DATA";

	/**
	 * Mtodo que valida o ambiente para autorizar o funcionamento do sistema.
	 * Verificaes:<br/>
	 *  Banco de dados, configuraes de Rede , Modo de Operao.
	 *
	 * @throws InfraExcecao Possvel exceo a ser lanada caso ocorra um erro no previsto.
	 *
	 * @throws NaoEncontradoExcecao, InconsistenciaExcecao,InfraExcecao
	 */
	public void validarAmbiente() throws NaoEncontradoExcecao, InconsistenciaExcecao,InfraExcecao {
		Repositorio.getInstancia().obterConexao();
		//validarData(new Date());
		final String macLocal = RedeUtil.obterEnderecoMac();
		if (macLocal == null || macLocal.isEmpty()) {
			throw new NaoEncontradoExcecao("Não econtrou interface de rede");
		}
		final String macExistente = obterConfiguracao(MAC_LOCAL);
		if(macExistente != null){
			if(!macLocal.equals(macExistente)){
				throw new InconsistenciaExcecao("endereço mac usado anteriormente diferente do atual"); //TODO Verificar
			}
		}else{
			salvarConfiguracaoTransacional(MAC_LOCAL, macLocal);
		}
		System.setProperty(MAC_LOCAL,macLocal);
		System.setProperty(MODO_OPERACAO, obterModoOperacao());
	}

	private void validarData(final Date data) throws InconsistenciaExcecao, InfraExcecao{
		final Date dataExistente = DataUtil.obterData(obterConfiguracao(ULTIMA_DATA));
		if (dataExistente != null) {
			if (data.before(dataExistente)) {
				throw new InconsistenciaExcecao("Data atual menor que última data do servidor");
			}
		}
	}

	/**
	 * Mtodo esttico implementa o padro singleton e retorna sempre a a mesma instncia da classe.
	 *
	 * @return ConfiguracaoNegocio instncia atual.
	 */
	public static ConfiguracaoNegocio getInstancia() {
		if (instancia == null) {
			instancia = new ConfiguracaoNegocio();
		}
		return instancia;
	}

	/**
	 * Mtodo que retorna a Data e Hora atual , Quando existe conexo com o servidor ser retornado a mesma do servidor,
	 * Caso o sistema esteja off-line sera obtido a ultima Data/hora do servidor sem conexo ser retornado da maquina local.
	 *
	 * @return Date Data e Hora Atual.
	 */
	public Date obterDataHora(final Date data) throws InconsistenciaExcecao, InfraExcecao{
		Date retorno;
		try {
			retorno = ServicoProxy.getInstance().getDataHoraServidor();
			salvarConfiguracaoTransacional(ULTIMA_DATA, DataUtil.formatar(retorno, DataUtil.DATA_HORA_PADRAO));
		} catch (CircuitBreakerOpenException e) {
			retorno = data;
			validarData(retorno);
		}
		return retorno;
	}

	/**
	 * Mtodo que retorna o modo de Operao da estao local, quando existe conexo com o servidor ser retornado a mesma do servidor,
	 * caso o sistema esteja sem conexo ser retornado da maquina local.
	 *
	 * @return EModoOperacao Modo de Operao da estao local.
	 *
	 * @throws InconsistenciaExcecao,InfraExcecao
	 *
	 * @throws InfraExcecao InfraExcecao Possvel exceo a ser lanada caso ocorra um erro no previsto.
	 */
	public String obterModoOperacao() throws InconsistenciaExcecao,InfraExcecao{
		String retorno;
		try {
			retorno = ServicoProxy.getInstance().obterModoOperacao(System.getProperty(MAC_LOCAL));
			salvarConfiguracao(ConfiguracaoNegocio.MODO_OPERACAO,retorno);
		} catch (CircuitBreakerOpenException e) {
			retorno = obterConfiguracao(MODO_OPERACAO);
		}catch(InfraExcecao e){
			retorno = obterConfiguracao(MODO_OPERACAO);
		}
		if(retorno == null){
			throw new InconsistenciaExcecao("Sem modo de operação");
		}
		return retorno;
	}

	/**
	 * Mtodo que retorna o valor de uma configurao pela sua chave.
	 *
	 * @param id Chave do valor da Configurao.
	 *
	 * @return String Valor da Configurao.
	 *
	 * @throws InfraExcecao InfraExcecao Possvel exceo a ser lanada caso ocorra um erro no previsto.
	 */
	public String obterConfiguracao(final String id) throws InfraExcecao {
		return ConfiguracaoDAO.getInstancia().obterPorId(id);
	}

	/**
	 * Metodo que salva o valor de uma Configurao.
	 *
	 * @param id Chave da Configurao.
 	 * @param valor Valor da Configurao.
	 *
	 * @throws InfraExcecao InfraExcecao Possvel exceo a ser lanada caso ocorra um erro no previsto.
	 */
	public void salvarConfiguracaoTransacional(final String id, final String valor) throws InfraExcecao {
		try {
			ConfiguracaoDAO.getInstancia().remover(id);
			ConfiguracaoDAO.getInstancia().inserir(id, valor);
			Repositorio.getInstancia().commit();
		} catch (InfraExcecao e) {
			Repositorio.getInstancia().rollback();
			throw e;
		}
	}

	public void salvarConfiguracao(final String id, final String valor) throws InfraExcecao {
		try {
			ConfiguracaoDAO.getInstancia().remover(id);
			ConfiguracaoDAO.getInstancia().inserir(id, valor);
		} catch (InfraExcecao e) {
			throw e;
		}
	}
}