package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javabanco.ConnectionFactory;
import model.Contato;


public class ContatoDao implements PadraoDao {

	private Connection con;

	@Override
	public void adiciona(Contato contato) throws SQLException {
		String sql = "insert into contatos(nome, email, endereco) values (?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
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
	public void getPorLetra() throws SQLException {

	}

	@Override
	public void getPorId() throws SQLException {

	}

	@Override
	public void alteraPorId() throws SQLException {

	}

	@Override
	public void removePorId() throws SQLException {

	}

}






