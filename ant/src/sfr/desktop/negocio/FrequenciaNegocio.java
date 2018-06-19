package gov.goias.sfr.desktop.negocio;

import gov.goias.excecao.InfraExcecao;
import gov.goias.sfr.desktop.dao.FrequenciaDAO;
import gov.goias.sfr.desktop.dao.Repositorio;
import gov.goias.sfr.desktop.entidade.Frequencia;
import gov.goias.sfr.excecao.NaoEncontradoExcecao;
import gov.goias.sfr.excecao.ValidacaoFrequenciaExcecao;
import gov.goias.sfr.service.ServicoProxy;
import gov.goias.sfr.to.FrequenciaTO;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import gov.goias.util.CircuitBreakerOpenException;
import org.apache.log4j.Logger;

public class FrequenciaNegocio{
	private final Logger log = Logger.getLogger(FrequenciaNegocio.class);
	private static FrequenciaNegocio instancia = null;
	public static final String OFF_LINE = "O";
	public static final String ENTRADA = "E";
	public static final String SAIDA = "S";

	private FrequenciaNegocio() {}

	public static FrequenciaNegocio getInstancia(){
		if (instancia == null) {
			instancia = new FrequenciaNegocio();
		}
		return instancia;
	}

	private void registrarFrequenciaLocal(final Date dataAtual, final String cpf) throws ValidacaoFrequenciaExcecao{
		try {
			final Date ultimaFrequencia = FrequenciaDAO.getInstancia().obterUltimaDataFrequenciaPorCPF(cpf);
			if (ultimaFrequencia != null) {
				if (dataAtual.getTime() - ultimaFrequencia.getTime() < 120000.0) { //Regra dois minutos
					throw new ValidacaoFrequenciaExcecao("Tempo mínimo de dois minutos entre registros.");
				}
			}
			final Frequencia f = new Frequencia();
			f.setCpf(cpf);
			f.setData(dataAtual);
			FrequenciaDAO.getInstancia().incluir(f);
			Repositorio.getInstancia().commitFrequencia();
		}catch(InfraExcecao e){
			try {
				Repositorio.getInstancia().rollbackFrequencia();
			}catch (InfraExcecao ex){
				log.error(ex);
			}finally {
				log.error(e);
			}
		}
	}

	public String registrarFrequencia(final Date dataAtual, final String cpf) throws ValidacaoFrequenciaExcecao{
		String retorno = null;
		try{
			retorno = ServicoProxy.getInstance().registrarFrequencia(System.getProperty(ConfiguracaoNegocio.MAC_LOCAL),cpf);
		} catch (CircuitBreakerOpenException e) {
			retorno = OFF_LINE;
			registrarFrequenciaLocal(dataAtual,cpf);
		}catch(InfraExcecao ex){
			log.error(ex);
		}
		return retorno;
	}

	public void removerFrequenciasEnviadas(final Date data) throws InfraExcecao{
		final Calendar c = Calendar.getInstance();
		c.setTime(data);
		c.add(Calendar.DATE, -30);
		try {
			FrequenciaDAO.getInstancia().apagarEnviadas(c.getTime());
			Repositorio.getInstancia().commit();
		} catch (InfraExcecao e) {
				Repositorio.getInstancia().rollback();
			throw e;
		}
	}

	public void enviarFrequencias(Date data) throws InfraExcecao{
		final List<Frequencia> frequencias = FrequenciaDAO.getInstancia().listarNaoEnviadas();
		final List<FrequenciaTO> lista = new ArrayList<FrequenciaTO>();
		int contador = 0;
		if(frequencias.size() > 0) {
			for (Frequencia f : frequencias) {
				if (contador == 100) { //Para nao pesar o servidor
					break;
				}
				final FrequenciaTO to = new FrequenciaTO();
				to.setCpf(f.getCpf());
				to.setData(f.getData());
				f.setEnvio(data);
				FrequenciaDAO.getInstancia().marcarEnvio(f);
				lista.add(to);
				contador++;
			}
			try {
				ServicoProxy.getInstance().enviarFrequencias(System.getProperty(ConfiguracaoNegocio.MAC_LOCAL), lista);
				Repositorio.getInstancia().commit();
			}catch(CircuitBreakerOpenException e) {
				Repositorio.getInstancia().rollback();
				log.debug(e);
			}catch (InfraExcecao e) {
				Repositorio.getInstancia().rollback();
				throw e;
			}catch(NaoEncontradoExcecao e){
				Repositorio.getInstancia().rollback();
				log.error(e);
			}
		}
	}

}