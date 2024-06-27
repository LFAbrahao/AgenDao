package app;

import dao.ContatoDao;
import model.Contato;

import java.sql.SQLException;
import java.util.Scanner;

public class TestaDaoAlteraPorId {
    public static void main(String[] args) throws SQLException {
       ContatoDao contatoDao = new ContatoDao();
       Scanner sc = new Scanner(System.in);
        System.out.println("qual é o id?");
       contatoDao.alteraPorId(sc.nextLong());
    }
}
