package service.jdbc;

import dao.jdbc.DaoArticleJDBC;
import dao.jdbc.impl.DaoArticleJDBCPoolImpl;
import jakarta.inject.Inject;
import model.Article;
import service.ServiceArticle;

import java.util.List;

public class ServiceArticleJDBC implements ServiceArticle {

    private final DaoArticleJDBC daoArticleJDBC;

    @Inject
    public ServiceArticleJDBC(DaoArticleJDBCPoolImpl daoArticleJDBC) {
        this.daoArticleJDBC = daoArticleJDBC;
    }

    @Override
    public List<Article> getAllArticle() {
        return daoArticleJDBC.getAll().get();
    }

    @Override
    public String getDescriptionAndNumberOfReadersOfEachArticle(Article selectedItem) {
        return daoArticleJDBC.getDescriptionAndNumberOfReadersOfEachArticle(selectedItem);
    }
}
