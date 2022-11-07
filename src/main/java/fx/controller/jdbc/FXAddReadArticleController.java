package fx.controller.jdbc;

import fx.controller.common.BasePantallaController;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import model.Article;
import model.Reader;
import service.ServiceArticle;
import service.ServiceReader;
import service.jdbc.ServiceArticleJDBC;
import service.jdbc.ServiceReaderJDBC;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FXAddReadArticleController extends BasePantallaController implements Initializable {

    @FXML
    private ListView<Reader> readerBox;
    @FXML
    private ListView<Article> articleBox;
    @FXML
    private ComboBox<Integer> ratingBox;

    private ServiceReader serviceReader;
    private ServiceArticle serviceArticle;


    private Alert alerta;

    @Inject
    public FXAddReadArticleController(ServiceReaderJDBC serviceReader, ServiceArticleJDBC serviceArticle) {
        this.serviceReader = serviceReader;
        this.serviceArticle = serviceArticle;
    }

    public void showArticles(MouseEvent mouseEvent) {
        ratingBox.getSelectionModel().clearSelection();
        articleBox.getItems().clear();
        List<Article> articleList = serviceArticle.getAllArticle();
        articleBox.getItems().addAll(articleList);
    }

    public void addReadArticle(ActionEvent actionEvent) {
        try {
            Reader reader = readerBox.getSelectionModel().getSelectedItem();
            Article article = articleBox.getSelectionModel().getSelectedItem();
            int idReader = reader.getId();
            int idArticle = article.getId();
            int rating = ratingBox.getSelectionModel().getSelectedItem();
            try{
                serviceReader.addReadArticle(idReader, idArticle, rating);
                clean();
                alert("NICE","Read article added successfully", Alert.AlertType.CONFIRMATION);
            }catch (Exception e){
                clean();
                alert("ERROR", "Error to add read article. Try again", Alert.AlertType.ERROR);
            }

        }catch (Exception e){
            clean();
            alert("ERROR","You must select all the fields", Alert.AlertType.INFORMATION);
            e.printStackTrace();
        }
    }

    public void loadReaders(){
        readerBox.getItems().clear();
        readerBox.getItems().addAll(serviceReader.getAllReaders().get());
    }


    public void loadRating(){
        ratingBox.getItems().clear();
        ratingBox.getItems().addAll(0,1,2,3,4,5,6,7,8,9,10);
    }

    public void clean() {
        readerBox.getSelectionModel().clearSelection();
        articleBox.getItems().clear();
        ratingBox.getSelectionModel().clearSelection();
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
        loadReaders();
        loadRating();
    }


}
