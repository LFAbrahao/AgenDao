package app;

import java.sql.SQLException;
import java.util.Scanner;

import dao.ContatoDao;
import model.Contato;

public class TestaDaoInsere {
	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Digite o nome do contato: ");
			String nome = scanner.nextLine();

			System.out.println("Digite o email do contato: ");
			String email = scanner.nextLine();

			System.out.println("Digite o endereço do contato: ");
			String endereco = scanner.nextLine();

			Contato contato = new Contato();
			contato.setNome(nome);
			contato.setEmail(email);
			contato.setEndereco(endereco);

			try {
				ContatoDao dao = new ContatoDao();
				dao.adiciona(contato);
				System.out.println("Gravação feita no Banco de Dados");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
