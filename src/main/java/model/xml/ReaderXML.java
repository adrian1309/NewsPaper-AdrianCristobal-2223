package model.xml;


import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDate;
import java.util.List;

@XmlRootElement
@XmlType(propOrder = { "name", "dateOfBirth", "subscription", "readArticle" })
@XmlAccessorType(XmlAccessType.FIELD)

public class ReaderXML {

    private String name;

    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate dateOfBirth;

    private List<SubscribeXML> subscription;

    private List<ReadArticlesXML> readArticle;


    public ReaderXML() {
    }

    public ReaderXML(String name, LocalDate dateOfBirth, List<SubscribeXML> subscriptions, List<ReadArticlesXML> readArticles) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.subscription = subscriptions;
        this.readArticle = readArticles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<SubscribeXML> getSubscription() {
        return subscription;
    }

    public void setSubscription(List<SubscribeXML> subscription) {
        this.subscription = subscription;
    }

    public List<ReadArticlesXML> getReadArticle() {
        return readArticle;
    }

    public void setReadArticle(List<ReadArticlesXML> readArticle) {
        this.readArticle = readArticle;
    }

    @Override
    public String toString() {
        return "Name: " + name + " - Birth: " + dateOfBirth;
    }
}
