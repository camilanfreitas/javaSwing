package gov.goias.sfr.desktop.util;

import org.apache.log4j.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <bold>PropriedadeUtil</bold> Clase que carrega o arquivo de propriedades da aplicação e retorna o valor pela chave.
 *
 * @author Marcos Fernando Costa.
 */
public class PropriedadeUtil {
    private final Logger log = Logger.getLogger(PropriedadeUtil.class);
    private static PropriedadeUtil instancia = null;
    private Properties propriedades = new Properties();

    /**
     * Construtor Padrão privádo que Carrega o arquivo de propriedades.
     */
    private PropriedadeUtil (){
        try {
            propriedades = new Properties();
            final InputStream f = PropriedadeUtil.class.getClassLoader().getResourceAsStream("configuracao.properties");
            propriedades.load(f);
        }catch(IOException e){
            log.fatal(e);
        }
    }

    /**
     * Método estático implementa o padrao singleton e retorna sempre a a mesma instância da classe.
     *
     * @return  PropriedadeUtil instância atual
     */
    public static PropriedadeUtil getInstancia() {
        if(instancia == null){
            instancia = new PropriedadeUtil();
        }
        return instancia;
    }

    /**
     * Método que retorna o valor da propriedade pela sua chave.
     *
     * @param chave Chave da propriedade.
     *
     * @return String Valor da propriedade.
     */
    public String obterPropriedade(final String chave){
        if(propriedades != null){
            return propriedades.getProperty(chave);
        }else{
            return null;
        }
    }

}