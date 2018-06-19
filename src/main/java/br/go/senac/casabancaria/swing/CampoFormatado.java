package br.go.senac.casabancaria.swing;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

public class CampoFormatado extends JFormattedTextField {
	
	public CampoFormatado() {
		adicionaEventos();
	}
	
	public CampoFormatado(String formato) throws ParseException {
		super(new MaskFormatter(formato));
		adicionaEventos();
	}
	
	private void adicionaEventos() {
		setFocusLostBehavior(JFormattedTextField.COMMIT);

		addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				selectAll();
			}
			//
			// // @Override
			// // public void focusLost(FocusEvent e) {
			// // }
		});

		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) transferFocus();
			}
		});
	}
	
	public MaskFormatter getMascara() {
		return  (MaskFormatter) getFormatter();
	}
	
}