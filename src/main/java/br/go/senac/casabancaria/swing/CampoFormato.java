package br.go.senac.casabancaria.swing;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

public class CampoFormato extends JFormattedTextField {
	private MaskFormatter mascara = null;

	public CampoFormato() {
		adicionaEventos();
	}
	
	public CampoFormato(String formato) throws ParseException {
		super(new MaskFormatter(formato));
		mascara = (MaskFormatter) getFormatter();
		adicionaEventos();
	}
	
	public MaskFormatter getMascara(){
		return mascara;
	}
	
	public String getRealValue(String ... mascara){
		String aux = this.getText();
		for (int i = 0; i < mascara.length; i++) {
			aux = aux.replace(mascara[i],"");
		}
		return aux.trim();
	}
	
	private void adicionaEventos() {
		setFocusLostBehavior(JFormattedTextField.COMMIT);
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				selectAll();
			}
		});
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) transferFocus();
			}
		});
	}

}