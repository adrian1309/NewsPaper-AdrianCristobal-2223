package fx.controller.jdbc;

import fx.controller.common.BasePantallaController;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import model.Reader;
import service.ServiceNewspaper;
import service.ServiceReader;
import service.jdbc.ServiceNewspaperJDBC;
import service.jdbc.ServiceReaderJDBC;

import java.net.URL;
import java.util.ResourceBundle;

public class FXReadersOfArticleTypeController extends BasePantallaController implements Initializable {

    @FXML
    private ListView<Reader> readerList;
    @FXML
    private ComboBox<String> typeBox;

    Alert alerta;

    ServiceReader serviceReader;

    @Inject
    public FXReadersOfArticleTypeController(ServiceReaderJDBC serviceReader) {
        this.serviceReader = serviceReader;
    }

    public void loadTypesComboBox(){
        typeBox.getItems().clear();
        typeBox.getItems().addAll("economics", "sports", "science");
    }
    public void searchByType(ActionEvent actionEvent) {
        try {
            readerList.getItems().clear();
            if (!typeBox.getSelectionModel().isEmpty()) {

                readerList.getItems().addAll(serviceReader.getReadersOfArticleType(typeBox.getSelectionModel().getSelectedItem()));
            }else{
                alert("PROBLEM", "You must select a type", Alert.AlertType.INFORMATION);
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
        readerList.getItems().clear();
        typeBox.getSelectionModel().clearSelection();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alerta = new Alert(Alert.AlertType.INFORMATION);
        loadTypesComboBox();
    }
}
