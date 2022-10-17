package utils;

public class QueryStrings {
    //READER
    public static final String GET_ALL_READERS = "SELECT * FROM reader";
    public static final String ADD_READER = "INSERT INTO reader (name_reader, birth_reader) VALUES (?,?)";
    public static final String DELETE_READER = "DELETE FROM reader WHERE id = ?";
    public static final String UPDATE_READER = "UPDATE reader SET name_reader = ?, birth_reader = ? WHERE id = ?";

    //SUNSCRIBE
    public static final String GET_ALL_SUBSCRIBES = "SELECT * FROM subscribe";
    public static final String DELETE_SUBSCRIBE = "DELETE FROM subscribe WHERE id_newspaper = ? AND id_reader = ?";
}
