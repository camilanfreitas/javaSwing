package entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Cliente {

	private String cpf;
	private String nome;
	private int	tipo;
	private double prestacaoTerceiro;
	private double renda;
	private String email;
	private int cep;
	private String logradouro;
	private int numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String estado;
	private String atendente;

	public static boolean guardarDados(Cliente c) throws ClassNotFoundException, SQLException {

		Connection con = ConectaBD.conectaAoBanco();

		String comando = "INSERT INTO cliente(cpf, nome, tipo, prestacaoTerceiro, renda, email, cep, logradouro, numero, complemento, bairro, cidade, estado, atendente)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement instrucao = con.prepareStatement(comando);

		instrucao.setString(1, c.getCpf());
		instrucao.setString(2, c.getNome());
		instrucao.setInt(3, c.getTipo());
		instrucao.setDouble(4,  c.getPrestacaoTerceiro());
		instrucao.setDouble(5, c.getRenda());
		instrucao.setString(6, c.getEmail());
		instrucao.setInt(7, c.getCep());
		instrucao.setString(8, c.getLogradouro());
		instrucao.setInt(9, c.getNumero());
		instrucao.setString(10, c.getComplemento());
		instrucao.setString(11, c.getBairro());
		instrucao.setString(12, c.getCidade());
		instrucao.setString(13, c.getEstado());
		instrucao.setString(14, c.getAtendente());

		instrucao.executeUpdate();

		return true;		
	}

	public static ResultSet pesquisaCliente(String documento) throws ClassNotFoundException, SQLException {

		String comando = "SELECT * FROM cliente WHERE cpf like (?)";

		Connection con = ConectaBD.conectaAoBanco();

		PreparedStatement instrucao = con.prepareStatement(comando);

		instrucao.setString(1, documento);

		ResultSet rs = instrucao.executeQuery();


		con.close();			

		return rs;	

	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public double getPrestacaoTerceiro() {
		return prestacaoTerceiro;
	}

	public void setPrestacaoTerceiro(double prestacaoTerceiro) {
		this.prestacaoTerceiro = prestacaoTerceiro;
	}

	public double getRenda() {
		return renda;
	}

	public void setRenda(double renda) {
		this.renda = renda;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCep() {
		return cep;
	}

	public void setCep(int cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getAtendente() {
		return atendente;
	}

	public void setAtendente(String atendente) {
		this.atendente = atendente;
	}


}
