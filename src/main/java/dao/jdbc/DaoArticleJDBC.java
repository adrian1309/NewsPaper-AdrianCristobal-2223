package dao.jdbc;

import io.vavr.control.Either;
import model.Article;

import java.util.List;
import java.util.Map;

public interface DaoArticleJDBC {

    Either<Integer, List<Article>> getAll();

    String getDescriptionAndNumberOfReadersOfEachArticle(Article selectedItem);
}
