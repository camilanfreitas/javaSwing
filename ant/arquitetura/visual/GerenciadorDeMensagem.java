package midianet.arquitetura.visual;

import javax.swing.JOptionPane;

public class GerenciadorDeMensagem {
	
	public static void mensagemAlerta(String mensagem){
		JOptionPane.showMessageDialog(null, mensagem,"NSilva" ,JOptionPane.WARNING_MESSAGE);
	}
	
}