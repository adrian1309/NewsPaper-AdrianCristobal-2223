package service;

import io.vavr.control.Either;
import jakarta.errores.ApiError;
import model.Newspaper;
import model.Reader;

import java.util.List;
import java.util.Queue;

public interface ServiceNewspaper {

    Either<Integer, List<Newspaper>> findNewspapers();
    void addNewspaper(Newspaper newspaper);
    void deleteNewspaper(Newspaper newspaper);
    void updateNewspaper(Newspaper newspaper);
    Either<ApiError, Newspaper> findOne(String id);
}
