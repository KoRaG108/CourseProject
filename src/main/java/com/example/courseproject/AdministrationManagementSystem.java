package com.example.courseproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class AdministrationManagementSystem extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdministrationManagementSystem.class.getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1100, 600);

        Image image = new Image("file:./src/main/resources/assets/window-icon.png");
        stage.getIcons().add(image);
        stage.setTitle("Администрирование Приёмной Администрации");

        stage.setMinHeight(636);
        stage.setMinWidth(1114);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}