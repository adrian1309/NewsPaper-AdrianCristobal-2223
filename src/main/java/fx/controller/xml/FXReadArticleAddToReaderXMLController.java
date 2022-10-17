package fx.controller.xml;

import fx.controller.FXMainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import model.Article;
import model.xml.ReaderXML;
import model.xml.ReadersXML;
import service.files.ServiceArticleFile;
import service.files.ServiceReaderFile;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FXReadArticleAddToReaderXMLController implements Initializable {

    @FXML
    private ComboBox<Integer> ratingBox;
    @FXML
    private ListView<ReaderXML> readerBox;
    @FXML
    private ListView<Article> articleBox;

    private ServiceReaderFile serviceReader;

    private ServiceArticleFile serviceArticle;

    private FXMainController fxMainController;

    private Alert alerta;

    public void setMainController(FXMainController fxMainController) {
        this.fxMainController = fxMainController;
    }

    public void showArticles(MouseEvent mouseEvent) {
        ratingBox.getSelectionModel().clearSelection();
        articleBox.getItems().clear();
        List<Article> articleList = serviceArticle.getAllArticle();
        articleBox.getItems().addAll(articleList);
    }
    public void addReadArticle(ActionEvent actionEvent) {
        try {
            //if(!ratingBox.getSelectionModel().isEmpty() && !readerBox.getSelectionModel().isEmpty() && !articleBox.getSelectionModel().isEmpty()){
            ReaderXML readerXML = readerBox.getSelectionModel().getSelectedItem();
            String nameReader = readerXML.getName();
            int idReader = 0;
            switch (nameReader){
                case "Adrian":  idReader =  1;
                case "Miguel":  idReader =  2;
            }
            Article article = articleBox.getSelectionModel().getSelectedItem();
            int idArticle = article.getId();
            int rating = ratingBox.getSelectionModel().getSelectedItem();
            try{
                serviceReader.addReadArticle(nameReader,idReader, idArticle, rating);
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
        ReadersXML readersXMLList = serviceReader.getAllReaders();
        for (ReaderXML readerXML : readersXMLList.getReaders()) {
            readerBox.getItems().add(readerXML);
        }
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
        serviceReader = new ServiceReaderFile();
        serviceArticle = new ServiceArticleFile();
        loadReaders();
        loadRating();
    }

}
