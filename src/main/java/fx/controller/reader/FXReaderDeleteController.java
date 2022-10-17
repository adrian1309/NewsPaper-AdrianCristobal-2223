package fx.controller.reader;

import fx.controller.FXMainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import model.Article;
import model.Reader;
import model.Subscribe;
import service.ServiceReader;
import service.ServiceSubscribe;
import service.jdbc.ServiceReaderJDBC;
import service.jdbc.ServiceSubscribeJDBC;

import java.net.PortUnreachableException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class FXReaderDeleteController implements Initializable {
    @FXML
    private ListView<Reader> readerList;
    private ServiceReader serviceReader;
    private ServiceSubscribe serviceSubscribe;
    private Alert alerta;
    private FXMainController fxMainController;

    public void setMainController(FXMainController fxMainController) {
        this.fxMainController = fxMainController;
    }

    public void loadReaders() {
        readerList.getItems().clear();
        readerList.getItems().addAll(serviceReader.getAllReaders());
    }

    public void deleteReader() {//el fallo esta en el segundo if (creo) cierra todoo bien
        try {
            if (readerList.getSelectionModel().getSelectedItem() != null) {
                if (serviceSubscribe.getAllSubscribes().stream().noneMatch(subscribe -> subscribe.getId_reader() == readerList.getSelectionModel().getSelectedItem().getId())) {
                    serviceReader.deleteReader(readerList.getSelectionModel().getSelectedItem());
                    readerList.getItems().remove(readerList.getSelectionModel().getSelectedItem());
                    loadReaders();
                } else {
                    alerta.setTitle("¡CUIDADO!");
                    alerta.setAlertType(Alert.AlertType.WARNING);
                    alerta.setContentText("If you delete the Reader, the subscribes associated are going to be delete to. \n" +
                            "¿Are you sure to continue?");
                    Optional<ButtonType> action = alerta.showAndWait();
                    if (action.isPresent() && (action.get() == ButtonType.OK)) {
                        List<Subscribe> subscribeList = serviceSubscribe.getAllSubscribes().stream().filter(subscribe -> subscribe.getId_reader() == readerList.getSelectionModel().getSelectedItem().getId()).collect(Collectors.toList());
                        for (Subscribe subscribe : subscribeList) {
                            serviceSubscribe.deleteSubscribe(subscribe);
                        }
                        serviceReader.deleteReader(readerList.getSelectionModel().getSelectedItem());
                        alert("NICE", "Reader and their subscribes associated, deleted", Alert.AlertType.CONFIRMATION);
                        loadReaders();
                    }else {
                        alert("Ups", "Reader not deleted", Alert.AlertType.CONFIRMATION);
                        loadReaders();
                        //readerList.getSelectionModel().clearSelection();

                    }
                }
            } else {
                alert("Error", "You must select a reader", Alert.AlertType.INFORMATION);
            }
        } catch (Exception e) {
            alert("Error", "Failed to delete reader", Alert.AlertType.ERROR);
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
        serviceReader = new ServiceReaderJDBC();
        serviceSubscribe = new ServiceSubscribeJDBC();
        alerta = new Alert(Alert.AlertType.INFORMATION);
        loadReaders();
    }
}
