package service.jdbc;

import dao.files.IF.DaoNewspaperIF;
import dao.jdbc.DaoNewspaperJDBC;
import dao.jdbc.DaoReaderJDBC;
import dao.jdbc.impl.DaoNewspaperJDBCPoolImpl;
import dao.jdbc.impl.DaoReaderJDBCPoolImpl;
import io.vavr.control.Either;
import jakarta.errores.ApiError;
import jakarta.inject.Inject;
import model.Newspaper;
import model.Reader;
import service.ServiceNewspaper;

import java.util.List;

public class ServiceNewspaperJDBC implements ServiceNewspaper {

    private final DaoNewspaperJDBC daoNewspaperJDBC;

    @Inject
    public ServiceNewspaperJDBC(DaoNewspaperJDBCPoolImpl daoNewspaperJDBC) {
        this.daoNewspaperJDBC = daoNewspaperJDBC;
    }


    @Override
    public Either<Integer, List<Newspaper>> findNewspapers() {
        return daoNewspaperJDBC.getAll();
    }

    @Override
    public void addNewspaper(Newspaper newspaper) {
    }

    @Override
    public void deleteNewspaper(Newspaper newspaper) {

    }

    @Override
    public void updateNewspaper(Newspaper newspaper) {

    }

    @Override
    public Either<ApiError, Newspaper> findOne(String id) {
        return daoNewspaperJDBC.getOne(id);
    }
}
