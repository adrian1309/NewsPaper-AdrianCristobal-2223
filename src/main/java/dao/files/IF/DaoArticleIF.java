package dao.files.IF;

import model.Article;
import model.Newspaper;

import java.util.List;

public interface DaoArticleIF {

    List<Article> getAll();

    List<String> getByTypeString(int type);
    List<Article> getByTypeArticle(int type);

    boolean add(Article article);

    boolean delete(Article article);

    boolean existById(int id);

    List<Article> getAllByNewspaper(Newspaper newspaper);
}
