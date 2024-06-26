package app;

import dao.ContatoDao;
import model.Contato;

import java.sql.SQLException;
import java.util.Scanner;

public class TestaDaoAlteraPorId {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o ID do contato que deseja alterar: ");
        Long id = sc.nextLong();
        sc.nextLine(); // Consumir a quebra de linha deixada pelo nextLong()

        try {
            ContatoDao dao = new ContatoDao();

            // Obtendo o contato atual com o ID fornecido
            Contato contatoAtual = dao.getPorId(id);

            if (contatoAtual != null) {
                System.out.println("ID encontrada - Informe os novos dados:");

                System.out.println("Novo nome: ");
                String novoNome = sc.nextLine();
                if (novoNome.isEmpty() || novoNome.isBlank()|| novoNome.equals(contatoAtual.getNome())) {
					novoNome = contatoAtual.getNome();
					System.out.println("Nome mantido");
				} else {
					System.out.println("Nome alterado");
				}

                System.out.println("Novo email: ");
                String novoEmail = sc.nextLine();
                if (novoEmail.isEmpty() || novoEmail.isBlank()|| novoEmail.equals(contatoAtual.getEmail())) {
					novoEmail = contatoAtual.getEmail();
					System.out.println("Email mantido");
				} else {
					System.out.println("Email alterado");
				}
                

                System.out.println("Novo endereço: ");
                String novoEndereco = sc.nextLine();
                if (novoEndereco.isEmpty() || novoEndereco.isBlank()|| novoEndereco.equals(contatoAtual.getEndereco())) {
					novoEndereco = contatoAtual.getEndereco();
					System.out.println("Endereco mantido");
				} else {
					System.out.println("Endereco alterado");
				}
                
                

                // Criando um novo objeto Contato com os dados atualizados
                Contato contatoAtualizado = new Contato();
                contatoAtualizado.setId(id);
                contatoAtualizado.setNome(novoNome);
                contatoAtualizado.setEmail(novoEmail);
                contatoAtualizado.setEndereco(novoEndereco);

                // Chamando o método para alterar o contato no banco de dados
                dao.alteraPorId(id, novoNome, novoEmail, novoEndereco);

                

                

            } else {
                System.out.println("Contato não encontrado com o ID informado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}
