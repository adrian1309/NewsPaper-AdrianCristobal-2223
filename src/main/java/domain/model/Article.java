package domain.model;

import jakarta.enterprise.inject.spi.configurator.BeanConfigurator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Article {
    private int id;
    private String name_article;
    private int id_type;
    private int id_newspaper;

    public Article (String linea){
        String[] valor = linea.split(";");
        this.id = Integer.parseInt(valor[0]);
        this.name_article = valor[1];
        this.id_type = Integer.parseInt(valor[2]);
        this.id_newspaper = Integer.parseInt(valor[3]);
    }

    @Override
    public String toString() {
        return name_article;
    }

    public String toStringFile(){
        return id + ";" + name_article + ";" + id_type + ";" + id_newspaper;
    }
}
