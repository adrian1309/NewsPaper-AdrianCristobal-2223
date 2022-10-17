package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class Subscribe {
    private int id_newspaper;
    private int id_reader;
    private Date start_date;
    private Date end_date;
}
