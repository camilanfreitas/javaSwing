package entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Emprestimo {

	private int codigo;
	private String cliente;
	private String Atendente;
	private double txJuros;
	private String unidMedida;
	private double valorEmprestimo;
	private int prazo;
	private int tipo;

	public static boolean guardarDados(Emprestimo c){

		boolean status=false;

		try {

			Connection con = ConectaBD.conectaAoBanco();

			String comando = "INSERT INTO emprestimo VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement instrucao = con.prepareStatement(comando);

			instrucao.setInt(1, c.getCodigo());
			instrucao.setString(2, c.getCliente());
			instrucao.setString(3, c.getAtendente());
			instrucao.setDouble(4, c.getTxJuros());
			instrucao.setString(5, c.getUnidMedida());
			instrucao.setDouble(6, c.getValorEmprestimo());
			instrucao.setInt(7, c.getPrazo());
			instrucao.setInt(8, c.getTipo());

			int affectedRows = instrucao.executeUpdate();

			// check the affected rows 
			if (affectedRows > 0) {             
				status=true;
			}
			
			con.close();

		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage()+ " | SQLException");
		}

		return status;		
	}
	
	public static ArrayList<Emprestimo> pesquisaDados(Cliente c){

		ArrayList<Emprestimo> emp = new ArrayList<>();

		try {

			Connection con = ConectaBD.conectaAoBanco();

			String comando = "SELECT FROM emprestimo WHERE cliente = (?)";
			PreparedStatement instrucao = con.prepareStatement(comando);

			instrucao.setString(1, c.getCpf());

			ResultSet rs = instrucao.executeQuery();
			
			Emprestimo e;

			if (rs.isBeforeFirst()) { 
				while(rs.next()) {
					e = new Emprestimo();				
										
					e.setCodigo(rs.getInt(1));
					e.setCliente(rs.getString(2));
					e.setAtendente(rs.getString(3));
					e.setTxJuros(rs.getDouble(4));
					e.setUnidMedida(rs.getString(5));
					e.setValorEmprestimo(rs.getDouble(6));
					e.setPrazo(rs.getInt(7));
					e.setTipo(rs.getInt(8));
					
					emp.add(e);
				}				
			} 
			con.close();

		} catch (SQLException | ClassNotFoundException r) {
			System.out.println(r.getMessage()+ " | SQLException");
		}

		return emp;		
	}
	
	
	public static boolean apagaDados(Emprestimo c){

		boolean status=false;

		try {

			Connection con = ConectaBD.conectaAoBanco();

			String comando = "DELETE FROM emprestimo WHERE codigo = (?)";
			PreparedStatement instrucao = con.prepareStatement(comando);

			instrucao.setInt(1, c.getCodigo());

			int affectedRows = instrucao.executeUpdate();

			// check the affected rows 
			if (affectedRows > 0) {             
				status=true;
			}

		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage()+ " | SQLException");
		}

		return status;		
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getAtendente() {
		return Atendente;
	}

	public void setAtendente(String atendente) {
		Atendente = atendente;
	}

	public double getTxJuros() {
		return txJuros;
	}

	public void setTxJuros(double txJuros) {
		this.txJuros = txJuros;
	}

	public String getUnidMedida() {
		return unidMedida;
	}

	public void setUnidMedida(String unidMedida) {
		this.unidMedida = unidMedida;
	}

	public double getValorEmprestimo() {
		return valorEmprestimo;
	}

	public void setValorEmprestimo(double valorEmprestimo) {
		this.valorEmprestimo = valorEmprestimo;
	}

	public int getPrazo() {
		return prazo;
	}

	public void setPrazo(int prazo) {
		this.prazo = prazo;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}


}
