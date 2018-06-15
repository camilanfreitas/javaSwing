package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controles.CtrlLogin;
import entidades.Atendente;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class TelaLogin {

	private static JFrame frame;
	private static JTextField textUsuario;
	private static JPasswordField textSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin window = new TelaLogin();
					TelaLogin.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("OhBanks - Faça Login");
		frame.setBounds(325, 125, 300, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblUsuario = new JLabel("Usu\u00E1rio");
		lblUsuario.setBounds(52, 125, 46, 14);
		frame.getContentPane().add(lblUsuario);

		textUsuario = new JTextField();
		textUsuario.setBounds(52, 140, 183, 20);
		frame.getContentPane().add(textUsuario);
		textUsuario.setColumns(10);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(52, 175, 46, 14);
		frame.getContentPane().add(lblSenha);

		textSenha = new JPasswordField();
		textSenha.setBounds(52, 190, 183, 20);
		frame.getContentPane().add(textSenha);		
		textSenha.setColumns(10);

		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (!getTextSenha().equals("") && !getTextUsuario().equals("")) {
					fazerLogin();
				}else {
					if(getTextSenha().equals("") && getTextUsuario().equals("")) {
						JOptionPane.showMessageDialog(null, "Digite seu usuário e senha ou crie um novo cadastro");
					}else if(getTextUsuario().equals("")) {
						JOptionPane.showMessageDialog(null, "Digite seu usuário");
					}else {
						JOptionPane.showMessageDialog(null, "Digite sua senha");
					}
					textSenha.setText(null);
				}

			}
		});
		btnEntrar.setBounds(52, 225, 183, 23);
		frame.getContentPane().add(btnEntrar);

		JButton btnCriarUsurio = new JButton("Criar Usu\u00E1rio");
		btnCriarUsurio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				criarUser();
				
			}
		});
		btnCriarUsurio.setBounds(52, 308, 183, 23);
		frame.getContentPane().add(btnCriarUsurio);

		JLabel lblNoPossuiCadastro = new JLabel("N\u00E3o possui cadastro?");
		lblNoPossuiCadastro.setBounds(52, 288, 183, 14);
		frame.getContentPane().add(lblNoPossuiCadastro);
		
		
	}


	private static void criarUser() {
		
		String nome, user, senha, confSenha;
		
		nome = JOptionPane.showInputDialog("Qual o seu nome completo: ");
		user = JOptionPane.showInputDialog("Crie um nome de usuário: ");		
		
		while(CtrlLogin.validaUser(user) || user.equals("")){
			user = JOptionPane.showInputDialog("Este nome de usuário já existe, tente novamente ");
		}
		
		do {
			senha = JOptionPane.showInputDialog("Crie uma senha: ");
			confSenha = JOptionPane.showInputDialog("Digite novamente sua senha: ");
		}while(!senha.equals(confSenha));

		Atendente a = new Atendente(nome, user, senha);

		//Aciona o Controle para salvar o cadastro
		if(CtrlLogin.cadastrarNovoLogin(a)) {
			JOptionPane.showMessageDialog(null, nome+", seu usuário foi criado com sucesso. ");
		}

	}

	//Método de Fazer Login
	private static void fazerLogin(){

		Atendente a = new Atendente();
		
		a.setLogin(getTextUsuario());
		a.setSenha(getTextSenha());
		
		//Aciona o controle para tentar o Login
		boolean userValido = CtrlLogin.tentarLogin(a);

		if(userValido) {
			//Aciona o controle para abrir o controle
			CtrlLogin.abrirSistema();
			frame.dispose();
		}else {
			JOptionPane.showMessageDialog(null, "O Login falhou, tente novamente ou cadastre um novo usuário");
			textSenha.setText(null);
			textUsuario.setText(null);
		}
	}

	private static String getTextUsuario() {
		return textUsuario.getText();
	}

	private static String getTextSenha() {
		return String.valueOf(textSenha.getPassword());
	}
	
	
}
