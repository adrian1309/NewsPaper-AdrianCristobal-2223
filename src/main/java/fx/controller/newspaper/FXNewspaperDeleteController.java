package fx.controller.newspaper;

import fx.controller.FXMainController;
import fx.controller.common.BasePantallaController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import model.Article;
import model.Newspaper;
import service.files.ServiceArticleFile;
import service.files.ServiceNewspaperFile;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class FXNewspaperDeleteController extends BasePantallaController implements Initializable {

    @FXML
    public ListView<Newspaper> newspaperList;
    private FXMainController mainController;

    ServiceNewspaperFile serviceNewspaper;

    ServiceArticleFile serviceArticle;
    Alert alerta;

    public void setMainController(FXMainController fxMainController){
        this.mainController = fxMainController;
    }

    public void loadNewspaperList(){
        newspaperList.getItems().clear();
        newspaperList.getItems().addAll(serviceNewspaper.getAllNewspaper());
        newspaperList.refresh();
    }

    public void deleteNewspaper() {
        try {
            if (newspaperList.getSelectionModel().getSelectedItem() != null) {
                if (serviceArticle.getAllArticleByNewspaper(newspaperList.getSelectionModel().getSelectedItem()).size() == 0) {
                    serviceNewspaper.deleteNewspaper(newspaperList.getSelectionModel().getSelectedItem());
                    newspaperList.getItems().remove(newspaperList.getSelectionModel().getSelectedItem());
                    loadNewspaperList();
                } else {
                    alerta.setTitle("¡CUIDADO!");
                    alerta.setContentText("If you delete the Newspaper, the articles associated are going to be delete to. \n" +
                            "¿Are you sure to continue?");
                    Optional<ButtonType> action = alerta.showAndWait();
                    if (action.get() == ButtonType.OK) {
                        if (serviceNewspaper.deleteNewspaper(newspaperList.getSelectionModel().getSelectedItem())) {
                            try{
                               List<Article> articleList = serviceArticle.getAllArticleByNewspaper(newspaperList.getSelectionModel().getSelectedItem());
                                System.out.println(articleList.size());
                               for (Article article : articleList) {
                                   serviceArticle.deleteArticle(article);
                                   System.out.println(serviceArticle.getAllArticle());
                               }
                               loadNewspaperList();
                               alert("NICE", "Newspaper and their articles associated, deleted", Alert.AlertType.CONFIRMATION);
                            }catch (Exception e){
                                alert("ERROR", "Error interno. Try again", Alert.AlertType.ERROR);
                            }
                        }
                    }
                }
            } else {
                alert("Error", "You must select a newspaper", Alert.AlertType.INFORMATION );
            }
        } catch (Exception e) {
            alert("Error", "Failed to delete newspaper", Alert.AlertType.ERROR );
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
        alerta = new Alert(Alert.AlertType.INFORMATION);
        serviceNewspaper = new ServiceNewspaperFile();
        serviceArticle = new ServiceArticleFile();
    }
}
