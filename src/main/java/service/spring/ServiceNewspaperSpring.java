package service.spring;

import dao.spring.DaoNewspaperSpring;
import dao.spring.impl.DaoNewspaperSpringImpl;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Newspaper;
import service.ServiceNewspaper;

import java.util.List;

public class ServiceNewspaperSpring implements ServiceNewspaper {

    DaoNewspaperSpring daoNewspaperSpring;

    @Inject
    public ServiceNewspaperSpring(DaoNewspaperSpringImpl daoNewspaperSpringImpl) {
        this.daoNewspaperSpring = daoNewspaperSpringImpl;
    }

    @Override
    public Either<Integer, List<Newspaper>> findNewspapers() {
        return daoNewspaperSpring.findNewspapers();
    }

    @Override
    public void addNewspaper(Newspaper newspaper) {
        daoNewspaperSpring.addNewspaper(newspaper);
    }

    @Override
    public void deleteNewspaper(Newspaper newspaper) {
        daoNewspaperSpring.deleteNewspaper(newspaper);
    }

    @Override
    public void updateNewspaper(Newspaper newspaper) {
        daoNewspaperSpring.updateNewspaper(newspaper);
    }
}
