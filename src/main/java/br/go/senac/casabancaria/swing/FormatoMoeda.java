package br.go.senac.casabancaria.swing;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class FormatoMoeda {

	public static String formatar(double valor) {
		DecimalFormat df = new DecimalFormat("0.##");
		return df.format(valor);
	}

	public static String formatar(BigDecimal valor) {
		DecimalFormat df = new DecimalFormat("#,##0.00");
		return df.format(valor.doubleValue());

	}

}
