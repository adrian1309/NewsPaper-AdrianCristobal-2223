package fx.controller.reader;

import fx.controller.FXMainController;
import fx.controller.common.BasePantallaController;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Newspaper;
import model.Reader;
import service.ServiceReader;
import service.files.ServiceNewspaperFile;
import service.jdbc.ServiceReaderJDBC;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class FXReaderReadController extends BasePantallaController implements Initializable {

    @FXML
    private TableView<Reader> readerTable;
    @FXML
    private TableColumn<Reader, Integer> idColumnTable;
    @FXML
    private TableColumn<Reader, String> nameColumnTable;
    @FXML
    private TableColumn<Reader, Date> birthDateColumnTable;

    private ServiceReader serviceReader;

    @Inject
    public FXReaderReadController(ServiceReader serviceReader){
        this.serviceReader = serviceReader;
    }

    private FXMainController mainController;

    public void setMainController(FXMainController fxMainController){
        this.mainController = fxMainController;
    }

    public void loadReadersList(){
        readerTable.getItems().clear();
        readerTable.getItems().addAll(serviceReader.getAllReaders().get());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //serviceReader = new ServiceReaderJDBC();
        loadReadersList();
        idColumnTable.setCellValueFactory(new PropertyValueFactory<Reader, Integer>("id"));
        nameColumnTable.setCellValueFactory(new PropertyValueFactory<Reader, String>("name_reader"));
        birthDateColumnTable.setCellValueFactory(new PropertyValueFactory<Reader, Date>("birth_reader"));
    }
}
