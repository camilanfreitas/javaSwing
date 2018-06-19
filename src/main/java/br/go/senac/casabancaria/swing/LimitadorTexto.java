package br.go.senac.casabancaria.swing;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class LimitadorTexto extends PlainDocument {
	private static final long serialVersionUID = -205598652587702025L;

	private int tamanho;

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		setTamanho(tamanho);
	}

	public LimitadorTexto(int tamanho){
		this.tamanho = tamanho;
	}
	
	private LimitadorTexto(){
		
	}
	
	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
			if(str != null && (getLength() + str.length() < tamanho +1 )){
				super.insertString(offs, str, a);
	        }
	}
	
}