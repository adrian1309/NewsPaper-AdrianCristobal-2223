package gui;


import jakarta.enterprise.util.AnnotationLiteral;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

import java.io.IOException;


public class FXMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        //SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        //final SeContainer container = initializer.initialize();

        FXMLLoader loaderMenu = new FXMLLoader(
                getClass().getResource("/fxml/FXMain.fxml"));
        BorderPane root = loaderMenu.load();

        Scene scene = new Scene(root);
        primaryStage.setTitle("NewsPaper WebStore");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);

        //container.getBeanManager().fireEvent(primaryStage, new AnnotationLiteral<StartUpScene>() {});

    }
}
