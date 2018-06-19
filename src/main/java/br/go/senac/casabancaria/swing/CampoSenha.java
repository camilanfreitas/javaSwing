package br.go.senac.casabancaria.swing;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPasswordField;

public class CampoSenha extends JPasswordField {
	private static final long serialVersionUID = 7525806870660455191L;

	public CampoSenha() {
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

	public CampoSenha(int tamanho) {
		this();
		this.setDocument(new LimitadorTexto(tamanho));
	}

}