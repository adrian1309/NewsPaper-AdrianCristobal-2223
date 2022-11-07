package fx.controller.jdbc;

import fx.controller.common.BasePantallaController;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import model.Article;
import model.Newspaper;
import model.Reader;
import service.ServiceNewspaper;
import service.ServiceReader;
import service.jdbc.ServiceNewspaperJDBC;
import service.jdbc.ServiceReaderJDBC;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FXReaderSubscribingToNewspaperController extends BasePantallaController implements Initializable {
    @FXML
    private ListView<Newspaper> newspaperBox;
    private ServiceNewspaper serviceNewspaper;
    private ServiceReader serviceReader;

    private Alert alerta;

    @Inject
    public FXReaderSubscribingToNewspaperController(ServiceNewspaperJDBC serviceNewspaperJDBC, ServiceReaderJDBC serviceReader) {
        this.serviceNewspaper = serviceNewspaperJDBC;
        this.serviceReader = serviceReader;
    }



    public void loadNewspapers() {
        newspaperBox.getItems().clear();
        List<Newspaper> newspaperList = serviceNewspaper.findNewspapers().get();
        newspaperBox.getItems().addAll(newspaperList);
    }

    public void addSubscriber(ActionEvent actionEvent) {
        try {
            Newspaper newspaper = newspaperBox.getSelectionModel().getSelectedItem();
            int idNewspaper = newspaper.getId();
            int idReader = 0;
            List<Reader> readerList = serviceReader.getAllReaders().get();
            for (Reader reader : readerList) {
                if (reader.getName_reader().equals(getPrincipalController().userName)) {
                    idReader = reader.getId();
                }
            }
            serviceReader.addSubscriber(idNewspaper, idReader);
            alert("NICE", "Subscriber added", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            alert("Error", "Error adding subscriber", Alert.AlertType.ERROR);
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
        alerta = new Alert(Alert.AlertType.INFORMATION);
        loadNewspapers();
    }

}

