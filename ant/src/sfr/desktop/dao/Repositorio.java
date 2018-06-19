package gov.goias.sfr.desktop.dao;

import gov.goias.excecao.InfraExcecao;
import org.apache.log4j.Logger;
import org.h2.tools.Server;

import java.sql.*;

/**
 * <b>Repositorio</b> Classe responsável por embarcar o banco de dados H2.
 * Constroi a estrutura caso não exista (banco novo).
 * Controla a conexão.
 *
 * @author Marcos Fernando.
 */
public class Repositorio {
    private final Logger log = Logger.getLogger(Repositorio.class);
    private static final String OS = System.getProperty("os.name").toLowerCase();
    private static final String DRIVER   = "org.h2.Driver";
    private static final String URL_WIN  = "jdbc:h2:file:C:/ponto/SFR";
    private static final String URL_UX   = "jdbc:h2:tcp://localhost/db/SFR";
    private static final String URL      = OS.indexOf("win") >= 0 ? URL_WIN : URL_UX;
    private static final String USUARIO  = "supart";
    private static Repositorio instancia = null;
    private Server banco = null;
    private Connection conexaoFrequencia = null;
    private Connection conexao = null;

    /**
     * Construtor Privado que inicia o banco H2 embarcado.
     * Caso ocorra qualquer problema gera um log e aborta a execução do sistema.
     */
    private Repositorio(){
        try {
            banco = Server.createPgServer().createTcpServer();
            banco.start();
            log.info("Banco de dados H2 Inicializado...");
            Class.forName(DRIVER);
            log.info("Conectando ao banco de dados H2...");
            conexao = DriverManager.getConnection(URL, USUARIO, getSenha());
            conexao.setAutoCommit(false);
            construirEstrutura();
            log.info("Verificando estrutura do banco.");
            conexaoFrequencia = DriverManager.getConnection(URL, USUARIO, getSenha());
            conexaoFrequencia.setAutoCommit(false);
            log.info("Conectado com sucesso ao banco de dados H2.");
        } catch (Exception ex) {
            log.fatal("Erro ao iniciar ou conectar ao banco de dados \n" + ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    /**
     * Método estático implementa o padro singleton e retorna sempre a a mesma instância da classe.
     *
     * @return Repositorio instancia atual.
     */
    public static Repositorio getInstancia(){
        if(instancia == null){
            instancia = new Repositorio();
        }
        return instancia;
    }

    /**
     * Método que constroi a estrutura de tabelas (caso no exista).
     *
     * @throws InfraExcecao Possível exceção a ser lançada caso ocorra algum problema para criar a estrutura de tabelas.
     */
    public void construirEstrutura() throws InfraExcecao{
        executarDDL("CREATE TABLE IF NOT EXISTS FREQUENCIA(FREQ_ID BIGINT AUTO_INCREMENT, FREQ_CPF VARCHAR(11) NULL, FREQ_DATA TIMESTAMP NOT NULL, FREQ_ENVIO TIMESTAMP)");
        executarDDL("ALTER TABLE FREQUENCIA DROP COLUMN IF EXISTS FREQ_MODO");
        executarDDL("ALTER TABLE FREQUENCIA DROP COLUMN IF EXISTS FREQ_DIGITAL");
        executarDDL("CREATE TABLE IF NOT EXISTS CONFIGURACAO(CONF_ID VARCHAR(20) NOT NULL, CONF_VALOR VARCHAR(80), PRIMARY KEY(CONF_ID))");
        executarDDL("DROP TABLE IF EXISTS SERVIDOR");
        executarDDL("CREATE TABLE IF NOT EXISTS BIOMETRIA(BIOM_ID BIGINT AUTO_INCREMENT,PESS_CPF VARCHAR(11) NOT NULL, PESS_NOME VARCHAR(80) NOT NULL, PESS_DEDO INT NOT NULL , PESS_DIGITAL BLOB, PRIMARY KEY(BIOM_ID))");
        corrigirReenvio();
    }

    private void executarDDL(final String sql) throws InfraExcecao{
        try{
            final PreparedStatement p = conexao.prepareStatement(sql);
            p.execute();
            p.close();
        }catch(Exception e){
            log.debug(sql);
            log.error(e);
            throw new InfraExcecao(e);
        }
    }

    /**
     * Método que retorna a conexão separada para frequência com o banco de dados embarcado H2.
     *
     * @return Connnection Conexão com o banco de dados.
     *
     * @throws InfraExcecao Possível exceção a ser lançada caso no tenha conexão com o banco o a mesma esteja fechada.
     */
    public Connection obterConexaoFrequencia() throws InfraExcecao {
        try {
            if(conexaoFrequencia == null || conexaoFrequencia.isClosed()) {
                final InfraExcecao e = new InfraExcecao("Conexo Fechada...");
                log.fatal(e);
                throw e;
            }
        } catch (SQLException e) {
            log.fatal(e);
            throw new InfraExcecao(e);
        }
        return conexaoFrequencia;
    }

    /**
     * Método que retorna a conexão padrão com o banco de dados embarcado H2.
     *
     * @return Connnection Conexão com o banco de dados.
     *
     * @throws InfraExcecao Possível exceção a ser lançada caso no tenha conexão com o banco o a mesma esteja fechada.
     */
    public Connection obterConexao() throws InfraExcecao {
        try {
            if(conexao == null || conexao.isClosed()) {
                final InfraExcecao e = new InfraExcecao("Conexo Fechada...");
                log.fatal(e);
                throw e;
            }
        } catch (SQLException e) {
            log.fatal(e);
            throw new InfraExcecao(e);
        }
        return conexao;
    }

    /**
     * Método que efetiva as alterações na conexão separada da frequência de DML no banco de dados (commit).
     *
     * @throws InfraExcecao Possivel exceção a ser lancada caso ocorra algum erro na transção corrente.
     */
    public void commitFrequencia() throws InfraExcecao{
        try{
            conexaoFrequencia.commit();
        }catch(Exception e){
            log.error(e);
            throw new InfraExcecao(e);
        }
    }

    /**
     * Método que desfaz as alterações de DML na conexão separada de frequência no banco de dados (commit).
     *
     * @throws InfraExcecao Possivel exceção a ser lancada caso ocorra algum erro na transção corrente.
     */
    public void rollbackFrequencia() throws InfraExcecao{
        try{
            conexaoFrequencia.rollback();
        }catch(Exception e){
            log.error(e);
            throw new InfraExcecao(e);
        }
    }

    /**
     * Método que efetiva as alterações de DML na conexão padrão no banco de dados (commit).
     *
     * @throws InfraExcecao Possivel exceção a ser lancada caso ocorra algum erro na transção corrente.
     */
    public void commit() throws InfraExcecao{
        try{
            conexao.commit();
        }catch(Exception e){
            log.error(e);
            throw new InfraExcecao(e);
        }
    }

    /**
     * Metodo que desfaz as alteraces DML no banco de dados (rollback).
     *
     * @throws InfraExcecao Possivel excecao a ser lancada caso ocorra algum erro ao desfazer a transao corrente.
     */
    public void rollback() throws InfraExcecao{
        try{
            conexao.rollback();
        }catch(Exception e){
            log.error(e);
            throw new InfraExcecao(e);
        }
    }


    /**
     * Método que fecha a conexão separada da frequência com o banco de dados.
     *
     * @throws InfraExcecao Possível exceção a ser lancada caso ocorra algum erro ao fechar.
     */
    public void fecharFrequencia() throws InfraExcecao{
        try{
            obterConexaoFrequencia().close();
        }catch(Exception e){
            log.error(e);
            throw new InfraExcecao(e);
        }
    }

    /**
     * Método que fecha a conexão padrão com o banco de dados.
     *
     * @throws InfraExcecao Possvel exceo a ser lanada caso no consiga fechar a conexo.
     */
    public void fechar() throws InfraExcecao{
        try{
            obterConexao().close();
        }catch(Exception e){
            log.error(e);
            throw new InfraExcecao(e);
        }
    }

    private void corrigirReenvio(){
        PreparedStatement p0 = null;
        ResultSet r = null;
        try {
            p0 = conexao.prepareStatement("SELECT COUNT(CONF_VALOR) AS TOT FROM CONFIGURACAO WHERE CONF_ID = 'REENVIAR'");
            r = p0.executeQuery();
            r.next();
            if(r.getInt(1) == 0 ){
                PreparedStatement p = conexao.prepareStatement("INSERT INTO CONFIGURACAO(CONF_ID,CONF_VALOR)VALUES('REENVIAR','S')");
                p.execute();
                p.close();
                p = conexao.prepareStatement("UPDATE FREQUENCIA SET FREQ_ENVIO = NULL");
                p.execute();
                p.close();
                conexao.commit();
                log.info("aplicou reenvio");
            }
        }catch(Exception e){
            try {
                conexao.rollback();
                log.error(e);
            }catch(Exception ex){
                log.error(ex);
            }
        }finally {
            try {
                if (r != null) r.close();
            }catch(Exception ex){
                log.error(ex);
            }
            try{
                if (p0 != null) p0.close();
            }catch(Exception ex){
                log.error(ex);
            }
        }
    }

    private String getSenha(){
        return "$Up4rt";
    }

}