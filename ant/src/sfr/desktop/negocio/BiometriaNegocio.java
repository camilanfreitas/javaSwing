package gov.goias.sfr.desktop.negocio;

import gov.goias.excecao.InfraExcecao;
import gov.goias.sfr.desktop.dao.BiometriaDAO;
import gov.goias.sfr.desktop.dao.Repositorio;
import gov.goias.sfr.desktop.entidade.Biometria;
import gov.goias.sfr.excecao.NaoEncontradoExcecao;
import gov.goias.sfr.service.ServicoGriaule;
import gov.goias.sfr.service.ServicoProxy;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import gov.goias.sfr.to.BiometriaTO;
import gov.goias.util.CircuitBreakerOpenException;

import org.apache.log4j.Logger;

public class BiometriaNegocio {
	private final Logger log = Logger.getLogger(BiometriaNegocio.class);
	private static BiometriaNegocio instancia = null;

	public static BiometriaNegocio getInstancia(){
		if (instancia == null) {
			instancia = new BiometriaNegocio();
		}
		return instancia;
	}

	public void sincronizar() throws InfraExcecao{
		try{
			final List<BiometriaTO> biometrias = ServicoProxy.getInstance().obterBiometrias(System.getProperty(ConfiguracaoNegocio.MAC_LOCAL));
			try {
				BiometriaDAO.getInstancia().limpar();
				for (BiometriaTO to : biometrias) {
					final Biometria b = new Biometria();
					b.setNome(to.getNome());
					b.setCpf(to.getCpf());
					b.setDedo(1);
					b.setBiometria(to.getDigital());
					BiometriaDAO.getInstancia().inserir(b);
				}
				Repositorio.getInstancia().commit();
			}catch(InfraExcecao e){
				log.error("Falha ao atualizar biometrias!", e);
				Repositorio.getInstancia().rollback();
				throw e;
			}
		} catch (CircuitBreakerOpenException e) {
			log.error(e);
		}
	}

	public Biometria obterPorCpf(final String cpf) throws NaoEncontradoExcecao, InfraExcecao {
		final Biometria servidor = BiometriaDAO.getInstancia().obterPorCpf(cpf);
		if(servidor == null){
			throw new NaoEncontradoExcecao("cpf");
		}
		return servidor;
	}
	
	public List<Biometria> listarTodas() throws InfraExcecao{
		return BiometriaDAO.getInstancia().listarTodos();
	}

}