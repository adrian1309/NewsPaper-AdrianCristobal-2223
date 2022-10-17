package fx.controller.xml;

import fx.controller.FXMainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import model.xml.ReaderXML;
import service.files.ServiceReaderFile;

import java.net.URL;
import java.util.ResourceBundle;

public class FXReaderDeleteXMLController implements Initializable {

    private ServiceReaderFile serviceReader;
    @FXML
    private ListView<ReaderXML> readerList;

    private FXMainController mainController;

    private Alert alerta;

    public void setMainController(FXMainController fxMainController){
        this.mainController = fxMainController;
    }

    public void deleteReader() {
        try {
            if (readerList.getSelectionModel().getSelectedItem() != null) {
                    ReaderXML reader = readerList.getSelectionModel().getSelectedItem();
                    try {
                        serviceReader.deleteReader(reader);
                        readerList.getItems().remove(reader);
                        readerList.getSelectionModel().clearSelection();
                        readerList.refresh();
                        alert("NICE", "Reader deleted", Alert.AlertType.CONFIRMATION);
                    } catch (Exception e) {
                        alert("ERROR", "Reader not delete. Try again", Alert.AlertType.ERROR);
                    }
                }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void loadReaders(){
        readerList.getItems().clear();
        readerList.getItems().addAll(serviceReader.getAllReaders().getReaders());
        readerList.refresh();
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
        serviceReader = new ServiceReaderFile();
        loadReaders();
    }
}
