package com.andreypshenichnyj.iate.ui;

import com.andreypshenichnyj.iate.db.SchemaInitializer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ParserApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/andreypshenichnyj/iate/ui/main_view.fxml"));
        SchemaInitializer.initialize();
        Scene scene = new Scene(loader.load());
        primaryStage.setTitle("YAOD Parser");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}