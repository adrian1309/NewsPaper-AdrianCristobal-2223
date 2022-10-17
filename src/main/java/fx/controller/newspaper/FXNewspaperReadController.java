package fx.controller.newspaper;

import fx.controller.FXMainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Newspaper;
import service.files.ServiceNewspaperFile;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class FXNewspaperReadController implements Initializable {
    @FXML
    public TableView<Newspaper> newspaperTable;
    @FXML
    public TableColumn<Newspaper, Integer> idColumnTable;
    @FXML
    public TableColumn<Newspaper, String> nameColumnTable;
    @FXML
    public TableColumn<Newspaper, Date> releaseDateColumnTable;

    private FXMainController mainController;

    private ServiceNewspaperFile serviceNewspaperFile;

    public void setMainController(FXMainController fxMainController){
        this.mainController = fxMainController;
    }

    public void loadNewspaperList(){
        newspaperTable.getItems().clear();
        newspaperTable.getItems().addAll(serviceNewspaperFile.getAllNewspaper());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        serviceNewspaperFile = new ServiceNewspaperFile();
        idColumnTable.setCellValueFactory(new PropertyValueFactory<Newspaper, Integer>("id"));
        nameColumnTable.setCellValueFactory(new PropertyValueFactory<Newspaper, String>("name_newspaper"));
        releaseDateColumnTable.setCellValueFactory(new PropertyValueFactory<Newspaper, Date>("realise_date"));
    }


}
