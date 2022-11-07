package fx.controller.jdbc;

import fx.controller.common.BasePantallaController;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import model.Article;
import service.ServiceArticle;
import service.ServiceReader;
import service.jdbc.ServiceArticleJDBC;
import service.jdbc.ServiceReaderJDBC;

import java.net.URL;
import java.util.ResourceBundle;

public class FXDescriptionAndNumberReadersOfEachArticleController extends BasePantallaController implements Initializable {


    @FXML
    private ListView<String> descriptionAndNumberOfReadersList;
    @FXML
    private ComboBox<Article> articleBox;

    Alert alerta;

    ServiceArticle serviceArticle;

    @Inject
    public FXDescriptionAndNumberReadersOfEachArticleController(ServiceArticleJDBC serviceArticleJDBC) {
        this.serviceArticle = serviceArticleJDBC;
    }

    public void loadArticlesComboBox(){
        articleBox.getItems().clear();
        articleBox.getItems().addAll(serviceArticle.getAllArticle());
    }

    public void searchByArticle(ActionEvent actionEvent){
        try {
            descriptionAndNumberOfReadersList.getItems().clear();
            if (!articleBox.getSelectionModel().isEmpty()) {
                descriptionAndNumberOfReadersList.getItems().addAll(serviceArticle.getDescriptionAndNumberOfReadersOfEachArticle(articleBox.getSelectionModel().getSelectedItem()));
            }else{
                alert("PROBLEM", "You must select an article", Alert.AlertType.INFORMATION);
            }
        }catch (Exception e){
            alert("Error", "Error to search readers", Alert.AlertType.ERROR);
            clean();
        }
    }

    private void alert(String titulo, String texto, Alert.AlertType tipo){
        alerta.setTitle(titulo);
        alerta.setContentText(texto);
        alerta.setAlertType(tipo);
        alerta.showAndWait();
    }

    public void clean(){
        descriptionAndNumberOfReadersList.getItems().clear();
        articleBox.getSelectionModel().clearSelection();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alerta = new Alert(Alert.AlertType.INFORMATION);
        loadArticlesComboBox();
    }


}
