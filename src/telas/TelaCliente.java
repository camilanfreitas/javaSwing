package telas;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.JFrame;

import controles.CtrlCliente;
import entidades.Cliente;

public class TelaCliente {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCliente window = new TelaCliente();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */

	public TelaCliente( ) {
		initialize();
	}
	
	public TelaCliente(String nome) {
		setNomeAtendente(nome);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private static String nomeAtendente;
	private static String cpfClienteAtual;
	private static Scanner leitor = new Scanner(System.in);
	

	public static void opcoes(){
		
		Cliente cl;
		boolean b = false;		
		setCpfClienteAtual("123123");
	
		int op=0;
		
		do{
			System.out.println("1-Pesquisar");			
			System.out.println("2-Gravar");
			System.out.println("3-Atualizar");
			op=leitor.nextInt();
			
			switch (op) {
			case 1:

				System.out.println("Digite o CPF");
				String cpf = leitor.next();
				
				pesquisaCliente(cpf);
				
				break;
				
			case 2:


				cl = new Cliente();
				
				System.out.println("CPF: ");		
				cl.setCpf(leitor.next()	);
				System.out.println("Nome: ");
				cl.setNome(leitor.next());
				cl.setBairro("Jd Vitoria");
				cl.setCep(74748456);
				cl.setCidade("Goiania");
				cl.setComplemento(null);
				cl.setEmail(null);
				cl.setEstado("GO");
				cl.setLogradouro("Rua A");
				cl.setNumero(2);
				cl.setPrestacaoTerceiro(0);
				cl.setRenda(3000.50);
				cl.setTipo(2);
				
				//boolean b = Cliente.atualizaDados(cl, getCpfClienteAtual());
				
				

				try {
					b = CtrlCliente.preparaCadastro(cl);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(b) {
					System.out.println("Gravou");
				}else {
					System.out.println("Nao gravou");
				}
				
				break;

				
			case 3:


				cl = new Cliente();
				
				cl.setCpf("111111111");
				cl.setNome("Joao");
				cl.setBairro("Jd Vitoria");
				cl.setCep(74748456);
				cl.setCidade("Goiania");
				cl.setComplemento(null);
				cl.setEmail(null);
				cl.setEstado("GO");
				cl.setLogradouro("Rua A");
				cl.setNumero(2);
				cl.setPrestacaoTerceiro(0);
				cl.setRenda(3000.50);
				cl.setTipo(2);
				
				try {
					b = Cliente.atualizaDados(cl, getCpfClienteAtual());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break;
			default:
				break;
			}
			
		}while(op!=0);
		

	}

	public static String pesquisaCliente (String cpf) {

		Cliente c = CtrlCliente.preparaPesquisa(cpf);

		if(c!=null) {
			System.out.println(c.getNome());
			return c.getNome();
		}else {
			System.out.println("Cliente nao encontrado");
			return "";
		}

	}
		

	public static String getCpfClienteAtual() {
		return cpfClienteAtual;
	}

	public static void setCpfClienteAtual(String cpfClienteAtual) {
		TelaCliente.cpfClienteAtual = cpfClienteAtual;
	}

	public static String getNomeAtendente() {
		return nomeAtendente;
	}

	public static void setNomeAtendente(String nomeAtendente) {
		TelaCliente.nomeAtendente = nomeAtendente;
	}
	
	
	

}
