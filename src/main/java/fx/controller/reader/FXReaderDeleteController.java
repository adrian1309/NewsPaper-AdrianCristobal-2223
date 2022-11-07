package fx.controller.reader;

import fx.controller.FXMainController;
import fx.controller.common.BasePantallaController;
import jakarta.inject.Inject;
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

public class FXReaderDeleteController extends BasePantallaController implements Initializable {
    @FXML
    private ListView<Reader> readerList;
    private ServiceReader serviceReader;
    private ServiceSubscribe serviceSubscribe;
    private Alert alerta;
    private FXMainController fxMainController;

    @Inject
    public FXReaderDeleteController(ServiceReaderJDBC serviceReader, ServiceSubscribeJDBC serviceSubscribe) {
        this.serviceReader = serviceReader;
        this.serviceSubscribe = serviceSubscribe;
    }

    public void setMainController(FXMainController fxMainController) {
        this.fxMainController = fxMainController;
    }

    public void loadReaders() {
        readerList.getItems().clear();
        readerList.getItems().addAll(serviceReader.getAllReaders().get());
    }

    public void deleteReader() {//el fallo esta en el segundo if (creo) cierra todoo bien
        try {
            if (readerList.getSelectionModel().getSelectedItem() != null) {
                List<Subscribe> subscribeList = serviceSubscribe.getAllSubscribes().stream().filter(s -> s.getId_newspaper() == readerList.getSelectionModel().getSelectedItem().getId()).collect(Collectors.toList());
                if (subscribeList.size() == 0) {
                    serviceReader.deleteReader(readerList.getSelectionModel().getSelectedItem());
                    loadReaders();
                    alert("GOOD", "Reader deleted", Alert.AlertType.INFORMATION);
                } else {
                    Alert alertDialog = new Alert(Alert.AlertType.CONFIRMATION);
                    alertDialog.setTitle("Confirmation");
                    alertDialog.setHeaderText("The reader has subscribers associated. Do you want to delete them?");
                    alertDialog.setContentText("If you delete the reader, the subscribers associated are going to be delete to. \n" +
                            "Are you sure?");

                    Optional<ButtonType> result = alertDialog.showAndWait();
                    if (result.get() == ButtonType.OK){
                        serviceReader.deleteReader(readerList.getSelectionModel().getSelectedItem());
                        loadReaders();
                        alert("GOOD", "Reader and subscribers associated, deleted", Alert.AlertType.INFORMATION);
                    } else {
                        alertDialog.close();
                    }
                }
            }else{
                alert("Ups!", "You have to select a reader to delete it", Alert.AlertType.INFORMATION);
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
        //serviceReader = new ServiceReaderJDBC();
        //serviceSubscribe = new ServiceSubscribeJDBC();
        alerta = new Alert(Alert.AlertType.INFORMATION);
        loadReaders();
    }
}
