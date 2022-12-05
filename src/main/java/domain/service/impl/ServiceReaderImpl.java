package domain.service.impl;

import dao.DaoReader;
import dao.impl.DaoReaderImpl;
import domain.model.Newspaper;
import domain.model.Reader;
import domain.service.ServiceReader;
import io.vavr.control.Either;
import jakarta.errores.ApiError;
import jakarta.inject.Inject;

import java.util.List;

public class ServiceReaderImpl implements ServiceReader {

    DaoReader daoReader;

    @Inject
    public ServiceReaderImpl(DaoReaderImpl daoReaderImpl) {
        this.daoReader = daoReaderImpl;
    }


    @Override
    public Either<ApiError, List<Reader>> findAll() {
        return daoReader.findAll();
    }

    @Override
    public Reader findOne(int id) {
        return daoReader.findOne(id);
    }

    @Override
    public Reader add(Reader reader) {
        return daoReader.save(reader);
    }

    @Override
    public boolean delete(int id) {
        return daoReader.delete(id);
    }

    @Override
    public Reader update(Reader reader) {
        return daoReader.update(reader);
    }
}
