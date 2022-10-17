package fx.controller.xml;

import fx.controller.FXMainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import model.Article;
import model.xml.ReaderXML;
import service.files.ServiceArticleFile;
import service.files.ServiceReaderFile;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FXReaderByArticleByTypeXMLController implements Initializable {

    private FXMainController mainController;
    @FXML
    private ListView<ReaderXML> readerList;
    @FXML
    private ComboBox<String> typeBox = new ComboBox<String>();

    private ServiceReaderFile serviceReader;

    private ServiceArticleFile serviceArticle;

    private Alert alerta;

    public void setMainController(FXMainController fxMainController) {
        this.mainController = fxMainController;
    }
    public void loadTypesComboBox(){
        typeBox.getItems().clear();
        typeBox.getItems().addAll("economics", "sports", "science");
    }
    public void searchByType(ActionEvent actionEvent) {
        try{
            String type = typeBox.getValue();
            int valorType = 0;
            switch (type) {
                case "economics" -> valorType = 1;
                case "sports" -> valorType = 2;
                case "science" -> valorType = 3;
                default -> {
                }
            }
            List<Article> listArticle = serviceArticle.getArticleByTypeArticle(valorType);
            List<Integer> idsArticle = new ArrayList<>();
            for (Article article : listArticle) {
                idsArticle.add(article.getId());
            }
            List<ReaderXML> listReaders = serviceReader.getReadersByArticleByType(idsArticle);
            if (!listReaders.isEmpty()){
                clean();
                readerList.getItems().addAll(listReaders);
            }else{
                clean();
                alert("INFO", "Does not exit readers with articles for this type", Alert.AlertType.INFORMATION);
            }
        }catch (Exception e){
            clean();
            alert("ERROR", "Select a type", Alert.AlertType.INFORMATION);
            e.printStackTrace();
        }
    }

    public void clean(){
        readerList.getItems().clear();
        typeBox.getSelectionModel().clearSelection();
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
        serviceArticle = new ServiceArticleFile();
        loadTypesComboBox();
    }


}
