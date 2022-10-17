package dao.files.impl;

import config.file.ConfigProperties;
import dao.files.IF.DaoTypeIF;
import model.Type;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoTypeFileImpl implements DaoTypeIF {

    @Override
    public List<Type> getAll() {
        File file = new File(ConfigProperties.getInstance().getProperty("TypeFile"));
        ArrayList<Type> listType = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                String[] dataType = st.split(";");
                Type type = new Type(Integer.parseInt(dataType[0]), dataType[1]);
                listType.add(type);
            }
        } catch (IOException ex) {
            Logger.getLogger(DaoTypeIF.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listType;
    }

}
