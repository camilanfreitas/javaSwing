package br.go.senac.casabancaria.swing;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;


public class CampoMoeda extends JFormattedTextField{
	private static final long serialVersionUID = -7866771598150045558L;
	Locale ptBr = new Locale("pt", "BR");
	NumberFormat formato = NumberFormat.getCurrencyInstance(ptBr);
	
	public CampoMoeda(int tamanho, int decimais) {
		//super(currency);
		setHorizontalAlignment(SwingConstants.RIGHT);
		adicionaOuvinteFoco();
		criaDocument(tamanho,decimais);
	}
	
	private void adicionaOuvinteFoco(){
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				setText(getText().replace(".",""));
				selectAll();
			}
			@Override
			public void focusLost(FocusEvent e){
				if(!getText().isEmpty()) {
					setText(formato.format(Double.parseDouble(getText().replace(",", "."))).replace("R$", ""));
				}
			}
		});
	}
	
	public Double getDoubleValue(){
		if(!getText().isEmpty()){
			return Double.parseDouble(getText().replace(".","").replace(",","."));
		}
		return 0d;
	}
	
	
	private void criaDocument(final int tamanho, final int decimais){
		this.setDocument(new PlainDocument(){
			private static final long serialVersionUID = 1L;
			
			public void insertString(int offSet, String str, AttributeSet attr) throws BadLocationException{
				boolean inserir = true;
				String texto = null;
				
				if(str.length() > 1){
					texto = str;
				}else{
					texto = getText(0, getLength());	
				}
				//Verifica a digitação de ponto
 				if((str.codePointAt(0) == 46 || str.codePointAt(0) == 44) && !texto.contains(",") ){
					super.insertString(offSet, ",", attr);
					inserir = false;
				}
				//verifica se sao apenas numeros
				if( inserir == true && (str.codePointAt(0) < 48 || str.codePointAt(0) > 57 ) ){
					inserir = false;
				}
				if(inserir == true){
					int posicaoPonto = texto.indexOf(",");
					//verifica o tamanho do campo quando não tem decimais
					if( posicaoPonto == -1 ){
						if ( (getLength()  + str.length() ) >= ((tamanho - decimais) + 1)){
							inserir = false;
						}
					}else{ //verificando o tamanho quando existe decimais
						if ( (getLength()  + str.length() ) >= (tamanho +2)){
							inserir = false;
						}
					}
				}
				if(inserir){
					super.insertString(offSet, str, attr);
		        }
			}
			
		});
	}

}