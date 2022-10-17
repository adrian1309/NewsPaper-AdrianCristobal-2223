package dao.jdbc;

import model.Reader;

import java.util.List;

public interface DaoReaderJDBC {

    List<Reader> getAllReaders();

    void addReader(Reader reader);

    void deleteReader(Reader reader);

    void updateReader(Reader reader);
}
