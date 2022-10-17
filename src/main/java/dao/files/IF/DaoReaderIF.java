package dao.files.IF;

import model.xml.ReaderXML;
import model.xml.ReadersXML;

import java.util.List;

public interface DaoReaderIF {

    ReadersXML getAllReaders();
    List<ReaderXML> suscribeReaderByNewspaper(int idNewspaper);

    List<ReaderXML> getReadersByArticleByType(List<Integer> idsArticles);

    void addReadArticle(String name, int idReader, int idArticle, int rating);

    void deleteReader(ReaderXML reader);
}
