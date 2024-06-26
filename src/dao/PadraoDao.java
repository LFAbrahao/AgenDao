package dao;

import model.Contato;

import java.sql.SQLException;
import java.util.List;

public interface PadraoDao {
    void adiciona() throws SQLException;

    List<?> getLista() throws SQLException;

    List<Contato> getPorLetra(String letra) throws SQLException;

    Contato getPorId(Long id) throws SQLException;

    void removePorId(Long id) throws SQLException;

	void alteraPorId(Long id) throws SQLException;
}