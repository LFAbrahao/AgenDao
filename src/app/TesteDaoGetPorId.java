package app;

import dao.ContatoDao;
import model.Contato;

import java.sql.SQLException;
import java.util.Scanner;

public class TesteDaoGetPorId {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o ID do contato: ");
        Long id = sc.nextLong();

        try {
            ContatoDao dao = new ContatoDao();
            Contato contato = dao.getPorId(id);

            if (contato != null) {
                System.out.println("Nome: " + contato.getNome());
                System.out.println("Email: " + contato.getEmail());
                System.out.println("Endereço: " + contato.getEndereco());
            } else {
                System.out.println("Contato não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}
