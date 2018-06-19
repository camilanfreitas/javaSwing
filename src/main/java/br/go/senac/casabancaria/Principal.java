package br.go.senac.casabancaria;

import br.go.senac.casabancaria.telas.TelaLogin;
import lombok.extern.log4j.Log4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import javax.swing.*;

/**
 * Classe que inicia o fluxo principal do sistema.
 */
@Log4j
@SpringBootApplication
public abstract class Principal {
	
	/**
	 * Método Inicial do sistema.
	 *
	 * @param args Argumentos básicos (Não utilizados apenas existem pelo padrâo exigido.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			UIManager.put("swing.boldMetal", Boolean.FALSE);
			ConfigurableApplicationContext ctx = new SpringApplicationBuilder(Principal.class).headless(false).run(args);
			ctx.getBean(TelaLogin.class).setVisible(true);
		}catch (Exception e){
			log.error(e.getMessage(),e);
		}
	}
	
}