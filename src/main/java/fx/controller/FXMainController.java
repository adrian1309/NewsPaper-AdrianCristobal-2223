package fx.controller;

import fx.controller.common.BasePantallaController;
import fx.controller.common.Pantallas;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;


import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

@Log4j2
public class FXMainController extends BorderPane implements Initializable{
    @FXML
    private BorderPane rootPantallaPrincipal;
    @FXML
    private MenuBar fxMenuTop;
    @FXML
    private Menu menuItemsOptions;
    @FXML
    private Menu menuItemsJDBC;
    @FXML
    private MenuItem menuBackToLogin;
    @FXML
    private MenuItem menuWelcome;
    @FXML
    private MenuItem menuAddReadArticle;
    @FXML
    private MenuItem menuReaderSubscribeNewspaper;
    private Stage primaryStage;

    Instance<Object> instance;

    private Alert alert;

    public boolean adminUser;

    public String userName;
    public String userPassword;



    public FXMainController(){}
    @Inject
    public FXMainController(Instance<Object> instance) {
        this.instance = instance;
        alert= new Alert(Alert.AlertType.NONE);
    }
    /*
    public void preLoadPantallaStarWars()  {
        try {
            if (apiStarWars == null) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/apiStarWars.fxml"));
                apiStarWars = fxmlLoader.load();
                BasePantallaController pantallaController = fxmlLoader.getController();
                pantallaController.setPrincipalController(this);
                pantallaController.setCenter(apiStarWars);
            }
        }catch (Exception e){
            Logger.getLogger("ERROR").log(Level.WARNING, e.getMessage(), e);
        }
    }

     */
    private void cambioPantalla(Pane pantallaNueva) {
        rootPantallaPrincipal.setCenter(pantallaNueva);
    }

    private void cargarPantalla(Pantallas pantalla) {
        cambioPantalla(cargarPantallaPane(pantalla.getRutaPantalla()));
    }

    private Pane cargarPantallaPane(String ruta) {
        Pane panePantalla = null;
        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(controller -> instance.select(controller).get());
            panePantalla = fxmlLoader.load(getClass().getResourceAsStream(ruta));
            BasePantallaController pantallaController = fxmlLoader.getController();
            pantallaController.setPrincipalController(this);
            pantallaController.principalCargado();


        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
        return panePantalla;
    }
    /**
     private void closeWindowEvent(WindowEvent event) {
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
     alert.getButtonTypes().remove(ButtonType.OK);
     alert.getButtonTypes().add(ButtonType.CANCEL);
     alert.getButtonTypes().add(ButtonType.YES);
     alert.setTitle("Quit application");
     alert.setContentText("Close without saving?");
     alert.initOwner(primaryStage.getOwner());
     Optional<ButtonType> res = alert.showAndWait();
     }
     */

    @FXML
    private void menuClick(ActionEvent actionEvent) {
        switch (((MenuItem) actionEvent.getSource()).getId()) {

            //Inicio
            case "menuBackToLogin" -> {
                cargarPantalla(Pantallas.LOGIN);
                fxMenuTop.setVisible(false);
            }
            case "menuWelcome" -> cargarPantalla(Pantallas.WELCOME);

            //Articles
            case "menuAddArticle" -> cargarPantalla(Pantallas.ARTICLE_CREATE);
            case "menuReadArticle" -> cargarPantalla(Pantallas.ARTICLE_READ);
            case "menuFindByTypeArticle" -> cargarPantalla(Pantallas.ARTICLE_BY_TYPE);
            case "menuDeleteArticle" -> cargarPantalla(Pantallas.ARTICLE_DELETE);
            case "menuUpdateArticle" -> cargarPantalla(Pantallas.ARTICLE_UPDATE);


            //Readers and JDBC
            case "menuAddReader" -> cargarPantalla(Pantallas.READER_CREATE);
            case "menuReadReader" -> cargarPantalla(Pantallas.READER_READ);
            case "menuDeleteReader" -> cargarPantalla(Pantallas.READER_DELETE);
            case "menuUpdateReader" -> cargarPantalla(Pantallas.READER_UPDATE);
            case "menuFindReadersSubscribeNewspaper" -> cargarPantalla(Pantallas.READERS_SUBSCRIBE_NEWSPAPER);
            case "menuFindReadersArticleType" -> cargarPantalla(Pantallas.READERS_OF_ARTICLE_TYPE);
            case "menuAddReadArticle" -> cargarPantalla(Pantallas.ADD_READ_ARTICLE);
            case "menuGetDescriptionAndNumberReadersOfEachArticle" -> cargarPantalla(Pantallas.DESCRIPTION_AND_NUMBER_READERS_OF_EACH_ARTICLE);
            case "menuGetNameOfOldestSubscribersOfNewspaper" -> cargarPantalla(Pantallas.NAME_OF_OLDEST_SUBSCRIBERS_OF_NEWSPAPER);
            case "menuReaderSubscribeNewspaper" -> cargarPantalla(Pantallas.READER_SUBSCRIBE_NEWSPAPER);
            case "menuFindNewspapersSpring" -> cargarPantalla(Pantallas.FIND_NEWSPAPERS_SPRING);
            case "menuDeleteNewspaperSpring" -> cargarPantalla(Pantallas.DELETE_NEWSPAPER_SPRING);
            case "menuAddNewspaperSpring" -> cargarPantalla(Pantallas.ADD_NEWSPAPER_SPRING);
            case "menuUpdateNewspaperSpring" -> cargarPantalla(Pantallas.NEWSPAPER_UPDATE_SPRING);
        }
    }

    public void onLoginHecho() {
        cargarPantalla(Pantallas.WELCOME);
        fxMenuTop.setVisible(true);
        esconderMenu();
    }

    public void esconderMenu(){
        if (!adminUser){
            //aqui solo ve los JDBC
            resetMenuNotVisible();
            menuItemsJDBC.setVisible(true);
            menuItemsOptions.setVisible(true);

            resetMenuItemsNotVisible();
            menuWelcome.setVisible(true);
            menuBackToLogin.setVisible(true);
            menuAddReadArticle.setVisible(true);
            menuReaderSubscribeNewspaper.setVisible(true);
        } else {
            resetMenuVisible();
            resetMenuItemsVisible();
            menuReaderSubscribeNewspaper.setVisible(false);
        }
    }

    public void resetMenuNotVisible(){
        fxMenuTop.getMenus().forEach(menu -> menu.setVisible(false));
    }
    public void resetMenuVisible(){
        fxMenuTop.getMenus().forEach(menu -> menu.setVisible(true));
    }
    public void resetMenuItemsNotVisible(){
        fxMenuTop.getMenus().forEach(menu ->
                menu.getItems().forEach(item ->
                        item.setVisible(false)));
    }
    public void resetMenuItemsVisible(){
        fxMenuTop.getMenus().forEach(menu ->
                menu.getItems().forEach(item ->
                        item.setVisible(true)));
    }




    public void setStage (Stage stage){
        primaryStage = stage;
        //primaryStage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fxMenuTop.setVisible(false);
        cargarPantalla(Pantallas.LOGIN);
    }
}