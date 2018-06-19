package br.go.senac.casabancaria.telas;

import br.go.senac.casabancaria.controles.CtrlLogin;
import br.go.senac.casabancaria.entidades.Atendente;
import br.go.senac.casabancaria.exception.NegocioException;
import br.go.senac.casabancaria.swing.CampoSenha;
import br.go.senac.casabancaria.swing.CampoTexto;
import br.go.senac.casabancaria.util.ImagemUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Log4j
@Component
public class TelaLogin extends JFrame {
	
	private JPanel contentPane;
	private static JTextField textUsuario;
	private static JPasswordField textSenha;
	
	@Autowired
	private TelaCliente telaCliente;
	
	@Autowired
	private CtrlLogin controle;
	
	/**
	 * Construtor padrão.
	 */
	public TelaLogin() {
		initialize();
	}

	/**
	 * Constroi a tela
	 */
	private void initialize() {
		try {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(600,300);
			//setLocation(Toolkit.getDefaultToolkit().getScreenSize().width - 600, Toolkit.getDefaultToolkit().getScreenSize().height - 300);
			setLocation(300,300);
			setUndecorated(true);
			
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			//contentPane.setBackground(new Color(123, 150, 249));
			contentPane.setLayout(null);
			setContentPane(contentPane);
			
			JLabel lbClose = new JLabel("X");
			lbClose.setBounds(585,2,13,16);
			lbClose.setToolTipText("Clique para fechar o sistema");
			lbClose.setHorizontalAlignment(SwingConstants.CENTER);
			lbClose.setVerticalAlignment(SwingConstants.CENTER);
			lbClose.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			lbClose.setFont(new Font("Arial", Font.BOLD, 12));
			lbClose.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					System.exit(0);
				}
			});
			contentPane.add(lbClose);
			
			JLabel lbTitulo = new JLabel("OhBanks");
			lbTitulo.setBounds(250,25,270,65);
			lbTitulo.setFont(new Font("Helvetica", Font.BOLD, 60));
			lbTitulo.setForeground(new Color(25, 0, 200));
			contentPane.add(lbTitulo);
			
			JLabel lbSombra = new JLabel("OhBanks");
			lbSombra.setBounds(252,27,270,65);
			lbSombra.setFont(lbTitulo.getFont());
			lbSombra.setForeground(new Color(200, 205, 202));
			contentPane.add(lbSombra);
			
			JLabel lblUsuario = new JLabel("Usuário");
			lblUsuario.setBounds(350, 95, 46, 14);
			contentPane.add(lblUsuario);
			
			textUsuario = new CampoTexto(15);
			textUsuario.setText("admin");
			textUsuario.setToolTipText("Informe o seu usuário");
			textUsuario.setBounds(350, 112, 183, 20);
			contentPane.add(textUsuario);

			JLabel lblSenha = new JLabel("Senha");
			lblSenha.setBounds(350, 138, 46, 14);
			contentPane.add(lblSenha);
			
			textSenha = new CampoSenha(15);
			textSenha.setBounds(350, 155, 183, 20);
			textSenha.setText("123");
			textSenha.setToolTipText("Informe sua senha");
			getContentPane().add(textSenha);
			
			JButton btnEntrar = new JButton("Entrar");
			btnEntrar.setToolTipText("Clique para validar seus dados e entrar no sistema");
			btnEntrar.setBounds(350, 190, 183, 23);
			btnEntrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					login();
				}
			});
			contentPane.add(btnEntrar);
			
			JLabel lblNoPossuiCadastro = new JLabel("Não possui cadastro?");
			lblNoPossuiCadastro.setBounds(380, 223, 183, 14);
			contentPane.add(lblNoPossuiCadastro);
			
			JButton btnCriarUsurio = new JButton("Criar Usuário");
			btnCriarUsurio.setToolTipText("Clique para criar um novo usuário");
			btnCriarUsurio.setBounds(350, 240, 183, 23);
//			btnCriarUsurio.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//
//					criarUser();
//
//				}
//			});
			contentPane.add(btnCriarUsurio);
		
			JLabel lbImagem = new JLabel();
			lbImagem.setIcon(ImagemUtil.obterImagem("img/splash.jpg"));
			lbImagem.setBounds(0,0,getWidth(),getHeight());
			lbImagem.setOpaque(true);
			contentPane.add(lbImagem);
		} catch(Exception e){
			log.error(e);
			JOptionPane.showMessageDialog(this, "Ocorreu um problema contate o desenvolvimento","Erro", JOptionPane.ERROR_MESSAGE);
			log.error(e.getMessage(), e);
		}
		
	}

//	private static void criarUser() {
//
//		String nome, user, senha, confSenha;
//
//		nome = JOptionPane.showInputDialog("Qual o seu nome completo: ");
//		user = JOptionPane.showInputDialog("Crie um nome de usu�rio: ");
//
//		while(CtrlLogin.validaUser(user) || user.equals("")){
//			user = JOptionPane.showInputDialog("Este nome de usu�rio j� existe, tente novamente ");
//		}
//
//		do {
//			senha = JOptionPane.showInputDialog("Crie uma senha: ");
//			confSenha = JOptionPane.showInputDialog("Digite novamente sua senha: ");
//		}while(!senha.equals(confSenha));
//
//		Atendente a = new Atendente(nome, user, senha);
//
//		//Aciona o Controle para salvar o cadastro
//		if(CtrlLogin.cadastrarNovoLogin(a)) {
//			JOptionPane.showMessageDialog(null, nome+", seu usu�rio foi criado com sucesso. ");
//		}
//
//	}
//
	/**
	 * Metodo de login
	 */
	private void login() {
		if(StringUtils.isEmpty(textUsuario.getText())){
			JOptionPane.showMessageDialog(this, "Usuário não informado!","Atenção", JOptionPane.WARNING_MESSAGE);
			textUsuario.requestFocus();
			return;
		}
		if(StringUtils.isEmpty(String.valueOf(textSenha.getPassword()))){
			JOptionPane.showMessageDialog(this, "Senha não informada!","Atenção", JOptionPane.WARNING_MESSAGE);
			textSenha.requestFocus();
			return;
		}
		try {
			Atendente atendente = controle.authenticar(textUsuario.getText(), String.valueOf(textSenha.getPassword()));
			telaCliente.setVisible(true);
			telaCliente.setAtendente(atendente);
			setVisible(false);
			dispose();
		} catch (NegocioException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(),"Atenção", JOptionPane.WARNING_MESSAGE);
			textUsuario.requestFocus();
		} catch (RuntimeException e) {
			JOptionPane.showMessageDialog(this, "Ocorreu um problema contate o desenvolvimento","Erro", JOptionPane.ERROR_MESSAGE);
			log.error(e.getMessage(), e);
		}
	}
	
}