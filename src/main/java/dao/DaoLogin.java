package dao;

import domain.model.Login;
import io.vavr.control.Either;
import jakarta.errores.ApiError;

import java.util.List;

public interface DaoLogin {

    Either<ApiError, List<Login>> findAll();
}
