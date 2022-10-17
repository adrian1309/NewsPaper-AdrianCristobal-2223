package service.jdbc;

import dao.jdbc.DaoReaderJDBC;
import dao.jdbc.DaoSubscribeJDBC;
import dao.jdbc.impl.DaoSubscribeJDBCImpl;
import model.Subscribe;
import service.ServiceSubscribe;

import java.util.List;

public class ServiceSubscribeJDBC implements ServiceSubscribe {


    @Override
    public void addSubscribe(Subscribe subscribe) {
        DaoSubscribeJDBC dao = new DaoSubscribeJDBCImpl();
        dao.addSubscribe(subscribe);
    }

    @Override
    public void deleteSubscribe(Subscribe subscribe) {
        DaoSubscribeJDBC dao = new DaoSubscribeJDBCImpl();
        dao.deleteSubscribe(subscribe);
    }

    @Override
    public void updateSubscribe(Subscribe subscribe) {

    }

    @Override
    public List<Subscribe> getAllSubscribes() {
        DaoSubscribeJDBC dao = new DaoSubscribeJDBCImpl();
        return dao.getAllSubscribes();
    }
}
