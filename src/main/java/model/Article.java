package model;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
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
        return "Name: " + name_article + " - Type: " + id_type + " - Newspaper: " + id_newspaper;
    }

    public String toStringFile(){
        return id + ";" + name_article + ";" + id_type + ";" + id_newspaper;
    }
}
