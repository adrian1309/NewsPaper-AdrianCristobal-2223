package fx.controller;

import config.file.ConfigYaml;
import fx.controller.common.BasePantallaController;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import model.Reader;
import service.ServiceReader;
import service.jdbc.ServiceReaderJDBC;

import javax.crypto.MacSpi;
import java.util.List;
import java.util.stream.Collectors;

public class FXLoginMainController extends BasePantallaController {

    @FXML
    public BorderPane rootPantallaPrincipal;
    @FXML
    public TextField fxUser;
    @FXML
    public TextField passBox;
    @FXML
    public Label errorBox;

    ServiceReader serviceReader;

    @Inject
    public FXLoginMainController(ServiceReaderJDBC serviceReaderJDBC) {
        this.serviceReader = serviceReaderJDBC;
    }

    public void clickLogin(ActionEvent actionEvent) {
        List<Reader> readerList = serviceReader.getAllReaders().get();
        List<String> userNames = readerList.stream().map(Reader::getName_reader).collect(Collectors.toList());
        for (String userName : userNames) {
            if (fxUser.getText().equals(userName)) {
                if (passBox.getText().equals(userName)){
                    Reader usuario = serviceReader.getAllReaders().get().stream().filter(reader -> reader.getName_reader().equals(fxUser.getText())).collect(Collectors.toList()).get(0);
                    if (usuario.getId() < 0) {
                        this.getPrincipalController().userName = userName;
                        this.getPrincipalController().userPassword = passBox.getText();
                        this.getPrincipalController().adminUser = true;
                        this.getPrincipalController().onLoginHecho();

                    } else {
                        this.getPrincipalController().userName = userName;
                        this.getPrincipalController().userPassword = passBox.getText();
                        this.getPrincipalController().adminUser = false;
                        this.getPrincipalController().onLoginHecho();
                    }
                } else {
                    errorBox.setText("Incorrect password");
                }
            } else {
                errorBox.setText("User not find");
            }
        }
        /*
        if(fxUser.getText().equals(ConfigYaml.getInstance().getUser())
                && passBox.getText().equals(ConfigYaml.getInstance().getPass())){
            getPrincipalController().onLoginHecho();
        }else{
            errorBox.setText("User or password is wrong");
        }

         */


    }
}
