package gov.goias.sfr.desktop.entidade;

import java.util.Date;

/**
 *Classe que representa o registro de uma Frequencia.
 *
 * @author Marcos Fernando.
 */
public class Frequencia {
    private Long id;
    private String cpf;
    private Date data;
    private Date envio;

    /**
     * Método que retorna o valor da propriedade id.
     *
     * @return Long Id Identificador da frequência.
     */
    public Long getId() {
        return id;
    }

    /**
     * Método que atribui o valor da propriedade Id.
     *
     * @param id Id Identificador da frequência.
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     *Método que retorna o valor da propriedade CPF.
     *
     * @return String CPF da pessoa a registrar a frequência.
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Método que atribui o valor da propriedade CPF.
     *
     * @param numero CPF da pessoa a registrar a frequência.
     */
    public void setCpf(final String numero) {
        this.cpf = numero;
    }

    /**
     * Método que retorna o valor da propriedade Data e representa a data/hora (momento) do registro da frequência.
     *
     * @return Date Data da frequência.
     */
    public Date getData() {
        if(data != null) {
            return (Date)data.clone();
        }else{
            return null;
        }
    }

    /**
     * Método que atribui o valor da propriedade Data e representa a data/hora (momento) do registro da frequência.
     *
     * @param data Data da Frequência.
     */
    public void setData(final Date data) {
        if(data != null){
            this.data = (Date)data.clone();
        }else{
            this.data = null;
        }
    }

    /**
     * Método que retorna o valor da propriedade Envio e representa a data/hora (momento) do envio ao serivdor da frequência.
     *
     * @return Date Data do envio.
     */
    public Date getEnvio() {
        return envio;
    }

    /**
     * Método que atribui o valor da propriedade Envio e representa a data/hora (momento) da criao do registro da frequência.
     *
     * @param data Data da Frequência.
     */
    public void setEnvio(final Date data) {
        this.envio = data;
    }

}