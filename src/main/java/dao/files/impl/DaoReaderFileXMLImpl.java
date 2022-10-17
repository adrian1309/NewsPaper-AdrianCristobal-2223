package dao.files.impl;


import config.file.ConfigProperties;
import dao.files.IF.DaoReaderIF;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import model.xml.ReadArticlesXML;
import model.xml.ReaderXML;
import model.xml.ReadersXML;

public class DaoReaderFileXMLImpl implements DaoReaderIF {

        @Override
        public ReadersXML getAllReaders() {
                ReadersXML listReaders = null;

                try {
                        JAXBContext context = JAXBContext.newInstance(ReadersXML.class);
                        Marshaller marshaller = context.createMarshaller();
                        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                        Unmarshaller unmarshaller = context.createUnmarshaller();
                        Path xmlFile = Paths
                                .get(ConfigProperties.getInstance().getProperty("ReaderXML"));

                        // Read the XML document from the file
                        listReaders = (ReadersXML) unmarshaller.unmarshal(Files.newInputStream(xmlFile));

                } catch (Exception e) {
                        e.printStackTrace();
                }

                return listReaders;
        }

        @Override
        public List<ReaderXML> suscribeReaderByNewspaper(int idNewspaper) {

                List<ReaderXML> listReadersByNewspaper = new ArrayList<>();

                try {
                        JAXBContext context = JAXBContext.newInstance(ReadersXML.class);
                        Marshaller marshaller = context.createMarshaller();
                        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                        Unmarshaller unmarshaller = context.createUnmarshaller();
                        Path xmlFile = Paths
                                .get(ConfigProperties.getInstance().getProperty("ReaderXML"));

                        // Read the XML document from the file
                        ReadersXML readersXML = (ReadersXML) unmarshaller.unmarshal(Files.newInputStream(xmlFile));
                        List<ReaderXML> listReaders = new ArrayList<>();
                        listReaders.addAll(readersXML.getReaders());
                        for (ReaderXML r : listReaders) {
                                if (r.getSubscription().stream().anyMatch(s -> s.getId_newspaper() == idNewspaper)) {
                                        listReadersByNewspaper.add(r);
                                }
                        }

                } catch (Exception e) {
                        e.printStackTrace();
                }

                return listReadersByNewspaper;
        }

        @Override
        public List<ReaderXML> getReadersByArticleByType(List<Integer> idsArticles) {

                    List<ReaderXML> listReadersByArticleByType = new ArrayList<>();

                    try {
                            JAXBContext context = JAXBContext.newInstance(ReadersXML.class);
                            Marshaller marshaller = context.createMarshaller();
                            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                            Unmarshaller unmarshaller = context.createUnmarshaller();
                            Path xmlFile = Paths
                                    .get(ConfigProperties.getInstance().getProperty("ReaderXML"));

                            // Read the XML document from the file
                            ReadersXML readersXML = (ReadersXML) unmarshaller.unmarshal(Files.newInputStream(xmlFile));
                            List<ReaderXML> listReaders = new ArrayList<>();
                            listReaders.addAll(readersXML.getReaders());
                            for (ReaderXML r : listReaders) {
                                    for (int idArticle : idsArticles) {
                                            if (r.getReadArticle().stream().anyMatch(ra -> ra.getId_article() == idArticle)) {
                                                    listReadersByArticleByType.add(r);
                                            }
                                    }
                            }

                    } catch (Exception e) {
                            e.printStackTrace();
                    }

                    return listReadersByArticleByType;
        }

        @Override
        public void addReadArticle(String name,int idReader, int idArticle, int rating) {

                    try {
                            JAXBContext context = JAXBContext.newInstance(ReadersXML.class);
                            Marshaller marshaller = context.createMarshaller();
                            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                            Unmarshaller unmarshaller = context.createUnmarshaller();
                            Path xmlFile = Paths
                                    .get(ConfigProperties.getInstance().getProperty("ReaderXML"));

                            ReadArticlesXML readArticleXML = new ReadArticlesXML();

                            ReadersXML readersXML = (ReadersXML) unmarshaller.unmarshal(Files.newInputStream(xmlFile));
                            List<ReaderXML> listReaders = new ArrayList<>();
                            listReaders.addAll(readersXML.getReaders());

                            ReaderXML readerXMLAntiguo = new ReaderXML();
                            ReaderXML readerXMLNuevo = new ReaderXML();

                            for (ReaderXML r : listReaders) {
                                    if (r.getName().equals(name)) {
                                            int idReadArticle = r.getReadArticle().get(r.getReadArticle().size() - 1).getId()+1;
                                            readArticleXML.setId(idReadArticle);
                                            readArticleXML.setId_reader(idReader);
                                            readArticleXML.setId_article(idArticle);
                                            readArticleXML.setRating(rating);
                                            readerXMLAntiguo = r;

                                            r.getReadArticle().add(readArticleXML);
                                            readerXMLNuevo = r;
                                    }
                            }

                            try {
                                    readersXML.removeReader(readerXMLAntiguo);
                                    readersXML.addReader(readerXMLNuevo);
                                    marshaller.marshal(readersXML, Files.newOutputStream(xmlFile));
                            }catch (Exception e) {
                                    e.printStackTrace();
                            }


                    } catch (Exception e) {
                            e.printStackTrace();
                    }

        }

        @Override
        public void deleteReader(ReaderXML reader) {
                try {
                        JAXBContext context = JAXBContext.newInstance(ReadersXML.class);
                        Marshaller marshaller = context.createMarshaller();
                        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                        Unmarshaller unmarshaller = context.createUnmarshaller();
                        Path xmlFile = Paths
                                .get(ConfigProperties.getInstance().getProperty("ReaderXML"));

                        ReadersXML readersXML = (ReadersXML) unmarshaller.unmarshal(Files.newInputStream(xmlFile));
                        List<ReaderXML> listReaders = new ArrayList<>();
                        listReaders.addAll(readersXML.getReaders());


                        for (ReaderXML r : listReaders) {
                                if (Objects.equals(r.getName(), reader.getName())) {
                                        readersXML.removeReader(r);
                                }
                        }
                        marshaller.marshal(readersXML, Files.newOutputStream(xmlFile));
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
}
