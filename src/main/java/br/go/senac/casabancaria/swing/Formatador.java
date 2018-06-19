package br.go.senac.casabancaria.swing;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

public class Formatador {
	
	public static final String FORMATO_CPF  = "###.###.###-##";
	public static final String FORMATO_CNPJ = "###.###.###.###/####-##";
	public static final String FORMATO_CEP	= "###.###-###";
	
	public static String formatar(String texto, String formato){
		MaskFormatter formatador = null;
		String retorno = null;
		try {
			formatador = new MaskFormatter(formato);
			formatador.setValueContainsLiteralCharacters(false);
			retorno = formatador.valueToString(texto);
		} catch (ParseException e) {
			
		}
		return retorno;
		
	}
	
}