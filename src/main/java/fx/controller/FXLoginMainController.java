package fx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FXLoginMainController {
    @FXML
    public TextField fxUser;
    @FXML
    public TextField passBox;
    @FXML
    public Label errorBox;

    private FXMainController mainController;

    public void setMain(FXMainController mainController) {
        this.mainController = mainController;
    }

    public void clickLogin(ActionEvent actionEvent) {
        mainController.chargeWelcome();
        /*
        if(fxUser.getText().equals(ConfigYaml.getInstance().getUser())
                && passBox.getText().equals(ConfigYaml.getInstance().getPass())){
            //mainController.chargeWelcome();
        }else{
            errorBox.setText("User or password is wrong");
        }

         */
    }
}
