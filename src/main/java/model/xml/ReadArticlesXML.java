package model.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@XmlRootElement(name = "readArticle")
@XmlType(propOrder = { "id", "id_reader", "id_article", "rating"})
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReadArticlesXML {

    private int id;
    private int id_reader;
    private int id_article;
    private int rating;

}
