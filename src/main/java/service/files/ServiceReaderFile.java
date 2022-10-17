package service.files;

import dao.files.impl.DaoReaderFileXMLImpl;
import dao.files.IF.DaoReaderIF;
import model.xml.ReaderXML;
import model.xml.ReadersXML;

import java.util.List;

public class ServiceReaderFile {


    public ReadersXML getAllReaders(){
        DaoReaderIF daoReader = new DaoReaderFileXMLImpl();
        return daoReader.getAllReaders();
    }
    public List<ReaderXML> suscribeReaderByNewspaper(int idNewspaper){
        DaoReaderIF daoReaderIF = new DaoReaderFileXMLImpl();
        return daoReaderIF.suscribeReaderByNewspaper(idNewspaper);
    }

    public List<ReaderXML> getReadersByArticleByType(List<Integer> idsArticles){
        DaoReaderIF daoReaderIF = new DaoReaderFileXMLImpl();
        return daoReaderIF.getReadersByArticleByType(idsArticles);
    }

    public void addReadArticle(String name, int idReader, int idArticle, int rating){
        DaoReaderIF daoReaderIF = new DaoReaderFileXMLImpl();
        daoReaderIF.addReadArticle(name, idReader, idArticle, rating);
    }

    public void deleteReader(ReaderXML reader) {
        DaoReaderIF daoReaderIF = new DaoReaderFileXMLImpl();
        daoReaderIF.deleteReader(reader);
    }
}
