package dao;

import domain.model.Reader;
import io.vavr.control.Either;
import jakarta.errores.ApiError;

import java.util.List;

public interface DaoReader {

    Either<ApiError, List<Reader>> findAll();
    Reader findOne(int id);
    Reader save(Reader reader);
    boolean delete(int id);
    Reader update(Reader reader);
}
