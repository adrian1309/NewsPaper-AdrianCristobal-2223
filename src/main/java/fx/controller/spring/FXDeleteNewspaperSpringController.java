package fx.controller.spring;

import fx.controller.common.BasePantallaController;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import model.Article;
import model.Newspaper;
import service.ServiceArticle;
import service.ServiceNewspaper;
import service.jdbc.ServiceArticleJDBC;
import service.spring.ServiceNewspaperSpring;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class FXDeleteNewspaperSpringController extends BasePantallaController implements Initializable {
    @FXML
    private ListView<Newspaper> newspaperList;
    private ServiceNewspaper serviceNewspaper;
    private ServiceArticle serviceArticle;
    private Alert alerta;

    @Inject
    public FXDeleteNewspaperSpringController(ServiceNewspaperSpring serviceNewspaperSpring, ServiceArticleJDBC serviceArticleJDBC) {
        this.serviceNewspaper = serviceNewspaperSpring;
        this.serviceArticle = serviceArticleJDBC;
    }

    public void loadNewspaper() {
        newspaperList.getItems().clear();
        newspaperList.getItems().addAll(serviceNewspaper.findNewspapers().get());
    }


    public void deleteNewspaper(ActionEvent actionEvent) {
        try {
            if (newspaperList.getSelectionModel().getSelectedItem() != null) {
                List<Article> articleList = serviceArticle.getAllArticle().stream().filter(a -> a.getId_newspaper() == newspaperList.getSelectionModel().getSelectedItem().getId()).collect(Collectors.toList());
                if (articleList.size() == 0) {
                    serviceNewspaper.deleteNewspaper(newspaperList.getSelectionModel().getSelectedItem());
                    loadNewspaper();
                    alert("GOOD", "Newspaper deleted", Alert.AlertType.INFORMATION);
                } else {
                    Alert alertDialog = new Alert(Alert.AlertType.CONFIRMATION);
                    alertDialog.setTitle("Confirmation");
                    alertDialog.setHeaderText("The newspaper has articles associated. Do you want to delete them?");
                    alertDialog.setContentText("If you delete the newspaper, the articles associated are going to be delete to. \n" +
                            "Are you sure?");

                    Optional<ButtonType> result = alertDialog.showAndWait();
                    if (result.get() == ButtonType.OK){
                        serviceNewspaper.deleteNewspaper(newspaperList.getSelectionModel().getSelectedItem());
                        loadNewspaper();
                        alert("GOOD", "Newspaper and articles associated, deleted", Alert.AlertType.INFORMATION);
                    } else {
                        alertDialog.close();
                    }
                }
            }else{
                alert("Ups!", "You have to select a newspaper to delete it", Alert.AlertType.INFORMATION);
            }
        } catch (Exception e) {
            alert("Error", "Failed to delete Newspaper", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void alert(String titulo, String texto, Alert.AlertType tipo){
        alerta.setTitle(titulo);
        alerta.setContentText(texto);
        alerta.setAlertType(tipo);
        alerta.showAndWait();
        alerta.getButtonTypes();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alerta = new Alert(Alert.AlertType.INFORMATION);
        loadNewspaper();
    }

}
