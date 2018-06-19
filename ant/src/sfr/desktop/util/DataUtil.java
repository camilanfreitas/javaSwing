package gov.goias.sfr.desktop.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * <bold>DataUtil</bold> Classe que contem utilitários de data.
 *
 * @author Marcos Fernando.
 */
public abstract class DataUtil {
    public static final String DATA_HORA_PADRAO = "dd/MM/yyyy HH:mm";
    public static final String HORA_MINUTO  = "HH:mm";

    /**
     * Método estático que formata uma data em um formato desejado como Texto.
     *
     * @param data Data a ser formatada.
     * @param formato Formato desejado ex: "dd/mm/yyyy"
     *
     * @return String data Formatada.
     */
    public static String formatar(final Date data, final String formato){
        if(data != null && formato != null){
            return new SimpleDateFormat(formato).format(data) ;
        }else{
            return null;
        }
    }

    /**
     * Método estático que retorna uma data por extenso em português.
     *
     * @param data Data desejada.
     *
     * @return String data por extenso.
     */
    public static String porExetenso(final Date data){
        if(data != null) {
            final Calendar c = Calendar.getInstance();
            c.setTime(data);
            final String meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
            final String dias[] = {"Domingo", "Segunda-feira", "Terça-feira", "Quarta-feira", "Quinta-feira", "Sexta-feira", "Sábado"};
            final int d = c.get(Calendar.DAY_OF_MONTH);
            final int s = c.get(Calendar.DAY_OF_WEEK);
            final int m = c.get(Calendar.MONTH);
            return String.format("%s, %s de %s", dias[s - 1], d, meses[m]);
        }else{
            return null;
        }
    }

    /**
     * Método que retorna uma data por uma string de data.
     * O formato esperado e: dd/MM/yyyy HH:mm:ss.
     * Caso o parametro seja inválido sera sempre retornado nulo.
     *
     * @param data String de data.
     *
     * @return Date Objeto tipo data.
     */
    public static Date obterData(final String data){
        if(data != null){
            final DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ENGLISH);
            try {
                return df.parse(data);
            }catch(ParseException e){
                return null;
            }
        }else {
            return null;
        }
    }

}