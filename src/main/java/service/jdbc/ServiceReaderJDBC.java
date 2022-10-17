package service.jdbc;

import dao.jdbc.DaoReaderJDBC;
import dao.jdbc.impl.DaoReaderJDBCImpl;
import jakarta.inject.Inject;
import model.Reader;
import service.ServiceReader;

import java.util.List;

public class ServiceReaderJDBC implements ServiceReader {

   /* @Inject
    public ServiceReaderJDBC(DaoReaderJDBC daoReaderJDBC) {
        this.daoReaderJDBC = daoReaderJDBC;
    }
    */

    @Override
    public List<Reader> getAllReaders() {
        DaoReaderJDBC daoReaderJDBC = new DaoReaderJDBCImpl();
        return daoReaderJDBC.getAllReaders();
    }

    @Override
    public void addReader(Reader reader) {
        DaoReaderJDBC daoReaderJDBC = new DaoReaderJDBCImpl();
        daoReaderJDBC.addReader(reader);
    }

    @Override
    public void deleteReader(Reader reader) {
        DaoReaderJDBC daoReaderJDBC = new DaoReaderJDBCImpl();
        daoReaderJDBC.deleteReader(reader);
    }

    @Override
    public void updateReader(Reader reader) {
        DaoReaderJDBC daoReaderJDBC = new DaoReaderJDBCImpl();
        daoReaderJDBC.updateReader(reader);
    }


}
