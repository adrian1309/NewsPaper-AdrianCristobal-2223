package fx.controller.reader;

import fx.controller.FXMainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Reader;
import service.ServiceReader;
import service.jdbc.ServiceReaderJDBC;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class FXReaderCreateController implements Initializable {
    @FXML
    private TextField tfName;
    @FXML
    private DatePicker dpBirthDate;
    private ServiceReader serviceReader;
    private Alert alerta;
    private FXMainController fxMainController;

    public void setMainController(FXMainController fxMainController) {
        this.fxMainController = fxMainController;
    }
    public void addReader() {
        try {
            Reader reader = new Reader();
            reader.setName_reader(tfName.getText());
            reader.setBirth_reader(Date.valueOf(dpBirthDate.getValue()));
            serviceReader.addReader(reader);
            clean();
            alert("NICE","Reader added successfully", Alert.AlertType.CONFIRMATION);
        }catch (Exception e){
            alert("Error", "Error to add reader", Alert.AlertType.ERROR);
        }
    }

    private void clean(){
        tfName.clear();
        dpBirthDate.getEditor().clear();
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
        alerta = new Alert(Alert.AlertType.INFORMATION);
    }

}
