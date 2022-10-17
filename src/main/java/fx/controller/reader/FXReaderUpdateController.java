package fx.controller.reader;

import fx.controller.FXMainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Reader;
import service.ServiceReader;
import service.jdbc.ServiceReaderJDBC;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class FXReaderUpdateController implements Initializable {
    @FXML
    private TextField tfName;
    @FXML
    private DatePicker dpBirthDate;
    @FXML
    private ListView<Reader> lvReader;

    ServiceReader serviceReader;

    Alert alerta;
    private FXMainController fxMainController;

    public void setMainController(FXMainController fxMainController) {
        this.fxMainController = fxMainController;
    }

    public void loadReader() {
        lvReader.getItems().clear();
        lvReader.getItems().addAll(serviceReader.getAllReaders());
    }

    public void readerSelected() {
        Reader reader = lvReader.getSelectionModel().getSelectedItem();
        tfName.setText(reader.getName_reader());
        LocalDate date = convertToLocalDateViaSqlDate((Date) reader.getBirth_reader());
        dpBirthDate.setValue(date);
    }

    public void btnUpdate() {
        try{
            String name = tfName.getText();
            Date date = Date.valueOf(dpBirthDate.getValue());
            Reader reader = lvReader.getSelectionModel().getSelectedItem();
            reader.setName_reader(name);
            reader.setBirth_reader(date);
            serviceReader.updateReader(reader);
            loadReader();
            clean();
        }catch (Exception e){
            alert("Error", "Error to update reader", Alert.AlertType.ERROR);
        }
    }

    public void clean() {
        tfName.setText("");
        dpBirthDate.setValue(null);
    }

    private void alert(String titulo, String texto, Alert.AlertType tipo){
        alerta.setTitle(titulo);
        alerta.setContentText(texto);
        alerta.setAlertType(tipo);
        alerta.showAndWait();
        clean();
    }

    public LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        serviceReader = new ServiceReaderJDBC();
        alerta = new Alert(Alert.AlertType.INFORMATION);
        loadReader();
    }
}
