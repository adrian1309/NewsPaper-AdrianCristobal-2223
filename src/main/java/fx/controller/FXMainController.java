package fx.controller;

import fx.controller.article.FXArticleByTypeController;
import fx.controller.article.FXArticleCreateController;
import fx.controller.newspaper.FXNewspaperDeleteController;
import fx.controller.newspaper.FXNewspaperReadController;
import fx.controller.reader.FXReaderCreateController;
import fx.controller.reader.FXReaderDeleteController;
import fx.controller.reader.FXReaderReadController;
import fx.controller.reader.FXReaderUpdateController;
import fx.controller.xml.FXReadArticleAddToReaderXMLController;
import fx.controller.xml.FXReaderByArticleByTypeXMLController;
import fx.controller.xml.FXReaderDeleteXMLController;
import fx.controller.xml.FXReaderSuscribeByNewspaperXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.Getter;
import service.files.ServiceNewspaperFile;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
public class FXMainController implements Initializable {
    @FXML
    private BorderPane fxRoot;

    public void setFxRoot(BorderPane fxRoot) {
        this.fxRoot = fxRoot;
    }

    private Stage primaryStage;

    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private MenuBar fxMenuTop;

    private AnchorPane login;
    private FXLoginMainController loginMainController;

    private AnchorPane welcome;
    private FXWelcomeController welcomeController;

    private AnchorPane readNewspaper;
    private FXNewspaperReadController readNewsPaperController;

    private AnchorPane findArticleByType;
    private FXArticleByTypeController findArticleByTypeController;

    private AnchorPane createArticle;
    private FXArticleCreateController createArticleController;

    private AnchorPane deleteNewspaper;
    private FXNewspaperDeleteController deleteNewspaperController;

    private AnchorPane readerSuscribeByNewspaperXML;
    private FXReaderSuscribeByNewspaperXMLController readerSuscribeByNewspaperXMLController;

    private AnchorPane readerByArticleByTypeXML;
    private FXReaderByArticleByTypeXMLController readerByArticleByTypeXMLController;


    private AnchorPane readArticleAddToReaderXML;
    private FXReadArticleAddToReaderXMLController readArticleAddToReaderXMLController;

    private AnchorPane readerDeleteXML;
    private FXReaderDeleteXMLController readerDeleteXMLController;

    private AnchorPane readerCreate;
    private FXReaderCreateController readerCreateController;
    private AnchorPane readerRead;
    private FXReaderReadController readerReadController;

    private AnchorPane readerDelete;
    private FXReaderDeleteController readerDeleteController;

    private AnchorPane readerUpdate;
    private FXReaderUpdateController readerUpdateController;






    public void preloadLogin() {
        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/FXLoginMain.fxml"));
            login = loaderMenu.load();
            loginMainController
                    = loaderMenu.getController();

            loginMainController.setMain(this);
        } catch (IOException ex) {
            Logger.getLogger(FXMainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void preloadWelcome() {
        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/FXWelcome.fxml"));
            welcome = loaderMenu.load();
            welcomeController = loaderMenu.getController();

        } catch (IOException ex) {
            Logger.getLogger(FXWelcomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void preloadReadNewspaper() {
        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/newspaper/FXNewspaperRead.fxml"));
            readNewspaper = loaderMenu.load();
            readNewsPaperController = loaderMenu.getController();
            readNewsPaperController.setMainController(this);

        } catch (IOException ex) {
            Logger.getLogger(FXNewspaperReadController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void preloadFindArticleByType() {
        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/article/FXArticleByType.fxml"));
            findArticleByType = loaderMenu.load();
            findArticleByTypeController = loaderMenu.getController();
            findArticleByTypeController.setMainController(this);

        } catch (IOException ex) {
            Logger.getLogger(FXArticleByTypeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void preloadCreateArticle() {
        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/article/FXArticleCreate.fxml"));
            createArticle = loaderMenu.load();
            createArticleController = loaderMenu.getController();
            createArticleController.setMainController(this);

        } catch (IOException ex) {
            Logger.getLogger(FXArticleCreateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void preloadDeleteNewspaper() {
        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/newspaper/FXNewspaperDelete.fxml"));
            deleteNewspaper = loaderMenu.load();
            deleteNewspaperController = loaderMenu.getController();
            deleteNewspaperController.setMainController(this);

        } catch (IOException ex) {
            Logger.getLogger(FXNewspaperDeleteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void preloadReaderSuscribeByNewspaperXML() {
        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/xml/FXReaderSuscribeByNewspaperXML.fxml"));
            readerSuscribeByNewspaperXML = loaderMenu.load();
            readerSuscribeByNewspaperXMLController = loaderMenu.getController();
            readerSuscribeByNewspaperXMLController.setMainController(this);

        } catch (IOException ex) {
            Logger.getLogger(FXReaderSuscribeByNewspaperXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void preloadReaderByArticleByTypeXML() {
        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/xml/FXReaderByArticleByTypeXML.fxml"));
            readerByArticleByTypeXML = loaderMenu.load();
            readerByArticleByTypeXMLController = loaderMenu.getController();
            readerByArticleByTypeXMLController.setMainController(this);

        } catch (IOException ex) {
            Logger.getLogger(FXReaderByArticleByTypeXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void preloadReadArticleAddToReaderXML() {
        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/xml/FXReadArticleAddToReaderXML.fxml"));
            readArticleAddToReaderXML = loaderMenu.load();
            readArticleAddToReaderXMLController = loaderMenu.getController();
            readArticleAddToReaderXMLController.setMainController(this);

        } catch (IOException ex) {
            Logger.getLogger(FXReadArticleAddToReaderXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void preloadReaderDeleteXML() {
        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/xml/FXReaderDeleteXML.fxml"));
            readerDeleteXML = loaderMenu.load();
            readerDeleteXMLController = loaderMenu.getController();
            readerDeleteXMLController.setMainController(this);

        } catch (IOException ex) {
            Logger.getLogger(FXReaderDeleteXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void preloadReaderCreate() {
        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/reader/FXReaderCreate.fxml"));
            readerCreate = loaderMenu.load();
            readerCreateController = loaderMenu.getController();
            readerCreateController.setMainController(this);

        } catch (IOException ex) {
            Logger.getLogger(FXReaderCreateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void preloadReaderRead() {
        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/reader/FXReaderRead.fxml"));
            readerRead = loaderMenu.load();
            readerReadController = loaderMenu.getController();
            readerReadController.setMainController(this);

        } catch (IOException ex) {
            Logger.getLogger(FXReaderReadController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void preloadReaderDelete() {
        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/reader/FXReaderDelete.fxml"));
            readerDelete = loaderMenu.load();
            readerDeleteController = loaderMenu.getController();
            readerDeleteController.setMainController(this);

        } catch (IOException ex) {
            Logger.getLogger(FXReaderDeleteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void preloadReaderUpdate() {
        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/reader/FXReaderUpdate.fxml"));
            readerUpdate = loaderMenu.load();
            readerUpdateController = loaderMenu.getController();
            readerUpdateController.setMainController(this);

        } catch (IOException ex) {
            Logger.getLogger(FXReaderUpdateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void chargeLogin() {
        fxRoot.setCenter(login);
        fxMenuTop.setVisible(false);
    }

    public void chargeWelcome() {
        fxRoot.setCenter(welcome);
        fxMenuTop.setVisible(true);
    }

    public void chargeAddArticles() {
        createArticleController.loadComboboxs();
        fxRoot.setCenter(createArticle);
    }

    public void chargeReadArticles() {
    }

    public void chargeFindTypeArticles() {
        fxRoot.setCenter(findArticleByType);
    }

    public void chargeDeleteArticles() {
    }

    public void chargeUpdateArticles() {
    }

    public void chargeAddNewspapers() {
    }

    public void chargeReadNewspapers() {
        readNewsPaperController.loadNewspaperList();
        fxRoot.setCenter(readNewspaper);
    }

    public void chargeFindByIdNewspapers() {
    }

    public void chargeDeleteNewspapers() {

    }

    public void chargeAddReaders() {
        fxRoot.setCenter(readerCreate);
    }

    public void chargeReadReaders() {
        fxRoot.setCenter(readerRead);
    }


    public void chargeFindByIdReaders() {
    }

    public void chargeDeleteReaders() {
        fxRoot.setCenter(readerDelete);
    }


    public void chargeUpdateReaders(ActionEvent actionEvent) {
        fxRoot.setCenter(readerUpdate);
    }

    public void chargeReadersSubscribeByNewspaper() {
        fxRoot.setCenter(readerSuscribeByNewspaperXML);
    }

    public void chargeReaderByArticleByTypeXML() {
        fxRoot.setCenter(readerByArticleByTypeXML);
    }

    public void chargeAddReadArticle() {
        fxRoot.setCenter(readArticleAddToReaderXML);
    }


    public void chargeDeleteReader(ActionEvent actionEvent) {
        fxRoot.setCenter(readerDeleteXML);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //txt
        preloadWelcome();
        preloadLogin();
        preloadReadNewspaper();
        preloadFindArticleByType();
        preloadCreateArticle();
        preloadDeleteNewspaper();

        //xml
        preloadReaderSuscribeByNewspaperXML();
        preloadReaderByArticleByTypeXML();
        preloadReadArticleAddToReaderXML();
        preloadReaderDeleteXML();

        //jdbc
        preloadReaderCreate();
        preloadReaderRead();
        preloadReaderDelete();
        preloadReaderUpdate();

        chargeLogin();
    }
}