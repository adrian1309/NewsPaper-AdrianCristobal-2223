package dao.impl;

import dao.DaoLogin;
import dao.connections.DBConnectionPool;
import domain.model.Login;
import io.vavr.control.Either;
import jakarta.errores.ApiError;
import jakarta.inject.Inject;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.ErrorConstants;
import utils.QueryConstants;

import java.util.List;

public class DaoLoginImpl implements DaoLogin {

    private final DBConnectionPool pool;

    @Inject
    public DaoLoginImpl(DBConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public Either<ApiError, List<Login>> findAll() {
        Either<ApiError, List<Login>> result;
        try {
            JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());
            List<Login> userList = jtm.query(QueryConstants.GET_ALL_USERS, BeanPropertyRowMapper.newInstance(Login.class));
            result = Either.right(userList);
        } catch (Exception e) {
            e.printStackTrace();
            result = Either.left(new ApiError(ErrorConstants.ERROR_FIND_NEWSPAPER_LIST));
        }
        return result;
    }
}
