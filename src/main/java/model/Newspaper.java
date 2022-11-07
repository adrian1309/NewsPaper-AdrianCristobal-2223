package model;

import lombok.*;

import java.sql.Date;
import java.time.LocalDate;


@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Newspaper {

    private int id;
    private String name_newspaper;
    private LocalDate release_date;


    public Newspaper (String linea){
        String[] valor = linea.split(";");
        this.id = Integer.parseInt(valor[0]);
        this.name_newspaper = valor[1];
        this.release_date = Date.valueOf(valor[2]).toLocalDate();

    }

    @Override
    public String toString() {
        return name_newspaper;
    }
}
