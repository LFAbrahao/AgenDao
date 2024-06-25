package dao;

import model.Contato;

import java.sql.SQLException;
import java.util.List;

public interface PadraoDao {
    void adiciona(Contato contato) throws SQLException;

    List<?> getLista() throws SQLException;

    void getPorLetra(String letra) throws SQLException;

    Contato getPorId(Long id) throws SQLException;

//   void alteraPorId(String id) throws SQLException;

    void removePorId(String id) throws SQLException;

	void alteraPorId(Long id, String novoNome, String novoEmail, String novoEndereco) throws SQLException;
}