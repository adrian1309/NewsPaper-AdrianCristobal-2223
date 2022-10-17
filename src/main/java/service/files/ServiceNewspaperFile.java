package service.files;

import dao.files.IF.DaoNewspaperIF;
import dao.files.impl.DaoNewspaperFileImpl;
import model.Newspaper;

import java.util.List;

public class ServiceNewspaperFile {

    public List<Newspaper> getAllNewspaper(){
        DaoNewspaperIF dao = new DaoNewspaperFileImpl();
        return dao.getAll();
    }

    public boolean addNewspaper(Newspaper newspaper){
        DaoNewspaperIF dao = new DaoNewspaperFileImpl();
        return dao.add(newspaper);
    }

    public boolean deleteNewspaper(Newspaper newspaper){
        DaoNewspaperIF dao = new DaoNewspaperFileImpl();
        return dao.delete(newspaper);
    }
}
