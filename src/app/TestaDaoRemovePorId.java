package app;

import dao.ContatoDao;

import java.sql.SQLException;
import java.util.Scanner;

public class TestaDaoRemovePorId {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ContatoDao contatoDao = new ContatoDao();

            System.out.println("Digita o Id que deseja excluir: ");
            String idContato = scanner.nextLine();

            contatoDao.removePorId(Long.valueOf(idContato));
            System.out.println("Contato removido com sucesso.");

        } catch (SQLException e) {
            System.out.println("Erro no SQL");
        }
    }
}