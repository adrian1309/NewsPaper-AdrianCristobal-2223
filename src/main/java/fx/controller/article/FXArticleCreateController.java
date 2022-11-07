package fx.controller.article;

import fx.controller.FXMainController;
import fx.controller.common.BasePantallaController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Article;
import service.files.ServiceArticleFile;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FXArticleCreateController extends BasePantallaController implements Initializable {
    @FXML
    public TextField txtNameArticle;
    @FXML
    public ComboBox<String> newspaperBox;
    @FXML
    public ComboBox<String> typeBox;

    ServiceArticleFile serviceArticle;

    Alert alerta;
    private FXMainController mainController;

    public void setMainController(FXMainController fxMainController) {
        this.mainController = fxMainController;
    }

    public void loadComboboxs() {
        List<String> newspaperList = new ArrayList<String>();
        newspaperList.add("El Pais");
        newspaperList.add("El Mundo");
        newspaperList.add("El Periodico");
        newspaperBox.getItems().addAll(newspaperList);

        List<String> typeList = new ArrayList<String>();
        typeList.add("economics");
        typeList.add("sports");
        typeList.add("science");
        typeBox.getItems().addAll(typeList);
    }

    public void clean(){
        txtNameArticle.setText("");
        newspaperBox.setValue("");
        typeBox.setValue("");
    }
    public void addArticle(ActionEvent actionEvent) {
        try {
            String nameArticle = txtNameArticle.getText();
            String newspaper = newspaperBox.getSelectionModel().getSelectedItem().toString();
            int valorNewspaper = 0;
            switch (newspaper) {
                case "El Pais" -> valorNewspaper = 1;
                case "El Mundo" -> valorNewspaper = 2;
                case "El Periodico" -> valorNewspaper = 3;
                default -> {
                }
            }
            String type = typeBox.getSelectionModel().getSelectedItem().toString();
            int valorType = 0;
            switch (type) {
                case "economics" -> valorType = 1;
                case "sports" -> valorType = 2;
                case "science" -> valorType = 3;
                default -> {
                }
            }
            if (nameArticle.isEmpty() || newspaper.isEmpty() || type.isEmpty()) {
                clean();
                alert("ERROR", "You must fill all the fields", Alert.AlertType.INFORMATION);
                clean();
            } else {
                clean();
                Article article = new Article(5,nameArticle, valorNewspaper, valorType);
                serviceArticle = new ServiceArticleFile();
                serviceArticle.addArticle(article);
                alert("Information", "Article created successfully", Alert.AlertType.INFORMATION);
            }
        }catch (Exception e){
            clean();
            alert("ERROR", "Incorrect data", Alert.AlertType.INFORMATION);
            e.printStackTrace();
        }
    }

    private void alert(String titulo, String texto, Alert.AlertType tipo){
        alerta.setTitle(titulo);
        alerta.setContentText(texto);
        alerta.setAlertType(tipo);
        alerta.showAndWait();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        serviceArticle = new ServiceArticleFile();
        alerta = new Alert(Alert.AlertType.INFORMATION);
    }
}
