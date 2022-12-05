package domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reader {

    private int id;
    private String name_reader;
    private LocalDate birth_reader;

    public Reader(int id, String name_reader) {
        this.id = id;
        this.name_reader = name_reader;
    }

}
