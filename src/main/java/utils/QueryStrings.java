package utils;

public class QueryStrings {
    //READER
    public static final String GET_ALL_READERS = "SELECT * FROM reader";
    public static final String ADD_READER = "INSERT INTO reader (name_reader, birth_reader) VALUES (?,?)";
    public static final String DELETE_READER = "DELETE FROM reader WHERE id = ?";
    public static final String UPDATE_READER = "UPDATE reader SET name_reader = ?, birth_reader = ? WHERE id = ?";
    public static final String GET_READERS_BY_NEWSPAPER = "SELECT * FROM reader WHERE id IN (SELECT id_reader FROM subscribe WHERE id_newspaper = ?)";

    //SUBSCRIBE
    public static final String GET_ALL_SUBSCRIBES = "SELECT * FROM subscribe";
    public static final String DELETE_SUBSCRIBE = "DELETE FROM subscribe WHERE id_newspaper = ? AND id_reader = ?";
    public static final String DELETE_SUBSCRIBE_BY_ID_READER = "DELETE FROM subscribe WHERE id_reader = ?";

    //NEWSPAPER
    public static final String GET_ALL_NEWSPAPERS = "SELECT * FROM newspaper";
    public static final String GET_NAME_OF_100_OLDEST_SUBSCRIPTORS_OF_A_NEWSPAPER = "select description, count(id_reader) as number_of_readers from article, readarticle, type where article.id = readarticle.id_article and article.id_type = type.id and article.id = ? group by description";
    public static final String ADD_NEWSPAPER = "INSERT INTO newspaper (name_newspaper, release_date) VALUES (:name_newspaper,:release_date)";
    public static final String DELETE_NEWSPAPER = "DELETE FROM newspaper WHERE id = ?";
    public static final String UPDATE_NEWSPAPER = "UPDATE newspaper SET name_newspaper = ?, release_date = ? WHERE id = ?";

    //ARTICLE
    public static final String GET_ALL_ARTICLES = "SELECT * FROM article";
    public static final String ADD_READ_ARTICLE = "INSERT INTO readarticle (id_reader, id_article, ranking) VALUES (?,?,?)";
    public static final String GET_READERS_OF_ARTICLE_TYPE = "select * from reader where id in (select id_reader from readarticle where id_article in (select id from article where id_type in (select id from type where description = ?)))";
    public static final String GET_DESCRIPTION_AND_NUMBER_READERS_OF_EACH_ARTICLE = "SELECT description, count(id_reader) FROM type, article, readarticle WHERE type.id = article.id_type AND article.id = readarticle.id_article and article.id = ? GROUP BY description";
    public static final String ADD_SUBSCRIBER = "INSERT INTO subscribe (id_newspaper, id_reader) VALUES (?,?)";
    public static final String DELETE_ARTICLES_BY_NEWSPAPER = "DELETE FROM article WHERE id_newspaper = ?";

    //READARTICLE
    public static final String DELETE_READARTICLE_BY_ID_READER = "DELETE FROM readarticle WHERE id_reader = ?";

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //                                                          PSP
    //Newspaper
    public static final String GET_ONE_NEWSPAPER_BY_ID = "SELECT * FROM newspaper WHERE id = ?";
}
