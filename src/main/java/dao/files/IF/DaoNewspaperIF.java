package dao.files.IF;

import model.Newspaper;

import java.util.List;

public interface DaoNewspaperIF {

    List<Newspaper> getAll();
    boolean add(Newspaper newspaper);
    boolean delete(Newspaper newspaper);
}
