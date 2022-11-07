package dao.spring;

import io.vavr.control.Either;
import model.Newspaper;

import java.util.List;
import java.util.Map;

public interface DaoNewspaperSpring {

    Either<Integer, List<Newspaper>> findNewspapers();

    void addNewspaper(Newspaper newspaper);

    void deleteNewspaper(Newspaper newspaper);

    void updateNewspaper(Newspaper newspaper);
}
