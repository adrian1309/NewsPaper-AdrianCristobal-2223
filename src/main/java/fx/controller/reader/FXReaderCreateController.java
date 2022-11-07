package fx.controller.reader;

import fx.controller.FXMainController;
import fx.controller.common.BasePantallaController;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import lombok.extern.log4j.Log4j2;
import model.Reader;
import service.ServiceReader;
import service.jdbc.ServiceReaderJDBC;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

@Log4j2
public class FXReaderCreateController extends BasePantallaController implements Initializable {
    @FXML
    private TextField tfName;
    @FXML
    private DatePicker dpBirthDate;
    private ServiceReader serviceReader;
    private Alert alerta;
    private FXMainController fxMainController;

    @Inject
    public FXReaderCreateController(ServiceReader serviceReader){
        this.serviceReader = serviceReader;
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
        //serviceReader = new ServiceReaderJDBC();
        alerta = new Alert(Alert.AlertType.INFORMATION);
    }

}
