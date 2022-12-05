package domain.service.impl;

import dao.DaoLogin;
import dao.DaoNewspaper;
import dao.impl.DaoLoginImpl;
import dao.impl.DaoNewspaperImpl;
import domain.model.Login;
import domain.service.ServiceLogin;
import io.vavr.control.Either;
import jakarta.errores.ApiError;
import jakarta.inject.Inject;

import java.util.List;

public class ServiceLoginImpl implements ServiceLogin {

    DaoLogin daoLogin;

    @Inject
    public ServiceLoginImpl(DaoLoginImpl daoLoginImpl) {
        this.daoLogin = daoLoginImpl;
    }


    @Override
    public Either<ApiError, List<Login>> findAll() {
        return daoLogin.findAll();
    }
}
