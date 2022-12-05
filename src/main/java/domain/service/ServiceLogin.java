package domain.service;

import domain.model.Login;
import io.vavr.control.Either;
import jakarta.errores.ApiError;

import java.util.List;

public interface ServiceLogin {

    Either<ApiError, List<Login>> findAll();
}
