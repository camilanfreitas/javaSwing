package telas;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JComboBox;

import controles.CtrlCliente;
import entidades.Atendente;
import entidades.Cliente;
import javax.swing.SwingConstants;
import javax.swing.JPanel;


public class TelaCliente {

	private static JFrame frame;

	private static Atendente at;
	private static String cpfClienteAtual;
	private static JTextField textPesquisaCpf;
	private static JTextField textNome;
	private static JTextField textCpf;
	private static JTextField textEndereco;
	private static JTextField textCompl;
	private static JTextField textCep;
	private static JTextField textNum;
	private static JTextField textCidade;
	private static JTextField textBairro;
	private static JTextField textEmail;
	private static JTextField textRenda;
	private static JTextField textMargem;
	private static JComboBox<String> comboBoxEstado = new JComboBox<String>();
	private static JComboBox<String> comboBoxTipo = new JComboBox<String>();

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
		frame = new JFrame("OhBanks - Cadastro de Clientes");
		frame.setBounds(50, 50, 900, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblWelcome = new JLabel("Ol� "+at.getNome()+ ", pesquise um cliente ou cadastre um novo ");
		lblWelcome.setBounds(10, 29, 274, 18);
		frame.getContentPane().add(lblWelcome);


		textPesquisaCpf = new JTextField();
		textPesquisaCpf =DefinirTiposCaracteresETamanho(11,  "1234567890");
		try{
			javax.swing.text.MaskFormatter format_textField4 = new javax.swing.text.MaskFormatter("###.###.###-##");
			textPesquisaCpf = new javax.swing.JFormattedTextField(format_textField4);
		}catch (Exception e){
			System.out.println(e.getMessage());
		}		

		JButton btnCadastrarNovoCliente = new JButton("Cadastrar Cliente");
		btnCadastrarNovoCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCadastrarNovoCliente.setBounds(11, 146, 165, 23);
		frame.getContentPane().add(btnCadastrarNovoCliente);
		textPesquisaCpf.setBounds(11, 56, 165, 23);
		frame.getContentPane().add(textPesquisaCpf);
		textPesquisaCpf.setColumns(10);

		JButton btnPesquisar = new JButton("Pesquisar Cliente");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				limpaCampos();

				if(!textPesquisaCpf.getText().equals("")) {
					pesquisaCliente(textPesquisaCpf.getText());
				}else {
					JOptionPane.showMessageDialog(null, "Digite um CPF");
				}
			}
		});
		btnPesquisar.setBounds(11, 90, 165, 23);
		frame.getContentPane().add(btnPesquisar);

		JSeparator separator = new JSeparator(JSeparator.VERTICAL);
		separator.setBounds(186, 58, 2, 544);
		frame.getContentPane().add(separator);

		JLabel lblCategoria = new JLabel("Categoria*");
		lblCategoria.setBounds(216, 93, 128, 14);
		frame.getContentPane().add(lblCategoria);

		comboBoxTipo.addItem("1 - Funcion�rio P�blico");
		comboBoxTipo.addItem("2 - Aposentado");
		comboBoxTipo.addItem("3 - Pensionista");
		comboBoxTipo.setBounds(354, 90, 208, 20);
		frame.getContentPane().add(comboBoxTipo);

		JLabel lblCpf = new JLabel("CPF*");
		lblCpf.setBounds(216, 124, 126, 14);
		frame.getContentPane().add(lblCpf);

		textCpf = new JTextField();
		textCpf = DefinirTiposCaracteresETamanho(11,  "1234567890");
		try{
			javax.swing.text.MaskFormatter format_textField4 = new javax.swing.text.MaskFormatter("###.###.###-##");
			textCpf = new javax.swing.JFormattedTextField(format_textField4);
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		textCpf.setBounds(354, 121, 208, 20);
		frame.getContentPane().add(textCpf);
		textCpf.setColumns(10);

		JLabel lblNome = new JLabel("Nome*");
		lblNome.setBounds(216, 154, 128, 14);
		frame.getContentPane().add(lblNome);

		textNome = new JTextField();
		textNome.setBounds(354, 148, 507, 20);
		frame.getContentPane().add(textNome);
		textNome.setColumns(10);

		JLabel lblCep = new JLabel("CEP*");
		lblCep.setBounds(216, 182, 128, 14);
		frame.getContentPane().add(lblCep);

		textCep = new JTextField();
		textCep = DefinirTiposCaracteresETamanho(8,  "1234567890");
		try{
			javax.swing.text.MaskFormatter format_textField4 = new javax.swing.text.MaskFormatter("##.###-###");
			textCep = new javax.swing.JFormattedTextField(format_textField4);
		}catch (Exception e){
			System.out.println(e.getMessage());
		}		
		textCep.setBounds(354, 179, 79, 20);
		frame.getContentPane().add(textCep);
		textCep.setColumns(10);

		JLabel lblEndereco = new JLabel("Endere�o*");
		lblEndereco.setBounds(216, 213, 128, 14);
		frame.getContentPane().add(lblEndereco);

		textEndereco = new JTextField();
		textEndereco.setBounds(354, 210, 333, 20);
		frame.getContentPane().add(textEndereco);
		textEndereco.setColumns(10);

		JLabel lblNum = new JLabel("N�mero");
		lblNum.setBounds(216, 244, 125, 14);
		frame.getContentPane().add(lblNum);

		textNum = new JTextField();
		textNum.setBounds(354, 241, 79, 20);
		frame.getContentPane().add(textNum);
		textNum.setColumns(10);

		JLabel lblCompl = new JLabel("Complemento");
		lblCompl.setBounds(216, 275, 128, 14);
		frame.getContentPane().add(lblCompl);

		textCompl = new JTextField();
		textCompl.setBounds(354, 272, 333, 20);
		frame.getContentPane().add(textCompl);
		textCompl.setColumns(10);

		JLabel lblBairro = new JLabel("Bairro*");
		lblBairro.setBounds(216, 306, 128, 14);
		frame.getContentPane().add(lblBairro);

		textBairro = new JTextField();
		textBairro.setBounds(354, 303, 160, 20);
		frame.getContentPane().add(textBairro);
		textBairro.setColumns(10);

		JLabel lblEstado = new JLabel("Estado*");
		lblEstado.setBounds(216, 369, 128, 14);
		frame.getContentPane().add(lblEstado);

		comboBoxEstado.addItem("GO");
		comboBoxEstado.addItem("DF");
		comboBoxEstado.addItem("MG");
		comboBoxEstado.setBounds(354, 366, 105, 20);
		frame.getContentPane().add(comboBoxEstado);

		JLabel lblCidade = new JLabel("Cidade*");
		lblCidade.setBounds(216, 337, 128, 14);
		frame.getContentPane().add(lblCidade);

		textCidade = new JTextField();
		textCidade.setBounds(354, 334, 333, 20);
		frame.getContentPane().add(textCidade);
		textCidade.setColumns(10);

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(216, 400, 128, 14);
		frame.getContentPane().add(lblEmail);

		textEmail = new JTextField();
		//textEmail = DefinirTiposCaracteresETamanho(40,  "abcdefghijklmnopqrstuvwxyz@._-1234567890");
		textEmail.setBounds(354, 397, 334, 20);
		frame.getContentPane().add(textEmail);
		textEmail.setColumns(10);

		JLabel lblRenda = new JLabel("Renda L\u00EDquida*");
		lblRenda.setBounds(216, 431, 128, 14);
		frame.getContentPane().add(lblRenda);

		textRenda = new JTextField();
		textRenda.setBounds(354, 428, 160, 20);
		frame.getContentPane().add(textRenda);
		textRenda.setColumns(10);

		JLabel lblMargem = new JLabel("Margem Utilizada");
		lblMargem.setBounds(216, 462, 128, 14);
		frame.getContentPane().add(lblMargem);

		textMargem = new JTextField();
		textMargem.setBounds(354, 459, 160, 20);
		frame.getContentPane().add(textMargem);
		textMargem.setColumns(10);

		JLabel lblCamposObr = new JLabel("Aten��o! Os campos com asterisco (*) s�o obrigat�rios.");
		lblCamposObr.setBounds(216, 499, 354, 14);
		frame.getContentPane().add(lblCamposObr);
		
		JButton btnNovaSimulacao = new JButton("Simular Empr�stimo");
		btnNovaSimulacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNovaSimulacao.setBounds(709, 577, 165, 23);
		frame.getContentPane().add(btnNovaSimulacao);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(750, 524, 111, 23);
		frame.getContentPane().add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(628, 524, 111, 23);
		frame.getContentPane().add(btnCancelar);
		
		JButton btnApagar = new JButton("Apagar");
		btnApagar.setBounds(505, 524, 111, 23);
		frame.getContentPane().add(btnApagar);
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("CADASTRO DE CLIENTE")); 
		panel.setBounds(197, 56, 677, 503);
		frame.getContentPane().add(panel);
		
		JButton btnSair = new JButton("Sair do Sistema");
		btnSair.setBounds(10, 577, 165, 23);
		frame.getContentPane().add(btnSair);
		
		JButton btnConsultar = new JButton("Consultar Quita\u00E7\u00E3o");
		btnConsultar.setBounds(522, 577, 165, 23);
		frame.getContentPane().add(btnConsultar);

	}

	private static String pesquisaCliente (String cpf) {

		cpf = retiraMascara(cpf);

		Cliente c = CtrlCliente.preparaPesquisa(String.valueOf(cpf));
		if(c.getCpf()!=null) {
			preencheCampos(c);
			cpfClienteAtual=cpf;
			return c.getNome();
		}else {
			JOptionPane.showMessageDialog(null, "Cliente n�o encontrado.\nFa�a uma nova pesquisa ou cadastre um novo Cliente");
			return "";
		}
	}

	private static void preencheCampos (Cliente c) {

		textNome.setText(c.getNome());
		textCpf.setText(c.getCpf());
		textEndereco.setText(c.getLogradouro());
		textNum.setText(String.valueOf(c.getNumero()));
		textCompl.setText(c.getComplemento());
		textCep.setText(String.valueOf(c.getCep()));
		textCidade.setText(c.getCidade());
		textBairro.setText(c.getBairro());
		textEmail.setText(c.getEmail());
		textRenda.setText(String.valueOf(c.getRenda()));
		textMargem.setText(String.valueOf(c.getPrestacaoTerceiro()));
		if(c.getTipo()== 1) {
			comboBoxTipo.getEditor().setItem("1 - Funcion�rio P�blico");
		}else if(c.getTipo() == 2) {
			comboBoxTipo.getEditor().setItem("2 - Aposentado"); 
		}else {
			comboBoxTipo.getEditor().setItem("3 - Pensionista");
		}
		comboBoxEstado.getEditor().setItem(c.getEstado());
		
	}

	private static void cadastraDados(){


		Cliente cl = new Cliente();

		cl.setNome(textNome.getText());
		cl.setCpf(textCpf.getText());
		cl.setLogradouro(textEndereco.getText());
		cl.setNumero(Integer.parseInt(textNum.getText()));
		cl.setComplemento(textCompl.getText());
		cl.setCep(Integer.parseInt(textCep.getText()));
		cl.setCidade(textCidade.getText());
		cl.setBairro(textBairro.getText());
		cl.setEmail(textEmail.getText());
		cl.setRenda(Double.parseDouble(textRenda.getText()));
		cl.setPrestacaoTerceiro(Double.parseDouble(textMargem.getText()));
		
		
		if(comboBoxTipo.getSelectedItem().toString().equals("1 - Funcion�rio P�blico")) {
			cl.setTipo(1);
		}else if(comboBoxTipo.getSelectedItem().toString().equals("2 - Aposentado")) {
			cl.setTipo(2);
		}else {
			cl.setTipo(3);
		}

		boolean b = CtrlCliente.preparaCadastro(cl);
		
		if(b) {
			JOptionPane.showMessageDialog(null, "Cliente Cadastrado com sucesso!");
		}else {
			JOptionPane.showMessageDialog(null, "N�o foi poss�vel cadastrar o cliente, tente novamente.");
		}

	}
	
	private static void limpaCampos() {
		
		textNome.setText(null);
		textCpf.setText(null);
		textEndereco.setText(null);
		textNum.setText(null);
		textCompl.setText(null);
		textCep.setText(null);
		textCidade.setText(null);
		textBairro.setText(null);
		textEmail.setText(null);
		textRenda.setText(null);
		textMargem.setText(null);
		comboBoxTipo.getEditor().setItem(null);
		comboBoxEstado.getEditor().setItem(null);
		
	}
	
	private static String validaCampos() {
		
		String mensagem = "";
		
		if(textNome.getText()==null) {
			mensagem += "\nNome do Cliente";
		}
		if(textCpf.getText()==null) {
			mensagem += "\nN�mero de CPF";
		}
		if(textEndereco.getText()==null) {
			mensagem += "\nEndere�o";
		}
		textCpf.setText(null);
		textEndereco.setText(null);
		textNum.setText(null);
		textCompl.setText(null);
		textCep.setText(null);
		textCidade.setText(null);
		textBairro.setText(null);
		textEmail.setText(null);
		textRenda.setText(null);
		textMargem.setText(null);
		comboBoxTipo.getEditor().setItem(null);
		comboBoxEstado.getEditor().setItem(null);		
		
		return mensagem;
	}

	private static String retiraMascara(String texto) {

		texto=texto.replace(".", "");
		texto=texto.replace("-", "");		

		return texto;
	}

	//M�todo dispon�vel em: https://www.devmedia.com.br/java-swing-propriedades-do-jtextfield/21207
	public static JTextField DefinirTiposCaracteresETamanho(int tamanho,String caracteres)	{
		try
		{
			//defino a vari�vel que vai guardar a quantidade de caracteres
			String quantidade="";

			//defino um m�todo de repeti��o para repetir o numero de
			//vezes  do tamanho
			for(int i=0 ; i < tamanho; i++)
			{
				// defino asterisco para aceitar qualquer coisa e crio a m�scara
				quantidade=quantidade+"*";
			}        
			//  **********...   de acordo com o tamanho informado
			// defino que a mascara possui essa
			//quantidade de elementos que foi informado em tamanho e
			// foi colocada com * dentro de quantidade
			javax.swing.text.MaskFormatter nome=new javax.swing.text.MaskFormatter(quantidade);

			//defino que o par�metro caracter recebido pelo
			//m�todo cont�m os caracteres v�lidos 
			nome.setValidCharacters(caracteres);

			//retorno a mascara que foi criada  
			return new javax.swing.JFormattedTextField(nome);
		}// fim do try
		catch (Exception e)
		{      
			//mensagem se acontecer erro
			JOptionPane.showMessageDialog(null,"Ocorreu um erro");
			//retorno um campo de texto comum  
			return new JTextField();
		}//fim do catch
	}//fim do m�todo
}
