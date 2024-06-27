package app;

import java.sql.SQLException;
import java.util.Scanner;

import dao.ContatoDao;
import model.Contato;

public class TestaDaoInsere {
	public static void main(String[] args) {
		System.out.println("Quer adicionar um contato? (S/N)");
		Scanner sc = new Scanner(System.in);
		String resposta = sc.nextLine().toUpperCase();
		if (resposta.equals("S")){
            try {
                ContatoDao contatoDao = new ContatoDao();
				contatoDao.adiciona();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (resposta.equals("N")) {
			System.out.println("Tchau tchau!");
		} else {
			System.out.println("Não entendi a sua resposta.");
		}
	}

}
