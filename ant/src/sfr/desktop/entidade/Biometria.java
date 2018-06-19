package gov.goias.sfr.desktop.entidade;

import java.io.Serializable;

/**
 * Classe que representa uma biometria de uma pessoa.
 *
 * @author Marcos Fernando.
 */
public class Biometria implements Serializable {
	private String nome;
	private String cpf;
	private Integer dedo;
	private byte[]  biometria;

	/**
	 * Método que retorna o valor da propriedade Nome.
	 *
	 * @return String Nome da Pessoa.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Método que atribui o valor da propriedade Nome.
	 *
	 * @param nome Nome da Pessoa.
	 */
	public void setNome(final String nome) {
		this.nome = nome;
	}

	/**
	 * Método que retorna o valor da propriedade CPF.
	 *
	 * @return String CPF da Pessoa.
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * Método que atribui o valor da propriedade CPF.
	 *
	 * @param numero Nmero do CPF.
	 */
	public void setCpf(final String numero) {
		this.cpf = numero;
	}

	public Integer getDedo() {
		return dedo;
	}

	public void setDedo(final Integer dedo) {
		this.dedo = dedo;
	}

	public void setBiometria(final byte[] biometria) {
		if (biometria != null) {
			this.biometria = biometria.clone();
		}else {
			this.biometria = null;
		}
	}

	public byte[] getBiometria() {
		if(this.biometria != null) {
			return biometria.clone();
		}else {
			return null;
		}
	}

}