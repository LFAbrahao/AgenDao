package app;

import dao.ContatoDao;
import model.Contato;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class TestaDaoGetPorLetra {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.println("Digite sua letra: ");
            String letra = scanner.nextLine();

            if (letra == null || letra.isEmpty() || !letra.matches("[a-zA-Z]")) {
                System.out.println("Letra inválida.");
                return;
            }

            ContatoDao contatoDao = new ContatoDao();

            List<Contato> contatos = contatoDao.getPorLetra(letra);

            for (Contato contato : contatos) {
                System.out.println("ID: " + contato.getId());
                System.out.println("Nome: " + contato.getNome());
                System.out.println("Email: " + contato.getEmail());
                System.out.println("Endereço: " + contato.getEndereco());
                System.out.println("---------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
