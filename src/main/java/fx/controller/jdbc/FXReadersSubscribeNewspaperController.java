package fx.controller.jdbc;

import fx.controller.FXMainController;
import fx.controller.common.BasePantallaController;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import model.Newspaper;
import model.Reader;
import service.ServiceNewspaper;
import service.ServiceReader;
import service.jdbc.ServiceNewspaperJDBC;
import service.jdbc.ServiceReaderJDBC;

import java.net.URL;
import java.util.ResourceBundle;

public class FXReadersSubscribeNewspaperController extends BasePantallaController implements Initializable {

    @FXML
    private ComboBox<Newspaper> newspaperBox;
    @FXML
    private ListView<Reader> readerList;

    Alert alerta;

    ServiceReader serviceReader;
    ServiceNewspaper serviceNewspaper;

    @Inject
    public FXReadersSubscribeNewspaperController(ServiceReaderJDBC serviceReader, ServiceNewspaperJDBC serviceNewspaper) {
        this.serviceReader = serviceReader;
        this.serviceNewspaper = serviceNewspaper;
    }

    public void searchByNewspaper() {
        try {
            readerList.getItems().clear();
            if (!newspaperBox.getSelectionModel().isEmpty()) {
                readerList.getItems().addAll(serviceReader.getReadersSubscribeNewspaper(newspaperBox.getSelectionModel().getSelectedItem()));
            }else{
                alert("PROBLEM", "You must select a newspaper", Alert.AlertType.INFORMATION);
            }
        }catch (Exception e){
            alert("Error", "Error to search readers", Alert.AlertType.ERROR);
            clean();
        }
    }

    private void clean(){
        readerList.getItems().clear();
        newspaperBox.getEditor().clear();
    }

    private void alert(String titulo, String texto, Alert.AlertType tipo){
        alerta.setTitle(titulo);
        alerta.setContentText(texto);
        alerta.setAlertType(tipo);
        alerta.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alerta = new Alert(Alert.AlertType.INFORMATION);
        newspaperBox.getItems().addAll(serviceNewspaper.findNewspapers().get());
    }

}
