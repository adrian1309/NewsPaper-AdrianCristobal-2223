package domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
public class Reader {

    private int id;
    private String name_reader;
    private Date birth_reader;

    public Reader(int id, String name_reader, Date birth_reader) {
        this.id = id;
        this.name_reader = name_reader;
        this.birth_reader = birth_reader;
    }

    @Override
    public String toString() {
        return "Name: " + name_reader + " - Birth: " + birth_reader;

    }
}
