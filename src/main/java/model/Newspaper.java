package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.sql.Date;
import java.time.LocalDate;


@Data
@ToString
@AllArgsConstructor
public class Newspaper {

    private int id;
    private String name_newspaper;
    private LocalDate realise_date;

    public Newspaper (String linea){
        String[] valor = linea.split(";");
        this.id = Integer.parseInt(valor[0]);
        this.name_newspaper = valor[1];
        this.realise_date = Date.valueOf(valor[2]).toLocalDate();

    }

    public String toStringFile(){
        return id + ";" + name_newspaper + ";" + realise_date;
    }
}
