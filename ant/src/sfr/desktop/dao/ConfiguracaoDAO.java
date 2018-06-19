package gov.goias.sfr.desktop.dao;

import gov.goias.excecao.InfraExcecao;
import org.apache.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Classe de persistência da Entidade de Configuração.
 *
 * @author Marcos Fernando.
 */
public class ConfiguracaoDAO {
    private final Logger log = Logger.getLogger(ConfiguracaoDAO.class);
    private static ConfiguracaoDAO instancia = null;

    /**
     * Metodo estático implementa o padrão singleton e retorna sempre a mesma instancia da classe.
     *
     * @return ConfiguracaoDAO Instância atual.
     *
     * @throws InfraExcecao Possível exceção a ser lançada caso o mesmo não consiga obter uma conexão com o Repositório.
     */
    public static ConfiguracaoDAO getInstancia() throws InfraExcecao {
        if(instancia == null){
            instancia = new ConfiguracaoDAO();
        }
        return instancia;
    }

    /**
     * Mtodo que obtem uma configurao pelo seu Id;
     *
     * @param id Valor do Identificador;
     *
     * @return String Valor da Configuração.
     *
     * @throws InfraExcecao Possível exceção a ser lançada caso ocorra algum problema.
     */
    public String obterPorId(final String id) throws InfraExcecao {
        String retorno = null;
        final String sql = "SELECT CONF_VALOR FROM CONFIGURACAO WHERE CONF_ID = ?";
        try{
            final PreparedStatement c = Repositorio.getInstancia().obterConexao().prepareStatement(sql);
            c.setString(1, id);
            final ResultSet r = c.executeQuery();
            if (r.next()) {
                retorno = r.getString("CONF_VALOR");
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
     * Método que insere o valor no banco de dados.
     *
     * @param id  Identificador do valor.
     * @param valor Valor do valor.
     *
     * @throws InfraExcecao Possível exceção a ser lançada caso ocorra algum problema.
     */
    public void inserir(final String id, final String valor) throws InfraExcecao{
        final String sql = "INSERT INTO CONFIGURACAO(CONF_ID,CONF_VALOR)VALUES(?,?)";
        try{
            final PreparedStatement c = Repositorio.getInstancia().obterConexao().prepareStatement(sql);
            c.setString(1,id);
            c.setString(2,valor);
            c.execute();
            c.close();
        } catch (Exception e) {
            log.debug(sql);
            log.error(e);
            throw new InfraExcecao(e);
        }
    }

    /**
     * Método que remove o valor no banco de dados.
     *
     * @param id  Identificador do valor.
     *
     * @throws InfraExcecao Possível exceção a ser lançada caso ocorra algum problema.
     */
    public void remover(final String id) throws InfraExcecao{
        final String sql = "DELETE FROM CONFIGURACAO WHERE CONF_ID  = ?";
        try{
            final PreparedStatement c = Repositorio.getInstancia().obterConexao().prepareStatement(sql);
            c.setString(1,id);
            c.execute();
            c.close();
        } catch (Exception e) {
            log.debug(sql);
            log.error(e);
            throw new InfraExcecao(e);
        }
    }

}