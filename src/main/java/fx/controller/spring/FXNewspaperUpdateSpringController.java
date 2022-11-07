package fx.controller.spring;

import fx.controller.common.BasePantallaController;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Newspaper;
import model.Reader;
import service.ServiceNewspaper;
import service.spring.ServiceNewspaperSpring;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class FXNewspaperUpdateSpringController extends BasePantallaController implements Initializable {
    @FXML
    private TextField tfName;
    @FXML
    private DatePicker dpReleaseDate;
    @FXML
    private ListView<Newspaper> lvNewspaper;
    private Alert alerta;
    private ServiceNewspaper serviceNewspaper;

    @Inject
    public FXNewspaperUpdateSpringController(ServiceNewspaperSpring serviceNewspaperSpring) {
        this.serviceNewspaper = serviceNewspaperSpring;
    }

    public void newspaperSelected(MouseEvent mouseEvent) {
        Newspaper newspaper = lvNewspaper.getSelectionModel().getSelectedItem();
        tfName.setText(newspaper.getName_newspaper());
        dpReleaseDate.setValue(newspaper.getRelease_date());
    }

    public void btnUpdate(ActionEvent actionEvent) {
        try{
            String name = tfName.getText();
            LocalDate date = dpReleaseDate.getValue();
            Newspaper newspaper = lvNewspaper.getSelectionModel().getSelectedItem();
            newspaper.setName_newspaper(name);
            newspaper.setRelease_date(date);
            serviceNewspaper.updateNewspaper(newspaper);
            loadNewspaper();
            clean();
        }catch (Exception e){
            alert("Error", "Error to update reader", Alert.AlertType.ERROR);
        }
    }

    public void loadNewspaper() {
        lvNewspaper.getItems().clear();
        lvNewspaper.getItems().addAll(serviceNewspaper.findNewspapers().get());
    }

    public void clean() {
        tfName.setText("");
        dpReleaseDate.setValue(null);
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
        //serviceReader = new ServiceReaderJDBC();
        alerta = new Alert(Alert.AlertType.INFORMATION);
        loadNewspaper();
    }

}
