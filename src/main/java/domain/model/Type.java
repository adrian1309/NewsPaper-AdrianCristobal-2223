package domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor

public class Type {

    private int id;
    private String description;

    @Override
    public String toString() {
        return description;
    }
}
