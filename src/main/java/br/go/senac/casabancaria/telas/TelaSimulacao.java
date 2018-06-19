package br.go.senac.casabancaria.telas;

import br.go.senac.casabancaria.entidades.Atendente;
import br.go.senac.casabancaria.entidades.Cliente;
import br.go.senac.casabancaria.entidades.Emprestimo;
import br.go.senac.casabancaria.entidades.Tabela;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;


import javax.swing.JPanel;
import javax.swing.JSeparator;


public class TelaSimulacao {

	private static JFrame frame;

	private static Cliente cl;
	private static Atendente at;
	private static JTextField textNome;
	private static JTextField textCpf;
	private static JTextField textRenda;
	private static JTextField textMargem;
	private static JComboBox<String> comboBoxTipo = new JComboBox<>();
	private static JComboBox<String> comboBoxPrazo = new JComboBox<>();
	private static Tabela tableSAC;
	private static Tabela tablePRICE;
	private static JButton btnContratarSac;
	private static JTextField textValor;
	private static JTextField textTaxa;
	private static JTextField textPrazo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaSimulacao window = new TelaSimulacao();
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

	public TelaSimulacao() {
		initialize();
	}

	public static void iniciaTela(Cliente c, Atendente a) {
		cl = c;
		at = a;
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

		JLabel lblWelcome = new JLabel("Insira as informa\u00E7\u00F5es de valor, taxa, per\u00EDodo e prazo:");
		lblWelcome.setBounds(20, 130, 444, 18);
		frame.getContentPane().add(lblWelcome);

		JSeparator separator = new JSeparator(JSeparator.VERTICAL);
		separator.setBounds(442, 230, 2, 315);
		frame.getContentPane().add(separator);

		JLabel lblCategoria = new JLabel("Categoria*");
		lblCategoria.setBounds(22, 40, 128, 14);
		frame.getContentPane().add(lblCategoria);

		comboBoxTipo.addItem("1 - Funcion�rio P�blico");
		comboBoxTipo.addItem("2 - Aposentado");
		comboBoxTipo.addItem("3 - Pensionista");
		comboBoxTipo.setBounds(22, 65, 160, 20);
		frame.getContentPane().add(comboBoxTipo);

		JLabel lblCpf = new JLabel("CPF*");
		lblCpf.setBounds(196, 40, 113, 14);
		frame.getContentPane().add(lblCpf);

		textCpf = new JTextField();
		try{
			javax.swing.text.MaskFormatter format_textField4 = new javax.swing.text.MaskFormatter("###.###.###-##");
			textCpf = new javax.swing.JFormattedTextField(format_textField4);
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		textCpf.setBounds(192, 65, 117, 20);
		frame.getContentPane().add(textCpf);
		textCpf.setColumns(10);

		JLabel lblNome = new JLabel("Nome*");
		lblNome.setBounds(319, 40, 128, 14);
		frame.getContentPane().add(lblNome);

		textNome = new JTextField();
		textNome.setBounds(320, 65, 329, 20);
		frame.getContentPane().add(textNome);
		textNome.setColumns(10);

		JLabel lblRenda = new JLabel("Renda L\u00EDquida*");
		lblRenda.setBounds(659, 40, 95, 14);
		frame.getContentPane().add(lblRenda);

		textRenda = new JTextField();
		textRenda.setBounds(659, 65, 95, 20);
		frame.getContentPane().add(textRenda);
		textRenda.setColumns(10);

		JLabel lblMargem = new JLabel("Margem Utilizada");
		lblMargem.setBounds(764, 40, 95, 14);
		frame.getContentPane().add(lblMargem);

		textMargem = new JTextField();
		textMargem.setBounds(764, 65, 95, 20);
		frame.getContentPane().add(textMargem);
		textMargem.setColumns(10);

		JButton btnVoltar = new JButton("Voltar ao Cadastro de Clientes");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVoltar.setBounds(654, 577, 220, 23);
		frame.getContentPane().add(btnVoltar);

		JButton btnContratarPrice = new JButton("Contratar PRICE");
		btnContratarPrice.setBounds(674, 514, 170, 23);
		frame.getContentPane().add(btnContratarPrice);

		JPanel panelCliente = new JPanel();
		panelCliente.setBorder(BorderFactory.createTitledBorder("CLIENTE")); 
		panelCliente.setBounds(10, 11, 864, 99);
		frame.getContentPane().add(panelCliente);

		JButton btnSair = new JButton("Sair do Sistema");
		btnSair.setBounds(10, 577, 165, 23);
		frame.getContentPane().add(btnSair);

		Emprestimo e = new Emprestimo();

		tableSAC = criarTabela(e, 380, 220);
		tableSAC.setEnabled(false);
		tableSAC.setBounds(20, 252, 400, 245);
		frame.getContentPane().add(tableSAC);


		tablePRICE = criarTabela(e, 380, 220);
		tablePRICE.setEnabled(false);
		tablePRICE.setBounds(460, 252, 400, 245);
		frame.getContentPane().add(tablePRICE);

		btnContratarSac = new JButton("Contratar SAC");
		btnContratarSac.setBounds(253, 514, 170, 23);
		frame.getContentPane().add(btnContratarSac);

		JLabel lblSistemaSac = new JLabel("Sistema SAC");
		lblSistemaSac.setBounds(22, 235, 230, 14);
		frame.getContentPane().add(lblSistemaSac);

		JLabel lblSistemaPrice = new JLabel("Sistema PRICE");
		lblSistemaPrice.setBounds(466, 235, 230, 14);
		frame.getContentPane().add(lblSistemaPrice);



		JPanel panelEmprest = new JPanel();
		panelEmprest.setBorder(BorderFactory.createTitledBorder("EMPRESTIMO")); 
		panelEmprest.setBounds(10, 214, 864, 338);
		frame.getContentPane().add(panelEmprest);

		JLabel lblValor = new JLabel("Valor*");
		lblValor.setBounds(20, 159, 46, 14);
		frame.getContentPane().add(lblValor);

		textValor = new JTextField();
		textValor.setBounds(20, 178, 86, 20);
		frame.getContentPane().add(textValor);
		textValor.setColumns(10);

		JLabel lblTaxa = new JLabel("Taxa*");
		lblTaxa.setBounds(121, 159, 46, 14);
		frame.getContentPane().add(lblTaxa);

		textTaxa = new JTextField();
		textTaxa.setBounds(116, 178, 60, 20);
		frame.getContentPane().add(textTaxa);
		textTaxa.setColumns(10);

		JLabel lblPrazo = new JLabel("Prazo*");
		lblPrazo.setBounds(285, 159, 46, 14);
		frame.getContentPane().add(lblPrazo);

		textPrazo = new JTextField();
		textPrazo.setBounds(282, 178, 60, 20);
		frame.getContentPane().add(textPrazo);
		textPrazo.setColumns(10);

		JLabel lblPeriodo = new JLabel("Per\u00EDodo*");
		lblPeriodo.setBounds(186, 159, 46, 14);
		frame.getContentPane().add(lblPeriodo);


		comboBoxPrazo.setBounds(186, 178, 86, 20);
		frame.getContentPane().add(comboBoxPrazo);

		JButton btnNewButton = new JButton("Simular");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				


			}
		});
		btnNewButton.setBounds(355, 177, 160, 23);
		frame.getContentPane().add(btnNewButton);

	}

	private static Tabela criarTabela (Emprestimo e, int width, int height){

		Tabela t = new Tabela();

		

		t = new Tabela (e, width, height);



		return t;

	}
}
