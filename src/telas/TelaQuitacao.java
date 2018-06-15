package telas;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import entidades.Cliente;
import entidades.Emprestimo;
import entidades.Tabela;

import javax.swing.JPanel;


public class TelaQuitacao {

	private static JFrame frame;
	
	private static Cliente cl;
	private static JTextField textNome;
	private static JTextField textCpf;
	private static JTextField textRenda;
	private static JTextField textMargem;
	private static JComboBox<String> comboBoxTipo = new JComboBox<>();
	private static JComboBox<String> comboBoxEmpr = new JComboBox<>();
	private static Tabela table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaQuitacao window = new TelaQuitacao();
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

	public TelaQuitacao() {
		Cliente a = new Cliente();
		a.setNome("Camila");
		cl=a;
		initialize();
	}

	public static void iniciaTela(Cliente a) {
		cl = a;
		main(null);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private static void initialize() {
		frame = new JFrame("OhBanks - Cadastro de Clientes");
		frame.setBounds(50, 50, 900, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblWelcome = new JLabel("Selecione o emprestimo para ver os detalhes");
		lblWelcome.setBounds(10, 29, 274, 18);
		frame.getContentPane().add(lblWelcome);


		JLabel lblCategoria = new JLabel("Categoria*");
		lblCategoria.setBounds(22, 83, 128, 14);
		frame.getContentPane().add(lblCategoria);

		comboBoxTipo.addItem("1 - Funcionário Público");
		comboBoxTipo.addItem("2 - Aposentado");
		comboBoxTipo.addItem("3 - Pensionista");
		comboBoxTipo.setBounds(22, 106, 208, 20);
		frame.getContentPane().add(comboBoxTipo);

		JLabel lblCpf = new JLabel("CPF*");
		lblCpf.setBounds(244, 83, 126, 14);
		frame.getContentPane().add(lblCpf);

		textCpf = new JTextField();
		try{
			javax.swing.text.MaskFormatter format_textField4 = new javax.swing.text.MaskFormatter("###.###.###-##");
			textCpf = new javax.swing.JFormattedTextField(format_textField4);
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		textCpf.setBounds(240, 106, 208, 20);
		frame.getContentPane().add(textCpf);
		textCpf.setColumns(10);

		JLabel lblNome = new JLabel("Nome*");
		lblNome.setBounds(457, 83, 128, 14);
		frame.getContentPane().add(lblNome);

		textNome = new JTextField();
		textNome.setBounds(458, 106, 403, 20);
		frame.getContentPane().add(textNome);
		textNome.setColumns(10);

		JLabel lblRenda = new JLabel("Renda L\u00EDquida*");
		lblRenda.setBounds(22, 137, 128, 14);
		frame.getContentPane().add(lblRenda);

		textRenda = new JTextField();
		textRenda.setBounds(22, 162, 160, 20);
		frame.getContentPane().add(textRenda);
		textRenda.setColumns(10);

		JLabel lblMargem = new JLabel("Margem Utilizada");
		lblMargem.setBounds(192, 137, 128, 14);
		frame.getContentPane().add(lblMargem);

		textMargem = new JTextField();
		textMargem.setBounds(192, 162, 160, 20);
		frame.getContentPane().add(textMargem);
		textMargem.setColumns(10);

		JButton btnVoltar = new JButton("Voltar ao Cadastro de Clientes");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVoltar.setBounds(654, 577, 220, 23);
		frame.getContentPane().add(btnVoltar);

		JButton btnCancelar = new JButton("Cancelar Emprestimo");
		btnCancelar.setBounds(674, 514, 170, 23);
		frame.getContentPane().add(btnCancelar);

		comboBoxEmpr.setBounds(362, 162, 499, 20);
		frame.getContentPane().add(comboBoxEmpr);

		JLabel lblEmprestimos = new JLabel("Emprestimos");
		lblEmprestimos.setBounds(364, 137, 128, 14);
		frame.getContentPane().add(lblEmprestimos);

		JPanel panelCliente = new JPanel();
		panelCliente.setBorder(BorderFactory.createTitledBorder("CLIENTE")); 
		panelCliente.setBounds(10, 57, 864, 146);
		frame.getContentPane().add(panelCliente);

		JButton btnSair = new JButton("Sair do Sistema");
		btnSair.setBounds(10, 577, 165, 23);
		frame.getContentPane().add(btnSair);
		
		Emprestimo e = new Emprestimo();
		
		table = criarTabela(e, 790, 220);
		table.setEnabled(false);
		table.setBounds(36, 252, 811, 250);
		frame.getContentPane().add(table);
		
		
		
		/*JPanel panelEmprest = new JPanel();
		panelEmprest.setBorder(BorderFactory.createTitledBorder("EMPRESTIMO")); 
		panelEmprest.setBounds(10, 214, 864, 338);
		frame.getContentPane().add(panelEmprest);*/

	}
	
	private static Tabela criarTabela (Emprestimo e, int width, int height){
		
		Tabela t = new Tabela();
		
		Object[][] data = {
			    {"Kathy", "Smith",
			     "Snowboarding", new Integer(5), new Boolean(false)},
			    {"John", "Doe",
			     "Rowing", new Integer(3), new Boolean(true)},
			    {"Sue", "Black",
			     "Knitting", new Integer(2), new Boolean(false)},
			    {"Jane", "White",
			     "Speed reading", new Integer(20), new Boolean(true)},
			    {"Joe", "Brown",
			     "Pool", new Integer(10), new Boolean(false)}
			};
		
		t = new Tabela (data, width, height);
		
		
		
		return t;
		
	}
}
