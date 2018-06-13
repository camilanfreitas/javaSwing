package telas;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import controles.CtrlCliente;
import entidades.Atendente;
import entidades.Cliente;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class TelaCliente {

	private static JFrame frame;


	private static Atendente at;
	private static String cpfClienteAtual;
	private static Scanner leitor = new Scanner(System.in);	
	private static JFormattedTextField textPesquisaCpf;
	private static JButton btnCadastrarNovoCliente;
	private static JLabel lblNome;
	private static JTextField textField;
	private static JTextField textCpf;
	private static JLabel lblCategoria;
	private static JTextField textRenda;

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
		Atendente a = new Atendente();
		a.setNome("Camila");
		at=a;
		initialize();
	}

	public static void iniciaTela(Atendente a) {
		at = a;
		main(null);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private static void initialize() {
		frame = new JFrame();
		frame.setBounds(50, 50, 900, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblWelcome = new JLabel("Ol\u00E1 "+at.getNome()+ ", o que deseja fazer?");
		lblWelcome.setBounds(10, 92, 302, 14);
		frame.getContentPane().add(lblWelcome);
		
		
		textPesquisaCpf = new JFormattedTextField();
		 try{
	           javax.swing.text.MaskFormatter format_textField4 = new javax.swing.text.MaskFormatter("###.###.###-##");
	           textPesquisaCpf = new javax.swing.JFormattedTextField(format_textField4);
	        }catch (Exception e){
	        	System.out.println(e.getMessage());
	        }		
		textPesquisaCpf.setBounds(572, 88, 131, 23);
		frame.getContentPane().add(textPesquisaCpf);
		textPesquisaCpf.setColumns(10);

		JButton btnPesquisar = new JButton("Pesquisar Cliente");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(!textPesquisaCpf.getText().equals("")) {
					pesquisaCliente(textPesquisaCpf.getText());
				}else {
					JOptionPane.showMessageDialog(null, "Digite um CPF");
				}
			}
		});
		btnPesquisar.setBounds(714, 88, 160, 23);
		frame.getContentPane().add(btnPesquisar);

		btnCadastrarNovoCliente = new JButton("Cadastrar Novo Cliente");
		btnCadastrarNovoCliente.setBounds(335, 88, 189, 23);
		frame.getContentPane().add(btnCadastrarNovoCliente);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 122, 864, 2);
		frame.getContentPane().add(separator);

		lblCategoria = new JLabel("Categoria:");
		lblCategoria.setBounds(10, 149, 70, 14);
		frame.getContentPane().add(lblCategoria);

		JComboBox<String> comboBoxTipo = new JComboBox<String>();
		comboBoxTipo.addItem("1 - Funcionário Público");
		comboBoxTipo.addItem("2 - Aposentado");
		comboBoxTipo.addItem("3 - Pensionista");
		comboBoxTipo.setBounds(73, 146, 189, 20);
		frame.getContentPane().add(comboBoxTipo);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(271, 152, 28, 14);
		frame.getContentPane().add(lblCpf);

		textCpf = new JTextField();
		 try{
	           javax.swing.text.MaskFormatter format_textField4 = new javax.swing.text.MaskFormatter("###.###.###-##");
	           textCpf = new javax.swing.JFormattedTextField(format_textField4);
	        }catch (Exception e){
	        	System.out.println(e.getMessage());
	        }
		textCpf.setBounds(309, 149, 131, 20);
		frame.getContentPane().add(textCpf);
		textCpf.setColumns(10);
		
		lblNome = new JLabel("Nome:");
		lblNome.setBounds(451, 152, 46, 14);
		frame.getContentPane().add(lblNome);

		textField = new JTextField();
		textField.setBounds(493, 149, 381, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textRenda = new JTextField();
		 try{
	           javax.swing.text.MaskFormatter format_textField4 = new javax.swing.text.MaskFormatter("#.###.###,##");
	        }catch (Exception e){
	        	System.out.println(e.getMessage());
	        }
		
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
}
