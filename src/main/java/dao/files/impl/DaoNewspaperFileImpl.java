package dao.files.impl;

import config.file.ConfigProperties;
import dao.files.IF.DaoNewspaperIF;
import model.Newspaper;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DaoNewspaperFileImpl implements DaoNewspaperIF {


    /*
    @Inject
    Newspaper newspaper;

     */

    @Override
    public List<Newspaper> getAll() {
        File file = new File(ConfigProperties.getInstance().getProperty("NewspaperFile"));
        ArrayList<Newspaper> listNewspaper = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                Newspaper np = new Newspaper(st);
                listNewspaper.add(np);
            }
        } catch (IOException ex) {
            Logger.getLogger(DaoNewspaperIF.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listNewspaper;
    }

    @Override
    public boolean add(Newspaper newspaper) {
        File file = new File(ConfigProperties.getInstance().getProperty("NewspaperFile"));
        try (FileWriter writer = new FileWriter(file, true);  //the true will append the new data
             BufferedWriter bw = new BufferedWriter(writer)) {
            String content = newspaper.getId() + ";" + newspaper.getName_newspaper()
                    + ";" + newspaper.getRealise_date() + "\n";
            bw.write(content);
            return true;
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
            return false;
        }
    }

    @Override
    public boolean delete(Newspaper newspaper) {
        List<Newspaper> listNewspaper = getAll();
        File file = new File(ConfigProperties.getInstance().getProperty("NewspaperFile"));
        try (FileWriter writer = new FileWriter(file, false);  //the true will append the new data
             BufferedWriter bw = new BufferedWriter(writer)) {
            bw.write("");
            listNewspaper.remove(newspaper);
            listNewspaper.forEach(newspaper1 -> add(newspaper1));
            return true;
        } catch (IOException ex) {
            Logger.getLogger(DaoNewspaperIF.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
