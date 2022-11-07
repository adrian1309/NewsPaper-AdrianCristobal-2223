package fx.controller.jdbc;

import fx.controller.common.BasePantallaController;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import model.Newspaper;
import service.ServiceArticle;
import service.ServiceNewspaper;
import service.ServiceReader;
import service.jdbc.ServiceArticleJDBC;
import service.jdbc.ServiceNewspaperJDBC;
import service.jdbc.ServiceReaderJDBC;

import java.net.URL;
import java.util.ResourceBundle;

public class FXNameOfOldestSubscribersOfNewspaperController extends BasePantallaController implements Initializable {

    @FXML
    private ListView<String> nameOldestSubscribersOfNewspaperList;
    @FXML
    private ComboBox<Newspaper> newspaperBox;

    Alert alerta;

    ServiceReader serviceReader;

    ServiceNewspaper serviceNewspaper;

    @Inject
    public FXNameOfOldestSubscribersOfNewspaperController(ServiceReaderJDBC serviceReaderJDBC, ServiceNewspaperJDBC serviceNewspaperJDBC) {
        this.serviceReader = serviceReaderJDBC;
        this.serviceNewspaper = serviceNewspaperJDBC;
    }
    public void loadNewspapersComboBox(){
        newspaperBox.getItems().clear();
        newspaperBox.getItems().addAll(serviceNewspaper.findNewspapers().get());
    }

    public void searchByNewspaper(ActionEvent actionEvent) {
        try {
            nameOldestSubscribersOfNewspaperList.getItems().clear();
            if (!newspaperBox.getSelectionModel().isEmpty()) {
                nameOldestSubscribersOfNewspaperList.getItems().addAll(serviceReader.getNameOldestSubscribersOfNewspaper(newspaperBox.getSelectionModel().getSelectedItem()));
            }else{
                alert("PROBLEM", "You must select a newspaper", Alert.AlertType.INFORMATION);
            }
        }catch (Exception e){
            alert("Error", "Error to search subscribers", Alert.AlertType.ERROR);
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
        nameOldestSubscribersOfNewspaperList.getItems().clear();
        newspaperBox.getSelectionModel().clearSelection();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alerta = new Alert(Alert.AlertType.INFORMATION);
        loadNewspapersComboBox();
    }


}
