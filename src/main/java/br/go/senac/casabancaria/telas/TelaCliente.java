package br.go.senac.casabancaria.telas;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Vector;

import javax.annotation.PostConstruct;
import javax.swing.*;

import br.go.senac.casabancaria.controles.CtrlCliente;
import br.go.senac.casabancaria.entidades.Atendente;
import br.go.senac.casabancaria.entidades.Cliente;
import br.go.senac.casabancaria.swing.*;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.swing.border.EmptyBorder;

@Log4j
@Component
public class TelaCliente extends JFrame {
	
	private JPanel       contentPane;
	private JLabel       lblWelcome;
	private Atendente    atendente;
	private JTextField   textNome;
	private CampoFormato textCpf;
	private JTextField   textEndereco;
	private CampoFormato textTelefone;
	private JTextField   textCompl;
	private CampoFormato textCep;
	private CampoNumero  textNum;
	private JTextField   textCidade;
	private JTextField   textBairro;
	private JTextField   textEmail;
	private CampoMoeda   textRenda;
	private CampoMoeda   textMargem;
	private JComboBox<String> comboBoxEstado = new JComboBox<String>();
	private JComboBox<String> comboBoxTipo;
	private JButton btnNovaSimulacao;
	private JButton btnApagar;
	private Cliente form;
	
	@Autowired
	private CtrlCliente controle;
	
	public TelaCliente( ) {
		initialize();
	}

	@PostConstruct
	private void carregaListas(){
		List<String> tipos =  controle.listarTipos();
		comboBoxTipo.setModel(new DefaultComboBoxModel<>(new Vector<>(tipos)));
		((DefaultComboBoxModel)comboBoxTipo.getModel()).removeElementAt(0);
		comboBoxTipo.setSelectedIndex(-1);
		List<String> ufs = controle.listarUfs();
		comboBoxEstado.setModel(new DefaultComboBoxModel<>(new Vector<>(ufs)));
		((DefaultComboBoxModel)comboBoxEstado.getModel()).removeElementAt(0);
		comboBoxEstado.setSelectedIndex(-1);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			setTitle("OhBanks - Cadastro de Clientes");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(50, 50, 690, 455);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(null);
			setContentPane(contentPane);
			
			lblWelcome = new JLabel();
			lblWelcome.setBounds(20, 15, 800, 18);
			lblWelcome.setFont(new Font("Helvetica",Font.PLAIN, 14));
			lblWelcome.setForeground(new Color(85, 85, 239));
			contentPane.add(lblWelcome);
			
			JPanel pnGeral = new JPanel();
			pnGeral.setLayout(null);
			pnGeral.setBorder(BorderFactory.createTitledBorder("CADASTRO DE CLIENTE"));
			pnGeral.setBounds(15, 40, 657, 350);
			contentPane.add(pnGeral);
			
			JLabel lblCpf = new JLabel("CPF*");
			lblCpf.setBounds(15, 15, 112, 20);
			pnGeral.add(lblCpf);
			
			textCpf = new CampoFormato("###.###.###-##");
			textCpf.setBounds(15, 35, 112, 20);
			pnGeral.add(textCpf);
			
			JLabel lblNome = new JLabel("Nome*");
			lblNome.setBounds(132, 15, 507, 20);
			pnGeral.add(lblNome);
			
			textNome = new CampoTexto(80);
			textNome.setBounds(132, 35, 507, 20);
			pnGeral.add(textNome);
			
			JLabel lblCategoria = new JLabel("Categoria*");
			lblCategoria.setBounds(15, 60, 413, 20);
			pnGeral.add(lblCategoria);
			
			comboBoxTipo = new CampoLista();
			comboBoxTipo.setBounds(15, 80, 413, 20);
			pnGeral.add(comboBoxTipo);
			
			JLabel lblTelefone = new JLabel("Telefone");
			lblTelefone.setBounds(435, 60, 110, 20);
			pnGeral.add(lblTelefone);
			
			textTelefone = new CampoFormato("(##)####-####");
			textTelefone.setBounds(435, 80, 110, 20);
			pnGeral.add(textTelefone);
			
			JLabel lblCep = new JLabel("CEP*");
			lblCep.setBounds(550, 60, 88, 20);
			pnGeral.add(lblCep);
			
			textCep = new CampoFormato("##.###-###");
			textCep.setBounds(550, 80, 88, 20);
			pnGeral.add(textCep);
			
			JLabel lblEndereco = new JLabel("Endereço*");
			lblEndereco.setBounds(15, 105, 530, 20);
			pnGeral.add(lblEndereco);
			
			textEndereco = new CampoTexto(80);
			textEndereco.setBounds(15, 125, 530, 20);
			pnGeral.add(textEndereco);
			
			JLabel lblNum = new JLabel("Número");
			lblNum.setBounds(550, 105, 88, 20);
			pnGeral.add(lblNum);
			
			textNum = new CampoNumero(5);
			textNum.setBounds(550, 125, 88, 20);
			pnGeral.add(textNum);
			
			JLabel lblBairro = new JLabel("Bairro*");
			lblBairro.setBounds(15, 150, 280, 20);
			pnGeral.add(lblBairro);
			
			textBairro = new CampoTexto(30);
			textBairro.setBounds(15, 170, 280, 20);
			pnGeral.add(textBairro);
			
			JLabel lblCidade = new JLabel("Cidade*");
			lblCidade.setBounds(300, 150, 280, 20);
			pnGeral.add(lblCidade);
			
			textCidade = new CampoTexto(50);
			textCidade.setBounds(300, 170, 280, 20);
			pnGeral.add(textCidade);
			
			JLabel lblEstado = new JLabel("UF*");
			lblEstado.setBounds(588, 148, 50, 20);
			pnGeral.add(lblEstado);
			
			comboBoxEstado.setBounds(588, 168, 50, 20);
			pnGeral.add(comboBoxEstado);
			
			JLabel lblCompl = new JLabel("Complemento");
			lblCompl.setBounds(15, 193, 307, 20);
			pnGeral.add(lblCompl);
			
			textCompl = new CampoTexto(50);
			textCompl.setBounds(15, 213, 307, 20);
			pnGeral.add(textCompl);
			
			JLabel lblEmail = new JLabel("E-mail");
			lblEmail.setBounds(327, 193, 312, 20);
			pnGeral.add(lblEmail);
			
			textEmail = new CampoTexto(80);
			textEmail.setBounds(327, 213, 312, 20);
			pnGeral.add(textEmail);
			
			JLabel lblRenda = new JLabel("Renda Líquida*");
			lblRenda.setBounds(15, 238, 100, 20);
			pnGeral.add(lblRenda);
			
			textRenda = new CampoMoeda(8,2);
			textRenda.setBounds(15, 258, 100, 20);
			pnGeral.add(textRenda);
			
			JLabel lblMargem = new JLabel("Margem Utilizada");
			lblMargem.setBounds(120, 238, 110, 14);
			pnGeral.add(lblMargem);

			textMargem = new CampoMoeda(8,2);
			textMargem.setBounds(120, 258, 110, 20);
			pnGeral.add(textMargem);
			
			JLabel lblCamposObr = new JLabel("Atenção! Os campos com asterisco (*) são obrigatórios.");
			lblCamposObr.setForeground(Color.red);
			lblCamposObr.setBounds(15, 283, 600, 20);
			pnGeral.add(lblCamposObr);
			
			JButton btnNovo = new JButton("Novo");
			btnNovo.setBounds(15, 310, 111, 23);
			btnNovo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//				int resposta = JOptionPane.showConfirmDialog(null, "Ao cancelar, as altera��es no cadastro de Cliente n�o ser�o"
					//						+ " armazenadas e os campos da tela ser�o limpos.\nDeseja continuar?");
					//				if(resposta==0) {
					//					limpaCampos();
					//				}
					limpaCampos();
					textCpf.requestFocus();
				}
			});
			pnGeral.add(btnNovo);
			
			JButton btnSalvar = new JButton("Salvar");
			btnSalvar.setBounds(136, 310, 111, 23);
			btnSalvar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					salvar();
				}
			});
			pnGeral.add(btnSalvar);
			
			btnApagar = new JButton("Apagar");
			btnApagar.setVisible(false);
			btnApagar.setBounds(257, 310, 111, 23);
			btnApagar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
//				if(cpfClienteAtual==null){
//					JOptionPane.showMessageDialog(null, "N�o h� registro de atual.\nPesquise o cliente que deseja excluir.");
//				}else {
//					int resposta = JOptionPane.showConfirmDialog(null, "Voc� est� prestes a apagar o registro de um cliente, essa a��o � irrevers�vel."
//							+ "\nDeseja continuar?");
//
//					if(resposta==0) {
//						apagarCliente();
//					}
//				}
				}
			});
			pnGeral.add(btnApagar);
			
			btnNovaSimulacao = new JButton("Simular Empréstimo");
			btnNovaSimulacao.setVisible(false);
			btnNovaSimulacao.setBounds(373, 310, 165, 23);
			btnNovaSimulacao.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//falta completar metodo validador de dados
					if (!textNome.getText().equals("")) {
						Cliente cl = new Cliente();
						cl.setNome(textNome.getText());
						cl.setCpf(textCpf.getText());
						cl.setTelefone(textTelefone.getText());
						cl.setLogradouro(textEndereco.getText());
//						cl.setNumero(Integer.parseInt(textNum.getText()));
						cl.setComplemento(textCompl.getText());
//						cl.setCep(Integer.parseInt(textCep.getText()));
						cl.setCidade(textCidade.getText());
						cl.setBairro(textBairro.getText());
						cl.setEmail(textEmail.getText());
						cl.setRenda(Double.parseDouble(textRenda.getText()));
						cl.setPrestacaoTerceiro(Double.parseDouble(textMargem.getText()));
						cl.setEstado(comboBoxEstado.getSelectedItem().toString());
						cl.setAtendente(atendente.getLogin());
						
						CtrlCliente.criarSimulacao(cl, atendente);
						//					frame.dispose();
					} else {
						JOptionPane.showMessageDialog(null, "� necess�rio um cadastro de cliente para realizar uma simula��o."
								                                    + "\nPesquise um cliente ou cadastre um antes de continuar.");
					}
				}
			});
			pnGeral.add(btnNovaSimulacao);
			
			JButton btnSair = new JButton("Sair do Sistema");
			btnSair.setBounds(18, 400, 165, 23);
			contentPane.add(btnSair);
			

//			textPesquisaCpf = new CampoFormato("###.###.###-##");
//			textPesquisaCpf.setBounds(11, 56, 165, 23);
//			contentPane.add(textPesquisaCpf);
			
//			JButton btnPesquisar = new JButton("Pesquisar Cliente");
//			btnPesquisar.setBounds(11, 90, 165, 23);
//			btnPesquisar.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent event) {
//					//				if(!textPesquisaCpf.getText().equals("")) {
//					//					pesquisaCliente(textPesquisaCpf.getText());
//					//				}else {
//					//					JOptionPane.showMessageDialog(null, "Digite um CPF");
//					//					limpaCampos();
//					//				}
//				}
//			});
//			contentPane.add(btnPesquisar);
			
//			JButton btnCadastrarNovoCliente = new JButton("Cadastrar Cliente");
//			btnCadastrarNovoCliente.setBounds(4, 140, 165, 23);
//			btnCadastrarNovoCliente.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent arg0) {
////					limpaCampos();
////					cpfClienteAtual=null;
////					JOptionPane.showMessageDialog(null, "Preencha os campos da p�gina com as informa��es do cliente."
////							+ "\nAten��o aos campos obrigat�rios.");
//				}
//			});
//			contentPane.add(btnCadastrarNovoCliente);
			
//			JSeparator separator = new JSeparator(JSeparator.VERTICAL);
//			separator.setBounds(186, 58, 2, 544);
//			contentPane.add(separator);

		}catch (Exception e){
			log.error(e.getMessage(),e);
		}
	}
	

	/*private  String validaCampos() {

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
	}*/
	
	public void setAtendente(Atendente atendente){
		this.atendente = atendente;
		lblWelcome.setText("Olá " + atendente.getNome() + ", digite o cpf para pesquisar um cliente, ou informe um novo para cadastro.");
	}

	private  void apagarCliente() {		

//		if(CtrlCliente.preparaDelete(cpfClienteAtual)) {
//			JOptionPane.showMessageDialog(null, "Cadastro de cliente apagado!");
//			limpaCampos();
//		}else {
//			JOptionPane.showMessageDialog(null, "N�o foi poss�vel apagar o cadatro de cliente, tente novamente.");
//		}
	}

	private  void pesquisaCliente (String cpf) {

//		Cliente c = CtrlCliente.preparaPesquisa(cpf);
//		if(c!=null) {
//		//	cpfClienteAtual=c.getCpf();
//			preencheCampos(c);
//		}else {
//			JOptionPane.showMessageDialog(null, "Cliente n�o encontrado.\nFa�a uma nova pesquisa ou cadastre um novo Cliente");
//			limpaCampos();
//		}
	}

	private  void preencheCampos (Cliente c) {

		textNome.setText(c.getNome());
		textCpf.setText(c.getCpf());
		textEndereco.setText(c.getLogradouro());
		textTelefone.setText(c.getTelefone());
		textNum.setText(String.valueOf(c.getNumero()));
		textCompl.setText(c.getComplemento());
		textCep.setText(String.valueOf(c.getCep()));
		textCidade.setText(c.getCidade());
		textBairro.setText(c.getBairro());
		textEmail.setText(c.getEmail());
		textRenda.setText(String.valueOf(c.getRenda()));
		textMargem.setText(String.valueOf(c.getPrestacaoTerceiro()));
		if(c.getTipo() == 1) {
			comboBoxTipo.setSelectedItem("1 - Funcion�rio P�blico");
		}else if(c.getTipo() == 2) {
			comboBoxTipo.setSelectedItem("2 - Aposentado"); 
		}else {
			comboBoxTipo.setSelectedItem("3 - Pensionista");
		}
		comboBoxEstado.setSelectedItem(c.getEstado());

	}

	private void salvar(){
		if(StringUtils.isEmpty(textCpf.getRealValue(".","-"))){
			JOptionPane.showMessageDialog(this, "CPF não informado!","Atenção", JOptionPane.WARNING_MESSAGE);
			textCpf.requestFocus();
			return;
		}
		if(StringUtils.isEmpty(textNome.getText())){
			JOptionPane.showMessageDialog(this, "Nome não informado!","Atenção", JOptionPane.WARNING_MESSAGE);
			textNome.requestFocus();
			return;
		}
		if(comboBoxTipo.getSelectedIndex() == -1){
			JOptionPane.showMessageDialog(this, "Categoria não informada!","Atenção", JOptionPane.WARNING_MESSAGE);
			comboBoxTipo.requestFocus();
			return;
		}
		if(StringUtils.isEmpty(textCep.getRealValue(".","-"))){
			JOptionPane.showMessageDialog(this, "Cep não informado!","Atenção", JOptionPane.WARNING_MESSAGE);
			textCep.requestFocus();
			return;
		}
		if(StringUtils.isEmpty(textEndereco.getText())){
			JOptionPane.showMessageDialog(this, "Endereço não informado!","Atenção", JOptionPane.WARNING_MESSAGE);
			textEndereco.requestFocus();
			return;
		}
		if(StringUtils.isEmpty(textBairro.getText())){
			JOptionPane.showMessageDialog(this, "Bairro não informado!","Atenção", JOptionPane.WARNING_MESSAGE);
			textBairro.requestFocus();
			return;
		}
		if(StringUtils.isEmpty(textCidade.getText())){
			JOptionPane.showMessageDialog(this, "Cidade não informada!","Atenção", JOptionPane.WARNING_MESSAGE);
			textCidade.requestFocus();
			return;
		}
		if(comboBoxEstado.getSelectedIndex() == -1){
			JOptionPane.showMessageDialog(this, "Uf não informada!","Atenção", JOptionPane.WARNING_MESSAGE);
			comboBoxEstado.requestFocus();
			return;
		}
		if(textRenda.getText().trim().length() > 2){
			JOptionPane.showMessageDialog(this, "Renda Líquida não informada!","Atenção", JOptionPane.WARNING_MESSAGE);
			textRenda.requestFocus();
			return;
		}
		
		try{
			form = Cliente
					       .builder()
					       .cpf(textCpf.getRealValue(".","-"))
						   .nome(textNome.getText())
						   .tipo(comboBoxTipo.getSelectedIndex())
					       .telefone(textTelefone.getRealValue("(",")","-"))
					       .cep(textCep.getRealValue(".","-"))
					       .logradouro(textEndereco.getText())
						   .numero(textNum.getText())
					       .bairro(textBairro.getText())
					       .cidade(textCidade.getText())
						   .estado(comboBoxEstado.getSelectedItem().toString())
					       .complemento(textCompl.getText())
					       .email(textEmail.getText())
					       .renda(textRenda.getDoubleValue())
						   .prestacaoTerceiro(textMargem.getDoubleValue())
					       .build();
			controle.salvar(form);
		}catch (Exception e){
			JOptionPane.showMessageDialog(this, "Ocorreu um problema ao salvar contate o desenvolvimento","Erro", JOptionPane.ERROR_MESSAGE);
			log.error(e.getMessage(),e);
		}
		
//		cl.setNome(textNome.getText());
//		cl.setCpf(textCpf.getText());
//		cl.setTelefone(textTelefone.getText());
//		cl.setLogradouro(textEndereco.getText());
//		cl.setNumero(Integer.parseInt(textNum.getText()));
//		cl.setComplemento(textCompl.getText());
//		cl.setCep(Integer.parseInt(textCep.getText()));
//		cl.setCidade(textCidade.getText());
//		cl.setBairro(textBairro.getText());
//		cl.setEmail(textEmail.getText());
//		cl.setRenda(Double.parseDouble(textRenda.getText()));
//		cl.setPrestacaoTerceiro(Double.parseDouble(textMargem.getText()));
//		cl.setEstado(comboBoxEstado.getSelectedItem().toString());


//		if(comboBoxTipo.getSelectedItem().toString().equals("1 - Funcion�rio P�blico")) {
//			cl.setTipo(1);
//		}else if(comboBoxTipo.getSelectedItem().toString().equals("2 - Aposentado")) {
//			cl.setTipo(2);
//		}else {
//			cl.setTipo(3);
//		}
//
//
//		if(cpfClienteAtual==null) {
//			boolean b = CtrlCliente.preparaCadastro(cl);
//
//			if(b) {
//				JOptionPane.showMessageDialog(null, "Cliente Cadastrado com sucesso!");
//			}else {
//				JOptionPane.showMessageDialog(null, "N�o foi poss�vel cadastrar o cliente, tente novamente.");
//			}
//
//		}else {
//
//			boolean b = CtrlCliente.preparaAlteracao(cl, cpfClienteAtual);
//
//			if(b) {
//				JOptionPane.showMessageDialog(null, "Cliente Alterado com sucesso!");
//			}else {
//				JOptionPane.showMessageDialog(null, "N�o foi poss�vel cadastrar o cliente, tente novamente.");
//			}
//		}

	}

	private  void limpaCampos() {
		textNome.setText("");
		textCpf.setText("");
		textTelefone.setText("");
		textEndereco.setText("");
		textNum.setText("");
		textCompl.setText("");
		textCep.setText("");
		textCidade.setText("");
		textBairro.setText("");
		textEmail.setText("");
		textRenda.setText("");
		textMargem.setText("");
		comboBoxTipo.setSelectedIndex(-1);
		comboBoxEstado.setSelectedIndex(-1);
		btnApagar.setVisible(false);
		btnNovaSimulacao.setVisible(false);
	}


	/*private  String retiraMascara(String texto) {

		texto=texto.replace(".", "");
		texto=texto.replace("-", "");
		texto=texto.replace("(", "");
		texto=texto.replace(")", "");

		return texto;
	}

	//M�todo dispon�vel em: https://www.devmedia.com.br/java-swing-propriedades-do-jtextfield/21207
	public  JTextField DefinirTiposCaracteresETamanho(int tamanho,String caracteres)	{
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
	}//fim do m�todo*/
}
