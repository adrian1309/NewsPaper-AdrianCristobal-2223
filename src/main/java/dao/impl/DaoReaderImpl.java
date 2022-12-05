package dao.impl;

import dao.DaoReader;
import dao.connections.DBConnectionPool;
import domain.model.Reader;
import domain.model.errores.InternalServerException;
import domain.model.errores.NotFoundException;
import io.vavr.control.Either;
import jakarta.errores.ApiError;
import jakarta.inject.Inject;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.ErrorConstants;
import utils.QueryConstants;

import java.sql.Date;
import java.util.List;

public class DaoReaderImpl implements DaoReader {


    private final DBConnectionPool pool;

    @Inject
    public DaoReaderImpl(DBConnectionPool pool) {
        this.pool = pool;
    }


    @Override
    public Either<ApiError, List<Reader>> findAll() {
        Either<ApiError, List<Reader>> result;
        try {
            JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());
            List<Reader> readerList = jtm.query(QueryConstants.GET_ALL_READERS, BeanPropertyRowMapper.newInstance(Reader.class));
            result = Either.right(readerList);
        } catch (Exception e) {
            e.printStackTrace();
            result = Either.left(new ApiError(ErrorConstants.ERROR_FIND_READER_LIST));
        }
        return result;
    }

    @Override
    public Reader findOne(int id) {
        try {
            JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());
            List<Reader> readerList = jtm.query(QueryConstants.GET_READER_BY_ID, BeanPropertyRowMapper.newInstance(Reader.class), id);
            if (readerList.size() == 0) {
                throw new NotFoundException(ErrorConstants.ERROR_FIND_READER_WITH_ID);
            }
            return readerList.get(0);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(ErrorConstants.ERROR_NOT_NUMBER);
        }
    }

    @Override
    public Reader save(Reader reader) {
        try {
            JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());
            jtm.update(QueryConstants.ADD_READER, reader.getName_reader(), reader.getBirth_reader());
            return reader;
        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalServerException(ErrorConstants.ERROR_INSERT_READER);
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());
            jtm.update(QueryConstants.DELETE_READER, id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalServerException(ErrorConstants.ERROR_DELETE_READER);
        }
    }

    @Override
    public Reader update(Reader reader) {
        Reader result;
        try{
            Date date = Date.valueOf(reader.getBirth_reader());
            JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());
            jtm.update(QueryConstants.UPDATE_READER, reader.getName_reader(), date, reader.getId());
            result = reader;
        } catch (NotFoundException e) {
            e.printStackTrace();
            throw new NotFoundException(ErrorConstants.ERROR_UPDATE_READER_NOT_FOUND_ID);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalServerException(ErrorConstants.ERROR_UPDATE_READER_INTERNAL_SERVER);
        }
        return result;
    }
}
