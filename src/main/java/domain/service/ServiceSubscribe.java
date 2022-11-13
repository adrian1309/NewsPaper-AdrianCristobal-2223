package domain.service;

import domain.model.Subscribe;

import java.util.List;

public interface ServiceSubscribe {
    void addSubscribe(Subscribe subscribe);
    void deleteSubscribe(Subscribe subscribe);
    void updateSubscribe(Subscribe subscribe);
    List<Subscribe> getAllSubscribes();
}
