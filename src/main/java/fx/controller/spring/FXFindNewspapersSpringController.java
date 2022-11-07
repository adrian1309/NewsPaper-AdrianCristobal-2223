package fx.controller.spring;

import fx.controller.common.BasePantallaController;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Newspaper;
import service.ServiceNewspaper;
import service.spring.ServiceNewspaperSpring;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class FXFindNewspapersSpringController extends BasePantallaController implements Initializable {
    @FXML
    private TableView<Newspaper> newspaperTable;
    @FXML
    private TableColumn<Newspaper, Integer> idColumnTable;
    @FXML
    private TableColumn<Newspaper, String> nameColumnTable;
    @FXML
    private TableColumn<Newspaper, Date> releaseDateColumnTable;

    private ServiceNewspaper serviceNewspaper;

    @Inject
    public FXFindNewspapersSpringController(ServiceNewspaperSpring serviceNewspaperSpring){
        this.serviceNewspaper = serviceNewspaperSpring;
    }

    public void loadReadersList(){
        newspaperTable.getItems().clear();
        newspaperTable.getItems().addAll(serviceNewspaper.findNewspapers().get());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //serviceReader = new ServiceReaderJDBC();
        loadReadersList();
        idColumnTable.setCellValueFactory(new PropertyValueFactory<Newspaper, Integer>("id"));
        nameColumnTable.setCellValueFactory(new PropertyValueFactory<Newspaper, String>("name_newspaper"));
        releaseDateColumnTable.setCellValueFactory(new PropertyValueFactory<Newspaper, Date>("release_date"));
    }
}
