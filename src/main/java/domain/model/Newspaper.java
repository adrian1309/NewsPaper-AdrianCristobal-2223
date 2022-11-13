package domain.model;

import lombok.*;

import java.sql.Date;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Newspaper {

    private int id;
    private String name_newspaper;
    private LocalDate release_date;

    public Newspaper(int id, String name_newspaper) {
        this.id = id;
        this.name_newspaper = name_newspaper;
    }

}
