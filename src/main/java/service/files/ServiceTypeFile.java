package service.files;

import dao.files.impl.DaoTypeFileImpl;
import dao.files.IF.DaoTypeIF;
import model.Type;

import java.util.List;

public class ServiceTypeFile {

    public List<Type> getAllType(){
        DaoTypeIF dao = new DaoTypeFileImpl();
        return dao.getAll();
    }
}
