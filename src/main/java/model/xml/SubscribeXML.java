package model.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@XmlRootElement(name = "subscription")
@XmlType(propOrder = { "id", "id_reader", "id_newspaper", "signingDate", "cancellationDate" })
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@ToString
public class SubscribeXML {

    private Integer id;
    private Integer id_reader;
    private Integer id_newspaper;

    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate signingDate;

    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate cancellationDate;


}
