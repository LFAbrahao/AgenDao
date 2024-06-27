import dao.ContatoDao;
import model.Contato;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AgenDaoApplication {
    public static void main(String[] args) {
        try {
            ContatoDao contatoDao = new ContatoDao();
            Scanner scanner = new Scanner(System.in);
            int opcao;

            do {
                System.out.println("Escolha uma opção: ");
                System.out.println("1. Adicionar contato");
                System.out.println("2. Listar todos os contatos");
                System.out.println("3. Buscar contatos por letra");
                System.out.println("4. Alterar contato por ID");
                System.out.println("5. Remover contato por ID");
                System.out.println("6. Sair");
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        contatoDao.adiciona();
                        break;
                    case 2:
                        List<Contato> contatos = contatoDao.getLista();
                        for (Contato contato : contatos) {
                            System.out.println("Nome do contato: "+contato.getNome());
                            System.out.println("Email do contato: "+contato.getEmail());
                            System.out.println("Endereço do contato: "+contato.getEndereco());
                            System.out.println("=============================================");
                        }
                        break;
                    case 3:
                        System.out.print("Digite a letra para buscar os contatos: ");
                        String letra = scanner.nextLine();
                        List<Contato> contatosPorLetra = contatoDao.getPorLetra(letra);
                        for (Contato contato : contatosPorLetra) {
                            System.out.println("ID: " + contato.getId());
                            System.out.println("Nome: " + contato.getNome());
                            System.out.println("Email: " + contato.getEmail());
                            System.out.println("Endereço: " + contato.getEndereco());
                            System.out.println("---------------------------------");
                        }
                        break;
                    case 4:
                        System.out.print("Digite o ID do contato que deseja alterar: ");
                        Long idAlterar = scanner.nextLong();
                        scanner.nextLine();
                        contatoDao.alteraPorId(idAlterar);
                        break;
                    case 5:
                        System.out.print("Digite o ID do contato que deseja remover: ");
                        Long idRemover = scanner.nextLong();
                        scanner.nextLine();
                        contatoDao.removePorId(idRemover);
                        break;
                    case 6:
                        System.out.println("Saindo...");
                        contatoDao.closeConnection();
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            } while (opcao != 6);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
