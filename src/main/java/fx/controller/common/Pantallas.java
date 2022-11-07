package fx.controller.common;

public enum Pantallas {

    MAIN("/fxml/FXMain.fxml"),
    LOGIN("/fxml/FXLoginMain.fxml"),
    WELCOME("/fxml/FXWelcome.fxml"),

    //ARTICLES
    ARTICLE_CREATE("/fxml/article/FXArticleCreate.fxml"),
    ARTICLE_DELETE("/fxml/article/FXArticleDelete.fxml"),
    ARTICLE_READ("/fxml/article/FXArticleRead.fxml"),
    ARTICLE_UPDATE("/fxml/article/FXArticleUpdate.fxml"),
    ARTICLE_BY_TYPE("/fxml/article/FXArticleByType.fxml"),


    //READERS CRUD
    READER_CREATE("/fxml/reader/FXReaderCreate.fxml"),
    READER_DELETE("/fxml/reader/FXReaderDelete.fxml"),
    READER_READ("/fxml/reader/FXReaderRead.fxml"),
    READER_UPDATE("/fxml/reader/FXReaderUpdate.fxml"),

    //READERS JDBC
    READERS_SUBSCRIBE_NEWSPAPER("/fxml/jdbc/FXReadersSubscribeNewspaper.fxml"),
    READERS_OF_ARTICLE_TYPE("/fxml/jdbc/FXReadersOfArticleType.fxml"),
    ADD_READ_ARTICLE("/fxml/jdbc/FXAddReadArticle.fxml"),

    DESCRIPTION_AND_NUMBER_READERS_OF_EACH_ARTICLE("/fxml/jdbc/FXDescriptionAndNumberReadersOfEachArticle.fxml"),
    NAME_OF_OLDEST_SUBSCRIBERS_OF_NEWSPAPER("/fxml/jdbc/FXNameOfOldestSubscribersOfNewspaper.fxml"),
    READER_SUBSCRIBE_NEWSPAPER("/fxml/jdbc/FXReaderSubscribingToNewspaper.fxml"),

    //NEWSPAPERS SPRING
    FIND_NEWSPAPERS_SPRING("/fxml/spring/FXFindNewspapersSpring.fxml"),
    DELETE_NEWSPAPER_SPRING("/fxml/spring/FXDeleteNewspaperSpring.fxml"),
    ADD_NEWSPAPER_SPRING("/fxml/spring/FXAddNewspaperSpring.fxml"),
    NEWSPAPER_UPDATE_SPRING("/fxml/spring/FXNewspaperUpdateSpring.fxml");


    private String rutaPantalla;
    Pantallas(String ruta) {
        this.rutaPantalla=ruta;
    }
    public String getRutaPantalla(){return rutaPantalla;}
}
