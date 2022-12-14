package domain.service;

import com.google.protobuf.Api;
import domain.model.Reader;
import io.vavr.control.Either;
import domain.model.Newspaper;
import jakarta.errores.ApiError;

import java.util.List;

public interface ServiceReader {

    Either<ApiError, List<Reader>> findAll();
    Reader findOne(int id);
    Reader add(Reader reader);
    boolean delete(int id);
    Reader update(Reader reader);
}
