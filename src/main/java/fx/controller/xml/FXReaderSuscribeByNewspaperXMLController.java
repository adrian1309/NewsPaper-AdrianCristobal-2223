package fx.controller.xml;

import fx.controller.FXMainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import model.Newspaper;
import model.xml.ReaderXML;
import service.files.ServiceNewspaperFile;
import service.files.ServiceReaderFile;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class FXReaderSuscribeByNewspaperXMLController implements Initializable {

    private ServiceReaderFile serviceReader = new ServiceReaderFile();
    private ServiceNewspaperFile serviceNewspaper = new ServiceNewspaperFile();

    private FXMainController mainController;
    @FXML
    private ListView<ReaderXML> lvReadersSuscribe;
    @FXML
    private ComboBox<String> cbNewspaper;

    public void setMainController(FXMainController fxMainController) {
        this.mainController = fxMainController;
    }


    public void showReaders(ActionEvent actionEvent) {
        lvReadersSuscribe.getItems().clear();
        Newspaper newspaperComboBox = serviceNewspaper.getAllNewspaper().stream().filter(n -> n.getName_newspaper().equals(cbNewspaper.getValue())).collect(Collectors.toList()).get(0);
        List<ReaderXML> listReader = serviceReader.suscribeReaderByNewspaper(newspaperComboBox.getId());
        lvReadersSuscribe.getItems().addAll(listReader);

    }

    public void clean(ActionEvent actionEvent) {
        cbNewspaper.getSelectionModel().clearSelection();
        lvReadersSuscribe.getItems().clear();
    }

    public void loadNewspaper(){
        cbNewspaper.getItems().clear();
        List<Newspaper> newspaperList = serviceNewspaper.getAllNewspaper();
        List<String> newspaperNameList = newspaperList.stream().map(Newspaper::getName_newspaper).collect(Collectors.toList());
        cbNewspaper.getItems().addAll(newspaperNameList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadNewspaper();
    }
}
