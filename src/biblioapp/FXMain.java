/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioapp;

import classeApp.Emprunt;
import classeApp.Livre;
import classeApp.Materiel;
import classeApp.Member;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author the Richenel
 */
public class FXMain extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
        Materiel.initialise();
        Member.initialise();
        Emprunt.initialise();
        Parent root =FXMLLoader.load(getClass().getResource("/fxmlFile/Login.fxml"));
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
