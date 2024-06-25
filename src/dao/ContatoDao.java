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
	public void adiciona(Contato contato) throws SQLException {
		String sql = "insert into contatos(nome, email, endereco) values (?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		Scanner sc = new Scanner(System.in);

		stmt.setString(1, contato.getNome());
		stmt.setString(2, contato.getEmail());
		stmt.setString(3, contato.getEndereco());

		stmt.execute();

		stmt.close();
		con.close();
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
		con.close();
		
		return contatos;
	}

	@Override
	public void getPorLetra(String letra) throws SQLException {

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
			}
		}
		return contato;
	}

	@Override
	public void alteraPorId(Long id, String novoNome, String novoEmail, String novoEndereco) throws SQLException {
	    String query = "UPDATE contatos SET nome = ?, email = ?, endereco = ? WHERE id = ?";
	    
	    try (PreparedStatement stmt = con.prepareStatement(query)) {
	        stmt.setString(1, novoNome);
	        stmt.setString(2, novoEmail);
	        stmt.setString(3, novoEndereco);
	        stmt.setLong(4, id);
	        
	        int linhasModificadas = stmt.executeUpdate();
	        if (linhasModificadas> 0) {
	            System.out.println("Contato atualizado com sucesso!");
	        } else {
	            System.out.println("Nenhum contato encontrado com o ID informado.");
	        }
	    }
	}

	@Override
	public void removePorId(String id) throws SQLException {

	}

}






