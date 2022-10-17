package model.xml;


import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "readers")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReadersXML {

    @XmlElement(name = "reader")
    private List<ReaderXML> readers;

    public ReadersXML() {
        readers=new ArrayList<>();}

    public List<ReaderXML> getReaders() {
        return readers;
    }

    public void setReaders(List<ReaderXML> listReaders) {
        this.readers = listReaders;
    }

    public void addReader(ReaderXML r) {
        readers.add(r);
    }

    public void removeReader(ReaderXML r) {
        readers.remove(r);
    }

    @Override
    public String toString() {
        return "Readers{" + "Reader=" + readers + '}' + '\n';
    }
}
