package br.go.senac.casabancaria.swing;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JFormattedTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;


public class CampoNumero extends JFormattedTextField{
	private static final long serialVersionUID = -7866771598150045558L;
	
	public CampoNumero(){
		adicionaOuvinteFoco();
		criaDocument(-1);
	}
	
	public CampoNumero(int tamanho) {
		adicionaOuvinteFoco();
		criaDocument(tamanho);
	}
	
	private void adicionaOuvinteFoco(){
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				selectAll();
			}
		});
	}
	
	private void criaDocument(final int tamanho){
		if(tamanho == -1){
			this.setDocument(new PlainDocument(){
				private static final long serialVersionUID = 1L;

				public void insertString(int offSet, String str, AttributeSet attr) throws BadLocationException{
				if(str.codePointAt(0) < 48 || str.codePointAt(0) > 57 ) {
					return;
				}
				super.insertString(offSet, str, attr);
				}
			});
		}else{
			this.setDocument(new PlainDocument(){
				private static final long serialVersionUID = 1L;

				public void insertString(int offSet, String str, AttributeSet attr) throws BadLocationException{
				if(str.codePointAt(0) < 48 || str.codePointAt(0) > 57 ) {
					return;
				}
				if(str != null && (getLength() + str.length() < tamanho +1 )){
					super.insertString(offSet, str, attr);
		        }
				}
			});
		}
	}
	
}