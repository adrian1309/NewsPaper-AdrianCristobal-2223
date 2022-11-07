package dao.jdbc;

import io.vavr.control.Either;
import model.Newspaper;
import model.Reader;

import java.util.List;

public interface DaoReaderJDBC {

    Either<Integer, List<Reader>> getAll();

    int add(Reader reader);

    int delete(Reader reader);

    int update(Reader reader);

    List<Reader> getReadersSubscribeNewspaper(Newspaper newspaper);

    List<Reader> getReadersOfArticleType(String type);

    void addReadArticle(int idReader, int idArticle, int rating);

    String getOlderSubscribersOfNewspaper(Newspaper selectedItem);

    void addSubscriber(int idNewspaper, int idReader);
}
