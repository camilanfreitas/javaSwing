package telas;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JComboBox;

import controles.CtrlCliente;
import entidades.Atendente;
import entidades.Cliente;


public class TelaCliente {

	private static JFrame frame;


	private static Atendente at;
	private static String cpfClienteAtual;
	private static JTextField textPesquisaCpf;
	private static JTextField textNome;
	private static JTextField textCpf;
	private static JTextField textEndereco;
	private static JTextField textNum;
	private static JTextField textCompl;
	private static JTextField textCep;

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

		JLabel lblWelcome = new JLabel("Ol\u00E1 "+at.getNome()+ ", o que deseja fazer?");
		lblWelcome.setBounds(10, 92, 302, 14);
		frame.getContentPane().add(lblWelcome);
		
		
		textPesquisaCpf = new JTextField();
		textPesquisaCpf =DefinirTiposCaracteresETamanho(11,  "1234567890");
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

		JButton btnCadastrarNovoCliente = new JButton("Cadastrar Novo Cliente");
		btnCadastrarNovoCliente.setBounds(335, 88, 189, 23);
		frame.getContentPane().add(btnCadastrarNovoCliente);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 122, 864, 2);
		frame.getContentPane().add(separator);

		JLabel lblCategoria = new JLabel("Categoria*");
		lblCategoria.setBounds(10, 149, 70, 14);
		frame.getContentPane().add(lblCategoria);

		JComboBox<String> comboBoxTipo = new JComboBox<String>();
		comboBoxTipo.addItem("1 - Funcionário Público");
		comboBoxTipo.addItem("2 - Aposentado");
		comboBoxTipo.addItem("3 - Pensionista");
		comboBoxTipo.setBounds(73, 146, 189, 20);
		frame.getContentPane().add(comboBoxTipo);

		JLabel lblCpf = new JLabel("CPF*");
		lblCpf.setBounds(271, 152, 28, 14);
		frame.getContentPane().add(lblCpf);

		textCpf = new JTextField();
		textCpf = DefinirTiposCaracteresETamanho(11,  "1234567890");
		 try{
	           javax.swing.text.MaskFormatter format_textField4 = new javax.swing.text.MaskFormatter("###.###.###-##");
	           textCpf = new javax.swing.JFormattedTextField(format_textField4);
	        }catch (Exception e){
	        	System.out.println(e.getMessage());
	        }
		textCpf.setBounds(309, 149, 131, 20);
		frame.getContentPane().add(textCpf);
		textCpf.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome*");
		lblNome.setBounds(10, 194, 46, 14);
		frame.getContentPane().add(lblNome);

		textNome = new JTextField();
		textNome.setBounds(52, 191, 388, 20);
		frame.getContentPane().add(textNome);
		textNome.setColumns(10);
		
		JLabel lblEndereco = new JLabel("Endere\u00E7o*");
		lblEndereco.setBounds(10, 233, 70, 14);
		frame.getContentPane().add(lblEndereco);
		
		textEndereco = new JTextField();
		textEndereco.setBounds(83, 230, 357, 20);
		frame.getContentPane().add(textEndereco);
		textEndereco.setColumns(10);
		
		JLabel lblNum = new JLabel("N\u00BA");
		lblNum.setBounds(10, 271, 28, 14);
		frame.getContentPane().add(lblNum);
		
		textNum = new JTextField();
		textNum  = DefinirTiposCaracteresETamanho(6,  "1234567890");
		textNum.setBounds(34, 268, 57, 20);
		frame.getContentPane().add(textNum);
		textNum.setColumns(10);
		
		JLabel lblCompl = new JLabel("Complemento");
		lblCompl.setBounds(106, 271, 95, 14);
		frame.getContentPane().add(lblCompl);
		
		textCompl = new JTextField();
		textCompl.setBounds(190, 268, 250, 20);
		frame.getContentPane().add(textCompl);
		textCompl.setColumns(10);
		
		JLabel lblCep = new JLabel("CEP");
		lblCep.setBounds(10, 310, 46, 14);
		frame.getContentPane().add(lblCep);
		
		textCep = new JTextField();
		textCep = DefinirTiposCaracteresETamanho(8,  "1234567890");
		 try{
	           javax.swing.text.MaskFormatter format_textField4 = new javax.swing.text.MaskFormatter("##.###-###");
	           textCep = new javax.swing.JFormattedTextField(format_textField4);
	        }catch (Exception e){
	        	System.out.println(e.getMessage());
	        }
		textCep.setBounds(53, 307, 86, 20);
		frame.getContentPane().add(textCep);
		textCep.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(149, 310, 46, 14);
		frame.getContentPane().add(lblEstado);
		
		JComboBox<String> comboBoxEstado = new JComboBox<String>();
		comboBoxEstado.addItem("GO");
		comboBoxEstado.addItem("AC");
		comboBoxEstado.addItem("AL");
		comboBoxEstado.addItem("AP");
		comboBoxEstado.addItem("AM");
		comboBoxEstado.addItem("BA");
		comboBoxEstado.addItem("CE");
		comboBoxEstado.addItem("DF");
		comboBoxEstado.addItem("ES");
		comboBoxEstado.addItem("MA");
		comboBoxEstado.addItem("MT");
		comboBoxEstado.addItem("MS");
		comboBoxEstado.addItem("MG");
		comboBoxEstado.addItem("PA");
		comboBoxEstado.addItem("PB");
		comboBoxEstado.addItem("PR");
		comboBoxEstado.addItem("PE");
		comboBoxEstado.addItem("PI");
		comboBoxEstado.addItem("RJ");
		comboBoxEstado.addItem("RN");
		comboBoxEstado.addItem("RS");
		comboBoxEstado.addItem("RO");
		comboBoxEstado.addItem("RR");
		comboBoxEstado.addItem("SC");
		comboBoxEstado.addItem("SP");
		comboBoxEstado.addItem("SE");
		comboBoxEstado.addItem("TO");
		comboBoxEstado.setBounds(200, 307, 46, 20);
		frame.getContentPane().add(comboBoxEstado);
		
	}

	private static String pesquisaCliente (String cpf) {
		
		cpf = retiraMascara(cpf);

		Cliente c = CtrlCliente.preparaPesquisa(String.valueOf(cpf));
		if(c.getCpf()!=null) {
			preencheCampos(c);
			cpfClienteAtual=cpf;
			return c.getNome();
		}else {
			JOptionPane.showMessageDialog(null, "Cliente não encontrado.\nFaça uma nova pesquisa ou cadastre um novo Cliente");
			return "";
		}
	}
	
	private static void preencheCampos (Cliente c) {
		
		textNome.setText(c.getNome());
		textCpf.setText(c.getCpf());
		textEndereco.setText(c.getLogradouro());
		textNum.setText(c.getNumeroText());
		textCompl.setText(c.getComplemento());
		textCep.setText(c.getCepText());
		
	}
	
	private static String retiraMascara(String texto) {
		
		texto=texto.replace(".", "");
		texto=texto.replace("-", "");		
		
		return texto;
	}
	
	//Método disponível em: https://www.devmedia.com.br/java-swing-propriedades-do-jtextfield/21207
	public static JTextField DefinirTiposCaracteresETamanho(int tamanho,String caracteres)	{
	    try
	    {
	        //defino a variável que vai guardar a quantidade de caracteres
	        String quantidade="";
	 
	        //defino um método de repetição para repetir o numero de
	        //vezes  do tamanho
	        for(int i=0 ; i < tamanho; i++)
	        {
	            // defino asterisco para aceitar qualquer coisa e crio a máscara
	            quantidade=quantidade+"*";
	        }        
	        //  **********...   de acordo com o tamanho informado
	        // defino que a mascara possui essa
	        //quantidade de elementos que foi informado em tamanho e
	        // foi colocada com * dentro de quantidade
	        javax.swing.text.MaskFormatter nome=new javax.swing.text.MaskFormatter(quantidade);
	 
	        //defino que o parâmetro caracter recebido pelo
	        //método contém os caracteres válidos 
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
	}//fim do método
	
	
}
