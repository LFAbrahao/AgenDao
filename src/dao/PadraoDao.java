package dao;

import model.Contato;

import java.sql.SQLException;
import java.util.List;

public interface PadraoDao<T> {
    void adiciona(T object) throws SQLException;

    List<?> getLista() throws SQLException;

    void getPorLetra() throws SQLException;

    void getPorId() throws SQLException;

    void alteraPorId() throws SQLException;

    void removePorId() throws SQLException;
}