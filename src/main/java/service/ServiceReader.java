package service;

import model.Reader;

import java.util.List;

public interface ServiceReader {

    List<Reader> getAllReaders();

    void addReader(Reader reader);

    void deleteReader(Reader selectedItem);

    void updateReader(Reader reader);
}
