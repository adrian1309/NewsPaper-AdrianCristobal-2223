package dao.jdbc;

import io.vavr.control.Either;
import jakarta.errores.ApiError;
import model.Newspaper;

import java.util.List;

public interface DaoNewspaperJDBC {
    Either<Integer, List<Newspaper>> getAll();

    Either<ApiError, Newspaper> getOne(String id);
}
