package service;

import io.vavr.control.Either;
import model.Newspaper;
import model.Reader;

import java.util.List;

public interface ServiceReader {

    Either<Integer, List<Reader>> getAllReaders();

    int addReader(Reader reader);

    int deleteReader(Reader selectedItem);

    int updateReader(Reader reader);

    List<Reader> getReadersSubscribeNewspaper(Newspaper newspaper);
    List<Reader> getReadersOfArticleType(String type);

    void addReadArticle(int idReader, int idArticle, int rating);

    String getNameOldestSubscribersOfNewspaper(Newspaper selectedItem);

    void addSubscriber(int idNewspaper, int idReader);
}
