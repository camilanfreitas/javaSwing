package br.go.senac.casabancaria.swing;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;


public class CampoTexto extends JTextField{
	private static final long serialVersionUID = -7866771598150045558L;
	
	public CampoTexto(){
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				selectAll();
			}
		});
		
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent evt) {
			if (evt.getKeyCode() == KeyEvent.VK_ENTER){
				transferFocus();
			}
			}
		});
	}
	
	public CampoTexto(int tamanho) {
		this();
		this.setDocument(new LimitadorTexto(tamanho));
	}
	
}