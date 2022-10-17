package dao.jdbc;

import model.Subscribe;

import java.util.List;

public interface DaoSubscribeJDBC {
    void addSubscribe(Subscribe subscribe);

    List<Subscribe> getAllSubscribes();

    void deleteSubscribe(Subscribe subscribe);
}
