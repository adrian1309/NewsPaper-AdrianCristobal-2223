package fx.controller.article;

import fx.controller.FXMainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import service.files.ServiceArticleFile;
import service.files.ServiceTypeFile;

import java.net.URL;
import java.util.*;

public class FXArticleByTypeController implements Initializable {
    @FXML
    public ListView<String> articleList;
    @FXML
    public ComboBox<String> typeBox;

    private ServiceArticleFile serviceArticle;

    private ServiceTypeFile serviceType;

    Alert alerta;

    private FXMainController mainController;

    public void setMainController(FXMainController fxMainController){
        this.mainController = fxMainController;
    }



    public void loadItems() {
        List<String> typesList = new ArrayList<String>();
        typesList.add("economics");
        typesList.add("sports");
        typesList.add("science");
        typeBox.getItems().addAll(typesList);
    }

    public void clean(){
        articleList.getItems().clear();
        typeBox.getSelectionModel().clearSelection();
    }

    public void searchByType(ActionEvent actionEvent) {
        try{
            String type = typeBox.getSelectionModel().getSelectedItem();
            int valorType = 0;
            switch (type) {
                case "economics" -> valorType = 1;
                case "sports" -> valorType = 2;
                case "science" -> valorType = 3;
                default -> {
                }
            }
            List<String> listArticle = serviceArticle.getArticleByTypeString(valorType);
            if (!listArticle.isEmpty()){
                clean();
                articleList.getItems().addAll(listArticle);
            }else{
                clean();
                alert("INFO", "No existen articles para ese type", Alert.AlertType.INFORMATION);
            }
        }catch (Exception e){
            clean();
            alert("ERROR", "Selecciona un type", Alert.AlertType.INFORMATION);
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
        serviceType = new ServiceTypeFile();
        alerta = new Alert(Alert.AlertType.INFORMATION);
        loadItems();
    }
}
