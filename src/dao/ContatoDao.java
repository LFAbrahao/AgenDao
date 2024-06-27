package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import javabanco.ConnectionFactory;
import model.Contato;


public class ContatoDao implements PadraoDao {

	private Connection con;

	public ContatoDao() throws SQLException {
		this.con = new ConnectionFactory().getConnection();
	}

	@Override
	public void adiciona() throws SQLException {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Digite o nome: ");
		String nome = scanner.nextLine();

		System.out.print("Digite o email: ");
		String email = scanner.nextLine();

		System.out.print("Digite o endereço: ");
		String endereco = scanner.nextLine();

		String sql = "INSERT INTO contatos (nome, email, endereco) VALUES (?, ?, ?)";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, nome);
			stmt.setString(2, email);
			stmt.setString(3, endereco);

			stmt.execute();
			System.out.println("Contato adicionado com sucesso!");
		}
	}

	@Override
	public List<Contato> getLista() throws SQLException {
		String query = "select * from contatos";
		PreparedStatement stmt = con.prepareStatement(query);
		ResultSet rset = stmt.executeQuery();
		List<Contato> contatos = new ArrayList<Contato>();
		
		while (rset.next()) {
			Contato contato = new Contato();
			contato.setNome(rset.getString("nome"));
			contato.setEmail(rset.getString("email"));
			contato.setEndereco(rset.getString("endereco"));
			contatos.add(contato);
		}
		
		rset.close();
		stmt.close();
		
		return contatos;
	}

	@Override
	public List<Contato> getPorLetra(String letra) throws SQLException {
		String query = "select * from contatos where nome like ?";
		List<Contato> contatos = new ArrayList<>();
		try (PreparedStatement stmt = con.prepareStatement(query)) {
			stmt.setString(1, letra + "%");
			try (ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					Contato contato = new Contato();
					contato.setId(rset.getLong("id"));
					contato.setNome(rset.getString("nome"));
					contato.setEmail(rset.getString("email"));
					contato.setEndereco(rset.getString("endereco"));
					contatos.add(contato);
				}
				rset.close();
				stmt.close();

				return contatos;
			}
		}
	}

	@Override
	public Contato getPorId(Long id) throws SQLException {
		String query = "select * from contatos where id = ?";
		Contato contato = null;
		try (PreparedStatement stmt = con.prepareStatement(query)) {
			stmt.setLong(1, id);
			try (ResultSet rset = stmt.executeQuery()) {
				if (rset.next()) {
					contato = new Contato();
					contato.setId(rset.getLong("id"));
					contato.setNome(rset.getString("nome"));
					contato.setEmail(rset.getString("email"));
					contato.setEndereco(rset.getString("endereco"));
				}
				rset.close();
				stmt.close();

				return contato;
			}
		}
	}

	@Override
	public void alteraPorId(Long id) throws SQLException {
		String querySelect = "SELECT nome, email, endereco FROM contatos WHERE id = ?";
		String nomeAtual = null, emailAtual = null, enderecoAtual = null;

		try (PreparedStatement stmt = con.prepareStatement(querySelect)) {
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				nomeAtual = rs.getString("nome");
				emailAtual = rs.getString("email");
				enderecoAtual = rs.getString("endereco");
			} else {
				System.out.println("Nenhum contato encontrado com o ID informado.");
				return;
			}
		}

		Scanner scanner = new Scanner(System.in);

		System.out.println("Nome atual: " + nomeAtual);
		System.out.print("Novo nome (deixe em branco para manter): ");
		String novoNome = scanner.nextLine();
		if (novoNome.isEmpty()) {
			novoNome = nomeAtual;
		}

		System.out.println("Email atual: " + emailAtual);
		System.out.print("Novo email (deixe em branco para manter): ");
		String novoEmail = scanner.nextLine();
		if (novoEmail.isEmpty()) {
			novoEmail = emailAtual;
		}

		System.out.println("Endereço atual: " + enderecoAtual);
		System.out.print("Novo endereço (deixe em branco para manter): ");
		String novoEndereco = scanner.nextLine();
		if (novoEndereco.isEmpty()) {
			novoEndereco = enderecoAtual;
		}

		String queryUpdate = "UPDATE contatos SET nome = ?, email = ?, endereco = ? WHERE id = ?";

		try (PreparedStatement stmt = con.prepareStatement(queryUpdate)) {
			stmt.setString(1, novoNome);
			stmt.setString(2, novoEmail);
			stmt.setString(3, novoEndereco);
			stmt.setLong(4, id);

			int linhasModificadas = stmt.executeUpdate();
			if (linhasModificadas > 0) {
				System.out.println("Contato atualizado com sucesso!");
			} else {
				System.out.println("Nenhum contato encontrado com o ID informado.");
			}
		}
	}

	@Override
	public void removePorId(Long id) throws SQLException {
		String sql = "delete from contatos where id = ?";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setLong(1, id);

			stmt.executeUpdate();
		}
	}

	public void closeConnection() throws SQLException {
		if (con != null && !con.isClosed()) {
			con.close();
		}
	}
}