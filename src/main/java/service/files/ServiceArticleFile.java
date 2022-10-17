package service.files;

import dao.files.impl.DaoArticleFileImpl;
import dao.files.IF.DaoArticleIF;
import model.Article;
import model.Newspaper;

import java.util.List;

public class ServiceArticleFile {

    public List<Article> getAllArticle(){
        DaoArticleIF dao = new DaoArticleFileImpl();
        return dao.getAll();
    }

    public List<String> getArticleByTypeString(int type){
        DaoArticleIF dao = new DaoArticleFileImpl();
        return dao.getByTypeString(type);
    }

    public List<Article> getArticleByTypeArticle(int type){
        DaoArticleIF dao = new DaoArticleFileImpl();
        return dao.getByTypeArticle(type);
    }

    public boolean addArticle(Article article){
        DaoArticleIF dao = new DaoArticleFileImpl();
        return dao.add(article);
    }

    public boolean deleteArticle(Article article){
        DaoArticleIF dao = new DaoArticleFileImpl();
        return dao.delete(article);
    }

    public boolean existArticleById(int id){
        DaoArticleIF dao = new DaoArticleFileImpl();
        return dao.existById(id);
    }

    public List<Article> getAllArticleByNewspaper(Newspaper newspaper){
        DaoArticleIF dao = new DaoArticleFileImpl();
        return dao.getAllByNewspaper(newspaper);
    }
}
