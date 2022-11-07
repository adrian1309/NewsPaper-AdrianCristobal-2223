package service.jdbc;

import dao.jdbc.DaoReaderJDBC;
import dao.jdbc.impl.DaoReaderJDBCImpl;
import dao.jdbc.impl.DaoReaderJDBCPoolImpl;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Newspaper;
import model.Reader;
import service.ServiceReader;

import java.util.List;

public class ServiceReaderJDBC implements ServiceReader {

   /* @Inject
    public ServiceReaderJDBC(DaoReaderJDBC daoReaderJDBC) {
        this.daoReaderJDBC = daoReaderJDBC;
    }
    */

    private final DaoReaderJDBC daoReaderJDBC;

    @Inject
    public ServiceReaderJDBC(DaoReaderJDBCPoolImpl daoReaderJDBC) {
        this.daoReaderJDBC = daoReaderJDBC;
    }


    @Override
    public Either<Integer, List<Reader>> getAllReaders() {
        //DaoReaderJDBC daoReaderJDBC = new DaoReaderJDBCPoolImpl();
        return daoReaderJDBC.getAll();
    }

    @Override
    public int addReader(Reader reader) {
        //DaoReaderJDBC daoReaderJDBC = new DaoReaderJDBCImpl();
        return daoReaderJDBC.add(reader);
    }

    @Override
    public int deleteReader(Reader reader) {
        //DaoReaderJDBC daoReaderJDBC = new DaoReaderJDBCImpl();
        return daoReaderJDBC.delete(reader);
    }

    @Override
    public int updateReader(Reader reader) {
        //DaoReaderJDBC daoReaderJDBC = new DaoReaderJDBCImpl();
        return daoReaderJDBC.update(reader);
    }

    @Override
    public List<Reader> getReadersSubscribeNewspaper(Newspaper newspaper) {
        return daoReaderJDBC.getReadersSubscribeNewspaper(newspaper);
    }

    @Override
    public List<Reader> getReadersOfArticleType(String type) {
        return daoReaderJDBC.getReadersOfArticleType(type);
    }

    @Override
    public void addReadArticle(int idReader, int idArticle, int rating) {
        daoReaderJDBC.addReadArticle(idReader, idArticle, rating);
    }

    @Override
    public String getNameOldestSubscribersOfNewspaper(Newspaper selectedItem) {
        return daoReaderJDBC.getOlderSubscribersOfNewspaper(selectedItem);
    }

    @Override
    public void addSubscriber(int idNewspaper, int idReader) {
        daoReaderJDBC.addSubscriber(idNewspaper, idReader);
    }


}
