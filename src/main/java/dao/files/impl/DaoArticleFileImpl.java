package dao.files.impl;

import config.file.ConfigProperties;
import dao.files.IF.DaoArticleIF;
import model.Article;
import model.Newspaper;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.nio.file.StandardOpenOption.*;

public class DaoArticleFileImpl implements DaoArticleIF {


    @Override
    public List<Article> getAll() {
        File file = new File(ConfigProperties.getInstance().getProperty("ArticleFile"));
        ArrayList<Article> listArticle = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                String[] dataArticle = st.split(";");
                Article article = new Article(Integer.parseInt(dataArticle[0]), dataArticle[1], Integer.parseInt(dataArticle[2]), Integer.parseInt(dataArticle[3]));
                listArticle.add(article);
            }
        } catch (IOException ex) {
            Logger.getLogger(DaoArticleIF.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listArticle;
    }

    @Override
    public List<String> getByTypeString(int type) {
        File file = new File(ConfigProperties.getInstance().getProperty("ArticleFile"));
        ArrayList<String> listArticle = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                Article article = new Article(st);
                if (article.getId_type() == type) {
                    listArticle.add(article.toStringFile());
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(DaoArticleIF.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listArticle;
    }

    @Override
    public List<Article> getByTypeArticle(int type) {
        File file = new File(ConfigProperties.getInstance().getProperty("ArticleFile"));
        ArrayList<Article> listArticle = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                Article article = new Article(st);
                if (article.getId_type() == type) {
                    listArticle.add(article);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(DaoArticleIF.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listArticle;
    }

    @Override
    public boolean add(Article article) {
        //File file = new File(ConfigProperties.getInstance().getProperty("ArticleFile"));
        Path path = Paths.get(ConfigProperties.getInstance().getProperty("ArticleFile"));
        boolean result = false;
        /*try (FileWriter writer = new FileWriter(file, true);  //the true will append the new data
             BufferedWriter bw = new BufferedWriter(writer)) {
            String content = article.getId() + ";" + article.getName_article()
                     + ";" + article.getId_newspaper() + ";" + article.getId_type() + "\n";
            bw.write(content);
            result = true;
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

         */
        try (BufferedWriter writer = Files.newBufferedWriter(path, APPEND)) {
            String content = article.getId() + ";" + article.getName_article()
                    + ";" + article.getId_newspaper() + ";" + article.getId_type() + "\n";
            writer.write(content);
            result = true;
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        return result;
    }

    @Override
    public boolean delete(Article article) {
        List<Article> listArticle = getAll();
        File file = new File(ConfigProperties.getInstance().getProperty("ArticleFile"));
        try (FileWriter writer = new FileWriter(file, false);  //the true will append the new data
             BufferedWriter bw = new BufferedWriter(writer)) {
            bw.write("");
            listArticle.remove(article);
            listArticle.forEach(article1 -> add(article1));
            return true;
        } catch (IOException ex) {
            Logger.getLogger(DaoArticleIF.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean existById(int id) {
        File file = new File(ConfigProperties.getInstance().getProperty("ArticleFile"));
        ArrayList<Article> listArticle = new ArrayList<>();
        AtomicBoolean value = new AtomicBoolean(false);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                String[] dataArticle = st.split(";");
                Article article = new Article(Integer.parseInt(dataArticle[0]), dataArticle[1], Integer.parseInt(dataArticle[2]), Integer.parseInt(dataArticle[3]));
                listArticle.add(article);
            }
        } catch (IOException ex) {
            Logger.getLogger(DaoArticleIF.class.getName()).log(Level.SEVERE, null, ex);
        }
        listArticle.forEach((article) -> {
            if (article.getId() == id) {
                value.set(true);
            }
        });
        return value.get();
    }

    @Override
    public List<Article> getAllByNewspaper(Newspaper newspaper) {
        File file = new File(ConfigProperties.getInstance().getProperty("ArticleFile"));
        ArrayList<Article> listArticle = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                String[] dataArticle = st.split(";");
                Article article = new Article(Integer.parseInt(dataArticle[0]), dataArticle[1], Integer.parseInt(dataArticle[2]), Integer.parseInt(dataArticle[3]));
                if (article.getId_newspaper() == newspaper.getId()) {
                    listArticle.add(article);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(DaoArticleIF.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listArticle;
    }
}
