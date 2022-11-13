package domain.service;

import domain.model.Article;

import java.util.List;

public interface ServiceArticle {

    List<Article> getAllArticle();

    String getDescriptionAndNumberOfReadersOfEachArticle(Article selectedItem);
}
