package telas;

import java.sql.SQLException;
import java.util.Scanner;

import controles.CtrlLogin;
import entidades.Atendente;

public class Login {


	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		fazerLogin();

	}

	
	public static void fazerLogin() throws ClassNotFoundException, SQLException {
		Scanner leitor = new Scanner(System.in);

		System.out.println("Digite o login: ");
		String u = leitor.next();
		System.out.println("Digite a senha: ");
		String s = leitor.next();

		boolean userValido = CtrlLogin.tentarLogin(u, s);

		if(userValido) {
			System.out.println("Conectou");
		}else {
			System.out.println("Falhou");
		}
	}

}
