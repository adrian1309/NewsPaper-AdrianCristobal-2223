package domain.service;

import io.vavr.control.Either;
import jakarta.errores.ApiError;
import domain.model.Newspaper;

import java.util.List;

public interface ServiceNewspaper {
    Either<ApiError, List<Newspaper>> findAll();
    Newspaper findOne(int id);
    Newspaper add(Newspaper newspaper);
    boolean delete(int id);
    Newspaper update(Newspaper newspaper);
    List<String> findAllNewspaperLessThanDate(String date);
}
