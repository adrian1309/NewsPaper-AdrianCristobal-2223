package fx.controller.spring;

import fx.controller.FXMainController;
import fx.controller.common.BasePantallaController;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Newspaper;
import service.ServiceNewspaper;
import service.files.ServiceArticleFile;
import service.spring.ServiceNewspaperSpring;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class FXAddNewspaperSpringController extends BasePantallaController implements Initializable {

    @FXML
    private TextField txtNameNewspaper;
    @FXML
    private ListView<Newspaper> lvNewspaper;

    ServiceNewspaper serviceNewspaper;

    Alert alerta;


    @Inject
    public FXAddNewspaperSpringController(ServiceNewspaperSpring serviceNewspaperSpring) {
        this.serviceNewspaper = serviceNewspaperSpring;

    }


    public void addNewspaper(ActionEvent actionEvent) {
        try{
            Newspaper newspaper = new Newspaper();
            newspaper.setId(-100);
            newspaper.setName_newspaper(txtNameNewspaper.getText());
            newspaper.setRelease_date(LocalDate.now());
            serviceNewspaper.addNewspaper(newspaper);
            clean();
            loadNewspapers();
            alert("NICE","Newspaper added successfully", Alert.AlertType.CONFIRMATION);
        }catch (Exception e){
            alert("ERROR", "Error to add newspaper", Alert.AlertType.ERROR);
        }
    }

    private void loadNewspapers(){
        lvNewspaper.getItems().clear();
        lvNewspaper.getItems().addAll(serviceNewspaper.findNewspapers().get());
    }

    private void clean(){
        txtNameNewspaper.clear();
        lvNewspaper.getItems().clear();
    }

    private void alert(String titulo, String texto, Alert.AlertType tipo){
        alerta.setTitle(titulo);
        alerta.setContentText(texto);
        alerta.setAlertType(tipo);
        alerta.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alerta = new Alert(Alert.AlertType.NONE);
        loadNewspapers();
    }

}
