package telas;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import controles.CtrlCliente;
import entidades.Cliente;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		frame.setBounds(100, 100, 584, 455);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblProcurarPorUm = new JLabel("Pesquisar por um Cliente (CPF)");
		lblProcurarPorUm.setBounds(25, 38, 225, 14);
		frame.getContentPane().add(lblProcurarPorUm);
		
		textPesquisaCpf = new JTextField();
		textPesquisaCpf.setBounds(25, 52, 136, 23);
		frame.getContentPane().add(textPesquisaCpf);
		textPesquisaCpf.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(!textPesquisaCpf.getText().equals("")) {
					pesquisaCliente(textPesquisaCpf.getText());
				}else {
					JOptionPane.showMessageDialog(null, "Digite um CPF");
				}
				
				
				
			}
		});
		btnPesquisar.setBounds(161, 52, 110, 23);
		frame.getContentPane().add(btnPesquisar);
		
		btnCadastrarNovoCliente = new JButton("Cadastrar Novo Cliente");
		btnCadastrarNovoCliente.setBounds(413, 52, 128, 23);
		frame.getContentPane().add(btnCadastrarNovoCliente);
		
		

		



	}

	private DefaultTableModel modeloTable;

	private void preencherJtableCidade() {
		//Aqui carrego minha lista
		ArrayList <Cliente> listaClientes = CtrlCliente.buscaClientes();
		modeloTable = new DefaultTableModel();

		//Aqui verifico se a jTable tem algum registo se tiver eu deleto
		while (modeloTable.getRowCount() > 0) {
			modeloTable.removeRow(0);
		}

		//Aqui eu adiciono cada linha da lista na jTable
		for (Cliente cliente : listaClientes) {
			System.out.println(cliente.getNome()+", "+cliente.getCpf()+", "+cliente.getTipo());
		}
	}








	private static String nomeAtendente;
	private static String cpfClienteAtual;
	private static Scanner leitor = new Scanner(System.in);	
	private JTextField textPesquisaCpf;
	private JButton btnCadastrarNovoCliente;

	public static void opcoes(String user){

		Cliente cl;
		boolean b = false;		
		setCpfClienteAtual("123123");
		setNomeAtendente(user);
		CtrlCliente.setUser(user);

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




				b = CtrlCliente.preparaCadastro(cl);


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


				b = Cliente.atualizaDados(cl, getCpfClienteAtual());


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
