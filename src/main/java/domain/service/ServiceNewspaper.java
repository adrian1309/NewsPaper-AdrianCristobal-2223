package domain.service;

import io.vavr.control.Either;
import jakarta.errores.ApiError;
import domain.model.Newspaper;

import java.util.List;

public interface ServiceNewspaper {

    Either<Integer, List<Newspaper>> findNewspapers();
    void addNewspaper(Newspaper newspaper);
    void deleteNewspaper(Newspaper newspaper);
    void updateNewspaper(Newspaper newspaper);
    Either<ApiError, List<Newspaper>> findAll();
    Newspaper findOne(int id);
    boolean delete(int id);
    Newspaper add(Newspaper newspaper);
}
